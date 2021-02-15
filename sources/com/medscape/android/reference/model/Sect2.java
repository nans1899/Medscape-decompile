package com.medscape.android.reference.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sect2 implements Serializable {
    public String id;
    public ArrayList<Para> paras;
    public String title;
    public String type;

    public Sect2 addPara(Para para) {
        if (this.paras == null) {
            this.paras = new ArrayList<>();
        }
        this.paras.add(para);
        return this;
    }
}
