package com.example.data.curriculum

import com.example.data.model.*
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.ceil

object CurriculumCatalog {

    val istanbulZone: ZoneId = ZoneId.of("Europe/Istanbul")
    val turkishLocale: Locale = Locale("tr", "TR")

    fun todayIstanbul(): LocalDate = LocalDate.now(istanbulZone)
    fun todayEpochDay(): Long = todayIstanbul().toEpochDay()

    fun formatTurkishDate(epochDay: Long): String {
        val date = LocalDate.ofEpochDay(epochDay)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE", turkishLocale)
        return date.format(formatter)
    }

    fun formatShortDate(epochDay: Long): String {
        val date = LocalDate.ofEpochDay(epochDay)
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy", turkishLocale)
        return date.format(formatter)
    }

    fun formatNumber(value: Number): String = NumberFormat.getInstance(turkishLocale).format(value)

    val allLessons: List<Lesson> by lazy {
        months1To3Lessons + months4To6Lessons + months7To9Lessons + months10To12Lessons
    }

    val monthRoadmaps: List<MonthRoadmap> = listOf(
        MonthRoadmap(
            monthNumber = 1,
            title = "Düşünmeyi Öğrenmek",
            subtitle = "Felsefe, mantık, sorgulama, argüman ve eleştirel düşünme",
            mainGoal = "Dogmatik kabullerden sıyrılarak gerekçeli, tutarlı ve eleştirel akıl yürütme disiplini kazanmak.",
            subGoals = listOf(
                "Sokratik sorgulama ile kendi varsayımlarını test etmek",
                "Ontoloji ve epistemolojinin temel sorularını ayırt etmek",
                "Günlük tartışmalardaki mantık safsatalarını tespit etmek",
                "Erdem etiği, ödev etiği ve iyi yaşam modellerini karşılaştırmak"
            ),
            disciplines = listOf(DisciplineId.FELSEFE),
            weeklyModules = listOf(
                "Hafta 1: Mitos'tan Logos'a ve Sokratik Yöntem",
                "Hafta 2: Epistemoloji ve Argüman Analizi",
                "Hafta 3: Mantık Safsataları Uygulaması",
                "Hafta 4: Etik, Özgür İrade ve Siyaset Felsefesi"
            ),
            readingTexts = listOf("Platon — Sokrates'in Savunması", "Descartes — Metot Üzerine Konuşma", "Epiktetos — Enkheiridion"),
            practiceActivities = listOf("Sokratik 4 Soru Deneyi", "Haber Argüman Haritası", "Kontrol Dikotomisi Günlüğü"),
            capstoneProjectTitle = "Ay 1 Analiz Görevi: Kişisel Düşünme Manifestosu",
            capstoneProjectDescription = "Sorgulamadan kabul ettiğiniz bir toplumsal yargıyı Sokratik yöntem ve safsata denetimiyle yeniden inceleyen 1 sayfalık değerlendirme yazın.",
            monthlyExamTitle = "Ay 1 Genel Değerlendirme Sınavı — Düşünmeyi Öğrenmek"
        ),
        MonthRoadmap(
            monthNumber = 2,
            title = "Kendini ve İnsanı Anlamak",
            subtitle = "Psikoloji, bellek, dikkat, öğrenme, davranış ve karar verme",
            mainGoal = "İnsan zihninin çalışma ilkelerini, bilişsel yanlılıklarını ve kalıcı öğrenme mekanizmalarını kavramak.",
            subGoals = listOf(
                "Çalışma belleği ve uzun süreli bellek ilkelerini kendi çalışmasına uygulamak",
                "Sistem 1/Sistem 2 ayrımıyla karar hatalarını azaltmak",
                "Alışkanlık mimarisi ve anlam odaklı dayanıklılık geliştirmek"
            ),
            disciplines = listOf(DisciplineId.PSIKOLOJI),
            weeklyModules = listOf(
                "Hafta 1: Dikkat, Bellek ve Aktif Hatırlama",
                "Hafta 2: Bilişsel Yanlılıklar ve Sistem 1/2",
                "Hafta 3: Bilişsel Esneklik ve Alışkanlık Döngüsü",
                "Hafta 4: Motivasyon, Sosyal Psikoloji ve Dayanıklılık"
            ),
            readingTexts = listOf("Daniel Kahneman — Hızlı ve Yavaş Düşünme", "Viktor Frankl — İnsanın Anlam Arayışı"),
            practiceActivities = listOf("25 Dk Derin Odak Deneyi", "Alışkanlık Zinciri Kurulumu", "Anlam Bildirgesi"),
            capstoneProjectTitle = "Ay 2 Analiz Görevi: Bilişsel Yanlılık Öz-Denetimi",
            capstoneProjectDescription = "Son haftalardaki 3 kararınızı Sistem 1 yanlılıkları açısından inceleyip Sistem 2 kontrol mekanizması tasarlayın.",
            monthlyExamTitle = "Ay 2 Genel Değerlendirme Sınavı — Kendini ve İnsanı Anlamak"
        ),
        MonthRoadmap(
            monthNumber = 3,
            title = "Bilimsel Düşünme",
            subtitle = "Bilimsel yöntem, kanıt, deney, istatistik ve bilimsel okuryazarlık",
            mainGoal = "Doğal dünyayı bilimsel yasa ve kuramlarla açıklayabilmek, kanıtı sözde bilimden ayırabilmek.",
            subGoals = listOf(
                "Yanlışlanabilirlik ilkesini kavramak",
                "Korelasyon ve nedensellik ayrımını istatistikte uygulamak",
                "Fizik, kimya, biyoloji, ekoloji ve matematiksel düşünmeyi bütünleştirmek"
            ),
            disciplines = listOf(DisciplineId.BILIM),
            weeklyModules = listOf(
                "Hafta 1: Bilimsel Yöntem ve Popper",
                "Hafta 2: Fizik, Kimya, Jeoloji ve Astronomi",
                "Hafta 3: İstatistik ve Olasılık Okuryazarlığı",
                "Hafta 4: Biyoloji, Genetik, Ekoloji ve Üstel Düşünme"
            ),
            readingTexts = listOf("Carl Sagan — Karanlık Bir Dünyada Bilimin Mum Işığı", "Richard Feynman — Fizik Yasaları Üzerine"),
            practiceActivities = listOf("Sahte Korelasyon Avı", "Bilimsel Ölçek Karşılaştırması", "Temel Oran Olasılık Hesabı"),
            capstoneProjectTitle = "Ay 3 Analiz Görevi: Bilimsel Kanıt Raporu",
            capstoneProjectDescription = "Popüler bir sağlık veya çevre iddiasını kontrol grubu, örneklem ve yanlışlanabilirlik ölçütleriyle değerlendirin.",
            monthlyExamTitle = "Ay 3 Genel Değerlendirme Sınavı — Bilimsel Düşünme"
        ),
        MonthRoadmap(
            monthNumber = 4,
            title = "İnsanlık Tarihini Anlamak",
            subtitle = "İlk medeniyetlerden Antik Çağ'a kadar tarihsel gelişim",
            mainGoal = "Tarihi kaynak eleştirisiyle okumayı ve Neolitik Devrim'den Antik Akdeniz uygarlıklarına uzanan kurumsal gelişimi kavramayı sağlamak.",
            subGoals = listOf(
                "Birincil ve ikincil kaynak tenkidini uygulamak",
                "Mezopotamya, Mısır, Yunan ve Roma miraslarını karşılaştırmak",
                "Anadolu ve İpek Yolu'nu etkileşim ağı olarak okumak"
            ),
            disciplines = listOf(DisciplineId.TARIH),
            weeklyModules = listOf(
                "Hafta 1: Tarih Bilimi ve İlk Uygarlıklar",
                "Hafta 2: Antik Yunan ve Roma Hukuku",
                "Hafta 3: İbn Haldun ve Uzun Süreli Tarih",
                "Hafta 4: Anadolu ve İpek Yolu Etkileşimleri"
            ),
            readingTexts = listOf("İbn Haldun — Mukaddime (Seçmeler)", "Fernand Braudel — Akdeniz"),
            practiceActivities = listOf("İki Kaynak Karşılaştırması", "Uzun Süre Şehir Analizi", "Kültürel Difüzyon İzleme"),
            capstoneProjectTitle = "Ay 4 Analiz Görevi: Birincil Kaynak Eleştirisi",
            capstoneProjectDescription = "Antik dönemden seçilen bir yazıt veya vakayinameyi dış ve iç tenkit ilkeleriyle inceleyin.",
            monthlyExamTitle = "Ay 4 Genel Değerlendirme Sınavı — İnsanlık Tarihini Anlamak"
        ),
        MonthRoadmap(
            monthNumber = 5,
            title = "Medeniyetler ve Tarihsel Dönüşüm",
            subtitle = "Orta Çağ, İslam medeniyeti, Türk tarihi, Rönesans ve modernleşme",
            mainGoal = "İslam ve Türk medeniyetlerinin bilimsel-kurumsal katkılarını ve modern dünyayı kuran büyük devrimleri neden-sonuç zincirinde kavramak.",
            subGoals = listOf(
                "Fuat Sezgin perspektifiyle İslam bilim tarihini tanımak",
                "Selçuklu ve Osmanlı kurumlarını (vakıf, ahilik, tımar) anlamak",
                "Rönesans, Aydınlanma, Fransız ve Sanayi Devrimlerini bağlamak"
            ),
            disciplines = listOf(DisciplineId.TARIH),
            weeklyModules = listOf(
                "Hafta 1: Orta Çağ ve İslam Bilim Çağı",
                "Hafta 2: Türk Tarihi, Selçuklu ve Osmanlı",
                "Hafta 3: Rönesans, Reform ve Coğrafi Keşifler",
                "Hafta 4: Aydınlanma, Sanayi Devrimi ve 20. Yüzyıl"
            ),
            readingTexts = listOf("Halil İnalcık — Klasik Çağ", "Fuat Sezgin — İslam'da Bilim ve Teknik"),
            practiceActivities = listOf("Bilim Tarihi Zinciri", "Kurumsal Süreklilik Analizi", "500 Yıllık Zaman Çizelgesi"),
            capstoneProjectTitle = "Ay 5 Analiz Görevi: Medeniyet Dönüşüm Karşılaştırması",
            capstoneProjectDescription = "Coğrafi Keşifler ve Sanayi Devrimi'nin Osmanlı ve dünya dengelerine etkisini neden-sonuç ilişkisiyle çözümleyin.",
            monthlyExamTitle = "Ay 5 Genel Değerlendirme Sınavı — Medeniyetler ve Dönüşüm"
        ),
        MonthRoadmap(
            monthNumber = 6,
            title = "Ekonomik Dünyayı Anlamak",
            subtitle = "Ekonominin temel ilkeleri, piyasa, enflasyon, para ve finans",
            mainGoal = "Mikroekonomi, makroekonomi ve kişisel finans yönetimini günlük kararlara uygulanabilir biçimde öğrenmek.",
            subGoals = listOf(
                "Kıtlık, fırsat maliyeti ve arz-talep dengesini kullanmak",
                "Enflasyon, faiz, GSYH ve merkez bankası araçlarını yorumlamak",
                "Bütçe, bileşik getiri ve risk çeşitlendirmesi uygulamak"
            ),
            disciplines = listOf(DisciplineId.EKONOMI),
            weeklyModules = listOf(
                "Hafta 1: Kıtlık, Fırsat Maliyeti ve Arz-Talep",
                "Hafta 2: Makroekonomi, Enflasyon ve Kurumlar",
                "Hafta 3: İktisat Düşünce Tarihi (Smith–Acemoğlu)",
                "Hafta 4: Kişisel Finans, Bileşik Getiri ve Risk"
            ),
            readingTexts = listOf("Adam Smith — Ulusların Zenginliği (Giriş)", "Daron Acemoğlu — Ulusların Düşüşü"),
            practiceActivities = listOf("Zaman Fırsat Maliyeti", "Kişisel Enflasyon Sepeti", "50/30/20 Bütçe Taslağı"),
            capstoneProjectTitle = "Ay 6 Proje Görevi: Kişisel Finans & Makro Görünüm Planı",
            capstoneProjectDescription = "Reel getiri, acil durum fonu ve kişisel bütçe dengesini içeren 12 aylık finansal dayanıklılık planı hazırlayın.",
            monthlyExamTitle = "Ay 6 Genel Değerlendirme Sınavı — Ekonomik Dünyayı Anlamak"
        ),
        MonthRoadmap(
            monthNumber = 7,
            title = "Edebiyatı Okumak ve Yorumlamak",
            subtitle = "Edebî türler, roman analizi, karakter ve anlatı çözümlemesi",
            mainGoal = "Türk ve dünya edebiyatı başyapıtlarını anlatıcı, tema, sembol ve tarihsel bağlam açısından derinlemesine çözümleyebilmek.",
            subGoals = listOf(
                "Anlatıcı ve bakış açısı katmanlarını tanımak",
                "Klasik ve modern dünya yazarları ile Türk edebiyatını karşılaştırmak",
                "Üç katmanlı eleştirel okuma pratiği edinmek"
            ),
            disciplines = listOf(DisciplineId.EDEBIYAT_SANAT),
            weeklyModules = listOf(
                "Hafta 1: Edebî Türler ve Karakter Analizi",
                "Hafta 2: Dünya Edebiyatı Akımları",
                "Hafta 3: Türk Edebiyatı (Yaşar Kemal & Sabahattin Ali)",
                "Hafta 4: Eleştirel Okuma Atölyesi"
            ),
            readingTexts = listOf("Dostoyevski — Suç ve Ceza", "Sabahattin Ali — Kürk Mantolu Madonna", "George Orwell — 1984"),
            practiceActivities = listOf("Kısa Metin Katman Analizi", "Karşılaştırmalı Öykü İncelemesi", "Kitap Değerlendirme Kartı"),
            capstoneProjectTitle = "Ay 7 Analiz Görevi: Edebî Eser Çözümleme Denemesi",
            capstoneProjectDescription = "Seçilen bir roman veya uzun öyküyü karakter psikolojisi, anlatıcı ve toplumsal tema ekseninde inceleyin.",
            monthlyExamTitle = "Ay 7 Genel Değerlendirme Sınavı — Edebiyatı Okumak"
        ),
        MonthRoadmap(
            monthNumber = 8,
            title = "Sanatı ve Kültürü Anlamak",
            subtitle = "Sanat tarihi, estetik, mimari, müzik ve sinema",
            mainGoal = "Görsel ve işitsel sanatları biçim, teknoloji ve anlam katmanlarıyla okuyabilmek.",
            subGoals = listOf(
                "Resim ve heykelde perspektif ve soyutlama evrimini tanımak",
                "Mimari, müzik ve sinema dilinin temel öğelerini çözümlemek",
                "Algoritma çağında özgün estetik yargı geliştirmek"
            ),
            disciplines = listOf(DisciplineId.EDEBIYAT_SANAT),
            weeklyModules = listOf(
                "Hafta 1: Resim, Heykel ve Perspektif",
                "Hafta 2: Mimar Sinan ve Mekân Estetiği",
                "Hafta 3: Müzik ve Sinema Kurgusu",
                "Hafta 4: Estetik Değerlendirme ve Kültür Eleştirisi"
            ),
            readingTexts = listOf("E. H. Gombrich — Sanatın Öyküsü", "John Berger — Görme Biçimleri", "Doğan Kuban — Sinan'ın Sanatı"),
            practiceActivities = listOf("Eser Analiz Notu", "5 Dk Sinema Sahnesi Çözümlemesi", "Kültür Diyeti Planı"),
            capstoneProjectTitle = "Ay 8 Analiz Görevi: Disiplinler Arası Estetik İnceleme",
            capstoneProjectDescription = "Bir mimari yapı veya klasik sinema sahnesini ışık, oran, müzik ve tarihsel bağlam açısından değerlendirin.",
            monthlyExamTitle = "Ay 8 Genel Değerlendirme Sınavı — Sanatı ve Kültürü Anlamak"
        ),
        MonthRoadmap(
            monthNumber = 9,
            title = "Teknolojiyi Anlamak",
            subtitle = "Bilgisayar bilimi, internet, algoritmalar, veri ve programlamaya giriş",
            mainGoal = "Bilgisayar sistemlerinin işleyişini sıfırdan anlamak, algoritma kurmak ve Python temelleriyle basit araçlar tasarlamak.",
            subGoals = listOf(
                "Donanım, işletim sistemi, DNS ve HTTPS mantığını bilmek",
                "Python değişkenleri, koşulları ve döngüleriyle bütçe/veri mantığı kurmak",
                "Veri tabanları ve siber güvenlik hijyenini uygulamak"
            ),
            disciplines = listOf(DisciplineId.TEKNOLOJI_YZ),
            weeklyModules = listOf(
                "Hafta 1: İkili Sistem, Donanım ve İnternet",
                "Hafta 2: Algoritma Mantığı",
                "Hafta 3: Sıfırdan Python ve Bütçe Projesi",
                "Hafta 4: Veri Tabanları ve Siber Güvenlik"
            ),
            readingTexts = listOf("Charles Petzold — Code", "Allen Downey — Think Python"),
            practiceActivities = listOf("2FA Güvenlik Denetimi", "Python Bütçe Aracı Tasarımı", "Veri Tablosu Şeması"),
            capstoneProjectTitle = "Ay 9 Proje Görevi: Kişisel Bütçe & Veri Tablosu Python Tasarımı",
            capstoneProjectDescription = "Değişken, liste/sözlük, koşul ve döngü kullanarak aylık giderleri veya okuma kayıtlarını özetleyen Python algoritmasını hazırlayın.",
            monthlyExamTitle = "Ay 9 Genel Değerlendirme Sınavı — Teknolojiyi Anlamak"
        ),
        MonthRoadmap(
            monthNumber = 10,
            title = "Yapay Zekâ Çağı",
            subtitle = "Makine öğrenmesi, üretken yapay zekâ, yapay zekâ etiği ve uygulamalar",
            mainGoal = "Üretken yapay zekâyı çalışma mantığı, prompt mühendisliği ve etik sınırlarıyla bilinçli biçimde kullanabilmek.",
            subGoals = listOf(
                "Makine öğrenmesi, derin öğrenme ve LLM kavramlarını ayırmak",
                "Yapılandırılmış istem (prompt) mühendisliğiyle verimli üretim yapmak",
                "Algoritmik önyargı, halüsinasyon ve insan denetimi ilkelerini uygulamak"
            ),
            disciplines = listOf(DisciplineId.TEKNOLOJI_YZ),
            weeklyModules = listOf(
                "Hafta 1: Makine Öğrenmesi ve Derin Öğrenme",
                "Hafta 2: Büyük Dil Modelleri ve Halüsinasyon",
                "Hafta 3: Prompt Engineering ve YZ Projeleri",
                "Hafta 4: Yapay Zekâ Etiği ve Dijital Mahremiyet"
            ),
            readingTexts = listOf("UNESCO YZ Eğitim Rehberi", "Cathy O'Neil — Matematiksel İmha Silahları"),
            practiceActivities = listOf("YZ Araştırma Asistanı Deneyi", "4 Bileşenli İstem Dönüşümü", "Gizlilik Denetimi"),
            capstoneProjectTitle = "Ay 10 Proje Görevi: YZ Destekli Araştırma ve Python İş Akışı",
            capstoneProjectDescription = "Yapay Zekâ Öğretmeni ile adım adım doğrulamalı bir araştırma veya küçük otomasyon tasarımını belgeleyin.",
            monthlyExamTitle = "Ay 10 Genel Değerlendirme Sınavı — Yapay Zekâ Çağı"
        ),
        MonthRoadmap(
            monthNumber = 11,
            title = "Din, Ahlak ve İnsanlığın Anlam Arayışı",
            subtitle = "Dinler tarihi, ahlak felsefesi, İslam düşüncesi ve maneviyat",
            mainGoal = "Din, ahlak ve maneviyatı kaynak ayrımına (Kur'an, sahih hadis, Hanefi/diğer mezhep içtihatları, ilmî yorum) sadık kalarak tarihsel ve felsefi derinlikle incelemek.",
            subGoals = listOf(
                "Dinler tarihi ve ahlak felsefesini ilmî tarafsızlıkla kavramak",
                "Kur'an, Siyer, Hadis usulü ve Hanefi fıkhı ile mezhep ayrımını bilmek",
                "Gazali, İbn Rüşd, Maturidi, Eşari ve Mevlânâ çizgisinde akıl–vahiy–maneviyat dengesini kurmak"
            ),
            disciplines = listOf(DisciplineId.DIN_AHLAK),
            weeklyModules = listOf(
                "Hafta 1: Din Kavramı, Dinler Tarihi ve Kelâm",
                "Hafta 2: Kur'an, Siyer ve Hadis Usulü",
                "Hafta 3: İslam Hukuk Düşüncesi ve Hanefi Fıkhı",
                "Hafta 4: Gazali, İbn Rüşd, Din-Bilim ve Mevlânâ"
            ),
            readingTexts = listOf("TDV İslam Ansiklopedisi İlgili Maddeler", "İbn Rüşd — Faslü'l-Makâl", "Gazali — El-Munkız"),
            practiceActivities = listOf("Ayet-Hadis-Yorum Ayrımı", "TDV İslam Ansiklopedisi İncelemesi", "Ahlak Muhasebesi"),
            capstoneProjectTitle = "Ay 11 Analiz Görevi: Kaynakça Temelli Din-Bilim-Ahlak İncelemesi",
            capstoneProjectDescription = "Seçilen ahlaki veya kelâmi bir meseleyi ayet, hadis, mezhep metodolojisi ve düşünce tarihi katmanlarını ayırarak analiz edin.",
            monthlyExamTitle = "Ay 11 Genel Değerlendirme Sınavı — Din, Ahlak ve Anlam Arayışı"
        ),
        MonthRoadmap(
            monthNumber = 12,
            title = "Disiplinler Arası Düşünme ve Sentez",
            subtitle = "Öğrenilen sekiz alanın bir araya getirilmesi, eleştirel değerlendirme ve bitirme projesi",
            mainGoal = "Sekiz disiplini tek bir zihinsel ağda birleştirerek bağımsız öğrenme sistemi ve bitirme sentezini tamamlamak.",
            subGoals = listOf(
                "Karmaşık sorunlara 8 disiplinin kavram setiyle yaklaşmak",
                "Ömür boyu kişisel bilgi bankası ve okuma düzenini oturtmak",
                "Bitirme projesini yazıp savunmak"
            ),
            disciplines = DisciplineId.entries,
            weeklyModules = listOf(
                "Hafta 1: Zihinsel Modeller Kafesi ve Sistem Düşüncesi",
                "Hafta 2: Bağımsız Araştırma ve Bilgi Ekosistemi",
                "Hafta 3: 8 Disiplin Çapraz Tekrarı",
                "Hafta 4: Bitirme Manifestosu ve Savunma"
            ),
            readingTexts = listOf("Kendi Notlarım ve Yıllık Seçki", "Sönke Ahrens — Akıllı Notlar"),
            practiceActivities = listOf("8 Pencere Egzersizi", "2. Yıl Okuma Listesi", "Bitirme Manifestosu"),
            capstoneProjectTitle = "Yıllık Bitirme Projesi: Sekiz Disiplin Büyük Sentez Manifestosu",
            capstoneProjectDescription = "Çağdaş bir insanlık sorununu sekiz disiplinin tamamından kavramlarla inceleyen özgün bitirme tezinizi hazırlayın.",
            monthlyExamTitle = "Ay 12 Büyük Sentez ve Mezuniyet Değerlendirmesi"
        )
    )

