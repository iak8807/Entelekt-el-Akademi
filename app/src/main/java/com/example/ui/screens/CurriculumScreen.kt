package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.DisciplineId
import com.example.data.model.Lesson
import com.example.data.model.LessonProgressDoc
import com.example.data.model.MonthRoadmap
import com.example.ui.DashboardSnapshot
import com.example.ui.components.AcademicCard
import com.example.ui.components.DisciplineBadge
import com.example.ui.theme.ScholarGreen

private enum class CurriculumFilterStatus(val label: String) {
    TUMU("Tümü"),
    TAMAMLANAN("Tamamlanan"),
    BEKLEYEN("Bekleyen"),
    FAVORILER("Favoriler"),
    DAHA_SONRA("Daha Sonra Oku")
}

@Composable
fun CurriculumScreen(
    snapshot: DashboardSnapshot,
    onOpenLesson: (String) -> Unit,
    onOpenMonthlyExam: (Int) -> Unit,
    onToggleFavorite: (Lesson, Boolean) -> Unit,
    onToggleReadLater: (Lesson, Boolean) -> Unit
) {
    var selectedMainTab by remember { mutableIntStateOf(0) } // 0 = 12 Ay, 1 = 8 Disiplin
    var searchQuery by remember { mutableStateOf("") }
    var selectedDiscipline by remember { mutableStateOf<DisciplineId?>(null) }
    var selectedStatus by remember { mutableStateOf(CurriculumFilterStatus.TUMU) }
    var expandedMonth by remember { mutableIntStateOf(snapshot.planSummary.currentAcademicMonth) }

    val queryLower = searchQuery.trim().lowercase(CurriculumCatalog.turkishLocale)

    fun matchesFilters(lesson: Lesson): Boolean {
        val prog = snapshot.progressMap[lesson.id]
        val statusOk = when (selectedStatus) {
            CurriculumFilterStatus.TUMU -> true
            CurriculumFilterStatus.TAMAMLANAN -> prog?.completed == true
            CurriculumFilterStatus.BEKLEYEN -> prog?.completed != true
            CurriculumFilterStatus.FAVORILER -> prog?.favorite == true
            CurriculumFilterStatus.DAHA_SONRA -> prog?.readLater == true
        }
        val discOk = selectedDiscipline == null || lesson.discipline == selectedDiscipline
        val textOk = if (queryLower.isBlank()) true else {
            lesson.title.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower) ||
                lesson.purpose.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower) ||
                lesson.summary.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower) ||
                lesson.keyConcepts.any {
                    it.title.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower) ||
                        it.definition.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower)
                } ||
                lesson.detailedContent.any {
                    it.heading.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower) ||
                        it.body.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower)
                } ||
                lesson.keyThinkers.any {
                    it.name.lowercase(CurriculumCatalog.turkishLocale).contains(queryLower)
                }
        }
        return statusOk && discOk && textOk
    }

    val filteredLessons = remember(
        queryLower,
        selectedDiscipline,
        selectedStatus,
        snapshot.progressMap
    ) {
        CurriculumCatalog.allLessons.filter(::matchesFilters)
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Müfredat ve Bilgi Kütüphanesi",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )

                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    SegmentedButton(
                        selected = selectedMainTab == 0,
                        onClick = { selectedMainTab = 0 },
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2),
                        modifier = Modifier.testTag("curriculum_tab_months")
                    ) {
                        Text("12 Aylık Takvim")
                    }
                    SegmentedButton(
                        selected = selectedMainTab == 1,
                        onClick = { selectedMainTab = 1 },
                        shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2),
                        modifier = Modifier.testTag("curriculum_tab_disciplines")
                    ) {
                        Text("8 Disiplin Kütüphanesi")
                    }
                }

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("curriculum_search_input"),
                    placeholder = { Text("Ders başlığı, kavram, düşünür veya içerikte ara...") },
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Ara") },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = { searchQuery = "" },
                                modifier = Modifier.testTag("clear_search_button")
                            ) {
                                Icon(Icons.Outlined.Close, contentDescription = "Aramayı temizle")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CurriculumFilterStatus.entries.forEach { st ->
                        FilterChip(
                            selected = selectedStatus == st,
                            onClick = { selectedStatus = st },
                            label = { Text(st.label) },
                            modifier = Modifier.testTag("filter_status_${st.name}")
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedDiscipline == null,
                        onClick = { selectedDiscipline = null },
                        label = { Text("Tüm Disiplinler") },
                        modifier = Modifier.testTag("filter_disc_all")
                    )
                    DisciplineId.entries.forEach { disc ->
                        FilterChip(
                            selected = selectedDiscipline == disc,
                            onClick = {
                                selectedDiscipline = if (selectedDiscipline == disc) null else disc
                            },
                            label = { Text(disc.shortTitle) },
                            modifier = Modifier.testTag("filter_disc_${disc.name}")
                        )
                    }
                }
            }
        }

        if (queryLower.isNotBlank() || selectedStatus != CurriculumFilterStatus.TUMU || selectedDiscipline != null) {
            item {
                Text(
                    text = "${filteredLessons.size} ders eşleşti",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp)
                )
            }
            items(filteredLessons, key = { it.id }) { lesson ->
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    LessonListCard(
                        lesson = lesson,
                        progress = snapshot.progressMap[lesson.id],
                        onOpen = { onOpenLesson(lesson.id) },
                        onToggleFavorite = { onToggleFavorite(lesson, it) },
                        onToggleReadLater = { onToggleReadLater(lesson, it) }
                    )
                }
            }
        } else if (selectedMainTab == 0) {
            items(CurriculumCatalog.monthRoadmaps, key = { it.monthNumber }) { roadmap ->
                val monthLessons = CurriculumCatalog.lessonsForMonth(roadmap.monthNumber)
                val completedInMonth = monthLessons.count { snapshot.progressMap[it.id]?.completed == true }
                val isExpanded = expandedMonth == roadmap.monthNumber
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    MonthRoadmapCard(
                        roadmap = roadmap,
                        lessons = monthLessons,
                        completedCount = completedInMonth,
                        progressMap = snapshot.progressMap,
                        isExpanded = isExpanded,
                        onToggleExpand = {
                            expandedMonth = if (isExpanded) -1 else roadmap.monthNumber
                        },
                        onOpenLesson = onOpenLesson,
                        onOpenMonthlyExam = { onOpenMonthlyExam(roadmap.monthNumber) },
                        onToggleFavorite = onToggleFavorite,
                        onToggleReadLater = onToggleReadLater
                    )
                }
            }
        } else {
            items(DisciplineId.entries, key = { it.name }) { discipline ->
                val discLessons = CurriculumCatalog.lessonsForDiscipline(discipline)
                val completedCount = discLessons.count { snapshot.progressMap[it.id]?.completed == true }
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    DisciplineLibraryCard(
                        discipline = discipline,
                        lessons = discLessons,
                        completedCount = completedCount,
                        progressMap = snapshot.progressMap,
                        onOpenLesson = onOpenLesson,
                        onToggleFavorite = onToggleFavorite,
                        onToggleReadLater = onToggleReadLater
                    )
                }
            }
        }
    }
}

