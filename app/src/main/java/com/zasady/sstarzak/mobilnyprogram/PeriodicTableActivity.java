package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

public class PeriodicTableActivity extends Activity implements View.OnClickListener{

    List<Element> elements;

    TableLayout periodic_tl;

    TableRow periodic_tr;

    TableRow.LayoutParams tr_params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_table);

        periodic_tl = (TableLayout) findViewById(R.id.periodic_table_layout);

        tr_params = new TableRow.LayoutParams();
        tr_params.width = 30;
        tr_params.height =60;

        for(int a = 0; a < 7; a++) {
            periodic_tr = new TableRow(this);
            for (int b = 0; b < 18; b++) {
                Button button = new Button(this);
                periodic_tr.addView(button,tr_params);

            }
            periodic_tl.addView(periodic_tr);
        }

        elements = Element.listAll(Element.class);

    }

    @Override
    public void onClick(View view) {

    }
}
