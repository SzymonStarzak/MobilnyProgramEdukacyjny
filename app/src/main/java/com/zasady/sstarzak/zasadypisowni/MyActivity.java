package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Iterator;
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

        Alphabet a = new Alphabet("u");
        List<Rule> list = Rule.listAll(Rule.class);//, "alphabet = ?", new String[]{String.valueOf(a)});


        for( Rule b:list) {
            //if(b.alphabet.pl_str == "u")
                tw.append(b.alphabet.pl_str);
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
        Alphabet a;
        a = new Alphabet("rz"); a.save();
        a = new Alphabet("ż");  a.save(); //a.setId(2l);
        a = new Alphabet("u");  a.save(); //a.setId(3l);
        a = new Alphabet("ó");  a.save(); //a.setId(4l);
        a = new Alphabet("ch"); a.save(); //a.setId(5l);
        a = new Alphabet("h");  a.save(); //a.setId(6l);

        Rule r;
        r = new Rule("w wyrazach wymienia sie na R", new Alphabet("rz")); r.save();
        r = new Rule("Rz piszemy w zakończeniach wyrazów: -arz, -erz", new Alphabet("rz")); r.save();
        r = new Rule("Rz piszemy po spółgłoskach: b, p, d, t, g, k, ch, j, w", new Alphabet("rz")); r.save();
        r = new Rule("w zakończeniach rzeczowników: un, unek, uchna, uszka, uszek, uch, us, usia", new Alphabet("u")); r.save();
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
