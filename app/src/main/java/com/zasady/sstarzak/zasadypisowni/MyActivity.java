package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class MyActivity extends Activity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        context = getApplicationContext();


        seedDataIntoDataBase();

        TextView tw = (TextView) findViewById(R.id.textView);

        //Alphabet a = new Alphabet("u");
        List<Rule> list = Rule.listAll(Rule.class);//, "alphabet = ?", new String[]{String.valueOf(a)});
        List<Word> words = Word.listAll(Word.class);
        //List<Rule> list = sel.list();

        for( Rule b:list) for(Word w:words) {
            if(b.alphabet.pl_str.equals("u") && w.alphabet.pl_str.equals("u"))
                tw.append("\n" + b.principle + " <> " + w.full_word);
        }
     /*   tw.setText("");
        for(Word w:words) {
            if(w.alphabet.pl_str.equals("rz")){
                tw.append(" " + w.alphabet );
            }
        }*/

       // Alphabet a2 = Alphabet.findById(Alphabet.class,3l);
       // long a2 = Alphabet.count(Alphabet.class,null,null);

        //tw.setText(String.valueOf(a2));


//        while (a.hasNext()) {
//            Alphabet a2 = a.next();
//       p     tw.append(a2.pl_str + ", ");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    public void seedDataIntoDataBase() {
        this.deleteDatabase("zasady.db");
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


       Word w1 = new Word("Tokarz", "Toka",a1); w1.save();
            w1 = new Word("Ucho", "cho",a3); w1.save();
         //   w1 = new Word("")
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
