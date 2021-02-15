package com.webmd.wbmdcmepulse.models.parsers.articles;

import java.io.Serializable;

public class ContentValueTypePair<T> implements Serializable {
    protected String mType;
    protected T mValue;

    public ContentValueTypePair() {
    }

    public ContentValueTypePair(T t, String str) {
        this.mValue = t;
        this.mType = str;
    }

    public T getValue() {
        return this.mValue;
    }

    public void setValue(T t) {
        this.mValue = t;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }
}
