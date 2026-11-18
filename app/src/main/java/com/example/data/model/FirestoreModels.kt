package com.example.data.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@Keep
@IgnoreExtraProperties
data class UserProfileDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val displayName: String = "Öğrenci",
    val programStartEpochDay: Long = 20400L,
    val weeklyTargetHours: Long = 9L,
    val dailyMinutesTarget: Long = 45L,
    val busyDayMode: Boolean = false,
    val availableDays: List<Long> = listOf(1L, 2L, 3L, 4L, 5L, 6L),
    val notifDailyLesson: Boolean = true,
    val notifSpacedRepetition: Boolean = true,
    val notifWeeklyReview: Boolean = true,
    val notifMonthlyExam: Boolean = true,
    val notifGoalCompleted: Boolean = true,
    val totalStudyMinutes: Long = 0L,
    val currentStreakDays: Long = 0L,
    val longestStreakDays: Long = 0L,
    val lastStudyEpochDay: Long = 0L,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class LessonProgressDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val lessonId: String = "",
    val monthNumber: Long = 1L,
    val disciplineId: String = "",
    val completed: Boolean = false,
    val favorite: Boolean = false,
    val readLater: Boolean = false,
    val learnedSummary: String = "",
    val studyMinutes: Long = 0L,
    val masteryScore: Long = 0L,
    val completedTasks: List<String> = emptyList(),
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class ExamResultDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val lessonId: String = "",
    val examTitle: String = "",
    val disciplineId: String = "",
    val monthNumber: Long = 1L,
    val totalScore: Long = 0L,
    val correctCount: Long = 0L,
    val wrongCount: Long = 0L,
    val knowledgeScore: Long = 0L,
    val understandingScore: Long = 0L,
    val applicationScore: Long = 0L,
    val analysisScore: Long = 0L,
    val aiEvaluationScore: Long = 0L,
    val aiEvaluationRationale: String = "",
    val weakAreas: List<String> = emptyList(),
    val reviewConcepts: List<String> = emptyList(),
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class SpacedCardDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val conceptTitle: String = "",
    val conceptSummary: String = "",
    val disciplineId: String = "",
    val lessonId: String = "",
    val intervalDays: Long = 1L,
    val nextReviewEpochDay: Long = 20400L,
    val reviewCount: Long = 0L,
    val lastRecallLevel: String = "NEW",
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class StudyNoteDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val title: String = "",
    val content: String = "",
    val quoteText: String = "",
    val personalReflection: String = "",
    val category: String = "Ders Notu",
    val disciplineId: String = "GENEL",
    val lessonId: String = "",
    val tags: List<String> = emptyList(),
    val favorite: Boolean = false,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)

@Keep
@IgnoreExtraProperties
data class StudyLogDoc(
    @DocumentId val id: String = "",
    val userId: String = "",
    val epochDay: Long = 20400L,
    val minutesStudied: Long = 0L,
    val lessonsTouched: Long = 0L,
    val reviewsCompleted: Long = 0L,
    @ServerTimestamp val createdAt: Timestamp? = null,
    @ServerTimestamp val updatedAt: Timestamp? = null
)
