package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class NumeralSystemActivity extends Activity implements View.OnClickListener {

    CountDownTimer cdt;

    TextView countdown_tv;

    int cdt_time;

    int max_random_range;

    int min_random_value;

    int[] radioIds;

    RadioButton[] radioButtons;

    Button check_button;

    Integer[] id_for_correct;

    Integer[] id_for_answers;

    Random r;

    Integer correct;

    String[] corrects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeral_system);

        cdt_time = 40000;
        min_random_value = 2;
        max_random_range = 20;

        countdown_tv = (TextView) findViewById(R.id.countdown_tv);

        check_button = (Button) findViewById(R.id.numeral_check_button);
        check_button.setOnClickListener(this);

        radioIds = new int[9];
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

        for (int a = 0; a < 9; a++) {
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
                numeralSystemTest();
            }
        }.start();

        clearRadioGroups();

        id_for_answers = new Integer[3];

        id_for_correct = new Integer[3];
        r = new Random();
        for (int a = 0; a < 9; a += 3) {
            id_for_correct[a / 3] = radioIds[a + r.nextInt(3)];
        }

        correct = new Random().nextInt(max_random_range) + min_random_value;
        corrects = new String[3];
        corrects[0] = correct.toString();
        corrects[1] = Integer.toHexString(correct);
        corrects[2] = Integer.toBinaryString(correct);

        for (int a = 0; a < 3; a++) {
            RadioButton rb = (RadioButton) findViewById(id_for_correct[a]);
            rb.setText(corrects[a]);
        }

        Integer[] inc = generateInncorrectNumbers(correct);

        for (int a = 0; a < 9; a++) {
            RadioButton rb = (RadioButton) findViewById(radioIds[a]);

            if (!Arrays.asList(id_for_correct).contains(rb.getId())) {
                if (a <= 2) rb.setText(Integer.toString(inc[a]));
                if (a > 2 && a <= 5) rb.setText(Integer.toHexString(inc[a]));
                if (a > 5) rb.setText(Integer.toBinaryString(inc[a]));
            }
        }
    }

    public void numeralSystemTest() {
        if (Arrays.equals(id_for_answers, id_for_correct)) {
            onCorrectAnswer();
        } else {
            onIncorrectAnswer();
        }
    }

    public void onCorrectAnswer() {
        Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
        cdt.cancel();
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                numeralSystemGame();
            }
        };
        h.postDelayed(r1, 2000);
    }

    public void onIncorrectAnswer() {
        Toast.makeText(this, "Źle", Toast.LENGTH_SHORT).show();
        cdt.cancel();
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                numeralSystemGame();
            }
        };
        h.postDelayed(r1, 2000);
    }

    public Integer[] generateInncorrectNumbers(int correct) {
        Integer[] inc = new Integer[9];
        int index = 0;
        int random;

        do {
            do {
                random = new Random().nextInt(max_random_range) + min_random_value;
            } while ((random == correct) || Arrays.asList(inc).contains(random));

            inc[index++] = random;
        } while (index < 9);

        return inc;
    }

    public void clearRadioGroups() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupDec);
        rg.clearCheck();
        rg = (RadioGroup) findViewById(R.id.radioGroupHex);
        rg.clearCheck();
        rg = (RadioGroup) findViewById(R.id.radioGroupBin);
        rg.clearCheck();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cdt.cancel();
    }

    @Override
    public void onClick(View view) {

        if (view.getClass().equals(RadioButton.class)) {
            RadioButton rb = (RadioButton) view;
            if (rb.getId() == radioIds[0] || rb.getId() == radioIds[1] || rb.getId() == radioIds[2]) {
                id_for_answers[0] = rb.getId();
            } else if (rb.getId() == radioIds[3] || rb.getId() == radioIds[4] || rb.getId() == radioIds[5]) {
                id_for_answers[1] = rb.getId();
            } else if (rb.getId() == radioIds[6] || rb.getId() == radioIds[7] || rb.getId() == radioIds[8]) {
                id_for_answers[2] = rb.getId();
            }

        } else if (view.getClass().equals(Button.class)) {
            numeralSystemTest();
        }
    }
}
