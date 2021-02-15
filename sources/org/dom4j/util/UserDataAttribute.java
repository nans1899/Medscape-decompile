package org.dom4j.util;

import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;

public class UserDataAttribute extends DefaultAttribute {
    private Object data;

    public UserDataAttribute(QName qName) {
        super(qName);
    }

    public UserDataAttribute(QName qName, String str) {
        super(qName, str);
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }
}
