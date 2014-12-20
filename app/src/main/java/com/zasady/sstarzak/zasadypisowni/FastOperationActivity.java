package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.Random;


public class FastOperationActivity extends Activity implements View.OnClickListener{

    CountDownTimer cdt;

    Integer cdt_time;

    TextView number1_tv;

    TextView number2_tv;

    TextView number3_tv;

    TextView correct_number_tv;

    TextView operator1_tv;

    TextView operator2_tv;

    TextView countdown_tv;

    Button button_plus;

    Button button_minus;

    Button button_times;

    Button button_div;

    Button button_mod;

    Integer number1;

    Integer number2;

    Integer number3;

    Integer correct_number;

    Integer answer_number;

    Integer operators_count;

    Integer max_number;

    Integer operator1;

    Integer operator2;

    Integer attempts;

    String sOperator1;

    String sOperator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_operation);
        countdown_tv = (TextView) findViewById(R.id.countdown_tv);
        number1_tv = (TextView) findViewById(R.id.number1_tv);
        number2_tv = (TextView) findViewById(R.id.number2_tv);
        number3_tv = (TextView) findViewById(R.id.number3_tv);
        correct_number_tv = (TextView) findViewById(R.id.correct_number_tv);
        operator1_tv = (TextView) findViewById(R.id.operator1_tv);
        operator2_tv = (TextView) findViewById(R.id.operator2_tv);

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
            }

            @Override
            public void onFinish() {
                countdown_tv.setText("0"); onIncorrectAnswer();
            }
        }.start();

        Random r = new Random();

        attempts = 2;

        number1 = r.nextInt(2 * max_number)-15;
        number2 = r.nextInt(max_number)+1;
        number3 = r.nextInt(max_number)+1;

        operator1 = r.nextInt(operators_count);
        operator2 = r.nextInt(operators_count);

        JexlEngine jexl = new JexlEngine();
        Expression func = jexl.createExpression("x1"+getSymbolicOperator(operator1)+"x2"+getSymbolicOperator(operator2)+"x3");
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
        Expression func = jexl.createExpression("x1"+sOperator1+"x2"+sOperator2+"x3");
        MapContext mc = new MapContext();

        mc.set("x1", number1);
        mc.set("x2", number2);
        mc.set("x3", number3);

        answer_number = Integer.parseInt(func.evaluate(mc).toString());

        if (answer_number.equals(correct_number)) {
            onCorrectAnswer();
        }
        else {
            onIncorrectAnswer();
        }
    }

    public void onCorrectAnswer() {
        Toast.makeText(this,"Dobrze",Toast.LENGTH_SHORT).show();
        cdt.cancel();
        fastOperationGame();
    }
    public void onIncorrectAnswer() {
        Toast.makeText(this,"Źle " + getSymbolicOperator(operator1) + " " + getSymbolicOperator(operator2),Toast.LENGTH_LONG).show();
        cdt.cancel();
        fastOperationGame();
    }
    public String getSymbolicOperator(int operator) {
        switch (operator) {
            case 0: return "+";
            case 1: return "-";
            case 2: return "*";
            case 3: return "/";
            case 4: return "%";
        }
        return "";
    }
    @Override
    public void onClick(View view) {

        if(attempts == 2) {
            switch (view.getId()) {
                case R.id.button_plus: sOperator1 = getSymbolicOperator(0); break;
                case R.id.button_minus: sOperator1 = getSymbolicOperator(1); break;
                case R.id.button_times: sOperator1 = getSymbolicOperator(2); break;
                case R.id.button_div: sOperator1 = getSymbolicOperator(3); break;
                case R.id.button_mod: sOperator1 = getSymbolicOperator(4); break;
            }
            operator1_tv.setText(sOperator1);
            attempts--;
        } else if( attempts == 1) {
            switch (view.getId()) {
                case R.id.button_plus: sOperator2 = getSymbolicOperator(0); break;
                case R.id.button_minus: sOperator2 = getSymbolicOperator(1); break;
                case R.id.button_times: sOperator2 = getSymbolicOperator(2); break;
                case R.id.button_div: sOperator2 = getSymbolicOperator(3); break;
                case R.id.button_mod: sOperator2 = getSymbolicOperator(4); break;
            }
            operator2_tv.setText(sOperator2);
            attempts--;
        }

        if (attempts == 0) {
            fastOperationTest(sOperator1,sOperator2);
        }


    }
}
