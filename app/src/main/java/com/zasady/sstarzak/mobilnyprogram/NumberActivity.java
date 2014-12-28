package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class NumberActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        Button b1 = (Button) findViewById(R.id.fast_operation_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FastOperationActivity.class);
                startActivity(intent);
            }
        });

        Button b2 = (Button) findViewById(R.id.periodic_table_button);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PeriodicTableActivity.class);
                intent.putExtra("language","polish");
                startActivity(intent);
            }
        });

        Button b3 = (Button) findViewById(R.id.eng_periodic_table_button);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PeriodicTableActivity.class);
                intent.putExtra("language","english");
                startActivity(intent);
            }
        });

        Button b4 = (Button) findViewById(R.id.numeral_game_button);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NumeralSystemActivity.class);
                startActivity(intent);
            }
        });

    }

}
