package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class DTD implements DTDOutput {
    public Hashtable elements = new Hashtable();
    public Hashtable entities = new Hashtable();
    public Hashtable externalDTDs = new Hashtable();
    public Vector items = new Vector();
    public Hashtable notations = new Hashtable();
    public DTDElement rootElement;

    public void write(PrintWriter printWriter) throws IOException {
        Enumeration elements2 = this.items.elements();
        while (elements2.hasMoreElements()) {
            ((DTDOutput) elements2.nextElement()).write(printWriter);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DTD)) {
            return false;
        }
        return this.items.equals(((DTD) obj).items);
    }

    public void setItems(Object[] objArr) {
        this.items = new Vector(objArr.length);
        for (Object addElement : objArr) {
            this.items.addElement(addElement);
        }
    }

    public Object[] getItems() {
        return this.items.toArray();
    }

    public void setItem(Object obj, int i) {
        this.items.setElementAt(obj, i);
    }

    public Object getItem(int i) {
        return this.items.elementAt(i);
    }

    public Vector getItemsByType(Class cls) {
        Vector vector = new Vector();
        Enumeration elements2 = this.items.elements();
        while (elements2.hasMoreElements()) {
            Object nextElement = elements2.nextElement();
            if (cls.isAssignableFrom(nextElement.getClass())) {
                vector.addElement(nextElement);
            }
        }
        return vector;
    }
}
