package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class RuleException extends SugarRecord <RuleException> {

    String content;

    Alphabet alphabet;


    public RuleException() {
    }

    public RuleException(String content, Alphabet alphabet) {
        this.content = content;
        this.alphabet = alphabet;
    }
}
