package com.example.data.curriculum

import com.example.data.model.*

val months1To3Lessons: List<Lesson> = listOf(
    // ======================== AY 1 — DÜŞÜNMEYİ ÖĞRENMEK (FELSEFE) ========================
    Lesson(
        id = "m01_l01",
        monthNumber = 1,
        weekNumber = 1,
        discipline = DisciplineId.FELSEFE,
        title = "Felsefi Düşüncenin Doğuşu, Ontoloji ve Sokratik Sorgulama",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Kullanıcının mitostan logosa geçişi kavramasını, varlık (ontoloji) sorularını tanımasını ve Sokratik sorgulamayı günlük yaşamındaki kabulleri test etmek için kullanabilmesini sağlamak.",
        learningOutcomes = listOf(
            "Tanıma: Felsefenin Antik Yunan ve İslam düşüncesindeki temel soru alanlarını tanımlar.",
            "Anlama: Mitos ile logos arasındaki yöntemsel farkı kendi cümleleriyle açıklar.",
            "Uygulama: Sokratik diyalog tekniğini bir gündelik tartışmada veya kararda uygular.",
            "Analiz ve Sentez: Ontolojik varsayımların insan eylemlerini ve etik yargıları nasıl belirlediğini değerlendirir."
        ),
        prerequisites = listOf("Ön koşul gerektirmez; merak ve sorgulama isteği yeterlidir."),
        keyConcepts = listOf(
            ConceptCard("Logos", "Akıl, gerekçeli söz ve evrendeki düzen ilkesi.", "Bir iddiayı 'öyle söyleniyor' diye değil, nedenleriyle savunmak."),
            ConceptCard("Ontoloji", "Varlığın ne olduğunu, ilk ilkelerini ve varoluş biçimlerini inceleyen felsefe dalı.", "'Zihnimdeki bir fikir gerçekten var mıdır?' sorusunu sormak."),
            ConceptCard("Maieutik (Doğurtma)", "Sokrates'in hazır bilgi vermek yerine sorularla kişinin kendi çelişkilerini görmesini sağlama yöntemi.", "Bir arkadaşa öğüt vermek yerine 'Bunun her durumda geçerli olduğuna nasıl eminsin?' diye sormak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Felsefe Nedir ve Neden Doğmuştur?",
                "Felsefe (philosophia), kelime anlamıyla 'bilgelik sevgisi'dir. Aristoteles'in Metafizik'te vurguladığı üzere felsefe hayret ve merakla başlar. MÖ 6. yüzyılda Milet'te Thales ve takipçileri, doğa olaylarını yalnızca mitolojik anlatılarla (mitos) değil, gözlemlenebilir ve akılla tartışılabilir ilkelerle (logos) açıklamaya yöneldi. Bu kırılma, dogmatik kabulden gerekçeli sorgulamaya geçişin başlangıcıdır."
            ),
            ContentSection(
                "Ontoloji: Varlık ve Oluş Problemi",
                "Ontoloji, 'Var olan nedir?' ve 'Değişimin ardında kalıcı bir öz var mıdır?' sorularını sorar. Herakleitos her şeyin sürekli bir akış (oluş) içinde olduğunu savunurken, Parmenides değişimin duyusal bir yanılsama olduğunu, hakiki varlığın bir ve değişmez olduğunu öne sürer. Platon bu gerilimi Duyular Dünyası ile İdealar Dünyası ayrımıyla çözmeye çalışır; Aristoteles ise töz (ousia) kavramıyla formu maddenin içine yerleştirir. İslam felsefesinde İbn Sina 'zorunlu varlık' (Vâcibü'l-Vücûd) ve 'mümkün varlık' ayrımıyla ontolojiye yeni bir derinlik kazandırır."
            ),
            ContentSection(
                "Sokratik Yöntem ve Entelektüel Alçakgönüllülük",
                "Sokrates'in gücü çok şey bilmesinde değil, bilmediğinin farkında olmasında yatar (Sokratik bilgisizlik). Diyaloglarında önce muhatabın 'adalet', 'cesaret' veya 'bilgi' tanımını alır (ironi), ardından karşı örneklerle o tanımın çelişkilerini ortaya çıkarır (çürütme / elenchus) ve kişiyi daha sağlam bir kavrayışa yöneltir (maieutik). Bugün eleştirel düşünmenin kalbinde de aynı ilke yer alır: Kendi inançlarımızı en sert biçimde yine kendimiz sınamalıyız."
            )
        ),
        historicalBackground = "MÖ 6. yüzyıl İyonya'sından Atina Akademisi'ne ve 9.–12. yüzyıl Bağdat–Endülüs çeviri ve telif geleneğine (Farabi, İbn Sina, İbn Rüşd) uzanan felsefe tarihi, aklın sınırlarını ve varlığın anlamını araştıran kesintisiz bir insanlık mirasıdır.",
        realLifeExamples = listOf(
            "Sosyal medyada karşılaşılan iddialı bir haberi paylaşmadan önce 'Bunun kaynağı kim, gizli varsayımı ne?' diye sormak.",
            "Bir iş veya kariyer kararında 'Başarı herkes için para mıdır, yoksa anlamlı üretim midir?' diyerek kendi tanımını sorgulamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Sokrates", "MÖ 469–399", "Sokratik sorgulama (elenchus) ve ahlaki öz-inceleme yönteminin kurucusu."),
            ThinkerHighlight("Platon", "MÖ 427–347", "İdealar kuramı, Devlet ve Akademia geleneğinin mimarı."),
            ThinkerHighlight("Aristoteles", "MÖ 384–322", "Biçimsel mantık, töz ontolojisi ve erdem etiğinin sistemleştiricisi."),
            ThinkerHighlight("İbn Sina", "980–1037", "Zorunlu ve mümkün varlık ayrımıyla İslam ve Batı metafiziğini derinden etkileyen filozof.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Varlık Anlayışları",
                "Platon (İdealizm)", "Gerçeklik duyular üstü değişmez idealardadır; nesneler onların gölgesidir.",
                "Aristoteles (Gerçekçilik)", "Gerçeklik somut tekil varlıklardadır; form maddenin içinde içkindir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Merak ve Hayret", "doğurur", "Felsefi Soru"),
            ConceptRelation("Sokratik Sorgulama", "açar", "Varsayım Analizi"),
            ConceptRelation("Ontoloji", "temellendirir", "Bilgi ve Değer Yargıları"),
            ConceptRelation("Logos", "ayrışır", "Dogmatik Kabul")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Şehir Meclisinde 'İyi Vatandaş' Tartışması",
            scenario = "Bir belediye toplantısında katılımcılardan biri 'İyi vatandaş kurallara hiç itiraz etmeden uyandır' der. Başka biri 'İyi vatandaş haksızlığa karşı çıkandır' savunur. Tartışma gerginleşir ve kimse diğerini dinlemez.",
            analysisQuestions = listOf(
                "Sokrates bu toplantıda olsaydı ilk hangi soruyu sorardı?",
                "İki tarafın da kullandığı 'iyi' ve 'kural' kavramlarındaki belirsizlik nerededir?"
            ),
            keyTakeaway = "Sokratik yöntem kişileri değil tanımları sınar; ortak bir tanım zemini kurulduğunda tartışma kavgadan ortak araştırmaya dönüşür."
        ),
        reflectionQuestions = listOf(
            "Hayatınızda hiç sorgulamadan kabul ettiğiniz üç temel inanç hangileridir?",
            "'Sorgulanmamış yaşam yaşanmaya değer değildir' sözü bugünün hızlı dijital dünyasında ne ifade eder?"
        ),
        summary = "Felsefe, doğa ve insan hakkındaki sorulara dogmalarla değil akıl yürütme (logos) ile yanıt arar. Ontoloji varlığın yapısını incelerken, Sokratik yöntem bilgi iddialarımızı çelişkilere karşı sınar.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Mitos ile logos arasındaki temel fark nedir?", "Mitos geleneğe ve olağanüstü anlatılara dayanırken, logos akla, kanıta ve tartışılabilir gerekçelere dayanır."),
            ActiveRecallItem("İbn Sina'nın ontolojiye getirdiği temel ayrım hangisidir?", "Zorunlu Varlık (Vâcibü'l-Vücûd) ile Mümkün Varlık ayrımı.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m01_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Sokrates'in sorular sorarak muhatabının kendi çelişkilerini fark etmesini sağladığı yönteme ne ad verilir?",
                options = listOf("Dogmatizm", "Maieutik / Sokratik Yöntem", "Empirizm", "Determinizm"),
                correctOptionIndex = 1,
                explanation = "Sokrates hazır tanımlar dikte etmez; diyalog ve sorularla (elenchus ve maieutik) kişinin kendi düşüncesindeki tutarsızlıkları görmesini sağlar."
            ),
            QuizQuestion(
                id = "m01_l01_q2",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Aristoteles'e göre idealar, fiziksel nesnelerden tamamen kopuk ayrı bir gökyüzü dünyasında yaşar.",
                correctBool = false,
                explanation = "Yanlış. İdeaları duyulur dünyadan ayıran Platon'dur; Aristoteles ise formun (özün) bizzat somut nesnenin içinde bulunduğunu savunur."
            ),
            QuizQuestion(
                id = "m01_l01_q3",
                type = QuestionType.FILL_IN_BLANK,
                competency = CompetencyType.BILGI,
                prompt = "Varlığın ilk ilkelerini, yapısını ve varoluş biçimlerini inceleyen felsefe dalına ________ denir.",
                acceptedAnswers = listOf("ontoloji", "varlık felsefesi"),
                explanation = "Varlık felsefesi anlamına gelen ontoloji, 'Var olan nedir?' sorusunu merkeze alır."
            ),
            QuizQuestion(
                id = "m01_l01_q4",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Bir arkadaşınız 'Çoğunluğun doğru dediği şey her zaman doğrudur' iddiasında bulunsa, Sokratik yöntemle ona nasıl bir karşı soru ve analiz sunardınız?",
                explanation = "İyi bir yanıt, çoğunluğun yanıldığı tarihsel örnekleri hatırlatmak yerine önce muhataba 'doğruluk' ölçütünü tanımlatır ve sayısal çoğunluk ile epistemik doğruluk arasındaki farkı sorgular.",
                rubricHint = "Sokratik soru tekniği, kavram tanımı ve çoğunluk-doğruluk ayrımı aranır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Feynman & Sokratik Diyalog Egzersizi",
            instructions = "Günlük hayatta sıkça duyduğunuz bir kalıp yargıyı (örneğin 'Çok çalışan her zaman kazanır' veya 'Teknoloji insanı yalnızlaştırır') seçin ve Sokratik yöntemle bu iddiayı sınayan 4 ardışık soru yazın.",
            deliverableHint = "Sorularınızı Not Defteri bölümüne 'Sokratik Deneme' başlığıyla kaydedebilirsiniz."
        ),
        references = listOf(
            AcademicReference("Platon, Sokrates'in Savunması (Çev. Ari Çokona), Türkiye İş Bankası Kültür Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Sokratik sorgulama ve bilgelik anlayışı için birincil metin."),
            AcademicReference("Ahmet Arslan, Felsefeye Giriş, Adres Yayınları", ReferenceType.AKADEMIK_KITAP, "Türkçe felsefe literatüründe sistematik giriş kaynağı."),
            AcademicReference("Stanford Encyclopedia of Philosophy — 'Socrates' & 'Metaphysics'", ReferenceType.UNIVERSITE_YAYINI, "Akademik kavram doğrulaması.")
        )
    ),
    Lesson(
        id = "m01_l02",
        monthNumber = 1,
        weekNumber = 2,
        discipline = DisciplineId.FELSEFE,
        title = "Epistemoloji, Mantık ve Argüman Analizi",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Bilginin kaynağını ve sınırlarını (rasyonalizm, empirizm, eleştirel felsefe) incelemek ve geçerli akıl yürütmeyi mantık hatalarından (safsatalardan) ayırabilmek.",
        learningOutcomes = listOf(
            "Tanıma: Rasyonalizm, empirizm ve Kantçı sentezin temel tezlerini tanır.",
            "Anlama: Geçerlilik ile doğruluk arasındaki mantıksal farkı açıklar.",
            "Uygulama: Medya metinlerindeki temel mantık safsatalarını (ad hominem, saman adam, sahte ikilem) tespit eder.",
            "Analiz ve Sentez: Gazali, Hume ve Kant çizgisinde nedensellik ve kesin bilgi krizini karşılaştırır."
        ),
        prerequisites = listOf("Ders 1: Felsefi Düşüncenin Doğuşu ve Sokratik Sorgulama."),
        keyConcepts = listOf(
            ConceptCard("Epistemoloji", "Bilginin doğasını, kaynağını, ölçütlerini ve sınırlarını inceleyen felsefe disiplini.", "Bir iddiaya 'neden inanmalıyım?' sorusunun yöntemi."),
            ConceptCard("Tümdengelim ve Tümevarım", "Genelden özele zorunlu çıkarım (tümdengelim) ile tekil gözlemlerden genele ulaşma (tümevarım).", "Tüm insanlar ölümlüdür -> Sokrates ölümlüdür (tümdengelim)."),
            ConceptCard("Safsata (Mantık Hatası)", "İlk bakışta ikna edici görünen ancak mantıksal olarak geçersiz akıl yürütme.", "Kişinin fikrini çürütmek yerine kişiliğine saldırmak (Ad Hominem).")
        ),
        detailedContent = listOf(
            ContentSection(
                "Bilgi Nedir ve Nereden Gelir?",
                "Klasik epistemoloji bilgiyi 'gerekçelendirilmiş doğru inanç' olarak tanımlar. Peki bu gerekçe akıldan mı (rasyonalizm) yoksa deneyimden mi (empirizm) gelir? Descartes 'Düşünüyorum, öyleyse varım' diyerek tüm duyusal verileri şüpheye tabi tutar ve doğuştan akıl ilkelerine dayanır. Spinoza bilgiyi geometrik bir kesinlikle kurar. Buna karşılık David Hume, deneyimin ötesinde zorunlu bir nedensellik gözlemleyemediğimizi gösterir; tıpkı yüzyıllar önce Gazali'nin Tehâfütü'l-Felâsife'de neden-sonuç arasındaki zorunluluğu sorgulaması gibi."
            ),
            ContentSection(
                "Kant'ın Büyük Sentezi",
                "Immanuel Kant, Hume'un şüpheciliğinin kendisini 'dogmatik uykudan uyandırdığını' belirtir. Kant'a göre bilgi deneyimle başlar ancak yalnızca deneyimden doğmaz. Zihin, duyusal verileri zaman-mekân formları ve nedensellik gibi kategorilerle işler. Böylece ne salt akılcılık ne de salt deneycilik tek başına yeterlidir."
            ),
            ContentSection(
                "Argüman Yapısı ve Yaygın Safsatalar",
                "Sağlam bir argüman öncüller ve sonuçtan oluşur. Geçerli bir argümanda öncüller doğruysa sonuç zorunlu olarak doğrudur. Gündelik tartışmalarda en sık rastlanan hatalar şunlardır: Kişiye Saldırı (Ad Hominem), Korkuluk / Saman Adam (rakibin görüşünü çarpıtıp zayıflatma), Kaygan Zemin ve Otoriteye Başvuru."
            )
        ),
        historicalBackground = "Aristoteles'in Organon'u ile kurulan klasik mantık, Farabi ve İbn Sina tarafından İslam dünyasında geliştirildi; 17.–18. yüzyılda Descartes, Hume ve Kant ile modern bilgi kuramı şekillendi.",
        realLifeExamples = listOf(
            "Bir uzman görüşü yerine ünlünün reklamına güvenmek (yanlış otorite safsatası).",
            "'Ya bu politikayı tamamen kabul edersin ya da ülkeyi sevmiyorsun' ifadesindeki sahte ikilemi fark etmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Descartes", "1596–1650", "Metodik şüphe ve modern rasyonalizmin kurucusu."),
            ThinkerHighlight("David Hume", "1711–1776", "Tümevarım ve nedensellik eleştirisiyle empirizmi zirveye taşıyan İskoç filozof."),
            ThinkerHighlight("Immanuel Kant", "1724–1804", "Aklın sınırlarını ve sentetik a priori bilgiyi temellendiren düşünür."),
            ThinkerHighlight("Gazali", "1058–1111", "Kesin bilgi arayışı (El-Munkız) ve nedensellik eleştirisiyle tanınan âlim ve mütefekkir.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Bilginin Kaynağı",
                "Rasyonalizm (Descartes, Spinoza)", "Kesin bilginin temeli akıl ilkeleri ve doğuştan gelen kavramlardır.",
                "Empirizm (Hume)", "Tüm bilgi duyusal izlenimlerden ve deneyimden türetilir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Öncüller", "destekler", "Argüman Sonucu"),
            ConceptRelation("Hume Eleştirisi", "tetikler", "Kantçı Sentez"),
            ConceptRelation("Safsata", "bozar", "Geçerli Akıl Yürütme")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Tartışma Programında Safsata Avı",
            scenario = "Bir ekonomist yeni vergi düzenlemesini verilerle eleştirir. Karşı konuşmacı 'Sen zaten geçen yıl da bir tahminde yanılmıştın, ayrıca zengin semtte oturuyorsun, sözlerin geçersiz' der.",
            analysisQuestions = listOf(
                "Konuşmacı hangi mantık hatalarını yapmıştır?",
                "Ekonomistin verilerini çürütmek için nasıl bir karşı argüman kurulmalıydı?"
            ),
            keyTakeaway = "Bir kişinin geçmiş hatası veya yaşam tarzı, sunduğu güncel verilerin doğruluğunu mantıksal olarak geçersiz kılmaz (Ad Hominem)."
        ),
        reflectionQuestions = listOf(
            "Bir bilginin 'güvenilir' olduğunu kabul etmeniz için hangi üç kanıt ölçütünü ararsınız?"
        ),
        summary = "Epistemoloji bilginin kaynağını ve sınırlarını araştırır; mantık ise tutarlı çıkarım kurallarını verir. Hume ve Gazali nedensellik alışkanlığını sorgularken Kant zihnin aktif rolünü ortaya koymuştur.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Geçerli bir argüman her zaman doğru sonuç verir mi?", "Hayır. Öncüllerden biri yanlışsa argüman biçimsel olarak geçerli ama 'sağlam' (sound) olmayabilir.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m01_l02_q1",
                type = QuestionType.MATCHING,
                competency = CompetencyType.BILGI,
                prompt = "Düşünürleri temel epistemolojik kavramlarıyla eşleştirin:",
                matchingPairs = listOf(
                    "Descartes" to "Metodik Şüphe",
                    "David Hume" to "Alışkanlık ve Nedensellik Eleştirisi",
                    "Immanuel Kant" to "Deneyim ve Zihin Kategorileri Sentezi"
                ),
                explanation = "Descartes kesinlik için metodik şüpheyi, Hume nedensellik eleştirisini, Kant ise akıl-deney sentezini savunmuştur."
            ),
            QuizQuestion(
                id = "m01_l02_q2",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "'Rakibimin savunduğu yenilenebilir enerji planı aslında tüm fabrikaları kapatmak istiyor' diyerek karşı tarafın savını abartıp çarpıtan kişi hangi safsatayı yapmıştır?",
                options = listOf("Korkuluk (Saman Adam) Safsatası", "Döngüsel Akıl Yürütme", "Yanlış Benzetme", "Tümdengelim"),
                correctOptionIndex = 0,
                explanation = "Karşı tarafın gerçek argümanı yerine kolayca çürütülebilecek karikatürize edilmiş bir versiyonuna saldırmak Korkuluk (Straw Man) safsatasıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Argüman Haritası Çıkarma",
            instructions = "Güncel bir köşe yazısı veya tartışma konusu seçin; yazarın ana iddiasını, 2 destekleyici öncülünü ve varsa tespit ettiğiniz 1 mantık hatasını ya da gizli varsayımını yazın.",
            deliverableHint = "Notlarım sekmesinden 'Eleştirel Okuma' etiketiyle kaydedin."
        ),
        references = listOf(
            AcademicReference("Descartes, Metot Üzerine Konuşma, MEB / İş Bankası Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Rasyonalist yöntemin kurucu metni."),
            AcademicReference("Nigel Warburton, Felsefeye Giriş, Alfa Yayınları", ReferenceType.AKADEMIK_KITAP, "Epistemoloji ve mantık tartışmalarına açık giriş.")
        )
    ),
    Lesson(
        id = "m01_l03",
        monthNumber = 1,
        weekNumber = 4,
        discipline = DisciplineId.FELSEFE,
        title = "Etik, Özgür İrade, Siyaset Felsefesi ve İyi Yaşam",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "Ahlaki karar verme çerçevelerini (erdem etiği, ödev etiği, faydacılık), özgür irade-determinizm tartışmasını, siyaset felsefesini ve iyi yaşam anlayışlarını karşılaştırmak.",
        learningOutcomes = listOf(
            "Tanıma: Erdem etiği, deontoloji (Kant) ve faydacılık yaklaşımlarını tanımlar.",
            "Anlama: Özgür irade ile determinizm arasındaki temel gerilimi açıklar.",
            "Uygulama: Bir ahlaki ikilemi üç farklı etik kuram açısından değerlendirir.",
            "Analiz ve Sentez: Hannah Arendt, Sartre ve Epiktetos üzerinden bireysel sorumluluk ve iyi yaşam kavramlarını sentezler."
        ),
        prerequisites = listOf("Ders 1 ve Ders 2'deki kavram analizi temeli."),
        keyConcepts = listOf(
            ConceptCard("Erdem Etiği (Aristoteles)", "Eylemden önce karakterin ölçülülük ve pratik bilgelikle (phronesis) inşası.", "Doğruyu ceza korkusundan değil, adil bir insan olduğun için yapmak."),
            ConceptCard("Koşulsuz Buyruk (Kant)", "Eyleminin ilkesinin herkes için evrensel bir yasa olmasını isteyebileceğin biçimde davran.", "'Herkes yalan söyleseydi güven kalır mıydı?' testi."),
            ConceptCard("Kötülüğün Sıradanlığı (Hannah Arendt)", "Büyük kötülüklerin yalnızca canavarlar tarafından değil, düşünmeyi bırakan sıradan görevlilerce işlenebilmesi.", "'Ben sadece emri uyguladım' bahanesinin ahlaki tehlikesi.")
        ),
        detailedContent = listOf(
            ContentSection(
                "İyi Yaşam (Eudaimonia) ve Stoacı Dayanıklılık",
                "Aristoteles için nihai amaç geçici haz değil, aklın ve erdemin rehberliğinde gerçekleşen 'eudaimonia' (insani serpilme ve iyi yaşam) halidir. Stoacı Epiktetos ise kontrol edebildiklerimiz (düşüncelerimiz, tepkilerimiz) ile kontrol edemediklerimizi (dış olaylar, başkalarının sözleri) ayırarak içsel özgürlüğe ulaşmayı öğretir."
            ),
            ContentSection(
                "Özgür İrade, Varoluşçuluk ve Siyaset",
                "Determinizm her seçimin önceki nedenlerce belirlendiğini savunurken, Sartre 'insan özgürlüğe mahkûmdur' diyerek seçimlerimizin tüm sorumluluğunu bize yükler. Siyaset felsefesinde ise adalet, meşruiyet ve özgürlük soruları toplum sözleşmesi ve kamusal alan kavramlarıyla (Hannah Arendt) tartışılır; bireyin düşünme sorumluluğu yurttaşlığın temelidir."
            ),
            ContentSection(
                "Estetik ve Değer Dünyası",
                "İyi ve doğrunun yanında güzel olanın ne olduğunu soran estetik, insanın dünyayla kurduğu anlam ilişkisinin üçüncü sütunudur. Sanat yalnızca süs değil, algıyı derinleştiren bir bilme biçimidir."
            )
        ),
        historicalBackground = "Atina'da etik ve siyasetle başlayan iyi yaşam arayışı, Stoacılık ve İslam ahlak felsefesiyle (Farabi'nin Erdemli Şehir'i) devam etmiş, Aydınlanma (Kant, Hume) ve 20. yüzyıl kıta felsefesiyle (Nietzsche, Sartre, Arendt) modern biçimini almıştır.",
        realLifeExamples = listOf(
            "Yapay zekâ çağında otonom araçların kaza ikilemlerinde faydacılık ile ödev etiğinin çatışması.",
            "Zor bir iş gününde Epiktetos'un kontrol dikotomisini uygulayarak yalnızca kendi tepkisine odaklanmak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Epiktetos", "50–135", "Kontrolümüzdeki ve dışımızdaki şeyleri ayıran Stoacı bilge."),
            ThinkerHighlight("Farabi", "872–950", "Erdemli Toplum (El-Medînetü'l-Fâzıla) tasarımıyla siyaset ve mutluluk felsefesinin öncüsü."),
            ThinkerHighlight("Friedrich Nietzsche", "1844–1900", "Değerlerin yeniden değerlendirilmesi ve bireysel yaratıcılık vurgusu."),
            ThinkerHighlight("Hannah Arendt", "1906–1975", "Siyasal sorumluluk, özgürlük ve 'kötülüğün sıradanlığı' kavramlarının kuramcısı.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Ahlaki Ölçütler",
                "Kantçı Ödev Etiği", "Eylemin sonucu ne olursa olsun niyet ve evrensel ahlak yasası esastır.",
                "Faydacılık", "En fazla sayıda insan için en yüksek net faydayı üreten eylem doğrudur."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Erdem", "inşa eder", "İyi Yaşam (Eudaimonia)"),
            ConceptRelation("Sorumluluk", "gerektirir", "Özgür İrade ve Düşünme"),
            ConceptRelation("Farabi", "bağlar", "Bireysel Mutluluk ve Adil Toplum")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Şirket Verisi ve Vicdani Karar",
            scenario = "Bir veri analisti, çalıştığı şirketin kullanıcı gizliliğini ihlal eden bir özelliği sessizce yayına aldığını fark eder. Yöneticiler 'Rakipler de yapıyor, işini kaybetmek istemiyorsan ses çıkarma' der.",
            analysisQuestions = listOf(
                "Kant'ın koşulsuz buyruğu ve Arendt'in düşünme sorumluluğu açısından bu çalışan nasıl davranmalıdır?",
                "Epiktetos'un kontrol dikotomisi bu kararda nasıl bir içsel güç sağlar?"
            ),
            keyTakeaway = "Etik ilkeler yalnızca kolay zamanlarda değil, bedel ödeme ihtimali doğduğunda karakteri sınar."
        ),
        reflectionQuestions = listOf(
            "Sizin için 'iyi bir yaşam' hangi üç erdem üzerine kuruludur?"
        ),
        summary = "Etik kuramları eylemlerimizi niyet, sonuç ve karakter üzerinden değerlendirir. İyi yaşam, hem bireysel erdemi hem de kamusal sorumluluğu gerektirir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Epiktetos'un temel ayrımı nedir?", "Kontrolümüz altında olanlar (yargılarımız, arzularımız, eylemlerimiz) ve kontrolümüz dışında olanlar (beden, ün, dış olaylar).")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m01_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.ANLAMA,
                prompt = "Hannah Arendt 'kötülüğün sıradanlığı' kavramıyla aşağıdakilerden hangisini vurgular?",
                options = listOf(
                    "Kötülüğün yalnızca doğuştan gelen patolojik bir hastalık olduğunu",
                    "Sorgulamayı ve ahlaki yargıda bulunmayı bırakan sıradan bireylerin büyük kötülüklere alet olabileceğini",
                    "İnsanların özgür iradeye hiç sahip olmadığını",
                    "Ekonomik krizlerin tek başına ahlakı belirlediğini"
                ),
                correctOptionIndex = 1,
                explanation = "Arendt, Eichmann örneği üzerinden, eleştirel düşünmeyi askıya alıp körü körüne itaat etmenin yıkıcı sonuçlarını analiz eder."
            ),
            QuizQuestion(
                id = "m01_l03_q2",
                type = QuestionType.CASE_RESOLUTION,
                competency = CompetencyType.UYGULAMA,
                prompt = "Acil bir durumda bir kişinin hayatını kurtarmak için küçük bir yalan söylemeniz gerekse, Kantçı ödev etiği ile faydacılık nasıl farklı kararlar verirdi? Kendi sentezinizi yazın.",
                explanation = "Kant mutlak doğruculuk ilkesinin evrenselleştirilebilirliğini korurken, faydacılık can kurtarmanın doğuracağı toplam iyiliği esas alır.",
                rubricHint = "Her iki etik kuramın gerekçesini net ayırmak ve somut muhakeme kurmak beklenir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Kontrol Dikotomisi Günlüğü",
            instructions = "Bugün sizi kaygılandıran 3 durumu yazın; her birinin karşısına 'Kontrolümde' ve 'Kontrolüm dışında' başlıklarıyla somut bir aksiyon cümlesi ekleyin.",
            deliverableHint = "Kendi Düşüncelerim bölümüne kaydedin."
        ),
        references = listOf(
            AcademicReference("Aristoteles, Nikomakhos'a Etik, BilgeSu / Say Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Erdem etiği ve eudaimonia kavramının ana kaynağı."),
            AcademicReference("Epiktetos, Düşünceler ve Sohbetler (Enkheiridion), İş Bankası Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Stoacı yaşam pratiği.")
        )
    ),

    // ======================== AY 2 — KENDİNİ VE İNSANI ANLAMAK (PSİKOLOJİ) ========================
    Lesson(
        id = "m02_l01",
        monthNumber = 2,
        weekNumber = 1,
        discipline = DisciplineId.PSIKOLOJI,
        title = "Bilişsel Psikoloji, Dikkat, Bellek ve Etkili Öğrenme",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "İnsan dikkatinin sınırlarını, bellek türlerini ve pasif okuma yerine aktif hatırlama ile aralıklı tekrarın nöro-bilişsel temelini kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: Kısa süreli bellek, çalışma belleği ve uzun süreli bellek sistemlerini tanımlar.",
            "Anlama: Ebbinghaus unutma eğrisini ve aralıklı tekrarın etkisini açıklar.",
            "Uygulama: Kendi öğrenme rutininde çoklu görev (multitasking) tuzaklarını azaltır.",
            "Analiz ve Sentez: Piaget ve Vygotsky'nin bilişsel gelişim modellerini öğrenme tasarımıyla ilişkilendirir."
        ),
        prerequisites = listOf("Ay 1 felsefi sorgulama ve öz-gözlem alışkanlığı."),
        keyConcepts = listOf(
            ConceptCard("Çalışma Belleği", "Bilgiyi geçici olarak tutup işlediğimiz sınırlı kapasiteli bilişsel tezgâh.", "Zihinden iki basamaklı sayıları çarparken sayıları akılda tutmak."),
            ConceptCard("Aktif Hatırlama (Active Recall)", "Metni tekrar okumak yerine bilgiyi zihinden geri çağırmaya zorlamak.", "Kitabı kapatıp az önce okuduğunu kendi cümlelerinle anlatmak."),
            ConceptCard("Yakınsal Gelişim Alanı (Vygotsky)", "Kişinin tek başına yapabildikleri ile rehberlikle başarabilecekleri arasındaki gelişim bölgesi.", "Yapay zekâ öğretmeninin çözümü vermeden ipucuyla seni zorlaması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Psikolojinin Bilimsel Doğuşu ve Bilişsel Devrim",
                "1879'da Wilhelm Wundt'un ilk psikoloji laboratuvarını kurması ve William James'in işlevselci yaklaşımıyla psikoloji, felsefeden ayrılarak deneysel bir bilim haline geldi. 20. yüzyıl ortasında B. F. Skinner'ın davranışçılığına ek olarak bilişsel psikoloji zihnin bilgiyi nasıl kodladığını, depoladığını ve geri çağırdığını incelemeye başladı."
            ),
            ContentSection(
                "Dikkat, Algı ve Bilişsel Yük",
                "Dikkat sınırlı bir kaynaktır. Aynı anda hem bildirimlere bakıp hem derin bir felsefe metni okumaya çalışmak 'dikkat kalıntısı' yaratır ve anlama hızını %40'a kadar düşürür. Bilgilerin uzun süreli belleğe kalıcı biçimde yerleşmesi için derin işleme (anlamlandırma, bağlantı kurma) ve uyku sırasındaki bellek pekiştirmesi şarttır."
            ),
            ContentSection(
                "Gelişim ve Sosyal Öğrenme",
                "Jean Piaget zihinsel şemaların özümseme ve uyumsama ile geliştiğini gösterirken, Lev Vygotsky öğrenmenin sosyal ve dilsel bağlamda (iskele kurma / scaffolding) gerçekleştiğini kanıtlar. Albert Bandura ise öz-yeterlik inancının çaba ve sebatı doğrudan belirlediğini ortaya koyar."
            )
        ),
        historicalBackground = "1885'te Hermann Ebbinghaus'un bellek deneylerinden modern bilişsel sinirbilime uzanan çalışmalar, öğrenmenin pasif bir 'kopyalama' değil aktif bir 'yeniden inşa' süreci olduğunu göstermiştir.",
        realLifeExamples = listOf(
            "Bir dersi 5 kez altını çizerek okumak yerine 1 kez okuyup 3 soru çözmek daha kalıcı öğrenme sağlar.",
            "Zor bir konuyu öğrenirken telefonu başka odaya koyarak çalışma belleğini korumak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("William James", "1842–1910", "Modern Amerikan psikolojisinin kurucusu; dikkat ve alışkanlık üzerine öncü çalışmalar."),
            ThinkerHighlight("Jean Piaget", "1896–1980", "Bilişsel gelişim aşamaları ve şema kuramının mimarı."),
            ThinkerHighlight("Lev Vygotsky", "1896–1934", "Sosyo-kültürel gelişim ve Yakınsal Gelişim Alanı kuramcısı."),
            ThinkerHighlight("Albert Bandura", "1925–2021", "Sosyal bilişsel kuram ve öz-yeterlik kavramının öncüsü.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Öğrenme Kuramları",
                "Davranışçılık (Skinner)", "Gözlenebilir davranış, pekiştirme ve çevresel koşullandırmaya odaklanır.",
                "Bilişsel Kuram (Piaget, Vygotsky)", "Zihinsel şemalar, anlamlandırma ve sosyal etkileşimi merkeze alır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Odaklanmış Dikkat", "besler", "Çalışma Belleği"),
            ConceptRelation("Aktif Hatırlama", "güçlendirir", "Uzun Süreli Bellek"),
            ConceptRelation("Aralıklı Tekrar", "yener", "Unutma Eğrisi")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Çok Okuyup Çabuk Unutan Öğrenci",
            scenario = "Mert her akşam 2 saat ders metinlerini renkli kalemle işaretleyerek okuyor; ancak bir hafta sonraki sınavda kavramları birbirine karıştırıyor.",
            analysisQuestions = listOf(
                "Mert'in yaşadığı 'akıcılık yanılsaması' (illusion of competence) neden kaynaklanıyor?",
                "Bu akademideki hangi iki özellik Mert'in sorununu doğrudan çözer?"
            ),
            keyTakeaway = "Metni akıcı okumak bilgiyi öğrendiğimiz yanılsamasını doğurur; gerçek ölçüt kitaba bakmadan hatırlayabilmektir."
        ),
        reflectionQuestions = listOf(
            "Günün hangi saatinde zihinsel berraklığınız en yüksek ve dikkatinizi en çok ne dağıtıyor?"
        ),
        summary = "Kalıcı öğrenme; bölünmemiş dikkat, çalışma belleğini aşırı yüklememe, aktif hatırlama ve aralıklı tekrar ile gerçekleşir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Yakınsal Gelişim Alanı nedir?", "Bireyin tek başına başarabileceği düzey ile bir rehber eşliğinde ulaşabileceği potansiyel düzey arasındaki alan.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m02_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Aşağıdaki yöntemlerden hangisi uzun süreli bellekte kalıcılığı en güçlü biçimde artırır?",
                options = listOf("Aynı metni art arda üç kez okumak", "Metnin tamamını fosforlu kalemle çizmek", "Aktif hatırlama ve aralıklı tekrar yapmak", "Aynı anda müzik ve bildirimlerle çalışmak"),
                correctOptionIndex = 2,
                explanation = "Aktif hatırlama nöral bağlantıları güçlendirir; aralıklı tekrar ise unutma başlamadan hemen önce bilgiyi tazeler."
            ),
            QuizQuestion(
                id = "m02_l01_q2",
                type = QuestionType.SHORT_ANSWER,
                competency = CompetencyType.UYGULAMA,
                prompt = "Sınav öncesi 'çok iyi anladım' hissini veren ama sınavda başarısızlığa yol açan okuma yanılsamasına ne denir?",
                acceptedAnswers = listOf("akıcılık yanılsaması", "yetkinlik yanılsaması", "illusion of competence"),
                explanation = "Tanıdık gelen metni okurken onu bildiğimizi sanmaya akıcılık/yetkinlik yanılsaması denir."
            )
        ),
        practiceTask = PracticeTask(
            title = "25 Dakikalık Derin Odak Deneyi",
            instructions = "Tüm bildirimleri kapatarak bir dersi 25 dakika boyunca okuyun, ardından ekranı kapatıp aklınızda kalan 3 ana fikri yazın.",
            deliverableHint = "Eksik hatırladığınız noktaları dersin 'Bu dersten ne öğrendim?' alanına ekleyin."
        ),
        references = listOf(
            AcademicReference("Daniel Schacter, Belleğin Yedi Günahı, Bilge Kültür Sanat", ReferenceType.AKADEMIK_KITAP, "Bellek işleyişi ve unutma mekanizmaları."),
            AcademicReference("Roediger & Karpicke (2006), Test-Enhanced Learning, Psychological Science", ReferenceType.HAKEMLI_MAKALE, "Aktif hatırlamanın deneysel kanıtı.")
        )
    ),
    Lesson(
        id = "m02_l02",
        monthNumber = 2,
        weekNumber = 2,
        discipline = DisciplineId.PSIKOLOJI,
        title = "Bilişsel Yanlılıklar, Karar Verme ve Alışkanlık Mimarisi",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Sistem 1 ve Sistem 2 düşünme ayrımını, başlıca bilişsel yanlılıkları ve sürdürülebilir alışkanlık oluşturma döngüsünü incelemek.",
        learningOutcomes = listOf(
            "Tanıma: Daniel Kahneman'ın Sistem 1 ve Sistem 2 kuramını tanımlar.",
            "Anlama: Doğrulama yanlılığı, çıpalama ve kayıptan kaçınma etkilerini açıklar.",
            "Uygulama: Kendi günlük kararlarında bilişsel yanlılık kontrol listesi kullanır.",
            "Analiz ve Sentez: Bilişsel terapi (Aaron Beck) ve alışkanlık döngüleri arasındaki ilişkiyi değerlendirir."
        ),
        prerequisites = listOf("Ay 1 Mantık ve Safsata dersi."),
        keyConcepts = listOf(
            ConceptCard("Sistem 1 ve Sistem 2", "Hızlı, sezgisel, otomatik düşünme (Sistem 1) ile yavaş, analitik, çaba gerektiren düşünme (Sistem 2).", "Trafikte ani refleks Sistem 1; aylık bütçe hesabı Sistem 2'dir."),
            ConceptCard("Doğrulama Yanlılığı", "Mevcut inancımızı destekleyen kanıtları arayıp çelişenleri görmezden gelme eğilimi.", "Sadece kendi görüşümüzdeki haberleri okuyup karşıt veriyi küçümsemek."),
            ConceptCard("Alışkanlık Döngüsü", "İşaret (Cue) -> İstek/Rutin (Routine) -> Ödül (Reward) mekanizması.", "Kahveden hemen sonra 15 dk kitap açmayı tetikleyiciye bağlamak.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Zihnin İki Hızı ve Sezgisel Tuzaklar",
                "Nobel ödüllü psikolog Daniel Kahneman insan zihninin iki modda çalıştığını gösterir. Sistem 1 hayatta kalmamız için saniyeler içinde karar üretir; ancak istatistiksel ve karmaşık durumlarda sistematik hatalar (bilişsel yanlılıklar) yapar. Çıpalama (ilk duyduğumuz sayıya takılma), bulunabilirlik kısayolu (akla ilk gelen örneği yaygın sanma) ve batık maliyet yanılgısı rasyonel kararları saptırır."
            ),
            ContentSection(
                "Otomatik Düşünceler ve Bilişsel Esneklik",
                "Aaron Beck'in bilişsel kuramına göre duygularımızı doğrudan olaylar değil, o olaylara yüklediğimiz otomatik düşünceler ve bilişsel çarpıtmalar ('ya hep ya hiç', 'felaketleştirme') şekillendirir. Düşünceleri kanıtlarla sınamak duygusal dengeyi güçlendirir."
            ),
            ContentSection(
                "İrade Gücü Yerine Alışkanlık Tasarımı",
                "Sadece motivasyona güvenmek kırılgandır. B. F. Skinner'ın pekiştirme ilkeleri ve modern alışkanlık bilimi, ortam tasarımının iradeden daha güçlü olduğunu gösterir: İstenen davranışı görünür ve kolay, dikkat dağıtıcıları ise zor ve erişilmez kılmak gerekir."
            )
        ),
        historicalBackground = "1970'lerde Kahneman ve Tversky'nin Beklenti Kuramı (Prospect Theory) klasik 'tam rasyonel insan' varsayımını kökten değiştirdi ve davranışsal ekonominin önünü açtı.",
        realLifeExamples = listOf(
            "Verimsiz bir projeye 'zaten çok emek verdim' diyerek devam etmek (Batık Maliyet Yanılgısı).",
            "Telefonu çalışma masasının uzağına koyarak ortam tasarımı yapmak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Daniel Kahneman", "1934–2024", "İkili süreç kuramı (Sistem 1/2) ve karar verme yanlılıklarının öncüsü."),
            ThinkerHighlight("Aaron Beck", "1921–2021", "Bilişsel Terapi'nin kurucusu; otomatik düşünceler ve bilişsel çarpıtmalar."),
            ThinkerHighlight("B. F. Skinner", "1904–1990", "Edimsel koşullanma ve çevresel pekiştirme analizi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Karar Modları",
                "Sistem 1", "Hızlı, zahmetsiz, duygusal ve kalıp yargılara açık.",
                "Sistem 2", "Yavaş, enerji tüketen, mantıksal denetim yapabilen."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Sistem 1 Kısayolları", "üretir", "Bilişsel Yanlılık"),
            ConceptRelation("Bilişsel Çarpıtma", "tetikler", "Olumsuz Duygu"),
            ConceptRelation("Ortam Tasarımı", "kolaylaştırır", "Kalıcı Alışkanlık")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Yatırımda Kayıptan Kaçınma",
            scenario = "Bir yatırımcı değeri %35 düşen bir hisseyi temel verileri bozulmasına rağmen 'zararı realize etmemek için' elinde tutmaya devam eder.",
            analysisQuestions = listOf(
                "Burada hangi iki bilişsel yanlılık devrededir?",
                "Sistem 2 perspektifinden karar nasıl verilmelidir?"
            ),
            keyTakeaway = "Geçmiş maliyet geri gelmez; karar, geçmişe göre değil bugünkü en iyi alternatife göre verilmelidir."
        ),
        reflectionQuestions = listOf(
            "Son bir ayda doğrulama yanlılığına düştüğünüz bir konu oldu mu?"
        ),
        summary = "Zihnimiz enerji tasarrufu için hızlı kısayollar kullanır; bu da karar hatalarına yol açabilir. Yanlılıkları tanımak ve ortamı bilinçli tasarlamak öz-yönetimi güçlendirir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Batık maliyet yanılgısı nedir?", "Geri alınamayacak geçmiş yatırımlar yüzünden verimsiz bir karara devam etme hatası.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m02_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "Bir mağazada 2.000 TL'den 1.200 TL'ye indiği yazan bir ürünü aslında ihtiyacı olmadığı halde ucuz algılayıp alan kişi hangi etkiden etkilenmiştir?",
                options = listOf("Çıpalama (Anchoring) Etkisi", "Plasebo Etkisi", "Yakınsal Gelişim", "Bölünmüş Dikkat"),
                correctOptionIndex = 0,
                explanation = "İlk görülen 2.000 TL fiyatı zihinde referans (çıpa) oluşturur ve 1.200 TL'yi gerçek değerinden bağımsız olarak cazip gösterir."
            ),
            QuizQuestion(
                id = "m02_l02_q2",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "Kahneman'a göre yalnızca eğitimsiz insanlar bilişsel yanlılıklara düşer; uzmanlar Sistem 1 hatalarından tamamen muaftır.",
                correctBool = false,
                explanation = "Yanlış. Bilişsel yanlılıklar insan zihninin evrimsel mimarisinden kaynaklanır; uzmanlar da kontrol mekanizması kurmazsa aynı hatalara düşebilir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Alışkanlık Zinciri Kurulumu",
            instructions = "Her gün yapmak istediğiniz 15 dakikalık okuma veya tekrar alışkanlığı için 'Tetikleyici - Rutin - Ödül' üçlüsünü somutlaştırın.",
            deliverableHint = "Profil > Çalışma Planlayıcısı bölümünden günlük hedefinizi seçin."
        ),
        references = listOf(
            AcademicReference("Daniel Kahneman, Hızlı ve Yavaş Düşünme, Varlık Yayınları", ReferenceType.AKADEMIK_KITAP, "Sistem 1/2 ve bilişsel yanlılıkların başyapıtı."),
            AcademicReference("Aaron T. Beck, Cognitive Therapy and the Emotional Disorders", ReferenceType.AKADEMIK_KITAP, "Bilişsel çarpıtmaların analizi.")
        )
    ),
    Lesson(
        id = "m02_l03",
        monthNumber = 2,
        weekNumber = 4,
        discipline = DisciplineId.PSIKOLOJI,
        title = "Motivasyon, Duygular, Sosyal Psikoloji ve Dayanıklılık",
        durationMinutes = 20,
        lengthCategory = LessonLength.STANDART,
        purpose = "İçsel ve dışsal motivasyonu, duygusal düzenlemeyi, sosyal uyum baskısını ve Viktor Frankl'ın anlam merkezli psikolojik dayanıklılık yaklaşımını öğrenmek.",
        learningOutcomes = listOf(
            "Tanıma: Öz-belirleme kuramının (özerklik, yetkinlik, ilişkisellik) üç temel ihtiyacını sayar.",
            "Anlama: Sosyal psikolojideki uyma (Asch) ve itaat (Milgram) mekanizmalarını açıklar.",
            "Uygulama: Zorlayıcı durumlarda anlam odaklı yeniden çerçeveleme uygular.",
            "Analiz ve Sentez: Felsefedeki Arendt kavramıyla sosyal psikoloji deneylerini karşılaştırır."
        ),
        prerequisites = listOf("Ders 4 ve 5."),
        keyConcepts = listOf(
            ConceptCard("İçsel Motivasyon", "Ödül veya ceza için değil, öğrenme ve merakın kendisi için eyleme geçme.", "Puan için değil dünyayı anlamak için okumak."),
            ConceptCard("Psikolojik Dayanıklılık (Resilience)", "Zorluk, kayıp ve belirsizlik karşısında dağılmadan uyum sağlama ve anlam üretebilme kapasitesi.", "Krizi kimliğin sonu değil dönüşüm anı olarak görmek."),
            ConceptCard("Sosyal Uyma (Conformity)", "Grubun açık veya örtük baskısıyla bireyin kendi algısından bile vazgeçip çoğunluğa katılması.", "Asch deneyinde bariz kısa çizgiyi grup yüzünden uzun demek.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Motivasyon ve Duyguların İşlevi",
                "Duygular aklın düşmanı değil, bilgi taşıyan sinyallerdir. Korku tehlikeye, öfke sınır ihlaline, üzüntü ise kayıp ve değer verdiğimiz şeylere işaret eder. Öz-belirleme kuramına göre kalıcı motivasyon dışsal ödüllerle değil; özerklik (seçim hakkı), yetkinlik (ustalaşma hissi) ve aidiyetle beslenir."
            ),
            ContentSection(
                "Sosyal Etki: Grup Baskısı ve Sorumluluk Dağılması",
                "İnsan sosyal bir varlıktır; ancak grup içinde bireysel yargımız zayıflayabilir. Kalabalık ortamlarda 'Nasıl olsa bir başkası yardım eder' düşüncesiyle harekete geçmemek (seyirci etkisi) bunun tipik örneğidir. Bunu bilmek, kalabalık içinde bile bilinçli bireysel inisiyatif almamızı sağlar."
            ),
            ContentSection(
                "Viktor Frankl ve Anlam Arayışı",
                "Logoterapinin kurucusu Viktor Frankl, en ağır koşullarda bile insanın elinden alınamayacak tek özgürlüğün 'verilen koşullara karşı takınacağı tutumu seçme özgürlüğü' olduğunu belirtir. Bu yaklaşım Stoacı felsefe ile doğrudan örtüşür."
            )
        ),
        historicalBackground = "1950–60'larda Solomon Asch ve Stanley Milgram deneyleri insan davranışının durumsal baskılara ne kadar duyarlı olduğunu göstererek sosyal psikolojiyi dönüştürdü.",
        realLifeExamples = listOf(
            "Bir toplantıda herkes hatalı bir fikri onaylarken veriye dayanarak nazikçe itiraz edebilmek."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Viktor Frankl", "1905–1997", "Logoterapi ve anlam merkezli psikolojik dayanıklılığın kurucusu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Motivasyon Kaynakları",
                "Dışsal Ödül", "Kısa vadede hız verir; ödül kesildiğinde ilgi hızla düşebilir.",
                "İçsel Merak ve Anlam", "Uzun vadeli 12 aylık derin öğrenmeyi ayakta tutan asıl yakıttır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Anlam ve Amaç", "besler", "Psikolojik Dayanıklılık"),
            ConceptRelation("Özerklik + Yetkinlik", "üretir", "İçsel Motivasyon")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Kalabalıkta Sessiz Kalmak",
            scenario = "On kişilik bir proje ekibinde teslim tarihinin gerçekçi olmadığı herkesçe bilinmektedir ama ilk konuşan yönetici iyimser olduğu için kimse itiraz etmez.",
            analysisQuestions = listOf(
                "Grup düşüncesi (groupthink) nasıl kırılır?"
            ),
            keyTakeaway = "Sağlıklı ekip kültüründe 'şeytanın avukatlığı' kurumsallaştırılmalıdır."
        ),
        reflectionQuestions = listOf(
            "Sizi 12 aylık bu öğrenme yolculuğunda ayakta tutacak en derin içsel neden nedir?"
        ),
        summary = "Kalıcı başarı dışsal alkışla değil içsel anlam, özerklik, duygusal farkındalık ve sosyal baskıya karşı bağımsız düşünebilme cesaretiyle sağlanır.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Öz-belirleme kuramının üç temel ihtiyacı nelerdir?", "Özerklik, yetkinlik ve ilişkisellik (aidiyet).")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m02_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Viktor Frankl'ın 'İnsanın Anlam Arayışı' kitabında temellendirdiği psikoterapi ekolü hangisidir?",
                options = listOf("Davranışçılık", "Logoterapi", "Psikanaliz", "Gestalt"),
                correctOptionIndex = 1,
                explanation = "Frankl insanın temel itici gücünün haz veya güç değil 'anlam istenci' olduğunu savunan Logoterapiyi kurmuştur."
            ),
            QuizQuestion(
                id = "m02_l03_q2",
                type = QuestionType.OPEN_ENDED_ANALYSIS,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "1. ayda gördüğümüz Hannah Arendt'in 'düşünme sorumluluğu' ile bu dersteki Asch sosyal uyma bulgularını birleştirerek bireyin özgürlüğünü nasıl koruyabileceğini tartışın.",
                explanation = "İyi bir analiz, grup baskısının eleştirel düşünmeyi nasıl uyuşturduğunu ve içsel ölçütlere sahip olmanın önemini vurgular.",
                rubricHint = "Felsefe-psikoloji disiplinler arası bağlantısı kurulmalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Anlam Bildirgesi",
            instructions = "Kendi Düşüncelerim alanına bu 12 aylık akademiden beklediğiniz zihinsel dönüşümü 5 cümlede yazın.",
            deliverableHint = "Zorlandığınız günlerde bu nota geri dönüp okuyacaksınız."
        ),
        references = listOf(
            AcademicReference("Viktor E. Frankl, İnsanın Anlam Arayışı, Okuyan Us Yayınları", ReferenceType.AKADEMIK_KITAP, "Anlam ve dayanıklılık psikolojisinin temel eseri.")
        )
    ),

    // ======================== AY 3 — BİLİMSEL DÜŞÜNME (BİLİM) ========================
    Lesson(
        id = "m03_l01",
        monthNumber = 3,
        weekNumber = 1,
        discipline = DisciplineId.BILIM,
        title = "Bilimsel Yöntem, Yanlışlanabilirlik ve Nedensellik vs Korelasyon",
        durationMinutes = 30,
        lengthCategory = LessonLength.DERINLEMESINE,
        purpose = "Bilimi yanlış bilimden ayıran ölçütleri (Karl Popper), hipotez-teori-kanun hiyerarşisi yanılgısını ve korelasyon ile nedensellik arasındaki farkı kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: Hipotez, bilimsel teori ve bilimsel kanun kavramlarını doğru tanımlar.",
            "Anlama: Yanlışlanabilirlik ilkesinin neden bilimin ayırt edici ölçütü olduğunu açıklar.",
            "Uygulama: İstatistiksel grafiklerdeki sahte korelasyonları ve karıştırıcı değişkenleri tespit eder.",
            "Analiz ve Sentez: Doğal bilimler ile yanlış bilim iddialarını metodolojik açıdan ayrıştırır."
        ),
        prerequisites = listOf("Ay 1 Epistemoloji ve Ay 2 Bilişsel Yanlılıklar."),
        keyConcepts = listOf(
            ConceptCard("Yanlışlanabilirlik (Popper)", "Bir önermenin bilimsel sayılabilmesi için hangi gözlemle çürütülebileceğinin ilkece belirtilebilmesi.", "'Yarın yağmur yağabilir veya yağmayabilir' yanlışlanamaz olduğu için bilimsel tahmin değildir."),
            ConceptCard("Bilimsel Teori", "Çok sayıda deney ve gözlemle doğrulanmış kapsamlı açıklama çerçevesi (kanuna dönüşecek 'tahmin' değildir!).", "Evrim teorisi veya Genel Görelilik birer tahmin değil, kapsamlı açıklama sistemidir."),
            ConceptCard("Korelasyon != Nedensellik", "İki değişkenin birlikte artıp azalması birinin diğerine neden olduğunu kanıtlamaz.", "Yazın dondurma satışları ile boğulma vakaları birlikte artar; ortak neden sıcak havadır.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Bilim Nedir? Kanıt ve Deney Kültürü",
                "Galileo Galilei'nin düşen cisimler ve teleskop gözlemleriyle başlattığı modern bilim, otorite metinlerine değil tekrarlanabilir deney ve matematiksel modele dayanır. Richard Feynman'ın dediği gibi bilimin ilk ilkesi 'kendini kandırmamaktır ve en kolay kandırabileceğin kişi yine sensin'. Bu yüzden çift kör deneyler, kontrol grupları ve hakem değerlendirmesi zorunludur."
            ),
            ContentSection(
                "Hipotez, Teori ve Kanun Yanılgısı",
                "Günlük dilde 'teori' kelimesi 'tahmin' anlamında kullanılır; oysa bilimde teori, olguları açıklayan en yüksek düzeyli bütünlüklü çerçevedir. Bilimsel kanun genellikle bir ilişkinin matematiksel özetini (ne olduğunu) verirken, teori o ilişkinin mekanizmasını (neden ve nasıl olduğunu) açıklar."
            ),
            ContentSection(
                "İstatistik, Olasılık ve Sahte Bilimden Korunma",
                "Carl Sagan'ın 'Baloney Detection Kit' (Saçmalık Tespit Kiti) yaklaşımı; bağımsız doğrulamayı, nicel veriyi, alternatif hipotezleri ve istatistiksel anlamlılığı vurgular. Örneklem büyüklüğü küçük veya kontrol grubu olmayan iddialar bilimsel kanıt sayılmaz."
            )
        ),
        historicalBackground = "16.–17. yüzyıl Bilimsel Devrimi (Galileo, Newton) ile başlayan deneysel paradigma, 20. yüzyılda Karl Popper'ın bilim felsefesiyle yöntemsel sınırlarına kavuştu.",
        realLifeExamples = listOf(
            "'Kahve içenlerin daha uzun yaşadığı' haberinde sosyoekonomik düzey veya yaşam tarzı gibi gizli değişkenleri sorgulamak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Galileo Galilei", "1564–1642", "Modern gözlemsel astronomi ve deneysel fiziğin öncüsü."),
            ThinkerHighlight("Karl Popper", "1902–1994", "Bilimi sözde bilimden ayıran yanlışlanabilirlik ölçütünün kuramcısı."),
            ThinkerHighlight("Richard Feynman", "1918–1988", "Kuantum elektrodinamiği öncüsü ve bilimsel dürüstlük savunucusu."),
            ThinkerHighlight("Carl Sagan", "1934–1996", "Bilimsel şüphecilik ve popüler bilim okuryazarlığının simge ismi.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Bilim ve Sözde Bilim",
                "Bilimsel Yaklaşım", "Yanlışlanabilir testler kurar, hata payını raporlar, yeni kanıtla güncellenir.",
                "Sözde (Yanlış) Bilim", "Anekdotlara dayanır, çürütülünce bahaneler üretir, kesinlik iddiasındadır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Gözlem", "üretir", "Hipotez"),
            ConceptRelation("Kontrollü Deney", "sınar", "Yanlışlanabilirlik"),
            ConceptRelation("Ortak Değişken", "açıklar", "Sahte Korelasyon")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Mucize Takviye Reklamı",
            scenario = "Bir şirket, 12 gönüllü üzerinde yaptığı ankette kullanıcıların %83'ünün kendini daha enerjik hissettiğini söyleyerek yeni bir takviyeyi 'bilimsel olarak kanıtlandı' diye pazarlıyor.",
            analysisQuestions = listOf(
                "Bu çalışmada hangi üç yöntemsel eksik vardır?"
            ),
            keyTakeaway = "Kontrol grubu, plasebo ve yeterli örneklem olmadan yapılan çalışmalar bilimsel kanıt oluşturmaz."
        ),
        reflectionQuestions = listOf(
            "Yakın zamanda 'bilimsel olarak kanıtlanmış' etiketiyle gördüğünüz hangi iddiayı artık daha şüpheyle incelerdiniz?"
        ),
        summary = "Bilimsel düşünce; yanlışlanabilir hipotezler kurma, kontrollü deney, istatistiksel okuryazarlık ve nedenselliği korelasyondan ayırma disiplinidir.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Neden teoriler büyüyünce kanuna dönüşmez?", "Kanun olgular arasındaki örüntüyü tanımlar; teori ise o örüntünün altındaki açıklama mekanizmasıdır.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m03_l01_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Karl Popper'a göre bir kuramı bilimsel yapan temel özellik nedir?",
                options = listOf("Herkes tarafından sevilmesi", "Yanlışlanabilir olması", "Asla değişmemesi", "Eski kitaplarda yazması"),
                correctOptionIndex = 1,
                explanation = "Popper'a göre bilimsel bir kuram, hangi deneysel gözlemle çürütülebileceğini açıkça ortaya koyabilmelidir."
            ),
            QuizQuestion(
                id = "m03_l01_q2",
                type = QuestionType.TRUE_FALSE,
                competency = CompetencyType.ANLAMA,
                prompt = "İki veri seti arasında %95 korelasyon bulunması, birincinin ikinciye kesin olarak neden olduğunu kanıtlar.",
                correctBool = false,
                explanation = "Yanlış. Korelasyon yalnızca birlikte değişimi gösterir; üçüncü bir gizli değişken her ikisini de etkiliyor olabilir."
            )
        ),
        practiceTask = PracticeTask(
            title = "Sahte Korelasyon Avı",
            instructions = "Sağlık veya ekonomi alanından bir haber başlığı seçin; başlığın korelasyonu nedensellik gibi sunup sunmadığını 3 maddede inceleyin.",
            deliverableHint = "Karıştırıcı (confounding) değişken adaylarını sıralayın."
        ),
        references = listOf(
            AcademicReference("Carl Sagan, Karanlık Bir Dünyada Bilimin Mum Işığı, TÜBİTAK Yayınları", ReferenceType.AKADEMIK_KITAP, "Bilimsel şüphecilik ve sözde bilim eleştirisi."),
            AcademicReference("Karl Popper, Bilimsel Araştırmanın Mantığı, YKY", ReferenceType.AKADEMIK_KITAP, "Yanlışlanabilirlik kuramı.")
        )
    ),
    Lesson(
        id = "m03_l02",
        monthNumber = 3,
        weekNumber = 2,
        discipline = DisciplineId.BILIM,
        title = "Evrenin ve Maddenin Yasaları: Fizik, Kimya, Jeoloji ve Astronomi",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Doğa bilimlerinin temel sütunlarını (Newton mekaniği, Einstein göreliliği, atom ve kimyasal bağlar, jeolojik zaman ve kozmos) bütünleşik bir bakışla kavramak.",
        learningOutcomes = listOf(
            "Tanıma: Newton ve Einstein fiziği, atom yapısı, periyodik düzen ve levha tektoniğini tanımlar.",
            "Anlama: Enerjinin korunumu ve kimyasal tepkimelerin gündelik doğadaki işleyişini açıklar.",
            "Uygulama: Astronomik ölçekleri ve derin jeolojik zamanı insanlık tarihiyle ölçeklendirir."
        ),
        prerequisites = listOf("Ders 7: Bilimsel Yöntem."),
        keyConcepts = listOf(
            ConceptCard("Görelilik (Einstein)", "Uzay ve zamanın mutlak değil gözlemciye ve kütleçekimine bağlı olarak bükülebilir bir doku olması.", "GPS uydularının saatleri görelilik düzeltmesi olmadan günde kilometrelerce sapardı."),
            ConceptCard("Kimyasal Bağ ve Periyot", "Elementlerin elektron paylaşımı/alışverişi ile yeni özelliklere sahip bileşikler kurması.", "Yanıcı hidrojen ile yakıcı oksijenin birleşip suyu oluşturması."),
            ConceptCard("Derin Zaman ve Levha Tektoniği", "Dünya kabuğunun milyonlarca yıl boyunca dev levhalar halinde hareket etmesi.", "Depremlerin ve dağ kuşaklarının tesadüf değil levha sınırlarının sonucu olması.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Fizik: Hareketten Uzay-Zaman Dokusuna",
                "Isaac Newton Principia'da elmayı düşüren yerçekimi ile gezegenleri yörüngede tutan kütleçekiminin aynı evrensel yasa olduğunu gösterdi. 20. yüzyılda Albert Einstein, ışık hızının sabitliğinden yola çıkarak uzay ve zamanı birleştirdi (E=mc²) ve kütleçekimini uzay-zamanın bükülmesi olarak yeniden tanımladı."
            ),
            ContentSection(
                "Kimya ve Radyoaktivite",
                "Marie Curie'nin radyum ve polonyum keşifleri, atomun bölünmez bir bilye değil kendi iç enerjisine sahip dinamik bir yapı olduğunu kanıtladı. Kimya, atomların dış kabuk elektronlarının dansıdır; canlılıktan endüstriye kadar her dönüşüm elektron düzenlenmesine dayanır."
            ),
            ContentSection(
                "Astronomi ve Jeoloji: Kozmik Ölçeğimiz",
                "Yaklaşık 13,8 milyar yıllık evrende Dünya 4,5 milyar yaşındadır. Jeoloji kaya katmanları ve levha tektoniği üzerinden gezegenin geçmişini okurken, astronomi yıldızların içinde üretilen ağır elementlerin bedenlerimizi oluşturduğunu gösterir."
            )
        ),
        historicalBackground = "1687 Principia'dan 1905–1915 Görelilik kuramlarına ve 1960'larda levha tektoniğinin kabulüne uzanan süreç, doğa bilimlerinin ortak matematiksel dilini kurdu.",
        realLifeExamples = listOf(
            "Telefonunuzdaki GPS navigasyonunun hem özel hem genel görelilik düzeltmelerini her saniye kullanması."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Isaac Newton", "1643–1727", "Hareket yasaları, evrensel kütleçekimi ve kalkülüsün kurucusu."),
            ThinkerHighlight("Albert Einstein", "1879–1955", "Özel ve genel görelilik ile fotoelektrik etki kuramcısı."),
            ThinkerHighlight("Marie Curie", "1867–1934", "İki farklı dalda Nobel kazanan ilk bilim insanı; radyoaktivite araştırmalarının öncüsü.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Fizik Ölçekleri",
                "Newton Mekaniği", "Günlük hızlarda ve orta ölçeklerde mükemmel kesinlikle çalışır.",
                "Einstein & Kuantum", "Işık hızına yakın, dev kütleli veya atom altı ölçeklerde geçerli derin çerçevedir."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("Yıldız Çekirdeği", "üretir", "Ağır Elementler (Kimya)"),
            ConceptRelation("Kütle", "büker", "Uzay-Zaman"),
            ConceptRelation("Manto Konveksiyonu", "sürükler", "Tektonik Levhalar")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Bilimsel Modeller Neden Güncellenir?",
            scenario = "Bir öğrenci 'Einstein Newton'u düzelttiğine göre demek ki Newton tamamen yanlıştı ve fizikçilere güvenilmez' diye düşünür.",
            analysisQuestions = listOf(
                "Yeni bir kuramın eskiyi kapsaması (yaklaşıklık ilkesi) neden bir başarısızlık değil ilerlemedir?"
            ),
            keyTakeaway = "Einstein Newton'u çöpe atmadı; Newton mekaniğinin düşük hızlardaki çok hassas bir özel durum olduğunu gösterdi."
        ),
        reflectionQuestions = listOf(
            "Kozmik ve jeolojik zaman ölçeğini düşünmek günlük sorunlarınıza bakışınızı nasıl etkiler?"
        ),
        summary = "Fizik, kimya, jeoloji ve astronomi; enerjiden maddeye, Dünya kabuğundan galaksilere kadar aynı doğa yasalarının farklı ölçeklerdeki tezahürünü inceler.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("GPS sistemleri neden Einstein göreliliğine ihtiyaç duyar?", "Uydular hem hızlı hareket eder (özel görelilik) hem de Dünya yüzeyinden uzakta daha zayıf kütleçekimindedir (genel görelilik).")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m03_l02_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.BILGI,
                prompt = "Radyoaktivite üzerine öncü çalışmalarıyla hem Fizik hem Kimya alanında Nobel Ödülü alan bilim insanı kimdir?",
                options = listOf("Rosalind Franklin", "Marie Curie", "Ada Lovelace", "Lise Meitner"),
                correctOptionIndex = 1,
                explanation = "Marie Curie 1903'te Fizik, 1911'de Kimya Nobel Ödülü'nü kazanmıştır."
            ),
            QuizQuestion(
                id = "m03_l02_q2",
                type = QuestionType.FILL_IN_BLANK,
                competency = CompetencyType.BILGI,
                prompt = "Dünya'nın dış kabuğunun dev parçalar halinde hareket ederek depremleri ve kıta kaymasını açıkladığı kurama ________ tektoniği denir.",
                acceptedAnswers = listOf("levha", "plaka"),
                explanation = "Levha (plaka) tektoniği modern yerbiliminin temel paradigmasıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Bilimsel Ölçek Karşılaştırması",
            instructions = "Gündelik bir cihazın (ör. akıllı telefon veya mikrodalga) çalışmasında yer alan 1 fizik, 1 kimya ve 1 matematiksel ilkeyi listeleyin.",
            deliverableHint = "Pil elektrokimyası, yarı iletken fiziği ve ikili sayı sistemi iyi örneklerdir."
        ),
        references = listOf(
            AcademicReference("Richard Feynman, Fizik Yasaları Üzerine, TÜBİTAK Yayınları", ReferenceType.AKADEMIK_KITAP, "Fizik yasalarının birliği ve matematiksel güzelliği."),
            AcademicReference("TÜBİTAK Bilim ve Teknik Arşivi", ReferenceType.BILIMSEL_KURUM, "Türkçe bilimsel kurum kaynağı.")
        )
    ),
    Lesson(
        id = "m03_l03",
        monthNumber = 3,
        weekNumber = 4,
        discipline = DisciplineId.BILIM,
        title = "Yaşamın Bilimi: Biyoloji, Genetik, Ekoloji ve Matematiksel Düşünme",
        durationMinutes = 25,
        lengthCategory = LessonLength.STANDART,
        purpose = "Canlılığın hücresel ve genetik temelini, evrimsel uyumu, ekosistemlerin karşılıklı bağımlılığını ve matematiksel modellemenin bilimsel düşüncedeki yerini kavratmak.",
        learningOutcomes = listOf(
            "Tanıma: DNA, gen, doğal seçilim ve ekosistem döngülerini tanımlar.",
            "Anlama: Üstel büyüme ile doğrusal büyüme arasındaki farkı matematiksel olarak açıklar.",
            "Uygulama: Antibiyotik direnci veya salgın eğrilerini ekolojik ve olasılıksal mantıkla yorumlar."
        ),
        prerequisites = listOf("Ders 7 ve Ders 8."),
        keyConcepts = listOf(
            ConceptCard("Doğal Seçilim (Darwin)", "Çevreye uyum sağlayan kalıtsal özelliklerin nesiller boyunca daha yüksek oranda aktarılması.", "Gereksiz antibiyotik kullanımının dirençli bakterileri hayatta bırakması."),
            ConceptCard("Ekoloji", "Canlıların birbirleriyle ve fiziksel çevreleriyle oluşturduğu enerji ve madde ağı.", "Bir bölgedeki yırtıcı türün yok olmasının bitki örtüsünü bile değiştirmesi."),
            ConceptCard("Matematiksel Düşünme (Üstel Artış)", "Her adımda sabit sayı eklemek yerine sabit oranla çarpılan büyüme.", "Katlanan kağıt veya bileşik faiz mantığı.")
        ),
        detailedContent = listOf(
            ContentSection(
                "Hücreden Genetiğe: Yaşamın Kodları",
                "Tüm canlılık hücrelerden oluşur ve kalıtsal bilgiyi DNA molekülünde taşır. Charles Darwin ve Alfred Russel Wallace 19. yüzyılda türlerin ortak atadan çeşitlendiğini gösterirken, 20. yüzyılda Mendel genetiği ve DNA'nın çift sarmal yapısı bu mekanizmanın moleküler temelini açıkladı."
            ),
            ContentSection(
                "Ekoloji ve Karmaşık Sistemler",
                "Ekosistemler doğrusal makineler değil, geri bildirim döngüleriyle çalışan karmaşık ağlardır. Karbon, azot ve su döngüleri bozulduğunda iklimden gıda güvenliğine kadar zincirleme etkiler ortaya çıkar."
            ),
            ContentSection(
                "Olasılık ve Matematiksel Sezgi",
                "İnsan beyni doğrusal düşünmeye yatkındır; ancak doğadaki pek çok süreç (salgın yayılımı, bileşik getiri, radyoaktif bozunma) üstel ve olasılıksaldır. Bayesçi güncelleme ise yeni kanıt geldikçe bir olasılık tahminini nasıl rasyonel biçimde değiştireceğimizi öğretir."
            )
        ),
        historicalBackground = "1859'da Türlerin Kökeni ve 1953'te DNA yapısının çözülmesiyle biyoloji, tasnif biliminden bilgi ve sistem bilimine dönüştü.",
        realLifeExamples = listOf(
            "Bir tarama testinin doğruluğunu yorumlarken hastalığın toplumdaki görülme sıklığını (temel oran / base rate) hesaba katmak."
        ),
        keyThinkers = listOf(
            ThinkerHighlight("Charles Darwin", "1809–1882", "Doğal seçilim yoluyla evrim kuramının kurucusu.")
        ),
        comparativeExplanations = listOf(
            ComparisonItem(
                "Büyüme Biçimleri",
                "Doğrusal Büyüme", "Her adımda +2 eklenir: 2, 4, 6, 8, 10...",
                "Üstel Büyüme", "Her adımda x2 çarpılır: 2, 4, 8, 16, 32... Kısa sürede patlama yaratır."
            )
        ),
        conceptMapLinks = listOf(
            ConceptRelation("DNA Mutasyonu", "besler", "Genetik Çeşitlilik"),
            ConceptRelation("Çevre Baskısı", "seçer", "Uyumlu Özellik"),
            ConceptRelation("Bayes Kuralı", "günceller", "Kanıta Dayalı Olasılık")
        ),
        caseStudy = CaseStudy(
            title = "Örnek Olay: Temel Oran İhmali (Base Rate Fallacy)",
            scenario = "1000 kişiden 1'inde görülen nadir bir hastalık için %99 doğrulukta bir test yapılıyor. Testiniz pozitif çıkıyor. Hastalığı taşıma olasılığınız %99 mudur?",
            analysisQuestions = listOf(
                "999 sağlıklı kişiden %1'i yanlış pozitif verirse kaç sağlıklı kişiye pozitif çıkar?",
                "Gerçek hastalık olasılığı yaklaşık yüzde kaçtır?"
            ),
            keyTakeaway = "Nadir durumlarda 1000 kişiden 1 hasta ve ~10 yanlış pozitif çıkar; gerçek olasılık yalnızca %9 civarındadır!"
        ),
        reflectionQuestions = listOf(
            "Günlük haberlerde yüzde ve oran okurken artık hangi ek soruyu soracaksınız?"
        ),
        summary = "Biyoloji ve ekoloji canlılığı karşılıklı bağımlı bir ağ olarak gösterirken, olasılık ve matematiksel düşünme belirsizlik altında doğru yargıya varmamızı sağlar.",
        activeRecallPrompts = listOf(
            ActiveRecallItem("Antibiyotik direnci neden doğal seçilime örnektir?", "İlaç duyarlı bakterileri öldürürken rastgele direnç genine sahip olanların çoğalmasına alan açar.")
        ),
        quizQuestions = listOf(
            QuizQuestion(
                id = "m03_l03_q1",
                type = QuestionType.MULTIPLE_CHOICE,
                competency = CompetencyType.UYGULAMA,
                prompt = "Bir göldeki su yosunları her gün iki katına çıkıyor ve 30. günde gölün tamamını kaplıyorsa, gölün yarısı kaçıncı günde kaplanmıştır?",
                options = listOf("15. gün", "20. gün", "29. gün", "25. gün"),
                correctOptionIndex = 2,
                explanation = "Üstel büyümede her gün iki katına çıkıldığı için tam dolmadan bir önceki gün (29. gün) gölün yarısı kaplıdır."
            ),
            QuizQuestion(
                id = "m03_l03_q2",
                type = QuestionType.CASE_RESOLUTION,
                competency = CompetencyType.ANALIZ_SENTEZ,
                prompt = "Neden 'gereksiz antibiyotik kullanımı sadece bireye değil tüm topluma zarar verir' ifadesi hem ekoloji hem de evrimsel biyoloji açısından doğrudur? Açıklayın.",
                explanation = "Dirençli bakteri suşlarının seçilip çevreye yayılması ortak mikrobiyal ekosistemi ve gelecek tedavilerin etkinliğini tehdit eder.",
                rubricHint = "Doğal seçilim ve ortak ekosistem etkisi vurgulanmalıdır."
            )
        ),
        practiceTask = PracticeTask(
            title = "Olasılık ve Test Yorumlama",
            instructions = "Bir sağlık veya finans istatistiğinde 'mutlak risk' ile 'göreli risk' arasındaki farkı kısa bir örnekle not alın.",
            deliverableHint = "Örneğin '%50 artış' binde 1'den binde 1,5'a çıkmak olabilir."
        ),
        references = listOf(
            AcademicReference("Charles Darwin, Türlerin Kökeni, İş Bankası Kültür Yayınları", ReferenceType.BIRINCIL_TARIHSEL, "Evrimsel biyolojinin kurucu metni."),
            AcademicReference("Jordan Ellenberg, Nasıl Yanılmazsınız? Matematiksel Düşünmenin Gücü, Pegasus", ReferenceType.AKADEMIK_KITAP, "Olasılık ve istatistik sezgisi.")
        )
    )
)
