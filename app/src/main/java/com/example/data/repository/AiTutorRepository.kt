package com.example.data.repository

import android.util.Log
import com.example.data.model.DisciplineId
import com.example.data.model.Lesson
import com.example.data.model.TutorMode
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

private const val TAG = "AiTutorRepo"

data class AiOpenEndedEvaluation(
    val totalAiScore: Int,
    val conceptualAccuracy: Int,
    val logicalConsistency: Int,
    val justification: Int,
    val exampleUsage: Int,
    val criticalDepth: Int,
    val rationale: String,
    val missingAreas: List<String>,
    val recommendedConcepts: List<String>
)

data class AiMonthlyReport(
    val learnedThisMonth: String,
    val successfulTopics: String,
    val missingAreas: String,
    val conceptsToReview: String,
    val nextMonthFocus: String,
    val interdisciplinaryLinks: String
)

class AiTutorRepository {

    private val openEndedSchema = Schema.obj(
        properties = mapOf(
            "totalAiScore" to Schema.integer("Toplam 0-100 puan"),
            "conceptualAccuracy" to Schema.integer("Kavramsal doğruluk 0-20"),
            "logicalConsistency" to Schema.integer("Mantıksal tutarlılık 0-20"),
            "justification" to Schema.integer("Gerekçelendirme 0-20"),
            "exampleUsage" to Schema.integer("Örnek kullanımı 0-20"),
            "criticalDepth" to Schema.integer("Eleştirel derinlik 0-20"),
            "rationale" to Schema.string("Türkçe ayrıntılı değerlendirme gerekçesi"),
            "missingAreas" to Schema.array(Schema.string(), "Eksik öğrenme alanları"),
            "recommendedConcepts" to Schema.array(Schema.string(), "Tekrar edilmesi önerilen kavramlar")
        )
    )

    private val monthlyReportSchema = Schema.obj(
        properties = mapOf(
            "learnedThisMonth" to Schema.string("1. Bu ay neler öğrendim?"),
            "successfulTopics" to Schema.string("2. Hangi konularda başarılı oldum?"),
            "missingAreas" to Schema.string("3. Hangi konularda eksiklerim var?"),
            "conceptsToReview" to Schema.string("4. Hangi kavramları tekrar etmeliyim?"),
            "nextMonthFocus" to Schema.string("5. Bir sonraki ay hangi konulara ağırlık vermeliyim?"),
            "interdisciplinaryLinks" to Schema.string("6. Farklı disiplinler arasında hangi bağlantıları kurabildim?")
        )
    )

