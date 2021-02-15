package org.simpleframework.xml.transform;

class IntegerTransform implements Transform<Integer> {
    IntegerTransform() {
    }

    public Integer read(String str) {
        return Integer.valueOf(str);
    }

    public String write(Integer num) {
        return num.toString();
    }
}
