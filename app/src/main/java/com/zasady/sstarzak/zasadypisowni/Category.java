package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-03.
 */
public class Category extends SugarRecord<Category> {

    String plname;

    String engname;

    public Category() {
    }

    public Category(String plname, String engname) {
        this.engname = engname;
        this.plname = plname;
    }
}
