package com.bea.xml.stream.util;

class Symbol {
    int depth;
    String name;
    String value;

    Symbol(String str, String str2, int i) {
        this.name = str;
        this.value = str2;
        this.depth = i;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int getDepth() {
        return this.depth;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.depth);
        stringBuffer.append("][");
        stringBuffer.append(this.name);
        stringBuffer.append("][");
        stringBuffer.append(this.value);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
