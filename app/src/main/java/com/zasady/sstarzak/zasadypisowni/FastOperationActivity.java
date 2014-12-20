package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.Random;


public class FastOperationActivity extends Activity implements View.OnClickListener{

    TextView number1_tv;

    TextView number2_tv;

    TextView number3_tv;

    TextView answer_tv;

    TextView operator1_tv;

    TextView operator2_tv;

    TextView countdown_tv;

    Button button_plus;

    Button button_minus;

    Button button_times;

    Button button_div;

    Button button_mod;

    Button button_pow;

    Button button_sqrt;

    Button button_minus_sqrt;

    Integer number1;

    Integer number2;

    Integer number3;

    Integer answer_number;

    Integer operator1;

    Integer operator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_operation);
        countdown_tv = (TextView) findViewById(R.id.countdown_tv);
        number1_tv = (TextView) findViewById(R.id.number1_tv);
        number2_tv = (TextView) findViewById(R.id.number2_tv);
        number3_tv = (TextView) findViewById(R.id.number3_tv);
        answer_tv = (TextView) findViewById(R.id.answer_number_tv);
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

        fastOperationGame();

    }
    public void fastOperationGame() {
        CountDownTimer cdt = new CountDownTimer(9000, 100) {
            @Override
            public void onTick(long l) {
                countdown_tv.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                countdown_tv.setText("0");
            }
        }.start();

        Random r = new Random();

        number1 = r.nextInt(15)+1;
        number2 = r.nextInt(15)+1;
        number3 = r.nextInt(15)+1;

        operator1 = r.nextInt(5);
        operator2 = r.nextInt(5);

        String sOperator1="";
        switch (operator1) {
            case 0: sOperator1 = "+"; break;
            case 1: sOperator1 = "-"; break;
            case 2: sOperator1 = "*"; break;
            case 3: sOperator1 = "/"; break;
            case 4: sOperator1 = "%"; break;
        }

        String sOperator2="";
        switch (operator2) {
            case 0: sOperator2 = "+"; break;
            case 1: sOperator2 = "-"; break;
            case 2: sOperator2 = "*"; break;
            case 3: sOperator2 = "/"; break;
            case 4: sOperator2 = "%"; break;
        }

        //Korekta wylosowanych liczb dla operacji dzielenia
      /*  if (operator2 == 3 && operator1 != 3) {
            while (number2 % number3 != 0){
                number2= r.nextInt(30)+1;
            }
        }
       else if (operator1 == 3 && operator2 != 3) {
           while (number1 % number2 != 0){
               number1=r.nextInt(30)+1;
           }
       }
       else if (operator1 == 3 && operator2 == 3){
            while (number1 % (number2 * number3) != 0){
                number1++;
            }
        }*/

        JexlEngine jexl = new JexlEngine();
        Expression func = jexl.createExpression("x1"+sOperator1+"x2"+sOperator2+"x3");
        MapContext mc = new MapContext();

       // answer_number =  Integer.valueOf(func.evaluate(mc).toString());
        mc.set("x1", number1);
        mc.set("x2", number2);
        mc.set("x3", number3);

        number1_tv.setText(String.valueOf(number1));
        number2_tv.setText(String.valueOf(number2));
        number3_tv.setText(String.valueOf(number3));

        operator1_tv.setText(sOperator1);
        operator2_tv.setText(sOperator2);

        answer_number = Integer.parseInt(func.evaluate(mc).toString());
        answer_tv.setText(String.valueOf(answer_number));
      //  answer_tv.setText(func.evaluate(mc).toString());

    }

    public void fastOperationTest() {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_plus: break;
            case R.id.button_minus: break;
            case R.id.button_times: break;
            case R.id.button_div: break;
        }
    }
}
