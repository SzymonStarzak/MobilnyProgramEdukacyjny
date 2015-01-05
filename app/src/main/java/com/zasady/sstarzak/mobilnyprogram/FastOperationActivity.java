package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.Random;


public class FastOperationActivity extends Activity implements View.OnClickListener {

    private CountDownTimer cdt;

    private CountDownTimer cdt2;

    private LinearLayout expression_ll;

    private  Integer cdt_time;

    private TextView number1_tv;

    private  TextView number2_tv;

    private TextView number3_tv;

    private TextView correct_number_tv;

    private  TextView operator1_tv;

    private  TextView operator2_tv;

    private TextView countdown_tv;

    private Button button_plus;

    private   Button button_minus;

    private  Button button_times;

    private   Button button_div;

    private   Button button_mod;

    private  Integer number1;

    private   Integer number2;

    private  Integer number3;

    private  Integer correct_number;

    private   Integer answer_number;

    private    Integer operators_count;

    private   Integer max_number;

    private   Integer operator1;

    private  Integer operator2;

    private  Integer attempts;

    private  String sOperator1;

    private  String sOperator2;

    @Override
    protected void onStop() {
        super.onStop();
        cdt.cancel();
        cdt2.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_operation);

        expression_ll = (LinearLayout) findViewById(R.id.expression_ll);

        countdown_tv = (TextView) findViewById(R.id.countdown_tv);
        number1_tv = (TextView) findViewById(R.id.number1_tv);
        number2_tv = (TextView) findViewById(R.id.number2_tv);
        number3_tv = (TextView) findViewById(R.id.number3_tv);
        correct_number_tv = (TextView) findViewById(R.id.correct_number_tv);
        operator1_tv = (TextView) findViewById(R.id.operator1_tv);
        operator2_tv = (TextView) findViewById(R.id.operator2_tv);

        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "android_7.ttf");
        number1_tv.setTypeface(tf);
        number2_tv.setTypeface(tf);
        number3_tv.setTypeface(tf);
        correct_number_tv.setTypeface(tf);

        button_plus = (Button) findViewById(R.id.button_plus);
        button_minus = (Button) findViewById(R.id.button_minus);
        button_times = (Button) findViewById(R.id.button_times);
        button_div = (Button) findViewById(R.id.button_div);
        button_mod = (Button) findViewById(R.id.button_mod);

        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_times.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_mod.setOnClickListener(this);

        operators_count = 5;
        max_number = 15;

        cdt_time = 15000;

        fastOperationGame();
    }

    public void fastOperationGame() {
        cdt = new CountDownTimer(cdt_time, 100) {
            @Override
            public void onTick(long l) {
                countdown_tv.setText(String.valueOf(l / 1000));
                if(l / 1000 <= 5) {
                    MyViewAnimations.myTimerShakeAnimation(countdown_tv, 550);
                    countdown_tv.setTextColor(Color.RED);
                }
            }
            @Override
            public void onFinish() {
                countdown_tv.setText("0");
                onIncorrectAnswer();
            }
        }.start();

        cdt2 = new CountDownTimer(cdt_time, 900) {
            @Override
            public void onTick(long l) {
                switch (attempts) {
                    case 2: MyViewAnimations.myJumpingAnimation(operator1_tv,900,operator1_tv.getHeight()/2); break;
                    case 1: MyViewAnimations.myJumpingAnimation(operator2_tv,900,operator2_tv.getHeight()/2); break;
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countdown_tv.setTextColor(Color.BLACK);
        enableButtons(true);

        Random r = new Random();

        attempts = 2;

        number1 = r.nextInt(2 * max_number) - 15;
        number2 = r.nextInt(max_number) + 1;
        number3 = r.nextInt(max_number) + 1;

        operator1 = r.nextInt(operators_count);
        operator2 = r.nextInt(operators_count);

        JexlEngine jexl = new JexlEngine();
        Expression func = jexl.createExpression("x1" + getSymbolicOperator(operator1) + "x2" + getSymbolicOperator(operator2) + "x3");
        MapContext mc = new MapContext();

        mc.set("x1", number1);
        mc.set("x2", number2);
        mc.set("x3", number3);

        number1_tv.setText(String.valueOf(number1));
        number2_tv.setText(String.valueOf(number2));
        number3_tv.setText(String.valueOf(number3));

        operator1_tv.setText("?");
        operator2_tv.setText("?");

        correct_number = Integer.parseInt(func.evaluate(mc).toString());
        correct_number_tv.setText(String.valueOf(correct_number));
    }

    public void fastOperationTest(String sOperator1, String sOperator2) {

        JexlEngine jexl = new JexlEngine();
        Expression func = jexl.createExpression("x1" + sOperator1 + "x2" + sOperator2 + "x3");
        MapContext mc = new MapContext();

        mc.set("x1", number1);
        mc.set("x2", number2);
        mc.set("x3", number3);

        answer_number = Integer.parseInt(func.evaluate(mc).toString());

        if (answer_number.equals(correct_number)) {
            onCorrectAnswer();
        } else {
            onIncorrectAnswer();
        }
    }

    public void onCorrectAnswer() {
        final GradientDrawable gd = (GradientDrawable) expression_ll.getBackground();
        cdt.cancel();
        cdt2.cancel();
        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                gd.setColor(Color.parseColor("#EBFCCE"));
                expression_ll.setBackground(gd);
                fastOperationGame();
            }
        };
        h.postDelayed(r1, 2000);

        gd.setColor(Color.GREEN);
        expression_ll.setBackground(gd);

        enableButtons(false);
    }

    public void onIncorrectAnswer() {
        cdt.cancel();
        cdt2.cancel();

        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                fastOperationGame();
            }
        };
        h.postDelayed(r1, 2000);
        MyViewAnimations.myWrongAnswerShakerAnimation(expression_ll, 25, 10);
        enableButtons(false);
    }
    public void enableButtons(boolean b) {
        button_plus.setEnabled(b);
        button_minus.setEnabled(b);
        button_div.setEnabled(b);
        button_mod.setEnabled(b);
        button_times.setEnabled(b);
    }
    public String getSymbolicOperator(int operator) {
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            case 4:
                return "%";
        }
        return "";
    }

    @Override
    public void onClick(View view) {

        if (attempts == 2) {
            switch (view.getId()) {
                case R.id.button_plus:
                    sOperator1 = getSymbolicOperator(0);
                    break;
                case R.id.button_minus:
                    sOperator1 = getSymbolicOperator(1);
                    break;
                case R.id.button_times:
                    sOperator1 = getSymbolicOperator(2);
                    break;
                case R.id.button_div:
                    sOperator1 = getSymbolicOperator(3);
                    break;
                case R.id.button_mod:
                    sOperator1 = getSymbolicOperator(4);
                    break;
            }
            operator1_tv.setText(sOperator1);
            attempts--;
        } else if (attempts == 1) {
            switch (view.getId()) {
                case R.id.button_plus:
                    sOperator2 = getSymbolicOperator(0);
                    break;
                case R.id.button_minus:
                    sOperator2 = getSymbolicOperator(1);
                    break;
                case R.id.button_times:
                    sOperator2 = getSymbolicOperator(2);
                    break;
                case R.id.button_div:
                    sOperator2 = getSymbolicOperator(3);
                    break;
                case R.id.button_mod:
                    sOperator2 = getSymbolicOperator(4);
                    break;
            }
            operator2_tv.setText(sOperator2);
            attempts--;
        }

        if (attempts == 0) {
            fastOperationTest(sOperator1, sOperator2);
        }
    }
}
