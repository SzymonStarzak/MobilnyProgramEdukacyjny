package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class Word extends SugarRecord<Word> {

    String full_word;

    String part_word;

    Alphabet alphabet;


    public Word() {
    }

    public Word(String full_word, String part_word, Alphabet alphabet) {
        this.full_word = full_word;
        this.part_word = part_word;
        this.alphabet = alphabet;
    }
}