    fun lessonById(id: String): Lesson? = allLessons.firstOrNull { it.id == id }

    fun lessonsForMonth(monthNumber: Int): List<Lesson> =
        allLessons.filter { it.monthNumber == monthNumber }

    fun lessonsForDiscipline(disciplineId: DisciplineId): List<Lesson> =
        allLessons.filter { it.discipline == disciplineId }

    fun buildMonthlyExamQuestions(monthNumber: Int): List<QuizQuestion> {
        val monthLessons = lessonsForMonth(monthNumber)
        val collected = monthLessons.flatMap { it.quizQuestions }.toMutableList()
        if (collected.none { it.type == QuestionType.OPEN_ENDED_ANALYSIS || it.type == QuestionType.CASE_RESOLUTION }) {
            val roadmap = monthRoadmaps.first { it.monthNumber == monthNumber }
            collected.add(
                QuizQuestion(
                    id = "month_${monthNumber}_synthesis",
                    type = QuestionType.OPEN_ENDED_ANALYSIS,
                    competency = CompetencyType.ANALIZ_SENTEZ,
                    prompt = "${roadmap.title} ayı kapsamında öğrendiğiniz kavramları günlük yaşamdan somut bir örnekle ilişkilendirerek 5–7 cümlede sentezleyin.",
                    explanation = "Aylık sentez sorusu kavramsal doğruluk, mantıksal tutarlılık, gerekçelendirme ve örnek kullanımı ölçütleriyle değerlendirilir.",
                    rubricHint = "Ayın ana öğrenme hedefine uygun çok katmanlı analiz beklenir."
                )
            )
        }
        return collected
    }

