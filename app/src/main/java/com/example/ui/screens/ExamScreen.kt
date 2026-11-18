package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.*
import com.example.ui.ExamGradingState
import com.example.ui.components.AcademicCard
import com.example.ui.components.CompetencyBarRow
import com.example.ui.components.DisciplineBadge
import com.example.ui.theme.ScholarGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    examType: String,
    targetId: String,
    gradingState: ExamGradingState,
    onResetExam: () -> Unit,
    onSubmitExam: (
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
    ) -> Unit,
    onBack: () -> Unit,
    onOpenLesson: (String) -> Unit
) {
    BackHandler { onBack() }

    LaunchedEffect(examType, targetId) {
        onResetExam()
    }

    val singleLesson = remember(examType, targetId) {
        if (examType == "lesson") CurriculumCatalog.lessonById(targetId) else null
    }
    val monthNumber = remember(examType, targetId, singleLesson) {
        if (examType == "month") targetId.toIntOrNull()?.coerceIn(1, 12) ?: 1
        else singleLesson?.monthNumber ?: 1
    }
    val roadmap = remember(monthNumber) {
        CurriculumCatalog.monthRoadmaps.firstOrNull { it.monthNumber == monthNumber }
    }
    val examId = if (examType == "month") "month_$monthNumber" else (singleLesson?.id ?: targetId)
    val examTitle = if (examType == "month") {
        roadmap?.monthlyExamTitle ?: "Ay $monthNumber Genel Değerlendirme Sınavı"
    } else {
        "${singleLesson?.title ?: "Ders"} — Mini Sınav"
    }
    val discipline = singleLesson?.discipline ?: roadmap?.disciplines?.firstOrNull() ?: DisciplineId.FELSEFE
    val questions = remember(examType, targetId) {
        if (examType == "month") CurriculumCatalog.buildMonthlyExamQuestions(monthNumber)
        else singleLesson?.quizQuestions.orEmpty()
    }

    val mcqAnswers = remember(examId) { mutableStateMapOf<String, Int>() }
    val tfAnswers = remember(examId) { mutableStateMapOf<String, Boolean>() }
    val textAnswers = remember(examId) { mutableStateMapOf<String, String>() }
    val matchingSelections = remember(examId) { mutableStateMapOf<String, Map<Int, Int>>() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Ölçme ve Değerlendirme", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("exam_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri dön")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard(borderColor = discipline.accentColor.copy(alpha = 0.45f)) {
                        DisciplineBadge(discipline = discipline)
                        Spacer(Modifier.height(8.dp))
                        Text(examTitle, style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "${questions.size} soru • Bilgi (%25), Anlama (%25), Uygulama (%25), Analiz ve Sentez (%25)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            when (gradingState) {
                is ExamGradingState.Evaluating -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 600.dp)
                                .padding(vertical = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator()
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "Nesnel yanıtlar ve açık uçlu analiziniz 5 akademik ölçütle değerlendiriliyor...",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                is ExamGradingState.Error -> {
                    item {
                        Box(modifier = Modifier.widthIn(max = 600.dp)) {
                            AcademicCard(containerColor = MaterialTheme.colorScheme.errorContainer) {
                                Text(
                                    text = gradingState.message,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onErrorContainer
                                )
                                Spacer(Modifier.height(10.dp))
                                Button(
                                    onClick = onResetExam,
                                    modifier = Modifier.testTag("exam_retry_button")
                                ) {
                                    Text("Sınav Ekranına Dön")
                                }
                            }
                        }
                    }
                }

                is ExamGradingState.Completed -> {
                    item {
                        Box(modifier = Modifier.widthIn(max = 600.dp)) {
                            ExamResultSummaryCard(
                                result = gradingState,
                                onRetake = onResetExam,
                                onOpenRecommendedLesson = onOpenLesson
                            )
                        }
                    }
                }

                is ExamGradingState.Idle -> {
                    itemsIndexed(questions, key = { _, q -> q.id }) { index, q ->
                        Box(modifier = Modifier.widthIn(max = 600.dp)) {
                            ExamQuestionCard(
                                index = index + 1,
                                question = q,
                                selectedMcq = mcqAnswers[q.id],
                                onSelectMcq = { mcqAnswers[q.id] = it },
                                selectedTf = tfAnswers[q.id],
                                onSelectTf = { tfAnswers[q.id] = it },
                                textAnswer = textAnswers[q.id].orEmpty(),
                                onTextChange = { textAnswers[q.id] = it },
                                matchingMap = matchingSelections[q.id].orEmpty(),
                                onMatchingChange = { leftIdx, rightOrigIdx ->
                                    val cur = matchingSelections[q.id].orEmpty().toMutableMap()
                                    cur[leftIdx] = rightOrigIdx
                                    matchingSelections[q.id] = cur
                                }
                            )
                        }
                    }

                    if (questions.isNotEmpty()) {
                        item {
                            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                                Button(
                                    onClick = {
                                        onSubmitExam(
                                            examId,
                                            examTitle,
                                            discipline,
                                            monthNumber,
                                            questions,
                                            mcqAnswers.toMap(),
                                            tfAnswers.toMap(),
                                            textAnswers.toMap(),
                                            matchingSelections.toMap(),
                                            singleLesson
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 52.dp)
                                        .testTag("submit_exam_button"),
                                    shape = RoundedCornerShape(14.dp)
                                ) {
                                    Icon(Icons.Outlined.TaskAlt, contentDescription = null)
                                    Spacer(Modifier.width(8.dp))
                                    Text("Sınavı Tamamla ve Değerlendir", style = MaterialTheme.typography.labelLarge)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExamQuestionCard(
    index: Int,
    question: QuizQuestion,
    selectedMcq: Int?,
    onSelectMcq: (Int) -> Unit,
    selectedTf: Boolean?,
    onSelectTf: (Boolean) -> Unit,
    textAnswer: String,
    onTextChange: (String) -> Unit,
    matchingMap: Map<Int, Int>,
    onMatchingChange: (Int, Int) -> Unit
) {
    AcademicCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Soru $index • ${question.type.label}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = question.competency.label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(question.prompt, style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        when (question.type) {
            QuestionType.MULTIPLE_CHOICE -> {
                question.options.forEachIndexed { optIdx, optText ->
                    val selected = selectedMcq == optIdx
                    Surface(
                        color = if (selected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { onSelectMcq(optIdx) }
                            .testTag("mcq_${question.id}_$optIdx")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = selected, onClick = { onSelectMcq(optIdx) })
                            Spacer(Modifier.width(6.dp))
                            Text(optText, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            QuestionType.TRUE_FALSE -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf(true to "Doğru", false to "Yanlış").forEach { (valBool, label) ->
                        val selected = selectedTf == valBool
                        FilterChip(
                            selected = selected,
                            onClick = { onSelectTf(valBool) },
                            label = { Text(label) },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("tf_${question.id}_$valBool")
                        )
                    }
                }
            }

            QuestionType.FILL_IN_BLANK, QuestionType.SHORT_ANSWER -> {
                OutlinedTextField(
                    value = textAnswer,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("short_input_${question.id}"),
                    placeholder = { Text("Yanıtınızı yazın...") },
                    singleLine = question.type == QuestionType.FILL_IN_BLANK,
                    minLines = if (question.type == QuestionType.SHORT_ANSWER) 2 else 1,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            QuestionType.OPEN_ENDED_ANALYSIS, QuestionType.CASE_RESOLUTION -> {
                if (question.rubricHint.isNotBlank()) {
                    Text(
                        text = "Değerlendirme İpucu: ${question.rubricHint}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(6.dp))
                }
                OutlinedTextField(
                    value = textAnswer,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("open_input_${question.id}"),
                    placeholder = { Text("Kavramsal ve gerekçeli analizinizi yazın (YZ öğretmeni 5 ölçütle puanlayacaktır)...") },
                    minLines = 4,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            QuestionType.MATCHING -> {
                val shuffledRightIndices = remember(question.id) {
                    question.matchingPairs.indices.reversed()
                }
                question.matchingPairs.forEachIndexed { leftIdx, (leftText, _) ->
                    var menuOpen by remember(question.id, leftIdx) { mutableStateOf(false) }
                    val chosenRightIdx = matchingMap[leftIdx]
                    val chosenLabel = chosenRightIdx?.let {
                        question.matchingPairs.getOrNull(it)?.second
                    } ?: "Eşleşme seç..."

                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        Text(
                            text = "${leftIdx + 1}. $leftText",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(4.dp))
                        Box {
                            OutlinedButton(
                                onClick = { menuOpen = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 48.dp)
                                    .testTag("match_${question.id}_$leftIdx"),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(chosenLabel, modifier = Modifier.weight(1f))
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Seçenek aç")
                            }
                            DropdownMenu(
                                expanded = menuOpen,
                                onDismissRequest = { menuOpen = false }
                            ) {
                                shuffledRightIndices.forEach { rightOrigIdx ->
                                    DropdownMenuItem(
                                        text = { Text(question.matchingPairs[rightOrigIdx].second) },
                                        onClick = {
                                            onMatchingChange(leftIdx, rightOrigIdx)
                                            menuOpen = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExamResultSummaryCard(
    result: ExamGradingState.Completed,
    onRetake: () -> Unit,
    onOpenRecommendedLesson: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        AcademicCard(borderColor = ScholarGreen.copy(alpha = 0.5f)) {
            Text(
                text = "Sınav Sonuç Raporu",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Toplam Yeterlilik Puanı", style = MaterialTheme.typography.labelMedium)
                    Text(
                        text = "%${result.totalScore}",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Nesnel Puan: %${result.objectiveScore}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "${result.objectiveCorrect} / ${result.objectiveTotal} doğru",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(Modifier.height(14.dp))
            CompetencyBarRow("Bilgi (%25)", result.knowledgeScore, MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            CompetencyBarRow("Anlama (%25)", result.understandingScore, MaterialTheme.colorScheme.secondary)
            Spacer(Modifier.height(8.dp))
            CompetencyBarRow("Uygulama (%25)", result.applicationScore, ScholarGreen)
            Spacer(Modifier.height(8.dp))
            CompetencyBarRow("Analiz ve Sentez (%25)", result.analysisScore, MaterialTheme.colorScheme.tertiary)
        }

        if (result.aiEvaluation != null) {
            val ai = result.aiEvaluation
            AcademicCard(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)) {
                Text(
                    text = "Yapay Zekâ Açık Uçlu Değerlendirmesi (Nesnel Puandan Ayrı)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "YZ Değerlendirme Puanı: ${ai.totalAiScore} / 100",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(6.dp))
                Text("• Kavramsal Doğruluk: ${ai.conceptualAccuracy}/20", style = MaterialTheme.typography.bodySmall)
                Text("• Mantıksal Tutarlılık: ${ai.logicalConsistency}/20", style = MaterialTheme.typography.bodySmall)
                Text("• Gerekçelendirme: ${ai.justification}/20", style = MaterialTheme.typography.bodySmall)
                Text("• Örnek Kullanımı: ${ai.exampleUsage}/20", style = MaterialTheme.typography.bodySmall)
                Text("• Eleştirel Derinlik: ${ai.criticalDepth}/20", style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
                Text(ai.rationale, style = MaterialTheme.typography.bodySmall)
            }
        }

        if (result.wrongExplanations.isNotEmpty()) {
            AcademicCard {
                Text("Yanlış Yanıtlanan Sorular ve Açıklamaları", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                result.wrongExplanations.forEach { (prompt, exp) ->
                    Text(prompt, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold)
                    Text(exp, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        AcademicCard {
            Text("Eksik Alanlar ve Tekrar Önerileri", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                text = if (result.weakAreas.isEmpty()) "Belirgin bir eksik alan tespit edilmedi."
                else result.weakAreas.joinToString(" • "),
                style = MaterialTheme.typography.bodySmall
            )
            if (result.recommendedConcepts.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Tekrar Edilecek Kavramlar: ${result.recommendedConcepts.joinToString(", ")}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(
                    onClick = onRetake,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                        .testTag("retake_exam_button")
                ) {
                    Text("Tekrar Çöz")
                }
                if (result.recommendedLesson != null) {
                    Button(
                        onClick = { onOpenRecommendedLesson(result.recommendedLesson.id) },
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 48.dp)
                            .testTag("exam_next_lesson_button")
                    ) {
                        Text("Sıradaki Ders")
                    }
                }
            }
        }
    }
}
