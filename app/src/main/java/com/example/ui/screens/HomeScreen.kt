package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.RecallLevel
import com.example.data.model.SpacedCardDoc
import com.example.ui.DashboardSnapshot
import com.example.ui.components.AcademicCard
import com.example.ui.components.DisciplineBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.ScholarGreen

@Composable
fun HomeScreen(
    snapshot: DashboardSnapshot,
    onOpenLesson: (String) -> Unit,
    onToggleTask: (com.example.data.model.Lesson, String) -> Unit,
    onReviewCard: (SpacedCardDoc, RecallLevel) -> Unit,
    onNavigateTab: (String) -> Unit,
    onOpenNotes: () -> Unit,
    onOpenExam: (String, String) -> Unit
) {
    val todayEpoch = remember { CurriculumCatalog.todayEpochDay() }
    val todayFormatted = remember(todayEpoch) { CurriculumCatalog.formatTurkishDate(todayEpoch) }
    val startFormatted = remember(snapshot.profile.programStartEpochDay) {
        CurriculumCatalog.formatShortDate(snapshot.profile.programStartEpochDay)
    }
    val daysElapsed = (todayEpoch - snapshot.profile.programStartEpochDay).coerceAtLeast(1L)
    val remainingDays = (365L - daysElapsed).coerceAtLeast(0L)
    val finishFormatted = remember(snapshot.planSummary.estimatedCompletionEpochDay) {
        CurriculumCatalog.formatShortDate(snapshot.planSummary.estimatedCompletionEpochDay)
    }

    val lesson = snapshot.planSummary.recommendedLesson
    val lessonProgress = snapshot.progressMap[lesson.id]
    val completedTasks = lessonProgress?.completedTasks.orEmpty()
    val avgMastery = if (snapshot.progressMap.isEmpty()) 0 else {
        snapshot.progressMap.values.map { it.masteryScore }.average().toInt()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // A. KARŞILAMA ALANI
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 600.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.img_academy_hero),
                        contentDescription = "Akademi kütüphane görseli",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .matchParentSize()
                            .clip(MaterialTheme.shapes.large)
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.84f),
                                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.94f)
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = todayFormatted,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondaryContainer
                        )
                        Text(
                            text = "Hoş geldin, ${snapshot.profile.displayName}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "12 Aylık Entelektüel Yolculuk • Ay ${snapshot.planSummary.currentAcademicMonth}/12",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                        )
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f),
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            HeroMetric("Başlangıç", startFormatted)
                            HeroMetric("Geçen Süre", "$daysElapsed gün")
                            HeroMetric("Kalan Süre", "$remainingDays gün")
                            HeroMetric("Tahmini Bitiş", finishFormatted)
                        }
                    }
                }
            }
        }

        // B. GENEL İLERLEME
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Genel İlerleme",
                        subtitle = "Tamamlama ile ustalık puanı birbirinden bağımsız ölçülür"
                    )
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "12 Aylık Program İlerlemesi",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "%${snapshot.overallCompletionPct} (${snapshot.planSummary.completedCount}/${snapshot.planSummary.totalLessonsCount})",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { snapshot.overallCompletionPct / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MiniStatChip(
                            label = "Bugün",
                            value = "${snapshot.todayStudyMinutes} dk",
                            modifier = Modifier.weight(1f)
                        )
                        MiniStatChip(
                            label = "Bu Hafta",
                            value = "${snapshot.weeklyStudyMinutes} dk",
                            modifier = Modifier.weight(1f)
                        )
                        MiniStatChip(
                            label = "Ustalık",
                            value = "%$avgMastery",
                            modifier = Modifier.weight(1f)
                        )
                        MiniStatChip(
                            label = "Seri",
                            value = "${snapshot.profile.currentStreakDays} gün",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // C. BUGÜNÜN DERSİ / YOĞUN GÜN MODU
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard(
                    borderColor = lesson.discipline.accentColor.copy(alpha = 0.45f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DisciplineBadge(discipline = lesson.discipline)
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            if (snapshot.planSummary.isTodayRestDay) {
                                AssistChip(
                                    onClick = {},
                                    label = { Text("Dinlenme Günü") }
                                )
                            }
                            if (snapshot.planSummary.carriedOverCount > 0) {
                                AssistChip(
                                    onClick = {},
                                    label = { Text("${snapshot.planSummary.carriedOverCount} devreden") }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    if (snapshot.profile.busyDayMode) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Yoğun Gün Modu Aktif (15 Dakika Planı)",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "1. Temel kavramı oku (${lesson.keyConcepts.firstOrNull()?.title ?: "Ana kavram"})\n2. 5 dk aktif hatırlama tekrarı yap\n3. 1 düşünme sorusunu yanıtla",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                    }

                    Text(
                        text = "Bugünün Önerilen Dersi",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Ay ${lesson.monthNumber} • ${lesson.durationMinutes} dk (${lesson.lengthCategory.label})",
                        style = MaterialTheme.typography.labelMedium,
                        color = lesson.discipline.accentColor
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = lesson.purpose,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(14.dp))
                    Button(
                        onClick = { onOpenLesson(lesson.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("start_today_lesson_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Derse Başla", style = MaterialTheme.typography.labelLarge)
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Derse başla"
                        )
                    }
                }
            }
        }

        // D. GÜNLÜK GÖREVLER
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Günlük Görevler",
                        subtitle = "${lesson.title} için öğrenme döngüsü"
                    )
                    Spacer(Modifier.height(6.dp))
                    val dailyTasks = listOf(
                        "read" to "Bugünkü konuyu oku ve özetle",
                        "recall" to "Aktif hatırlama kartlarını yanıtla",
                        "quiz" to "Mini değerlendirme sınavını tamamla",
                        "note" to "'Bu dersten ne öğrendim?' notu yaz",
                        "practice" to "Günlük yaşam uygulama görevini işaretle"
                    )
                    dailyTasks.forEach { (key, label) ->
                        val checked = key in completedTasks
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { onToggleTask(lesson, key) }
                                .padding(horizontal = 4.dp, vertical = 4.dp)
                                .testTag("daily_task_$key"),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { onToggleTask(lesson, key) }
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (checked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // E. BUGÜN TEKRAR ETMEN GEREKENLER
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Bugün Tekrar Etmen Gerekenler",
                        subtitle = if (snapshot.dueCards.isEmpty()) {
                            "Harika! Bugün bekleyen tekrar kartınız yok."
                        } else {
                            "${snapshot.dueCards.size} kavram aralıklı tekrar bekliyor (1-3-7-14-30 gün)"
                        },
                        actionLabel = "Tümü",
                        onAction = { onNavigateTab("progress") }
                    )
                    if (snapshot.dueCards.isNotEmpty()) {
                        Spacer(Modifier.height(8.dp))
                        snapshot.dueCards.take(3).forEach { card ->
                            DueSpacedCardItem(
                                card = card,
                                onReview = { level -> onReviewCard(card, level) }
                            )
                            Spacer(Modifier.height(10.dp))
                        }
                    }
                }
            }
        }

        // F. EĞİTİM TAKVİMİ ÖZETİ & HIZLI ERİŞİM
        item {
            val currentRoadmap = CurriculumCatalog.monthRoadmaps.firstOrNull {
                it.monthNumber == snapshot.planSummary.currentAcademicMonth
            }
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Eğitim Takvimi ve Hızlı Erişim",
                        subtitle = currentRoadmap?.let { "Ay ${it.monthNumber}: ${it.title}" }
                    )
                    if (currentRoadmap != null) {
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = currentRoadmap.mainGoal,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        QuickAccessTile(
                            title = "Öğretmen",
                            icon = Icons.Outlined.Psychology,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("quick_tutor_tile"),
                            onClick = { onNavigateTab("tutor") }
                        )
                        QuickAccessTile(
                            title = "Müfredat",
                            icon = Icons.Outlined.AutoStories,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("quick_curriculum_tile"),
                            onClick = { onNavigateTab("curriculum") }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        QuickAccessTile(
                            title = "Notlarım",
                            icon = Icons.Outlined.EditNote,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("quick_notes_tile"),
                            onClick = onOpenNotes
                        )
                        QuickAccessTile(
                            title = "Ay ${snapshot.planSummary.currentAcademicMonth} Sınavı",
                            icon = Icons.Outlined.Quiz,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("quick_monthly_exam_tile"),
                            onClick = {
                                onOpenExam("month", snapshot.planSummary.currentAcademicMonth.toString())
                            }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    OutlinedButton(
                        onClick = { onNavigateTab("progress") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("quick_progress_report_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Outlined.Analytics, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Gelişim Raporumu ve Analizleri Gör")
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroMetric(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun MiniStatChip(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DueSpacedCardItem(
    card: SpacedCardDoc,
    onReview: (RecallLevel) -> Unit
) {
    var showAnswer by remember(card.id) { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = card.conceptTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${card.intervalDays} gün aralık",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.height(6.dp))
            if (!showAnswer) {
                OutlinedButton(
                    onClick = { showAnswer = true },
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .testTag("reveal_spaced_${card.id}"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Tanımı ve Örneği Göster")
                }
            } else {
                Text(
                    text = card.conceptSummary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Hatırlama Düzeyiniz:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    RecallLevel.entries.forEach { level ->
                        val shortLabel = when (level) {
                            RecallLevel.NONE -> "Hiç"
                            RecallLevel.HARD -> "Zor"
                            RecallLevel.PARTIAL -> "Kısmen"
                            RecallLevel.EASY -> "Kolay"
                        }
                        val tint: Color = when (level) {
                            RecallLevel.NONE -> MaterialTheme.colorScheme.error
                            RecallLevel.HARD -> MaterialTheme.colorScheme.secondary
                            RecallLevel.PARTIAL -> MaterialTheme.colorScheme.primary
                            RecallLevel.EASY -> ScholarGreen
                        }
                        FilledTonalButton(
                            onClick = { onReview(level) },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("recall_${level.code}_${card.id}"),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = tint.copy(alpha = 0.14f),
                                contentColor = tint
                            )
                        ) {
                            Text(
                                text = shortLabel,
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickAccessTile(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier.heightIn(min = 64.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
