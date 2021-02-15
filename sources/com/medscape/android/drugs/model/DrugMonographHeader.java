package com.medscape.android.drugs.model;

import com.medscape.android.db.model.DrugClass;
import java.io.Serializable;
import java.util.List;

public class DrugMonographHeader implements Serializable {
    private static final long serialVersionUID = -6924526167207316898L;
    private String av;
    private String br;
    private List<DrugClass> classes;
    private String gc;

    public String getGc() {
        return this.gc;
    }

    public void setGc(String str) {
        this.gc = str;
    }

    public String getAv() {
        return this.av;
    }

    public void setAv(String str) {
        this.av = str;
    }

    public String getBr() {
        return this.br;
    }

    public void setBr(String str) {
        this.br = str;
    }

    public List<DrugClass> getClasses() {
        return this.classes;
    }

    public void setClasses(List<DrugClass> list) {
        this.classes = list;
    }
}
