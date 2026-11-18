package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.DisciplineId
import com.example.data.model.RecallLevel
import com.example.data.model.SpacedCardDoc
import com.example.ui.DashboardSnapshot
import com.example.ui.MonthlyReportState
import com.example.ui.components.*
import com.example.ui.theme.ScholarGreen

@Composable
fun ProgressScreen(
    snapshot: DashboardSnapshot,
    monthlyReportState: MonthlyReportState,
    onReviewCard: (SpacedCardDoc, RecallLevel) -> Unit,
    onGenerateMonthlyReport: (Int) -> Unit
) {
    var selectedSection by remember { mutableIntStateOf(0) } // 0=Grafikler, 1=Tekrar, 2=Raporlar, 3=Rozetler
    var reportMonth by remember { mutableIntStateOf(snapshot.planSummary.currentAcademicMonth) }

    val disciplinePcts = remember(snapshot.progressMap) {
        DisciplineId.entries.map { disc ->
            val lessons = CurriculumCatalog.lessonsForDiscipline(disc)
            val done = lessons.count { snapshot.progressMap[it.id]?.completed == true }
            val pct = if (lessons.isEmpty()) 0 else (done * 100) / lessons.size
            disc to pct
        }
    }
    val monthlyPcts = remember(snapshot.progressMap) {
        (1..12).map { m ->
            val lessons = CurriculumCatalog.lessonsForMonth(m)
            val done = lessons.count { snapshot.progressMap[it.id]?.completed == true }
            if (lessons.isEmpty()) 0 else (done * 100) / lessons.size
        }
    }
    val today = remember { CurriculumCatalog.todayEpochDay() }
    val weeklyChartData = remember(snapshot.studyLogs, today) {
        val logMap = snapshot.studyLogs.associateBy { it.epochDay }
        val dayLabels = listOf("G-6", "G-5", "G-4", "G-3", "G-2", "Dün", "Bugün")
        (0..6).map { offset ->
            val day = today - (6 - offset)
            dayLabels[offset] to (logMap[day]?.minutesStudied ?: 0L)
        }
    }
    val examScores = remember(snapshot.examResults) {
        snapshot.examResults.reversed().map { it.totalScore.toInt() }
    }
    val repeatSuccessPct = remember(snapshot.spacedCards) {
        if (snapshot.spacedCards.isEmpty()) 0 else {
            val mastered = snapshot.spacedCards.count {
                it.lastRecallLevel == RecallLevel.EASY.code || it.lastRecallLevel == RecallLevel.PARTIAL.code
            }
            (mastered * 100) / snapshot.spacedCards.size
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 600.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Öğrenme Analitiği ve Gelişim",
                    style = MaterialTheme.typography.headlineSmall
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SummaryMetricTile("Genel", "%${snapshot.overallCompletionPct}", Modifier.weight(1f))
                    SummaryMetricTile("Sınav Ort.", "%${snapshot.averageExamScore}", Modifier.weight(1f))
                    SummaryMetricTile("Aylık Süre", "${snapshot.monthlyStudyMinutes} dk", Modifier.weight(1f))
                    SummaryMetricTile("Tekrar", "%$repeatSuccessPct", Modifier.weight(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Analiz Grafikleri", "Akıllı Tekrar", "Aylık Rapor (YZ)", "Rozetler").forEachIndexed { idx, label ->
                        FilterChip(
                            selected = selectedSection == idx,
                            onClick = { selectedSection = idx },
                            label = { Text(label) },
                            modifier = Modifier.testTag("progress_tab_$idx")
                        )
                    }
                }
            }
        }

        when (selectedSection) {
            0 -> {
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader("Sekiz Disiplin Karşılaştırması")
                            Spacer(Modifier.height(8.dp))
                            DisciplineComparisonChart(disciplinePcts)
                        }
                    }
                }
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader("12 Aylık İlerleme Çubukları")
                            Spacer(Modifier.height(10.dp))
                            TwelveMonthProgressChart(monthlyPcts)
                        }
                    }
                }
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader(
                                title = "Haftalık Çalışma Süresi",
                                subtitle = "Hedef: günde ${snapshot.profile.dailyMinutesTarget} dk • En uzun seri: ${snapshot.profile.longestStreakDays} gün"
                            )
                            Spacer(Modifier.height(10.dp))
                            WeeklyStudyMinutesChart(
                                dayLabelsAndMinutes = weeklyChartData,
                                targetMinutes = snapshot.profile.dailyMinutesTarget
                            )
                        }
                    }
                }
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader(
                                title = "Sınav Puanları Seyri",
                                subtitle = "${snapshot.examResults.size} kayıtlı sınav"
                            )
                            Spacer(Modifier.height(8.dp))
                            if (examScores.isEmpty()) {
                                Text(
                                    text = "Henüz tamamlanmış sınav bulunmuyor. Ders veya aylık değerlendirme çözdüğünüzde grafik burada oluşacaktır.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            } else {
                                ExamScoresLineChart(examScores)
                            }
                            val strongest = disciplinePcts.maxByOrNull { it.second }
                            val weakest = disciplinePcts.minByOrNull { it.second }
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = "En Güçlü Alan: ${strongest?.first?.shortTitle ?: "-"} (%${strongest?.second ?: 0}) • Odaklanılacak Alan: ${weakest?.first?.shortTitle ?: "-"} (%${weakest?.second ?: 0})",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            1 -> {
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader(
                                title = "Akıllı Tekrar Sistemi (1–3–7–14–30 Gün)",
                                subtitle = "Toplam ${snapshot.spacedCards.size} kavram • Bugün bekleyen: ${snapshot.dueCards.size}"
                            )
                            Spacer(Modifier.height(10.dp))
                            if (snapshot.dueCards.isEmpty()) {
                                Text(
                                    text = "Bugün tekrar sırası gelen kavram yok. Bir dersi tamamladığınızda veya 'Tekrar Programına Ekle'ye bastığınızda kavramlar otomatik döngüye girer.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            } else {
                                snapshot.dueCards.forEach { card ->
                                    DueSpacedCardItem(card = card, onReview = { level -> onReviewCard(card, level) })
                                    Spacer(Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }
            }

            2 -> {
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader(
                                title = "Aylık Öğrenme ve Değerlendirme Raporu",
                                subtitle = "6 temel soruyu mevcut öğrenme verilerinize göre yanıtlar"
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                (1..12).forEach { m ->
                                    FilterChip(
                                        selected = reportMonth == m,
                                        onClick = { reportMonth = m },
                                        label = { Text("Ay $m") },
                                        modifier = Modifier.testTag("report_month_chip_$m")
                                    )
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Button(
                                onClick = { onGenerateMonthlyReport(reportMonth) },
                                enabled = monthlyReportState !is MonthlyReportState.Loading,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 48.dp)
                                    .testTag("generate_monthly_report_button"),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Outlined.AutoAwesome, contentDescription = null)
                                Spacer(Modifier.width(8.dp))
                                Text("Ay $reportMonth Yapay Zekâ Raporunu Oluştur")
                            }

                            Spacer(Modifier.height(12.dp))
                            when (monthlyReportState) {
                                is MonthlyReportState.Idle -> {
                                    Text(
                                        text = "Rapor oluşturduğunuzda bu ay öğrendikleriniz, başarılı ve eksik alanlarınız ile disiplinler arası senteziniz burada görüntülenecektir.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                is MonthlyReportState.Loading -> {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                                        Text("Aylık rapor oluşturuluyor...", style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                                is MonthlyReportState.Error -> {
                                    Text(
                                        text = monthlyReportState.message,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                                is MonthlyReportState.Success -> {
                                    val r = monthlyReportState.report
                                    ReportQABlock("1. Bu ay neler öğrendim?", r.learnedThisMonth)
                                    ReportQABlock("2. Hangi konularda başarılı oldum?", r.successfulTopics)
                                    ReportQABlock("3. Hangi konularda eksiklerim var?", r.missingAreas)
                                    ReportQABlock("4. Hangi kavramları tekrar etmeliyim?", r.conceptsToReview)
                                    ReportQABlock("5. Bir sonraki ay hangi konulara ağırlık vermeliyim?", r.nextMonthFocus)
                                    ReportQABlock("6. Farklı disiplinler arasında hangi bağlantıları kurabildim?", r.interdisciplinaryLinks)
                                }
                            }
                        }
                    }
                }
            }

            3 -> {
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            SectionHeader(
                                title = "Akademik Başarı Rozetleri",
                                subtitle = "${snapshot.badges.count { it.isEarned }} / ${snapshot.badges.size} rozet kazanıldı"
                            )
                            Spacer(Modifier.height(8.dp))
                            snapshot.badges.forEach { badge ->
                                Surface(
                                    color = if (badge.isEarned) ScholarGreen.copy(alpha = 0.12f)
                                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (badge.isEarned) Icons.Outlined.WorkspacePremium else Icons.Outlined.Lock,
                                            contentDescription = badge.title,
                                            tint = if (badge.isEarned) ScholarGreen else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = badge.title,
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = badge.description,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Text(
                                            text = badge.criteriaLabel,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (badge.isEarned) ScholarGreen else MaterialTheme.colorScheme.onSurfaceVariant
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
}

@Composable
private fun SummaryMetricTile(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun ReportQABlock(question: String, answer: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            text = question,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(2.dp))
        Text(answer, style = MaterialTheme.typography.bodySmall)
    }
}
