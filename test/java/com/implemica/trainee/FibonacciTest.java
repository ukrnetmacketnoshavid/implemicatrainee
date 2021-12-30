package com.implemica.trainee;

import com.implemica.trainee.util.Messages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();

    @BeforeEach
    public void setSystemOut() throws IOException {
        OUT_CONTENT.close();
        ERR_CONTENT.close();
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    @AfterEach
    public void setSystemOutDefault() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    void testValidData(String real, String expectedResult) throws IOException {
        String data = real + System.lineSeparator();
        String expected = Messages.FIB_MESSAGE_INPUT + System.lineSeparator();
        expected += expectedResult + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Fibonacci.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }

    void testInvalidData(char real) throws IOException {
        String expected = Messages.FIB_MESSAGE_INPUT + System.lineSeparator();
        expected += Messages.EXCEPTION_MESSAGE + System.lineSeparator();
        String data = real + System.lineSeparator();

        try (InputStream is = new ByteArrayInputStream(data.getBytes())) {
            System.setIn(is);
            Fibonacci.main(new String[]{});
        }

        String outContent = OUT_CONTENT.toString();
        assertEquals(expected, outContent);

        OUT_CONTENT.reset();
    }


    @Test
    void randomArgs() throws IOException {
        //region Random arguments 0:999 (count 100)
        testValidData("44", "701408733");
        testValidData("786", "82186452381005908303921699339795353860699455101453346636441756584240231663355338845390501466361958937618980292830190307844401857073430837440997251860323019906010648");
        testValidData("694", "4874581448301122336922268895532800198289478061460705444533362455679490302313612384437870442661399928780266855508500626326652774489500305984505967");
        testValidData("178", "7084593923980518516849609894969925639");
        testValidData("921", "1343172118197190792578750244364655512769497592664280789098578949032851734178642763891810536882491709396961194463386647005135217509904680325466274697080777803057051246741824115181280325156445346");
        testValidData("643", "107045318469544833137513810461138676125700361713371062225356384423352406508989872286703546023838895248020118486827785715412687077502837");
        testValidData("912", "17670258618864975009258339416537971760012675183748670183843230539845347074301607362326211014092320301155064788141495664328603298786390904066437630628079431525530236022705233071673280212746944");
        testValidData("207", "8146227408089084511865756065370647467555938");
        testValidData("100", "354224848179261915075");
        testValidData("119", "3311648143516982017180081");
        testValidData("446", "722774046296497854662108306212056471121727445630906510906079096662473988285986629470097563353");
        testValidData("500", "139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125");
        testValidData("628", "78478930303301394239843272739152284597168350307833709759211620078672098597363314490123518850316619782518365389244854105885364519011");
        testValidData("977", "678327652996327247779165248550766470831037490502555950658956568576707887540426543340885860756645125827972888077357233723647620612838774164351083221391085478586558455287774853825925814085355698767729652157");
        testValidData("360", "769246427201094785080787978422393713094534885688979999504447628313150135520");
        testValidData("315", "303124944601307480151388995590175408058857436768033423077509087810");
        testValidData("681", "9356167927584356870497608935205215407421313285040837712795857869873919680576803330075673420058256777291515175125872666989233785694260034313506");
        testValidData("629", "126981576631475750014906387931931581692734251880848776469071481399496762848826070039224556421753327196738843828004058553268696153829");
        testValidData("673", "199157600921893687120946774967741139719553336404271541042268988715143669561498617223356051795193904924125069739438025770149158497269999660993");
        testValidData("692", "1861924432321340358923654223577184602713974790372179157621272372092724304661935302195881518126371396586551324571164935066116342041973532071187629");
        testValidData("168", "57602132235424755886206198685365216");
        testValidData("234", "3577855662560905981638959513147239988861837901112");
        testValidData("956", "27713991332898951849369141694887577580844268614029932119435071462261690126253987857716252505338630328337483625842233853861187686551179198305850552365314432013776222604488073014165694290306137408859517");
        testValidData("686", "103761492339168842018587530858475070677646106130118753779820168511287979391742270610548339362599898075442971926458625775220986965441725618892473");
        testValidData("978", "1097557198057001938453841363644415006374573595389643436520255061711780089392489003778952798974711705543381888276590315395738796138788161499726039945177057123749403326820574003343599955000130556475552464664");
        testValidData("938", "4796468010215467919076529823006083464780245926722455425215511529385797164306636491296358572434288868069007497587075212013832712901412374265385928976436467726430908640666426913865194845300474316369");
        testValidData("617", "394356525940980789237251503981788661105973067486900420888553226429695525202946913198807090452871827112484641102219993421466953197");
        testValidData("373", "400778865046997419409593818195095036058794082069603285936485366789883567055193");
        testValidData("818", "400317635020648612310790041088238592255743759132676778939157794065829229936154295301659883075008037887643872516650906325635548507651579922770721393896139633111131764256609");
        testValidData("832", "337467291449099146757502470290836158590234343320848346916393368492084016526053835309718655151992113871219648483672951186416690553446928534675098781707516012681429879198633659");
        testValidData("170", "150804340016807970735635273952047185");
        testValidData("603", "467801993911057346969253632393329698441821925792111695787002567703451068793258021745557947676079828499403918483241873884718402");
        testValidData("697", "20649058376862930966764036030509631786019440726559168907424267534212002902244191317797589177054256850728231628400173261501031188363554465780154577");
        testValidData("140", "81055900096023504197206408605");
        testValidData("423", "11279421434798829164267456416982623413744225016940372471505281055817787346703464230494882");
        testValidData("513", "72639767602615659833415769076743441675530755653534070653184546540063470576806357692953027861477736726533858");
        testValidData("190", "2281217241465037496128651402858212007295");
        testValidData("704", "599533884777687896776005738659978075404416227379871730343634213152746766776753741738279860246291162932098104341810018762401600714179499797853539653");
        testValidData("41", "165580141");
        testValidData("943", "53193645363047428491370308446469609309956480560844941363428847958576455066558240319701707035338994711852685519455642703137273765731372642540796377854126340204575352673825498297174102574526190607037");
        testValidData("966", "3408595602048842192085573042563767667651357310208955412954617302492150110099095239235159792443674132892173477096414015495488103362893809061571240441775097232478525591507346647078766720398903771099444488");
        testValidData("714", "73737793246207310181111783586281770474544906489662762126822474855332172823238396621423984939851151640719286567187123807149070235069588974830000871352");
        testValidData("233", "2211236406303914545699412969744873993387956988653");
        testValidData("986", "51561825446860938110793076583476206104187289543801551805526760038311388677693583538642998241029745630862414851028572631651719101588411748442058105785232737257797440672942788044026840565796908594291106493873");
        testValidData("348", "2388987100892084569134167581129323824600775934984068529143761591653482576");
        testValidData("43", "433494437");
        testValidData("937", "2964380256264737027751613972484188318342902629413059592340951144698015550421029677866167691172936753460125107751808201405322412504014729683541789208526919678625616709698816597250508762424479215217");
        testValidData("545", "353817194107882765682973465820020735142876652434685818788140779559062385248698970169949188915715679402458524638045");
        testValidData("775", "412986819712346493611195411383827409934884076105338432187865946722511205681167419748123598380002157658381536968436929608311101496272839009791376254928568258611725");
        testValidData("901", "88793027306605937532517516910637647045239090036365766884466525589158360259770006891772711976920559280382807770394537471560061517120086971996377683290300054868066659454250625417891167369401");
        testValidData("179", "11463113765491467695340528626429782121");
        testValidData("200", "280571172992510140037611932413038677189525");
        testValidData("356", "112231541198094907919471875344539277405965520328288418022089107495493842317");
        testValidData("346", "912511873855702065852730553217787283194150290277873860991322859307033303");
        testValidData("138", "30960598847965113057878492344");
        testValidData("149", "6161314747715278029583501626149");
        testValidData("343", "215414832505658809004682396169711233230800418578767753330908886771798637");
        testValidData("896", "8006462277582187659515819010005631083285326436520662969075190367723975226011685499453801676865275236376322149753593676553210972296951449120936850931134870481678508226750540122559762585797");
        testValidData("358", "293825989466396564333419951255644330166833468672422805842178911936214659279");
        testValidData("850", "1949885951587339044875793733356219760673772926586260591210358470405525665390243100575540781157408285819450131557173898143210104351541330710230926558457389046268596497514105167175");
        testValidData("132", "1725375039079340637797070384");
        testValidData("194", "15635695580168194910579363790217849593217");
        testValidData("413", "91708675472160090711036102616287632089318911882123915231537729562617689923506638302133");
        testValidData("512", "44893845313309942978077298160660626646181883623886239791269694466661322268805744081870933775586567858979269");
        testValidData("503", "590606256885570541884749772942551428042092906415213688441386695785274827100237057501589632981816458291377");
        testValidData("751", "3983206532963909120418404757324525361518628307077248076132424727688912059360140985766913457277311357354018072741855874826116981382251922695786329432008306749");
        testValidData("295", "20038668997554240570909178165665757608500558774338041350112205");
        testValidData("518", "805587367387474993557712643417250666635155463347703764181824844653814375958479581952978891769754733107167209");
        testValidData("443", "170623807298553611905880623235491323624375649830112453507358412671675285005069415562415412537");
        testValidData("438", "15385139106431711643524221557482279748374290861085515437934408603594478161737710269882076744");
        testValidData("894", "3058196460392500408237626043887111407494030727680570342702189838250663535676380920225341964465603758501876035280436064726336224548056373315160275762890652278750789498701279235077149249192");
        testValidData("254", "54122222371037658776676579571233761483351206693809497");
        testValidData("124", "36726740705505779255899443");
        testValidData("101", "573147844013817084101");
        testValidData("883", "15367433362106577541023664804208466284893300766114398081710415029191302443255038883671520523820306555555107728234131999436214731543989331035480117481491920453278983400009134843095074197");
        testValidData("767", "8790935013445116921936279326741234416424326583142759687535224469376247443807485547238584642560488535894605793267485703553666936794295659289908233590567092465153");
        testValidData("30", "832040");
        testValidData("221", "6867260041627791953052057353082063289320596021");
        testValidData("468", "28624020537229717283244863695841661789309459371299941322398704536965075971940465647510004531000816");
        testValidData("72", "498454011879264");
        testValidData("5", "5");
        testValidData("340", "50852543833066829834000968538942350270948615962802847667687312219838755");
        testValidData("421", "4308355614659046773460502197440934169467600804865999014851680725486111854012659190521721");
        testValidData("892", "1168127103595313565197059121655703139196765746521048059031379147028015381017457261222224216531536039129305956087714517625797701347217670824543976357537086354573860269353297582671685161779");
        testValidData("265", "10770594215935749279482183257489712959102052723690265265");
        testValidData("766", "5433096631200596107874376870220259036974141537070695371984205464938990123414900530571986692045026068474520952254400747023163125965085075901642681564251767371263");
        testValidData("89", "1779979416004714189");
        testValidData("15", "610");
        testValidData("683", "24494765638867511151916549745777100298497746540040085841082376850207163750579175454139937240543282326005200455342074221839890907366049137503897");
        testValidData("811", "13787681438379834200595023949629790030968901073603415504224340988131204131445471231759779408115378269776131884582346904536507241527525506970736829205739365581273575674789");
        testValidData("501", "225591516161936330872512695036072072046011324913758190588638866418474627738686883405015987052796968498626");
        testValidData("463", "2581026321725799052501427023415820835483307041235886067265438236979150980453897702509193714952317");
        testValidData("567", "14012222329320380360477957577902026327196843291708621628988463816224289805272203709520415969453367214046785150335122978");
        testValidData("856", "34989283708770667662541652387090338829811236198946201442126596836151883171881834318520120922197161834728851675807218049427537394442238388079163825565697353574788769715671437617067");
        testValidData("952", "4043416842843241290099041659660249909531224306589421275694000614476488132735776448246323948757113365576561739150547571258244504383875101392580671207240894031964711770362974274621210659067317997121899");
        testValidData("695", "7887238464280904314920883567488415793864981332549231731445452539266256299965289466679859367196428460973982386445836317587189206937027079897824305");
        testValidData("436", "5876600217011727891986851402355662620898026272799849437157779835010586219504163589210317027");
        testValidData("774", "255239891487955204763028765174429290225574698227916993274034384901108067924350903389446104490355598143955494037924409485928126310226314831809427765737181917594632");
        testValidData("439", "24893677995851695395061591712608896875850555449371181438711037372178370103971256950553836461");
        testValidData("593", "3803519665686090773261824444390176450610990315946847165272163502295150248561608391620538293153837330784168723765437946791613");
        //endregion
    }

    @Test
    void subjectArea() throws IOException {
        //region Subject space 0..100
        testValidData("0", "0");
        testValidData("1", "1");
        testValidData("2", "1");
        testValidData("3", "2");
        testValidData("4", "3");
        testValidData("5", "5");
        testValidData("6", "8");
        testValidData("7", "13");
        testValidData("8", "21");
        testValidData("9", "34");
        testValidData("10", "55");
        testValidData("11", "89");
        testValidData("12", "144");
        testValidData("13", "233");
        testValidData("14", "377");
        testValidData("15", "610");
        testValidData("16", "987");
        testValidData("17", "1597");
        testValidData("18", "2584");
        testValidData("19", "4181");
        testValidData("20", "6765");
        testValidData("21", "10946");
        testValidData("22", "17711");
        testValidData("23", "28657");
        testValidData("24", "46368");
        testValidData("25", "75025");
        testValidData("26", "121393");
        testValidData("27", "196418");
        testValidData("28", "317811");
        testValidData("29", "514229");
        testValidData("30", "832040");
        testValidData("31", "1346269");
        testValidData("32", "2178309");
        testValidData("33", "3524578");
        testValidData("34", "5702887");
        testValidData("35", "9227465");
        testValidData("36", "14930352");
        testValidData("37", "24157817");
        testValidData("38", "39088169");
        testValidData("39", "63245986");
        testValidData("40", "102334155");
        testValidData("41", "165580141");
        testValidData("42", "267914296");
        testValidData("43", "433494437");
        testValidData("44", "701408733");
        testValidData("45", "1134903170");
        testValidData("46", "1836311903");
        testValidData("47", "2971215073");
        testValidData("48", "4807526976");
        testValidData("49", "7778742049");
        testValidData("50", "12586269025");
        testValidData("51", "20365011074");
        testValidData("52", "32951280099");
        testValidData("53", "53316291173");
        testValidData("54", "86267571272");
        testValidData("55", "139583862445");
        testValidData("56", "225851433717");
        testValidData("57", "365435296162");
        testValidData("58", "591286729879");
        testValidData("59", "956722026041");
        testValidData("60", "1548008755920");
        testValidData("61", "2504730781961");
        testValidData("62", "4052739537881");
        testValidData("63", "6557470319842");
        testValidData("64", "10610209857723");
        testValidData("65", "17167680177565");
        testValidData("66", "27777890035288");
        testValidData("67", "44945570212853");
        testValidData("68", "72723460248141");
        testValidData("69", "117669030460994");
        testValidData("70", "190392490709135");
        testValidData("71", "308061521170129");
        testValidData("72", "498454011879264");
        testValidData("73", "806515533049393");
        testValidData("74", "1304969544928657");
        testValidData("75", "2111485077978050");
        testValidData("76", "3416454622906707");
        testValidData("77", "5527939700884757");
        testValidData("78", "8944394323791464");
        testValidData("79", "14472334024676221");
        testValidData("80", "23416728348467685");
        testValidData("81", "37889062373143906");
        testValidData("82", "61305790721611591");
        testValidData("83", "99194853094755497");
        testValidData("84", "160500643816367088");
        testValidData("85", "259695496911122585");
        testValidData("86", "420196140727489673");
        testValidData("87", "679891637638612258");
        testValidData("88", "1100087778366101931");
        testValidData("89", "1779979416004714189");
        testValidData("90", "2880067194370816120");
        testValidData("91", "4660046610375530309");
        testValidData("92", "7540113804746346429");
        testValidData("93", "12200160415121876738");
        testValidData("94", "19740274219868223167");
        testValidData("95", "31940434634990099905");
        testValidData("96", "51680708854858323072");
        testValidData("97", "83621143489848422977");
        testValidData("98", "135301852344706746049");
        testValidData("99", "218922995834555169026");
        testValidData("100", "354224848179261915075");
        //endregion
    }

    @Test
    void boundaryValues() throws IOException {
        //region Bottom line
        testValidData("0", "0");
        testValidData("1", "1");
        testValidData("2", "1");
        //endregion
        //region Upper bound
        testValidData("996", "6341685300418834712936873743652479702279493077782703784593930186219165735154458712792650716708440646016361617676315200611489358319848695671037772054859840711063922357900430130265783715452104982240098275933872");
        testValidData("997", "10261062362033262336604926729245222132668558120602124277764622905699407982546711488272859468887457959087733119242564077850743657661180827326798539177758919828135114407499369796465649524266755391104990099120377");
        testValidData("998", "16602747662452097049541800472897701834948051198384828062358553091918573717701170201065510185595898605104094736918879278462233015981029522997836311232618760539199036765399799926731433239718860373345088375054249");
        //endregion

    }

    @Test
    void classesEquivalence() throws IOException {
        //region Even result
        testValidData("6", "8");
        testValidData("21", "10946");
        testValidData("27", "196418");
        testValidData("33", "3524578");
        testValidData("36", "14930352");
        testValidData("39", "63245986");
        testValidData("42", "267914296");
        testValidData("45", "1134903170");
        testValidData("48", "4807526976");
        testValidData("51", "20365011074");
        testValidData("54", "86267571272");
        testValidData("57", "365435296162");
        testValidData("60", "1548008755920");
        testValidData("63", "6557470319842");
        testValidData("66", "27777890035288");
        testValidData("69", "117669030460994");
        testValidData("72", "498454011879264");
        testValidData("75", "2111485077978050");
        testValidData("78", "8944394323791464");
        testValidData("81", "37889062373143906");
        testValidData("84", "160500643816367088");
        testValidData("87", "679891637638612258");
        testValidData("90", "2880067194370816120");
        testValidData("93", "12200160415121876738");
        testValidData("96", "51680708854858323072");
        testValidData("99", "218922995834555169026");
        testValidData("120", "5358359254990966640871840");
        testValidData("600", "110433070572952242346432246767718285942590237357555606380008891875277701705731473925618404421867819924194229142447517901959200");
        //endregion
        //region Odd result
        testValidData("10", "55");
        testValidData("13", "233");
        testValidData("16", "987");
        testValidData("19", "4181");
        testValidData("22", "17711");
        testValidData("25", "75025");
        testValidData("28", "317811");
        testValidData("31", "1346269");
        testValidData("34", "5702887");
        testValidData("37", "24157817");
        testValidData("40", "102334155");
        testValidData("43", "433494437");
        testValidData("47", "2971215073");
        testValidData("50", "12586269025");
        testValidData("53", "53316291173");
        testValidData("56", "225851433717");
        testValidData("59", "956722026041");
        testValidData("62", "4052739537881");
        testValidData("65", "17167680177565");
        testValidData("68", "72723460248141");
        testValidData("71", "308061521170129");
        testValidData("74", "1304969544928657");
        testValidData("77", "5527939700884757");
        testValidData("80", "23416728348467685");
        testValidData("83", "99194853094755497");
        testValidData("86", "420196140727489673");
        testValidData("254", "54122222371037658776676579571233761483351206693809497");
        //endregion

    }

    @Test
    void otherArgs() throws IOException {
        //region Personal
        testValidData("666", "6859356963880484413875401302176431788073214234535725264860437720157972142108894511264898366145528622543082646626140527097739556699078708088");
        testValidData("21", "10946");
        testValidData("58", "591286729879");
        testValidData("121", "8670007398507948658051921");
        testValidData("13", "233");
        testValidData("777", "1081213530912648191985419587942084110095342850438593857649766278346130479286685742885693301250359913460718567974798268702550329302771992851392180275594318434818082");
        //endregion
        //region Prime numbers
        testValidData("11", "89");
        testValidData("17", "1597");
        testValidData("19", "4181");
        testValidData("23", "28657");
        testValidData("29", "514229");
        testValidData("31", "1346269");
        testValidData("37", "24157817");
        testValidData("41", "165580141");
        testValidData("43", "433494437");
        testValidData("47", "2971215073");
        //endregion
        //region Fibonacci
        testValidData("3", "2");
        testValidData("5", "5");
        testValidData("8", "21");
        testValidData("34", "5702887");
        testValidData("55", "139583862445");
        testValidData("89", "1779979416004714189");
        //endregion
    }

    @Test
    void realizationArgs() throws IOException {
        testInvalidData('%');
        testInvalidData('i');
    }
}