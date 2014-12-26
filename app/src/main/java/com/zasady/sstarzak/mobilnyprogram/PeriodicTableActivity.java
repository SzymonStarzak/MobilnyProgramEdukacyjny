package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class PeriodicTableActivity extends Activity implements View.OnClickListener {

    List<Element> elements;

    Element first_element;

    TableLayout periodic_tl;

    TableRow periodic_tr;

    TableRow.LayoutParams tr_params;

    TextView element_tv;

    long randomValueForId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table);

        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        periodic_tl = (TableLayout) findViewById(R.id.periodic_table_layout);

        elements = Element.listAll(Element.class);

        tr_params = new TableRow.LayoutParams();
        //    tr_params.width = 65;
        //   tr_params.height = 50;

        tr_params.width = p.x / 18;
        tr_params.height = p.y / 14;

        int index = 0;
        for (int a = 0; a < 10 * 18; a++) {
            if (a % 18 == 0) {
                periodic_tr = new TableRow(this);
                periodic_tl.addView(periodic_tr);
            }
            GradientDrawable gd = new GradientDrawable();
            gd.setCornerRadius(15);
            gd.setStroke(1, 0xFF000000);

            Button button = new Button(this);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6.5f);
            Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "DroidSerif-Bold.ttf");
            button.setTypeface(tf);

            button.setOnClickListener(this);
            if (checkGroupAndPerioidForElement(a)) {
                button.setText(elements.get(index).symbol);
                gd.setColor(colorElement(elements.get(index)));
                button.setBackground(gd);
                index++;
            } else {
                button.setVisibility(View.INVISIBLE);
            }
            periodic_tr.addView(button, tr_params);
        }
        periodicTableGame();
    }

    public void periodicTableGame() {
        first_element = elements.get(1);
        element_tv = (TextView) findViewById(R.id.element_tv);
        randomValueForId = first_element.getId() + new Random().nextInt((int) Element.count(Element.class, null, null)-1);

        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "DroidSerif-BoldItalic.ttf");
        element_tv.setTypeface(tf);
        element_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        element_tv.setText(elements.get((int) randomValueForId).name);
    }

    public int colorElement(Element e) {
        if (e.chemical_nature.equals("metal"))
            return Color.rgb(175, 238, 238);
        else if (e.chemical_nature.equals("niemetal"))
            return Color.rgb(221, 160, 221);
        else if (e.chemical_nature.equals("gaz_szlachetny"))
            return Color.YELLOW;
        else if (e.chemical_nature.equals("metal/półmetal"))
            return Color.rgb(250, 128, 114);
        else if (e.chemical_nature.equals("niemetal/półmetal"))
            return Color.rgb(154, 205, 50);
        else if (e.chemical_nature.equals("prawdopodobnie_niemetal"))
            return Color.WHITE;

        return 0;
    }

    public boolean checkGroupAndPerioidForElement(int value) {
        if (value >= 1 && value <= 16 ||
                value >= 20 && value <= 29 ||
                value >= 38 && value <= 47 ||
                value >= 126 && value <= 144 ||
                value >= 144 && value <= 146 ||
                value >= 162 && value <= 164 ||
                value == 92 ||
                value == 110)
            return false;
        else
            return true;
    }

    public void onCorrectAnswer() {


        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                periodicTableGame();
            }
        };
        h.postDelayed(r1, 2000);
        Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
    }

    public void onIncorrectAnswer() {

        final Handler h = new Handler();
        final Runnable r1 = new Runnable() {
            @Override
            public void run() {
                periodicTableGame();
            }
        };
        h.postDelayed(r1, 2000);
        Toast.makeText(this, "Źle", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        if (b.getText().equals(elements.get((int) randomValueForId).symbol)) {
            onCorrectAnswer();
        } else {
            onIncorrectAnswer();
        }
    }
}
