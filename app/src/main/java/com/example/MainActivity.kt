package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.repository.AcademyRepository
import com.example.data.repository.AiTutorRepository
import com.example.firebase.authStateFlow
import com.example.firebase.configureAppCheck
import com.example.ui.AcademyViewModel
import com.example.ui.DataLoadState
import com.example.ui.screens.*
import com.example.ui.theme.AkademiTheme
import com.example.util.StudyNotificationHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

private data class TopNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val topNavItems = listOf(
    TopNavItem("home", "Ana Sayfa", Icons.Filled.Home, Icons.Outlined.Home),
    TopNavItem("curriculum", "Müfredat", Icons.Filled.AutoStories, Icons.Outlined.AutoStories),
    TopNavItem("tutor", "Öğretmen", Icons.Filled.Psychology, Icons.Outlined.Psychology),
    TopNavItem("progress", "İlerlemem", Icons.Filled.Analytics, Icons.Outlined.Analytics),
    TopNavItem("profile", "Profil", Icons.Filled.Person, Icons.Outlined.Person)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // App Check MUST be configured before any Firebase service calls or Compose UI instantiation.
        configureAppCheck(this, intent)
        StudyNotificationHelper.ensureChannel(this)
        enableEdgeToEdge()

        setContent {
            AkademiTheme {
                val currentUser by remember { Firebase.auth.authStateFlow() }
                    .collectAsStateWithLifecycle(initialValue = Firebase.auth.currentUser)

                if (currentUser == null) {
                    AuthScreen()
                } else {
                    val user = currentUser!!
                    key(user.uid) {
                        AuthenticatedAcademyRoot(user = user)
                    }
                }
            }
        }
    }
}