    /**
     * Spaced repetition intervals: 0 -> 1 -> 3 -> 7 -> 14 -> 30 days.
     */
    fun computeNextSpacedState(
        currentIntervalDays: Long,
        currentReviewCount: Long,
        recallLevel: RecallLevel,
        todayEpoch: Long = todayEpochDay()
    ): Triple<Long, Long, Long> {
        val ladder = listOf(1L, 3L, 7L, 14L, 30L)
        val nextInterval = when (recallLevel) {
            RecallLevel.NONE -> 1L
            RecallLevel.HARD -> 1L
            RecallLevel.PARTIAL -> {
                val idx = ladder.indexOfFirst { it >= currentIntervalDays }.coerceAtLeast(0)
                ladder.getOrElse((idx + 1).coerceAtMost(ladder.lastIndex)) { 3L }
            }
            RecallLevel.EASY -> {
                val idx = ladder.indexOfFirst { it > currentIntervalDays }
                if (idx == -1) 30L else ladder.getOrElse((idx + 1).coerceAtMost(ladder.lastIndex)) { ladder[idx] }
            }
        }
        return Triple(nextInterval, todayEpoch + nextInterval, currentReviewCount + 1L)
    }

    /**
     * Flexible study planner: computes today's recommended lesson, carried-over count,
     * and dynamic estimated completion date without resetting on missed days.
     */
    data class StudyPlanSummary(
        val recommendedLesson: Lesson,
        val isTodayRestDay: Boolean,
        val carriedOverCount: Int,
        val completedCount: Int,
        val totalLessonsCount: Int,
        val estimatedCompletionEpochDay: Long,
        val currentAcademicMonth: Int
    )

