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

        this.deleteDatabase("zasady.db");
        seedDataIntoDataBase();

        TextView tw = (TextView) findViewById(R.id.textView);

        //Alphabet a = new Alphabet("u");
        List<Rule> list = Rule.listAll(Rule.class);//, "alphabet = ?", new String[]{String.valueOf(a)});


        for( Rule b:list) {
            if(b.alphabet.pl_str.equals("u"))
                tw.append(" " + b.principle );
        }

       // Alphabet a2 = Alphabet.findById(Alphabet.class,3l);
       // long a2 = Alphabet.count(Alphabet.class,null,null);

        //tw.setText(String.valueOf(a2));


//        while (a.hasNext()) {
//            Alphabet a2 = a.next();
//            tw.append(a2.pl_str + ", ");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    public void seedDataIntoDataBase() {
        Alphabet.deleteAll(Alphabet.class);
        Alphabet a1 = new Alphabet("rz"); a1.save();
        Alphabet a2 = new Alphabet("ż");  a2.save(); //a.setId(2l);
        Alphabet a3 = new Alphabet("u");  a3.save(); //a.setId(3l);
        Alphabet a4 = new Alphabet("ó");  a4.save(); //a.setId(4l);
        Alphabet a5 = new Alphabet("ch"); a5.save(); //a.setId(5l);
        Alphabet a6 = new Alphabet("h");  a6.save(); //a.setId(6l);


       Rule r1 = new Rule("w wyrazach wymienia sie na R", a1); r1.save();
       Rule r2 = new Rule("Rz piszemy w zakończeniach wyrazów: -arz, -erz", a1); r2.save();
       Rule r3 = new Rule("Rz piszemy po spółgłoskach: b, p, d, t, g, k, ch, j, w", a1); r3.save();
       Rule r4 = new Rule("w zakończeniach rzeczowników: un, unek, uchna, uszka, uszek, uch, us, usia", a3); r4.save();
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
