package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-03.
 */
public class HangmanCategory extends SugarRecord<HangmanCategory> {

    String plname;

    String engname;

    public HangmanCategory() {
    }

    public HangmanCategory(String plname, String engname) {
        this.engname = engname;
        this.plname = plname;
    }
}
