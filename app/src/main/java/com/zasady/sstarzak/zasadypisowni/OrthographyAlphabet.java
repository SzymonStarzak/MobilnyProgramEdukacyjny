package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class OrthographyAlphabet extends SugarRecord<OrthographyAlphabet> {

    String pl_str;

    public OrthographyAlphabet() {
    }

    public OrthographyAlphabet(String pl_str) {
        this.pl_str = pl_str;
    }
}
