package org.simpleframework.xml.core;

enum MethodType {
    GET(3),
    IS(2),
    SET(3),
    NONE(0);
    
    private int prefix;

    private MethodType(int i) {
        this.prefix = i;
    }

    public int getPrefix() {
        return this.prefix;
    }
}
