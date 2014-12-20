package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-04.
 */
public class HangmanWordsEng extends SugarRecord<HangmanWordsEng> {

    String word;

    HangmanCategory hangmanCategory;

    public HangmanWordsEng() {
    }

    public HangmanWordsEng(String word, HangmanCategory hangmanCategory) {
        this.word = word;
        this.hangmanCategory = hangmanCategory;
    }
}
