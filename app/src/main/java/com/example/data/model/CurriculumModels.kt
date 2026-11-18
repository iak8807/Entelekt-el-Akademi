package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.DisciplineEconomics
import com.example.ui.theme.DisciplineHistory
import com.example.ui.theme.DisciplineLiterature
import com.example.ui.theme.DisciplinePhilosophy
import com.example.ui.theme.DisciplinePsychology
import com.example.ui.theme.DisciplineReligion
import com.example.ui.theme.DisciplineScience
import com.example.ui.theme.DisciplineTech

enum class DisciplineId(
    val title: String,
    val shortTitle: String,
    val purpose: String,
    val accentColor: Color,
    val subtopics: List<String>,
    val keyThinkers: List<String>
) {
    FELSEFE(
        title = "Felsefe Okuryazarlığı",
        shortTitle = "Felsefe",
        purpose = "Düşünmenin, sorgulamanın, akıl yürütmenin ve bilgiye ulaşmanın yöntemlerini öğrenmek.",
        accentColor = DisciplinePhilosophy,
        subtopics = listOf(
            "Felsefe nedir?", "Felsefi düşüncenin doğuşu", "Ontoloji ve varlık felsefesi",
            "Epistemoloji ve bilgi felsefesi", "Etik ve ahlak felsefesi", "Mantık ve argüman analizi",
            "Siyaset felsefesi", "Estetik", "Özgür irade ve determinizm",
            "Bilinç ve zihin felsefesi", "İyi yaşam ve mutluluk", "Modern felsefe ve çağdaş düşünce"
        ),
        keyThinkers = listOf(
            "Sokrates", "Platon", "Aristoteles", "Epiktetos", "Farabi", "İbn Sina",
            "İbn Rüşd", "Gazali", "Descartes", "Spinoza", "Hume", "Kant",
            "Hegel", "Nietzsche", "Sartre", "Hannah Arendt"
        )
    ),
    PSIKOLOJI(
        title = "Psikoloji ve İnsan Davranışları",
        shortTitle = "Psikoloji",
        purpose = "İnsan zihnini, davranışlarını, öğrenme süreçlerini, duygularını ve toplumsal ilişkilerini bilimsel temelde anlamak.",
        accentColor = DisciplinePsychology,
        subtopics = listOf(
            "Psikoloji biliminin doğuşu", "Bilişsel psikoloji", "Bellek ve öğrenme",
            "Dikkat ve algı", "Motivasyon", "Duygular", "Kişilik kuramları",
            "Sosyal psikoloji", "Gelişim psikolojisi", "Bilişsel yanlılıklar",
            "Karar verme", "Alışkanlık oluşturma", "İnsan ilişkileri", "Psikolojik dayanıklılık"
        ),
        keyThinkers = listOf(
            "William James", "Jean Piaget", "Lev Vygotsky", "B. F. Skinner",
            "Albert Bandura", "Daniel Kahneman", "Aaron Beck", "Viktor Frankl"
        )
    ),
    BILIM(
        title = "Bilim ve Bilimsel Düşünce",
        shortTitle = "Bilim",
        purpose = "Bilimsel yöntemi anlamak, kanıtları değerlendirmek ve doğa olaylarını bilimsel ilkelerle açıklayabilmek.",
        accentColor = DisciplineScience,
        subtopics = listOf(
            "Bilim nedir?", "Bilimsel yöntem", "Hipotez, teori ve bilimsel kanun",
            "Deney ve gözlem", "Nedensellik ve korelasyon", "İstatistik ve olasılık",
            "Fizik", "Kimya", "Biyoloji", "Genetik", "Ekoloji", "Jeoloji",
            "Astronomi", "Matematiksel düşünme", "Bilim felsefesi", "Bilimsel bilgi ve yanlış bilim"
        ),
        keyThinkers = listOf(
            "Galileo Galilei", "Isaac Newton", "Charles Darwin", "Albert Einstein",
            "Marie Curie", "Richard Feynman", "Carl Sagan", "Karl Popper"
        )
    ),
    TARIH(
        title = "Tarih ve Medeniyet",
        shortTitle = "Tarih",
        purpose = "İnsanlık tarihini, medeniyetlerin gelişimini, siyasi ve toplumsal dönüşümleri neden-sonuç ilişkisi içinde anlamak.",
        accentColor = DisciplineHistory,
        subtopics = listOf(
            "Tarih bilimi ve kaynak eleştirisi", "Tarih öncesi dönemler", "İlk yerleşimler ve medeniyetler",
            "Mezopotamya ve Antik Mısır", "Antik Yunan", "Roma medeniyeti", "Orta Çağ",
            "İslam medeniyetinin doğuşu ve gelişimi", "Türk tarihi", "Selçuklu ve Osmanlı",
            "Rönesans ve Reform", "Coğrafi keşifler", "Aydınlanma", "Sanayi Devrimi",
            "Fransız Devrimi", "Modern dünya", "20. yüzyıl", "Çağdaş dünya tarihi"
        ),
        keyThinkers = listOf(
            "Herodot", "İbn Haldun", "Halil İnalcık", "Fuat Sezgin",
            "Fernand Braudel", "William H. McNeill"
        )
    ),
    EKONOMI(
        title = "Ekonomi ve Finans Okuryazarlığı",
        shortTitle = "Ekonomi",
        purpose = "Ekonomik sistemleri, ekonomik kararları, finansal kavramları ve kişisel finans yönetimini anlayabilmek.",
        accentColor = DisciplineEconomics,
        subtopics = listOf(
            "Ekonomi biliminin temel ilkeleri", "Kıtlık ve fırsat maliyeti", "Arz ve talep",
            "Piyasa dengesi", "Üretim ve tüketim", "Enflasyon", "Faiz", "İşsizlik", "GSYH",
            "Para ve bankacılık", "Merkez bankaları", "Maliye politikası", "Mikroekonomi",
            "Makroekonomi", "Ekonomik sistemler", "Gelir dağılımı ve yoksulluk",
            "Bütçe yönetimi", "Tasarruf ve yatırım", "Bileşik getiri", "Finansal risk"
        ),
        keyThinkers = listOf(
            "Adam Smith", "David Ricardo", "Karl Marx", "John Maynard Keynes",
            "Friedrich Hayek", "Amartya Sen", "Daron Acemoğlu"
        )
    ),
    EDEBIYAT_SANAT(
        title = "Edebiyat ve Sanat Okuryazarlığı",
        shortTitle = "Edebiyat & Sanat",
        purpose = "Edebî eserleri ve sanat üretimlerini yalnızca tüketmek değil, analiz etmek, yorumlamak ve estetik açıdan değerlendirebilmek.",
        accentColor = DisciplineLiterature,
        subtopics = listOf(
            "Edebiyat nedir?", "Roman, öykü, şiir, deneme ve tiyatro", "Karakter analizi",
            "Anlatıcı ve bakış açısı", "Tema ve sembol", "Edebî akımlar", "Klasik edebiyat",
            "Modern edebiyat", "Türk edebiyatı", "Dünya edebiyatı", "Sanat tarihi",
            "Resim", "Heykel", "Mimari", "Müzik", "Sinema", "Estetik değerlendirme"
        ),
        keyThinkers = listOf(
            "Dostoyevski", "Tolstoy", "Cervantes", "Shakespeare", "Kafka", "Camus",
            "Virginia Woolf", "George Orwell", "Gabriel García Márquez", "Yaşar Kemal", "Sabahattin Ali"
        )
    ),
    TEKNOLOJI_YZ(
        title = "Teknoloji ve Yapay Zekâ Okuryazarlığı",
        shortTitle = "Teknoloji & YZ",
        purpose = "Dijital dünyayı anlamak, teknolojiyi bilinçli kullanmak ve yapay zekâ araçlarıyla üretim yapabilmek.",
        accentColor = DisciplineTech,
        subtopics = listOf(
            "Bilgisayarın çalışma mantığı", "Donanım ve yazılım", "İşletim sistemleri",
            "İnternet ve ağlar", "Veri ve veri tabanları", "Algoritma mantığı",
            "Programlamaya giriş", "Python temelleri", "Yapay zekâ nedir?",
            "Makine öğrenmesi", "Derin öğrenme", "Üretken yapay zekâ", "Büyük dil modelleri",
            "Prompt engineering", "Yapay zekâ etiği", "Siber güvenlik",
            "Dijital mahremiyet", "Otomasyon", "Yapay zekâ ile proje geliştirme"
        ),
        keyThinkers = listOf(
            "Alan Turing", "Ada Lovelace", "John von Neumann", "Claude Shannon",
            "Geoffrey Hinton", "Yann LeCun", "Fei-Fei Li", "Tim Berners-Lee"
        )
    ),
    DIN_AHLAK(
        title = "Din, Ahlak ve Maneviyat Okuryazarlığı",
        shortTitle = "Din & Ahlak",
        purpose = "Din, ahlak, maneviyat ve insanın anlam arayışını tarihsel, felsefi ve ilmî açıdan inceleyebilmek.",
        accentColor = DisciplineReligion,
        subtopics = listOf(
            "Din kavramı", "İnanç ve ibadet", "Ahlak felsefesi", "Din ve toplum",
            "Dinler tarihi", "İslam'ın temel kaynakları", "Kur'an'ın yapısı ve ana konuları",
            "Siyer", "Hadis ilmi", "İslam ahlakı", "İslam düşünce tarihi",
            "İslam medeniyeti", "İslam felsefesi", "İslam hukuk düşüncesi",
            "Din ve bilim ilişkisi", "Din ve modernite", "Maneviyat ve insanın anlam arayışı"
        ),
        keyThinkers = listOf(
            "Gazali", "Farabi", "İbn Sina", "İbn Rüşd",
            "İbn Haldun", "Maturidi", "Eşari", "Mevlânâ"
        )
    )
}

