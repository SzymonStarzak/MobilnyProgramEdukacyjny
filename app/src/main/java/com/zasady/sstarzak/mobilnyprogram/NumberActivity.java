package com.zasady.sstarzak.mobilnyprogram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NumberActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        list = (ListView) findViewById(R.id.listView_science);
        list.setAdapter(new MyAdapter2(this));
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                Intent intent = new Intent(getApplicationContext(),FastOperationActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(),PeriodicTableActivity.class);
                intent.putExtra("language","polish");
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getApplicationContext(),PeriodicTableActivity.class);
                intent.putExtra("language","english");
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getApplicationContext(),NumeralSystemActivity.class);
                startActivity(intent);
                break;
        }
    }
}
class SingleRow2 {
    String title;
    String desc;
    int image;

    SingleRow2(String title, String desc, int image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
}
class MyAdapter2 extends BaseAdapter {
    ArrayList<SingleRow2> list;
    Context context;
    MyAdapter2(Context c) {
        list = new ArrayList<SingleRow2>();
        Resources res = c.getResources();
        context = c;
        String [] titles = res.getStringArray(R.array.titles_science);
        String [] descs = res.getStringArray(R.array.descs_science);
        int [] images = {R.drawable.math_operations,R.drawable.chemical,R.drawable.chemical_eng,R.drawable.numeral};

        for(int i = 0; i < 4; i++) {
            list.add(new SingleRow2(titles[i],descs[i],images[i]));
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.singel_row,viewGroup,false);
        TextView title = (TextView) row.findViewById(R.id.textView);
        TextView desc = (TextView) row.findViewById(R.id.textView2);
        ImageView image = (ImageView) row.findViewById(R.id.imageView);

        SingleRow2   temp = list.get(i);
        title.setText(temp.title);
        desc.setText(temp.desc);
        image.setImageResource(temp.image);

        return row;
    }
}