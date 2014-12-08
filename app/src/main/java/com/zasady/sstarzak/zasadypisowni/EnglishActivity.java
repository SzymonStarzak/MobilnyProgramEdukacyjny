package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

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
        englishWordsGame();
    }

    public void englishWordsGame() {
        random_value_for_id = (new Random()).nextInt((int) (english_words_count));
        english_word = EnglishWords.findById(EnglishWords.class, english_first_word.getId() + random_value_for_id);

        tv_example.setText(english_word.example);
        tv_word.setText(english_word.word);
        rb1.setText(english_word.correct);
        rb2.setText(english_word.fake1);
        rb3.setText(english_word.fake2);
        rb4.setText(english_word.fake3);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.english_radio1: break;
            case R.id.english_radio2: break;
            case R.id.english_radio3: break;
            case R.id.english_radio4: break;
            case R.id.english_words_check_button: break;
        }
    }
}