enum class LessonLength(val label: String, val rangeLabel: String) {
    KISA("Kısa Ders", "5–10 dk"),
    STANDART("Standart Ders", "15–25 dk"),
    DERINLEMESINE("Derinlemesine Ders", "30–45 dk")
}

enum class CompetencyType(val label: String) {
    BILGI("Bilgi (%25)"),
    ANLAMA("Anlama (%25)"),
    UYGULAMA("Uygulama (%25)"),
    ANALIZ_SENTEZ("Analiz ve Sentez (%25)")
}

enum class QuestionType(val label: String) {
    MULTIPLE_CHOICE("Çoktan Seçmeli"),
    TRUE_FALSE("Doğru / Yanlış"),
    FILL_IN_BLANK("Boşluk Doldurma"),
    MATCHING("Eşleştirme"),
    SHORT_ANSWER("Kısa Cevap"),
    OPEN_ENDED_ANALYSIS("Açık Uçlu Analiz"),
    CASE_RESOLUTION("Örnek Olay Çözümleme")
}

enum class ReferenceType(val label: String) {
    AKADEMIK_KITAP("Akademik Kitap"),
    HAKEMLI_MAKALE("Hakemli Makale"),
    UNIVERSITE_YAYINI("Üniversite Yayını"),
    BILIMSEL_KURUM("Bilimsel Kurum"),
    BIRINCIL_TARIHSEL("Birincil Tarihsel Kaynak"),
    GUVENILIR_DINI("Güvenilir Dinî Kaynak")
}

