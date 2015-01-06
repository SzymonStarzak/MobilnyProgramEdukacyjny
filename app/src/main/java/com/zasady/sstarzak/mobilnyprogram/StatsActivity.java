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
import java.util.List;


public class StatsActivity extends Activity implements AdapterView.OnItemClickListener  {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        list = (ListView) findViewById(R.id.listView_stats);
        list.setAdapter(new MyAdapterForStats(this));
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
class SingleStat {
    String name;
    String title;
    String desc;
    String proc;
    int image;

    SingleStat(String name, String win, String lost, String proc, int image) {
        this.name = name;
        this.title = win;
        this.desc = lost;
        this.proc = proc;
        this.image = image;
    }
}
class MyAdapterForStats extends BaseAdapter {
    ArrayList<SingleStat> list;
    List<Stats> stats = Stats.listAll(Stats.class);
    Context context;
    MyAdapterForStats(Context c) {
        list = new ArrayList<SingleStat>();
        Resources res = c.getResources();
        context = c;
        String [] app_names = getAppNames();
        String [] wins = getWins();
        String [] losts = getLosts();
        String [] proc = getWinProc();
        int [] images = {R.drawable.pioro,R.drawable.wisielec,R.drawable.wisielec_eng,R.drawable.angielski,
                R.drawable.math_operations, R.drawable.chemical, R.drawable.chemical_eng, R.drawable.numeral};

        for(int i = 0; i < (int) Stats.count(Stats.class,null,null); i++) {
            list.add(new SingleStat(app_names[i], "Wygranych: " + wins[i],"Przegranych: " + losts[i],"Skuteczność: " + proc[i], images[i]));
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
        View row = inflater.inflate(R.layout.singel_row_for_stats,viewGroup,false);
        TextView app_names = (TextView) row.findViewById(R.id.app_name_tv);
        TextView win = (TextView) row.findViewById(R.id.wins_tv);
        TextView lost = (TextView) row.findViewById(R.id.losts_tv);
        TextView proc = (TextView) row.findViewById(R.id.procentage_tv);

        ImageView image = (ImageView) row.findViewById(R.id.imageView);

        SingleStat temp = list.get(i);
        app_names.setText(temp.name);
        win.setText(temp.title);
        lost.setText(temp.desc);
        proc.setText(temp.proc);
        image.setImageResource(temp.image);

        return row;
    }

    public String [] getAppNames(){
        Resources res = context.getResources();
        String [] temp1 = res.getStringArray(R.array.titles_language);
        String [] temp2 = res.getStringArray(R.array.titles_science);
        String [] names = new String[temp1.length + temp2.length];
        for(int a = 0; a < temp1.length + temp2.length; a++){
            if(a<temp1.length) {
                names[a] = temp1[a];
            }
            else {
                names[a] = temp2[a-temp1.length];
            }
        }
        return names;
    }

    public String [] getWins(){
        String [] wins = new String[(int) Stats.count(Stats.class,null,null)];
        int i =0;
        for(Stats s : stats) {
            wins[i++] = String.valueOf(s.win);
        }
        return wins;
    }
    public String [] getLosts(){
        String [] losts = new String[(int) Stats.count(Stats.class,null,null)];
        int i =0;
        for(Stats s : stats) {
            losts[i++] = String.valueOf(s.lose);
        }
        return losts;
    }

    public String [] getWinProc() {
        String [] proc = new String[(int) Stats.count(Stats.class,null,null)];
        int i =0;
        for(Stats s : stats) {
            if(s.win != 0) {
                proc[i++] = String.valueOf(Math.round( ((double) s.win / (double)(s.lose + s.win))*100) ) + "%";
            }
            else{
                proc[i++] = "0%";
            }
        }
        return proc;
    }
}
