package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HangmanActivity extends Activity implements View.OnClickListener {

    private List<PolishAlphabet> polish_letters;

    private List<Category> categories;

    private List<GameWords> game_words;

    private List <Button> button_array;

    private ImageView hangman_image;

    private TableLayout tl_buttons;

    private TextView hangman_tw;

    private long polish_letters_count;

    private long firstword;

    private long randomvalueforid;

    private int randomletterindex;

    private String hangman_puzzle;

    private String hangman_word;

    private int attempts = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        polish_letters = PolishAlphabet.listAll(PolishAlphabet.class);
        polish_letters_count = PolishAlphabet.count(PolishAlphabet.class,null,null);
        button_array = new ArrayList<Button>();
        tl_buttons = (TableLayout) findViewById(R.id.hangman_buttons);

        categories = Category.listAll(Category.class);
        game_words = GameWords.listAll(GameWords.class);

        TableRow tw_buttons = new TableRow(this);
        TableRow.LayoutParams table_params = new TableRow.LayoutParams();
        table_params.height = 55;
        table_params.width = 55;

        int iterator = 0;
        for(PolishAlphabet pa : polish_letters) {
            Button temp_butt = new Button(this);
            temp_butt.setId(iterator++);
            temp_butt.setText(pa.letter);
            button_array.add(temp_butt);
        }

        iterator = 0;
        for( Button b : button_array) {
            b.setOnClickListener(this);
            tw_buttons.addView(b, table_params);
            if( iterator++ == (polish_letters_count/4)-1) {
                iterator = 0;
                tl_buttons.addView(tw_buttons);
                tw_buttons = new TableRow(this);
            }
        }

        hangmanTest();
    }

    public void hangmanTest() {
        attempts = 4;
        firstword = game_words.get(1).getId();
        randomvalueforid = (new Random()).nextInt((int) (GameWords.count(GameWords.class,null,null)));
        randomletterindex = 0;
        hangman_puzzle = "";
        hangman_word = game_words.get((int) randomvalueforid).word;

        hangman_tw = (TextView) findViewById(R.id.hangman_textView);
        randomletterindex = new Random().nextInt( hangman_word.length() );
        hangman_tw.setText("");

        hangman_image = (ImageView) findViewById(R.id.hangman_image);
        hangman_image.setImageResource(R.drawable.hangman1);

        for(int a = 0; a < polish_letters_count; a++) {
            Button b = (Button) findViewById(a);
            b.setVisibility(View.VISIBLE);
        }

        for(int a = 0; a < hangman_word.length(); a++) {
            if(hangman_word.charAt(a) != hangman_word.charAt(randomletterindex))
                hangman_puzzle += "_";
            else
                hangman_puzzle += hangman_word.charAt(randomletterindex);
        }

        hangman_tw.setText(hangman_puzzle);
    }
//nie umiem wyrażeń regularnych
    public void hangmanGame( String selected_letter) {
        String hangman_temp = "";
        if(hangman_word.contains(selected_letter)) {
            for(int a = 0; a < hangman_word.length();a++ ) {
                if(hangman_word.charAt(a) == selected_letter.charAt(0) && hangman_puzzle.charAt(a) == '_')
                    hangman_temp += hangman_word.charAt(a);
                else
                    hangman_temp += hangman_puzzle.charAt(a);
            }
            hangman_puzzle = hangman_temp;
            hangman_tw.setText(hangman_puzzle);
        }
        else {
            attempts--;
            switch (attempts) {
                case 3: hangman_image.setImageResource(R.drawable.hangman2); break;
                case 2: hangman_image.setImageResource(R.drawable.hangman3); break;
                case 1: hangman_image.setImageResource(R.drawable.hangman4); break;
                case 0: {
                    hangman_image.setImageResource(R.drawable.hangman5);
                    final Handler h = new Handler();
                    final Runnable r1 = new Runnable() {
                        @Override
                        public void run() {
                            hangmanTest();
                        }
                    };
                    h.postDelayed(r1,3000);
                    break;
                }
            }
        }

    }
    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        b.setVisibility(View.INVISIBLE);
        hangmanGame(String.valueOf(b.getText()).toLowerCase());
    }
}
