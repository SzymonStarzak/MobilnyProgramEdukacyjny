package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OrthographyActivity extends Activity {

    private List<OrthographyWord> orthographyWords;

    private OrthographyWord orthographyWord;

    private List<OrthographyWord> filteredwords;

    private OrthographyAlphabet firstletter;

    private OrthographyAlphabet letter;

    private OrthographyAlphabet letterpair;

    private Select selectalphabet;

    private long alphabetcount;

    private long wordscount;

    private long filteredwordscount;

    private long randomvalueforid;

    Context context;

    Button button1;

    Button button2;

    TextView tw1;

    String answer;

   // private long random

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orthography);

        button1 = (Button) findViewById(R.id.orthography_answer1_button);
        button2 = (Button) findViewById(R.id.orthography_answer2_button);
        tw1 = (TextView) findViewById(R.id.partofword);

        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(100);
        gd.setStroke(10, Color.LTGRAY);
        gd.setSize(p.x / 4, p.x / 4);


        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "PTF76F.ttf");
        button1.setTypeface(tf);
        button1.setBackground(gd);
        button1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        button2.setTypeface(tf);
        button2.setBackground(gd);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);

        tw1.setTypeface(tf);
        tw1.setTextScaleX(1.4f);

        alphabetcount = OrthographyAlphabet.count(OrthographyAlphabet.class, null, null);
        selectalphabet = Select.from(OrthographyAlphabet.class);
        firstletter = (OrthographyAlphabet) selectalphabet.first();
        letter = OrthographyAlphabet.findById(OrthographyAlphabet.class, firstletter.getId() + randomvalueforid);
        orthographyWords = OrthographyWord.listAll(OrthographyWord.class);
        wordscount = OrthographyWord.count(OrthographyWord.class, null, null);
        filteredwords = new ArrayList<OrthographyWord>();

        context = getApplicationContext();

        orthographyTest();
    }

    public void orthographyTest() {
        tw1.setTextColor(Color.BLACK);
        //losowanie liter o nieparzystej liczbie pozycyjnej, id + 1 będzie alternatywną odpowiedzią
        do {
            randomvalueforid = (new Random()).nextInt((int) (alphabetcount));
        }while(randomvalueforid %2 == 1);

        letter = OrthographyAlphabet.findById(OrthographyAlphabet.class, firstletter.getId() + randomvalueforid);
        letterpair = OrthographyAlphabet.findById(OrthographyAlphabet.class, firstletter.getId() + randomvalueforid + 1);

        filteredwords.clear();

          for(OrthographyWord w: orthographyWords) {
            if (w.orthographyAlphabet.pl_str.equals(letter.pl_str) || w.orthographyAlphabet.pl_str.equals(letterpair.pl_str))
                filteredwords.add(w);
        }
        filteredwordscount = (long) filteredwords.size();
        orthographyWord = filteredwords.get((new Random()).nextInt((int) filteredwordscount));



        final int diff = indexOfWordsDifference(orthographyWord.full_word, orthographyWord.part_word);

        String puzzle_word = orthographyWord.part_word.substring(0,diff) + "..." + orthographyWord.part_word.substring(diff);

        button1.setText(letter.pl_str);
        button2.setText(letterpair.pl_str);
        tw1.setText(puzzle_word);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 answer = orthographyWord.part_word.substring(0,diff) + letter.pl_str + orthographyWord.part_word.substring(diff);

                if(answer.equals(orthographyWord.full_word)) {
                    onCorrectAnswer();

                } else {
                    onIncorrectAnswer();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 answer = orthographyWord.part_word.substring(0,diff) + letterpair.pl_str + orthographyWord.part_word.substring(diff);

                if(answer.equals(orthographyWord.full_word)) {
                    onCorrectAnswer();

                } else {
                   onIncorrectAnswer();
                }
            }
        });
    }

    public void onCorrectAnswer() {
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                orthographyTest();
            }
        };
        h.postDelayed(r1, 2000);
        tw1.setText(answer);
        tw1.setTextColor(Color.GREEN);
        Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
    }

    public void onIncorrectAnswer() {
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                orthographyTest();
            }
        };
        h.postDelayed(r1, 2000);
        tw1.setText(answer);
        tw1.setTextColor(Color.RED);
        Toast.makeText(this, "Źle", Toast.LENGTH_SHORT).show();
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

}
