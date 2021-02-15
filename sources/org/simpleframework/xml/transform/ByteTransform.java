package org.simpleframework.xml.transform;

class ByteTransform implements Transform<Byte> {
    ByteTransform() {
    }

    public Byte read(String str) {
        return Byte.valueOf(str);
    }

    public String write(Byte b) {
        return b.toString();
    }
}
