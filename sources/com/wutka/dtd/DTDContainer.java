package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public abstract class DTDContainer extends DTDItem {
    protected Vector items = new Vector();

    public abstract void write(PrintWriter printWriter) throws IOException;

    public void add(DTDItem dTDItem) {
        this.items.addElement(dTDItem);
    }

    public void remove(DTDItem dTDItem) {
        this.items.removeElement(dTDItem);
    }

    public Vector getItemsVec() {
        return this.items;
    }

    public DTDItem[] getItems() {
        DTDItem[] dTDItemArr = new DTDItem[this.items.size()];
        this.items.copyInto(dTDItemArr);
        return dTDItemArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof DTDContainer) && super.equals(obj)) {
            return this.items.equals(((DTDContainer) obj).items);
        }
        return false;
    }

    public void setItem(DTDItem[] dTDItemArr) {
        this.items = new Vector(dTDItemArr.length);
        for (DTDItem addElement : dTDItemArr) {
            this.items.addElement(addElement);
        }
    }

    public DTDItem[] getItem() {
        DTDItem[] dTDItemArr = new DTDItem[this.items.size()];
        this.items.copyInto(dTDItemArr);
        return dTDItemArr;
    }

    public void setItem(DTDItem dTDItem, int i) {
        this.items.setElementAt(dTDItem, i);
    }

    public DTDItem getItem(int i) {
        return (DTDItem) this.items.elementAt(i);
    }
}
