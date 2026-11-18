package com.example.data.curriculum

import com.example.data.model.*

val months4To6Lessons: List<Lesson> = listOf(
    // ======================== AY 4 — İNSANLIK TARİHİNİ ANLAMAK (TARİH I) ========================
    Lesson(
        id = "m04_l01",
        monthNumber = 4,
        weekNumber = 1,
        discipline = DisciplineId.TARIH,
        title = "Tarih Bilimi, Kaynak Eleştirisi ve İlk Medeniyetler",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Tarihin yalnızca olay ezberi değil kaynak eleştirisi ve neden-sonuç bilimi olduğunu göstermek; tarih öncesi dönemlerden Mezopotamya ve Antik Mısır'a geçişi incelemek.",
        learningOutcomes = listOf(
            "Tanıma: Birincil ve ikincil kaynak türlerini, Neolitik Devrimi ve Mezopotamya–Mısır medeniyetlerini tanımlar.",
            "Anlama: İbn Haldun'un tarih metodolojisindeki tenkit ilkesini açıklar.",
            "Uygulama: Tarihsel bir metni 'Kim yazdı, kime yazdı, amacı neydi?' sorularıyla değerlendirir."
        ),
        prerequisites = listOf("Ay 1 Eleştirel okuma ve Ay 3 Kanıt değerlendirme."),
        keyConcepts = listOf(
            ConceptCard("Kaynak Eleştirisi", "Tarihsel belgenin gerçekliğini (dış tenkit) ve güvenilirliğini (iç tenkit) sorgulama yöntemi.", "Bir kralın zafer kitabesini okurken abartı payını başka kaynaklarla denetlemek."),
            ConceptCard("Neolitik Devrim", "Avcı-toplayıcılıktan tarıma, yerleşik yaşama ve artı ürüne geçiş.", "Göbeklitepe'den Çatalhöyük'e uzanan toplumsal örgütlenme dönüşümü."),
            ConceptCard("Asabiyet (İbn Haldun)", "Bir topluluğu bir arada tutan dayanışma ve ortak irade bağı.", "Devletlerin yükseliş ve gerileme döngülerini açıklayan sosyolojik güç.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Tarih Nasıl Yazılır? Herodot'tan İbn Haldun ve Halil İnalcık'a",
                "Herodot geçmişin hikâyesini kayıt altına alırken nedenleri aramaya başlamış, ancak tarih bilimine asıl sosyolojik yöntemi İbn Haldun kazandırmıştır. Mukaddime'de İbn Haldun, nakledilen her rivayetin 'umran' (toplumsal yapı ve doğa kanunları) ile tutarlılığının sınanması gerektiğini belirtir. Çağdaş Türk tarihçiliğinde Halil İnalcık arşiv belgeleri (tahrir defterleri) üzerinden ekonomik-toplumsal tarihi merkeze almıştır."
            ),
            ContentSection(
                "İlk Yerleşimler, Mezopotamya ve Antik Mısır",
                "Anadolu'daki Göbeklitepe ve Çatalhöyük, insanlığın yerleşik düzene ve sembolik kültüre geçişinin en erken tanıklarındandır. Dicle–Fırat havzasında Sümerlerin yazıyı (çivi yazısı), hukuk metinlerini ve şehir devletlerini kurmasıyla tarihî çağlar başlar. Nil'in düzenli taşkınları ise Mısır'da merkezi bürokrasi, takvim ve anıtsal mimariyi doğurmuştur."
            )
        ),
        historicalBackground = "MÖ 10.000 civarında Bereketli Hilal ve Anadolu'da başlayan tarımsal dönüşüm, MÖ 3200'lerde yazının icadıyla kurumsal devlet yapılarına evrildi.",
        realLifeExamples = listOf(
            "Tarihsel bir iddiayı sosyal medyada gördüğünde 'Hangi birincil arşiv veya arkeolojik bulguya dayanıyor?' sorusunu sormak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Herodot", "MÖ 484–425", "Sorgulayan tarihyazımının Batı'daki ilk temsilcisi."),
            ThinkerHighlight("İbn Haldun", "1332–1406", "Tarih felsefesi, umran bilimi ve kaynak tenkidinin kurucusu."),
            ThinkerHighlight("Halil İnalcık", "1916–2016", "Arşiv temelli Osmanlı sosyal ve ekonomik tarihçiliğinin duayeni.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İki Nehir Medeniyeti",
                "Mezopotamya", "Öngörülemez taşkınlar, bağımsız şehir devletleri, yazılı hukuk (Hammurabi).",
                "Antik Mısır", "Düzenli Nil taşkınları, merkezi firavun yönetimi, süreklilik."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Tarım ve Artı Ürün", "doğurur", "İş Bölümü ve Yazı"),
            ConceptRelation("Kaynak Tenkidi", "korur", "Tarihsel Nesnellik")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Savaş Raporunu Okumak",
            scenario = "Antik bir kralın tapınak duvarına kazıttığı yazıda 'Düşmanı tek günde yok ettim, ordumda tek kayıp olmadı' yazmaktadır.",
            analysisQuestions = listOf(
                "Bu metin hangi açıdan değerli, hangi açıdan taraflıdır?"
            ),
            keyTakeaway = "Taraflı bir yazıt bile dönemin ideolojisini ve dilini yansıttığı için kıymetlidir; ancak olgusal gerçekliği başka kaynaklarla çapraz kontrol edilmelidir."
        ),
        reflectionQuestions = listOf(
            "Bugün yaşadığımız olayların 500 yıl sonra hangi 'birincil kaynaklardan' okunacağını düşünüyorsunuz?"
        ),
        summary = "Tarih, geçmişin eleştirel süzgeçten geçirilmiş kanıtlarla inşasıdır. Yerleşik tarım ve yazı, Mezopotamya ve Mısır gibi ilk büyük uygarlıkları doğurmuştur.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Dış tenkit ile iç tenkit arasındaki fark nedir?", "Dış tenkit belgenin maddi gerçekliğini (tarih, mühür, dil), iç tenkit ise yazarın niyet ve doğruluğunu sınar.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m04_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Tarihi yalnızca olay aktarımı olmaktan çıkarıp toplumsal ve ekonomik nedenlere dayandıran 'Mukaddime' adlı eserin yazarı kimdir?",
                options = listOf("Herodot", "İbn Haldun", "Fernand Braudel", "Thukydides"),
                correctOptionIndex = 1,
                explanation = "İbn Haldun Mukaddime'de tarih metodolojisini ve toplumların yükseliş-çöküş yasalarını (umran ilmi) sistemleştirmiştir."
            ),
            QuizQuestion(
                id = "m04_l01_q2",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Yazının icadından önceki dönemlere ait insanlık tarihi bilimsel olarak incelenemez.",
                correctBool = false,
                explanation = "Yanlış. Arkeoloji, antropoloji ve radyokarbon tarihleme gibi yöntemlerle yazı öncesi dönemler de bilimsel olarak incelenir."
            )
        ),
        practiceTask = PracticeTask(
            title = "İki Kaynak Karşılaştırması",
            instructions = "Merak ettiğiniz bir tarihsel olay hakkında bir birincil kaynak alıntısı ile modern bir tarihçinin yorumunu yan yana not edin.",
            deliverableHint = "Olgu ile yorumu ayrı satırlara yazın."
        ),
        references = listOf(
            AcademicReference("İbn Haldun, Mukaddime (Haz. Süleyman Uludağ), Dergâh Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Tarih metodolojisi ve medeniyet teorisi."),
            AcademicReference("Halil İnalcık, Tarihçilerin Kutbu: Söyleşiler, İş Bankası Kültür Yayınları", ReferenceType.AKADEMIK_KITAP, "Kaynak eleştirisi ve arşivcilik.")
        )
    ),
    Lesson(
        id = "m04_l02",
        monthNumber = 4,
        weekNumber = 2,
        discipline = DisciplineId.TARIH,
        title = "Antik Yunan, Roma Medeniyeti ve Akdeniz Dünyası",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Antik Yunan polis kültürünün, Roma hukuku ve imparatorluk mimarisinin Akdeniz dünyasındaki kurumsal mirasını analiz etmek.",
        learningOutcomes = listOf(
            "Tanıma: Polis, yurttaşlık, Roma Cumhuriyeti ve Roma Hukuku kavramlarını tanımlar.",
            "Anlama: Antik Yunan'daki doğrudan demokrasi ile modern temsili demokrasi arasındaki farkı açıklar.",
            "Analiz ve Sentez: Fernand Braudel'in uzun süreli Akdeniz perspektifiyle siyasal-coğrafi yapıyı bağlar."
        ),
        prerequisites = listOf("Ders 10."),
        keyConcepts = listOf(
            ConceptCard("Polis (Şehir Devleti)", "Vatandaşların ortak kamusal alanı (agora) paylaştığı bağımsız Antik Yunan siyasal birimi.", "Atina'da siyasal katılım ile Sparta'daki askerî disiplin karşıtlığı."),
            ConceptCard("Roma Hukuku", "Kişi, mülkiyet ve sözleşme kavramlarını yazılı kurallara bağlayarak modern kıta hukukuna temel olan sistem.", "Suçun şahsiliği ve sözleşmeye bağlılık ilkeleri.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Antik Yunan: Şehir Devletleri, Demokrasi ve Sınırları",
                "Dağlık coğrafya Yunanistan'da merkezi bir imparatorluk yerine Atina ve Sparta gibi özerk şehir devletlerini (polis) doğurdu. Atina'da geliştirilen demokrasi yurttaşların mecliste doğrudan oy kullanmasına dayanıyordu; ancak kadınlar, yabancılar ve köleler bu hakkın dışındaydı. Bu ayrımı görmek tarihi romantize etmeden anlamamızı sağlar."
            ),
            ContentSection(
                "Roma: Cumhuriyetten İmparatorluğa ve Hukuk Mirası",
                "Roma gücü yalnızca lejyonlarından değil, yol ağlarından, su kemerlerinden, vatandaşlık entegrasyonundan ve On İki Levha Kanunları'ndan Jüstinyen Derlemeleri'ne uzanan Roma Hukuku'ndan geliyordu. Fernand Braudel ve William H. McNeill gibi tarihçiler, Akdeniz'in deniz ticareti ve kültürel etkileşim alanı olarak bütünlüğüne dikkat çeker."
            )
        ),
        historicalBackground = "MÖ 5. yüzyıl Klasik Atina'sından MÖ 1. yüzyılda Roma İmparatorluğu'na ve MS 476 Batı Roma'nın yıkılışına uzanan dönem, Avrupa ve Akdeniz uygarlığının temel taşlarını döşedi.",
        realLifeExamples = listOf(
            "Bugünkü borçlar ve medeni hukukumuzdaki pek çok kavramın (ör. iyiniyet, ahde vefa) Roma Hukuku kökenli olması."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Fernand Braudel", "1902–1985", "Annales ekolü öncüsü; tarihi kısa olaylar yerine uzun coğrafi ve ekonomik dalgalarla (longue durée) okuyan tarihçi."),
            ThinkerHighlight("William H. McNeill", "1917–2016", "Medeniyetler arası etkileşimi merkeze alan küresel tarih yaklaşımının öncüsü.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Siyasal Model Farkı",
                "Atina Demokrasisi", "Küçük ölçekli şehir devletinde doğrudan halk meclisi oylaması.",
                "Roma Cumhuriyeti", "Senato, konsüller ve halk tribünleri arasında kurumsal denge-denetim."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Coğrafya", "biçimlendirir", "Şehir Devletleri"),
            ConceptRelation("Roma Hukuku", "etkiler", "Modern Hukuk Sistemleri")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Kurumsal Çöküş ve Toprak Eşitsizliği",
            scenario = "Roma'nın fetihleri büyüdükçe küçük çiftçiler topraklarını kaybedip kentlere yığılırken büyük senatörler dev arazilere sahip olur.",
            analysisQuestions = listOf(
                "Ekonomik eşitsizliğin Roma Cumhuriyeti'ndeki siyasal krize etkisi ne olmuştur?"
            ),
            keyTakeaway = "Kurumsal denge bozulup orta sınıf eridiğinde cumhuriyetler popülist çatışmalara ve otokratik dönüşümlere açık hale gelir."
        ),
        reflectionQuestions = listOf(
            "Sizce bir medeniyeti daha uzun ömürlü kılan askerî güç müdür yoksa hukuk ve kurumlar mıdır?"
        ),
        summary = "Antik Yunan felsefe ve siyasal katılım kavramlarını tartışmaya açarken, Roma hukuk ve imparatorluk yönetimiyle kalıcı kurumsal yapılar bıraktı.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Fernand Braudel'in 'longue durée' (uzun süre) kavramı ne demektir?", "Siyasi olayların altındaki yavaş değişen coğrafya, iklim, demografi ve ekonomik yapıları inceleme yaklaşımı.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m04_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Tarih yazımında günlük siyasi olaylardan çok uzun vadeli coğrafi, ekonomik ve toplumsal yapıları incelemeyi savunan tarihçi kimdir?",
                options = listOf("Fernand Braudel", "Herodot", "Platon", "Galileo"),
                correctOptionIndex = 0,
                explanation = "Annales okulunun öncüsü Fernand Braudel 'Akdeniz ve Akdeniz Dünyası' eserinde uzun süreli (longue durée) tarihi kurgulamıştır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Uzun Süre Analizi",
            instructions = "Yaşadığınız şehrin tarihini son 5 yıllık olaylarla değil, son 100 yıllık coğrafya ve ticaret yollarıyla açıklayan 1 paragraf yazın.",
            deliverableHint = "Braudel perspektifini kullanın."
        ),
        references = listOf(
            AcademicReference("Fernand Braudel, Akdeniz ve Akdeniz Dünyası, Eren / Doğu Batı Yayınları", ReferenceType.AKADEMIK_KITAP, "Uzun süreli tarih yaklaşımı.")
        )
    ),
    Lesson(
        id = "m04_l03",
        monthNumber = 4,
        weekNumber = 4,
        discipline = DisciplineId.TARIH,
        title = "Anadolu ve İpek Yolu'nda Kültürlerin Karşılaşması",
        durationMinutes = 15,
        lengthCategory = LessonLength.STANDART,
        purpose = "Anadolu ve İpek Yolu'nu yalnızca savaş alanı değil ticaret, bilim ve kültür aktarımı koridoru olarak yorumlamak.",
        learningOutcomes = listOf(
            "Anlama: İpek Yolu'nun ticari ve entelektüel etkileşim rolünü açıklar.",
            "Analiz ve Sentez: Medeniyetlerin birbirinden izole değil alışveriş içinde geliştiğini değerlendirir."
        ),
        prerequisites = listOf("Ders 10 ve 11."),
        keyConcepts = listOf(
            ConceptCard("Kültürel Difüzyon", "Teknolojilerin, fikirlerin, tarım ürünlerinin ve sanat biçimlerinin ticaret ve göç yollarıyla yayılması.", "Kağıt ve pusulanın Çin'den İslam dünyasına, oradan Avrupa'ya ulaşması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Bağlantılı Tarih (Connected History)",
                "William H. McNeill'in vurguladığı gibi hiçbir medeniyet kendi başına bir ada değildir. Hititlerden Lidyalılara, Perslerden Helenistik krallıklara kadar Anadolu ve İpek Yolu, paranın, alfabelerin, tıbbın ve felsefenin karşılaştığı dev bir laboratuvar olmuştur."
            )
        ),
        historicalBackground = "MÖ 2. binyıldan MS 15. yüzyıla dek kara ve deniz ipek yolları Avrasya medeniyetlerini birbirine bağladı.",
        realLifeExamples = listOf(
            "Kullandığımız sıfır rakamı ve ondalık sistemin Hindistan'dan Harizmi aracılığıyla İslam ve Avrupa matematiğine geçişi."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("William H. McNeill", "1917–2016", "Dünya tarihini medeniyetler arası etkileşim ağı olarak yorumlayan tarihçi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Tarih Okuma Biçimi",
                "İzole Ulusal Tarih", "Her kültürü kendi içine kapalı ve bağımsız varsayar.",
                "Küresel Etkileşim Tarihi", "Bilgi, ticaret ve salgınların sınır aşan etkilerini gösterir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Ticaret Yolları", "taşır", "Bilim ve Teknoloji")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Semerkant'ta Kağıt Üretimi",
            scenario = "8. yüzyılda Semerkant'ta kurulan kağıt atölyeleri parşömen ve papirüse kıyasla çok daha ucuz ve dayanıklı yazı malzemesi üretir.",
            analysisQuestions = listOf(
                "Ucuz kağıt üretimi Bağdat'ta kitap kültürünü ve bilimsel tercüme hareketini nasıl etkilemiştir?"
            ),
            keyTakeaway = "Maddi bir üretim teknolojisi (kağıt) entelektüel patlamanın doğrudan altyapısını oluşturabilir."
        ),
        reflectionQuestions = listOf(
            "Bugün internetin bilgi dolaşımındaki rolü geçmişteki İpek Yolu ve kağıt devrimiyle nasıl benzerlik gösterir?"
        ),
        summary = "Anadolu ve Avrasya ticaret yolları, yalnızca ipek ve baharat değil, kâğıt, rakamlar, tıp ve felsefe taşımıştır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Kağıt üretiminin yaygınlaşması İslam bilim dünyasında neyi tetikledi?", "Ucuz kitap üretimi, kütüphanelerin çoğalması ve Beytülhikme çeviri hareketi.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m04_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.ANLAMA,
                prompt = "Kâğıt teknolojisinin Semerkant üzerinden İslam dünyasında yaygınlaşmasının en belirgin sonucu aşağıdakilerden hangisidir?",
                options = listOf("Yazının unutulması", "Kitap üretiminin ucuzlaması ve ilim merkezlerinin büyümesi", "Ticaret yollarının kapanması", "Denizciliğin durması"),
                correctOptionIndex = 1,
                explanation = "Parşömene göre çok daha ekonomik olan kâğıt, kitapların ve kütüphanelerin hızla çoğalmasını sağladı."
            )
        ),
        practiceTask = PracticeTask(
            title = "Etkileşim İzi Sürme",
            instructions = "Türkçede veya günlük mutfağınızda kullandığınız 3 kavram/ürünün başka bir coğrafyadan nasıl geldiğini kısaca araştırıp not edin.",
            deliverableHint = "Örneğin 'algoritma' (Harizmi), 'pusula', 'kahve'."
        ),
        references = listOf(
            AcademicReference("William H. McNeill, Dünya Tarihi, İmge Kitabevi", ReferenceType.AKADEMIK_KITAP, "Medeniyetler arası etkileşim tarihi.")
        )
    ),

    // ======================== AY 5 — MEDENİYETLER VE TARİHSEL DÖNÜŞÜM (TARİH II) ========================
    Lesson(
        id = "m05_l01",
        monthNumber = 5,
        weekNumber = 1,
        discipline = DisciplineId.TARIH,
        title = "Orta Çağ, İslam Medeniyeti ve Bilimler Tarihi (Fuat Sezgin Perspektifi)",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "'Karanlık Orta Çağ' kalıbını sorgulamak; İslam medeniyetinin 8.–14. yüzyıllardaki bilimsel, felsefi ve kurumsal yaratıcılığını Fuat Sezgin'in tespitleriyle anlamak.",
        learningOutcomes = listOf(
            "Tanıma: Beytülhikme, rasathaneler, hastaneler (darüşşifalar) ve başlıca İslam âlimlerini tanımlar.",
            "Anlama: İslam biliminin yalnızca antik metinleri korumadığını, özgün deney ve yöntem eklediğini açıklar.",
            "Analiz ve Sentez: Bilim ve teknolojinin medeniyetler arası sürekliliğini değerlendirir."
        ),
        prerequisites = listOf("Ay 4 Tarih I ve Ay 3 Bilimsel Yöntem."),
        keyConcepts = listOf(
            ConceptCard("Beytülhikme", "Bağdat'ta kurulan büyük çeviri, araştırma ve gözlem akademisi.", "Antik Yunan, Hint ve Pers birikiminin Arapçaya çevrilip eleştirel olarak geliştirilmesi."),
            ConceptCard("Deneysel Optikte İbnü'l-Heysem", "Görmenin gözden çıkan ışınlarla değil nesneden gelen ışıkla gerçekleştiğini deneysel olarak kanıtlayan âlim.", "Modern karanlık oda ve bilimsel deney yönteminin öncüsü.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Orta Çağ'ın Çok Merkezli Gerçekliği",
                "Batı Avrupa erken Orta Çağ'da feodal parçalanma ve kentsel daralma yaşarken, aynı dönemde Doğu'da Bağdat, Kurtuba, Buhara, Semerkant ve Kahire dünyanın en canlı bilim ve ticaret merkezleriydi. Bu yüzden 'Karanlık Çağ' nitelemesi küresel değil yalnızca bölgesel bir dönemi ifade eder."
            ),
            ContentSection(
                "Fuat Sezgin ve Bilim Tarihi Sürekliliği",
                "Prof. Dr. Fuat Sezgin, İslam Bilim Tarihi Araştırmaları'nda Müslüman âlimlerin sadece aktarıcı değil yaratıcı olduğunu gösterir: Harizmi cebir ve algoritmayı, Biruni dünyanın yarıçapını yüksek hassasiyetle ölçmeyi, İbnü'l-Heysem deneysel optiği, El-Cezeri ise sibernetik ve otomatik makineleri geliştirdi. Endülüs ve Sicilya üzerinden bu birikim Avrupa'nın 12. yüzyıl uyanışını ve Rönesans'ı besledi."
            )
        ),
        historicalBackground = "8. yüzyıldan 14. yüzyıla uzanan İslam Altın Çağı, vakıf kurumlarıyla desteklenen medreseler, darüşşifalar ve rasathaneler ağı üzerine kuruluydu.",
        realLifeExamples = listOf(
            "Kullandığımız 'cebir' (el-cebr), 'algoritma' (Harizmi), 'kimya' ve 'sıfır' kelimelerinin bilimsel kökeni."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Fuat Sezgin", "1924–2018", "İslam Bilim Tarihi'ni binlerce yazma eser ve alet rekonstrüksiyonuyla belgeleyen büyük bilim tarihçisi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Bilim Tarihi Anlatısı",
                "Eski Avrupa-Merkezci Anlatı", "Antik Yunan'dan doğrudan Rönesans'a atlayarak aradaki 800 yılı boşluk sanırdı.",
                "Fuat Sezgin & Küresel Bilim Tarihi", "Antik birikim -> İslam dünyası (deneysel sıçrama) -> Rönesans kesintisiz zincirini belgeler."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Vakıf Kurumları", "finanse eder", "Medrese ve Darüşşifa"),
            ConceptRelation("İslam Bilim Çağı", "besler", "Avrupa Rönesansı")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Biruni'nin Dağdan Dünya Yarıçapı Ölçümü",
            scenario = "11. yüzyılda Biruni, gemiyle dünyayı dolaşmadan yalnızca bir dağın yüksekliğini ve ufuk açısını ölçüp trigonometri uygulayarak Dünya'nın yarıçapını %1'e yakın doğrulukla hesaplar.",
            analysisQuestions = listOf(
                "Bu örnek matematik ile gözlemin nasıl birleştiğini gösterir?"
            ),
            keyTakeaway = "Zihinsel yaratıcılık ve sağlam yöntem, sınırlı araçlarla bile olağanüstü bilimsel sonuçlar üretebilir."
        ),
        reflectionQuestions = listOf(
            "Bir toplumda bilimsel merakın kurumsallaşması için hangi ekonomik ve özgürlük koşulları gerekir?"
        ),
        summary = "Orta Çağ dünya çapında karanlık değildi; İslam medeniyeti tıp, optik, matematik ve astronomide deneysel yöntemi ilerleterek küresel bilim zincirinin ana halkasını kurdu.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Fuat Sezgin'in bilim tarihindeki temel tezi nedir?", "İslam biliminin yalnızca koruyucu değil, deney ve gözlemle yeni bilimler kuran özgün ve hayati bir halka olduğu.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m05_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Deneysel optiğin öncüsü sayılan ve 'Kitabü'l-Menazır' adlı eseriyle Avrupa'yı da derinden etkileyen âlim kimdir?",
                options = listOf("İbnü'l-Heysem", "Herodot", "Sokrates", "Adam Smith"),
                correctOptionIndex = 0,
                explanation = "İbnü'l-Heysem kontrollü karanlık oda deneyleriyle görme kuramını temellendirmiştir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Bilim Tarihi Zinciri",
            instructions = "Gülhane'deki İslam Bilim ve Teknoloji Tarihi Müzesi koleksiyonundan bir bilim insanını (Cezeri, Biruni veya Takiyüddin) kısaca araştırıp 3 cümle özet çıkarın.",
            deliverableHint = "Notlarım bölümüne ekleyin."
        ),
        references = listOf(
            AcademicReference("Fuat Sezgin, İslam'da Bilim ve Teknik (5 Cilt), İBTAV Yayınları", ReferenceType.AKADEMIK_KITAP, "İslam bilim tarihinin anıtsal kaynağı.")
        )
    ),
    Lesson(
        id = "m05_l02",
        monthNumber = 5,
        weekNumber = 2,
        discipline = DisciplineId.TARIH,
        title = "Türk Tarihi, Selçuklu ve Osmanlı Medeniyet Kurumları",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Orta Asya'dan Anadolu ve Balkanlara Türk tarihinin sürekliliğini; Selçuklu ve Osmanlı'nın hukuk, vakıf, tımar ve şehirleşme kurumlarını incelemek.",
        learningOutcomes = listOf(
            "Tanıma: Tımar sistemi, vakıf, ahilik, millet sistemi ve divan kurumlarını tanımlar.",
            "Anlama: Selçuklu kervansaray ve medrese ağının Anadolu'yu nasıl bir kültür-ticaret havzasına dönüştürdüğünü açıklar.",
            "Analiz ve Sentez: Halil İnalcık'ın yaklaşımlarıyla Osmanlı klasik düzeninin çözülme ve modernleşme dinamiklerini değerlendirir."
        ),
        prerequisites = listOf("Ders 13."),
        keyConcepts = listOf(
            ConceptCard("Ahilik Teşkilatı", "Anadolu'da mesleki ahlakı, kalite denetimini ve esnaf dayanışmasını birleştiren sivil kurum.", "Emeği ahlak ve çırak-kalfa-usta eğitimiyle birleştirmek."),
            ConceptCard("Tımar Sistemi", "Vergi gelirinin nakit yerine askerî ve yerel idari hizmet karşılığında yönetilmesi.", "Taşrada üretim devamlılığı ve merkezi nakit yükünü azaltma."),
            ConceptCard("Vakıf Medeniyeti", "Bireylerin mülklerini eğitim, sağlık, su ve imaret gibi kamusal hizmetlere kalıcı biçimde adaması.", "Devlet bütçesinden bağımsız sürdürülebilir sosyal destek.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Orta Asya'dan Anadolu'ya: Selçuklu Sentezi",
                "Büyük Selçuklular ve Anadolu Selçukluları; Türk devlet geleneği, İslam hukuku ve İran-Anadolu şehir kültürünü bir araya getirdi. Kervansaraylar ticareti sigortalarken, şifahaneler ve medreseler Anadolu kentlerinin mimari ve sosyal çekirdeğini oluşturdu."
            ),
            ContentSection(
                "Osmanlı Klasik Düzeni ve Dönüşüm",
                "Osmanlı İmparatorluğu'nun 600 yıllık varlığı pragmatik yönetim, hukuk (örfi ve şer'i hukuk dengesi) ve farklı dini topluluklara özerk alan tanıyan yapıya dayanıyordu. Ancak 16. yüzyıl sonundan itibaren Coğrafi Keşifler'in ticaret yollarını kaydırması, enflasyon ve askeri teknoloji değişimi klasik tımar düzenini zorladı; 19. yüzyıl Tanzimat'tan Cumhuriyet'e uzanan modernleşme çabalarını doğurdu."
            )
        ),
        historicalBackground = "1071 Malazgirt'ten 1453 İstanbul'un fethine, klasik çağdan Tanzimat ve 1923 Türkiye Cumhuriyeti'nin kuruluşuna uzanan tarihsel çizgi.",
        realLifeExamples = listOf(
            "Anadolu'daki tarihi han, köprü ve külliyelerin hâlâ kent meydanlarının merkezini oluşturması."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Halil İnalcık", "1916–2016", "Osmanlı Devletinin Ekonomik ve Sosyal Tarihi eserleriyle kurumsal analizi yerleştiren tarihçi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İdari Yapı",
                "Selçuklu", "Ticaret yolları ve atabeylik/emirlik esnekliğiyle Anadolu'nun Türkleşmesi ve imarı.",
                "Osmanlı Klasik Çağ", "Merkeziyetçi bürokrasi, kanunnameler ve kurumsallaşmış taşra yönetimi."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Coğrafi Keşifler", "etkiler", "Osmanlı Maliyesi"),
            ConceptRelation("Ahilik ve Vakıf", "güçlendirir", "Toplumsal Dayanışma")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Matbaa ve Teknolojik Adaptasyon",
            scenario = "Avrupa'da 1450'de başlayan matbaa, Osmanlı'da Müslüman tebaa için ancak 1727'de İbrahim Müteferrika ile kurumsal hale gelir (gayrimüslim matbaaları ise daha erken mevcuttur).",
            analysisQuestions = listOf(
                "Bu gecikmede hattatların geçim kaygısı ve yazma eser ekonomisi nasıl bir rol oynamıştır?"
            ),
            keyTakeaway = "Teknoloji transferinin hızı yalnızca teknik bilgiye değil, toplumsal çıkar gruplarına ve kurumsal teşviklere bağlıdır."
        ),
        reflectionQuestions = listOf(
            "Tarihsel kurumlarımızdan (ör. vakıf veya ahilik) günümüz sivil toplumu için hangi ilke yeniden yorumlanabilir?"
        ),
        summary = "Selçuklu ve Osmanlı medeniyetleri güçlü şehir, vakıf ve hukuk kurumları kurmuş; küresel ekonomik ve teknolojik dönüşümler karşısında köklü modernleşme süreçlerine girmiştir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Osmanlı klasik dönemini sarsan başlıca küresel etkenler nelerdi?", "Yeni ticaret yolları, Avrupa'dan gelen gümüş kaynaklı fiyat devrimi ve ateşli silah askerî devrimi.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m05_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Anadolu şehirlerinde esnaf ahlakını, kalite standartlarını ve mesleki eğitimi düzenleyen tarihî sivil teşkilat hangisidir?",
                options = listOf("Lonca dışı serbest piyasa", "Ahilik Teşkilatı", "Tımar Divanı", "Yeniçeri Ocağı"),
                correctOptionIndex = 1,
                explanation = "Ahi Evran öncülüğünde gelişen Ahilik, hem ekonomik hem ahlaki dayanışma kurumu idi."
            )
        ),
        practiceTask = PracticeTask(
            title = "Kurumsal Süreklilik Analizi",
            instructions = "Cumhuriyet'in kuruluşuna giden modernleşme hamlelerinden (eğitim, hukuk, ekonomi) birini seçip Osmanlı son dönemindeki köklerini 4 cümlede özetleyin.",
            deliverableHint = "Tanzimat ve Meşrutiyet dönemleriyle bağ kurun."
        ),
        references = listOf(
            AcademicReference("Halil İnalcık, Osmanlı İmparatorluğu: Klasik Çağ (1300–1600), YKY", ReferenceType.AKADEMIK_KITAP, "Klasik dönem kurumlarının temel analizi.")
        )
    ),
    Lesson(
        id = "m05_l03",
        monthNumber = 5,
        weekNumber = 4,
        discipline = DisciplineId.TARIH,
        title = "Rönesans, Aydınlanma, Sanayi Devrimi ve Çağdaş Dünya",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Modern dünyayı şekillendiren büyük kırılmaları (Rönesans, Reform, Coğrafi Keşifler, Aydınlanma, Fransız ve Sanayi Devrimleri, 20. ve 21. yüzyıl) neden-sonuç zinciriyle kavramak.",
        learningOutcomes = listOf(
            "Tanıma: Rönesans, Reform, Aydınlanma, Fransız Devrimi ve Sanayi Devrimi'nin temel dönemeçlerini tanımlar.",
            "Anlama: Üretim biçimlerindeki değişimin (Sanayi Devrimi) siyaseti ve gündelik zaman algısını nasıl dönüştürdüğünü açıklar.",
            "Analiz ve Sentez: 20. yüzyıl dünya savaşları, Soğuk Savaş ve küreselleşmenin bugünkü dünyaya etkilerini yorumlar."
        ),
        prerequisites = listOf("Ders 13 ve 14."),
        keyConcepts = listOf(
            ConceptCard("Aydınlanma", "Aklın, bireysel özgürlüklerin, bilimsel ilerlemenin ve hukuk devletinin temel ölçüt haline gelmesi.", "Kant'ın 'Kendi aklını kullanma cesareti göster' (Sapere Aude) çağrısı."),
            ConceptCard("Sanayi Devrimi", "Kas gücünden buhar ve makine enerjisine geçişle üretimin, kentleşmenin ve toplumsal sınıfların kökten dönüşümü.", "Ev atölyesinden saatle yönetilen fabrikalara geçiş.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Rönesans, Reform ve Coğrafi Keşifler",
                "15.–16. yüzyıllarda matbaa, hümanizm ve Antikite ile İslam bilim metinlerinin yeniden okunması Rönesans'ı tetikledi. Reform kilise tekelini kırarken, Coğrafi Keşifler Atlantik merkezli yeni bir küresel ekonomi (ve sömürgecilik dönemini) başlattı."
            ),
            ContentSection(
                "Aydınlanma, Fransız Devrimi ve Sanayi Çağı",
                "1789 Fransız Devrimi 'tebaa'dan 'hak sahibi yurttaş' kavramına geçişi ve milliyetçilik çağını başlattı. Aynı yıllarda İngiltere'de başlayan Sanayi Devrimi ise insanlık tarihinin en hızlı ekonomik verimlilik sıçramasını sağlarken işçi hakları, çevre kirliliği ve küresel güç dengesizliği gibi yeni sorunlar üretti."
            ),
            ContentSection(
                "20. Yüzyıldan Çağdaş Dünyaya",
                "İki Dünya Savaşı, dekolonizasyon (sömürgelerin bağımsızlığı), Soğuk Savaş ve ardından gelen dijital devrim bugünkü çok kutuplu, birbirine bağımlı dünyayı oluşturdu."
            )
        ),
        historicalBackground = "1450–2026 arasındaki beş yüz yıl, insanlık tarihinde önceki beş bin yıldan daha hızlı kurumsal ve teknolojik değişim üretmiştir.",
        realLifeExamples = listOf(
            "8 saatlik iş günü ve hafta sonu tatili kavramının Sanayi Devrimi sonrası işçi mücadeleleri ve sendikal süreçlerle doğmuş olması."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Halil İnalcık & Fernand Braudel", "Modern Tarih Okuması", "Küresel ekonomik kaymaların yerel devletleri nasıl dönüştürdüğünü açıklayan yaklaşımlar.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İki Büyük Devrim",
                "Fransız Devrimi (Siyasal)", "Egemenliğin kaynağını monarşiden halka ve anayasal haklara taşıdı.",
                "Sanayi Devrimi (Ekonomik)", "Enerji, fabrika üretimi, ulaşım ve kentleşmeyi kökten değiştirdi."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Aydınlanma", "besler", "Anayasal Yurttaşlık"),
            ConceptRelation("Sanayi Devrimi", "dönüştürür", "Şehirleşme ve Ekonomi")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Saat ve Fabrika Disiplini",
            scenario = "Tarım toplumunda insanlar güneş doğuşu ve mevsimlere göre çalışırken, 19. yüzyıl fabrikalarında vardiya düdüğü ve dakika hesabı başlar.",
            analysisQuestions = listOf(
                "Üretim teknolojisindeki değişim insanın zaman ve aile yaşamını nasıl yeniden biçimlendirmiştir?"
            ),
            keyTakeaway = "Teknoloji yalnızca araç değil, toplumsal yaşamın ritmini belirleyen görünmez bir mimardır."
        ),
        reflectionQuestions = listOf(
            "Bugün yaşadığımız Yapay Zekâ dönüşümünü Sanayi Devrimi ile kıyaslarsanız en büyük benzerlik ve fark nedir?"
        ),
        summary = "Rönesans'tan Sanayi Devrimi'ne ve 20. yüzyıla uzanan dönüşümler; insan hakları, ulus-devlet, sanayi kapitalizmi ve küresel karşılıklı bağımlılığı doğurmuştur.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Fransız Devrimi siyaset tarihinde hangi temel kavramı merkeze yerleştirdi?", "Tebaa yerine hak ve sorumluluklara sahip 'yurttaşlık' ve ulusal egemenlik.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m05_l03_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Coğrafi Keşifler, Aydınlanma ve Sanayi Devrimi arasındaki neden-sonuç zincirini 4–6 cümlede sentezleyin.",
                explanation = "Keşiflerle biriken sermaye ve ticaret ağları bilimsel-kurumsal yatırımları besledi; Aydınlanma eleştirel ve bilimsel ortamı açtı; bu birikim Sanayi Devrimi'nin teknolojik sıçramasını mümkün kıldı.",
                rubricHint = "Üç dönemi birbirine mantıksal neden-sonuç halkalarıyla bağlamalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Tarihsel Sentez Zaman Çizelgesi",
            instructions = "Son 500 yılın 5 dönüm noktasını (Rönesans, Keşifler, Aydınlanma, Sanayi, Dijital Çağ) seçip her birinin insan yaşamına getirdiği tek en büyük değişikliği yazın.",
            deliverableHint = "Kendi Düşüncelerim sekmesinde saklayın."
        ),
        references = listOf(
            AcademicReference("Eric Hobsbawm, Devrim Çağı (1789–1848), Dost Kitabevi", ReferenceType.AKADEMIK_KITAP, "Fransız ve Sanayi devrimlerinin ikili analizi.")
        )
    ),

    // ======================== AY 6 — EKONOMİK DÜNYAYI ANLAMAK (EKONOMİ) ========================
    Lesson(
        id = "m06_l01",
        monthNumber = 6,
        weekNumber = 1,
        discipline = DisciplineId.EKONOMI,
        title = "Kıtlık, Fırsat Maliyeti, Arz-Talep ve Piyasa Dengesi",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Ekonomi biliminin temel sorusu olan kıt kaynaklarla sınırsız ihtiyaçlar arasındaki seçimleri, fırsat maliyetini ve arz-talep fiyat oluşumunu kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: Kıtlık, fırsat maliyeti, marjinal fayda, arz, talep ve piyasa dengesini tanımlar.",
            "Anlama: Fiyatların piyasadaki kıtlık ve tercih bilgisini nasıl ilettiğini açıklar.",
            "Uygulama: Kendi zaman ve para kararlarında fırsat maliyetini hesaplar.",
            "Analiz ve Sentez: Adam Smith'in iş bölümü ile Friedrich Hayek'in dağılmış bilgi tezini karşılaştırır."
        ),
        prerequisites = listOf("Ay 2 Karar Verme ve Ay 3 Matematiksel Düşünme."),
        keyConcepts = listOf(
            ConceptCard("Fırsat Maliyeti", "Bir seçimi yaptığımızda vazgeçtiğimiz en iyi ikinci alternatifin değeri.", "Üniversitede 4 yıl okumanın maliyeti yalnızca harç değil, 4 yılda kazanılabilecek maaştır."),
            ConceptCard("Arz ve Talep Dengesi", "Üreticilerin satmak istediği miktar ile tüketicilerin almak istediği miktarın kesiştiği fiyat noktası.", "Kötü hasat yılında arz düşünce ürün fiyatının yükselmesi."),
            ConceptCard("Karşılaştırmalı Üstünlük (Ricardo)", "Bir taraf her şeyde daha verimli olsa bile en az görece maliyetle ürettiği alanda uzmanlaşmanın iki tarafı da kazandırması.", "İyi bir avukatın daktilo işini sekretere bırakması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Mikroekonominin Çekirdeği: Seçim ve Teşvikler",
                "Ekonomi yalnızca para bilimi değil, kıtlık altında insan seçimlerinin bilimidir. Zamanımız, enerjimiz ve gelirimiz sınırlıdır. Bu nedenle her 'evet', başka bir şeye 'hayır' demektir (fırsat maliyeti). Adam Smith'in Ulusların Zenginliği'nde gösterdiği üzere iş bölümü ve uzmanlaşma verimliliği katlar."
            ),
            ContentSection(
                "Fiyat Mekanizması ve Piyasa Dengesi",
                "Talep yasası fiyat arttıkça talep edilen miktarın düşmesini, arz yasası ise fiyat arttıkça üretim isteğinin artmasını ifade eder. Friedrich Hayek fiyatların birer 'bilgi sinyali' olduğunu vurgular: Hiçbir merkezî planlamacı milyonlarca tüketicinin anlık tercihini fiyat sistemi kadar hızlı toplayamaz; ancak tekeller, dışsallıklar ve bilgi asimetrisi piyasa aksaklıkları doğurabilir."
            )
        ),
        historicalBackground = "1776'da Adam Smith ve ardından David Ricardo ile klasik iktisat kuruldu; 19. yüzyıl sonu marjinal devrimle modern mikroekonomi şekillendi.",
        realLifeExamples = listOf(
            "Bir konsere ücretsiz bilet kazansanız bile o akşamı çalışarak veya dinlenerek geçirmemek onun fırsat maliyetidir."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Adam Smith", "1723–1790", "Modern iktisadın kurucusu; iş bölümü ve piyasa koordinasyonu."),
            ThinkerHighlight("David Ricardo", "1772–1823", "Karşılaştırmalı üstünlük kuramı."),
            ThinkerHighlight("Friedrich Hayek", "1899–1992", "Piyasalarda dağınık bilginin fiyatlar yoluyla koordinasyonu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Üretim ve Değer Yaklaşımı",
                "Mikroekonomi", "Birey, hane halkı ve firmaların fiyat ve teşvik kararlarını inceler.",
                "Makroekonomi", "Enflasyon, işsizlik, GSYH ve para politikası gibi toplam büyüklükleri inceler."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Kıtlık", "zorunlu kılar", "Seçim ve Fırsat Maliyeti"),
            ConceptRelation("Arz + Talep", "oluşturur", "Denge Fiyatı")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Tavan Fiyat Uygulaması",
            scenario = "Kira fiyatları hızla yükselince belediye kiraları piyasa dengesinin çok altında sabitler.",
            analysisQuestions = listOf(
                "Kısa vadede mevcut kiracılar korunsa da orta vadede yeni konut arzı ve konut kalitesi nasıl etkilenir?"
            ),
            keyTakeaway = "Fiyat kontrolleri kıtlığın nedenini çözmeden fiyat sinyalini bastırdığında konut kıtlığı (arz daralması) büyüyebilir; çözüm arzı artırıcı politikalarla desteklenmelidir."
        ),
        reflectionQuestions = listOf(
            "Bu hafta yaptığınız en pahalı harcamanın veya zaman seçiminin fırsat maliyeti neydi?"
        ),
        summary = "Ekonomi; kıtlık, fırsat maliyeti, marjinal karar ve arz-talep dengesi üzerinden hem bireysel seçimleri hem toplumsal üretimi açıklar.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Fırsat maliyeti nedir?", "Bir tercih uğruna vazgeçilen bir sonraki en değerli alternatifin getirisidir.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m06_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "Günde 2 saatini ek iş yaparak 600 TL kazanabilecek bir öğrenci o 2 saati ücretsiz bir konsere giderek geçirirse, konserin fırsat maliyeti nedir?",
                options = listOf("0 TL çünkü bilet ücretsiz", "600 TL ve o sürede kazanabileceği alternatif fayda", "Yalnızca ulaşım masrafı", "Sınırsız maliyet"),
                correctOptionIndex = 1,
                explanation = "Bilet ücretsiz olsa bile vazgeçilen 600 TL'lik alternatif kazanç fırsat maliyetidir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Zaman Fırsat Maliyeti Denetimi",
            instructions = "Son bir haftada amaçsız sosyal medya veya ekran tüketimine ayırdığınız süreyi hesaplayın ve bu sürenin fırsat maliyetini (kaç sayfa kitap veya kaç ders) yazın.",
            deliverableHint = "Notlarım bölümünde 'Kişisel Ekonomi' başlığına kaydedin."
        ),
        references = listOf(
            AcademicReference("Adam Smith, Ulusların Zenginliği, İş Bankası Kültür Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Klasik iktisadın kurucu metni."),
            AcademicReference("Gregory Mankiw, Ekonominin İlkeleri, Efil Yayınevi", ReferenceType.AKADEMIK_KITAP, "Mikro ve makroekonomiye standart ders kitabı.")
        )
    ),
    Lesson(
        id = "m06_l02",
        monthNumber = 6,
        weekNumber = 2,
        discipline = DisciplineId.EKONOMI,
        title = "Makroekonomi: Enflasyon, Faiz, GSYH, Merkez Bankaları ve Sistemler",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "GSYH, işsizlik, enflasyon, faiz, merkez bankacılığı, maliye politikası ile ekonomik sistemler ve kurumsal kalkınma (Acemoğlu & Sen) konularını anlamak.",
        learningOutcomes = listOf(
            "Tanıma: GSYH, TÜFE/enflasyon, politika faizi ve maliye politikasını tanımlar.",
            "Anlama: Enflasyonun alım gücünü nasıl erittiğini ve merkez bankalarının faiz aracını neden kullandığını açıklar.",
            "Analiz ve Sentez: Keynes, Marx, Hayek, Amartya Sen ve Daron Acemoğlu perspektiflerinden refah ve kurumları karşılaştırır."
        ),
        prerequisites = listOf("Ders 16."),
        keyConcepts = listOf(
            ConceptCard("Enflasyon", "Mal ve hizmetlerin genel fiyat düzeyindeki sürekli artış ve paranın alım gücündeki düşüş.", "Geçen yıl 100 TL'ye dolan sepetin bugün 150 TL'ye dolması."),
            ConceptCard("GSYH (Gayri Safi Yurtiçi Hasıla)", "Bir ülkede belirli dönemde üretilen tüm nihai mal ve hizmetlerin toplam piyasa değeri.", "Üretim hacmini ölçer ancak gelir dağılımını ve çevre tahribatını tek başına göstermez."),
            ConceptCard("Kapsayıcı Kurumlar (Daron Acemoğlu)", "Hukukun üstünlüğü, mülkiyet hakları ve fırsat eşitliği sunarak geniş kesimleri üretime katan kurumlar.", "Sömürücü kurumlar yerine kapsayıcı kurumlara sahip ülkelerin uzun vadede kalkınması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Para, Bankacılık, Enflasyon ve Merkez Bankası",
                "Para bir değişim aracı, değer saklama aracı ve hesap birimidir. Dolaşımdaki para ve toplam talep, ekonominin üretim kapasitesinin çok üzerine çıktığında veya maliyet şokları yaşandığında enflasyon doğar. Merkez bankaları para politikasıyla (faiz oranları ve likidite), hükümetler ise maliye politikasıyla (vergi ve kamu harcamaları) ekonomik dalgalanmaları yönetmeye çalışır."
            ),
            ContentSection(
                "Ekonomik Sistemler, Gelir Dağılımı ve Kalkınma",
                "Karl Marx sermaye birikimi ve sınıf eşitsizliklerine odaklanırken, John Maynard Keynes durgunluk dönemlerinde toplam talebi desteklemek için devlet müdahalesini savunur. Amartya Sen kalkınmayı yalnızca GSYH artışı değil 'insani yapabilirliklerin (eğitim, sağlık, özgürlük) genişlemesi' olarak tanımlar. Daron Acemoğlu ise ulusların zenginliğinin coğrafyadan çok kapsayıcı siyasal ve ekonomik kurumlara dayandığını kanıtlar."
            )
        ),
        historicalBackground = "1929 Büyük Buhranı Keynesyen makroekonomiyi doğurdu; 1970'lerin stagflasyonu merkez bankası bağımsızlığı tartışmasını getirdi; 21. yüzyılda kurumsal iktisat öne çıktı.",
        realLifeExamples = listOf(
            "Nominal maaşınız %20 artarken enflasyon %40 ise reel alım gücünüzün aslında düştüğünü hesaplayabilmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("John Maynard Keynes", "1883–1946", "Modern makroekonomi ve talep yönlü istikrar politikalarının kurucusu."),
            ThinkerHighlight("Karl Marx", "1818–1883", "Kapitalist üretim tarzı, emek-değer ve kriz dinamiklerinin eleştirel analisti."),
            ThinkerHighlight("Amartya Sen", "1933–", "Nobel ödüllü iktisatçı; kalkınmayı özgürlük ve yapabilirlik olarak tanımlayan düşünür."),
            ThinkerHighlight("Daron Acemoğlu", "1967–", "Nobel ödüllü iktisatçı; kapsayıcı kurumların kalkınmadaki rolünü ortaya koyan araştırmacı.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Ekonomik Kriz Çözümleri",
                "Keynesyen Yaklaşım", "Durgunlukta kamu yatırımı ve harcamayla toplam talebi canlandırır.",
                "Hayek / Piyasa Yaklaşımı", "Fiyat sinyallerinin bozulmamasını ve mali disiplini önceler."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Kapsayıcı Kurumlar", "destekler", "İnovasyon ve Refah"),
            ConceptRelation("Merkez Bankası Faizi", "etkiler", "Kredi Talebi ve Enflasyon")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Reel Faiz ve Alım Gücü",
            scenario = "Yıllık enflasyon %45 iken bir mevduat hesabı %35 faiz vermektedir.",
            analysisQuestions = listOf(
                "Yıl sonunda paranızın alım gücü artmış mıdır yoksa erimiş midir?"
            ),
            keyTakeaway = "Nominal faiz enflasyonun altındaysa reel getiri negatiftir; tasarruf sahibi alım gücü kaybeder."
        ),
        reflectionQuestions = listOf(
            "Sizce bir ülkenin başarısı yalnızca GSYH büyümesiyle ölçülebilir mi? Amartya Sen nasıl cevap verirdi?"
        ),
        summary = "Makroekonomi para, enflasyon, faiz ve istihdam dengelerini incelerken; gerçek kalkınma yalnızca rakamsal büyüme değil adil gelir dağılımı ve kapsayıcı kurumlarla ölçülür.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Para politikası ile maliye politikasını kimler yürütür?", "Para politikasını Merkez Bankası (faiz, para arzı), maliye politikasını Hükûmet/Hazine (vergi, kamu harcaması) yürütür.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m06_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Ulusların uzun vadeli ekonomik başarısını coğrafya veya kültürden ziyade 'kapsayıcı siyasal ve ekonomik kurumlara' bağlayan Nobel ödüllü iktisatçı kimdir?",
                options = listOf("Daron Acemoğlu", "David Ricardo", "Adam Smith", "William James"),
                correctOptionIndex = 0,
                explanation = "Daron Acemoğlu ve James Robinson, 'Ulusların Düşüşü' eserinde kapsayıcı kurumların önemini ortaya koymuştur."
            )
        ),
        practiceTask = PracticeTask(
            title = "Enflasyon Sepeti Takibi",
            instructions = "Kendi aylık harcamalarınızda en hızlı fiyatı artan 3 kalemi belirleyip yıllık tahmini kişisel enflasyonunuzu hesaplayın.",
            deliverableHint = "Resmi manşet enflasyon ile kişisel sepetiniz arasındaki farkı gözlemleyin."
        ),
        references = listOf(
            AcademicReference("Daron Acemoğlu & James Robinson, Ulusların Düşüşü, Doğan Kitap", ReferenceType.AKADEMIK_KITAP, "Kurumsal iktisat ve kalkınma."),
            AcademicReference("TCMB Eğitim ve Ekonomi Okuryazarlığı Yayınları", ReferenceType.BILIMSEL_KURUM, "Merkez bankacılığı ve para politikası temelleri.")
        )
    ),
    Lesson(
        id = "m06_l03",
        monthNumber = 6,
        weekNumber = 4,
        discipline = DisciplineId.EKONOMI,
        title = "Kişisel Finans: Bütçe, Tasarruf, Bileşik Getiri ve Risk Yönetimi",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "Bireysel bütçe yönetimi, acil durum fonu, borç disiplini, bileşik getiri matematiği ve finansal risk çeşitlendirmesi becerilerini kazandırmak.",
        learningOutcomes = listOf(
            "Tanıma: Gelir-gider dengesi, acil durum fonu, bileşik getiri ve çeşitlendirme kavramlarını tanımlar.",
            "Uygulama: Kişisel bütçe planı ve basit bileşik getiri hesabı yapar.",
            "Analiz ve Sentez: Yüksek getiri vaadi taşıyan finansal riskleri eleştirel biçimde değerlendirir."
        ),
        prerequisites = listOf("Ders 16 ve 17."),
        keyConcepts = listOf(
            ConceptCard("Bileşik Getiri", "Anaparadan kazanılan getirinin de zaman içinde yeniden getiri üretmesi ('kartopu etkisi').", "Her yıl %10 bileşik getiriyle para yaklaşık 7,2 yılda ikiye katlanır (72 Kuralı)."),
            ConceptCard("Acil Durum Fonu", "İş kaybı veya sağlık gibi beklenmedik durumlar için likit tutulan 3–6 aylık zorunlu gider rezervi.", "Yatırım bozdurmadan veya yüksek faizli borca girmeden kriz atlatmak."),
            ConceptCard("Çeşitlendirme", "Tüm birikimi tek bir varlığa koymayıp farklı risk profillerine dağıtma.", "Tek sepete tüm yumurtaları koymamak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Bütçe Yönetimi ve Önce Kendine Ödeme İlkesi",
                "Pek çok kişi ay sonunda 'kalanı' biriktirmeye çalışır ve genelde hiçbir şey kalmaz. Sürdürülebilir bütçe; gelir gelir gelmez zorunlu ihtiyaçları, tasarruf/yatırım oranını ve serbest harcamayı baştan ayırmaktır (ör. 50/30/20 çerçevesi kişisel koşullara göre uyarlanır)."
            ),
            ContentSection(
                "Bileşik Getiri ve Finansal Risk",
                "Finansta getiri ile risk arasında kopmaz bir bağ vardır. Kısa sürede 'garanti yüksek kazanç' vadeden hiçbir enstrüman risksiz değildir. Sabırlı, düzenli ve enflasyon üstü reel getiri hedefleyen yatırımlar zamanla büyük fark yaratır. Ayrıca tüketim kredisi faizi bileşik borç tuzağı oluşturabilir."
            )
        ),
        historicalBackground = "Modern portföy teorisi (Harry Markowitz) riskin yalnızca bireysel varlıkların değil varlıkların birbiriyle korelasyonunun yönetilmesiyle azaltılabileceğini göstermiştir.",
        realLifeExamples = listOf(
            "72 Kuralı: Yıllık reel getiri oranına 72'yi bölerek birikimin kaç yılda iki katına çıkacağını tahmin etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Amartya Sen", "Finansal Özgürlük Bağlamı", "Ekonomik güvencenin bireyin yaşam seçeneklerini ve özgürlüğünü genişletmesi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Finansal Yaklaşım",
                "Spekülasyon", "Kısa vadeli fiyat hareketlerini tahmin etmeye dayalı yüksek riskli kumar.",
                "Uzun Vadeli Yatırım", "Gerçek değer üretimine, çeşitlendirmeye ve bileşik zamana dayanır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Zaman + Düzenli Tasarruf", "besler", "Bileşik Getiri"),
            ConceptRelation("Çeşitlendirme", "azaltır", "Tekil Varlık Riski")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Erken Başlamanın Gücü",
            scenario = "Ayşe 25–35 yaş arası her ay 1 birim biriktirip sonra ekleme yapmıyor. Mehmet ise 35–55 yaş arası 20 yıl boyunca her ay 1 birim biriktiriyor.",
            analysisQuestions = listOf(
                "Eşit reel getiri altında neden Ayşe'nin erken başlayan bileşik getirisi Mehmet'e yaklaşabilir veya onu geçebilir?"
            ),
            keyTakeaway = "Bileşik getiride zaman çarpanı miktardan daha etkilidir; küçük de olsa erken başlamak kritiktir."
        ),
        reflectionQuestions = listOf(
            "Kendi bütçenizde önümüzdeki 3 ay içinde kısabileceğiniz ama yaşam kalitenizi düşürmeyecek bir harcama kalemi hangisidir?"
        ),
        summary = "Sağlam kişisel finans; bütçe disiplini, acil durum fonu, yüksek faizli borçtan kaçınma, çeşitlendirme ve bileşik getirinin sabrına dayanır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("72 Kuralı nasıl kullanılır?", "72 sayısını yıllık reel getiri yüzdesine böldüğünüzde paranın ikiye katlanacağı yaklaşık yılı bulursunuz.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m06_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "72 Kuralı'na göre yıllık %9 reel getiri sağlayan bir birikim yaklaşık kaç yılda iki katına çıkar?",
                options = listOf("4 yıl", "8 yıl", "12 yıl", "18 yıl"),
                correctOptionIndex = 1,
                explanation = "72 / 9 = 8 yıl."
            ),
            QuizQuestion(
                id = "m06_l03_q2",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Kişisel bütçe yönetimi ile Ay 2'de öğrendiğiniz 'dürtüsel Sistem 1 kararları ve ortam tasarımı' arasında nasıl bir bağ kurarsınız?",
                explanation = "Otomatik tasarruf talimatı vermek ve alışveriş tetikleyicilerini azaltmak, Sistem 1'in anlık harcama dürtülerini Sistem 2 lehine dengeler.",
                rubricHint = "Psikoloji ve kişisel finans bağlantısı aranır."
            )
        ),
        practiceTask = PracticeTask(
            title = "50/30/20 Bütçe Taslağı",
            instructions = "Aylık net gelirinizi Zorunlu İhtiyaçlar, İstekler ve Tasarruf/Borç Azaltma olmak üzere üç sütuna bölün.",
            deliverableHint = "Ay 9'da Python ile kendi bütçe analiz aracımızı kodlayacağız!"
        ),
        references = listOf(
            AcademicReference("SPK & Borsa İstanbul Finansal Okuryazarlık Rehberi", ReferenceType.BILIMSEL_KURUM, "Türkiye standartlarında bütçe ve risk okuryazarlığı.")
        )
    )
)
