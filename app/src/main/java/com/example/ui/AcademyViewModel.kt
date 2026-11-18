package com.example.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.*
import com.example.data.repository.AcademyRepository
import com.example.data.repository.AiMonthlyReport
import com.example.data.repository.AiOpenEndedEvaluation
import com.example.data.repository.AiTutorRepository
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

private const val TAG = "AcademyVM"

sealed interface DataLoadState<out T> {
    data object Loading : DataLoadState<Nothing>
    data class Success<T>(val data: T) : DataLoadState<T>
    data class Error(val message: String) : DataLoadState<Nothing>
}

data class TutorChatMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val modeLabel: String = ""
)

sealed interface TutorStreamState {
    data object Idle : TutorStreamState
    data object Loading : TutorStreamState
    data class Streaming(val partialText: String) : TutorStreamState
    data class Error(val message: String) : TutorStreamState
}

sealed interface ExamGradingState {
    data object Idle : ExamGradingState
    data object Evaluating : ExamGradingState
    data class Completed(
        val totalScore: Int,
        val objectiveScore: Int,
        val objectiveCorrect: Int,
        val objectiveTotal: Int,
        val knowledgeScore: Int,
        val understandingScore: Int,
        val applicationScore: Int,
        val analysisScore: Int,
        val aiEvaluation: AiOpenEndedEvaluation?,
        val wrongExplanations: List<Pair<String, String>>,
        val weakAreas: List<String>,
        val recommendedConcepts: List<String>,
        val recommendedLesson: Lesson?
    ) : ExamGradingState
    data class Error(val message: String) : ExamGradingState
}

sealed interface MonthlyReportState {
    data object Idle : MonthlyReportState
    data object Loading : MonthlyReportState
    data class Success(val monthNumber: Int, val report: AiMonthlyReport) : MonthlyReportState
    data class Error(val message: String) : MonthlyReportState
}

data class DashboardSnapshot(
    val profile: UserProfileDoc,
    val progressMap: Map<String, LessonProgressDoc>,
    val examResults: List<ExamResultDoc>,
    val spacedCards: List<SpacedCardDoc>,
    val notes: List<StudyNoteDoc>,
    val studyLogs: List<StudyLogDoc>,
    val planSummary: CurriculumCatalog.StudyPlanSummary,
    val dueCards: List<SpacedCardDoc>,
    val badges: List<AcademicBadge>,
    val overallCompletionPct: Int,
    val averageExamScore: Int,
    val weeklyStudyMinutes: Long,
    val monthlyStudyMinutes: Long,
    val todayStudyMinutes: Long
)

