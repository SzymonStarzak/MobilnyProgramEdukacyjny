package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    Context context;

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

        context = getApplicationContext();

        orthographyTest();
    }

    public void orthographyTest() {

        //losowanie liter o nieparzystej liczbie pozycyjnej, id + 1 będzie alternatywną odpowiedzią
        do {
            randomvalueforid = (new Random()).nextInt((int) (alphabetcount));
        }while(randomvalueforid %2 == 1);

        letter = Alphabet.findById(Alphabet.class,firstletter.getId() + randomvalueforid);
        letterpair = Alphabet.findById(Alphabet.class,firstletter.getId() + randomvalueforid + 1);

        filteredwords.clear();

          for(Word w: words) {
            if (w.alphabet.pl_str.equals(letter.pl_str) || w.alphabet.pl_str.equals(letterpair.pl_str))
                filteredwords.add(w);
        }
        filteredwordscount = (long) filteredwords.size();
        word = filteredwords.get((new Random()).nextInt((int) filteredwordscount));

        Button button1 = (Button) findViewById(R.id.orthography_answer1_button);
        Button button2 = (Button) findViewById(R.id.orthography_answer2_button);
        TextView tw1 = (TextView) findViewById(R.id.partofword);

        final int diff = indexOfWordsDifference(word.full_word,word.part_word);

        String puzzle_word = word.part_word.substring(0,diff) + "..." + word.part_word.substring(diff);

        button1.setText(letter.pl_str);
        button2.setText(letterpair.pl_str);
        tw1.setText(puzzle_word);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = word.part_word.substring(0,diff) + letter.pl_str + word.part_word.substring(diff);

                if(answer.equals(word.full_word)) {
                    Toast.makeText(context,"Dobrze",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context,"Źle",Toast.LENGTH_SHORT).show();
                }
                orthographyTest();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = word.part_word.substring(0,diff) + letterpair.pl_str + word.part_word.substring(diff);

                if(answer.equals(word.full_word)) {
                    Toast.makeText(context,"Dobrze",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context,"Źle",Toast.LENGTH_SHORT).show();
                }
                orthographyTest();
            }
        });
    }
    //nie znalazłem żadnej metody string która by zwracała index różnych znaków
    int indexOfWordsDifference(String full_word, String part_word) {
        int diff = -1;
        for(int a = 0; a < part_word.length(); a++) {
            if(full_word.charAt(a) != part_word.charAt(a)) {
                diff = a;
                break;
            }
        }
        if(diff == -1) {
            diff = part_word.length();
        }
        return diff;
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
