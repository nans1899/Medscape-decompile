package org.simpleframework.xml.transform;

class StringTransform implements Transform<String> {
    public String read(String str) {
        return str;
    }

    public String write(String str) {
        return str;
    }

    StringTransform() {
    }
}
