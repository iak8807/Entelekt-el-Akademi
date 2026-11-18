package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.model.DisciplineId
import com.example.data.model.TutorMode
import com.example.ui.TutorChatMessage
import com.example.ui.TutorStreamState
import com.example.ui.components.AcademicCard

@Composable
fun TutorScreen(
    selectedMode: TutorMode,
    selectedDiscipline: DisciplineId,
    activeLessonId: String?,
    messages: List<TutorChatMessage>,
    streamState: TutorStreamState,
    onSelectMode: (TutorMode) -> Unit,
    onSelectContext: (DisciplineId, String?) -> Unit,
    onSendMessage: (String) -> Unit,
    onSaveMessageToNotes: (TutorChatMessage) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    var discMenuOpen by remember { mutableStateOf(false) }
    var lessonMenuOpen by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val lessonsForDiscipline = remember(selectedDiscipline) {
        CurriculumCatalog.lessonsForDiscipline(selectedDiscipline)
    }
    val activeLesson = remember(activeLessonId) {
        activeLessonId?.let { CurriculumCatalog.lessonById(it) }
    }

    LaunchedEffect(messages.size, streamState) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Üst Bağlam ve Mod Başlığı
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_socratic_tutor),
                    contentDescription = "Yapay Zekâ Öğretmeni",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Yapay Zekâ Öğretmeni",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${selectedMode.title} • ${selectedMode.subtitle}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Disiplin & Ders Seçicileri
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { discMenuOpen = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("tutor_discipline_picker"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            selectedDiscipline.shortTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(Icons.Outlined.ExpandMore, contentDescription = "Disiplin seç")
                    }
                    DropdownMenu(
                        expanded = discMenuOpen,
                        onDismissRequest = { discMenuOpen = false }
                    ) {
                        DisciplineId.entries.forEach { d ->
                            DropdownMenuItem(
                                text = { Text(d.title) },
                                onClick = {
                                    onSelectContext(d, null)
                                    discMenuOpen = false
                                }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { lessonMenuOpen = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("tutor_lesson_picker"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            activeLesson?.title ?: "Ders (İsteğe Bağlı)",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(Icons.Outlined.ExpandMore, contentDescription = "Ders seç")
                    }
                    DropdownMenu(
                        expanded = lessonMenuOpen,
                        onDismissRequest = { lessonMenuOpen = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Genel Disiplin Sohbeti") },
                            onClick = {
                                onSelectContext(selectedDiscipline, null)
                                lessonMenuOpen = false
                            }
                        )
                        lessonsForDiscipline.forEach { l ->
                            DropdownMenuItem(
                                text = { Text("Ay ${l.monthNumber}: ${l.title}") },
                                onClick = {
                                    onSelectContext(selectedDiscipline, l.id)
                                    lessonMenuOpen = false
                                }
                            )
                        }
                    }
                }
            }

            // 8 Pedagojik Mod Çubuğu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TutorMode.entries.forEach { mode ->
                    FilterChip(
                        selected = selectedMode == mode,
                        onClick = { onSelectMode(mode) },
                        label = { Text(mode.title) },
                        modifier = Modifier.testTag("tutor_mode_${mode.name}")
                    )
                }
            }
        }

        // Sohbet Akışı
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .widthIn(max = 600.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages, key = { it.id }) { msg ->
                TutorBubble(
                    message = msg,
                    onSaveToNotes = if (!msg.isUser) {
                        { onSaveMessageToNotes(msg) }
                    } else null
                )
            }

            when (streamState) {
                is TutorStreamState.Loading -> {
                    item {
                        Surface(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                                Text("Öğretmen yanıt hazırlıyor...", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
                is TutorStreamState.Streaming -> {
                    item {
                        TutorBubble(
                            message = TutorChatMessage(
                                id = "streaming",
                                isUser = false,
                                text = streamState.partialText,
                                modeLabel = selectedMode.title
                            ),
                            onSaveToNotes = null
                        )
                    }
                }
                is TutorStreamState.Error -> {
                    item {
                        AcademicCard(containerColor = MaterialTheme.colorScheme.errorContainer) {
                            Text(
                                text = streamState.message,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                            val lastUser = messages.lastOrNull { it.isUser }?.text
                            if (!lastUser.isNullOrBlank()) {
                                Spacer(Modifier.height(6.dp))
                                TextButton(
                                    onClick = { onSendMessage(lastUser) },
                                    modifier = Modifier.testTag("tutor_retry_button")
                                ) {
                                    Icon(Icons.Outlined.Refresh, contentDescription = null)
                                    Spacer(Modifier.width(4.dp))
                                    Text("Tekrar Dene")
                                }
                            }
                        }
                    }
                }
                is TutorStreamState.Idle -> Unit
            }
        }

        // Hızlı Başlatıcılar & İleti Kutusu
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val quickPrompts = listOf(
                "Bu konuyu Feynman tekniğiyle özetle",
                "Bana Sokratik bir soru sor",
                "Diğer disiplinlerle ilişkisini kur",
                "5 soruluk kısa yoklama yap"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                quickPrompts.forEachIndexed { idx, prompt ->
                    AssistChip(
                        onClick = { onSendMessage(prompt) },
                        label = { Text(prompt, style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.testTag("tutor_quick_prompt_$idx")
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tutor_message_input"),
                    placeholder = { Text("Sorunuzu veya düşüncenizi yazın...") },
                    maxLines = 4,
                    shape = RoundedCornerShape(14.dp)
                )
                FilledIconButton(
                    onClick = {
                        val toSend = inputText.trim()
                        if (toSend.isNotEmpty()) {
                            inputText = ""
                            onSendMessage(toSend)
                        }
                    },
                    enabled = inputText.isNotBlank() &&
                        streamState !is TutorStreamState.Loading &&
                        streamState !is TutorStreamState.Streaming,
                    modifier = Modifier
                        .size(52.dp)
                        .testTag("tutor_send_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Gönder")
                }
            }
        }
    }
}

@Composable
private fun TutorBubble(
    message: TutorChatMessage,
    onSaveToNotes: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (message.isUser) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
            contentColor = if (message.isUser) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = if (message.isUser) 0.dp else 2.dp,
            modifier = Modifier.widthIn(max = 520.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                if (!message.isUser && message.modeLabel.isNotBlank()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message.modeLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (onSaveToNotes != null) {
                            IconButton(
                                onClick = onSaveToNotes,
                                modifier = Modifier
                                    .size(36.dp)
                                    .testTag("save_tutor_note_${message.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.BookmarkAdd,
                                    contentDescription = "Notlara kaydet",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                }
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
