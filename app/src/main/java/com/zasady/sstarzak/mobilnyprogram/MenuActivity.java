package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends Activity {

    private Context context;

    String DB_FULL_PATH = "zasady.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = getApplicationContext();

      // context.deleteDatabase(DB_FULL_PATH);

        if(!checkDataBase()) {
            context.deleteDatabase(DB_FULL_PATH);
            initOrthographyDb();
            initStatsDb();
            initHangmanDb();
            initPolishLettersDb();
            initEnglishLettersDb();
            initEnglishWordsDb();
            initElementsDb();
        }
        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "PTF76F.ttf");

        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(25);
        gd.setStroke(3, Color.RED);

        Button language = (Button) findViewById(R.id.language_button);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LanguageActivity.class);
                startActivity(intent);
            }
        });
        language.setBackground(gd);  //API 17 !
        language.setTypeface(tf);

        Button number = (Button) findViewById(R.id.number_button);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NumberActivity.class);
                startActivity(intent);
            }
        });
        number.setBackground(gd);
        number.setTypeface(tf);

        Button stats = (Button) findViewById(R.id.about_app_button);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StatsActivity.class);
                startActivity(intent);
            }
        });
        stats.setBackground(gd);
        stats.setTypeface(tf);

        Button finish = (Button) findViewById(R.id.exit_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        finish.setBackground(gd);
        finish.setTypeface(tf);

    }

    public void initStatsDb() {
        Stats.deleteAll(Stats.class);
        Stats s = new Stats("Ortografia",0,0); s.save();
        s = new Stats("Wisielec",0,0); s.save();
        s = new Stats("WisielecENG",0,0); s.save();
        s = new Stats("Angielski",0,0); s.save();
        s = new Stats("SzybkieOperacje",0,0); s.save();
        s = new Stats("TablicaMendelejewa",0,0); s.save();
        s = new Stats("TablicaMendelejewaENG",0,0); s.save();
        s = new Stats("SystemyLiczowe",0,0); s.save();
    }
    public void initEnglishWordsDb() {
        EnglishWords.deleteAll(EnglishWords.class);

        EnglishWords ew1 = new EnglishWords("chair","krzesło","komoda","podłoga","włosy",
                "And I did want to demonstrate the fact that the chair can accommodate people."); ew1.save();
        ew1 = new EnglishWords("oil","olej","woda","ropa","krem",
                "And then they'd tow the sharks back to Purteen Harbor, boil them up, use the oil."); ew1.save();
        ew1 = new EnglishWords("towel","ręcznik","serweta","zasłona","wieża",
                "So we see Pranav here going into the supermarket and he's shopping for some paper towels."); ew1.save();
        ew1 = new EnglishWords("arm","ramię","głowa","armia","nos",
                "And from the wicked their light is withholden, And the high arm is broken."); ew1.save();
        ew1 = new EnglishWords("bath","kąpiel","bat","umywalka","skrobanie",
                "They tell us to have a bath."); ew1.save();


    }
    public void initOrthographyDb() {
        OrthographyAlphabet.deleteAll(OrthographyAlphabet.class);
        OrthographyRule.deleteAll(OrthographyRule.class);
        OrthographyRuleException.deleteAll(OrthographyRule.class);
        OrthographyWord.deleteAll(OrthographyWord.class);

        OrthographyAlphabet a1 = new OrthographyAlphabet("rz"); a1.save();
        OrthographyAlphabet a2 = new OrthographyAlphabet("ż");  a2.save(); //a.setId(2l);
        OrthographyAlphabet a3 = new OrthographyAlphabet("u");  a3.save(); //a.setId(3l);
        OrthographyAlphabet a4 = new OrthographyAlphabet("ó");  a4.save(); //a.setId(4l);
        OrthographyAlphabet a5 = new OrthographyAlphabet("ch"); a5.save(); //a.setId(5l);
        OrthographyAlphabet a6 = new OrthographyAlphabet("h");  a6.save(); //a.setId(6l);

       //rz
       OrthographyRule r1 = new OrthographyRule("w wyrazach wymienia sie na R", a1); r1.save();
       OrthographyRule r2 = new OrthographyRule("Rz piszemy w zakończeniach wyrazów: -arz, -erz", a1); r2.save();
       OrthographyRule r3 = new OrthographyRule("Rz piszemy po spółgłoskach: b, p, d, t, g, k, ch, j, w", a1); r3.save();
       OrthographyRuleException re1 = new OrthographyRuleException("wyrazy: bukszpan, gżegżółka, kształt, kszyk (nazwa ptaka), piegża (nazwa ptaka), pszczoła, Pszczyna, pszenica, pszenżyto",a1); re1.save();
       OrthographyRuleException re2 = new OrthographyRuleException("w przymiotnikach zakończonych na: - szy, - ejszy, np.: lepszy, nowszy, najlepszy, najnowszy, ładniejszy, mocniejszy, najładniejszy, najmocniejszy",a1); re2.save();
       //ż
       OrthographyRule r4 = new OrthographyRule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: g, dz, h, z, ź, s",a2); r4.save();
       OrthographyRule r5 = new OrthographyRule("Ż piszemy w wyrazach zakończonych na: -aż, -eż",a2); r5.save();
       OrthographyRule r6 = new OrthographyRule("Ż piszemy po literach: l, ł, r, n", a2); r6.save();
       //u
       OrthographyRule r7 = new OrthographyRule("w zakończeniach rzeczowników: un, unek, uchna, uszka, uszek, uch, us, usia", a3); r7.save();
       OrthographyRule r8 = new OrthographyRule("U piszemy w czasownikach zakończonych na: uj ujesz uje",a3); r8.save();
       OrthographyRule r9 = new OrthographyRule("U piszemy w czasownikach typu: czuć, kuć, kłuć, pruć, snuć, np.: czuje, kuję, kłuję, pruję, snuję",a3); r9.save();
       //ó
       OrthographyRule r10 = new OrthographyRule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: o, e, a",a4); r10.save();
       OrthographyRule r11 = new OrthographyRule("Ó piszemy w wyrazach zakończonych na: -ów",a4); r11.save();
       OrthographyRule r12 = new OrthographyRule("Ó piszemy w wyrazach zakończonych na: -ówka",a4); r12.save();
       OrthographyRuleException re3 = new OrthographyRuleException("skuwka, wsuwka, zasuwka",a4); re3.save();
       OrthographyRuleException re4 = new OrthographyRuleException("Ó piszemy w wyrazach zakończonych na: -ówna",a4); re4.save();
       OrthographyRuleException re5 = new OrthographyRuleException("Ó piszemy na początku wyrazów: ósemka, ósmy, ów, ówczesny, ówcześnie, ówdzie.",a4); re5.save();
       //ch
       OrthographyRule r13 = new OrthographyRule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: sz",a5); r13.save();
       OrthographyRule r14 = new OrthographyRule("Ch piszemy po literze s np.: schab, schody, wschód", a5); r14.save();
       OrthographyRule r15 = new OrthographyRule("Ch piszemy na końcu wyrazów, np.: na drogach, orzech, zuch",a5); r15.save();
       OrthographyRuleException re6 = new OrthographyRuleException("druh, Boh (nazwa rzeki)",a5); re6.save();
       //h
       OrthographyRule r16 = new OrthographyRule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: g, ż, z, dz",a6); r16.save();


       OrthographyWord w1 = new OrthographyWord("tokarz", "toka",a1); w1.save();
       w1 = new OrthographyWord("ucho", "cho",a3); w1.save();
       w1 = new OrthographyWord("herb","erb",a6); w1.save();
       w1 = new OrthographyWord("chryzantemy","ryzantemy",a5); w1.save();
       w1 = new OrthographyWord("potężny","potęny",a2); w1.save();
       w1 = new OrthographyWord("łódka","łdka",a4 ); w1.save();
        w1 = new OrthographyWord("abdukcja","abdkcja",a3); w1.save();
        w1 = new OrthographyWord("abordaże","abordae",a2); w1.save();
        w1 = new OrthographyWord("przeprzężcie","peprzężcie",a1); w1.save();
        w1 = new OrthographyWord("przylepiec","pylepiec",a1); w1.save();
        w1 = new OrthographyWord("rozrzewniane","rozewniane",a1); w1.save();
        w1 = new OrthographyWord("hominida","ominida",a6); w1.save();
        w1 = new OrthographyWord("homofon","omofon",a6); w1.save();
        w1 = new OrthographyWord("hurtowna","urtowna",a6); w1.save();
        w1 = new OrthographyWord("pohamowała","poamowała",a6); w1.save();
        w1 = new OrthographyWord("szahady","szaady",a6); w1.save();
        w1 = new OrthographyWord("telehity","teleity",a6); w1.save();
        w1 = new OrthographyWord("rozniósł","roznisł",a4); w1.save();
        w1 = new OrthographyWord("wróbla","wrbla",a4); w1.save();
        w1 = new OrthographyWord("żydówka","żydwka",a4); w1.save();
        w1 = new OrthographyWord("żużlówce","żużlwce",a4); w1.save();
    }
    public void initHangmanDb() {
        HangmanCategory.deleteAll(HangmanCategory.class);
        HangmanWords.deleteAll(HangmanWords.class);
        HangmanWordsEng.deleteAll(HangmanWordsEng.class);

        HangmanCategory c1 = new HangmanCategory("Zwierzęta", "Animals"); c1.save();
        HangmanCategory c2 = new HangmanCategory("Owoce","Fruits"); c2.save();
        HangmanCategory c3 = new HangmanCategory("Części ciała","Body parts"); c3.save();
        HangmanCategory c4 = new HangmanCategory("Planety","Planets"); c4.save();

        //Animals PL
        HangmanWords gw1 = new HangmanWords("pies",c1); gw1.save();
        gw1 = new HangmanWords("koń",c1); gw1.save();
        gw1 = new HangmanWords("krowa",c1); gw1.save();
        gw1 = new HangmanWords("owca",c1); gw1.save();
        gw1 = new HangmanWords("wilk",c1); gw1.save();
        gw1 = new HangmanWords("krokodyl",c1); gw1.save();

        //Animials ENG
        HangmanWordsEng hwe1 = new HangmanWordsEng("dog",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("horse",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("cow",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("pig",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("wolf",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("tiger",c1); hwe1.save();

        //Fruits PL
        gw1 = new HangmanWords("banan",c2); gw1.save();
        gw1 = new HangmanWords("jagoda",c2); gw1.save();
        gw1 = new HangmanWords("gruszka",c2); gw1.save();
        gw1 = new HangmanWords("czereśnia",c2); gw1.save();
        gw1 = new HangmanWords("arbuz",c2); gw1.save();

        //Fruits ENG
        hwe1 = new HangmanWordsEng("apple",c2); hwe1.save();
        hwe1 = new HangmanWordsEng("orange",c2); hwe1.save();
        hwe1 = new HangmanWordsEng("watermelon",c2); hwe1.save();
        hwe1 = new HangmanWordsEng("pineapple",c2); hwe1.save();
        hwe1 = new HangmanWordsEng("grapes",c2); hwe1.save();

        //Body parts PL
        HangmanWords bp1 = new HangmanWords("ręka",c3); bp1.save();
        bp1 = new HangmanWords("ucho",c3); bp1.save();
        bp1 = new HangmanWords("noga",c3); bp1.save();
        bp1 = new HangmanWords("nos",c3); bp1.save();
        bp1 = new HangmanWords("czoło",c3); bp1.save();

        //Body parts PL
        HangmanWordsEng bpe1 = new HangmanWordsEng("arm",c3); bpe1.save();
        bpe1 = new HangmanWordsEng("finger",c3); bpe1.save();
        bpe1 = new HangmanWordsEng("head",c3); bpe1.save();
        bpe1 = new HangmanWordsEng("ear",c3); bpe1.save();
        bpe1 = new HangmanWordsEng("knee",c3); bpe1.save();

        //Planets PL
        HangmanWords planets = new HangmanWords("merkury",c4); planets.save();
        planets = new HangmanWords("wenus",c4); planets.save();
        planets = new HangmanWords("ziemia",c4); planets.save();
        planets = new HangmanWords("mars",c4); planets.save();
        planets = new HangmanWords("jowisz",c4); planets.save();
        planets = new HangmanWords("saturn",c4); planets.save();
        planets = new HangmanWords("uran",c4); planets.save();
        planets = new HangmanWords("neptun",c4); planets.save();

        HangmanWordsEng planetse = new HangmanWordsEng("mercury",c4); planetse.save();
        planetse = new HangmanWordsEng("venus",c4); planetse.save();
        planetse = new HangmanWordsEng("earth",c4); planetse.save();
        planetse = new HangmanWordsEng("mars",c4); planetse.save();
        planetse = new HangmanWordsEng("jupiter",c4); planetse.save();
        planetse = new HangmanWordsEng("saturn",c4); planetse.save();
        planetse = new HangmanWordsEng("uranus",c4); planetse.save();
        planetse = new HangmanWordsEng("neptune",c4); planetse.save();

    }
    public void initPolishLettersDb() {
        PolishAlphabet.deleteAll(PolishAlphabet.class);
        PolishAlphabet pa = new PolishAlphabet("A"); pa.save();
        pa = new PolishAlphabet("Ą"); pa.save();
        pa = new PolishAlphabet("B"); pa.save();
        pa = new PolishAlphabet("C"); pa.save();
        pa = new PolishAlphabet("Ć"); pa.save();
        pa = new PolishAlphabet("D"); pa.save();
        pa = new PolishAlphabet("E"); pa.save();
        pa = new PolishAlphabet("Ę"); pa.save();
        pa = new PolishAlphabet("F"); pa.save();
        pa = new PolishAlphabet("G"); pa.save();
        pa = new PolishAlphabet("H"); pa.save();
        pa = new PolishAlphabet("I"); pa.save();
        pa = new PolishAlphabet("J"); pa.save();
        pa = new PolishAlphabet("K"); pa.save();
        pa = new PolishAlphabet("L"); pa.save();
        pa = new PolishAlphabet("Ł"); pa.save();
        pa = new PolishAlphabet("M"); pa.save();
        pa = new PolishAlphabet("N"); pa.save();
        pa = new PolishAlphabet("Ń"); pa.save();
        pa = new PolishAlphabet("O"); pa.save();
        pa = new PolishAlphabet("Ó"); pa.save();
        pa = new PolishAlphabet("P"); pa.save();
        pa = new PolishAlphabet("R"); pa.save();
        pa = new PolishAlphabet("S"); pa.save();
        pa = new PolishAlphabet("Ś"); pa.save();
        pa = new PolishAlphabet("T"); pa.save();
        pa = new PolishAlphabet("U"); pa.save();
        pa = new PolishAlphabet("W"); pa.save();
        pa = new PolishAlphabet("Y"); pa.save();
        pa = new PolishAlphabet("Z"); pa.save();
        pa = new PolishAlphabet("Ź"); pa.save();
        pa = new PolishAlphabet("Ż"); pa.save();
    }
    public void initEnglishLettersDb() {
        EnglishAlphabet.deleteAll(EnglishAlphabet.class);
        EnglishAlphabet ea = new EnglishAlphabet("A"); ea.save();
        ea = new EnglishAlphabet("B"); ea.save();
        ea = new EnglishAlphabet("C"); ea.save();
        ea = new EnglishAlphabet("D"); ea.save();
        ea = new EnglishAlphabet("E"); ea.save();
        ea = new EnglishAlphabet("F"); ea.save();
        ea = new EnglishAlphabet("G"); ea.save();
        ea = new EnglishAlphabet("H"); ea.save();
        ea = new EnglishAlphabet("I"); ea.save();
        ea = new EnglishAlphabet("J"); ea.save();
        ea = new EnglishAlphabet("K"); ea.save();
        ea = new EnglishAlphabet("L"); ea.save();
        ea = new EnglishAlphabet("M"); ea.save();
        ea = new EnglishAlphabet("N"); ea.save();
        ea = new EnglishAlphabet("O"); ea.save();
        ea = new EnglishAlphabet("P"); ea.save();
        ea = new EnglishAlphabet("Q"); ea.save();
        ea = new EnglishAlphabet("R"); ea.save();
        ea = new EnglishAlphabet("S"); ea.save();
        ea = new EnglishAlphabet("T"); ea.save();
        ea = new EnglishAlphabet("U"); ea.save();
        ea = new EnglishAlphabet("V"); ea.save();
        ea = new EnglishAlphabet("W"); ea.save();
        ea = new EnglishAlphabet("X"); ea.save();
        ea = new EnglishAlphabet("Y"); ea.save();
        ea = new EnglishAlphabet("Z"); ea.save();
    }
    public void initElementsDb() {
        Element e = new Element("H","wodór","Hydrogen",1,1,1,"gaz","niemetal"); e.save();
        e = new Element("He","hel","Helium",2,18,1,"gaz","gaz_szlachetny"); e.save();
        e = new Element("Li","lit","Lithium",3,1,2,"stały","metal"); e.save();
        e = new Element("Be","beryl","Beryllium",4,2,2,"stały","metal"); e.save();
        e = new Element("B","bor","Boron",5,13,2,"stały","niemetal/półmetal"); e.save();
        e = new Element("C","węgiel","Carbon",6,14,2,"stały","niemetal/półmetal"); e.save();
        e = new Element("N","azot","Nitrogen",7,15,2,"gaz","niemetal"); e.save();
        e = new Element("O","tlen","Oxygen",8,16,2,"gaz","niemetal"); e.save();
        e = new Element("F","fluor","Fluorine",9,17,2,"gaz","niemetal"); e.save();
        e = new Element("Ne","neon","Neon",10,18,2,"gaz","gaz_szlachetny"); e.save();
        e = new Element("Na","sód","Sodium",11,1,3,"stały","metal"); e.save();
        e = new Element("Mg","magnez","Magnesium",12,2,3,"stały","metal"); e.save();
        e = new Element("Al","glin","Aluminium",13,13,3,"stały","metal/półmetal"); e.save();
        e = new Element("Si","krzem","Silicon",14,14,3,"stały","niemetal/półmetal"); e.save();
        e = new Element("P","fosfor","Phosphorus",15,15,3,"stały","niemetal/półmetal"); e.save();
        e = new Element("S","siarka","Sulfur",16,16,3,"stały","niemetal"); e.save();
        e = new Element("Cl","chlor","Chlorine",17,17,3,"gaz","niemetal"); e.save();
        e = new Element("Ar","argon","Argon",18,18,3,"gaz","gaz_szlachetny"); e.save();
        e = new Element("K","potas","Potassium",19,1,4,"stały","metal"); e.save();
        e = new Element("Ca","wapń","Calcium",20,2,4,"stały","metal"); e.save();
        e = new Element("Sc","skand","Scandium",21,3,4,"stały","metal"); e.save();
        e = new Element("Ti","tytan","Titanium",22,4,4,"stały","metal"); e.save();
        e = new Element("V","wanad","Vanadium",23,5,4,"stały","metal"); e.save();
        e = new Element("Cr","chrom","Chromium",24,6,4,"stały","metal"); e.save();
        e = new Element("Mn","mangan","Manganese",25,7,4,"stały","metal"); e.save();
        e = new Element("Fe","żelazo","Iron",26,8,4,"stały","metal"); e.save();
        e = new Element("Co","kobalt","Cobalt",27,9,4,"stały","metal"); e.save();
        e = new Element("Ni","nikiel","Nickel",28,10,4,"stały","metal"); e.save();
        e = new Element("Cu","miedź","Copper",29,11,4,"stały","metal"); e.save();
        e = new Element("Zn","cynk","Zinc",30,12,4,"stały","metal"); e.save();
        e = new Element("Ga","gal","Gallium",31,13,4,"stały","metal"); e.save();
        e = new Element("Ge","german","Germanium",32,14,4,"stały","metal/półmetal"); e.save();
        e = new Element("As","arsen","Arsenic",33,15,4,"stały","niemetal/półmetal"); e.save();
        e = new Element("Se","selen","Selenium",34,16,4,"stały","niemetal"); e.save();
        e = new Element("Br","brom","Bromine",35,17,4,"ciecz","niemetal"); e.save();
        e = new Element("Kr","krypton","Krypton",36,18,4,"gaz","gaz_szlachetny"); e.save();
        e = new Element("Rb","rubid","Rubidium",37,1,5,"stały","metal"); e.save();
        e = new Element("Sr","stront","Strontium",38,2,5,"stały","metal"); e.save();
        e = new Element("Y","itr","Yttrium",39,3,5,"stały","metal"); e.save();
        e = new Element("Zr","cyrkon","Zirconium",40,4,5,"stały","metal"); e.save();
        e = new Element("Nb","niob","Niobium",41,5,5,"stały","metal"); e.save();
        e = new Element("Mo","molibden","Molybdenum",42,6,5,"stały","metal"); e.save();
        e = new Element("Tc","technet","Technetium",43,7,5,"stały","metal"); e.save();
        e = new Element("Ru","ruten","Ruthenium",44,8,5,"stały","metal"); e.save();
        e = new Element("Rh","rod","Rhodium",45,9,5,"stały","metal"); e.save();
        e = new Element("Pd","pallad","Palladium",46,10,5,"stały","metal"); e.save();
        e = new Element("Ag","srebro","Silver",47,11,5,"stały","metal"); e.save();
        e = new Element("Cd","kadm","Cadmium",48,12,5,"stały","metal"); e.save();
        e = new Element("In","ind","Indium",49,13,5,"stały","metal"); e.save();
        e = new Element("Sn","cyna","Tin",50,14,5,"stały","metal"); e.save();
        e = new Element("Sb","antymon","Antimony",51,15,5,"stały","metal/półmetal"); e.save();
        e = new Element("Te","tellur","Tellurium",52,16,5,"stały","niemetal/półmetal"); e.save();
        e = new Element("I","jod","Iodine",53,17,5,"stały","niemetal"); e.save();
        e = new Element("Xe","ksenon","Xenon",54,18,5,"gaz","gaz_szlachetny"); e.save();
        e = new Element("Cs","cez","Caesium",55,1,6,"stały","metal"); e.save();
        e = new Element("Ba","bar","Barium",56,2,6,"stały","metal"); e.save();
     //   e = new Element("Lu","lutet","Lutetium",71,3,6,"stały","metal"); e.save();
        e = new Element("Hf","hafn","Hafnium",72,4,6,"stały","metal"); e.save();
        e = new Element("Ta","tantal","Tantalum",73,5,6,"stały","metal"); e.save();
        e = new Element("W","wolfram","Tungsten",74,6,6,"stały","metal"); e.save();
        e = new Element("Re","ren","Rhenium",75,7,6,"stały","metal"); e.save();
        e = new Element("Os","osm","Osmium",76,8,6,"stały","metal"); e.save();
        e = new Element("Ir","iryd","Iridium",77,9,6,"stały","metal"); e.save();
        e = new Element("Pt","platyna","Platinum",78,10,6,"stały","metal"); e.save();
        e = new Element("Au","złoto","Gold",79,11,6,"stały","metal"); e.save();
        e = new Element("Hg","rtęć","Mercury",80,12,6,"ciecz","metal"); e.save();
        e = new Element("Tl","tal","Thallium",81,13,6,"stały","metal"); e.save();
        e = new Element("Pb","ołów","Lead",82,14,6,"stały","metal"); e.save();
        e = new Element("Bi","bizmut","Bismuth",83,15,6,"stały","metal"); e.save();
        e = new Element("Po","polon","Polonium",84,16,6,"stały","metal/półmetal"); e.save();
        e = new Element("At","astat","Astatine",85,17,6,"stały","niemetal/półmetal"); e.save();
        e = new Element("Rn","radon","Radon",86,18,6,"gaz","gaz_szlachetny"); e.save();
        e = new Element("Fr","frans","Francium",87,1,7,"stały","metal"); e.save();
        e = new Element("Ra","rad","Radium",88,2,7,"stały","metal"); e.save();
   //     e = new Element("Lr","lorens","Lawrencium",103,3,7,"stały","metal"); e.save();
        e = new Element("Rf","rutherford","Rutherfordium",104,4,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Db","dubn","Dubnium",105,5,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Sg","seaborg","Seaborgium",106,6,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Bh","bohr","Bohrium",107,7,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Hs","has","Hassium",108,8,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Mt","meitner","Meitnerium",109,9,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Ds","darmsztadt","Darmstadtium",110,10,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Rg","roentgen","Roentgenium",111,11,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Cn","kopernik","Copernicium",112,12,7,"prawdopodobnie_ciecz","metal"); e.save();
        e = new Element("Uut","ununtrium","Ununtrium",113,13,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Fl","flerow","Flerovium",114,14,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Uup","ununpentium","Ununpentium",115,15,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Lv","liwermor","Livermorium",116,16,7,"prawdopodobnie_ciało_stałe","metal"); e.save();
        e = new Element("Uus","ununseptium","Ununseptium",117,17,7,"prawdopodobnie_ciało_stałe","prawdopodobnie_niemetal"); e.save();
        e = new Element("Uuo","ununoctium","Ununoctium",118,18,7,"prawdopodobnie_ciało_stałe","prawdopodobnie_niemetal"); e.save();
        e = new Element("La","lantan","Lanthanum",57,0,6,"stały","metal"); e.save();
        e = new Element("Ce","cer","Cerium",58,0,6,"stały","metal"); e.save();
        e = new Element("Pr","prazeodym","Praseodymium",59,0,6,"stały","metal"); e.save();
        e = new Element("Nd","neodym","Neodymium",60,0,6,"stały","metal"); e.save();
        e = new Element("Pm","promet","Promethium",61,0,6,"stały","metal"); e.save();
        e = new Element("Sm","samar","Samarium",62,0,6,"stały","metal"); e.save();
        e = new Element("Eu","europ","Europium",63,0,6,"stały","metal"); e.save();
        e = new Element("Gd","gadolin","Gadolinium",64,0,6,"stały","metal"); e.save();
        e = new Element("Tb","terb","Terbium",65,0,6,"stały","metal"); e.save();
        e = new Element("Dy","dysproz","Dysprosium",66,0,6,"stały","metal"); e.save();
        e = new Element("Ho","holm","Holmium",67,0,6,"stały","metal"); e.save();
        e = new Element("Er","erb","Erbium",68,0,6,"stały","metal"); e.save();
        e = new Element("Tm","tul","Thulium",69,0,6,"stały","metal"); e.save();
        e = new Element("Yb","iterb","Ytterbium",70,0,6,"stały","metal"); e.save();
        e = new Element("Lu","lutet","Lutetium",71,3,6,"stały","metal"); e.save();
        e = new Element("Ac","aktyn","Actinium",89,0,7,"stały","metal"); e.save();
        e = new Element("Th","tor","Thorium",90,0,7,"stały","metal"); e.save();
        e = new Element("Pa","protaktyn","Protactinium",91,0,7,"stały","metal"); e.save();
        e = new Element("U","uran","Uranium",92,0,7,"stały","metal"); e.save();
        e = new Element("Np","neptun","Neptunium",93,0,7,"stały","metal"); e.save();
        e = new Element("Pu","pluton","Plutonium",94,0,7,"stały","metal"); e.save();
        e = new Element("Am","ameryk","Americium",95,0,7,"stały","metal"); e.save();
        e = new Element("Cm","kiur","Curium",96,0,7,"stały","metal"); e.save();
        e = new Element("Bk","berkel","Berkelium",97,0,7,"stały","metal"); e.save();
        e = new Element("Cf","kaliforn","Californium",98,0,7,"stały","metal"); e.save();
        e = new Element("Es","einstein","Einsteinium",99,0,7,"stały","metal"); e.save();
        e = new Element("Fm","ferm","Fermium",100,0,7,"stały","metal"); e.save();
        e = new Element("Md","mendelew","Mendelevium",101,0,7,"stały","metal"); e.save();
        e = new Element("No","nobel","Nobelium",102,0,7,"stały","metal"); e.save();
        e = new Element("Lr","lorens","Lawrencium",103,3,7,"stały","metal"); e.save();

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(getApplicationContext().getDatabasePath(DB_FULL_PATH).getPath(), null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
        }
        return checkDB != null ? true : false;
    }
}
