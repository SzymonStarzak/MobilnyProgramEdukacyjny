package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-03.
 */
public class GameWords extends SugarRecord<GameWords> {

    String word;

    Category category;

    public GameWords() {
    }

    public GameWords(String word, Category category) {
        this.word = word;
        this.category = category;
    }
}
