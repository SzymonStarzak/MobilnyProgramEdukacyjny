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

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table);

        language = getIntent().getStringExtra("language");

        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        periodic_tl = (TableLayout) findViewById(R.id.periodic_table_layout);

        elements = Element.listAll(Element.class);

        tr_params = new TableRow.LayoutParams();

        tr_params.width = p.x / 18;
        tr_params.height = p.y / 13;

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
            button.setId(a * 0x64);
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
            colorLegend();
            periodic_tr.addView(button, tr_params);
        }
        periodicTableGame();
    }

    public void periodicTableGame() {
        first_element = elements.get(1);
        element_tv = (TextView) findViewById(R.id.element_tv);
        randomValueForId = first_element.getId() + new Random().nextInt((int) Element.count(Element.class, null, null) - 2);

        enableButtons(true);

        Typeface tf = Typeface.createFromAsset(this.getResources().getAssets(), "DroidSerif-BoldItalic.ttf");
        element_tv.setTypeface(tf);
        element_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

        if (language.equals("polish")) {
            element_tv.setText(elements.get((int) randomValueForId).name);
        } else if (language.equals("english")) {
            element_tv.setText(elements.get((int) randomValueForId).english_name);
        }
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
    public void colorLegend() {
        Button b;
        GradientDrawable gd;

        b = (Button) findViewById(R.id.button_m);
        gd = (GradientDrawable) b.getBackground();
        gd.setColor(Color.rgb(175, 238, 238));
        b.setBackground(gd);
        if (language.equals("english"))
            b.setText("metal");

        b = (Button) findViewById(R.id.button_n);
        gd = (GradientDrawable) b.getBackground();
        gd.setColor(Color.rgb(221, 160, 221));
        b.setBackground(gd);
        if (language.equals("english"))
            b.setText("non metal");


        b = (Button) findViewById(R.id.button_gs);
        gd = (GradientDrawable) b.getBackground();
        gd.setColor(Color.YELLOW);
        b.setBackground(gd);
        if (language.equals("english"))
            b.setText("gas");

        b = (Button) findViewById(R.id.button_mp);
        gd = (GradientDrawable) b.getBackground();
        gd.setColor(Color.rgb(250, 128, 114));
        b.setBackground(gd);
        if (language.equals("english"))
            b.setText("half metal/metal");

        b = (Button) findViewById(R.id.button_np);
        gd = (GradientDrawable) b.getBackground();
        gd.setColor(Color.rgb(154, 205, 50));
        b.setBackground(gd);
        if (language.equals("english"))
            b.setText("non/half metal");

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

        for (int a = 0; a < 10 * 18; a++) {
            Button b = (Button) findViewById(a * 0x64);
            if (b.getText().toString().equals(elements.get((int) randomValueForId).symbol)) {
                MyViewAnimations.myBlinkAnimation(b, 2000, 5, Color.GREEN, colorElement(findElementOnButton(b)));
                break;
            }
        }
    }

    public Element findElementOnButton(Button b) {
        for (int a = 0; a < Element.count(Element.class, null, null); a++) {
            if (b.getText().toString().equals(elements.get(a).symbol)) {
                return elements.get(a);
            }
        }
        return elements.get(1);
    }

    public void enableButtons(boolean bool) {
        for (int a = 0; a < 10 * 18; a++) {
            Button b = (Button) findViewById(a * 0x64);
            b.setEnabled(bool);
        }
    }
    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        enableButtons(false);
        if (b.getText().equals(elements.get((int) randomValueForId).symbol)) {
            onCorrectAnswer();
            MyViewAnimations.myBlinkAnimation(b, 2000, 5, Color.GREEN, colorElement(findElementOnButton(b)));
        } else {
            MyViewAnimations.myWrongAnswerShakerAnimation(view, 25, 15, Color.parseColor("#AAF52C2C"),colorElement(findElementOnButton(b)));
            onIncorrectAnswer();
        }
    }
}
