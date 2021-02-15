package org.simpleframework.xml.transform;

class LongTransform implements Transform<Long> {
    LongTransform() {
    }

    public Long read(String str) {
        return Long.valueOf(str);
    }

    public String write(Long l) {
        return l.toString();
    }
}
