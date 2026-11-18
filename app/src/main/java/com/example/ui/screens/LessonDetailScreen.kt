package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.DisciplineId
import com.example.data.model.Lesson
import com.example.data.model.LessonProgressDoc
import com.example.ui.components.AcademicCard
import com.example.ui.components.ConceptMapDiagram
import com.example.ui.components.DisciplineBadge
import com.example.ui.theme.ScholarGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lessonId: String,
    progress: LessonProgressDoc?,
    onBack: () -> Unit,
    onToggleComplete: (Lesson, Boolean) -> Unit,
    onToggleFavorite: (Lesson, Boolean) -> Unit,
    onToggleReadLater: (Lesson, Boolean) -> Unit,
    onSaveLearnedSummary: (Lesson, String) -> Unit,
    onEnrollSpacedRepetition: (Lesson) -> Unit,
    onStartMiniExam: (String) -> Unit,
    onDiscussWithTutor: (DisciplineId, String) -> Unit
) {
    BackHandler { onBack() }

    val lesson = remember(lessonId) { CurriculumCatalog.lessonById(lessonId) }
    if (lesson == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Ders Bulunamadı") },
                    navigationIcon = {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("lesson_back_button")) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("İstenen ders bulunamadı.")
            }
        }
        return
    }

    val isCompleted = progress?.completed == true
    val isFav = progress?.favorite == true
    val isReadLater = progress?.readLater == true
    val mastery = progress?.masteryScore ?: 0L
    var learnedText by remember(lesson.id, progress?.learnedSummary) {
        mutableStateOf(progress?.learnedSummary.orEmpty())
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Ay ${lesson.monthNumber} • Ders", maxLines = 1) },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("lesson_detail_back")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri dön")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onToggleFavorite(lesson, !isFav) },
                        modifier = Modifier.testTag("lesson_detail_fav")
                    ) {
                        Icon(
                            imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favori",
                            tint = if (isFav) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = { onToggleReadLater(lesson, !isReadLater) },
                        modifier = Modifier.testTag("lesson_detail_bookmark")
                    ) {
                        Icon(
                            imageVector = if (isReadLater) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Daha sonra oku",
                            tint = if (isReadLater) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                        )
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
            // 1. BAŞLIK, SÜRE, TAMAMLANMA VE USTALIK
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard(borderColor = lesson.discipline.accentColor.copy(alpha = 0.45f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DisciplineBadge(discipline = lesson.discipline)
                            Text(
                                text = "${lesson.durationMinutes} dk • ${lesson.lengthCategory.label}",
                                style = MaterialTheme.typography.labelMedium,
                                color = lesson.discipline.accentColor
                            )
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = lesson.title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AssistChip(
                                onClick = { onToggleComplete(lesson, !isCompleted) },
                                label = {
                                    Text(if (isCompleted) "Tamamlandı" else "Okunuyor")
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = if (isCompleted) Icons.Filled.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                                        contentDescription = null,
                                        tint = if (isCompleted) ScholarGreen else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                modifier = Modifier.testTag("lesson_status_chip")
                            )
                            AssistChip(
                                onClick = { onStartMiniExam(lesson.id) },
                                label = { Text("Öğrenme Ustalığı: %$mastery") },
                                leadingIcon = {
                                    Icon(Icons.Outlined.Analytics, contentDescription = null)
                                },
                                modifier = Modifier.testTag("lesson_mastery_chip")
                            )
                        }
                    }
                }
            }

            // 2. AMAÇ, 3. ÖĞRENME HEDEFLERİ (4 SEVİYE), 4. ÖN BİLGİLER
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("1. Dersin Amacı")
                        Text(lesson.purpose, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("2. Öğrenme Hedefleri (4 Öğrenme Seviyesi)")
                        lesson.learningOutcomes.forEachIndexed { idx, outcome ->
                            Text(
                                text = "• $outcome",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("3. Gerekli Ön Bilgiler")
                        Text(
                            text = lesson.prerequisites.joinToString(" • "),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 5. TEMEL KAVRAMLAR + ARALIKLI TEKRAR KAYDI
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LessonSectionTitle("4. Temel Kavramlar")
                            TextButton(
                                onClick = { onEnrollSpacedRepetition(lesson) },
                                modifier = Modifier.testTag("enroll_spaced_button")
                            ) {
                                Text("Tekrar Programına Ekle")
                            }
                        }
                        Spacer(Modifier.height(6.dp))
                        lesson.keyConcepts.forEach { concept ->
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = concept.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = lesson.discipline.accentColor,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(concept.definition, style = MaterialTheme.typography.bodySmall)
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = "Günlük Örnek: ${concept.everydayAnchor}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 6. DETAYLI KONU ANLATIMI & 7. TARİHSEL ARKA PLAN
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("5. Ayrıntılı Konu Anlatımı")
                        lesson.detailedContent.forEach { section ->
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = section.heading,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(section.body, style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("6. Tarihsel ve Kuramsal Arka Plan")
                        Text(lesson.historicalBackground, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            // 8. GERÇEK HAYAT ÖRNEKLERİ, 9. ÖNEMLİ KİŞİLER, 10. KARŞILAŞTIRMALI AÇIKLAMALAR
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("7. Gerçek Hayat Örnekleri")
                        lesson.realLifeExamples.forEach { ex ->
                            Text("• $ex", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 3.dp))
                        }
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("8. Önemli Kişiler ve Katkıları")
                        lesson.keyThinkers.forEach { thinker ->
                            Text(
                                text = "${thinker.name} (${thinker.era}): ${thinker.contribution}",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(vertical = 3.dp)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("9. Karşılaştırmalı Açıklamalar")
                        lesson.comparativeExplanations.forEach { cmp ->
                            Spacer(Modifier.height(6.dp))
                            Text(cmp.title, style = MaterialTheme.typography.labelLarge)
                            Spacer(Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(cmp.leftLabel, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                        Text(cmp.leftDetail, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(cmp.rightLabel, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                        Text(cmp.rightDetail, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 11. KAVRAM HARİTASI
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("10. Kavram Haritası")
                        Spacer(Modifier.height(6.dp))
                        ConceptMapDiagram(
                            relations = lesson.conceptMapLinks,
                            accentColor = lesson.discipline.accentColor
                        )
                    }
                }
            }

            // 12. ÖRNEK OLAY SENARYOSU & 13. DÜŞÜNME SORULARI & 14. ÖZET
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("11. Örnek Olay Analizi: ${lesson.caseStudy.title}")
                        Text(lesson.caseStudy.scenario, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(8.dp))
                        lesson.caseStudy.analysisQuestions.forEachIndexed { idx, q ->
                            Text(
                                text = "${idx + 1}. $q",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.55f),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Çıkarım: ${lesson.caseStudy.keyTakeaway}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("12. Eleştirel Düşünme Soruları")
                        lesson.reflectionQuestions.forEach { rq ->
                            Text("? $rq", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 2.dp))
                        }
                        Spacer(Modifier.height(12.dp))
                        LessonSectionTitle("13. Ders Özeti")
                        Text(lesson.summary, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            // 15. AKTİF HATIRLAMA KARTLARI
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("14. Aktif Hatırlama (Active Recall)")
                        Spacer(Modifier.height(6.dp))
                        lesson.activeRecallPrompts.forEachIndexed { index, item ->
                            var revealed by remember(lesson.id, index) { mutableStateOf(false) }
                            Surface(
                                onClick = { revealed = !revealed },
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .testTag("active_recall_card_$index")
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(item.prompt, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                                    Spacer(Modifier.height(4.dp))
                                    if (revealed) {
                                        Text(
                                            text = item.answerGuide,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    } else {
                                        Text(
                                            text = "Cevap rehberini görmek için dokun",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 16. BU DERSTEN NE ÖĞRENDİM? & 17. UYGULAMA GÖREVİ & 18. AKADEMİK KAYNAKÇA
            item {
                Box(modifier = Modifier.widthIn(max = 600.dp)) {
                    AcademicCard {
                        LessonSectionTitle("15. Bu Dersten Ne Öğrendim? (Feynman Özeti)")
                        Spacer(Modifier.height(6.dp))
                        OutlinedTextField(
                            value = learnedText,
                            onValueChange = { learnedText = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("learned_summary_input"),
                            minLines = 3,
                            placeholder = { Text("Öğrendiklerinizi kendi cümlelerinizle yazın...") },
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = { onSaveLearnedSummary(lesson, learnedText) },
                            modifier = Modifier
                                .heightIn(min = 48.dp)
                                .testTag("save_learned_summary_button"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Outlined.Save, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Öğrenme Notumu Kaydet")
                        }

                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(Modifier.height(12.dp))

                        LessonSectionTitle("16. Uygulama Görevi: ${lesson.practiceTask.title}")
                        Text(lesson.practiceTask.instructions, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Çıktı Beklentisi: ${lesson.practiceTask.deliverableHint}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )

                        Spacer(Modifier.height(14.dp))
                        LessonSectionTitle("17. Akademik Kaynakça")
                        lesson.references.forEach { ref ->
                            Surface(
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = ref.type.label,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Text(ref.citation, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                                    Text(ref.note, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                }
            }

            // ALT EYLEM ÇUBUĞU
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { onStartMiniExam(lesson.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 50.dp)
                            .testTag("lesson_to_exam_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Outlined.Quiz, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("18. Mini Değerlendirme Sınavına Geç")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FilledTonalButton(
                            onClick = { onToggleComplete(lesson, !isCompleted) },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("lesson_toggle_complete_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(if (isCompleted) "Tamamlandı ✓" else "Dersi Tamamla")
                        }
                        OutlinedButton(
                            onClick = { onDiscussWithTutor(lesson.discipline, lesson.id) },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("lesson_discuss_tutor_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Outlined.Psychology, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Öğretmenle Tartış")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LessonSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}
