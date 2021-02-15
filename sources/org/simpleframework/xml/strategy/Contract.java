package org.simpleframework.xml.strategy;

class Contract {
    private String label;
    private String length;
    private String mark;
    private String refer;

    public Contract(String str, String str2, String str3, String str4) {
        this.length = str4;
        this.label = str3;
        this.refer = str2;
        this.mark = str;
    }

    public String getLabel() {
        return this.label;
    }

    public String getReference() {
        return this.refer;
    }

    public String getIdentity() {
        return this.mark;
    }

    public String getLength() {
        return this.length;
    }
}
