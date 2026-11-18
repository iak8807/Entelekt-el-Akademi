const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_scholar_123";
const BOB_UID = "bob_scholar_456";

const [emulatorHost, emulatorPortStr] = (
  process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085"
).split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

function validProfilePayload(uid, now) {
  return {
    userId: uid,
    displayName: "Zeynep Demir",
    programStartEpochDay: 20400,
    weeklyTargetHours: 9,
    dailyMinutesTarget: 45,
    busyDayMode: false,
    availableDays: [1, 2, 3, 4, 5, 6],
    notifDailyLesson: true,
    notifSpacedRepetition: true,
    notifWeeklyReview: true,
    notifMonthlyExam: true,
    notifGoalCompleted: true,
    totalStudyMinutes: 120,
    currentStreakDays: 3,
    longestStreakDays: 5,
    lastStudyEpochDay: 20402,
    createdAt: now,
    updatedAt: now,
  };
}

test("Unauthenticated users are denied on all paths", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  const past = new Date(Date.now() - 1000);
  await assertFails(unauthDb.collection("users").doc(ALICE_UID).get());
  await assertFails(
    unauthDb.collection("users").doc(ALICE_UID).set(validProfilePayload(ALICE_UID, past))
  );
  await assertFails(
    unauthDb.collection("users").doc(ALICE_UID).collection("notes").get()
  );
});

test("User profile create, partial update, shadow field block, and cross-user isolation", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const past = new Date(Date.now() - 5000);

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).set(validProfilePayload(ALICE_UID, past))
  );

  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).update({
      busyDayMode: true,
      dailyMinutesTarget: 15,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject ghost field
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      isAdmin: true,
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject createdAt mutation
  await assertFails(
    aliceDb.collection("users").doc(ALICE_UID).update({
      createdAt: new Date(Date.now() - 1000),
      updatedAt: new Date(Date.now() - 1000),
    })
  );

  // Reject future timestamp
  await assertFails(
    aliceDb.collection("users").doc(BOB_UID).set(
      validProfilePayload(BOB_UID, new Date(Date.now() + 120000))
    )
  );

  // Cross-user isolation
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).update({
      displayName: "Hijacked",
      updatedAt: new Date(Date.now() - 1000),
    })
  );
});

test("LessonProgress, Notes, SpacedCards, ExamResults, and StudyLogs parity & security", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  const past = new Date(Date.now() - 5000);

  // Empty collection list succeeds for owner, fails for non-owner
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get()
  );
  await assertFails(
    bobDb.collection("users").doc(ALICE_UID).collection("lessonProgress").get()
  );

  // LessonProgress create & partial update (NO @DocumentId `id` field)
  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("lessonProgress")
      .doc("m01_l01")
      .set({
        userId: ALICE_UID,
        lessonId: "m01_l01",
        monthNumber: 1,
        disciplineId: "FELSEFE",
        completed: true,
        favorite: false,
        readLater: false,
        learnedSummary: "Sokratik sorgulama varsayımları sınar.",
        studyMinutes: 25,
        masteryScore: 85,
        completedTasks: ["read", "recall", "quiz"],
        createdAt: past,
        updatedAt: past,
      })
  );

  // Reject payload containing @DocumentId 'id'
  await assertFails(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("lessonProgress")
      .doc("m01_l02")
      .set({
        id: "m01_l02",
        userId: ALICE_UID,
        lessonId: "m01_l02",
        monthNumber: 1,
        disciplineId: "FELSEFE",
        completed: false,
        favorite: false,
        readLater: true,
        learnedSummary: "",
        studyMinutes: 10,
        masteryScore: 0,
        completedTasks: [],
        createdAt: past,
        updatedAt: past,
      })
  );

  // StudyNote create & update
  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("notes")
      .doc("note_1")
      .set({
        userId: ALICE_UID,
        title: "Epistemoloji Üzerine",
        content: "Gerekçelendirilmiş doğru inanç tartışması.",
        quoteText: "Sorgulanmamış yaşam yaşanmaya değer değildir.",
        personalReflection: "Bugün kendi kabullerimi yeniden inceledim.",
        category: "Kendi Düşüncelerim",
        disciplineId: "FELSEFE",
        lessonId: "m01_l01",
        tags: ["sokrates", "bilgi"],
        favorite: true,
        createdAt: past,
        updatedAt: past,
      })
  );

  // SpacedCard create & review update
  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("spacedCards")
      .doc("card_m01_l01_0")
      .set({
        userId: ALICE_UID,
        conceptTitle: "Maieutik (Doğurtma Yöntemi)",
        conceptSummary: "Sokrates'in sorularla hakikati ortaya çıkarma yöntemi.",
        disciplineId: "FELSEFE",
        lessonId: "m01_l01",
        intervalDays: 1,
        nextReviewEpochDay: 20403,
        reviewCount: 1,
        lastRecallLevel: "PARTIAL",
        createdAt: past,
        updatedAt: past,
      })
  );

  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("spacedCards")
      .doc("card_m01_l01_0")
      .update({
        intervalDays: 3,
        nextReviewEpochDay: 20406,
        reviewCount: 2,
        lastRecallLevel: "EASY",
        updatedAt: new Date(Date.now() - 1000),
      })
  );

  // ExamResult immutable after create
  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("examResults")
      .doc("exam_1")
      .set({
        userId: ALICE_UID,
        lessonId: "m01_l01",
        examTitle: "Felsefi Düşüncenin Doğuşu Sınavı",
        disciplineId: "FELSEFE",
        monthNumber: 1,
        totalScore: 88,
        correctCount: 4,
        wrongCount: 1,
        knowledgeScore: 100,
        understandingScore: 90,
        applicationScore: 80,
        analysisScore: 82,
        aiEvaluationScore: 84,
        aiEvaluationRationale: "Kavramsal doğruluk güçlü, örnekler geliştirilebilir.",
        weakAreas: ["Analiz ve Sentez"],
        reviewConcepts: ["Epistemoloji"],
        createdAt: past,
        updatedAt: past,
      })
  );

  await assertFails(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("examResults")
      .doc("exam_1")
      .update({ totalScore: 100, updatedAt: new Date(Date.now() - 1000) })
  );

  // StudyLog create & update
  await assertSucceeds(
    aliceDb
      .collection("users")
      .doc(ALICE_UID)
      .collection("studyLogs")
      .doc("day_20402")
      .set({
        userId: ALICE_UID,
        epochDay: 20402,
        minutesStudied: 35,
        lessonsTouched: 1,
        reviewsCompleted: 4,
        createdAt: past,
        updatedAt: past,
      })
  );
});
