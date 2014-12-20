package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-03.
 */
public class HangmanWords extends SugarRecord<HangmanWords> {

    String word;

    HangmanCategory hangmanCategory;

    public HangmanWords() {
    }

    public HangmanWords(String word, HangmanCategory hangmanCategory) {
        this.word = word;
        this.hangmanCategory = hangmanCategory;
    }
}
