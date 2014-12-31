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


public class LanguageActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        list = (ListView) findViewById(R.id.listView_language);
        list.setAdapter(new MyAdapter(this));
        list.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                Intent intent = new Intent(getApplicationContext(), OrthographyActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), HangmanActivity.class);
                intent.putExtra("language","polish");
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getApplicationContext(), HangmanActivity.class);
                intent.putExtra("language","english");
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), EnglishActivity.class);
                startActivity(intent);
                break;
        }
    }
}
class SingleRow {
    String title;
    String desc;
    int image;

    SingleRow(String title, String desc, int image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
}
class MyAdapter extends BaseAdapter {
    ArrayList<SingleRow> list;
    Context context;
    MyAdapter(Context c) {
        list = new ArrayList<SingleRow>();
        Resources res = c.getResources();
        context = c;
        String [] titles = res.getStringArray(R.array.titles_language);
        String [] descs = res.getStringArray(R.array.descs_language);
        int [] images = {R.drawable.pioro,R.drawable.wisielec,R.drawable.wisielec_eng,R.drawable.angielski};

        for(int i = 0; i < 4; i++) {
            list.add(new SingleRow(titles[i],descs[i],images[i]));
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

        SingleRow temp = list.get(i);
        title.setText(temp.title);
        desc.setText(temp.desc);
        image.setImageResource(temp.image);

        return row;
    }
}