package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HangmanActivity extends Activity implements View.OnClickListener {

    private List<PolishAlphabet> polish_letters;

    private List<EnglishAlphabet> english_letters;

    private Context context;

    private List<HangmanWords> hangman_words;

    private List<HangmanWordsEng> hangman_english_words;

    private List<Button> button_array;

    private ImageView hangman_image;

    private TableLayout tl_buttons;

    RelativeLayout rl;

    private TextView tv_hangman;

    private TextView tv_category;

    private Intent intent_language;

    private long letters_count;

    private long firstword;

    private long randomvalueforid;

    private int randomletterindex;

    private String selected_language;

    private String hangman_puzzle;

    private String hangman_word;

    private int attempts = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        context = getApplicationContext();

        rl = (RelativeLayout) findViewById(R.id.rl_hangman);

        hangman_image = (ImageView) findViewById(R.id.hangman_imageView);
        hangman_image.setScaleX(2.0f);
        hangman_image.setScaleY(1.5f);

        intent_language = getIntent();
        selected_language = intent_language.getStringExtra("language");

        polish_letters = PolishAlphabet.listAll(PolishAlphabet.class);
        english_letters = EnglishAlphabet.listAll(EnglishAlphabet.class);

        if (selected_language.equals("polish")) {
            letters_count = PolishAlphabet.count(PolishAlphabet.class, null, null);
            hangman_words = HangmanWords.listAll(HangmanWords.class);
        } else if (selected_language.equals("english")) {
            letters_count = EnglishAlphabet.count(EnglishAlphabet.class, null, null);
            hangman_english_words = HangmanWordsEng.listAll(HangmanWordsEng.class);
        }

        button_array = new ArrayList<Button>();
        tl_buttons = (TableLayout) findViewById(R.id.hangman_buttons);

        TableRow tw_buttons = new TableRow(this);
        TableRow.LayoutParams table_params = new TableRow.LayoutParams();


        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        table_params.width = p.x / 9;
        table_params.height = table_params.width;

        GradientDrawable gd = new GradientDrawable();
        gd.setStroke(1, 0xFF000000);
        gd.setColor(Color.rgb(175, 238, 238));

        int iterator = 0;
        for (int a = 0; a < letters_count; a++) {
            Button temp_butt = new Button(this);
            temp_butt.setId(iterator++);
            temp_butt.setTextSize(10);
            temp_butt.setBackground(gd);
            if (selected_language.equals("polish"))
                temp_butt.setText(polish_letters.get(a).letter);
            else if (selected_language.equals("english"))
                temp_butt.setText(english_letters.get(a).letter);
            button_array.add(temp_butt);
        }
        iterator = 0;
        for (Button b : button_array) {
            b.setOnClickListener(this);
            tw_buttons.addView(b, table_params);
            if (iterator++ == 7) {
                iterator = 0;
                tl_buttons.addView(tw_buttons);
                tw_buttons = new TableRow(this);
            }
        }
        if (selected_language.equals("english") && iterator > 0) {               //to dla angielskiej wersji
            tl_buttons.addView(tw_buttons);
        }
        hangmanTest();
    }

    public void hangmanTest() {
        attempts = 5;
        //pl
        if (selected_language.equals("polish")) {
            firstword = hangman_words.get(1).getId();
            randomvalueforid = (new Random()).nextInt((int) (HangmanWords.count(HangmanWords.class, null, null)));
            hangman_word = hangman_words.get((int) randomvalueforid).word;
        } else if (selected_language.equals("english")) {
            firstword = hangman_english_words.get(1).getId();
            randomvalueforid = (new Random()).nextInt((int) (HangmanWordsEng.count(HangmanWordsEng.class, null, null)));
            hangman_word = hangman_english_words.get((int) randomvalueforid).word;
        }
        randomletterindex = 0;
        hangman_puzzle = "";

        tv_hangman = (TextView) findViewById(R.id.hangman_textView);
        tv_hangman.setTextScaleX(3.0f);


        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "DroidSerif-Italic.ttf");
        GradientDrawable gd = new GradientDrawable();
        gd.setStroke(1, 0xFF000000);
        gd.setCornerRadius(5);
        gd.setColor(Color.rgb(250, 128, 114));
        tv_category = (TextView) findViewById(R.id.hangman_category_textView);
        tv_category.setTypeface(tf);
        tv_category.setBackground(gd);

        if (selected_language.equals("polish"))
            tv_category.setText("Kategoria: " + hangman_words.get((int) randomvalueforid).hangmanCategory.plname);
        else if (selected_language.equals("english"))
            tv_category.setText("Category: " + hangman_english_words.get((int) randomvalueforid).hangmanCategory.engname);

        randomletterindex = new Random().nextInt(hangman_word.length());

        hangman_image.setImageResource(R.drawable.hangman1);

        for (int a = 0; a < letters_count; a++) {
            Button b = (Button) findViewById(a);
            if (!b.getText().toString().toLowerCase().equals(String.valueOf(hangman_word.charAt(randomletterindex))))
                b.setVisibility(View.VISIBLE);
            else
                b.setVisibility(View.INVISIBLE);
        }

        for (int a = 0; a < hangman_word.length(); a++) {
            if (hangman_word.charAt(a) != hangman_word.charAt(randomletterindex))
                hangman_puzzle += "-";
            else
                hangman_puzzle += hangman_word.charAt(randomletterindex);
        }

        tv_hangman.setText(hangman_puzzle);
        tv_hangman.setTextColor(Color.BLACK);
    }

    public void hangmanGame(String selected_letter) {
        String hangman_temp = "";
        if (hangman_word.contains(selected_letter)) {
            for (int a = 0; a < hangman_word.length(); a++) {
                if (hangman_word.charAt(a) == selected_letter.charAt(0) && hangman_puzzle.charAt(a) == '-')
                    hangman_temp += hangman_word.charAt(a);
                else
                    hangman_temp += hangman_puzzle.charAt(a);
            }
            hangman_puzzle = hangman_temp;
            tv_hangman.setText(hangman_puzzle);
        } else {
            attempts--;
            switch (attempts) {
                case 4:
                    hangman_image.setImageResource(R.drawable.hangman2);
                    break;
                case 3:
                    hangman_image.setImageResource(R.drawable.hangman3);
                    break;
                case 2:
                    hangman_image.setImageResource(R.drawable.hangman4);
                    break;
                case 1:
                    hangman_image.setImageResource(R.drawable.hangman5);
                    break;
                case 0: {
                    hangman_image.setImageResource(R.drawable.hangman6);
                    onIncorrectAnswer();
                    break;
                }
            }
        }
        if (hangman_word.equals(hangman_puzzle)) {
           onCorrectAnswer();
        }
    }
    public void onCorrectAnswer() {
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                hangmanTest();
            }
        };
        h.postDelayed(r1, 2000);
        tv_hangman.setTextColor(Color.GREEN);
    }

    public void onIncorrectAnswer() {
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                hangmanTest();
            }
        };
        h.postDelayed(r1, 2000);
        tv_hangman.setText(hangman_word);
        tv_hangman.setTextColor(Color.RED);
    }
    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        b.setVisibility(View.INVISIBLE);
        hangmanGame(String.valueOf(b.getText()).toLowerCase());
    }
}
