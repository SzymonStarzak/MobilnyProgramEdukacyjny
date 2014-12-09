package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class FastOperationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_operation);
        final TextView tv = (TextView) findViewById(R.id.countdown_tv);
        CountDownTimer cdt = new CountDownTimer(30000,100) {
            @Override
            public void onTick(long l) {
                tv.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                tv.setText("Done");
            }
        }.start();
    }
}
