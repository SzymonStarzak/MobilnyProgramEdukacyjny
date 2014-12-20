package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class OrthographyWord extends SugarRecord<OrthographyWord> {

    String full_word;

    String part_word;

    OrthographyAlphabet orthographyAlphabet;


    public OrthographyWord() {
    }

    public OrthographyWord(String full_word, String part_word, OrthographyAlphabet orthographyAlphabet) {
        this.full_word = full_word;
        this.part_word = part_word;
        this.orthographyAlphabet = orthographyAlphabet;
    }
}