enum class RecallLevel(val label: String, val code: String, val description: String) {
    NONE("Hiç hatırlamıyorum", "NONE", "Kavram yarın tekrar programına alınır."),
    HARD("Zor hatırlıyorum", "HARD", "Kısa aralıkla (1 gün sonra) pekiştirilir."),
    PARTIAL("Kısmen hatırlıyorum", "PARTIAL", "Standart adım olan bir sonraki aralığa geçilir."),
    EASY("Kolay hatırlıyorum", "EASY", "Uzun aralığa (3–30 gün) taşınır.")
}

enum class TutorMode(val title: String, val subtitle: String, val systemDirective: String) {
    KONU_ANLATIMI(
        "Konu Anlatımı",
        "Yapılandırılmış, derinlemesine ve net açıklama",
        "Konuyu akademik titizlikle, temel kavramlardan başlayarak katmanlı ve örneklerle açıkla."
    ),
    SORU_CEVAP(
        "Soru-Cevap",
        "Doğrudan ve kaynak odaklı akademik yanıt",
        "Kullanıcının sorusunu doğrudan, kanıta dayalı ve kavramları birbirine karıştırmadan yanıtla."
    ),
    SOKRATIK_TARTISMA(
        "Sokratik Tartışma",
        "Cevabı hazır vermeden sorularla düşündürme",
        "Hazır cevap vermek yerine düşündürücü, varsayımları sorgulatan Sokratik sorular sor; adım adım kullanıcıyı sonuca ulaştır."
    ),
    SINAVA_HAZIRLIK(
        "Sınava Hazırlık",
        "Aktif hatırlama ve ölçme soruları",
        "Kullanıcıya 4 öğrenme seviyesinde (Tanıma, Anlama, Uygulama, Analiz) kısa yoklama soruları yönelt ve cevabını değerlendir."
    ),
    KAVRAM_ACIKLAMA(
        "Kavram Açıklama",
        "Feynman tekniği ve günlük hayat örnekleri",
        "Karmaşık kavramı önce teknik doğruluğuyla tanımla, sonra Feynman tekniğiyle sadeleştir ve günlük hayattan somut bir örnek ver."
    ),
    METIN_ANALIZI(
        "Metin Analizi",
        "Kaynak eleştirisi, argüman ve tema çözümleme",
        "Paylaşılan veya dersteki bir metni/alıntıyı ana tez, gizli varsayımlar, kanıt kalitesi ve tarihsel bağlam açısından çözümle."
    ),
    ELESTIREL_DUSUNME(
        "Eleştirel Düşünme",
        "Mantık hataları ve karşıt tezleri sınama",
        "Bir iddiayı safsatalar, bilişsel yanlılıklar ve karşıt argümanlar üzerinden test et; alternatif bakış açılarını sun."
    ),
    DISIPLINLER_ARASI(
        "Disiplinler Arası Analiz",
        "Sekiz disiplin arasında köprü kurma",
        "Konuyu Felsefe, Psikoloji, Bilim, Tarih, Ekonomi, Edebiyat/Sanat, Teknoloji ve Din/Ahlak perspektiflerinden en az üçüyle ilişkilendir."
    )
}

