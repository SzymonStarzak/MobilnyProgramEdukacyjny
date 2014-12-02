package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OrthographyActivity extends Activity {

    private List<Word> words;

    private Word word;

    private List<Word> filteredwords;

    private Alphabet firstletter;

    private Alphabet letter;

    private Alphabet letterpair;

    private Select selectalphabet;

    private long alphabetcount;

    private long wordscount;

    private long filteredwordscount;

    private long randomvalueforid;

   // private long random

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orthography);

        alphabetcount = Alphabet.count(Alphabet.class,null,null);
        selectalphabet = Select.from(Alphabet.class);
        firstletter = (Alphabet) selectalphabet.first();
        letter = Alphabet.findById(Alphabet.class,firstletter.getId() + randomvalueforid);
        words = Word.listAll(Word.class);
        wordscount = Word.count(Word.class, null, null);
        filteredwords = new ArrayList<Word>();

        orthographyTest();
    }

    public void orthographyTest() {

        //losowanie liter o nieparzystej liczbie pozycyjnej, id + 1 będzie alternatywną odpowiedzią
        do {
            randomvalueforid = (new Random()).nextInt((int) (alphabetcount));
        }while(randomvalueforid %2 == 1);

        letter = Alphabet.findById(Alphabet.class,firstletter.getId() + randomvalueforid);
        letterpair = Alphabet.findById(Alphabet.class,firstletter.getId() + randomvalueforid + 1);

          for(Word w: words) {
            if (w.alphabet.pl_str.equals(letter.pl_str))
                filteredwords.add(w);
        }
        filteredwordscount = (long) filteredwords.size();
        word = filteredwords.get((new Random()).nextInt((int) filteredwordscount));
        /*
        for( Word w: filteredwords) {
        //    tw.append(letter.pl_str + " " + w.full_word);
        }*/

        Button button1 = (Button) findViewById(R.id.orthography_answer1_button);
        Button button2 = (Button) findViewById(R.id.orthography_answer2_button);
        TextView tw1 = (TextView) findViewById(R.id.partofword);

        button1.setText(letter.pl_str);
        button2.setText(letterpair.pl_str);
        tw1.setText(word.part_word);

        orthographyTest();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.orthography, menu);
        return true;
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
