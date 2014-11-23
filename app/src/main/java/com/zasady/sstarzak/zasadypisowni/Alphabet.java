package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class Alphabet extends SugarRecord<Alphabet> {

    String pl_str;

    public Alphabet() {
    }

    public Alphabet(String pl_str) {
        this.pl_str = pl_str;
    }
}