@Composable
private fun MonthRoadmapCard(
    roadmap: MonthRoadmap,
    lessons: List<Lesson>,
    completedCount: Int,
    progressMap: Map<String, LessonProgressDoc>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onOpenLesson: (String) -> Unit,
    onOpenMonthlyExam: () -> Unit,
    onToggleFavorite: (Lesson, Boolean) -> Unit,
    onToggleReadLater: (Lesson, Boolean) -> Unit
) {
    AcademicCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "AY ${roadmap.monthNumber}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = roadmap.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = roadmap.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onToggleExpand,
                modifier = Modifier.testTag("expand_month_${roadmap.monthNumber}")
            ) {
                Icon(
                    imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Daralt" else "Genişlet"
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$completedCount / ${lessons.size} ders tamamlandı",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            TextButton(
                onClick = onToggleExpand,
                modifier = Modifier.testTag("toggle_month_btn_${roadmap.monthNumber}")
            ) {
                Text(if (isExpanded) "Gizle" else "Modülleri Gör")
            }
        }

        if (isExpanded) {
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Ana Hedef: ${roadmap.mainGoal}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            Text("Haftalık Modüller:", style = MaterialTheme.typography.labelLarge)
            roadmap.weeklyModules.forEach { mod ->
                Text(
                    text = "• $mod",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(8.dp))
            Text("Okuma Metinleri:", style = MaterialTheme.typography.labelLarge)
            Text(
                text = roadmap.readingTexts.joinToString(" • "),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(8.dp))
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = roadmap.capstoneProjectTitle,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = roadmap.capstoneProjectDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            lessons.forEach { lesson ->
                LessonListCard(
                    lesson = lesson,
                    progress = progressMap[lesson.id],
                    onOpen = { onOpenLesson(lesson.id) },
                    onToggleFavorite = { onToggleFavorite(lesson, it) },
                    onToggleReadLater = { onToggleReadLater(lesson, it) }
                )
                Spacer(Modifier.height(8.dp))
            }
            Button(
                onClick = onOpenMonthlyExam,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .testTag("month_exam_button_${roadmap.monthNumber}"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Outlined.Quiz, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(roadmap.monthlyExamTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
private fun DisciplineLibraryCard(
    discipline: DisciplineId,
    lessons: List<Lesson>,
    completedCount: Int,
    progressMap: Map<String, LessonProgressDoc>,
    onOpenLesson: (String) -> Unit,
    onToggleFavorite: (Lesson, Boolean) -> Unit,
    onToggleReadLater: (Lesson, Boolean) -> Unit
) {
    var expanded by remember(discipline.name) { mutableStateOf(false) }

    AcademicCard(borderColor = discipline.accentColor.copy(alpha = 0.45f)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DisciplineBadge(discipline = discipline)
            Text(
                text = "$completedCount / ${lessons.size} ders",
                style = MaterialTheme.typography.labelMedium,
                color = discipline.accentColor
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = discipline.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = discipline.purpose,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Önemli İsimler: ${discipline.keyThinkers.take(6).joinToString(", ")}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.testTag("expand_disc_${discipline.name}")
        ) {
            Text(if (expanded) "Alt Konuları ve Dersleri Gizle" else "Alt Konular & ${lessons.size} Dersi Göster")
        }

        if (expanded) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Alt Başlıklar: ${discipline.subtopics.joinToString(" • ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(10.dp))
            lessons.forEach { lesson ->
                LessonListCard(
                    lesson = lesson,
                    progress = progressMap[lesson.id],
                    onOpen = { onOpenLesson(lesson.id) },
                    onToggleFavorite = { onToggleFavorite(lesson, it) },
                    onToggleReadLater = { onToggleReadLater(lesson, it) }
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun LessonListCard(
    lesson: Lesson,
    progress: LessonProgressDoc?,
    onOpen: () -> Unit,
    onToggleFavorite: (Boolean) -> Unit,
    onToggleReadLater: (Boolean) -> Unit
) {
    val isCompleted = progress?.completed == true
    val isFav = progress?.favorite == true
    val isReadLater = progress?.readLater == true
    val mastery = progress?.masteryScore ?: 0L

    Surface(
        onClick = onOpen,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("lesson_card_${lesson.id}"),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DisciplineBadge(discipline = lesson.discipline)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Tamamlandı",
                            tint = ScholarGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                    }
                    Text(
                        text = "${lesson.durationMinutes} dk",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = lesson.purpose,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ay ${lesson.monthNumber} • Ustalık: %$mastery",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Row {
                    IconButton(
                        onClick = { onToggleFavorite(!isFav) },
                        modifier = Modifier.testTag("fav_lesson_${lesson.id}")
                    ) {
                        Icon(
                            imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorilere ekle",
                            tint = if (isFav) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(
                        onClick = { onToggleReadLater(!isReadLater) },
                        modifier = Modifier.testTag("read_later_${lesson.id}")
                    ) {
                        Icon(
                            imageVector = if (isReadLater) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Daha sonra oku",
                            tint = if (isReadLater) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
