package com.zasady.sstarzak.zasadypisowni;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HangmanActivity extends Activity implements View.OnClickListener {

    private List<PolishAlphabet> polish_letters;
    private long polish_letters_count;
    List <Button> button_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        polish_letters = PolishAlphabet.listAll(PolishAlphabet.class);
        polish_letters_count = PolishAlphabet.count(PolishAlphabet.class,null,null);button_array = new ArrayList<Button>();

        TableLayout tl_buttons = (TableLayout) findViewById(R.id.hangman_buttons);
        TableRow tw_buttons = new TableRow(this);

        TableRow.LayoutParams table_params = new TableRow.LayoutParams();
        table_params.height = 55;
        table_params.width = 55;

        int iterator = 0;
        for(PolishAlphabet pa : polish_letters) {
            Button temp_butt = new Button(this);
            temp_butt.setId(++iterator);
            temp_butt.setText(pa.letter);
            button_array.add(temp_butt);
        }

        iterator = 0;
        for( Button b : button_array) {
            b.setOnClickListener(this);
            tw_buttons.addView(b, table_params);
            if( iterator++ == 7) {
                iterator = 0;
                tl_buttons.addView(tw_buttons);
                tw_buttons = new TableRow(this);
            }
        }

        ImageView hangman_image = (ImageView) findViewById(R.id.hangman_image);
        hangman_image.setImageResource(R.drawable.hangman1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hangman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        Toast.makeText(this,"work",Toast.LENGTH_SHORT).show();
    }
}