class AcademyViewModel(
    private val repository: AcademyRepository,
    private val aiRepository: AiTutorRepository,
    private val currentUserId: String,
    initialDisplayName: String
) : ViewModel() {

    private val _statusBanner = MutableStateFlow<String?>(null)
    val statusBanner: StateFlow<String?> = _statusBanner.asStateFlow()

    private val _selectedTutorMode = MutableStateFlow(TutorMode.KONU_ANLATIMI)
    val selectedTutorMode: StateFlow<TutorMode> = _selectedTutorMode.asStateFlow()

    private val _selectedTutorDiscipline = MutableStateFlow(DisciplineId.FELSEFE)
    val selectedTutorDiscipline: StateFlow<DisciplineId> = _selectedTutorDiscipline.asStateFlow()

    private val _activeTutorLessonId = MutableStateFlow<String?>(null)
    val activeTutorLessonId: StateFlow<String?> = _activeTutorLessonId.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<TutorChatMessage>>(
        listOf(
            TutorChatMessage(
                id = "welcome_0",
                isUser = false,
                text = "Merhaba! Ben kişisel entelektüel gelişim öğretmeninizim. Seçtiğiniz ders, disiplin ve öğrenme moduna göre konuları derinlemesine açıklayabilir, Sokratik sorularla düşüncenizi sınayabilir veya kavram haritaları kurabilirim. Bugün neyi inceleyelim?",
                modeLabel = TutorMode.KONU_ANLATIMI.title
            )
        )
    )
    val chatMessages: StateFlow<List<TutorChatMessage>> = _chatMessages.asStateFlow()

    private val _tutorStreamState = MutableStateFlow<TutorStreamState>(TutorStreamState.Idle)
    val tutorStreamState: StateFlow<TutorStreamState> = _tutorStreamState.asStateFlow()

    private val _examGradingState = MutableStateFlow<ExamGradingState>(ExamGradingState.Idle)
    val examGradingState: StateFlow<ExamGradingState> = _examGradingState.asStateFlow()

    private val _monthlyReportState = MutableStateFlow<MonthlyReportState>(MonthlyReportState.Idle)
    val monthlyReportState: StateFlow<MonthlyReportState> = _monthlyReportState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.ensureUserProfile(currentUserId, initialDisplayName)
                .onFailure { err ->
                    _statusBanner.value = "Profil eşitlenemedi: ${err.localizedMessage ?: "Bilinmeyen hata"}"
                }
        }
    }

    private fun <T> Flow<T>.unwrapFirestoreErrors(label: String): Flow<Result<T>> =
        this.map { Result.success(it) }
            .catch { error ->
                val firestoreError = generateSequence(error) { it.cause }
                    .filterIsInstance<FirebaseFirestoreException>()
                    .firstOrNull()
                if (firestoreError == null && error is CancellationException) throw error
                val root = firestoreError ?: error
                Log.w(TAG, "Stream error ($label)", root)
                emit(Result.failure(root))
            }

    val dashboardState: StateFlow<DataLoadState<DashboardSnapshot>> = combine(
        repository.observeProfile(currentUserId).unwrapFirestoreErrors("profile"),
        repository.observeLessonProgress(currentUserId).unwrapFirestoreErrors("lessonProgress"),
        repository.observeExamResults(currentUserId).unwrapFirestoreErrors("examResults"),
        repository.observeSpacedCards(currentUserId).unwrapFirestoreErrors("spacedCards"),
        repository.observeNotes(currentUserId).unwrapFirestoreErrors("notes"),
        repository.observeStudyLogs(currentUserId).unwrapFirestoreErrors("studyLogs")
    ) { results ->
        @Suppress("UNCHECKED_CAST")
        val profileRes = results[0] as Result<UserProfileDoc?>
        @Suppress("UNCHECKED_CAST")
        val progressRes = results[1] as Result<List<LessonProgressDoc>>
        @Suppress("UNCHECKED_CAST")
        val examsRes = results[2] as Result<List<ExamResultDoc>>
        @Suppress("UNCHECKED_CAST")
        val cardsRes = results[3] as Result<List<SpacedCardDoc>>
        @Suppress("UNCHECKED_CAST")
        val notesRes = results[4] as Result<List<StudyNoteDoc>>
        @Suppress("UNCHECKED_CAST")
        val logsRes = results[5] as Result<List<StudyLogDoc>>

        val firstFailure = listOf(profileRes, progressRes, examsRes, cardsRes, notesRes, logsRes)
            .firstOrNull { it.isFailure }?.exceptionOrNull()
        if (firstFailure != null) {
            return@combine DataLoadState.Error(
                firstFailure.localizedMessage ?: "Veriler yüklenirken bir sorun oluştu."
            )
        }

        val profile = profileRes.getOrNull() ?: UserProfileDoc(userId = currentUserId)
        val progressList = progressRes.getOrNull().orEmpty()
        val progressMap = progressList.associateBy { it.lessonId }
        val exams = examsRes.getOrNull().orEmpty()
        val cards = cardsRes.getOrNull().orEmpty()
        val notes = notesRes.getOrNull().orEmpty()
        val logs = logsRes.getOrNull().orEmpty()

        val today = CurriculumCatalog.todayEpochDay()
        val plan = CurriculumCatalog.computeStudyPlan(profile, progressMap, today)
        val dueCards = cards.filter { it.nextReviewEpochDay <= today }
        val badges = CurriculumCatalog.computeBadges(profile, progressMap, exams)

        val totalLessons = CurriculumCatalog.allLessons.size.coerceAtLeast(1)
        val completedCount = progressList.count { it.completed }
        val overallPct = ((completedCount * 100f) / totalLessons).toInt().coerceIn(0, 100)
        val avgExam = if (exams.isEmpty()) 0 else exams.map { it.totalScore }.average().toInt()
        val weekMin = logs.filter { it.epochDay >= today - 6L }.sumOf { it.minutesStudied }
        val monthMin = logs.filter { it.epochDay >= today - 29L }.sumOf { it.minutesStudied }
        val todayMin = logs.firstOrNull { it.epochDay == today }?.minutesStudied ?: 0L

        DataLoadState.Success(
            DashboardSnapshot(
                profile = profile,
                progressMap = progressMap,
                examResults = exams,
                spacedCards = cards,
                notes = notes,
                studyLogs = logs,
                planSummary = plan,
                dueCards = dueCards,
                badges = badges,
                overallCompletionPct = overallPct,
                averageExamScore = avgExam,
                weeklyStudyMinutes = weekMin,
                monthlyStudyMinutes = monthMin,
                todayStudyMinutes = todayMin
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = DataLoadState.Loading
    )

    fun clearBanner() {
        _statusBanner.value = null
    }

    fun toggleLessonComplete(lesson: Lesson, markCompleted: Boolean) {
        viewModelScope.launch {
            val res = repository.upsertLessonProgress(
                userId = currentUserId,
                lesson = lesson,
                completed = markCompleted,
                addStudyMinutes = if (markCompleted) lesson.durationMinutes.toLong() else 0L
            )
            if (markCompleted) {
                repository.enrollLessonConceptsForSpacedRepetition(currentUserId, lesson)
            }
            res.onSuccess {
                _statusBanner.value = if (markCompleted) {
                    "Ders tamamlandı ve temel kavramlar Akıllı Tekrar programına eklendi."
                } else {
                    "Ders tamamlanma durumu güncellendi."
                }
            }.onFailure {
                _statusBanner.value = "Kaydedilemedi: ${it.localizedMessage ?: "Hata"}"
            }
        }
    }

    fun toggleFavorite(lesson: Lesson, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.upsertLessonProgress(currentUserId, lesson, favorite = isFavorite)
                .onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun toggleReadLater(lesson: Lesson, isReadLater: Boolean) {
        viewModelScope.launch {
            repository.upsertLessonProgress(currentUserId, lesson, readLater = isReadLater)
                .onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun toggleLessonTask(lesson: Lesson, taskKey: String) {
        viewModelScope.launch {
            repository.upsertLessonProgress(
                userId = currentUserId,
                lesson = lesson,
                toggleTaskKey = taskKey,
                addStudyMinutes = 5L
            ).onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun saveLearnedSummary(lesson: Lesson, summaryText: String) {
        viewModelScope.launch {
            repository.upsertLessonProgress(
                userId = currentUserId,
                lesson = lesson,
                learnedSummary = summaryText
            ).onSuccess {
                _statusBanner.value = "'Bu dersten ne öğrendim?' notunuz kaydedildi."
            }.onFailure {
                _statusBanner.value = "Not kaydedilemedi: ${it.localizedMessage}"
            }
        }
    }

    fun enrollLessonConcepts(lesson: Lesson) {
        viewModelScope.launch {
            repository.enrollLessonConceptsForSpacedRepetition(currentUserId, lesson)
                .onSuccess {
                    _statusBanner.value = "Ders kavramları 1-3-7-14-30 gün tekrar döngüsüne alındı."
                }
                .onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun submitSpacedReview(card: SpacedCardDoc, recallLevel: RecallLevel) {
        viewModelScope.launch {
            repository.reviewSpacedCard(currentUserId, card, recallLevel)
                .onSuccess {
                    _statusBanner.value = "${card.conceptTitle}: ${recallLevel.label} olarak güncellendi."
                }
                .onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun saveStudyNote(
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
    ) {
        viewModelScope.launch {
            repository.saveNote(
                userId = currentUserId,
                existingNoteId = existingNoteId,
                title = title,
                content = content,
                quoteText = quoteText,
                personalReflection = personalReflection,
                category = category,
                disciplineId = disciplineId,
                lessonId = lessonId,
                tags = tags,
                favorite = favorite
            ).onSuccess {
                _statusBanner.value = "Not bilgi bankanıza kaydedildi."
            }.onFailure {
                _statusBanner.value = "Not kaydedilemedi: ${it.localizedMessage}"
            }
        }
    }

    fun deleteStudyNote(noteId: String) {
        viewModelScope.launch {
            repository.deleteNote(currentUserId, noteId)
                .onSuccess { _statusBanner.value = "Not silindi." }
                .onFailure { _statusBanner.value = it.localizedMessage }
        }
    }

    fun updatePlannerSettings(
        weeklyHours: Long,
        dailyMinutes: Long,
        busyDayMode: Boolean,
        availableDays: List<Long>,
        notifDailyLesson: Boolean,
        notifSpacedRepetition: Boolean,
        notifWeeklyReview: Boolean,
        notifMonthlyExam: Boolean,
        notifGoalCompleted: Boolean
    ) {
        viewModelScope.launch {
            repository.updatePlannerAndNotifications(
                userId = currentUserId,
                weeklyTargetHours = weeklyHours,
                dailyMinutesTarget = dailyMinutes,
                busyDayMode = busyDayMode,
                availableDays = availableDays,
                notifDailyLesson = notifDailyLesson,
                notifSpacedRepetition = notifSpacedRepetition,
                notifWeeklyReview = notifWeeklyReview,
                notifMonthlyExam = notifMonthlyExam,
                notifGoalCompleted = notifGoalCompleted
            ).onSuccess {
                _statusBanner.value = "Çalışma takvimi ve bildirim tercihleri güncellendi."
            }.onFailure {
                _statusBanner.value = it.localizedMessage
            }
        }
    }

    // ==================== AI TUTOR ====================

    fun setTutorMode(mode: TutorMode) {
        _selectedTutorMode.value = mode
    }

    fun setTutorContext(disciplineId: DisciplineId, lessonId: String? = null) {
        _selectedTutorDiscipline.value = disciplineId
        _activeTutorLessonId.value = lessonId
    }

    fun sendTutorMessage(userPrompt: String) {
        val trimmed = userPrompt.trim()
        if (trimmed.isBlank()) return
        if (_tutorStreamState.value is TutorStreamState.Loading || _tutorStreamState.value is TutorStreamState.Streaming) {
            return
        }

        val currentMode = _selectedTutorMode.value
        val userMsg = TutorChatMessage(
            id = "u_${System.currentTimeMillis()}",
            isUser = true,
            text = trimmed,
            modeLabel = currentMode.title
        )
        val priorHistory = _chatMessages.value.map { it.isUser to it.text }
        _chatMessages.value = _chatMessages.value + userMsg
        _tutorStreamState.value = TutorStreamState.Loading

        val snap = (dashboardState.value as? DataLoadState.Success)?.data
        val activeLesson = _activeTutorLessonId.value?.let { CurriculumCatalog.lessonById(it) }
        val completedCount = snap?.planSummary?.completedCount ?: 0
        val weakAreas = snap?.examResults?.flatMap { it.weakAreas }?.distinct()?.take(5)?.joinToString(", ").orEmpty()

        viewModelScope.launch {
            val builder = StringBuilder()
            try {
                aiRepository.streamTutorResponse(
                    userMessage = trimmed,
                    mode = currentMode,
                    activeLesson = activeLesson,
                    selectedDiscipline = _selectedTutorDiscipline.value,
                    completedLessonCount = completedCount,
                    weakAreasSummary = weakAreas,
                    chatHistory = priorHistory
                ).collect { chunk ->
                    builder.append(chunk)
                    _tutorStreamState.value = TutorStreamState.Streaming(builder.toString())
                }

                val finalText = builder.toString().trim()
                if (finalText.isNotBlank()) {
                    _chatMessages.value = _chatMessages.value + TutorChatMessage(
                        id = "m_${System.currentTimeMillis()}",
                        isUser = false,
                        text = finalText,
                        modeLabel = currentMode.title
                    )
                    _tutorStreamState.value = TutorStreamState.Idle
                    repository.recordStudyActivity(currentUserId, minutesDelta = 3L, lessonsDelta = 0L, reviewsDelta = 0L)
                } else {
                    _tutorStreamState.value = TutorStreamState.Error("Yapay zekâ öğretmeni boş yanıt döndürdü.")
                }
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Tutor stream timed out", e)
                _tutorStreamState.value = TutorStreamState.Error("Öğretmen yanıtı 60 saniyede tamamlanamadı. Lütfen tekrar deneyin.")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Tutor stream failed", e)
                _tutorStreamState.value = TutorStreamState.Error(
                    e.localizedMessage ?: "Yapay zekâ bağlantısı kurulamadı."
                )
            }
        }
    }

    // ==================== EXAM GRADING ====================

    fun resetExamState() {
        _examGradingState.value = ExamGradingState.Idle
    }

    fun evaluateAndSaveExam(
        examId: String,
        examTitle: String,
        discipline: DisciplineId,
        monthNumber: Int,
        questions: List<QuizQuestion>,
        selectedMcqAnswers: Map<String, Int>,
        selectedTfAnswers: Map<String, Boolean>,
        textAnswers: Map<String, String>,
        matchingSelections: Map<String, Map<Int, Int>>,
        lessonIfSingle: Lesson?
    ) {
        viewModelScope.launch {
            _examGradingState.value = ExamGradingState.Evaluating
            try {
                var objCorrect = 0
                var objTotal = 0
                val wrongExplanations = mutableListOf<Pair<String, String>>()
                val competencyBuckets = mutableMapOf(
                    CompetencyType.BILGI to mutableListOf<Int>(),
                    CompetencyType.ANLAMA to mutableListOf<Int>(),
                    CompetencyType.UYGULAMA to mutableListOf<Int>(),
                    CompetencyType.ANALIZ_SENTEZ to mutableListOf<Int>()
                )
                var openEndedEval: AiOpenEndedEvaluation? = null

                for (q in questions) {
                    when (q.type) {
                        QuestionType.MULTIPLE_CHOICE -> {
                            objTotal++
                            val chosen = selectedMcqAnswers[q.id]
                            val ok = chosen == q.correctOptionIndex
                            if (ok) objCorrect++ else wrongExplanations.add(q.prompt to q.explanation)
                            competencyBuckets[q.competency]?.add(if (ok) 100 else 0)
                        }
                        QuestionType.TRUE_FALSE -> {
                            objTotal++
                            val chosen = selectedTfAnswers[q.id]
                            val ok = chosen == q.correctBool
                            if (ok) objCorrect++ else wrongExplanations.add(q.prompt to q.explanation)
                            competencyBuckets[q.competency]?.add(if (ok) 100 else 0)
                        }
                        QuestionType.FILL_IN_BLANK, QuestionType.SHORT_ANSWER -> {
                            objTotal++
                            val raw = textAnswers[q.id].orEmpty().trim().lowercase(CurriculumCatalog.turkishLocale)
                            val ok = raw.isNotBlank() && q.acceptedAnswers.any { ans ->
                                raw.contains(ans.lowercase(CurriculumCatalog.turkishLocale))
                            }
                            if (ok) objCorrect++ else wrongExplanations.add(q.prompt to q.explanation)
                            competencyBuckets[q.competency]?.add(if (ok) 100 else 0)
                        }
                        QuestionType.MATCHING -> {
                            objTotal++
                            val sel = matchingSelections[q.id].orEmpty()
                            val ok = q.matchingPairs.indices.all { idx -> sel[idx] == idx }
                            if (ok) objCorrect++ else wrongExplanations.add(q.prompt to q.explanation)
                            competencyBuckets[q.competency]?.add(if (ok) 100 else 0)
                        }
                        QuestionType.OPEN_ENDED_ANALYSIS, QuestionType.CASE_RESOLUTION -> {
                            val studentText = textAnswers[q.id].orEmpty().trim()
                            if (studentText.isNotBlank()) {
                                val aiRes = aiRepository.evaluateOpenEndedAnswer(
                                    questionPrompt = q.prompt,
                                    rubricHint = q.rubricHint,
                                    referenceExplanation = q.explanation,
                                    studentAnswer = studentText
                                )
                                aiRes.onSuccess { eval ->
                                    openEndedEval = eval
                                    competencyBuckets[q.competency]?.add(eval.totalAiScore)
                                }.onFailure { err ->
                                    _examGradingState.value = ExamGradingState.Error(
                                        "Açık uçlu yanıt YZ ile değerlendirilemedi: ${err.localizedMessage ?: "Bağlantı hatası"}"
                                    )
                                    return@launch
                                }
                            } else {
                                competencyBuckets[q.competency]?.add(0)
                            }
                        }
                    }
                }

                val objScore = if (objTotal == 0) 100 else ((objCorrect * 100f) / objTotal).toInt()
                fun avgBucket(type: CompetencyType): Int {
                    val list = competencyBuckets[type].orEmpty()
                    return if (list.isEmpty()) objScore else list.average().toInt().coerceIn(0, 100)
                }

                val knowledge = avgBucket(CompetencyType.BILGI)
                val understanding = avgBucket(CompetencyType.ANLAMA)
                val application = avgBucket(CompetencyType.UYGULAMA)
                val analysis = avgBucket(CompetencyType.ANALIZ_SENTEZ)
                val weightedTotal = ((knowledge + understanding + application + analysis) / 4.0).toInt().coerceIn(0, 100)

                val weakAreas = buildList {
                    if (knowledge < 70) add("Bilgi (%$knowledge)")
                    if (understanding < 70) add("Anlama (%$understanding)")
                    if (application < 70) add("Uygulama (%$application)")
                    if (analysis < 70) add("Analiz ve Sentez (%$analysis)")
                    openEndedEval?.missingAreas?.forEach { if (it.isNotBlank()) add(it) }
                }.distinct().take(8)

                val reviewConcepts = buildList {
                    openEndedEval?.recommendedConcepts?.forEach { add(it) }
                    lessonIfSingle?.keyConcepts?.take(2)?.forEach { add(it.title) }
                }.distinct().take(10)

                val nextRecLesson = CurriculumCatalog.allLessons
                    .firstOrNull { it.monthNumber >= monthNumber && it.id != examId }

                val aiScoreVal = openEndedEval?.totalAiScore ?: 0
                val aiRationaleVal = openEndedEval?.rationale ?: "Bu sınav nesnel sorular üzerinden değerlendirildi."

                repository.saveExamResult(
                    userId = currentUserId,
                    lessonId = examId,
                    examTitle = examTitle,
                    disciplineId = discipline.name,
                    monthNumber = monthNumber,
                    totalScore = weightedTotal.toLong(),
                    correctCount = objCorrect.toLong(),
                    wrongCount = (objTotal - objCorrect).coerceAtLeast(0).toLong(),
                    knowledgeScore = knowledge.toLong(),
                    understandingScore = understanding.toLong(),
                    applicationScore = application.toLong(),
                    analysisScore = analysis.toLong(),
                    aiEvaluationScore = aiScoreVal.toLong(),
                    aiEvaluationRationale = aiRationaleVal,
                    weakAreas = weakAreas,
                    reviewConcepts = reviewConcepts
                )

                if (lessonIfSingle != null) {
                    repository.upsertLessonProgress(
                        userId = currentUserId,
                        lesson = lessonIfSingle,
                        masteryScore = weightedTotal.toLong(),
                        toggleTaskKey = "quiz"
                    )
                    repository.enrollLessonConceptsForSpacedRepetition(currentUserId, lessonIfSingle)
                }

                _examGradingState.value = ExamGradingState.Completed(
                    totalScore = weightedTotal,
                    objectiveScore = objScore,
                    objectiveCorrect = objCorrect,
                    objectiveTotal = objTotal,
                    knowledgeScore = knowledge,
                    understandingScore = understanding,
                    applicationScore = application,
                    analysisScore = analysis,
                    aiEvaluation = openEndedEval,
                    wrongExplanations = wrongExplanations,
                    weakAreas = weakAreas,
                    recommendedConcepts = reviewConcepts,
                    recommendedLesson = nextRecLesson
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Exam grading error", e)
                _examGradingState.value = ExamGradingState.Error(e.localizedMessage ?: "Sınav değerlendirilemedi.")
            }
        }
    }

    // ==================== MONTHLY AI REPORT ====================

    fun generateMonthlyAiReport(monthNumber: Int) {
        val snap = (dashboardState.value as? DataLoadState.Success)?.data ?: return
        val roadmap = CurriculumCatalog.monthRoadmaps.firstOrNull { it.monthNumber == monthNumber } ?: return
        _monthlyReportState.value = MonthlyReportState.Loading

        val monthLessonIds = CurriculumCatalog.lessonsForMonth(monthNumber).map { it.id }.toSet()
        val completedTitles = snap.progressMap.values
            .filter { it.completed && it.lessonId in monthLessonIds }
            .mapNotNull { CurriculumCatalog.lessonById(it.lessonId)?.title }
        val monthExams = snap.examResults.filter { it.monthNumber == monthNumber.toLong() }
        val avgMonthScore = if (monthExams.isEmpty()) snap.averageExamScore else monthExams.map { it.totalScore }.average().toInt()
        val dueTitles = snap.dueCards.map { it.conceptTitle }.take(6)
        val weak = monthExams.flatMap { it.weakAreas }.distinct().take(6)

        viewModelScope.launch {
            aiRepository.generateMonthlyReport(
                monthNumber = monthNumber,
                monthTitle = roadmap.title,
                completedLessonTitles = completedTitles,
                averageExamScore = avgMonthScore,
                dueConceptTitles = dueTitles,
                weakAreas = weak,
                totalMinutesThisMonth = snap.monthlyStudyMinutes
            ).onSuccess { report ->
                _monthlyReportState.value = MonthlyReportState.Success(monthNumber, report)
            }.onFailure { err ->
                _monthlyReportState.value = MonthlyReportState.Error(
                    err.localizedMessage ?: "Aylık rapor oluşturulamadı."
                )
            }
        }
    }
}
