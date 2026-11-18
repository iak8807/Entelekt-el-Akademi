package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.data.model.StudyNoteDoc
import com.example.ui.components.AcademicCard

private enum class NoteFilterTab(val label: String) {
    TUMU("Tüm Notlar"),
    ALINTILAR("Alıntılar"),
    KENDI_DUSUNCELERIM("Kendi Düşüncelerim"),
    FAVORILER("Favoriler")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    notes: List<StudyNoteDoc>,
    onBack: () -> Unit,
    onSaveNote: (
        existingNoteId: String?,
        title: String,
        content: String,
        quoteText: String,
        personalReflection: String,
        category: String,
        disciplineId: String,
        lessonId: String,
        tags: List<String>,
        favorite: Boolean
    ) -> Unit,
    onDeleteNote: (String) -> Unit
) {
    BackHandler { onBack() }

    var selectedTab by remember { mutableStateOf(NoteFilterTab.TUMU) }
    var searchQuery by remember { mutableStateOf("") }
    var editingNote by remember { mutableStateOf<StudyNoteDoc?>(null) }
    var showEditorDialog by remember { mutableStateOf(false) }

    val qLower = searchQuery.trim().lowercase(CurriculumCatalog.turkishLocale)
    val filteredNotes = remember(notes, selectedTab, qLower) {
        notes.filter { note ->
            val tabOk = when (selectedTab) {
                NoteFilterTab.TUMU -> true
                NoteFilterTab.ALINTILAR -> note.quoteText.isNotBlank() || note.category == "Alıntı"
                NoteFilterTab.KENDI_DUSUNCELERIM -> note.personalReflection.isNotBlank() || note.category == "Kendi Düşüncem"
                NoteFilterTab.FAVORILER -> note.favorite
            }
            val textOk = if (qLower.isBlank()) true else {
                note.title.lowercase(CurriculumCatalog.turkishLocale).contains(qLower) ||
                    note.content.lowercase(CurriculumCatalog.turkishLocale).contains(qLower) ||
                    note.quoteText.lowercase(CurriculumCatalog.turkishLocale).contains(qLower) ||
                    note.personalReflection.lowercase(CurriculumCatalog.turkishLocale).contains(qLower) ||
                    note.tags.any { it.lowercase(CurriculumCatalog.turkishLocale).contains(qLower) }
            }
            tabOk && textOk
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Kişisel Bilgi Bankası ve Notlarım") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("notes_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri dön")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingNote = null
                    showEditorDialog = true
                },
                modifier = Modifier.testTag("add_note_fab")
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "Yeni not ekle")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .widthIn(max = 600.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("notes_search_input"),
                        placeholder = { Text("Not, alıntı, etiket veya düşüncelerimde ara...") },
                        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        NoteFilterTab.entries.forEach { tab ->
                            FilterChip(
                                selected = selectedTab == tab,
                                onClick = { selectedTab = tab },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag("notes_tab_${tab.name}")
                            )
                        }
                    }
                }
            }

            if (filteredNotes.isEmpty()) {
                item {
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard {
                            Text(
                                text = "Henüz eşleşen bir not bulunmuyor.",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Sağ alttaki + düğmesinden ders notu, kitap alıntısı veya 'Kendi Düşüncelerim' kaydı oluşturabilirsiniz.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(filteredNotes, key = { it.id }) { note ->
                    Box(modifier = Modifier.widthIn(max = 600.dp)) {
                        AcademicCard(
                            onClick = {
                                editingNote = note
                                showEditorDialog = true
                            },
                            modifier = Modifier.testTag("note_card_${note.id}")
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${note.category} • ${note.disciplineId}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Row {
                                    if (note.favorite) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = "Favori",
                                            tint = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = { onDeleteNote(note.id) },
                                        modifier = Modifier
                                            .size(36.dp)
                                            .testTag("delete_note_${note.id}")
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = "Notu sil",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            if (note.quoteText.isNotBlank()) {
                                Spacer(Modifier.height(6.dp))
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "“${note.quoteText}”",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontStyle = FontStyle.Italic,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(note.content, style = MaterialTheme.typography.bodySmall)
                            if (note.personalReflection.isNotBlank()) {
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = "Kendi Düşüncem: ${note.personalReflection}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            if (note.tags.isNotEmpty()) {
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = note.tags.joinToString("  ") { "#$it" },
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showEditorDialog) {
        NoteEditorDialog(
            initialNote = editingNote,
            onDismiss = { showEditorDialog = false },
            onSave = { title, content, quote, reflection, category, disciplineId, tags, fav ->
                onSaveNote(
                    editingNote?.id,
                    title,
                    content,
                    quote,
                    reflection,
                    category,
                    disciplineId,
                    editingNote?.lessonId.orEmpty(),
                    tags,
                    fav
                )
                showEditorDialog = false
            }
        )
    }
}

@Composable
private fun NoteEditorDialog(
    initialNote: StudyNoteDoc?,
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        content: String,
        quote: String,
        reflection: String,
        category: String,
        disciplineId: String,
        tags: List<String>,
        favorite: Boolean
    ) -> Unit
) {
    var title by remember { mutableStateOf(initialNote?.title.orEmpty()) }
    var content by remember { mutableStateOf(initialNote?.content.orEmpty()) }
    var quoteText by remember { mutableStateOf(initialNote?.quoteText.orEmpty()) }
    var reflection by remember { mutableStateOf(initialNote?.personalReflection.orEmpty()) }
    var category by remember { mutableStateOf(initialNote?.category ?: "Ders Notu") }
    var disciplineId by remember { mutableStateOf(initialNote?.disciplineId ?: "GENEL") }
    var tagsInput by remember { mutableStateOf(initialNote?.tags?.joinToString(", ").orEmpty()) }
    var favorite by remember { mutableStateOf(initialNote?.favorite == true) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initialNote == null) "Yeni Bilgi Kartı / Not" else "Notu Düzenle") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("Ders Notu", "Alıntı", "Kendi Düşüncem", "Araştırma").forEach { cat ->
                        FilterChip(
                            selected = category == cat,
                            onClick = { category = cat },
                            label = { Text(cat) }
                        )
                    }
                }
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = disciplineId == "GENEL",
                        onClick = { disciplineId = "GENEL" },
                        label = { Text("GENEL") }
                    )
                    DisciplineId.entries.forEach { d ->
                        FilterChip(
                            selected = disciplineId == d.name,
                            onClick = { disciplineId = d.name },
                            label = { Text(d.shortTitle) }
                        )
                    }
                }
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Başlık") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("note_dialog_title")
                )
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Not İçeriği") },
                    minLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("note_dialog_content")
                )
                OutlinedTextField(
                    value = quoteText,
                    onValueChange = { quoteText = it },
                    label = { Text("Alıntı (İsteğe bağlı)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = reflection,
                    onValueChange = { reflection = it },
                    label = { Text("Kendi Düşüncem (Yorumum)") },
                    minLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("note_dialog_reflection")
                )
                OutlinedTextField(
                    value = tagsInput,
                    onValueChange = { tagsInput = it },
                    label = { Text("Etiketler (virgülle ayırın)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = favorite, onCheckedChange = { favorite = it })
                    Text("Favorilere ekle")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val tags = tagsInput.split(",").map { it.trim() }.filter { it.isNotBlank() }
                    onSave(title, content, quoteText, reflection, category, disciplineId, tags, favorite)
                },
                modifier = Modifier.testTag("note_dialog_save_button")
            ) {
                Text("Kaydet")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}
