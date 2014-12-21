package com.zasady.sstarzak.mobilnyprogram;

import com.orm.SugarRecord;

/**
 * Created by sstarzak on 2014-12-20.
 */
public class Element extends SugarRecord<Element> {

    String symbol;

    String name;

    String english_name;

    Integer iAtomic;

    Integer iGroup;

    Integer iPerioid;

    String state_of_matter;

    String chemical_nature;

    public Element() {
    }

    public Element(String symbol, String name, String english_name, Integer atomic, Integer group, Integer perioid, String state_of_matter, String chemical_nature) {
        this.symbol = symbol;
        this.name = name;
        this.english_name = english_name;
        this.iAtomic = atomic;
        this.iGroup = group;
        this.iPerioid = perioid;
        this.state_of_matter = state_of_matter;
        this.chemical_nature = chemical_nature;
    }
}