@Composable
private fun AuthenticatedAcademyRoot(user: FirebaseUser) {
    val context = LocalContext.current
    val repository = remember(context.applicationContext) { AcademyRepository(context.applicationContext) }
    val aiRepository = remember { AiTutorRepository() }

    val factory = remember(user.uid, repository, aiRepository) {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AcademyViewModel(
                    repository = repository,
                    aiRepository = aiRepository,
                    currentUserId = user.uid,
                    initialDisplayName = user.displayName ?: user.email?.substringBefore("@") ?: "Öğrenci"
                ) as T
            }
        }
    }
    val vm: AcademyViewModel = viewModel(key = user.uid, factory = factory)

    val dashboardState by vm.dashboardState.collectAsStateWithLifecycle()
    val statusBanner by vm.statusBanner.collectAsStateWithLifecycle()
    val tutorMode by vm.selectedTutorMode.collectAsStateWithLifecycle()
    val tutorDiscipline by vm.selectedTutorDiscipline.collectAsStateWithLifecycle()
    val tutorLessonId by vm.activeTutorLessonId.collectAsStateWithLifecycle()
    val chatMessages by vm.chatMessages.collectAsStateWithLifecycle()
    val tutorStreamState by vm.tutorStreamState.collectAsStateWithLifecycle()
    val examGradingState by vm.examGradingState.collectAsStateWithLifecycle()
    val monthlyReportState by vm.monthlyReportState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(statusBanner) {
        val msg = statusBanner
        if (!msg.isNullOrBlank()) {
            snackbarHostState.showSnackbar(msg)
            vm.clearBanner()
        }
    }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showTopLevelNav = currentRoute in topNavItems.map { it.route }

    fun navigateTopTab(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val useRail = maxWidth >= 600.dp

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                if (showTopLevelNav && !useRail) {
                    NavigationBar {
                        topNavItems.forEach { item ->
                            val selected = currentRoute == item.route
                            NavigationBarItem(
                                selected = selected,
                                onClick = { navigateTopTab(item.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(item.label, maxLines = 1) },
                                modifier = Modifier.testTag("nav_${item.route}")
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (showTopLevelNav && useRail) {
                    NavigationRail {
                        Spacer(Modifier.height(12.dp))
                        topNavItems.forEach { item ->
                            val selected = currentRoute == item.route
                            NavigationRailItem(
                                selected = selected,
                                onClick = { navigateTopTab(item.route) },
                                icon = {
                                    Icon(
                                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                },
                                label = { Text(item.label) },
                                modifier = Modifier.testTag("rail_${item.route}")
                            )
                        }
                    }
                }

                when (val state = dashboardState) {
                    is DataLoadState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator()
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "Öğrenme verileriniz yükleniyor...",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    is DataLoadState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = "Veri bağlantısı kurulamadı",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = state.message,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    is DataLoadState.Success -> {
                        val snapshot = state.data
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("home") {
                                HomeScreen(
                                    snapshot = snapshot,
                                    onOpenLesson = { lessonId -> navController.navigate("lesson/$lessonId") },
                                    onToggleTask = { lesson, taskKey -> vm.toggleLessonTask(lesson, taskKey) },
                                    onReviewCard = { card, recall -> vm.submitSpacedReview(card, recall) },
                                    onNavigateTab = { route -> navigateTopTab(route) },
                                    onOpenNotes = { navController.navigate("notes") },
                                    onOpenExam = { type, id -> navController.navigate("exam/$type/$id") }
                                )
                            }

                            composable("curriculum") {
                                CurriculumScreen(
                                    snapshot = snapshot,
                                    onOpenLesson = { lessonId -> navController.navigate("lesson/$lessonId") },
                                    onOpenMonthlyExam = { month -> navController.navigate("exam/month/$month") },
                                    onToggleFavorite = { lesson, fav -> vm.toggleFavorite(lesson, fav) },
                                    onToggleReadLater = { lesson, rl -> vm.toggleReadLater(lesson, rl) }
                                )
                            }

                            composable("tutor") {
                                TutorScreen(
                                    selectedMode = tutorMode,
                                    selectedDiscipline = tutorDiscipline,
                                    activeLessonId = tutorLessonId,
                                    messages = chatMessages,
                                    streamState = tutorStreamState,
                                    onSelectMode = { mode -> vm.setTutorMode(mode) },
                                    onSelectContext = { disc, lessonId -> vm.setTutorContext(disc, lessonId) },
                                    onSendMessage = { text -> vm.sendTutorMessage(text) },
                                    onSaveMessageToNotes = { msg ->
                                        vm.saveStudyNote(
                                            title = "Öğretmen Notu (${msg.modeLabel})",
                                            content = msg.text,
                                            quoteText = "",
                                            personalReflection = "",
                                            category = "Ders Notu",
                                            disciplineId = tutorDiscipline.name,
                                            lessonId = tutorLessonId.orEmpty(),
                                            tags = listOf("YZ-Öğretmen", tutorDiscipline.shortTitle),
                                            favorite = false
                                        )
                                    }
                                )
                            }

                            composable("progress") {
                                ProgressScreen(
                                    snapshot = snapshot,
                                    monthlyReportState = monthlyReportState,
                                    onReviewCard = { card, recall -> vm.submitSpacedReview(card, recall) },
                                    onGenerateMonthlyReport = { month -> vm.generateMonthlyAiReport(month) }
                                )
                            }

                            composable("profile") {
                                ProfileScreen(
                                    snapshot = snapshot,
                                    userEmail = user.email,
                                    onOpenNotes = { navController.navigate("notes") },
                                    onSavePlanner = { wk, dm, busy, days, n1, n2, n3, n4, n5 ->
                                        vm.updatePlannerSettings(wk, dm, busy, days, n1, n2, n3, n4, n5)
                                    }
                                )
                            }

                            composable(
                                route = "lesson/{lessonId}",
                                arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
                            ) { backStack ->
                                val lessonId = backStack.arguments?.getString("lessonId").orEmpty()
                                LessonDetailScreen(
                                    lessonId = lessonId,
                                    progress = snapshot.progressMap[lessonId],
                                    onBack = { navController.popBackStack() },
                                    onToggleComplete = { lesson, done -> vm.toggleLessonComplete(lesson, done) },
                                    onToggleFavorite = { lesson, fav -> vm.toggleFavorite(lesson, fav) },
                                    onToggleReadLater = { lesson, rl -> vm.toggleReadLater(lesson, rl) },
                                    onSaveLearnedSummary = { lesson, txt -> vm.saveLearnedSummary(lesson, txt) },
                                    onEnrollSpacedRepetition = { lesson -> vm.enrollLessonConcepts(lesson) },
                                    onStartMiniExam = { id -> navController.navigate("exam/lesson/$id") },
                                    onDiscussWithTutor = { disc, id ->
                                        vm.setTutorContext(disc, id)
                                        navigateTopTab("tutor")
                                    }
                                )
                            }

                            composable(
                                route = "exam/{examType}/{targetId}",
                                arguments = listOf(
                                    navArgument("examType") { type = NavType.StringType },
                                    navArgument("targetId") { type = NavType.StringType }
                                )
                            ) { backStack ->
                                val examType = backStack.arguments?.getString("examType") ?: "lesson"
                                val targetId = backStack.arguments?.getString("targetId").orEmpty()
                                ExamScreen(
                                    examType = examType,
                                    targetId = targetId,
                                    gradingState = examGradingState,
                                    onResetExam = { vm.resetExamState() },
                                    onSubmitExam = { eId, title, disc, month, qs, mcq, tf, txt, match, single ->
                                        vm.evaluateAndSaveExam(eId, title, disc, month, qs, mcq, tf, txt, match, single)
                                    },
                                    onBack = { navController.popBackStack() },
                                    onOpenLesson = { id -> navController.navigate("lesson/$id") }
                                )
                            }

                            composable("notes") {
                                NotesScreen(
                                    notes = snapshot.notes,
                                    onBack = { navController.popBackStack() },
                                    onSaveNote = { existingId, title, content, quote, refl, cat, discId, lId, tags, fav ->
                                        vm.saveStudyNote(
                                            existingNoteId = existingId,
                                            title = title,
                                            content = content,
                                            quoteText = quote,
                                            personalReflection = refl,
                                            category = cat,
                                            disciplineId = discId,
                                            lessonId = lId,
                                            tags = tags,
                                            favorite = fav
                                        )
                                    },
                                    onDeleteNote = { noteId -> vm.deleteStudyNote(noteId) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
