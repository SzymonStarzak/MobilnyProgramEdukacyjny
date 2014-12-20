package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-07.
 */
public class EnglishWords extends SugarRecord<EnglishWords> {

    String word;

    String correct;

    String fake1;

    String fake2;

    String fake3;

    String example;

    public EnglishWords() {
    }

    public EnglishWords(String word, String correct, String fake1, String fake2, String fake3, String example) {
        this.word = word;
        this.correct = correct;
        this.fake1 = fake1;
        this.fake2 = fake2;
        this.fake3 = fake3;
        this.example = example;
    }

}
