package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-03.
 */
public class PolishAlphabet extends SugarRecord<PolishAlphabet> {

    String letter;

    public PolishAlphabet() {
    }

    public PolishAlphabet(String letter) {
        this.letter = letter;
    }
}
