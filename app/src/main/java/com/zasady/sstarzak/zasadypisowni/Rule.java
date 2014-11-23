package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class Rule extends SugarRecord <Rule> {

    String principle;

    Alphabet alphabet;

    public Rule() {
    }

    public Rule(String principle, Alphabet alphabet) {
        this.principle = principle;
        this.alphabet = alphabet;
    }
}
