package com.example.data.repository

import android.content.Context
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.*
import com.example.firebase.OperationType
import com.example.firebase.handleFirestoreError
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class AcademyRepository(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth = Firebase.auth
) {
    constructor(context: Context) : this(
        FirebaseFirestore.getInstance(
            context.applicationContext.getString(R.string.firestore_database_id)
        )
    )

    private fun requireUserId(): String {
        val uid = auth.currentUser?.uid
        if (uid.isNullOrBlank()) {
            throw IllegalStateException("Firestore erişimi için Google ile giriş yapılmalıdır.")
        }
        return uid
    }

    private fun sanitizeId(raw: String): String =
        raw.replace(Regex("[^a-zA-Z0-9_\\-]"), "_").take(64).ifBlank { "item" }

    fun observeProfile(userId: String): Flow<UserProfileDoc?> = flow {
        val uid = requireUserId()
        check(uid == userId) { "Kullanıcı oturumu değişti." }
        val docRef = db.collection("users").document(userId)
        emitAll(
            docRef.snapshots()
                .map { snapshot ->
                    if (!snapshot.exists()) null
                    else snapshot.toObject(
                        UserProfileDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    handleFlowError(error, OperationType.GET, docRef.path, uid)
                    throw error
                }
        )
    }

    fun observeLessonProgress(userId: String): Flow<List<LessonProgressDoc>> = flow {
        val uid = requireUserId()
        check(uid == userId)
        val colRef = db.collection("users").document(userId).collection("lessonProgress")
        emitAll(
            colRef.snapshots()
                .map { snap ->
                    snap.toObjects(
                        LessonProgressDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )
                }
                .catch { error ->
                    handleFlowError(error, OperationType.LIST, colRef.path, uid)
                    throw error
                }
        )
    }

    fun observeExamResults(userId: String): Flow<List<ExamResultDoc>> = flow {
        val uid = requireUserId()
        check(uid == userId)
        val colRef = db.collection("users").document(userId).collection("examResults")
        emitAll(
            colRef.snapshots()
                .map { snap ->
                    snap.toObjects(
                        ExamResultDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    ).sortedByDescending { it.createdAt?.seconds ?: 0L }
                }
                .catch { error ->
                    handleFlowError(error, OperationType.LIST, colRef.path, uid)
                    throw error
                }
        )
    }

    fun observeSpacedCards(userId: String): Flow<List<SpacedCardDoc>> = flow {
        val uid = requireUserId()
        check(uid == userId)
        val colRef = db.collection("users").document(userId).collection("spacedCards")
        emitAll(
            colRef.snapshots()
                .map { snap ->
                    snap.toObjects(
                        SpacedCardDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    ).sortedBy { it.nextReviewEpochDay }
                }
                .catch { error ->
                    handleFlowError(error, OperationType.LIST, colRef.path, uid)
                    throw error
                }
        )
    }

    fun observeNotes(userId: String): Flow<List<StudyNoteDoc>> = flow {
        val uid = requireUserId()
        check(uid == userId)
        val colRef = db.collection("users").document(userId).collection("notes")
        emitAll(
            colRef.snapshots()
                .map { snap ->
                    snap.toObjects(
                        StudyNoteDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    ).sortedByDescending { it.updatedAt?.seconds ?: it.createdAt?.seconds ?: 0L }
                }
                .catch { error ->
                    handleFlowError(error, OperationType.LIST, colRef.path, uid)
                    throw error
                }
        )
    }

    fun observeStudyLogs(userId: String): Flow<List<StudyLogDoc>> = flow {
        val uid = requireUserId()
        check(uid == userId)
        val colRef = db.collection("users").document(userId).collection("studyLogs")
        emitAll(
            colRef.snapshots()
                .map { snap ->
                    snap.toObjects(
                        StudyLogDoc::class.java,
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    ).sortedBy { it.epochDay }
                }
                .catch { error ->
                    handleFlowError(error, OperationType.LIST, colRef.path, uid)
                    throw error
                }
        )
    }

    private fun handleFlowError(
        error: Throwable,
        op: OperationType,
        path: String,
        uid: String
    ) {
        val firestoreError = generateSequence(error) { it.cause }
            .filterIsInstance<FirebaseFirestoreException>()
            .firstOrNull()
        if (firestoreError == null && error is CancellationException) return
        val ex = firestoreError ?: (error as? Exception)
        if (ex != null && auth.currentUser?.uid == uid) {
            handleFirestoreError(ex, op, path)
        }
    }

    suspend fun ensureUserProfile(userId: String, displayName: String): Result<Unit> {
        val path = "users/$userId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val docRef = db.collection("users").document(uid)
            val existing = docRef.get().await()
            if (!existing.exists()) {
                val safeName = displayName.trim().ifBlank { "Değerli Öğrenci" }.take(100)
                val today = CurriculumCatalog.todayEpochDay()
                val payload = mapOf(
                    "userId" to uid,
                    "displayName" to safeName,
                    "programStartEpochDay" to today,
                    "weeklyTargetHours" to 9L,
                    "dailyMinutesTarget" to 45L,
                    "busyDayMode" to false,
                    "availableDays" to listOf(1L, 2L, 3L, 4L, 5L, 6L),
                    "notifDailyLesson" to true,
                    "notifSpacedRepetition" to true,
                    "notifWeeklyReview" to true,
                    "notifMonthlyExam" to true,
                    "notifGoalCompleted" to true,
                    "totalStudyMinutes" to 0L,
                    "currentStreakDays" to 0L,
                    "longestStreakDays" to 0L,
                    "lastStudyEpochDay" to 0L,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun updatePlannerAndNotifications(
        userId: String,
        weeklyTargetHours: Long,
        dailyMinutesTarget: Long,
        busyDayMode: Boolean,
        availableDays: List<Long>,
        notifDailyLesson: Boolean,
        notifSpacedRepetition: Boolean,
        notifWeeklyReview: Boolean,
        notifMonthlyExam: Boolean,
        notifGoalCompleted: Boolean
    ): Result<Unit> {
        val path = "users/$userId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val safeDays = availableDays.filter { it in 1L..7L }.distinct().ifEmpty { listOf(1L, 2L, 3L, 4L, 5L) }
            db.collection("users").document(uid).update(
                mapOf(
                    "weeklyTargetHours" to weeklyTargetHours.coerceIn(1L, 60L),
                    "dailyMinutesTarget" to dailyMinutesTarget.coerceIn(15L, 180L),
                    "busyDayMode" to busyDayMode,
                    "availableDays" to safeDays,
                    "notifDailyLesson" to notifDailyLesson,
                    "notifSpacedRepetition" to notifSpacedRepetition,
                    "notifWeeklyReview" to notifWeeklyReview,
                    "notifMonthlyExam" to notifMonthlyExam,
                    "notifGoalCompleted" to notifGoalCompleted,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            ).await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun upsertLessonProgress(
        userId: String,
        lesson: Lesson,
        completed: Boolean? = null,
        favorite: Boolean? = null,
        readLater: Boolean? = null,
        learnedSummary: String? = null,
        addStudyMinutes: Long = 0L,
        masteryScore: Long? = null,
        toggleTaskKey: String? = null
    ): Result<Unit> {
        val safeLessonId = sanitizeId(lesson.id)
        val path = "users/$userId/lessonProgress/$safeLessonId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val docRef = db.collection("users").document(uid)
                .collection("lessonProgress").document(safeLessonId)
            val snap = docRef.get().await()

            if (!snap.exists()) {
                val initTasks = if (toggleTaskKey != null) listOf(toggleTaskKey) else emptyList()
                val payload = mapOf(
                    "userId" to uid,
                    "lessonId" to safeLessonId,
                    "monthNumber" to lesson.monthNumber.toLong().coerceIn(1L, 12L),
                    "disciplineId" to lesson.discipline.name.take(40),
                    "completed" to (completed ?: false),
                    "favorite" to (favorite ?: false),
                    "readLater" to (readLater ?: false),
                    "learnedSummary" to (learnedSummary ?: "").take(4000),
                    "studyMinutes" to addStudyMinutes.coerceIn(0L, 10000L),
                    "masteryScore" to (masteryScore ?: 0L).coerceIn(0L, 100L),
                    "completedTasks" to initTasks,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload).await()
            } else {
                val current = snap.toObject(
                    LessonProgressDoc::class.java,
                    DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                ) ?: LessonProgressDoc()

                val nextTasks = if (toggleTaskKey != null) {
                    if (toggleTaskKey in current.completedTasks) {
                        current.completedTasks - toggleTaskKey
                    } else {
                        (current.completedTasks + toggleTaskKey).distinct().take(10)
                    }
                } else {
                    current.completedTasks
                }

                val updates = mutableMapOf<String, Any>(
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                if (completed != null) updates["completed"] = completed
                if (favorite != null) updates["favorite"] = favorite
                if (readLater != null) updates["readLater"] = readLater
                if (learnedSummary != null) updates["learnedSummary"] = learnedSummary.take(4000)
                if (addStudyMinutes > 0L) {
                    updates["studyMinutes"] = (current.studyMinutes + addStudyMinutes).coerceIn(0L, 10000L)
                }
                if (masteryScore != null) {
                    updates["masteryScore"] = maxOf(current.masteryScore, masteryScore).coerceIn(0L, 100L)
                }
                if (toggleTaskKey != null) updates["completedTasks"] = nextTasks
                docRef.update(updates).await()
            }

            if (addStudyMinutes > 0L) {
                recordStudyActivity(uid, addStudyMinutes, lessonsDelta = 1L, reviewsDelta = 0L)
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, path)
            Result.failure(e)
        }
    }

    suspend fun enrollLessonConceptsForSpacedRepetition(
        userId: String,
        lesson: Lesson
    ): Result<Unit> {
        val basePath = "users/$userId/spacedCards"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val today = CurriculumCatalog.todayEpochDay()
            lesson.keyConcepts.forEachIndexed { index, concept ->
                val cardId = sanitizeId("card_${lesson.id}_$index")
                val docRef = db.collection("users").document(uid)
                    .collection("spacedCards").document(cardId)
                val snap = docRef.get().await()
                if (!snap.exists()) {
                    val payload = mapOf(
                        "userId" to uid,
                        "conceptTitle" to concept.title.take(200),
                        "conceptSummary" to "${concept.definition} — Örnek: ${concept.everydayAnchor}".take(2000),
                        "disciplineId" to lesson.discipline.name.take(40),
                        "lessonId" to sanitizeId(lesson.id),
                        "intervalDays" to 1L,
                        "nextReviewEpochDay" to (today + 1L),
                        "reviewCount" to 0L,
                        "lastRecallLevel" to "NEW",
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                    docRef.set(payload).await()
                }
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, basePath)
            Result.failure(e)
        }
    }

    suspend fun reviewSpacedCard(
        userId: String,
        card: SpacedCardDoc,
        recallLevel: RecallLevel
    ): Result<Unit> {
        val cardId = sanitizeId(card.id)
        val path = "users/$userId/spacedCards/$cardId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val (nextInterval, nextEpoch, nextCount) = CurriculumCatalog.computeNextSpacedState(
                currentIntervalDays = card.intervalDays,
                currentReviewCount = card.reviewCount,
                recallLevel = recallLevel
            )
            db.collection("users").document(uid)
                .collection("spacedCards").document(cardId)
                .update(
                    mapOf(
                        "intervalDays" to nextInterval.coerceIn(0L, 365L),
                        "nextReviewEpochDay" to nextEpoch,
                        "reviewCount" to nextCount.coerceIn(0L, 10000L),
                        "lastRecallLevel" to recallLevel.code,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            recordStudyActivity(uid, minutesDelta = 3L, lessonsDelta = 0L, reviewsDelta = 1L)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun saveExamResult(
        userId: String,
        lessonId: String,
        examTitle: String,
        disciplineId: String,
        monthNumber: Int,
        totalScore: Long,
        correctCount: Long,
        wrongCount: Long,
        knowledgeScore: Long,
        understandingScore: Long,
        applicationScore: Long,
        analysisScore: Long,
        aiEvaluationScore: Long,
        aiEvaluationRationale: String,
        weakAreas: List<String>,
        reviewConcepts: List<String>
    ): Result<Unit> {
        val docRef = db.collection("users").document(userId).collection("examResults").document()
        val path = docRef.path
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val payload = mapOf(
                "userId" to uid,
                "lessonId" to sanitizeId(lessonId),
                "examTitle" to examTitle.trim().take(200).ifBlank { "Değerlendirme Sınavı" },
                "disciplineId" to disciplineId.take(40).ifBlank { "FELSEFE" },
                "monthNumber" to monthNumber.toLong().coerceIn(1L, 12L),
                "totalScore" to totalScore.coerceIn(0L, 100L),
                "correctCount" to correctCount.coerceIn(0L, 100L),
                "wrongCount" to wrongCount.coerceIn(0L, 100L),
                "knowledgeScore" to knowledgeScore.coerceIn(0L, 100L),
                "understandingScore" to understandingScore.coerceIn(0L, 100L),
                "applicationScore" to applicationScore.coerceIn(0L, 100L),
                "analysisScore" to analysisScore.coerceIn(0L, 100L),
                "aiEvaluationScore" to aiEvaluationScore.coerceIn(0L, 100L),
                "aiEvaluationRationale" to aiEvaluationRationale.take(5000),
                "weakAreas" to weakAreas.take(10),
                "reviewConcepts" to reviewConcepts.take(15),
                "createdAt" to FieldValue.serverTimestamp(),
                "updatedAt" to FieldValue.serverTimestamp()
            )
            docRef.set(payload).await()
            recordStudyActivity(uid, minutesDelta = 10L, lessonsDelta = 0L, reviewsDelta = 0L)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.CREATE, path)
            Result.failure(e)
        }
    }

    suspend fun saveNote(
        userId: String,
        existingNoteId: String? = null,
        title: String,
        content: String,
        quoteText: String,
        personalReflection: String,
        category: String,
        disciplineId: String,
        lessonId: String,
        tags: List<String>,
        favorite: Boolean
    ): Result<Unit> {
        val safeTitle = title.trim().take(200).ifBlank { "Başlıksız Not" }
        val safeContent = content.trim().take(10000).ifBlank { "Not içeriği" }
        val colRef = db.collection("users").document(userId).collection("notes")
        val docRef = if (existingNoteId.isNullOrBlank()) colRef.document() else colRef.document(sanitizeId(existingNoteId))
        val path = docRef.path
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val safeTags = tags.map { it.trim() }.filter { it.isNotBlank() }.distinct().take(10)
            if (existingNoteId.isNullOrBlank()) {
                val payload = mapOf(
                    "userId" to uid,
                    "title" to safeTitle,
                    "content" to safeContent,
                    "quoteText" to quoteText.trim().take(2000),
                    "personalReflection" to personalReflection.trim().take(4000),
                    "category" to category.trim().take(60).ifBlank { "Ders Notu" },
                    "disciplineId" to disciplineId.trim().take(40).ifBlank { "GENEL" },
                    "lessonId" to lessonId.trim().take(64),
                    "tags" to safeTags,
                    "favorite" to favorite,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "updatedAt" to FieldValue.serverTimestamp()
                )
                docRef.set(payload).await()
            } else {
                docRef.update(
                    mapOf(
                        "title" to safeTitle,
                        "content" to safeContent,
                        "quoteText" to quoteText.trim().take(2000),
                        "personalReflection" to personalReflection.trim().take(4000),
                        "category" to category.trim().take(60).ifBlank { "Ders Notu" },
                        "disciplineId" to disciplineId.trim().take(40).ifBlank { "GENEL" },
                        "lessonId" to lessonId.trim().take(64),
                        "tags" to safeTags,
                        "favorite" to favorite,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, if (existingNoteId.isNullOrBlank()) OperationType.CREATE else OperationType.UPDATE, path)
            Result.failure(e)
        }
    }

    suspend fun deleteNote(userId: String, noteId: String): Result<Unit> {
        val safeId = sanitizeId(noteId)
        val path = "users/$userId/notes/$safeId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            db.collection("users").document(uid).collection("notes").document(safeId).delete().await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.DELETE, path)
            Result.failure(e)
        }
    }

    suspend fun recordStudyActivity(
        userId: String,
        minutesDelta: Long,
        lessonsDelta: Long,
        reviewsDelta: Long
    ): Result<Unit> {
        val today = CurriculumCatalog.todayEpochDay()
        val logId = "day_$today"
        val logPath = "users/$userId/studyLogs/$logId"
        return try {
            val uid = requireUserId()
            check(uid == userId)
            val logRef = db.collection("users").document(uid).collection("studyLogs").document(logId)
            val logSnap = logRef.get().await()
            if (!logSnap.exists()) {
                logRef.set(
                    mapOf(
                        "userId" to uid,
                        "epochDay" to today,
                        "minutesStudied" to minutesDelta.coerceIn(0L, 1440L),
                        "lessonsTouched" to lessonsDelta.coerceIn(0L, 100L),
                        "reviewsCompleted" to reviewsDelta.coerceIn(0L, 500L),
                        "createdAt" to FieldValue.serverTimestamp(),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            } else {
                val cur = logSnap.toObject(
                    StudyLogDoc::class.java,
                    DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                ) ?: StudyLogDoc()
                logRef.update(
                    mapOf(
                        "minutesStudied" to (cur.minutesStudied + minutesDelta).coerceIn(0L, 1440L),
                        "lessonsTouched" to (cur.lessonsTouched + lessonsDelta).coerceIn(0L, 100L),
                        "reviewsCompleted" to (cur.reviewsCompleted + reviewsDelta).coerceIn(0L, 500L),
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }

            val profileRef = db.collection("users").document(uid)
            val profileSnap = profileRef.get().await()
            if (profileSnap.exists()) {
                val prof = profileSnap.toObject(
                    UserProfileDoc::class.java,
                    DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                ) ?: UserProfileDoc()
                val newStreak = when (prof.lastStudyEpochDay) {
                    today -> prof.currentStreakDays.coerceAtLeast(1L)
                    today - 1L -> prof.currentStreakDays + 1L
                    else -> 1L
                }
                val longest = maxOf(prof.longestStreakDays, newStreak)
                profileRef.update(
                    mapOf(
                        "totalStudyMinutes" to (prof.totalStudyMinutes + minutesDelta).coerceIn(0L, 1000000L),
                        "currentStreakDays" to newStreak,
                        "longestStreakDays" to longest,
                        "lastStudyEpochDay" to today,
                        "updatedAt" to FieldValue.serverTimestamp()
                    )
                ).await()
            }
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            handleFirestoreError(e, OperationType.WRITE, logPath)
            Result.failure(e)
        }
    }
}
