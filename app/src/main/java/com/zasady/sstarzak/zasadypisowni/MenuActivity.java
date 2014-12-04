package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MenuActivity extends Activity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = getApplicationContext();

        //context.deleteDatabase("zasady.db");

        initOrthographyDb();
        initHangmanDb();
        initPolishLettersDb();

        Button language = (Button) findViewById(R.id.language_button);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LanguageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    public void initOrthographyDb() {
    //    this.deleteDatabase("zasady.db");
        Alphabet.deleteAll(Alphabet.class);
        Rule.deleteAll(Rule.class);
        RuleException.deleteAll(Rule.class);
        Word.deleteAll(Word.class);


        Alphabet a1 = new Alphabet("rz"); a1.save();
        Alphabet a2 = new Alphabet("ż");  a2.save(); //a.setId(2l);
        Alphabet a3 = new Alphabet("u");  a3.save(); //a.setId(3l);
        Alphabet a4 = new Alphabet("ó");  a4.save(); //a.setId(4l);
        Alphabet a5 = new Alphabet("ch"); a5.save(); //a.setId(5l);
        Alphabet a6 = new Alphabet("h");  a6.save(); //a.setId(6l);

       //rz
       Rule r1 = new Rule("w wyrazach wymienia sie na R", a1); r1.save();
       Rule r2 = new Rule("Rz piszemy w zakończeniach wyrazów: -arz, -erz", a1); r2.save();
       Rule r3 = new Rule("Rz piszemy po spółgłoskach: b, p, d, t, g, k, ch, j, w", a1); r3.save();
       RuleException re1 = new RuleException("wyrazy: bukszpan, gżegżółka, kształt, kszyk (nazwa ptaka), piegża (nazwa ptaka), pszczoła, Pszczyna, pszenica, pszenżyto",a1); re1.save();
       RuleException re2 = new RuleException("w przymiotnikach zakończonych na: - szy, - ejszy, np.: lepszy, nowszy, najlepszy, najnowszy, ładniejszy, mocniejszy, najładniejszy, najmocniejszy",a1); re2.save();
       //ż
       Rule r4 = new Rule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: g, dz, h, z, ź, s",a2); r4.save();
       Rule r5 = new Rule("Ż piszemy w wyrazach zakończonych na: -aż, -eż",a2); r5.save();
       Rule r6 = new Rule("Ż piszemy po literach: l, ł, r, n", a2); r6.save();
       //u
       Rule r7 = new Rule("w zakończeniach rzeczowników: un, unek, uchna, uszka, uszek, uch, us, usia", a3); r7.save();
       Rule r8 = new Rule("U piszemy w czasownikach zakończonych na: uj ujesz uje",a3); r8.save();
       Rule r9 = new Rule("U piszemy w czasownikach typu: czuć, kuć, kłuć, pruć, snuć, np.: czuje, kuję, kłuję, pruję, snuję",a3); r9.save();
       //ó
       Rule r10 = new Rule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: o, e, a",a4); r10.save();
       Rule r11 = new Rule("Ó piszemy w wyrazach zakończonych na: -ów",a4); r11.save();
       Rule r12 = new Rule("Ó piszemy w wyrazach zakończonych na: -ówka",a4); r12.save();
       RuleException re3 = new RuleException("skuwka, wsuwka, zasuwka",a4); re3.save();
       RuleException re4 = new RuleException("Ó piszemy w wyrazach zakończonych na: -ówna",a4); re4.save();
       RuleException re5 = new RuleException("Ó piszemy na początku wyrazów: ósemka, ósmy, ów, ówczesny, ówcześnie, ówdzie.",a4); re5.save();
       //ch
       Rule r13 = new Rule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: sz",a5); r13.save();
       Rule r14 = new Rule("Ch piszemy po literze s np.: schab, schody, wschód", a5); r14.save();
       Rule r15 = new Rule("Ch piszemy na końcu wyrazów, np.: na drogach, orzech, zuch",a5); r15.save();
       RuleException re6 = new RuleException("druh, Boh (nazwa rzeki)",a5); re6.save();
       //h
       Rule r16 = new Rule("wymienia się w innych formach tego samego wyrazu lub w innych wyrazach na: g, ż, z, dz",a6); r16.save();


       Word w1 = new Word("tokarz", "toka",a1); w1.save();
       w1 = new Word("ucho", "cho",a3); w1.save();
       w1 = new Word("herb","erb",a6); w1.save();
       w1 = new Word("chryzantemy","ryzantemy",a5); w1.save();
       w1 = new Word("potężny","potęny",a2); w1.save();
       w1 = new Word("łódka","łdka",a4 ); w1.save();
        w1 = new Word("abdukcja","abdkcja",a3); w1.save();
        w1 = new Word("abordaże","abordae",a2); w1.save();
        w1 = new Word("przeprzężcie","peprzężcie",a1); w1.save();
        w1 = new Word("przylepiec","pylepiec",a1); w1.save();
        w1 = new Word("rozrzewniane","rozewniane",a1); w1.save();
        w1 = new Word("hominida","ominida",a6); w1.save();
        w1 = new Word("homofon","omofon",a6); w1.save();
        w1 = new Word("hurtowna","urtowna",a6); w1.save();
        w1 = new Word("pohamowała","poamowała",a6); w1.save();
        w1 = new Word("szahady","szaady",a6); w1.save();
        w1 = new Word("telehity","teleity",a6); w1.save();
        w1 = new Word("rozniósł","roznisł",a4); w1.save();
        w1 = new Word("wróbla","wrbla",a4); w1.save();
        w1 = new Word("żydówka","żydwka",a4); w1.save();
        w1 = new Word("żużlówce","żużlwce",a4); w1.save();
    }
    public void initHangmanDb() {
        Category.deleteAll(Category.class);
        GameWords.deleteAll(GameWords.class);

        Category c1 = new Category("Zwierzęta", "Animals"); c1.save();
        Category c2 = new Category("Owoce","Fruits"); c2.save();

        //Animals
        GameWords gw1 = new GameWords("pies",c1); gw1.save();
        gw1 = new GameWords("koń",c1); gw1.save();

        //Fruits
        gw1 = new GameWords("banan",c2); gw1.save();
        gw1 = new GameWords("jagoda",c2); gw1.save();

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
