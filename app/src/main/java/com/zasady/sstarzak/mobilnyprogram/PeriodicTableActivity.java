package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

        periodic_tl = (TableLayout) findViewById(R.id.periodic_table_layout);

        elements = Element.listAll(Element.class);

        tr_params = new TableRow.LayoutParams();
        tr_params.width = 65;
        tr_params.height = 50;

        int index = 0;
        for (int a = 0; a < 9 * 18; a++) {
            if (a % 18 == 0) {
                periodic_tr = new TableRow(this);
                periodic_tl.addView(periodic_tr);
            }
            Button button = new Button(this);
            button.setTextSize(4.7f);
            button.setOnClickListener(this);
            if (checkGroupAndPerioidForElement(a)) {
                button.setText(elements.get(index).symbol);
                button.getBackground().setColorFilter(colorElement(elements.get(index)), PorterDuff.Mode.MULTIPLY);
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
        randomValueForId = first_element.getId() + new Random().nextInt((int) Element.count(Element.class, null, null));
          element_tv.setText(elements.get((int) randomValueForId).name);
    }

    public int colorElement(Element e) {
        if (e.chemical_nature.equals("metal"))
            return Color.BLUE;
        else if (e.chemical_nature.equals("niemetal"))
            return Color.MAGENTA;
        else if (e.chemical_nature.equals("gaz_szlachetny"))
            return Color.YELLOW;
        else if (e.chemical_nature.equals("metal/półmetal"))
            return Color.RED;
        else if (e.chemical_nature.equals("niemetal/półmetal"))
            return Color.GREEN;
        else if (e.chemical_nature.equals("prawdopodobnie_niemetal"))
            return Color.WHITE;

        return 0;
    }

    public boolean checkGroupAndPerioidForElement(int value) {
        if (value >= 1 && value <= 16 ||
                value >= 20 && value <= 29 ||
                value >= 38 && value <= 47 ||
                value >= 126 && value <= 128 ||
                value >= 144 && value <= 146 ||
                value == 92 ||
                value == 110)
            return false;
        else
            return true;
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        if ( b.getText().equals(elements.get((int) randomValueForId).symbol))
         Toast.makeText(this, "Dobrze", Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this, "Źle", Toast.LENGTH_SHORT).show();
        }
        periodicTableGame();
    }
}
