package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Select;

import java.util.List;
import java.util.Random;

public class EnglishActivity extends Activity implements View.OnClickListener {

    private List<EnglishWords> ew;

    private EnglishWords english_word;

    private EnglishWords english_first_word;

    private TextView tv_example;

    private TextView tv_word;

    private long english_words_count;

    private int random_value_for_id;

    private int random_position_of_correct;

    private int user_answer;

    private RadioButton rb1;

    private RadioButton rb2;

    private RadioButton rb3;

    private RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english);

        ew = EnglishWords.listAll(EnglishWords.class);
        english_words_count = EnglishWords.count(EnglishWords.class, null, null);
        english_first_word = Select.from(EnglishWords.class).first();

        rb1 = (RadioButton) findViewById(R.id.english_radio1);
        rb1.setOnClickListener(this);
        rb2 = (RadioButton) findViewById(R.id.english_radio2);
        rb2.setOnClickListener(this);
        rb3 = (RadioButton) findViewById(R.id.english_radio3);
        rb3.setOnClickListener(this);
        rb4 = (RadioButton) findViewById(R.id.english_radio4);
        rb4.setOnClickListener(this);

        Button b1 = (Button) findViewById(R.id.english_words_check_button);
        b1.setOnClickListener(this);

        tv_example = (TextView) findViewById(R.id.english_sentence_example);
        tv_word = (TextView) findViewById(R.id.english_word);
        englishWordsTest();
    }
    public void englishWordGame() {
        if(random_position_of_correct == user_answer){
            final Handler h = new Handler();
            final Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    englishWordsTest();
                }
            };
            h.postDelayed(r1,2000);
            Toast.makeText(getApplicationContext(),"Dobrze",Toast.LENGTH_SHORT).show();
        }else{
            final Handler h = new Handler();
            final Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    englishWordsTest();
                }
            };
            h.postDelayed(r1,2000);
            Toast.makeText(getApplicationContext(),"Źle",Toast.LENGTH_SHORT).show();
        }
    }
    public void englishWordsTest() {
        random_value_for_id = (new Random()).nextInt((int) (english_words_count));
        english_word = EnglishWords.findById(EnglishWords.class, english_first_word.getId() + random_value_for_id);
        random_position_of_correct = new Random().nextInt(4);

        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);

        switch (random_position_of_correct) {
            case 0: rb1.setText(english_word.correct);
                    rb2.setText(english_word.fake1);
                    rb3.setText(english_word.fake2);
                    rb4.setText(english_word.fake3);break;
            case 1: rb2.setText(english_word.correct);
                    rb1.setText(english_word.fake1);
                    rb3.setText(english_word.fake2);
                    rb4.setText(english_word.fake3);break;
            case 2: rb3.setText(english_word.correct);
                    rb2.setText(english_word.fake1);
                    rb1.setText(english_word.fake2);
                    rb4.setText(english_word.fake3);break;
            case 3: rb4.setText(english_word.correct);
                    rb2.setText(english_word.fake1);
                    rb3.setText(english_word.fake2);
                    rb1.setText(english_word.fake3);break;
        }
        tv_example.setText("Przykład: " + english_word.example);
        tv_word.setText(english_word.word);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.english_radio1: user_answer = 0; break;
            case R.id.english_radio2: user_answer = 1; break;
            case R.id.english_radio3: user_answer = 2; break;
            case R.id.english_radio4: user_answer = 3; break;
            case R.id.english_words_check_button: englishWordGame(); break;
        }
    }
}
