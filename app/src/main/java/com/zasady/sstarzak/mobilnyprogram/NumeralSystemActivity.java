package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class NumeralSystemActivity extends Activity implements View.OnClickListener {

    CountDownTimer cdt;

    TextView countdown_tv;

    int cdt_time;

    int [] radioIds;

    RadioButton [] radioButtons;

    int [] id_for_correct;

    Random r;

    Integer correct;

    String [] corrects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeral_system);

        cdt_time = 15000;
        countdown_tv = (TextView) findViewById(R.id.countdown_tv);

        radioIds = new int [9];
        radioIds[0] = R.id.radiodec1;
        radioIds[1] = R.id.radiodec2;
        radioIds[2] = R.id.radiodec3;
        radioIds[3] = R.id.radiohex1;
        radioIds[4] = R.id.radiohex2;
        radioIds[5] = R.id.radiohex3;
        radioIds[6] = R.id.radiobin1;
        radioIds[7] = R.id.radiobin2;
        radioIds[8] = R.id.radiobin3;

        radioButtons = new RadioButton[9];

        for(int a = 0; a < 9; a++) {
            radioButtons[a] = (RadioButton) findViewById(radioIds[a]);
            radioButtons[a].setOnClickListener(this);
        }
        numeralSystemGame();
    }

    public void numeralSystemGame() {
        cdt = new CountDownTimer(cdt_time, 100) {
            @Override
            public void onTick(long l) {
                countdown_tv.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                countdown_tv.setText("0");
               // onIncorrectAnswer();
            }
        }.start();

        id_for_correct = new int[3];
        r = new Random();
        for(int a = 0; a < 9; a+=3) {
            id_for_correct[a/3] = radioIds[a + r.nextInt(3)];
        }

        correct = new Random().nextInt(100) + 20;
        corrects = new String[3];
        corrects[0] = correct.toString();
        corrects[1] = Integer.toHexString(correct);
        corrects[2] = Integer.toBinaryString(correct);

        for(int a = 0; a < 3; a++) {
            RadioButton rb = (RadioButton) findViewById(id_for_correct[a]);
            rb.setText(corrects[a]);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        cdt.cancel();
    }

    @Override
    public void onClick(View view) {
        RadioButton r = (RadioButton) view;
        Toast.makeText(this,r.getText(),Toast.LENGTH_SHORT ).show();
    }
}
