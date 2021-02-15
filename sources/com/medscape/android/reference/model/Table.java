package com.medscape.android.reference.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {
    public Para caption;
    public int headerRowIndex = -1;
    public String id;
    public ArrayList<ArrayList<Para>> rows;
    public String tableClass;

    public Table(String str, String str2) {
        this.id = str;
        this.tableClass = str2;
    }

    public void addRow(ArrayList<Para> arrayList, boolean z) {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }
        if (z) {
            this.headerRowIndex = this.rows.size();
        }
        this.rows.add(arrayList);
    }
}
