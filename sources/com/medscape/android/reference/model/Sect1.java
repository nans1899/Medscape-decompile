package com.medscape.android.reference.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sect1 implements Serializable {
    public String id;
    public ArrayList<Sect2> subsections;
    public String title;
    public String type;

    public Sect1 addSubsection(Sect2 sect2) {
        if (this.subsections == null) {
            this.subsections = new ArrayList<>();
        }
        this.subsections.add(sect2);
        return this;
    }
}
