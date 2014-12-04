package com.zasady.sstarzak.zasadypisowni;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-11-23.
 */
public class OrthographyRuleException extends SugarRecord <OrthographyRuleException> {

    String content;

    OrthographyAlphabet orthographyAlphabet;


    public OrthographyRuleException() {
    }

    public OrthographyRuleException(String content, OrthographyAlphabet orthographyAlphabet) {
        this.content = content;
        this.orthographyAlphabet = orthographyAlphabet;
    }
}