data class ConceptCard(
    val title: String,
    val definition: String,
    val everydayAnchor: String
)

data class ContentSection(
    val heading: String,
    val body: String
)

data class ThinkerHighlight(
    val name: String,
    val era: String,
    val contribution: String
)

data class ComparisonItem(
    val title: String,
    val leftLabel: String,
    val leftDetail: String,
    val rightLabel: String,
    val rightDetail: String
)

data class ConceptRelation(
    val source: String,
    val relation: String,
    val target: String
)

data class CaseStudy(
    val title: String,
    val scenario: String,
    val analysisQuestions: List<String>,
    val keyTakeaway: String
)

data class ActiveRecallItem(
    val prompt: String,
    val answerGuide: String
)

data class QuizQuestion(
    val id: String,
    val type: QuestionType,
    val competency: CompetencyType,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctOptionIndex: Int = 0,
    val correctBool: Boolean = true,
    val acceptedAnswers: List<String> = emptyList(),
    val matchingPairs: List<Pair<String, String>> = emptyList(),
    val explanation: String,
    val rubricHint: String = ""
)

data class PracticeTask(
    val title: String,
    val instructions: String,
    val deliverableHint: String
)

data class AcademicReference(
    val citation: String,
    val type: ReferenceType,
    val note: String
)

data class Lesson(
    val id: String,
    val monthNumber: Int,
    val weekNumber: Int,
    val discipline: DisciplineId,
    val title: String,
    val durationMinutes: Int,
    val lengthCategory: LessonLength,
    val purpose: String,
    val learningOutcomes: List<String>,
    val prerequisites: List<String>,
    val keyConcepts: List<ConceptCard>,
    val detailedContent: List<ContentSection>,
    val historicalBackground: String,
    val realLifeExamples: List<String>,
    val keyThinkers: List<ThinkerHighlight>,
    val comparativeExplanations: List<ComparisonItem>,
    val conceptMapLinks: List<ConceptRelation>,
    val caseStudy: CaseStudy,
    val reflectionQuestions: List<String>,
    val summary: String,
    val activeRecallPrompts: List<ActiveRecallItem>,
    val quizQuestions: List<QuizQuestion>,
    val practiceTask: PracticeTask,
    val references: List<AcademicReference>
)

data class MonthRoadmap(
    val monthNumber: Int,
    val title: String,
    val subtitle: String,
    val mainGoal: String,
    val subGoals: List<String>,
    val disciplines: List<DisciplineId>,
    val weeklyModules: List<String>,
    val readingTexts: List<String>,
    val practiceActivities: List<String>,
    val capstoneProjectTitle: String,
    val capstoneProjectDescription: String,
    val monthlyExamTitle: String
)

data class AcademicBadge(
    val id: String,
    val title: String,
    val description: String,
    val criteriaLabel: String,
    val isEarned: Boolean
)
