package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;


/**
 * Created by sstarzak on 2014-11-23.
 */

public class OrthographyRule extends SugarRecord <OrthographyRule> {

    String principle;

    OrthographyAlphabet orthographyAlphabet;

    public OrthographyRule() {
    }

    public OrthographyRule(String principle, OrthographyAlphabet orthographyAlphabet) {
        this.principle = principle;
        this.orthographyAlphabet = orthographyAlphabet;
    }
}
