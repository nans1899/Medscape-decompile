package org.simpleframework.xml.transform;

class ShortTransform implements Transform<Short> {
    ShortTransform() {
    }

    public Short read(String str) {
        return Short.valueOf(str);
    }

    public String write(Short sh) {
        return sh.toString();
    }
}