    fun computeStudyPlan(
        profile: UserProfileDoc,
        progressMap: Map<String, LessonProgressDoc>,
        todayEpoch: Long = todayEpochDay()
    ): StudyPlanSummary {
        val completedIds = progressMap.values.filter { it.completed }.map { it.lessonId }.toSet()
        val remaining = allLessons.filterNot { it.id in completedIds }
        val nextLesson = remaining.firstOrNull() ?: allLessons.last()

        val daysElapsed = (todayEpoch - profile.programStartEpochDay).coerceAtLeast(0L)
        val expectedMonthlyPace = ((daysElapsed / 30L).toInt() + 1).coerceIn(1, 12)
        val expectedLessonsByNow = ((daysElapsed / 10L).toInt() + 1).coerceIn(1, allLessons.size)
        val carriedOver = (expectedLessonsByNow - completedIds.size).coerceAtLeast(0)

        val todayDow = LocalDate.ofEpochDay(todayEpoch).dayOfWeek.value.toLong()
        val isRestDay = todayDow !in profile.availableDays

        val activeDaysPerWeek = profile.availableDays.size.coerceAtLeast(1)
        val effectiveDailyMin = if (profile.busyDayMode) 15L else profile.dailyMinutesTarget.coerceAtLeast(15L)
        val remainingMinutes = remaining.sumOf { it.durationMinutes.toLong() }
        val weeklyCapacityMin = effectiveDailyMin * activeDaysPerWeek
        val weeksNeeded = if (remainingMinutes == 0L) 0L else ceil(remainingMinutes.toDouble() / weeklyCapacityMin.toDouble()).toLong()
        val estimatedFinish = todayEpoch + (weeksNeeded * 7L).coerceAtLeast(if (remaining.isEmpty()) 0L else 1L)

        return StudyPlanSummary(
            recommendedLesson = nextLesson,
            isTodayRestDay = isRestDay,
            carriedOverCount = carriedOver,
            completedCount = completedIds.size,
            totalLessonsCount = allLessons.size,
            estimatedCompletionEpochDay = estimatedFinish,
            currentAcademicMonth = nextLesson.monthNumber.coerceAtLeast(expectedMonthlyPace).coerceIn(1, 12)
        )
    }

