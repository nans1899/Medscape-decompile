package org.simpleframework.xml.core;

class Revision {
    private boolean equal = true;

    public double getDefault() {
        return 1.0d;
    }

    public boolean compare(Object obj, Object obj2) {
        if (obj2 != null) {
            this.equal = obj2.equals(obj);
        } else if (obj != null) {
            this.equal = obj.equals(Double.valueOf(1.0d));
        }
        return this.equal;
    }

    public boolean isEqual() {
        return this.equal;
    }
}
