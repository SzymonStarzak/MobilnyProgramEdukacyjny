package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LanguageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Button button1 = (Button) findViewById(R.id.ortography_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrthographyActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.hangman_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HangmanActivity.class);
                intent.putExtra("language","polish");
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.hangman_eng_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HangmanActivity.class);
                intent.putExtra("language","english");
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.english_words);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnglishActivity.class);
                startActivity(intent);
            }
        });
    }

}
