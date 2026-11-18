package com.example.ui.screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import com.example.data.curriculum.CurriculumCatalog
import com.example.firebase.signOutUser
import com.example.ui.DashboardSnapshot
import com.example.ui.components.AcademicCard
import com.example.ui.components.SectionHeader
import com.example.util.StudyNotificationHelper
import kotlin.math.roundToInt

@Composable
fun ProfileScreen(
    snapshot: DashboardSnapshot,
    userEmail: String?,
    onOpenNotes: () -> Unit,
    onSavePlanner: (
        weeklyHours: Long,
        dailyMinutes: Long,
        busyDayMode: Boolean,
        availableDays: List<Long>,
        notifDailyLesson: Boolean,
        notifSpacedRepetition: Boolean,
        notifWeeklyReview: Boolean,
        notifMonthlyExam: Boolean,
        notifGoalCompleted: Boolean
    ) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = remember { CredentialManager.create(context) }

    val profile = snapshot.profile
    var weeklyHours by remember(profile.weeklyTargetHours) { mutableFloatStateOf(profile.weeklyTargetHours.toFloat()) }
    var dailyMinutes by remember(profile.dailyMinutesTarget) { mutableLongStateOf(profile.dailyMinutesTarget) }
    var busyDayMode by remember(profile.busyDayMode) { mutableStateOf(profile.busyDayMode) }
    var availableDays by remember(profile.availableDays) { mutableStateOf(profile.availableDays.toSet()) }

    var notifDaily by remember(profile.notifDailyLesson) { mutableStateOf(profile.notifDailyLesson) }
    var notifSpaced by remember(profile.notifSpacedRepetition) { mutableStateOf(profile.notifSpacedRepetition) }
    var notifWeekly by remember(profile.notifWeeklyReview) { mutableStateOf(profile.notifWeeklyReview) }
    var notifMonthly by remember(profile.notifMonthlyExam) { mutableStateOf(profile.notifMonthlyExam) }
    var notifGoal by remember(profile.notifGoalCompleted) { mutableStateOf(profile.notifGoalCompleted) }

    var hasNotifPerm by remember { mutableStateOf(StudyNotificationHelper.hasNotificationPermission(context)) }
    val notifPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasNotifPerm = granted
    }

    val finishFormatted = remember(snapshot.planSummary.estimatedCompletionEpochDay) {
        CurriculumCatalog.formatShortDate(snapshot.planSummary.estimatedCompletionEpochDay)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // HESAP KARTI
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(profile.displayName, style = MaterialTheme.typography.titleLarge)
                            if (!userEmail.isNullOrBlank()) {
                                Text(
                                    text = userEmail,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Toplam Çalışma: ${profile.totalStudyMinutes} dk • Seri: ${profile.currentStreakDays} gün",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                signOutUser(credentialManager = credentialManager, scope = scope)
                            },
                            modifier = Modifier
                                .heightIn(min = 48.dp)
                                .testTag("sign_out_button")
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Çıkış yap")
                            Spacer(Modifier.width(6.dp))
                            Text("Çıkış")
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = onOpenNotes,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .testTag("profile_open_notes_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Outlined.EditNote, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Kişisel Bilgi Bankam & Notlarım (${snapshot.notes.size})")
                    }
                }
            }
        }

        // ESNEK ÇALIŞMA TAKVİMİ
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Esnek Çalışma Takvimi ve Planlayıcı",
                        subtitle = "Kaçırılan günler ilerlemenizi sıfırlamaz; bitiş tarihi dinamik güncellenir"
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Haftalık Hedef Süre: ${weeklyHours.roundToInt()} saat",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Slider(
                        value = weeklyHours,
                        onValueChange = { weeklyHours = it },
                        valueRange = 2f..25f,
                        steps = 22,
                        modifier = Modifier.testTag("weekly_hours_slider")
                    )

                    Spacer(Modifier.height(8.dp))
                    Text("Günlük Çalışma Süresi Tercihi:", style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(15L, 30L, 45L, 60L, 90L).forEach { mins ->
                            FilterChip(
                                selected = dailyMinutes == mins,
                                onClick = { dailyMinutes = mins },
                                label = { Text("$mins dk") },
                                modifier = Modifier.testTag("daily_target_$mins")
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))
                    Text("Çalışma Günleri (Seçilmeyenler Dinlenme Günüdür):", style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val dayNames = listOf(
                            1L to "Pzt", 2L to "Sal", 3L to "Çar",
                            4L to "Per", 5L to "Cum", 6L to "Cmt", 7L to "Paz"
                        )
                        dayNames.forEach { (dayNum, dayLabel) ->
                            val selected = dayNum in availableDays
                            FilterChip(
                                selected = selected,
                                onClick = {
                                    availableDays = if (selected && availableDays.size > 1) {
                                        availableDays - dayNum
                                    } else {
                                        availableDays + dayNum
                                    }
                                },
                                label = { Text(dayLabel) },
                                modifier = Modifier.testTag("day_toggle_$dayNum")
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Yoğun Gün Modu", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(
                                text = "Günlük planı 15 dakikalık mini derse indirger",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = busyDayMode,
                            onCheckedChange = { busyDayMode = it },
                            modifier = Modifier.testTag("busy_day_switch")
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Mevcut temponuzla tahmini bitiş tarihi: $finishFormatted",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        // BİLDİRİM TERCİHLERİ
        item {
            Box(modifier = Modifier.widthIn(max = 600.dp)) {
                AcademicCard {
                    SectionHeader(
                        title = "Bildirim ve Hatırlatma Ayarları",
                        subtitle = if (hasNotifPerm) "Bildirim izni etkin" else "Bildirim göndermek için izin gerekir"
                    )
                    Spacer(Modifier.height(6.dp))
                    NotificationToggleRow("Günlük ders hatırlatması", notifDaily) { notifDaily = it }
                    NotificationToggleRow("Aralıklı tekrar hatırlatması", notifSpaced) { notifSpaced = it }
                    NotificationToggleRow("Haftalık değerlendirme hatırlatması", notifWeekly) { notifWeekly = it }
                    NotificationToggleRow("Aylık sınav hatırlatması", notifMonthly) { notifMonthly = it }
                    NotificationToggleRow("Tamamlanan hedef bildirimi", notifGoal) { notifGoal = it }

                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (!hasNotifPerm && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                } else {
                                    StudyNotificationHelper.showReminderNotification(
                                        context = context,
                                        notificationId = 1001,
                                        title = "Entelektüel Akademi Hatırlatması",
                                        body = "Bugünkü dersiniz ve bekleyen tekrar kavramlarınız sizi bekliyor."
                                    )
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("test_notification_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Outlined.NotificationsActive, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text(if (hasNotifPerm) "Örnek Bildirim" else "İzin Ver")
                        }

                        Button(
                            onClick = {
                                onSavePlanner(
                                    weeklyHours.roundToInt().toLong(),
                                    dailyMinutes,
                                    busyDayMode,
                                    availableDays.toList().sorted(),
                                    notifDaily,
                                    notifSpaced,
                                    notifWeekly,
                                    notifMonthly,
                                    notifGoal
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .heightIn(min = 48.dp)
                                .testTag("save_planner_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Outlined.Save, contentDescription = null)
                            Spacer(Modifier.width(6.dp))
                            Text("Ayarları Kaydet")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationToggleRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