    fun computeBadges(
        profile: UserProfileDoc,
        progressMap: Map<String, LessonProgressDoc>,
        examResults: List<ExamResultDoc>
    ): List<AcademicBadge> {
        val completedLessons = progressMap.values.filter { it.completed }
        val completedCount = completedLessons.size
        val completedIds = completedLessons.map { it.lessonId }.toSet()

        fun disciplineCompleted(disciplineId: DisciplineId): Boolean {
            val required = lessonsForDiscipline(disciplineId).map { it.id }
            return required.isNotEmpty() && required.all { it in completedIds }
        }

        val month1Completed = lessonsForMonth(1).all { it.id in completedIds }
        val all8Touched = DisciplineId.entries.all { d ->
            completedLessons.any { it.disciplineId == d.name }
        }

        return listOf(
            AcademicBadge(
                id = "ilk_ders",
                title = "İlk Ders",
                description = "İlk dersinizi okuyup görevlerini tamamladınız.",
                criteriaLabel = "1 ders tamamla",
                isEarned = completedCount >= 1
            ),
            AcademicBadge(
                id = "ilk_hafta",
                title = "İlk Hafta",
                description = "En az 5 günlük kesintisiz öğrenme serisine ulaştınız.",
                criteriaLabel = "5 gün seri",
                isEarned = profile.longestStreakDays >= 5L
            ),
            AcademicBadge(
                id = "ilk_ay",
                title = "İlk Ay",
                description = "Ay 1 — Düşünmeyi Öğrenmek modülünü tamamladınız.",
                criteriaLabel = "Ay 1 tüm dersler",
                isEarned = month1Completed
            ),
            AcademicBadge(
                id = "felsefe_okuryazari",
                title = "Felsefe Okuryazarı",
                description = "Felsefe disiplinindeki tüm temel dersleri başarıyla tamamladınız.",
                criteriaLabel = "Felsefe tam",
                isEarned = disciplineCompleted(DisciplineId.FELSEFE)
            ),
            AcademicBadge(
                id = "bilimsel_dusunur",
                title = "Bilimsel Düşünür",
                description = "Bilim ve Bilimsel Düşünce derslerini %70+ yeterlilikle geçtiniz.",
                criteriaLabel = "Bilim tam & sınav",
                isEarned = disciplineCompleted(DisciplineId.BILIM) &&
                    examResults.any { it.disciplineId == DisciplineId.BILIM.name && it.totalScore >= 70L }
            ),
            AcademicBadge(
                id = "tarih_arastirmacisi",
                title = "Tarih Araştırmacısı",
                description = "Tarih ve Medeniyet derslerinin tamamını bitirdiniz.",
                criteriaLabel = "Tarih tam",
                isEarned = disciplineCompleted(DisciplineId.TARIH)
            ),
            AcademicBadge(
                id = "elestirel_okur",
                title = "Eleştirel Okur",
                description = "Edebiyat ve Sanat Okuryazarlığı derslerini tamamladınız.",
                criteriaLabel = "Edebiyat & Sanat tam",
                isEarned = disciplineCompleted(DisciplineId.EDEBIYAT_SANAT)
            ),
            AcademicBadge(
                id = "teknoloji_okuryazari",
                title = "Teknoloji Okuryazarı",
                description = "Teknoloji, Python ve Yapay Zekâ modüllerini tamamladınız.",
                criteriaLabel = "Teknoloji & YZ tam",
                isEarned = disciplineCompleted(DisciplineId.TEKNOLOJI_YZ)
            ),
            AcademicBadge(
                id = "sekiz_disiplin",
                title = "Sekiz Disiplin Tamamlayıcısı",
                description = "Sekiz temel disiplinin her birinde kalıcı öğrenme sağladınız.",
                criteriaLabel = "Tüm 8 disiplin",
                isEarned = all8Touched && completedCount >= allLessons.size
            )
        )
    }
}