    private val tutorModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            systemInstruction = content {
                text(
                    """
                    Sen 'Entelektüel Gelişim Akademisi'nin kişisel yapay zekâ öğretmenisin.
                    Bütün yanıtların duru, akademik, anlaşılır ve saygılı bir Türkçe ile olmalıdır.
                    Davranış Kuralların (KESİN):
                    1. Bilimsel konularda yalnızca kanıta ve deneysel yönteme dayalı bilgi ver.
                    2. Tarihsel konularda farklı görüşleri ve birincil/ikincil kaynakların niteliğini belirt.
                    3. Felsefede düşünürlerin görüşlerini birbirine asla karıştırma.
                    4. Ekonomide güncel veriler ile teorik iktisadi modelleri net biçimde ayır.
                    5. Dinî konularda Kur'an ayeti, sahih hadis, Hanefi/diğer mezhep fıkıh hükmü ve âlim yorumu ayrımını titizlikle koru; mezhep ve yorum farklılıklarını belirt; kaynak göstermeden hüküm üretme.
                    6. Kaynağı doğrulanamayan bilgiyi kesin gerçek gibi sunma; bilmediğin veya doğrulanamayan noktada bunu açıkça belirt.
                    7. Gerektiğinde doğrudan hazır cevap vermek yerine Sokratik sorular sorarak öğrencinin düşünmesini sağla.
                    8. Açıklamalarının derinliğini kullanıcının mevcut dersine ve performans geçmişine göre ayarla.
                    """.trimIndent()
                )
            }
        )
    }

    private val gradingModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = openEndedSchema
            }
        )
    }

    private val reportModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = monthlyReportSchema
            }
        )
    }

    fun streamTutorResponse(
        userMessage: String,
        mode: TutorMode,
        activeLesson: Lesson?,
        selectedDiscipline: DisciplineId,
        completedLessonCount: Int,
        weakAreasSummary: String,
        chatHistory: List<Pair<Boolean, String>>
    ): Flow<String> = flow {
        val contextHeader = buildString {
            appendLine("[ÖĞRENME BAĞLAMI]")
            appendLine("Seçili Öğretmen Modu: ${mode.title} — ${mode.systemDirective}")
            appendLine("Aktif Disiplin: ${selectedDiscipline.title}")
            if (activeLesson != null) {
                appendLine("Aktif Ders: ${activeLesson.title} (Ay ${activeLesson.monthNumber}, ${activeLesson.durationMinutes} dk)")
                appendLine("Dersin Temel Kavramları: ${activeLesson.keyConcepts.joinToString { it.title }}")
            }
            appendLine("Tamamlanan Ders Sayısı: $completedLessonCount / 36")
            if (weakAreasSummary.isNotBlank()) {
                appendLine("Önceki Sınavlardan Eksik Alanlar: $weakAreasSummary")
            }
            appendLine("Kullanıcının İletisi:")
            append(userMessage)
        }

        val historyContents: List<Content> = chatHistory.takeLast(8).map { (isUser, msg) ->
            content(if (isUser) "user" else "model") { text(msg) }
        }

        withTimeout(60_000L) {
            val chat = tutorModel.startChat(history = historyContents)
            chat.sendMessageStream(contextHeader).collect { chunk ->
                val textChunk = chunk.text.orEmpty()
                if (textChunk.isNotEmpty()) {
                    emit(textChunk)
                }
            }
        }
    }

    suspend fun evaluateOpenEndedAnswer(
        questionPrompt: String,
        rubricHint: String,
        referenceExplanation: String,
        studentAnswer: String
    ): Result<AiOpenEndedEvaluation> {
        return try {
            val prompt = """
                Aşağıdaki açık uçlu akademik sınav sorusuna verilen öğrenci yanıtını Türkçe olarak değerlendir.
                5 ölçütün her birini 0–20 arasında puanla ve toplam puanı (0–100) hesapla:
                1. Kavramsal doğruluk (0-20)
                2. Mantıksal tutarlılık (0-20)
                3. Gerekçelendirme (0-20)
                4. Örnek kullanımı (0-20)
                5. Eleştirel derinlik (0-20)

                Soru: $questionPrompt
                Değerlendirme İpucu: $rubricHint
                Referans Çerçeve: $referenceExplanation
                Öğrenci Yanıtı: $studentAnswer
            """.trimIndent()

            val response = withTimeout(60_000L) {
                gradingModel.generateContent(prompt)
            }
            val jsonStr = response.text ?: throw IllegalStateException("Yapay zekâ boş değerlendirme döndürdü.")
            val json = JSONObject(jsonStr)
            val missingArr = json.optJSONArray("missingAreas")
            val missingList = if (missingArr != null) {
                (0 until missingArr.length()).map { missingArr.getString(it) }
            } else emptyList()
            val recArr = json.optJSONArray("recommendedConcepts")
            val recList = if (recArr != null) {
                (0 until recArr.length()).map { recArr.getString(it) }
            } else emptyList()

            val c1 = json.optInt("conceptualAccuracy", 12).coerceIn(0, 20)
            val c2 = json.optInt("logicalConsistency", 12).coerceIn(0, 20)
            val c3 = json.optInt("justification", 12).coerceIn(0, 20)
            val c4 = json.optInt("exampleUsage", 12).coerceIn(0, 20)
            val c5 = json.optInt("criticalDepth", 12).coerceIn(0, 20)
            val sum = (c1 + c2 + c3 + c4 + c5).coerceIn(0, 100)

            Result.success(
                AiOpenEndedEvaluation(
                    totalAiScore = json.optInt("totalAiScore", sum).coerceIn(0, 100),
                    conceptualAccuracy = c1,
                    logicalConsistency = c2,
                    justification = c3,
                    exampleUsage = c4,
                    criticalDepth = c5,
                    rationale = json.optString("rationale", "Yanıt incelendi."),
                    missingAreas = missingList,
                    recommendedConcepts = recList
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Open-ended evaluation timed out", e)
            Result.failure(Exception("Değerlendirme 60 saniyede tamamlanamadı. Lütfen tekrar deneyin."))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Open-ended evaluation failed", e)
            Result.failure(e)
        }
    }

    suspend fun generateMonthlyReport(
        monthNumber: Int,
        monthTitle: String,
        completedLessonTitles: List<String>,
        averageExamScore: Int,
        dueConceptTitles: List<String>,
        weakAreas: List<String>,
        totalMinutesThisMonth: Long
    ): Result<AiMonthlyReport> {
        return try {
            val prompt = """
                Bir öğrencinin $monthNumber. Ay ($monthTitle) öğrenme verilerine göre Türkçe aylık gelişim raporu oluştur.
                Tamamlanan Dersler: ${completedLessonTitles.ifEmpty { listOf("Henüz ders tamamlanmadı") }.joinToString(", ")}
                Ortalama Sınav Puanı: %$averageExamScore
                Toplam Çalışma Süresi: $totalMinutesThisMonth dakika
                Tekrar Bekleyen Kavramlar: ${dueConceptTitles.ifEmpty { listOf("Yok") }.joinToString(", ")}
                Eksik Alanlar: ${weakAreas.ifEmpty { listOf("Belirgin eksik yok") }.joinToString(", ")}

                Şu 6 soruyu veriye dayalı, dürüst ve yapıcı biçimde yanıtla:
                1. Bu ay neler öğrendim?
                2. Hangi konularda başarılı oldum?
                3. Hangi konularda eksiklerim var?
                4. Hangi kavramları tekrar etmeliyim?
                5. Bir sonraki ay hangi konulara ağırlık vermeliyim?
                6. Farklı disiplinler arasında hangi bağlantıları kurabildim?
            """.trimIndent()

            val response = withTimeout(60_000L) {
                reportModel.generateContent(prompt)
            }
            val jsonStr = response.text ?: throw IllegalStateException("Rapor yanıtı boş geldi.")
            val json = JSONObject(jsonStr)
            Result.success(
                AiMonthlyReport(
                    learnedThisMonth = json.optString("learnedThisMonth", ""),
                    successfulTopics = json.optString("successfulTopics", ""),
                    missingAreas = json.optString("missingAreas", ""),
                    conceptsToReview = json.optString("conceptsToReview", ""),
                    nextMonthFocus = json.optString("nextMonthFocus", ""),
                    interdisciplinaryLinks = json.optString("interdisciplinaryLinks", "")
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Monthly report timed out", e)
            Result.failure(Exception("Aylık rapor oluşturma zaman aşımına uğradı."))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Monthly report generation failed", e)
            Result.failure(e)
        }
    }
}
