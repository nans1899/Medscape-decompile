package com.medscape.android.drugs.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class DrugMonograph implements Serializable {
    private static final long serialVersionUID = 7673521677921047219L;
    private DrugMonographHeader header;
    private HashMap<Integer, List<DrugMonographSection>> sections;

    public DrugMonographHeader getHeader() {
        return this.header;
    }

    public void setHeader(DrugMonographHeader drugMonographHeader) {
        this.header = drugMonographHeader;
    }

    public HashMap<Integer, List<DrugMonographSection>> getSections() {
        return this.sections;
    }

    public void setSections(HashMap<Integer, List<DrugMonographSection>> hashMap) {
        this.sections = hashMap;
    }
}
