package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.List;

public class PeriodicTableActivity extends Activity implements View.OnClickListener {

    List<Element> elements;

    Element first_element;

    TableLayout periodic_tl;

    TableRow periodic_tr;

    TableRow.LayoutParams tr_params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table);

        periodic_tl = (TableLayout) findViewById(R.id.periodic_table_layout);

        elements = Element.listAll(Element.class);

        tr_params = new TableRow.LayoutParams();
        tr_params.width = 50;
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
              //  button.setText(elements.get(index).symbol);
                index++;
            }
            else {
                button.setVisibility(View.INVISIBLE);
            }

            periodic_tr.addView(button, tr_params);

        }
    }

    public boolean checkGroupAndPerioidForElement(int value) {
        if (value >= 1 && value <= 16 ||
                value >= 20 && value <= 29 ||
                value >= 38 && value <= 47 ||
                value >= 126 && value <= 127 ||
                value >= 144 && value <= 145 ||
                value == 92 ||
                value == 110)
            return false;
        else
            return true;
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        Toast.makeText(this,b.getText(),Toast.LENGTH_SHORT).show();
    }
}
