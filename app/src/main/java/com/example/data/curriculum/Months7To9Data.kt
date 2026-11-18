package com.example.data.curriculum

import com.example.data.model.*

val months7To9Lessons: List<Lesson> = listOf(
    // ======================== AY 7 — EDEBİYATI OKUMAK VE YORUMLAMAK (EDEBİYAT) ========================
    Lesson(
        id = "m07_l01",
        monthNumber = 7,
        weekNumber = 1,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Edebiyatın Doğası, Türler, Anlatıcı ve Karakter Çözümlemesi",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Edebî metni yalnızca 'olay özeti' olarak okumaktan çıkıp anlatıcı, bakış açısı, karakter psikolojisi, tema ve sembol katmanlarıyla çözümlemeyi öğretmek.",
        learningOutcomes = listOf(
            "Tanıma: Roman, öykü, şiir, deneme ve tiyatro türlerinin yapısal özelliklerini tanımlar.",
            "Anlama: Güvenilmez anlatıcı, bilinç akışı ve bakış açısının anlamı nasıl kurduğunu açıklar.",
            "Uygulama: Bir roman veya öykü parçasını karakter motivasyonu ve semboller üzerinden analiz eder."
        ),
        prerequisites = listOf("Ay 2 Psikoloji (motivasyon ve empati) ve Ay 1 Metin Analizi."),
        keyConcepts = listOf(
            ConceptCard("Anlatıcı ve Bakış Açısı", "Hikâyeyi kimin, hangi bilgi sınırıyla aktardığı (hâkim, kahraman, gözlemci, güvenilmez anlatıcı).", "Bir olayı sanığın değil de önyargılı tanığın ağzından dinlemek anlamı tamamen değiştirir."),
            ConceptCard("Tema ve Sembol", "Metnin yüzey olayının altındaki temel mesele (tema) ve soyut anlam taşıyan somut imge (sembol).", "Dostoyevski'de dar ve karanlık odaların zihinsel sıkışmayı simgelemesi."),
            ConceptCard("Bilinç Akışı", "Karakterin iç düşüncelerini mantık sırası veya noktalama düzeni olmadan çağrışımlarla aktarma tekniği.", "Virginia Woolf'un Mrs. Dalloway romanında bir saatin zihinde yılları uyandırması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Edebiyat Nedir? Dilin Estetik ve İnsani İmkanı",
                "Tarih 'ne olduğunu', psikoloji 'zihnin nasıl işlediğini' genel yasalarla anlatır; edebiyat ise bir tek insanın iç dünyasını, çelişkisini ve tarihin birey üzerindeki ağırlığını doğrudan yaşatır. Roman, öykü, şiir, deneme ve tiyatro; insanlık durumunun farklı ölçek ve ritimlerdeki laboratuvarlarıdır."
            ),
            ContentSection(
                "Karakter Analizi ve Anlatı Mimarisi",
                "Tolstoy ve Dostoyevski gibi klasik ustalar karakterleri 'iyi-kötü' kartonlarına bölmez; aynı kişinin içindeki vicdan, kibir, merhamet ve korku çatışmasını işler. Modern edebiyatta Virginia Woolf ve Franz Kafka ise zamanın çizgiselliğini kırarak yabancılaşmayı ve içsel bilinci merkeze taşır."
            )
        ),
        historicalBackground = "Cervantes'in Don Kişot'u ile modern romanın doğuşundan Shakespeare trajedilerine, 19. yüzyıl Rus gerçekçiliğinden 20. yüzyıl modernizmine uzanan edebî dönüşüm.",
        realLifeExamples = listOf(
            "Bir haber veya hikâye okurken 'Anlatıcı neyi saklıyor, kimin perspektifi eksik?' sorusunu sormak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Cervantes", "1547–1616", "Don Kişot ile gerçeklik ve hayal çatışmasını işleyen modern romanın öncüsü."),
            ThinkerHighlight("Dostoyevski", "1821–1881", "Suç, vicdan, özgürlük ve insan ruhunun derin çelişkilerinin romancısı."),
            ThinkerHighlight("Tolstoy", "1828–1910", "Tarihsel akış içinde bireysel ahlak ve yaşam anlamını işleyen usta."),
            ThinkerHighlight("Virginia Woolf", "1882–1941", "Bilinç akışı tekniği ve iç zamanın modernist anlatıcısı.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Anlatı Teknikleri",
                "Hâkim (Tanrısal) Bakış", "Tüm karakterlerin içini ve geleceği bilen klasik anlatıcı.",
                "Güvenilmez / İçsel Anlatıcı", "Okuru karakterin öznel algısı ve yanılsamalarıyla baş başa bırakır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Anlatıcı Seçimi", "belirler", "Okurun Empati Mesafesi"),
            ConceptRelation("Sembol", "taşır", "Örtük Tema")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Güvenilmez Anlatıcıyı Çözmek",
            scenario = "Bir öyküde anlatıcı sürekli 'Ben son derece sakin ve mantıklı biriyim, komşularım ise sebepsiz yere bana düşman' demekte; ancak anlattığı her olayda kendisi kavgayı başlatmaktadır.",
            analysisQuestions = listOf(
                "Yazar anlatıcının sözleri ile eylemleri arasındaki çelişkiyi neden kurgulamıştır?"
            ),
            keyTakeaway = "İyi okur anlatıcının iddia ettiğini değil, metnin gösterdiği bütünlüğü okur."
        ),
        reflectionQuestions = listOf(
            "Şimdiye kadar okuduğunuz bir eserde sizi en çok dönüştüren karakter hangisiydi ve nedeni neydi?"
        ),
        summary = "Edebiyat okuryazarlığı olay özetinin ötesine geçip anlatıcı, karakter psikolojisi, sembol ve dil estetiğini çözümlemektir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Bilinç akışı tekniği neyi amaçlar?", "Karakterin iç konuşma ve çağrışımlarını doğrudan, filtresiz ve zaman sıçramalı biçimde yansıtmayı.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m07_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Karakterin zihninden geçen dağınık, çağrışımsal düşünceleri mantık süzgeci olmadan aktaran anlatı tekniğine ne ad verilir?",
                options = listOf("Manzum hikâye", "Bilinç akışı", "Gözlemci bakış açısı", "Epik anlatım"),
                correctOptionIndex = 1,
                explanation = "Virginia Woolf ve James Joyce gibi modernist yazarların ustalaştığı bilinç akışı tekniği içsel çağrışımları doğrudan aktarır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Kısa Metin Katman Çözümlemesi",
            instructions = "Sevdiğiniz bir öykü veya romanın ilk 2 sayfasını açın; anlatıcı türünü, hâkim duyguyu ve tekrarlanan bir sembolü not edin.",
            deliverableHint = "Notlarım > Edebiyat Çözümlemeleri kategorisine ekleyin."
        ),
        references = listOf(
            AcademicReference("Berna Moran, Edebiyat Kuramları ve Eleştiri, İletişim Yayınları", ReferenceType.AKADEMIK_KITAP, "Türkçe edebiyat eleştirisinin temel başvuru kitabı.")
        )
    ),
    Lesson(
        id = "m07_l02",
        monthNumber = 7,
        weekNumber = 2,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Edebî Akımlar, Dünya Edebiyatı ve Türk Edebiyatı",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Klasik edebiyattan romantizm, gerçekçilik, varoluşçuluk ve büyülü gerçekçiliğe uzanan akımları; Shakespeare, Kafka, Camus, Orwell, Márquez ile Yaşar Kemal ve Sabahattin Ali üzerinden incelemek.",
        learningOutcomes = listOf(
            "Tanıma: Klasisizm, realizm, varoluşçuluk, distopya ve büyülü gerçekçilik akımlarını tanır.",
            "Anlama: Türk edebiyatında toplumsal gerçekçilik (Sabahattin Ali, Yaşar Kemal) ile dünya edebiyatı arasındaki tematik paralellikleri açıklar.",
            "Analiz ve Sentez: Bir edebî eserin yazıldığı tarihsel ve toplumsal bağlamla ilişkisini kurar."
        ),
        prerequisites = listOf("Ders 19 ve Ay 5 Modern Dünya Tarihi."),
        keyConcepts = listOf(
            ConceptCard("Yabancılaşma ve Absürt (Kafka & Camus)", "Modern bürokrasi, anlamsızlık ve yalnızlık içinde bireyin varoluş sancısı.", "Kafka'nın Dönüşüm'ünde Gregor Samsa'nın üretkenliğini kaybedince dışlanması."),
            ConceptCard("Distopya (George Orwell)", "Otoriter iktidarın dili, hakikati ve belleği manipüle ettiği karanlık gelecek tasarımı.", "1984'te 'Yenisöylem' ile düşünce sınırlarının daraltılması."),
            ConceptCard("Anadolu Gerçekçiliği ve Sözlü Destan", "Sabahattin Ali'nin insan psikolojisi ve toplumsal adaletsizlik çözümlemeleri; Yaşar Kemal'in doğa-insan-destan dili.", "Kürk Mantolu Madonna ve İnce Memed geleneği.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Dünya Edebiyatında Büyük Kırılmalar",
                "Shakespeare insan iktidarını ve ihtirası sahneye taşırken, 19. yüzyıl realizmi toplumsal sınıfları gerçekçi ayrıntılarla inceledi. 20. yüzyılda Franz Kafka bürokratik yabancılaşmayı, Albert Camus absürt varoluş ve başkaldırıyı, George Orwell totaliter dil manipülasyonunu, Gabriel García Márquez ise Latin Amerika tarihini büyülü gerçekçilikle anlattı."
            ),
            ContentSection(
                "Türk Edebiyatının Evrensel Sesi",
                "Türk edebiyatı Tanzimat'tan Cumhuriyet'e uzanan süreçte hem yerli sözlü kültürü hem dünya roman formunu harmanladı. Sabahattin Ali, bireyin kırılgan iç dünyasını toplumsal baskılarla birlikte işledi. Yaşar Kemal ise Çukurova'nın doğasını, eşkıya-ağa düzenini ve halk anlatılarını evrensel bir epik dile dönüştürdü."
            )
        ),
        historicalBackground = "16. yüzyıl Rönesans tiyatrosundan 20. yüzyıl distopyalarına ve Cumhuriyet dönemi Türk romancılığına uzanan edebiyat atlası.",
        realLifeExamples = listOf(
            "Orwell'in 1984 eserindeki 'çiftdüşün' kavramını bugünün bilgi kirliliği ve propaganda analizinde kullanmak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("William Shakespeare", "1564–1616", "İktidar, kıskançlık ve insan doğasının trajik çözümleyicisi."),
            ThinkerHighlight("Franz Kafka", "1883–1924", "Modern bireyin bürokrasi ve aile karşısındaki yabancılaşmasının yazarı."),
            ThinkerHighlight("George Orwell", "1903–1950", "Dil, propaganda ve totalitarizm eleştirisinin ustası."),
            ThinkerHighlight("Yaşar Kemal", "1923–2015", "Anadolu doğasını ve toplumsal adalet arayışını evrensel destana dönüştüren yazar."),
            ThinkerHighlight("Sabahattin Ali", "1907–1948", "Türk öykü ve romanında insan ruhunun ve toplumun incelikli gözlemcisi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "İki 20. Yüzyıl Yaklaşımı",
                "Varoluşçu Edebiyat (Camus)", "İnsanın anlamdan yoksun dünyada kendi ahlaki duruşunu seçmesi.",
                "Büyülü Gerçekçilik (Márquez)", "Olağanüstü olayların tarihsel gerçeklikle sıradan bir doğallıkta birleşmesi."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Dil Manipülasyonu", "doğurur", "Düşünce Daralması (Orwell)"),
            ConceptRelation("Anadolu Sözlü Geleneği", "besler", "Yaşar Kemal Destanı")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: 'Yenisöylem' ve Kelimelerin Eksilmesi",
            scenario = "Orwell'in 1984 romanında rejim, sözlükten kelimeleri eksilterek bazı eleştirel kavramların düşünülmesini bile zorlaştırmayı hedefler.",
            analysisQuestions = listOf(
                "Kelime dağarcığı ile eleştirel düşünme kapasitesi arasında nasıl bir ilişki vardır?"
            ),
            keyTakeaway = "Kavramlarımız ne kadar zengin ve keskinse düşünme ufkumuz da o kadar geniştir."
        ),
        reflectionQuestions = listOf(
            "Türk ve dünya edebiyatından önümüzdeki 3 ayda mutlaka okuyacağınız 2 klasik eser hangisi olur?"
        ),
        summary = "Dünya ve Türk edebiyatı; iktidar, yabancılaşma, özgürlük, adalet ve doğa-insan ilişkisini tarihsel akımlar içinde farklı biçimlerle işlemiştir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Büyülü gerçekçilik nedir?", "Gerçekçi bir toplumsal arka plan içine olağanüstü unsurların şaşkınlık uyandırmadan doğal bir parça gibi örüldüğü anlatı biçimi.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m07_l02_q1",
                type = QuestionType.MATCHING,
                competency = CompetencyType.BILGI,
                prompt = "Yazarları temsil ettikleri temel tema veya akımla eşleştirin:",
                matchingPairs = listOf(
                    "George Orwell" to "Totaliter Dil ve Distopya",
                    "Gabriel García Márquez" to "Büyülü Gerçekçilik",
                    "Sabahattin Ali" to "İçsel Kırılganlık ve Toplumsal Gerçekçilik"
                ),
                explanation = "Orwell 1984 ile distopyanın, Márquez Yüzyıllık Yalnızlık ile büyülü gerçekçiliğin, Sabahattin Ali ise Türk toplumcu gerçekçi psikolojisinin öncüsüdür."
            )
        ),
        practiceTask = PracticeTask(
            title = "Karşılaştırmalı Eser Okuması",
            instructions = "Sabahattin Ali'den bir öykü ile Kafka veya Camus'den kısa bir metni okuyup 'yalnızlık' temasını nasıl ele aldıklarını karşılaştırın.",
            deliverableHint = "Not defterinizde 2 sütunlu kısa özet çıkarın."
        ),
        references = listOf(
            AcademicReference("Berna Moran, Türk Romanına Eleştirel Bir Bakış (3 Cilt), İletişim Yayınları", ReferenceType.AKADEMIK_KITAP, "Türk romanının yapısal ve tarihsel analizi.")
        )
    ),
    Lesson(
        id = "m07_l03",
        monthNumber = 7,
        weekNumber = 4,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Eleştirel Okuma Atölyesi ve Estetik Yorum",
        durationMinutes = 15,
        lengthCategory = LessonLength.KISA,
        purpose = "Okur olarak metnin yalnızca tüketicisi değil eleştirel yorumlayıcısı olmayı; çok katmanlı okuma pratiğini yerleştirmek.",
        learningOutcomes = listOf(
            "Uygulama: Bir metni düz anlam, yan anlam ve tarihsel alt metin olmak üzere üç düzeyde okur."
        ),
        prerequisites = listOf("Ders 19 ve 20."),
        keyConcepts = listOf(
            ConceptCard("Üç Katmanlı Okuma", "1) Ne oldu? (Düz anlam), 2) Nasıl anlatıldı? (Biçim), 3) Neyi sorguluyor? (Derin anlam).", "Bir romanı yalnızca olay örgüsü için değil üslup ve değer dünyası için okumak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Aktif Okurluk Pratiği",
                "İyi okur metinle diyaloğa girer: Kenar notları alır, karakterin seçimlerini etik açıdan tartar, yazarın seçtiği sıfatların ve zaman atlamalarının nedenini sorgular."
            )
        ),
        historicalBackground = "20. yüzyıl alımlama estetiği, anlamın yalnızca yazarın niyetinde değil metin ile okurun buluşmasında doğduğunu göstermiştir.",
        realLifeExamples = listOf(
            "Okuduğunuz her kitap için 1 sayfalık kişisel değerlendirme kartı hazırlamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Albert Camus", "1913–1960", "Felsefi düşünceyi edebî anlatıyla birleştiren deneme ve roman yazarı.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Okuma Modları",
                "Tüketici Okuma", "Olay bittiğinde kitabı kapatır ve hızla unutur.",
                "Eleştirel Okuma", "Metni başka kitaplarla, tarihle ve kendi yaşamıyla ilişkilendirir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Metin + Okur Soruları", "üretir", "Derin Yorum")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Deneme Türünün Gücü",
            scenario = "Montaigne'den Camus ve Sabahattin Eyüboğlu'na deneme türü, kesin hüküm vermeden düşünme sürecini okurla paylaşır.",
            analysisQuestions = listOf(
                "Deneme okumak kendi düşünce yazılarımızı nasıl geliştirir?"
            ),
            keyTakeaway = "Deneme, entelektüel dürüstlüğün ve esnek zihnin edebî formudur."
        ),
        reflectionQuestions = listOf(
            "Okuduğunuz son kitap size hangi yeni soruyu bıraktı?"
        ),
        summary = "Eleştirel okuma metni üç katmanda inceleyerek okuru pasif tüketiciden aktif anlam ortağına dönüştürür.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Üç katmanlı okumanın üç sorusu nedir?", "Ne anlatılıyor? Nasıl anlatılıyor? Neyi sorguluyor?")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m07_l03_q1",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.UYGULAMA,
                prompt = "Seçtiğiniz bir roman veya öyküyü 'Ne oldu? / Nasıl anlatıldı? / Neyi sorguluyor?' üçlüsüyle 4–5 cümlede değerlendirin.",
                explanation = "Üç düzeyin her birine somut örnek verilmesi beklenir.",
                rubricHint = "Olay, biçim/anlatıcı ve tema katmanları ayrıştırılmalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "İlk Kitap Değerlendirme Kartı",
            instructions = "Son okuduğunuz kitabın başlığı, yazarı, ana sorusu ve size kalan tek cümlesini Notlarım'a ekleyin.",
            deliverableHint = "'Kitap Kartı' etiketini kullanın."
        ),
        references = listOf(
            AcademicReference("Mortimer Adler & Charles Van Doren, Kitapları Nasıl Okumalı?, İthaki / Say", ReferenceType.AKADEMIK_KITAP, "Analitik ve sentopik okuma rehberi.")
        )
    ),

    // ======================== AY 8 — SANATI VE KÜLTÜRÜ ANLAMAK (SANAT) ========================
    Lesson(
        id = "m08_l01",
        monthNumber = 8,
        weekNumber = 1,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Sanat Tarihi, Resim, Heykel ve Estetik Değerlendirme",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Mağara resimlerinden Klasik heykele, İslam hat/tezhibinden Rönesans perspektifine ve modern soyut sanata kadar görsel sanatların dilini okumayı öğretmek.",
        learningOutcomes = listOf(
            "Tanıma: Perspektif, ışık-gölge (chiaroscuro), kompozisyon ve soyutlama kavramlarını tanımlar.",
            "Anlama: Fotoğrafın icadının resim sanatını neden izlenimcilik ve soyut sanata yönelttiğini açıklar.",
            "Uygulama: Bir tablo veya heykeli form, renk, tarihsel bağlam ve duygu açısından değerlendirir."
        ),
        prerequisites = listOf("Ay 1 Estetik ve Ay 5 Rönesans."),
        keyConcepts = listOf(
            ConceptCard("Çizgisel Perspektif", "İki boyutlu yüzeyde derinlik ve üç boyut yanılsaması yaratan matematiksel çizim sistemi.", "Rönesans'ta mimar ve ressamların göz hizasından ufuk noktası kurması."),
            ConceptCard("Soyutlama ve İzlenimcilik", "Dış dünyayı birebir kopyalamak yerine ışığı, rengi veya öz formu öne çıkaran yaklaşım.", "Fotoğraf makinesi çıkınca ressamların gerçekliği kopyalamak yerine içsel duygu ve ışığı araması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Görsel Dili Okumak: Kompozisyon, Işık ve Mekân",
                "Bir resim ya da heykel tesadüfi değildir; sanatçı izleyicinin gözünü nereye yönlendireceğini kompozisyon, kontrast ve oranla kurgular. Antik Yunan heykeli ideal insan oranını ararken, İslam sanatları geometrik sonsuzluk, hat ve tezhip aracılığıyla figür dışı soyut bir estetik düzen geliştirmiştir. Osmanlı'da minyatür ise perspektif yerine hiyerarşik ve anlatısal bir mekân kullanır."
            ),
            ContentSection(
                "Modern Sanat Neden Değişti?",
                "19. yüzyılda fotoğrafın icadıyla 'gerçeği birebir kaydetme' görevi kameraya geçince resim özgürleşti: İzlenimciler anlık ışığı, Ekspresyonistler içsel kaygıyı, Kübistler aynı nesneye çoklu açıdan bakışı tuvale taşıdı."
            )
        ),
        historicalBackground = "Lascaux'dan Antik Yunan'a, İslam hat sanatından Floransa Rönesansı'na ve 20. yüzyıl avangard akımlarına uzanan görsel tarih.",
        realLifeExamples = listOf(
            "Bir müzede tabloya yalnızca 'güzelmiş' deyip geçmek yerine ışık kaynağını, bakış yönünü ve dönemin teknolojisini incelemek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("E. H. Gombrich", "1909–2001", "Sanatın Öyküsü eseriyle görsel kültür okuryazarlığının en önemli rehberlerinden."),
            ThinkerHighlight("John Berger", "1926–2017", "Görme Biçimleri ile sanat eserinin toplumsal ve ideolojik okumasını yapan eleştirmen.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Mekân Temsili",
                "Rönesans Perspektifi", "Tek bir gözlemci noktasından derinlik yanılsaması kurar.",
                "Minyatür Sanatı", "Tek bir sahnede farklı zamanları ve önem hiyerarşisini düzlemde birleştirir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Fotoğrafın İcadı", "tetikler", "Modern Resimde Soyutlama"),
            ConceptRelation("Matematik ve Oran", "temellendirir", "Perspektif ve Geometri")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Modern Soyut Tablo Karşısında Ön Yargı",
            scenario = "Bir izleyici soyut bir tabloya bakıp 'Bunu beş yaşındaki çocuk da çizer' der.",
            analysisQuestions = listOf(
                "Sanatçının figüratif ustalığı bilerek terk etmesiyle teknik bilmemek arasındaki fark nedir?"
            ),
            keyTakeaway = "Picasso veya Kandinsky gibi ustalar kusursuz akademik çizim yapabildikleri halde yeni ifade dilleri kurmak için soyutlamaya gitmiştir."
        ),
        reflectionQuestions = listOf(
            "Sizi derinden etkileyen bir tablo, heykel veya hat eserinde ilk dikkatinizi çeken unsur nedir?"
        ),
        summary = "Görsel sanatlar tarihsel teknoloji, matematik ve kültürle dönüşür; estetik değerlendirme biçim, teknik ve anlam bağlamını birlikte okumayı gerektirir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Fotoğrafın icadı resim sanatını nasıl dönüştürdü?", "Birebir gerçekçi kopyalama zorunluluğunu kaldırarak izlenimcilik ve soyut sanatın yolunu açtı.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m08_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.ANLAMA,
                prompt = "19. yüzyıl sonunda resim sanatının birebir doğa taklidinden uzaklaşıp ışık ve soyutlamaya yönelmesinde en etkili teknolojik gelişme hangisidir?",
                options = listOf("Buharlı gemi", "Fotoğrafçılığın icadı", "Telgraf", "Pusula"),
                correctOptionIndex = 1,
                explanation = "Fotoğraf gerçeği anlık olarak belgeleyebildiği için ressamlar duygu, ışık ve form arayışına yönelmiştir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Müze / Eser Analiz Notu",
            instructions = "Osman Hamdi Bey'in Kaplumbağa Terbiyecisi veya Vermeer'in bir eserini internetten inceleyip ışık yönünü, renk paletini ve ana mesajını 4 cümlede yazın.",
            deliverableHint = "Notlarım > Sanat Analizi kategorisine ekleyin."
        ),
        references = listOf(
            AcademicReference("E. H. Gombrich, Sanatın Öyküsü, Remzi Kitabevi", ReferenceType.AKADEMIK_KITAP, "Dünya sanat tarihinin temel klasikleri."),
            AcademicReference("John Berger, Görme Biçimleri, Metis Yayınları", ReferenceType.AKADEMIK_KITAP, "Görsel kültür ve sanat eleştirisi.")
        )
    ),
    Lesson(
        id = "m08_l02",
        monthNumber = 8,
        weekNumber = 2,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Mimari, Müzik ve Sinemanın Dili",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Mimaride mekân-ışık ilişkisini (Mimar Sinan'dan modern mimariye), müzikte ritim-armoni-makam yapısını ve sinemada kurgu ile görüntü dilini kavramak.",
        learningOutcomes = listOf(
            "Tanıma: Kubbe taşıyıcı sistemleri, müzikal form/makam ve sinematografik kurgu kavramlarını tanımlar.",
            "Anlama: Mimar Sinan'ın Süleymaniye ve Selimiye'de mühendislik ile estetiği nasıl birleştirdiğini açıklar.",
            "Uygulama: Bir film sahnesini kamera açısı, kurgu ritmi ve ses tasarımıyla çözümler."
        ),
        prerequisites = listOf("Ders 22."),
        keyConcepts = listOf(
            ConceptCard("Mekân ve Işık (Mimari)", "Mimarinin yalnızca duvar örmek değil, insanın içinde yaşadığı ışık, akustik ve ölçek deneyimini tasarlaması.", "Selimiye Camii'nde sekizgen ayaklarla kubbenin mekânı tek bir bütünlükte toplaması."),
            ConceptCard("Kurgu (Montaj)", "İki ayrı çekimin yan yana getirilmesiyle her birinde tek başına olmayan yeni bir anlam üretilmesi (Kuleshov Etkisi).", "Yüz ifadesinin ardından gelen görselin izleyiciye farklı duygu hissettirmesi.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Mimari: Taşın, Işığın ve Mühendisliğin Uyumu",
                "Mimari en kamusal sanattır; içine girer ve içinde yaşarız. Mimar Sinan, Ayasofya'dan aldığı yapısal soruyu Süleymaniye ve Selimiye'de çözerek yan nefleri eritmiş, doğal ışık ve akustik rezonansla bütünleşmiş bir mekân birliği kurmuştur. Modern mimari ise betonarme, cam ve işlevsellik üzerinden kent siluetini dönüştürmüştür."
            ),
            ContentSection(
                "Müzik ve Sinema: Zamanın Sanatları",
                "Müzik zaman içinde akan mimaridir: Batı müziğinde çok seslilik ve armoni, Türk ve Doğu müziğinde ise makamların incelikli seyri ve usul ritimleri duygusal atmosferi kurar. Sinema ise edebiyat, resim, müzik ve tiyatroyu 'kamera hareketi ve kurgu' potasında eriten 20. yüzyıl sentez sanatıdır."
            )
        ),
        historicalBackground = "16. yüzyıl Osmanlı klasik mimarisinden barok/klasik müziğe ve 1895 Lumière Kardeşler sonrası sinema dilinin doğuşuna uzanan estetik evrim.",
        realLifeExamples = listOf(
            "Bir film izlerken müziği kapattığınızda gerilim sahnesinin nasıl değiştiğini test etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Mimar Sinan", "1489–1588", "Klasik Osmanlı mimarisinde akustik, taşıyıcı sistem ve mekân birliğinin zirvesi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Sanat ve Zaman",
                "Resim ve Heykel", "Mekânda eş zamanlı olarak algılanan görsel sanatlar.",
                "Müzik ve Sinema", "Zaman akışı içinde gerilim, ritim ve çözülme kuran sanatlar."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Taşıyıcı Mühendislik", "mümkün kılar", "Mimari Estetik"),
            ConceptRelation("Görüntü + Kurgu + Ses", "oluşturur", "Sinema Dili")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Şehir ve Estetik Kirlilik",
            scenario = "Tarihi dokuya sahip bir kent meydanına çevresiyle orantısız, işlevsiz ve görsel bütünlüğü bozan yüksek bir yapı dikilir.",
            analysisQuestions = listOf(
                "Mimari neden yalnızca mülk sahibini değil tüm kentin ortak psikolojisini ve kültürünü ilgilendirir?"
            ),
            keyTakeaway = "Mimari zorunlu olarak kamusal bir sanattır; iyi mimari insan ölçeğine ve çevreyle uyuma saygı gösterir."
        ),
        reflectionQuestions = listOf(
            "İçine girdiğinizde size huzur veya hayranlık veren bir yapı hangisidir? Neden?"
        ),
        summary = "Mimari mekânı, müzik zamanı, sinema ise görüntü-ses kurgusunu işleyerek insanın estetik algısını genişletir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Kuleshov Etkisi sinemada neyi kanıtlar?", "İzleyicinin anlamı tek bir kareden değil, ardışık iki görüntünün kurgusal birleşiminden çıkardığını.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m08_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Sinemada iki ayrı çekimin yan yana getirilmesiyle izleyicinin zihninde yeni bir anlam veya duygu doğmasını açıklayan deneysel olgu nedir?",
                options = listOf("Kuleshov Etkisi", "Plasebo Etkisi", "Doppler Etkisi", "Sokratik İroni"),
                correctOptionIndex = 0,
                explanation = "Lev Kuleshov aynı yüz ifadesini farklı görsellerle kurgulayarak montajın anlam üretme gücünü göstermiştir."
            )
        ),
        practiceTask = PracticeTask(
            title = "5 Dakikalık Sahne Çözümlemesi",
            instructions = "Sevdiğiniz bir filmin açılış 3 dakikasını seçip kamera açısı, renk tonu ve ses kullanımının hangi duyguyu verdiğini not alın.",
            deliverableHint = "Sinema dili çözümleme pratiği."
        ),
        references = listOf(
            AcademicReference("Doğan Kuban, Sinan'ın Sanatı ve Selimiye, Türkiye İş Bankası Kültür Yayınları", ReferenceType.AKADEMIK_KITAP, "Klasik Türk-İslam mimarisinin mühendislik ve estetik incelemesi.")
        )
    ),
    Lesson(
        id = "m08_l03",
        monthNumber = 8,
        weekNumber = 4,
        discipline = DisciplineId.EDEBIYAT_SANAT,
        title = "Kültür, Estetik Yargı ve Popüler Kültür Eleştirisi",
        durationMinutes = 15,
        lengthCategory = LessonLength.KISA,
        purpose = "Yüksek sanat, halk kültürü ve tüketim/algoritma odaklı popüler kültür arasındaki gerilimi değerlendirmek; özgün estetik beğeni geliştirmek.",
        learningOutcomes = listOf(
            "Analiz ve Sentez: Kitsch, özgünlük ve algoritmik beğeni kalıplarını eleştirel biçimde yorumlar."
        ),
        prerequisites = listOf("Ders 22 ve 23."),
        keyConcepts = listOf(
            ConceptCard("Estetik Mesafe ve Beğeni", "Beğeninin 'hoşuma gitti/gitmedi' anlık tepkisinin ötesinde eğitimle derinleşen bir yargı olması.", "İlk dinleyişte zor gelen bir klasik eserin tekrar dinledikçe katmanlarını açması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Algoritmalar Çağında Özgün Beğeni",
                "Bugün dinlediğimiz müzikleri ve izlediğimiz filmleri çoğu zaman öneri algoritmaları seçiyor. Estetik okuryazarlık, kolay tüketilen ve hemen unutturan içeriklerin ötesinde çaba isteyen ama derin iz bırakan eserlere sabır gösterebilmektir."
            )
        ),
        historicalBackground = "Kant'ın Yargı Gücünün Eleştirisi'nden Walter Benjamin ve Frankfurt Okulu'nun kültür endüstrisi eleştirisine uzanan estetik tartışma.",
        realLifeExamples = listOf(
            "Kısa video akışı yerine haftada bir tam albüm veya klasik film izleyip üzerine düşünmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Immanuel Kant", "Estetik Yargı", "Çıkarsız haz ve evrensel estetik yargı kavramları.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Kültür Tüketimi",
                "Hızlı Tüketim (Kitsch)", "Duyguyu kolayca sömürür, yenilik veya derinlik talep etmez.",
                "Sanatsal Deneyim", "Alışılmış bakışımızı sarsar, dikkat ve yorum emeği ister."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Dikkatli Sabır", "açar", "Estetik Derinlik")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Tek Tip Tasarım",
            scenario = "Dünyanın dört bir yanındaki kafelerin ve şehir merkezlerinin birbirinin tıpatıp aynısı görünmeye başlaması.",
            analysisQuestions = listOf(
                "Kültürel çeşitliliği korumak neden estetik ve tarihsel olarak önemlidir?"
            ),
            keyTakeaway = "Yerel zanaat, dil ve mimari hafıza küresel tekdüzelikten korunmalıdır."
        ),
        reflectionQuestions = listOf(
            "Son zamanlarda algoritmanın önermediği, bizzat araştırıp keşfettiğiniz bir sanat eseri var mı?"
        ),
        summary = "Estetik okuryazarlık, pasif eğlence tüketiminin ötesine geçip sanat eserleriyle bilinçli ve özgün bağ kurmaktır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Sanat ile kolay eğlence arasındaki temel fark nedir?", "Sanat alıcının bakışını dönüştürür ve çaba ister; kolay eğlence mevcut kalıpları onaylayıp hızla tüketilir.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m08_l03_q1",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Estetik değerlendirme yalnızca doğuştan gelen bir yetenektir; bilgi, tarih ve dikkatle geliştirilemez.",
                correctBool = false,
                explanation = "Yanlış. Estetik beğeni ve algı, sanat tarihi bilgisi, dikkatli gözlem ve çoklu eser karşılaştırmasıyla gelişir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Kültür Diyeti Planı",
            instructions = "Önümüzdeki ay izleyeceğiniz 1 nitelikli film, dinleyeceğiniz 1 tam albüm ve gezeceğiniz 1 sanal/fiziksel müze seçin.",
            deliverableHint = "Not defterinize listeleyin."
        ),
        references = listOf(
            AcademicReference("Umberto Eco, Güzelliğin Tarihi, Doğan Kitap", ReferenceType.AKADEMIK_KITAP, "Batı ve dünya estetik anlayışının tarihsel panoraması.")
        )
    ),

    // ======================== AY 9 — TEKNOLOJİYİ ANLAMAK (TEKNOLOJİ & PROGRAMLAMA) ========================
    Lesson(
        id = "m09_l01",
        monthNumber = 9,
        weekNumber = 1,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Bilgisayarın Mantığı, Donanım-Yazılım, İşletim Sistemleri ve İnternet",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Bilgisayarların 0 ve 1'lerle nasıl çalıştığını, donanım-işletim sistemi-yazılım katmanlarını ve internet ağlarının temel protokollerini sıfırdan anlatmak.",
        learningOutcomes = listOf(
            "Tanıma: Bit/bayt, CPU, RAM, depolama, işletim sistemi, IP, DNS ve HTTP/HTTPS kavramlarını tanımlar.",
            "Anlama: Tarayıcıya bir adres yazıldığında isteğin sunucuya nasıl ulaşıp geri döndüğünü adım adım açıklar.",
            "Uygulama: Günlük dijital işlemlerde ağ güvenliği ve parola hijyeni ilkelerini uygular."
        ),
        prerequisites = listOf("Sıfırdan başlangıç için uygundur; teknik ön koşul gerekmez."),
        keyConcepts = listOf(
            ConceptCard("İkili Sistem (Binary)", "Tüm metin, ses ve görüntülerin 0 (kapalı) ve 1 (açık) elektrik durumlarıyla kodlanması.", "8 bit bir araya gelerek 1 bayt oluşturur ve bir karakteri temsil eder."),
            ConceptCard("İşletim Sistemi", "Donanım kaynaklarını (CPU, bellek, disk) yöneten ve uygulamalara güvenli çalışma alanı sunan ana yazılım.", "Android, Linux, Windows ve macOS."),
            ConceptCard("TCP/IP ve DNS", "İnternetin veri paketlerini adresleyip (IP) alan adlarını numaralara çeviren (DNS) iletişim omurgası.", "DNS internetin telefon rehberi gibidir.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Donanımdan Yazılıma: Bilgisayar Nasıl Düşünür?",
                "Bilgisayar sihirli bir kutu değil, çok hızlı anahtarlardan (transistörlerden) oluşan bir mantık makinesidir. CPU (işlemci) komutları yürütür, RAM aktif çalışan verileri geçici ve çok hızlı biçimde tutar, SSD/disk ise kalıcı depolama sağlar. İşletim sistemi, bu kaynakların uygulamalar arasında çakışmadan paylaşılmasını sağlar."
            ),
            ContentSection(
                "İnternet, Ağlar ve Veri Tabanları",
                "İnternet tek bir merkez değil, birbirine fiber kablolar ve yönlendiricilerle bağlı ağların ağıdır. Bir web sayfası açtığınızda veriler küçük paketlere bölünür, DNS üzerinden sunucu IP adresi bulunur ve HTTPS şifrelemesiyle güvenli biçimde cihazınıza taşınır. Veri tabanları ise bu bilgileri düzenli tablolar veya belgeler halinde kalıcı saklar."
            )
        ),
        historicalBackground = "Alan Turing'in teorik hesaplama modelinden John von Neumann mimarisine, Claude Shannon'ın bilgi kuramından Tim Berners-Lee'nin World Wide Web icadına uzanan dijital devrim.",
        realLifeExamples = listOf(
            "Çok sekme açıldığında bilgisayarın yavaşlamasının sebebinin RAM doluluğu olduğunu anlamak.",
            "Adres çubuğunda HTTPS kilidinin veri şifrelemesini garanti ettiğini bilmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Alan Turing", "1912–1954", "Bilgisayar biliminin ve yapay zekâ sorgulamasının kurucusu."),
            ThinkerHighlight("John von Neumann", "1903–1957", "Modern bilgisayar mimarisinin (bellek + işlemci) kuramcısı."),
            ThinkerHighlight("Tim Berners-Lee", "1955–", "World Wide Web (HTTP/HTML) sisteminin mucidi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Bellek Türleri",
                "RAM (Geçici Bellek)", "Çok hızlıdır ama elektrik kesilince veya uygulama kapanınca silinir.",
                "SSD / Disk (Kalıcı)", "Veriyi uzun süre saklar, RAM'e göre daha yavaştır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Transistör (0/1)", "oluşturur", "Mantık Kapıları ve CPU"),
            ConceptRelation("DNS", "çözer", "Alan Adı -> IP Adresi")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Halka Açık Wi-Fi Riski",
            scenario = "Bir kafede şifresiz halka açık Wi-Fi ağına bağlanıp bankacılık işlemi yapıyorsunuz.",
            analysisQuestions = listOf(
                "Aynı ağdaki bir saldırgan hangi verileri görebilir ve HTTPS neden kritiktir?"
            ),
            keyTakeaway = "Şifresiz HTTP trafiği ağda dinlenebilir; hassas işlemlerde mobil veri/VPN ve mutlaka HTTPS kullanılmalıdır."
        ),
        reflectionQuestions = listOf(
            "Kullandığınız uygulamaların arka planda hangi verileri sakladığını ve ilettiğini hiç incelediniz mi?"
        ),
        summary = "Bilgisayarlar ikili mantık ve von Neumann mimarisiyle çalışır; işletim sistemi donanımı yönetir; internet ise paket anahtarlamalı ağlar ve DNS/HTTPS protokolleriyle veriyi taşır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("RAM ile SSD arasındaki temel fark nedir?", "RAM aktif çalışan programlar için hızlı ve geçici bellektir; SSD ise kalıcı depolamadır.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m09_l01_q1",
                type = QuestionType.MATCHING,
                competency = CompetencyType.BILGI,
                prompt = "Temel bilgisayar ve ağ bileşenlerini görevleriyle eşleştirin:",
                matchingPairs = listOf(
                    "CPU" to "Komutları işleyen merkezi işlem birimi",
                    "DNS" to "Alan adını IP adresine çeviren sistem",
                    "RAM" to "Çalışan uygulamalar için hızlı geçici bellek"
                ),
                explanation = "CPU hesaplama yapar, RAM geçici çalışma alanıdır, DNS alan adlarını IP'ye çözümler."
            )
        ),
        practiceTask = PracticeTask(
            title = "Dijital Güvenlik Kontrolü",
            instructions = "Önemli 3 hesabınızda iki faktörlü doğrulamanın (2FA) açık olup olmadığını ve benzersiz parola kullanıp kullanmadığınızı kontrol edin.",
            deliverableHint = "Siber güvenlik ve dijital mahremiyet alışkanlığı."
        ),
        references = listOf(
            AcademicReference("Charles Petzold, Code: Bilgisayar Donanım ve Yazılımının Gizli Dili", ReferenceType.AKADEMIK_KITAP, "Sıfırdan bilgisayar mantığını öğreten klasik eser.")
        )
    ),
    Lesson(
        id = "m09_l02",
        monthNumber = 9,
        weekNumber = 2,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Algoritma Mantığı ve Sıfırdan Python Programlamaya Giriş",
        durationMinutes = 35,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Hiç kod yazmamış kullanıcıya algoritma düşüncesini (sıra, koşul, döngü, fonksiyon) ve Python temellerini anlaşılır örneklerle öğretmek.",
        learningOutcomes = listOf(
            "Tanıma: Değişken, veri tipi, koşul (if/else), döngü (for/while), liste ve fonksiyon kavramlarını tanımlar.",
            "Anlama: Karmaşık bir problemi küçük adımlara (algoritmaya) bölme mantığını açıklar.",
            "Uygulama: Basit bir Python bütçe veya not hesaplama kodunu okuyup mantığını kurar."
        ),
        prerequisites = listOf("Ders 25."),
        keyConcepts = listOf(
            ConceptCard("Algoritma", "Bir problemi çözmek için başı sonu belirli, net ve sonlu adımlar dizisi.", "Harizmi'nin adından gelir; yemek tarifi veya arama algoritması birer algoritmadır."),
            ConceptCard("Değişken ve Koşul (if/else)", "Veriyi isimlendirip saklama (değişken) ve duruma göre karar dalı seçme (koşul).", "harcama > butce ise uyarı göster, değilse kaydet."),
            ConceptCard("Döngü ve Fonksiyon", "Tekrarlanan işi otomatikleştiren yapı (for) ve yeniden kullanılabilir kod bloğu (def).", "30 günün harcamalarını tek tek değil bir for döngüsüyle toplamak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Programlama Nedir? Düşünceyi Açık Adımlara Dökmek",
                "İnsanlar günlük dilde belirsizlikleri sezgiyle doldurur; bilgisayarlar ise harfiyen ne söylenirse onu yapar. Bu yüzden kod yazmak aslında açık ve mantıksal düşünme pratiğidir. Her program dört yapı taşına dayanır: Sıralı işlemler, Koşullar (if/else), Döngüler (for/while) ve Fonksiyonlar (def)."
            ),
            ContentSection(
                "İlk Python Örneklerimiz (Sıfırdan)",
                "Python, İngilizceye yakın sade sözdizimiyle başlangıç için ideal dildir:\n\n" +
                    "gelir = 35000\n" +
                    "giderler = [12000, 6500, 3200, 1800]\n" +
                    "toplam_gider = sum(giderler)\n" +
                    "kalan = gelir - toplam_gider\n" +
                    "if kalan > 0:\n" +
                    "    print(f'Tasarruf kapasitesi: {kalan} TL')\n" +
                    "else:\n" +
                    "    print('Bütçe açığı var, harcamaları gözden geçirin.')\n\n" +
                    "Bu 8 satır, liste, toplama, değişken ve koşullu karar mantığının tamamını gerçek bir kişisel bütçe probleminde birleştirir."
            )
        ),
        historicalBackground = "Adını 9. yüzyıl âlimi Harizmi'den alan algoritma kavramı, Ada Lovelace'ın ilk bilgisayar programı notlarıyla birleşerek modern yazılım mühendisliğine dönüştü.",
        realLifeExamples = listOf(
            "Bir Python betiğiyle aylık harcama listesini okuyup kategori toplamlarını saniyeler içinde hesaplamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Ada Lovelace", "1815–1852", "Tarihin ilk bilgisayar programını yazan ve makinelerin sembol işleyebileceğini öngören öncü.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Problem Çözme",
                "Manuel Hesap", "Her ay 50 satır harcamayı elle toplamak yavaş ve hataya açıktır.",
                "Algoritmik Otomasyon", "Kuralları bir kez fonksiyon olarak yazıp her veri setinde hatasız çalıştırmak."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Sorun Parçalama", "üretir", "Algoritma Adımları"),
            ConceptRelation("Koşul + Döngü", "oluşturur", "Program Mantığı")
        ),
        caseStudy = CaseStudy(
            title = "Öğrenme Projesi 1 & 2: Kişisel Bütçe ve Veri Tablosu Analizi",
            scenario = "Aylık gider kalemlerini (kira, gıda, ulaşım, eğitim) bir sözlük (dict) içinde tutup en yüksek gider kategorisini bulan bir fonksiyon tasarlamak istiyoruz.",
            analysisQuestions = listOf(
                "Hangi veri yapısı kategori adı ile tutarı eşleştirmek için en uygundur? (Sözlük / dict)",
                "En büyük harcamayı bulmak için döngüde nasıl bir karşılaştırma değişkeni tutulur?"
            ),
            keyTakeaway = "Basit bir Python sözlüğü ve döngü ile kendi kişisel bütçe ve tablo analiz aracınızı yazabilirsiniz."
        ),
        reflectionQuestions = listOf(
            "Günlük hayatınızda veya işinizde tekrar ettiğiniz hangi küçük işi bir yazılımla otomatikleştirmek isterdiniz?"
        ),
        summary = "Algoritma düşünmesi problemi adım adım parçalara ayırmaktır. Python'da değişkenler, listeler, sözlükler, koşullar ve döngülerle gerçek problemler kolayca modellenir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Python'da if/else ve for döngüsü ne işe yarar?", "if/else koşula göre farklı yol seçer; for döngüsü bir veri kümesindeki her eleman için aynı işlemi tekrarlar.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m09_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "Aşağıdaki Python kodu çalıştırıldığında ekrana ne yazar?\n\npuan = 75\nif puan >= 70:\n    print('Başarılı')\nelse:\n    print('Tekrar Gerekli')",
                options = listOf("Tekrar Gerekli", "Başarılı", "75", "Hata verir"),
                correctOptionIndex = 1,
                explanation = "75 >= 70 koşulu doğru (True) olduğu için ilk dal çalışır ve 'Başarılı' yazdırılır."
            ),
            QuizQuestion(
                id = "m09_l02_q2",
                type = QuestionType.FILL_IN_BLANK,
                competency = CompetencyType.BILGI,
                prompt = "Bir problemi çözmek için tanımlanan açık, sıralı ve sonlu adımlar kümesine ________ denir.",
                acceptedAnswers = listOf("algoritma"),
                explanation = "Harizmi'nin adından gelen algoritma kavramı bilgisayar biliminin temelidir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Python Uygulama Projesi: Bütçe & Ders Hesaplayıcı",
            instructions = "Yukarıdaki bütçe koduna ek olarak haftalık çalışma saatlerinizi tutan bir liste oluşturup ortalamasını hesaplayan 5 satırlık Python sözde kodu yazın. Yapay Zekâ Öğretmenine kodunuzu kontrol ettirebilirsiniz!",
            deliverableHint = "Teknoloji & YZ öğrenme projelerinden ilki."
        ),
        references = listOf(
            AcademicReference("Allen B. Downey, Think Python (Python Gibi Düşünmek)", ReferenceType.UNIVERSITE_YAYINI, "Sıfırdan başlayanlar için ücretsiz akademik Python kitabı.")
        )
    ),
    Lesson(
        id = "m09_l03",
        monthNumber = 9,
        weekNumber = 4,
        discipline = DisciplineId.TEKNOLOJI_YZ,
        title = "Veri Tabanları, Otomasyon, Siber Güvenlik ve Dijital Mahremiyet",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "Verinin yapılandırılmış tablolar ve bulut veri tabanlarında nasıl saklandığını, otomasyon mantığını, siber saldırı vektörlerini ve dijital mahremiyeti kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: İlişkisel tablo, belge veri tabanı, oltalama (phishing) ve şifreleme kavramlarını tanımlar.",
            "Uygulama: Sahte bağlantıları ve sosyal mühendislik tuzaklarını ayırt eder."
        ),
        prerequisites = listOf("Ders 25 ve 26."),
        keyConcepts = listOf(
            ConceptCard("Veri Tabanı Şeması", "Verilerin tutarlı, aranabilir ve güvenli biçimde saklanması için tanımlanan yapı ve ilişkiler.", "Bir öğrencinin notlarının yalnızca kendi kimliğiyle (UID) ilişkilendirilmesi."),
            ConceptCard("Oltalama (Phishing)", "Güvenilir bir kurum gibi görünerek parola veya kart bilgisi çalmaya çalışan sosyal mühendislik saldırısı.", "Acil hesap kapatma tehdidiyle sahte bağlantıya tıklatmak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Veri, Şema ve Otomasyon",
                "Modern uygulamalar veriyi rastgele metin dosyalarında değil, sorgulanabilir veri tabanlarında saklar. Doğru veri modeli kurulduğunda otomasyon araçları haftalık raporları, tekrar hatırlatmalarını ve tablo analizlerini insan müdahalesi olmadan yürütür."
            ),
            ContentSection(
                "Siber Güvenlik ve Dijital Mahremiyet",
                "Güvenliğin en zayıf halkası çoğu zaman yazılım değil insan psikolojisidir (acele, korku, merak). Dijital mahremiyet ise hangi uygulamanın hangi verinizi topladığını bilmek ve en az yetki ilkesini benimsemektir."
            )
        ),
        historicalBackground = "1970'te E. F. Codd'un ilişkisel veri tabanı modelinden günümüz bulut ve şifreli iletişim standartlarına uzanan veri mühendisliği.",
        realLifeExamples = listOf(
            "Gelen bir 'hesabınız askıya alındı' e-postasında gönderen alan adını dikkatle kontrol etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Claude Shannon", "1916–2001", "Bilgi kuramı ve modern kriptografinin matematiksel kurucusu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Veri Saklama",
                "Düz Metin / Tablo", "Küçük kişisel listeler için pratiktir, çoklu erişimde yetersiz kalır.",
                "Veri Tabanı (SQL / NoSQL)", "Yetkilendirme, hızlı sorgu ve eşzamanlı güvenli erişim sağlar."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("En Az Yetki İlkesi", "azaltır", "Veri İhlali Riski")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Şüpheli Kargo Mesajı",
            scenario = "Telefona 'Kargonuz adres hatası nedeniyle bekliyor, 25 TL doğrulama için tıklayın' mesajı gelir; link 'kargo-dogrulama-tr.xyz' şeklindedir.",
            analysisQuestions = listOf(
                "Bu mesajdaki üç kırmızı bayrak nedir?"
            ),
            keyTakeaway = "Resmî alan adı dışı URL, aciliyet baskısı ve küçük ödeme tuzağı klasik oltalama belirtileridir."
        ),
        reflectionQuestions = listOf(
            "Dijital ayak izinizi azaltmak için bugün kapatabileceğiniz gereksiz bir izin veya abonelik var mı?"
        ),
        summary = "Veri tabanları dijital dünyanın hafızası, otomasyon ise kollarıdır; siber güvenlik ve mahremiyet bilinçli kullanıcı davranışıyla başlar.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Oltalama (phishing) saldırılarına karşı ilk savunma refleksi nedir?", "Aciliyet baskısına kapılmadan gönderen adresini, alan adını doğrulamak ve şüpheli bağlantıya tıklamamak.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m09_l03_q1",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.UYGULAMA,
                prompt = "Tüm sitelerde aynı karmaşık parolayı kullanmak hesap güvenliği için yeterlidir.",
                correctBool = false,
                explanation = "Yanlış. Bir site veri ihlaline uğradığında aynı parolayı kullanan tüm diğer hesaplarınız da ele geçirilir; her hesap için benzersiz parola kullanılmalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Veri Tablosu Analiz Planı",
            instructions = "Kendi ilgi alanınızla ilgili (ör. okuduğunuz kitaplar veya harcamalarınız) 4 sütunlu bir veri tablosu şeması tasarlayın.",
            deliverableHint = "Sütun adlarını ve veri tiplerini (metin, sayı, tarih) belirleyin."
        ),
        references = listOf(
            AcademicReference("BTK Güvenli İnternet ve Siber Güvenlik Rehberleri", ReferenceType.BILIMSEL_KURUM, "Türkiye standartlarında dijital güvenlik okuryazarlığı.")
        )
    )
)
