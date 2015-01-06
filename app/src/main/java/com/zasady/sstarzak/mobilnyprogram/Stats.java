package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2015-01-06.
 */
public class Stats extends SugarRecord<Stats> {
    String name;
    int win;
    int lose;

    public Stats() {
    }

    public Stats(String name, int win, int lose) {
        this.name = name;
        this.win = win;
        this.lose = lose;
    }
    public void addNewStats(int new_win, int new_lost) {
        this.win += new_win;
        this.lose += new_lost;
    }
}
