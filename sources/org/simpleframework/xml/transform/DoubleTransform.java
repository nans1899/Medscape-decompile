package org.simpleframework.xml.transform;

class DoubleTransform implements Transform<Double> {
    DoubleTransform() {
    }

    public Double read(String str) {
        return Double.valueOf(str);
    }

    public String write(Double d) {
        return d.toString();
    }
}
