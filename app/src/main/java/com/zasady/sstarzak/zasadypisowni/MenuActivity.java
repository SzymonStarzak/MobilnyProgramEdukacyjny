package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends Activity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = getApplicationContext();

        context.deleteDatabase("zasady.db");

        initOrthographyDb();
        initHangmanDb();
        initPolishLettersDb();
        initEnglishLettersDb();

        Button language = (Button) findViewById(R.id.language_button);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LanguageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initOrthographyDb() {
    //    this.deleteDatabase("zasady.db");
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

        //Animals PL
        HangmanWords gw1 = new HangmanWords("pies",c1); gw1.save();
        gw1 = new HangmanWords("koń",c1); gw1.save();

        //Animials ENG
        HangmanWordsEng hwe1 = new HangmanWordsEng("dog",c1); hwe1.save();
        hwe1 = new HangmanWordsEng("horse",c1); hwe1.save();

        //Fruits PL
        gw1 = new HangmanWords("banan",c2); gw1.save();
        gw1 = new HangmanWords("jagoda",c2); gw1.save();

        //Fruits ENG
        hwe1 = new HangmanWordsEng("apple",c2); hwe1.save();

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

}
