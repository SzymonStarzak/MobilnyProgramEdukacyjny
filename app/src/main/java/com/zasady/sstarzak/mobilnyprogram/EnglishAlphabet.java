package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-04.
 */
public class EnglishAlphabet extends SugarRecord<EnglishAlphabet> {

    String letter;

    public EnglishAlphabet() {
    }

    public EnglishAlphabet(String letter) {
        this.letter = letter;
    }
}
