package com.example.data.curriculum

import com.example.data.model.*

val months10To12Lessons: List<Lesson> = listOf(
    // ======================== AY 10 — YAPAY ZEKÂ ÇAĞI (TEKNOLOJİ & YZ) ========================
    Lesson(
        id = "m10_l01",
        monthNumber = 10,
        weekNumber = 1,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Yapay Zekâ, Makine Öğrenmesi, Derin Öğrenme ve Büyük Dil Modelleri",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Klasik kural tabanlı yazılımlarla makine öğrenmesi, derin öğrenme ve büyük dil modellerinin (LLM) çalışma mantığını teknik doğruluğu bozmadan sade biçimde kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: Yapay zekâ, makine öğrenmesi, sinir ağı, token ve büyük dil modeli kavramlarını tanımlar.",
            "Anlama: LLM'lerin 'anlamlı metin üretirken' neden bazen halüsinasyon üretebildiğini olasılıksal temelde açıklar.",
            "Analiz ve Sentez: Yapay zekâ çıktılarını eleştirel düşünme ve kaynak doğrulaması ile sınar."
        ),
        prerequisites = listOf("Ay 3 Olasılık ve Ay 9 Programlama Temelleri."),
        keyConcepts = listOf(
            ConceptCard("Makine Öğrenmesi", "İnsanın her kuralı tek tek kodlaması yerine, modelin çok sayıda veriden örüntü öğrenmesi.", "Binlerce e-posta örneğinden spam ayırmayı öğrenen filtre."),
            ConceptCard("Büyük Dil Modeli (LLM)", "Dev metin veri setleri üzerinde bağlam içindeki bir sonraki kelime/token olasılığını tahmin ederek eğitilen derin sinir ağı.", "Cümlenin gidişatına ve derin bağlama göre tutarlı yanıt örmesi."),
            ConceptCard("Halüsinasyon (Uydurma Bilgi)", "Modelin akıcı ve ikna edici bir dille olgusal olarak yanlış veya kaynağı olmayan bilgi üretmesi.", "Var olmayan bir kitap sayfasını uydurabilmesi; bu yüzden insan denetimi şarttır.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Kuraldan Öğrenmeye: Yapay Zekâ Hiyerarşisi",
                "Yapay zekâ en geniş şemsiyedir. Klasik programlamada kuralları insan yazar (eğer sıcaklık > 30 ise klimayı aç). Makine öğrenmesinde ise girdi ve örnek çıktılar verilir, algoritma matematiksel ağırlıkları optimize ederek kuralı veriden çıkarır. Derin öğrenme (yapay sinir ağları) çok katmanlı yapısıyla görüntü, ses ve doğal dilde çığır açmıştır."
            ),
            ContentSection(
                "Üretken Yapay Zekâ ve Büyük Dil Modelleri Nasıl Çalışır?",
                "Transformer mimarisi ve 'dikkat (attention)' mekanizması sayesinde LLM'ler kelimeler arasındaki uzun mesafeli anlam ilişkilerini yakalar. Ancak modeller canlı bir insan bilinci veya değişmez ansiklopedi değil, olasılıksal örüntü motorlarıdır. Bu yüzden kaynak doğrulama sorumluluğu her zaman kullanıcıdadır."
            )
        ),
        historicalBackground = "1950 Turing Testi'nden 2012 derin öğrenme atılımına ve 2017 Transformer mimarisiyle başlayan üretken yapay zekâ çağına uzanan evrim.",
        realLifeExamples = listOf(
            "Yapay zekâdan bir tarihî olayın kaynakçasını aldığınızda ilgili kitabın gerçekten var olup olmadığını kontrol etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Geoffrey Hinton", "1947–", "Nobel ödüllü yapay sinir ağları ve geri yayılım öncüsü."),
            ThinkerHighlight("Fei-Fei Li", "1976–", "ImageNet ile modern bilgisayarlı görü devrimini tetikleyen ve insan merkezli YZ savunucusu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Yazılım Paradigmaları",
                "Klasik Programlama", "Kural + Veri -> Çıktı (kesin, deterministik).",
                "Makine Öğrenmesi", "Veri + Örnek Çıktılar -> Öğrenilmiş Model (olasılıksal)."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Veri + İstatistik", "eğitir", "Makine Öğrenmesi Modeli"),
            ConceptRelation("Olasılıksal Tahmin", "riskini taşır", "Halüsinasyon")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Yanlış Hukuki İçtihat",
            scenario = "Bir hukuk öğrencisi ödevinde araştırma yapmadan YZ'nin verdiği üç mahkeme kararını aynen kullanır ve kararların hiç var olmadığı ortaya çıkar.",
            analysisQuestions = listOf(
                "YZ burada araç olarak doğru mu kullanılmıştır, yoksa doğrulama adımı mı atlanmıştır?"
            ),
            keyTakeaway = "Yapay zekâ mükemmel bir taslak, beyin fırtınası ve analiz ortağıdır; ancak nihai olgusal doğrulama sorumluluğu insandadır."
        ),
        reflectionQuestions = listOf(
            "Yapay zekânın güçlü olduğu alanlarla (hız, sentez, taslak) insan zekâsının vazgeçilmez olduğu alanlar (vicdan, yargı, özgün soru) nelerdir?"
        ),
        summary = "Makine öğrenmesi ve büyük dil modelleri veriden örüntü öğrenen olasılıksal sistemlerdir; büyük üretkenlik sunarken doğrulama ve eleştirel denetim gerektirir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Büyük dil modellerinde 'halüsinasyon' neden oluşur?", "Model doğrulanmış bir veri tabanından kopyalamak yerine istatistiksel olarak akıcı görünen sonraki kelimeyi tahmin ettiği için.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m10_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.ANLAMA,
                prompt = "Klasik programlama ile makine öğrenmesi arasındaki temel fark nedir?",
                options = listOf(
                    "Makine öğrenmesi elektrik kullanmaz",
                    "Klasik programlamada kuralları insan yazar; makine öğrenmesinde sistem kuralları veriden çıkarır",
                    "Klasik programlama sadece metin işler",
                    "Makine öğrenmesi asla hata yapmaz"
                ),
                correctOptionIndex = 1,
                explanation = "Makine öğrenmesinde algoritma örnek verilerden örüntü ve karar sınırları öğrenir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Öğrenme Projesi 3: YZ Destekli Araştırma Asistanı Deneyi",
            instructions = "Uygulamadaki 'Öğretmen' sekmesinde bir konu seçip önce 'Konu Anlatımı', ardından 'Eleştirel Düşünme' modunda yanıt alıp iki yanıtın farkını karşılaştırın.",
            deliverableHint = "Aldığınız en iyi içgörüyü tek dokunuşla Notlara Kaydedin."
        ),
        references = listOf(
            AcademicReference("Stuart Russell & Peter Norvig, Artificial Intelligence: A Modern Approach", ReferenceType.UNIVERSITE_YAYINI, "Yapay zekâ alanının temel üniversite ders kitabı.")
        )
    ),
    Lesson(
        id = "m10_l02",
        monthNumber = 10,
        weekNumber = 2,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Prompt Engineering, Üretkenlik ve Yapay Zekâ ile Proje Geliştirme",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Yapay zekâ araçlarından yüksek nitelikli, doğrulanabilir çıktı almayı sağlayan istem mühendisliği (prompt engineering) ilkelerini ve proje geliştirme akışını öğretmek.",
        learningOutcomes = listOf(
            "Tanıma: Bağlam, rol, kısıt, örnek (few-shot) ve çıktı formatı bileşenlerini tanımlar.",
            "Uygulama: Sıradan bir soruyu yapılandırılmış, yüksek verimli bir isteme dönüştürür."
        ),
        prerequisites = listOf("Ders 28."),
        keyConcepts = listOf(
            ConceptCard("Yapılandırılmış İstem (Prompt)", "Rol + Bağlam + Görev + Kısıtlar + Çıktı Biçimi çerçevesiyle net talimat verme.", "'Ekonomi anlat' yerine 'Lise düzeyi olmayan bir okura enflasyonu 200 kelimede günlük örnekle açıkla' demek."),
            ConceptCard("Düşünce Zinciri ve İterasyon", "Modelden adım adım gerekçe istemek ve ilk yanıtı eleştirip iyileştirmek.", "Tek seferlik soru yerine diyalogla kaliteyi yükseltmek.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Prompt Engineering: Net Düşünmenin Aynası",
                "Belirsiz bir istem, belirsiz ve genel geçer bir yanıt üretir. İyi bir istem dört şeyi netleştirir: 1) Amacınız ve hedef kitleniz nedir? 2) Hangi bağlam/metin kullanılmalı? 3) Neler yapılmamalı (kısıtlar)? 4) Yanıt hangi yapıda olmalı (tablo, soru listesi, adım adım Python kodu)?"
            ),
            ContentSection(
                "Yapay Zekâ ile Proje Geliştirme (Öğrenme Projesi 4)",
                "Artık kodlama bilmeyen bir öğrenci bile YZ ile birlikte problem tanımlayıp küçük Python araçları geliştirebilir. Ancak altın kural şudur: Üretilen her satırı anlamadan kopyalamayın; YZ'ye 'Bu satır ne yapıyor, hata durumu nedir?' diye sorarak öğrenin."
            )
        ),
        historicalBackground = "2022 sonrası üretken modellerin yaygınlaşmasıyla doğal dil, yeni bir programlama ve araştırma arayüzüne dönüştü.",
        realLifeExamples = listOf(
            "Okuduğunuz zor bir makaleyi YZ'ye yapıştırıp 'Bana özetleme, aksine argümanındaki 3 zayıf noktayı sorarak öğret' demek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Yann LeCun", "1960–", "Evrişimli sinir ağları ve dünya modelleri üzerine çalışan Turing ödüllü bilim insanı.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İstem Kalitesi",
                "Zayıf İstem", "'Bana Python anlat.' -> Dağınık ve yüzlerce satırlık genel metin.",
                "Güçlü İstem", "'Hiç kod bilmeyen biri için günlük bütçe hesaplayan 10 satırlık Python kodu yaz ve her satırı tek cümlede açıkla.'"
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Net Bağlam + Kısıt", "yükseltir", "Çıktı Kalitesi")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Pasif Kopyalama vs Ortak Üretim",
            scenario = "İki öğrenci de Python ile veri tablosu analizi yapar. Birincisi kodu okuyup hiç anlamadan çalıştırır; ikincisi hata aldığında YZ ile hata mesajını adım adım çözer.",
            analysisQuestions = listOf(
                "Altı ay sonra gerçek bir problemde hangisi bağımsız çözüm üretebilir?"
            ),
            keyTakeaway = "YZ düşünmenin yerine geçerse zihni tembelleştirir; düşünmeyi desteklerse kapasiteyi katlar."
        ),
        reflectionQuestions = listOf(
            "Kendi öğrenme alanınızda tekrar eden bir görev için nasıl bir YZ iş akışı kurabilirsiniz?"
        ),
        summary = "Prompt engineering net düşünme ve bağlam kurma sanatıdır; yapay zekâyı düşünmenin yerine değil zihinsel kaldıraç olarak konumlandırmak gerekir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Etkili bir istemin 4 temel bileşeni nelerdir?", "Rol/amaç, bağlam/veri, kısıtlar ve beklenen çıktı formatı.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m10_l02_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.UYGULAMA,
                prompt = "'Bana Tarih hakkında bilgi ver' şeklindeki zayıf istemi; rol, bağlam, kısıt ve çıktı formatı ekleyerek güçlü bir isteme dönüştürün.",
                explanation = "İyi yanıt; belirli bir dönem, hedef seviye, kaynak ayrımı kısıtı ve yapılandırılmış format içerir.",
                rubricHint = "4 istem bileşeni net biçimde yer almalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Öğrenme Projesi 4: Kişisel Python Uygulama Tasarımı",
            instructions = "Öğretmen sekmesinde 'Konu Anlatımı' modunu seçip şu istemi deneyin: 'Günlük okuma sayfalarımı takip eden 15 satırlık başlangıç düzeyinde bir Python programı tasarla ve her satırını açıkla.'",
            deliverableHint = "Çıktıyı Notlarım'a 'Python Projem' olarak kaydedin."
        ),
        references = listOf(
            AcademicReference("UNESCO, Guidance for Generative AI in Education and Research", ReferenceType.BILIMSEL_KURUM, "Eğitimde üretken yapay zekânın pedagojik kullanımı.")
        )
    ),
    Lesson(
        id = "m10_l03",
        monthNumber = 10,
        weekNumber = 4,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Yapay Zekâ Etiği, Algoritmik Önyargı ve İnsan Özerkliği",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "Algoritmik yanlılık, veri telifi, deepfake, iş gücü dönüşümü ve yapay zekâ çağında insan sorumluluğunu etik-felsefi boyutuyla incelemek.",
        learningOutcomes = listOf(
            "Tanıma: Algoritmik önyargı, şeffaflık, hesap verebilirlik ve deepfake risklerini tanımlar.",
            "Analiz ve Sentez: Ay 1 etik ilkelerini yapay zekâ yönetişimi problemlerine uygular."
        ),
        prerequisites = listOf("Ay 1 Etik ve Ders 28."),
        keyConcepts = listOf(
            ConceptCard("Algoritmik Önyargı", "Eğitim verisindeki tarihsel eşitsizlikleri modelin öğrenip işe alım veya kredi kararlarında büyütmesi.", "Geçmiş verilere göre otomatik eleme yapan sistemin belirli grupları dışlaması."),
            ConceptCard("Hesap Verebilirlik", "Bir yapay zekâ sistemi zarar verdiğinde sorumluluğun 'kod yaptı' diyerek belirsizleşmemesi, insan/kurum muhatabının kalması.", "Otonom kararlarda insan denetimi (human-in-the-loop).")
        ),
        detailedContent = listOf(
            ContentSection(
                "Tarafsız Görünümün Ardındaki Değerler",
                "Bir algoritmanın matematiksel olması onun etik açıdan tarafsız olduğu anlamına gelmez. Hangi verinin toplandığı, neyin optimize edildiği (ör. yalnızca tıklanma süresi mi yoksa kullanıcı refahı mı?) tamamen insani ve politik seçimlerdir."
            ),
            ContentSection(
                "Hakikat Krizi ve İnsan Değeri",
                "Sentetik ses ve görüntülerin (deepfake) ucuzlamasıyla 'gördüğüme inanırım' dönemi sona ermiştir. Kaynak doğrulama, dijital okuryazarlık ve eleştirel düşünme her zamankinden daha kıymetlidir."
            )
        ),
        historicalBackground = "AB Yapay Zekâ Yasası (AI Act) ve UNESCO YZ Etiği Tavsiye Kararı ile şekillenen küresel yönetişim ilkeleri.",
        realLifeExamples = listOf(
            "Sosyal medyada gördüğünüz şok edici bir ses kaydı veya videonun sentetik olma ihtimalini sorgulamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Hannah Arendt & Fei-Fei Li", "Sorumluluk ve İnsan Merkezli YZ", "Teknolojinin insan yargısını devre dışı bırakmaması gerekliliği.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "YZ Yönetişimi",
                "Tam Otonom Karar", "Hızlı ama şeffaf olmayan, itirazı zor 'kara kutu' kararlar.",
                "İnsan Denetimli YZ", "Model öneri sunar; nihai etik ve hukuki kararı hesap verebilir insan verir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Yanlı Eğitim Verisi", "üretir", "Adaletsiz Tahmin"),
            ConceptRelation("İnsan Denetimi", "korur", "Hesap Verebilirlik")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Otomatik İşe Alım Filtresi",
            scenario = "Bir şirket geçmiş 10 yılın terfi verileriyle eğittiği yapay zekâya yeni adayları sıralatır; ancak sistem bazı aday gruplarını sistematik olarak geriye atar.",
            analysisQuestions = listOf(
                "Tarihsel veriyi körü körüne geleceğe taşımak neden etik ve verimlilik sorunu yaratır?"
            ),
            keyTakeaway = "Geçmiş önyargıları içeren veriler denetlenmeden eğitildiğinde teknoloji eski eşitsizlikleri görünmez kılarak otomatikleştirir."
        ),
        reflectionQuestions = listOf(
            "Hangi hayati kararların (sağlık, yargı, eğitim) asla tamamen insansız algoritmalara bırakılmaması gerektiğini düşünüyorsunuz?"
        ),
        summary = "Yapay zekâ etiği; veride adalet, şeffaflık, mahremiyet ve insan hesap verebilirliğini koruma mücadelesidir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Algoritmik önyargı neden oluşur?", "Modelin eğitiminde kullanılan tarihsel verilerin eksik, dengesiz veya toplumsal önyargılar içermesinden.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m10_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.ANLAMA,
                prompt = "Yapay zekâ sistemlerinde 'Human-in-the-loop' (Döngüde İnsan) ilkesi neyi amaçlar?",
                options = listOf(
                    "İnsanları tamamen işten çıkarmayı",
                    "Kritik kararların nihai onay ve etik sorumluluğunu insanda tutmayı",
                    "Bilgisayarları internetsiz çalıştırmayı",
                    "Veri boyutunu küçültmeyi"
                ),
                correctOptionIndex = 1,
                explanation = "Kritik alanlarda yapay zekânın karar destek aracı olarak kalıp nihai sorumluluğun insanda olması ilkesidir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Etik Denetim Listesi",
            instructions = "Kullandığınız bir yapay zekâ aracının gizlilik ayarlarını (sohbetlerin model eğitiminde kullanılıp kullanılmadığını) kontrol edin.",
            deliverableHint = "Dijital mahremiyet farkındalığı."
        ),
        references = listOf(
            AcademicReference("Cathy O'Neil, Matematiksel İmha Silahları, Tellekt", ReferenceType.AKADEMIK_KITAP, "Algoritmik önyargı ve hesap verebilirlik analizi.")
        )
    ),

    // ======================== AY 11 — DİN, AHLAK VE İNSANLIĞIN ANLAM ARAYIŞI ========================
    Lesson(
        id = "m11_l01",
        monthNumber = 11,
        weekNumber = 1,
        discipline = DisciplineId.DIN_AHLAK,
        title = "Din Kavramı, Dinler Tarihi ve Ahlak Felsefesi",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Din olgusunu antropolojik, tarihsel ve felsefi boyutlarıyla incelemek; dünya dinlerinin temel yaklaşımlarını ve din–ahlak ilişkisini tarafsız bir ilmî dille kavramak.",
        learningOutcomes = listOf(
            "Tanıma: Din kavramının inanç, ibadet, ahlak ve toplumsal kurum boyutlarını tanımlar.",
            "Anlama: Dinler tarihinde tevhid/monoteizm ve doğu gelenekleri arasındaki temel kavramsal farkları açıklar.",
            "Analiz ve Sentez: Euthyphron ikilemi üzerinden ahlak ile dinî emir arasındaki felsefi ilişkiyi tartışır."
        ),
        prerequisites = listOf("Ay 1 Etik ve Ay 4 Medeniyetler Tarihi."),
        keyConcepts = listOf(
            ConceptCard("Dinin Dört Boyutu", "İnanç (akide), İbadet (ritüel), Ahlak (değer) ve Toplumsal Kurum.", "Sadece şekle indirgemeden dört boyutu bütünlükle okumak."),
            ConceptCard("Dinler Tarihi Metodolojisi", "Kendi inancını veya karşı inancı karikatürize etmeden, birincil metinlere dayanarak fenomenolojik inceleme yapmak.", "İddiaları kendi bağlamı içinde doğru anlamak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Din Nedir? İnsanın Kutsal ve Anlam Arayışı",
                "Tarih boyunca bilinen tüm toplumlarda kutsal, aşkınlık, köken ve ölüm ötesi sorularına verilen yanıtlar dinî gelenekleri şekillendirmiştir. Din yalnızca bireysel bir vicdan deneyimi değil; aynı zamanda ortak değerler, hukuk normları, sanat ve dayanışma üreten köklü bir medeniyet kurucusudur."
            ),
            ContentSection(
                "Dinler Tarihi ve Ahlak İlişkisi",
                "Semavi/İbrahimi gelenekler (Yahudilik, Hristiyanlık, İslam) tek Tanrı ve vahiy merkezli bir tarih anlayışına dayanırken; Hinduizm, Budizm gibi Doğu gelenekleri döngüsel zaman ve içsel arınma vurgusu taşır. Ahlak felsefesinde ise 'Bir eylem Tanrı emrettiği için mi iyidir, yoksa iyi olduğu için mi emredilmiştir?' sorusu (Eşari ve Maturidi/Mutezile arasında da derin biçimde tartışılan hüsün-kubuh meselesi) İslam ve Batı düşüncesinin en verimli kavşaklarından biridir."
            )
        ),
        historicalBackground = "Antik çağdan ortaçağ kelâm tartışmalarına ve modern din bilimleri (religionswissenschaft) disiplinine uzanan karşılaştırmalı inceleme geleneği.",
        realLifeExamples = listOf(
            "Farklı inançlara veya yorumlara sahip insanlarla konuşurken önyargı yerine birincil kavramlara dayalı saygılı diyalog kurmak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("İmam Maturidi", "853–944", "Aklın iyiyi ve kötüyü kavrama kapasitesini vurgulayan Sünni kelâm ekolünün kurucu imamı."),
            ThinkerHighlight("İmam Eşari", "874–936", "İlahi irade ve sıfatlar konusunda Ehl-i Sünnet kelâmını sistemleştiren âlim.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İyilik ve Kötülüğün Bilinmesi (Hüsün–Kubuh)",
                "Maturidi Yaklaşımı", "Bazı temel iyi ve kötü değerler (ör. adalet, doğruluk) vahiy gelmeden de akılla bilinebilir.",
                "Eşari Yaklaşımı", "Ahlaki yükümlülük ve nihai iyi-kötü hükmü ilahi hitap/vahiy ile sabitlenir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Akıl + Vahiy", "tartışır", "Maturidi & Eşari Kelâmı"),
            ConceptRelation("İnanç + İbadet", "tamamlanır", "Ahlak")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Kaynaksız Dinî İddia",
            scenario = "Sosyal medyada herhangi bir ayet veya sahih hadis kaynağı verilmeden kesin bir dinî hüküm gibi paylaşılan bir gönderiyle karşılaşıyorsunuz.",
            analysisQuestions = listOf(
                "İlmi ölçütlere göre ayet, hadis ve âlim yorumu neden birbirinden net biçimde ayrılmalıdır?"
            ),
            keyTakeaway = "Kaynak gösterilmeden üretilen dinî hükümler ilmî açıdan geçersizdir; vahiy metni ile beşeri içtihat/yorum daima ayrılmalıdır."
        ),
        reflectionQuestions = listOf(
            "Sizce ahlaki davranışın sürdürülebilir olmasında vicdan, akıl ve maneviyat nasıl bir denge kurar?"
        ),
        summary = "Din ve ahlak incelemesi; inanç, ibadet ve etik boyutlarını tarihsel ve ilmî kaynaklara sadık kalarak, mezhep ve yorum farklılıklarını saygıyla ayırt ederek yapılmalıdır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Hüsün-kubuh tartışmasında Maturidi ile Eşari arasındaki temel fark nedir?", "Maturidi aklın temel iyi-kötüyü vahiy öncesi de kavrayabileceğini savunurken, Eşari yükümlülüğün vahiyle belirlendiğini vurgular.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m11_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "İslam kelâm tarihinde aklın temel iyi ve kötüyü (hüsün ve kubuh) kavrama gücünü daha belirgin biçimde vurgulayan Ehl-i Sünnet ekolü hangisidir?",
                options = listOf("Maturidilik", "Sofizm", "Materyalizm", "Pozitivizm"),
                correctOptionIndex = 0,
                explanation = "İmam Maturidi, insan aklının Yaratıcıyı ve temel ahlaki değerleri kavrayabileceği görüşüyle öne çıkar."
            )
        ),
        practiceTask = PracticeTask(
            title = "Kaynak Ayrımı Uygulaması",
            instructions = "Merak ettiğiniz ahlaki bir konuda (ör. doğruluk veya kul hakkı) 1 Kur'an ayeti meali, 1 hadis kaynağı ve 1 klasik İslam düşünürünün yorumunu ayrı başlıklar altında not alın.",
            deliverableHint = "Ayet, hadis ve yorum katmanlarını net etiketleyin."
        ),
        references = listOf(
            AcademicReference("Diyanet İşleri Başkanlığı, Kur'an Yolu Meali ve Tefsiri", ReferenceType.GUVENILIR_DINI, "Güvenilir Türkçe meal ve tefsir kaynağı."),
            AcademicReference("TDV İslam Ansiklopedisi — 'Din', 'Maturidiyye', 'Eşariyye' maddeleri", ReferenceType.GUVENILIR_DINI, "Hakemli ve muteber ilmî ansiklopedi.")
        )
    ),
    Lesson(
        id = "m11_l02",
        monthNumber = 11,
        weekNumber = 2,
        discipline = DisciplineId.DIN_AHLAK,
        title = "İslam'ın Temel Kaynakları: Kur'an, Siyer, Hadis ve Fıkıh Usulü",
        durationMinutes = 35,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "İslamî ilimlerin temel kaynak hiyerarşisini (Kur'an, Sünnet/Hadis, İcma, Kıyas), Hanefi fıkhının akıl-maslahat metodolojisini ve mezhep/yorum ayrımını ilmî titizlikle öğretmek.",
        learningOutcomes = listOf(
            "Tanıma: Kur'an'ın ana konularını, Siyer'i, hadis sıhhat derecelerini (sahih/hasen/zayıf/mevzu) ve Hanefi fıkıh usulü kavramlarını tanımlar.",
            "Anlama: Ayet, sahih hadis, fıkhi içtihat ve tarihsel yorum arasındaki bağlayıcılık farkını açıklar.",
            "Uygulama: Bir dinî bilginin kaynağını ve mezhep/yorum bağlamını sorgulayarak değerlendirir."
        ),
        prerequisites = listOf("Ders 31 ve Ay 4 Kaynak Eleştirisi."),
        keyConcepts = listOf(
            ConceptCard("Kur'an-ı Kerim'in Ana Eksenleri", "Tevhid (inanç), ibadet, ahlak, kıssalar ve adalet-hukuk ilkeleri.", "Kur'an'ı bağlam ve bütünlük (siyak-sibak) içinde anlamak."),
            ConceptCard("Hadis İlmi (İsnad ve Metin Tenkidi)", "Hz. Peygamber'in (s.a.v.) söz, fiil ve takrirlerinin raviler zinciri (isnad) ve metin tutarlılığıyla incelenmesi.", "Sahih (Kütüb-i Sitte) ile zayıf veya uydurma (mevzu) rivayetleri ayırmak."),
            ConceptCard("Hanefi Fıkıh Usulü ve İçtihat", "İmam Azam Ebu Hanife'nin Kur'an ve Sünnet temelinde akıl yürütme (kıyas) ve kamu yararı/kolaylık (istihsan, örf) ilkelerini işleten hukuk yöntemi.", "Mezheplerin dinin özü değil, ayet ve hadisleri anlama yöntemleri olduğunu bilmek.")
        ),
        detailedContent = listOf(
            ContentSection(
                "İslam'ın Birincil Kaynakları: Kur'an ve Siyer/Hadis",
                "İslam düşüncesinin kurucu ve mutlak kaynağı Kur'an-ı Kerim'dir. Kur'an'ın anlaşılmasında tarihsel bağlam (esbâb-ı nüzul), dil bütünlüğü ve Hz. Peygamber'in (s.a.v.) hayatını inceleyen Siyer ilmi temel rehberdir. Hadis ilminde ise âlimler, rivayetlerin doğruluğunu korumak için dünyada eşi az görülür bir 'isnad' (kaynak zinciri) ve 'cerh-ta'dil' (ravi güvenilirliği) eleştirisi geliştirmiştir."
            ),
            ContentSection(
                "Fıkıh Usulü, Hanefi Yaklaşımı ve Mezhep Farklılıkları",
                "Fıkıh, ibadetler ve toplumsal ilişkilerde (muamelat) ayet ve hadislerden pratik kurallar çıkarma ilmidir. Hanefi mezhebi, nassların (ayet ve sahih hadis) yanında kıyas, istihsan (hakkaniyet/maslahat gereği daha uygun çözümü seçme) ve örfü dikkate almasıyla tanınır. Şafii, Maliki ve Hanbeli mezhepleriyle olan görüş farklılıkları birer 'bölünme' değil, delilleri yorumlama metodolojisindeki zenginliktir. Bu nedenle tek bir fıkıh görüşünü tartışmasız tek hakikat gibi sunmak yerine diğer mezhep görüşlerini de belirtmek ilmî dürüstlük gereğidir."
            ),
            ContentSection(
                "Ayet, Hadis ve Âlim Görüşü Ayrımı",
                "Güvenilir dinî okuryazarlığın altın kuralı şudur: Ayet vahyin bizzat kendisidir; hadis Hz. Peygamber'in uygulamalarıdır ve sıhhat derecesine bakılır; fıkıh hükmü veya âlim yorumu ise belirli bir çağın ve metodolojinin ilmî çıkarımıdır. Kaynak gösterilmeden hüküm üretilmez."
            )
        ),
        historicalBackground = "7. yüzyıl Mekke–Medine döneminden 8. yüzyılda Kûfe'de Ebu Hanife'nin fıkıh mektebine, Buhari ve Müslim'in hadis derlemelerine uzanan İslamî ilimler geleneği.",
        realLifeExamples = listOf(
            "Bir fetva veya dinî bilgi okurken 'Bu bir Kur'an ayeti mi, hangi hadis kitabında geçiyor, yoksa belirli bir mezhebin içtihadı mı?' diye kontrol etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("İmam Azam Ebu Hanife", "699–767", "Kıyas, istihsan ve şûra yöntemiyle Hanefi fıkhının kurucusu."),
            ThinkerHighlight("İmam Buhari", "810–870", "Sıkı isnad ve ravi eleştirisiyle Sahih-i Buhari'yi derleyen hadis âlimi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Kaynak Katmanları",
                "Ayet ve Mütevatir Sünnet", "Doğrudan bağlayıcı ana kaynaklardır.",
                "İçtihat ve Mezhep Görüşleri", "Delillerden yöntemle çıkarılan yorumlardır; farklı mezhepler farklı sonuçlara ulaşabilir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Kur'an + Sahih Sünnet", "temeldir", "Fıkıh Usulü"),
            ConceptRelation("İsnad + Metin Tenkidi", "korur", "Hadis Güvenilirliği"),
            ConceptRelation("Hanefi İstihsanı", "gözetir", "Hakkaniyet ve Kolaylık")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Mezhep Farklılığını Kavramak",
            scenario = "İki arkadaş bir ibadet ayrıntısında farklı uygulama görür ve biri diğerine 'Seninki tamamen yanlış' der.",
            analysisQuestions = listOf(
                "Hanefi ve Şafii fıkhındaki bir yorum farkı neden dinin inkârı değil delil okuma farkıdır?"
            ),
            keyTakeaway = "Mezhepler aynı Kur'an ve Sünnet kaynağına dayanan ilmî hukuk okullarıdır; farklı içtihatlar karşılıklı saygıyla karşılanır."
        ),
        reflectionQuestions = listOf(
            "İslamî ilimlerdeki isnad (kaynak zinciri) titizliği, modern akademideki kaynak gösterme disipliniyle nasıl benzeşmektedir?"
        ),
        summary = "İslamî okuryazarlık; Kur'an'ı, sahih hadis kaynaklarını ve Hanefi/diğer mezhep usullerini birbirine karıştırmadan, kaynak göstererek ve yorum katmanlarını ayırarak anlamaktır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Hanefi fıkhında 'istihsan' ne anlama gelir?", "Genel kıyastan daha adil, toplumsal faydaya ve kolaylığa uygun olan daha güçlü şer'i delili tercih etme yöntemi.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m11_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Hadis ilminde bir rivayetin Hz. Peygamber'e ulaşan aktarıcılar zincirinin güvenilirliğini inceleyen kavrama ne ad verilir?",
                options = listOf("İsnad", "İstihsan", "Kıyas", "Siyak"),
                correctOptionIndex = 0,
                explanation = "İsnad, rivayeti nakleden raviler silsilesini ve güvenilirliklerini inceleyen yöntemdir."
            ),
            QuizQuestion(
                id = "m11_l02_q2",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Farklı fıkıh mezheplerinin belirli ayrıntılarda farklı hükümlere varması, onların farklı kutsal kitaplara dayanmasından kaynaklanır.",
                correctBool = false,
                explanation = "Yanlış. Dört büyük Sünni fıkıh mezhebi de aynı Kur'an ve Sünnet'e dayanır; farklılık delilleri yorumlama ve önceliklendirme usullerinden doğar."
            )
        ),
        practiceTask = PracticeTask(
            title = "TDV İslam Ansiklopedisi İncelemesi",
            instructions = "TDV İslam Ansiklopedisi'nden 'İstihsan' veya 'Hadis Usulü' kavramını okuyup kaynak göstererek 4 cümlelik özet çıkarın.",
            deliverableHint = "Güvenilir akademik dinî kaynak kullanımı."
        ),
        references = listOf(
            AcademicReference("TDV İslam Ansiklopedisi — 'Ebû Hanîfe', 'Hadis', 'Fıkıh' maddeleri", ReferenceType.GUVENILIR_DINI, "Muteber ilmî başvuru kaynağı."),
            AcademicReference("Hayreddin Karaman, İslam Hukukunda İctihad, İFAV", ReferenceType.GUVENILIR_DINI, "Mezhep ve içtihat metodolojisi.")
        )
    ),
    Lesson(
        id = "m11_l03",
        monthNumber = 11,
        weekNumber = 4,
        discipline = DisciplineId.DIN_AHLAK,
        title = "İslam Düşünce Tarihi, Din–Bilim İlişkisi ve Maneviyat",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Gazali, Farabi, İbn Sina, İbn Rüşd, İbn Haldun ve Mevlânâ çizgisinde akıl–vahiy, din–bilim ve kalbî maneviyat dengesini anlamak.",
        learningOutcomes = listOf(
            "Tanıma: Farabi–İbn Sina–Gazali–İbn Rüşd arasındaki felsefi tartışmayı ve Mevlânâ'nın tasavvuf ahlakını tanımlar.",
            "Anlama: İbn Rüşd'ün 'Hakikat hakikatle çelişmez' ilkesiyle din ve bilim ilişkisini açıklar.",
            "Analiz ve Sentez: Modern dünyada anlam bunalımı ile manevi derinlik arasındaki bağı değerlendirir."
        ),
        prerequisites = listOf("Ders 31, 32 ve Ay 1 Felsefe."),
        keyConcepts = listOf(
            ConceptCard("Hakikat Hakikatle Çelişmez (İbn Rüşd)", "Akli/bilimsel burhan ile doğru anlaşılan vahyin çatışmayacağı, çatışma görünürse yorumun derinleştirilmesi gerektiği ilkesi.", "Faslül-Makal eserindeki felsefe-din uyumu."),
            ConceptCard("Kalbî İhlas ve Muhasebe (Gazali & Mevlânâ)", "Dinin yalnızca dış kurallarla değil niyet, tevazu, merhamet ve öz-denetimle yaşanması.", "Gazali'nin İhyâ'sı ve Mevlânâ'nın sevgi-hikmet dili.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Büyük Diyalog: Gazali ve İbn Rüşd",
                "İslam düşünce tarihi donuk bir tekrar değil, canlı bir tartışma alanıydı. Gazali, Tehâfütü'l-Felâsife'de Meşşai filozofların (Farabi, İbn Sina) metafizik iddialarını mantıksal olarak eleştirdi; İbn Rüşd ise Tehâfütü't-Tehâfüt ve Faslü'l-Makâl'de aklın felsefi araştırma hakkını savunarak vahiy ile sahih aklın uzlaşacağını gösterdi."
            ),
            ContentSection(
                "Din, Bilim ve Modernite",
                "Din 'Neden varız, nasıl yaşamalıyız?' gibi anlam ve değer sorularını yanıtlarken; bilim 'Doğa nasıl işliyor?' sorusunu deneyle araştırır. İkisinin alanı karıştırılmadığında (bilimi dogmaya, dini ise fizik laboratuvarına indirgemediğinde) verimli bir uyum doğar."
            ),
            ContentSection(
                "Mevlânâ ve İnsanın Anlam Arayışı",
                "Mevlânâ Celâleddîn-i Rûmî, insanın benmerkezcilikten çıkıp merhamet, sabır ve aşkın hikmetle olgunlaşmasını işler. Hız ve tüketim çağında maneviyat, zihni sakinleştiren ve vicdanı diri tutan bir denge noktasıdır."
            )
        ),
        historicalBackground = "11.–13. yüzyıllar İslam dünyasında kelâm, felsefe ve tasavvufun büyük sentezlere (Gazali, İbn Rüşd, Mevlânâ) ulaştığı dönemdir.",
        realLifeExamples = listOf(
            "Bilimsel bir keşif karşısında inançla panik veya inkâra düşmek yerine İbn Rüşd'ün yöntem ilkelerini hatırlamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Gazali", "1058–1111", "Kelâm, fıkıh ve tasavvufu 'İhyâu Ulûmi'd-Dîn'de birleştiren büyük düşünür."),
            ThinkerHighlight("İbn Rüşd", "1126–1198", "Endülüs'ün büyük filozofu ve kadısı; akıl-vahiy uyumunun savunucusu."),
            ThinkerHighlight("Mevlânâ", "1207–1273", "Mesnevî ile insanın kâmil ahlak ve maneviyat yolculuğunu anlatan mütefekkir.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Gazali vs İbn Rüşd",
                "Gazali", "Aklın metafizik mutlaklığına sınır çeker, kalbî tecrübeyi ve niyeti öne alır.",
                "İbn Rüşd", "Felsefi akıl yürütmenin (burhan) dinî açıdan da meşru ve gerekli olduğunu savunur."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Vahiy (Anlam)", "tamamlar", "Bilim (Doğa Yasası)"),
            ConceptRelation("Niyet + Öz-Muhasebe", "besler", "İslam Ahlakı")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Bilim ve İnanç Gerilimi",
            scenario = "Bir genç bilimsel bir kozmoloji teorisi duyduğunda 'Demek ki ya bilimi ya inancı tamamen reddetmeliyim' ikilemine düşer.",
            analysisQuestions = listOf(
                "İbn Rüşd ve Fuat Sezgin perspektifinden bu sahte ikilem nasıl aşılır?"
            ),
            keyTakeaway = "Doğa yasalarını keşfetmek anlam arayışını yok etmez; İslam bilim geleneğinde evreni incelemek tefekkürün bir parçası görülmüştür."
        ),
        reflectionQuestions = listOf(
            "Maddi yoğunluk ve ekran hızı arasında kendi iç huzurunuzu ve ahlaki pusulanızı nasıl koruyorsunuz?"
        ),
        summary = "İslam düşünce tarihi akıl, vahiy, hukuk ve maneviyat arasındaki zengin diyaloğun ürünüdür. Din-bilim uyumu alanların yöntem farkına saygıyla kurulur.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("İbn Rüşd'ün Faslü'l-Makâl'deki ünlü ilkesi nedir?", "Hikmet (akıl/felsefe) ile şeriat (vahiy) ikiz kardeştir; hakikat hakikatle çelişmez.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m11_l03_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Gazali ile İbn Rüşd arasındaki tartışmayı özetleyip modern din-bilim ilişkisi açısından bugüne hangi dersin çıkarılabileceğini yazın.",
                explanation = "İki düşünürün akıl sınırları hakkındaki tezleri ve günümüzdeki yapıcı diyalog vurgulanmalıdır.",
                rubricHint = "İki düşünürü karıştırmadan ayrı tezlerini belirtmek şarttır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Anlam ve Ahlak Muhasebesi",
            instructions = "Mevlânâ veya Gazali'den doğruluk ve tevazu üzerine seçeceğiniz kısa bir metni okuyup günlük hayatınıza uyarlayacağınız tek bir ilke belirleyin.",
            deliverableHint = "Kendi Düşüncelerim alanına kaydedin."
        ),
        references = listOf(
            AcademicReference("İbn Rüşd, Faslü'l-Makâl (Felsefe–Din İlişkileri), Dergâh / İş Bankası", ReferenceType.BIRINCIL_TARIHSEL, "Akıl-vahiy ilişkisinde klasik başyapıt."),
            AcademicReference("Gazali, El-Munkız mine'd-Dalâl (Dalâletten Hidayete)", ReferenceType.BIRINCIL_TARIHSEL, "Gazali'nin epistemolojik ve manevi arayış otobiyografisi.")
        )
    ),

    // ======================== AY 12 — DİSİPLİNLER ARASI DÜŞÜNME VE SENTEZ ========================
    Lesson(
        id = "m12_l01",
        monthNumber = 12,
        weekNumber = 1,
        discipline = DisciplineId.FELSEFE,
        title = "Disiplinler Arası Düşünme: Zihinsel Modellerin Birleşimi",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "11 ay boyunca öğrenilen sekiz disiplinin temel kavramlarını tek bir soruna (ör. iklim krizi, YZ dönüşümü veya ekonomik eşitsizlik) aynı anda uygulayabilme becerisi kazandırmak.",
        learningOutcomes = listOf(
            "Analiz ve Sentez: Bir güncel meseleyi en az 5 farklı disiplinin kavram setiyle analiz eder.",
            "Uygulama: Tek boyutlu açıklamalar yerine çok disiplinli nedensellik haritası çizer."
        ),
        prerequisites = listOf("Ay 1–11 temel okuryazarlığı."),
        keyConcepts = listOf(
            ConceptCard("Zihinsel Modeller Kafesi", "Farklı disiplinlerin temel yasalarını zihinde birbirine bağlayarak tek gözle değil 360 derece görme.", "Sadece iktisatçı gibi bakan her şeyi fiyat sanır; sentezci tarih, psikoloji ve etiği de görür."),
            ConceptCard("Sistem Düşüncesi", "Parçaları tek tek değil aralarındaki geri bildirim ve gecikmeli etkilerle değerlendirme.", "Bir tarım sübvansiyonunun hem bütçeye hem ekolojiye hem kentleşmeye etkisini görmek.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Neden Tek Disiplin Yetmez?",
                "Gerçek dünya sorunları üniversite bölümlerine göre bölünmemiştir. Örneğin 'Yapay zekâ ve iş gücü' konusunu ele alalım: Teknoloji algoritmayı açıklar; Ekonomi verimlilik ve gelir dağılımını ölçer; Tarih Sanayi Devrimi'nden ders çıkarır; Psikoloji belirsizlik kaygısını ve öğrenme adaptasyonunu inceler; Felsefe ve Din/Ahlak ise insan onuru ve adalet ölçütünü koyar; Edebiyat ve Sanat ise bu dönüşümün insan ruhundaki karşılığını anlatır."
            ),
            ContentSection(
                "Entelektüel Bağımsızlık",
                "Bu akademinin nihai hedefi çok bilgi ezberlemek değil, yeni bir sorunla karşılaştığınızda kendi başınıza doğru soruları sorabilen, kanıt arayabilen, kaynakları ayıklayabilen ve özgün senteze ulaşabilen bağımsız bir düşünür olmanızdır."
            )
        ),
        historicalBackground = "Antikite'nin 'polimat' (çok yönlü bilge) geleneğinden Farabi, Biruni ve Leonardo da Vinci'ye uzanan bütüncül bilgi ideali.",
        realLifeExamples = listOf(
            "Bir kamu politikasını yalnızca maliyetle değil, psikolojik teşvikler, tarihsel emsaller ve etik sonuçlarla değerlendirmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Sekiz Disiplin Sentezi", "Bütüncül Bilgelik", "Sokrates'in sorgulaması, Kahneman'ın dikkati, Popper'ın kanıtı, İbn Haldun'un umranı, Acemoğlu'nun kurumları, Yaşar Kemal'in anlatısı, Turing'in algoritması ve İbn Rüşd'ün uyumu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Düşünme Biçimleri",
                "Tünel Görüşü", "Karmaşık sorunu tek bir disiplinin veya sloganın kalıbına sıkıştırır.",
                "Polimat / Sentezci Bakış", "Sekiz farklı pencereden kanıt toplayıp tutarlı bir yargı inşa eder."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("8 Disiplin", "besler", "Bütüncül Yargı"),
            ConceptRelation("Sistem Düşüncesi", "önler", "Tek Boyutlu Hata")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Kentte Yapay Zekâ ve Su Krizi",
            scenario = "Büyük bir metropolde veri merkezlerinin artan enerji-su tüketimi ile istihdam ve enerji faturaları arasında gerilim doğar.",
            analysisQuestions = listOf(
                "Bu sorunu Bilim/Ekoloji, Ekonomi, Teknoloji ve Etik açısından nasıl dengelersiniz?"
            ),
            keyTakeaway = "Kalıcı çözüm yalnızca teknik veya yalnızca ekonomik değildir; ekolojik sınırlar, kurumsal şeffaflık ve toplumsal adaleti birlikte gerektirir."
        ),
        reflectionQuestions = listOf(
            "12 ay önceye kıyasla bir haberi veya kitabı okurken zihninizde hangi yeni sorular kendiliğinden uyanıyor?"
        ),
        summary = "Disiplinler arası düşünme, sekiz bilgi alanını bağımsız çekmeceler olarak değil birbirini aydınlatan tek bir zihinsel ağ olarak kullanmaktır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Zihinsel modeller kafesi yaklaşımı neden tek uzmanlıktan üstündür?", "Gerçek dünya problemleri çok boyutlu olduğu için tek alana sıkışan analiz kör noktalar üretir.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m12_l01_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "'Sosyal medya algoritmalarının gençler üzerindeki etkisi' konusunu en az dört farklı disiplinin (Psikoloji, Ekonomi, Teknoloji, Felsefe/Etik) kavramlarını kullanarak analiz edin.",
                explanation = "Ekonomide dikkat ekonomisi, psikolojide dopamin/ödül döngüsü, teknolojide öneri algoritması, etikte özerklik ve sorumluluk kavramları sentezlenmelidir.",
                rubricHint = "En az 4 disiplinden somut kavramlar tutarlı biçimde bağlanmalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "8 Pencere Egzersizi",
            instructions = "Gündemdeki tek bir büyük konuyu seçin ve 8 disiplinin her biri için o konuya dair 1 temel soru yazın.",
            deliverableHint = "Bitirme projenizin iskeleti olacak!"
        ),
        references = listOf(
            AcademicReference("Charlie Munger & Modern Zihinsel Modeller Literatürü", ReferenceType.AKADEMIK_KITAP, "Disiplinler arası zihinsel modeller yaklaşımı.")
        )
    ),
    Lesson(
        id = "m12_l02",
        monthNumber = 12,
        weekNumber = 2,
        discipline = DisciplineId.BILIM,
        title = "Bağımsız Öğrenme, Bilgi Ekosistemi ve Kaynak Doğrulama",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "12 aylık program bittikten sonra da kullanıcının ömür boyu kendi kendini eğitebilmesi için kişisel bilgi yönetimi, araştırma ve okuma sistemi kurmasını sağlamak.",
        learningOutcomes = listOf(
            "Uygulama: Kişisel okuma programı, aktif not alma (Zettelkasten / Kendi Bilgi Bankam) ve aralıklı tekrar düzenini sürdürür."
        ),
        prerequisites = listOf("Ders 34."),
        keyConcepts = listOf(
            ConceptCard("Kişisel Bilgi Bankası", "Okunan her kaynaktan pasif alıntı yığmak yerine kendi cümleleriyle kalıcı fikir notları ve bağlantılar üretmek.", "Bu uygulamadaki 'Not Defteri' ve 'Kendi Düşüncelerim' arşivini ömürlük kullanmak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Mezuniyet Yok, Sürekli Derinleşme Var",
                "Gerçek entelektüel gelişim bir sertifika günüyle bitmez. İyi bir okuyucu her yıl kendi merak sorularını belirler, birincil kaynaklara gider, hakemli veriyi popüler iddiadan ayırır ve yazdıkça düşüncesini berraklaştırır."
            )
        ),
        historicalBackground = "Rönesans ortak yer defterlerinden (commonplace books) modern Zettelkasten bilgi ağlarına uzanan kişisel araştırma geleneği.",
        realLifeExamples = listOf(
            "Her ay bir temel kitap + bir eleştirel karşıt görüş okuma rutini."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Fuat Sezgin & Karl Popper", "Ömür Boyu Araştırma", "Disiplinli arşiv çalışması ve sürekli yanılabilirlik bilinci.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Bilgi Toplama",
                "Enformasyon Yığma", "Binlerce yer imi kaydedip hiç dönüp bakmamak.",
                "Bilgi Sentezleme", "Az ama öz notu kendi sözcüklerinle yazıp kavramlar arası köprü kurmak."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Birincil Kaynak + Kendi Notun", "üretir", "Bağımsız Düşünce")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Bilgi Obezitesi",
            scenario = "Kullanıcı günde 40 kısa video ve özet okuyor ama yıl sonunda tek bir konuyu derinlemesine açıklayamıyor.",
            analysisQuestions = listOf(
                "Derinliksiz hızın panzehri olan 'yavaş okuma + aktif yazma' alışkanlığı nasıl korunur?"
            ),
            keyTakeaway = "Az sayıda nitelikli kaynağı sindirmek, yüzlerce yüzeysel özetten katbekat değerlidir."
        ),
        reflectionQuestions = listOf(
            "Önümüzdeki 12 ayda derinleşmek istediğiniz ana okuma temanız ne olacak?"
        ),
        summary = "Bağımsız öğrenen kişi; güvenilir kaynak seçer, aktif not alır, düzenli tekrar yapar ve öğrendiklerini yazıyla sınar.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Pasif alıntı ile aktif bilgi notu arasındaki fark nedir?", "Aktif not, fikri kendi cümlelerinle yeniden kurup diğer kavramlarla ilişkilendirir.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m12_l02_q1",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Kişisel bilgi bankasında amaç en çok alıntıyı kopyalamak değil, fikirleri kendi cümlelerimizle yeniden üretip ilişkilendirmektir.",
                correctBool = true,
                explanation = "Doğru. Kendi cümlemizle yazmadığımız ve bağlam kurmadığımız notlar kalıcı öğrenmeye dönüşmez."
            )
        ),
        practiceTask = PracticeTask(
            title = "Gelecek Yıl Okuma Listesi",
            instructions = "8 disiplinden en çok derinleşmek istediğiniz 3 alanı seçip her biri için 1 temel kitap belirleyin.",
            deliverableHint = "Notlarım bölümünde '2. Yıl Yol Haritam' başlığıyla saklayın."
        ),
        references = listOf(
            AcademicReference("Sönke Ahrens, How to Take Smart Notes (Akıllı Notlar)", ReferenceType.AKADEMIK_KITAP, "Kişisel bilgi bankası ve düşünce geliştirme yöntemi.")
        )
    ),
    Lesson(
        id = "m12_l03",
        monthNumber = 12,
        weekNumber = 4,
        discipline = DisciplineId.DIN_AHLAK,
        title = "Bitirme Projesi: Sekiz Disiplin Büyük Sentez Manifestosu",
        durationMinutes = 40,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Kullanıcının 12 aylık yolculuğunu taçlandıran özgün bir disiplinler arası bitirme analizi üretmesini ve kendi entelektüel gelişimini ölçmesini sağlamak.",
        learningOutcomes = listOf(
            "Analiz ve Sentez: Sekiz disiplinin tamamından en az birer temel ilkeyi tutarlı bir bitirme tezinde birleştirir."
        ),
        prerequisites = listOf("Tüm 12 aylık müfredat."),
        keyConcepts = listOf(
            ConceptCard("Entelektüel Sentez", "Bilgiyi parçalı kutulardan çıkarıp yaşam, toplum ve hakikat karşısında bütüncül bir duruşa dönüştürmek.", "Bitirme teziniz bu akademinin en değerli ürünüdür.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Büyük Bitirme Görevi Yönergesi",
                "Bitirme çalışmanız için aşağıdaki üç güncel insanlık meselesinden birini seçin: (A) Yapay Zekâ Çağında İnsanın Değeri ve Özgürlüğü, (B) İklim, Ekonomi ve Ahlaki Sorumluluk, (C) Küresel Bilgi Çağında Hakikat ve Medeniyet Diyaloğu. Seçtiğiniz meseleyi Felsefe, Psikoloji, Bilim, Tarih, Ekonomi, Edebiyat/Sanat, Teknoloji ve Din/Ahlak perspektiflerinden inceleyen yapılandırılmış bir analiz hazırlayın."
            )
        ),
        historicalBackground = "12 aylık sistematik okuryazarlık programının final sentezi.",
        realLifeExamples = listOf(
            "Kendi düşüncelerim defterinizdeki Ay 1 notlarınızla Ay 12 bitirme metninizi karşılaştırarak zihinsel evriminizi görmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Tüm Akademi Mütefekkirleri", "12 Aylık Miras", "İnsanlık düşüncesinin ortak zirveleri.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Yolculuğun Başlangıcı vs Sonu",
                "1. Ay", "Parçalı bilgiler ve sorgulanmamış kabuller.",
                "12. Ay", "Kanıt arayan, kaynak ayıran, disiplinler arası köprü kuran bağımsız zihin."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("8 Disiplin Okuryazarlığı", "taçlanır", "Özgün Bitirme Sentezi")
        ),
        caseStudy = CaseStudy(
            title = "Bitirme Jürisi: Yapay Zekâ Öğretmeni ile Savunma",
            scenario = "Bitirme metninizi yazdıktan sonra Öğretmen sekmesinde 'Disiplinler Arası Analiz' modunu seçip tezinizi savunun.",
            analysisQuestions = listOf(
                "Tezinizdeki en zayıf varsayım nedir ve karşıt bir görüşe nasıl yanıt verirsiniz?"
            ),
            keyTakeaway = "Gerçek ustalık, kendi tezine yöneltilen en zor soruyu memnuniyetle karşılayabilmektir."
        ),
        reflectionQuestions = listOf(
            "Bu 12 aylık akademide sizi en çok şaşırtan veya dönüştüren kavram hangisi oldu?"
        ),
        summary = "Bitirme projesi, sekiz disiplindeki birikimi gerçek dünyanın büyük bir sorusuna uygulayarak bağımsız düşünme yetkinliğinizi belgelediğiniz final adımıdır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Öğrenmenin 4 seviyesi nelerdir?", "1) Tanıma, 2) Anlama, 3) Uygulama, 4) Analiz ve Sentez.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m12_l03_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Bitirme Tezi Özeti: 'İnsanın 21. Yüzyılda Anlam ve Özgürlük Arayışı' başlığını Felsefe, Psikoloji, Ekonomi, Teknoloji/YZ ve Din/Ahlak disiplinlerinden en az 4'ünü sentezleyerek 6–10 cümlede yazın.",
                explanation = "Yapay zekâ değerlendirmesi kavramsal doğruluk, mantıksal tutarlılık, gerekçelendirme, örnek kullanımı ve disiplinler arası derinliğe göre puanlayacaktır.",
                rubricHint = "Çoklu disiplin sentezi ve özgün muhakeme beklenir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Bitirme Manifestosu Kaydı",
            instructions = "12 aylık yolculuğunuzu özetleyen bitirme yazınızı 'Kendi Düşüncelerim' kategorisinde kalıcı olarak yayınlayın ve Öğretmen sekmesinde geri bildirim alın.",
            deliverableHint = "Tebrikler! Tüm akademi yol haritasını tamamlamaya hazırsınız."
        ),
        references = listOf(
            AcademicReference("Entelektüel Gelişim Akademisi 12 Aylık Çekirdek Kaynakçası", ReferenceType.UNIVERSITE_YAYINI, "8 disiplinin tüm birincil ve akademik eserleri.")
        )
    )
)
