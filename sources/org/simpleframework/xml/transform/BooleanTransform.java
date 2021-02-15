package org.simpleframework.xml.transform;

class BooleanTransform implements Transform<Boolean> {
    BooleanTransform() {
    }

    public Boolean read(String str) {
        return Boolean.valueOf(str);
    }

    public String write(Boolean bool) {
        return bool.toString();
    }
}
