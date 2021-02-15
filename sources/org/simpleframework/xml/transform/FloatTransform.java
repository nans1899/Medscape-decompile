package org.simpleframework.xml.transform;

class FloatTransform implements Transform<Float> {
    FloatTransform() {
    }

    public Float read(String str) {
        return Float.valueOf(str);
    }

    public String write(Float f) {
        return f.toString();
    }
}
