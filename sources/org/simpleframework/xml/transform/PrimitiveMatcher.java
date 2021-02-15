package org.simpleframework.xml.transform;

class PrimitiveMatcher implements Matcher {
    public Transform match(Class cls) throws Exception {
        if (cls == Integer.TYPE) {
            return new IntegerTransform();
        }
        if (cls == Boolean.TYPE) {
            return new BooleanTransform();
        }
        if (cls == Long.TYPE) {
            return new LongTransform();
        }
        if (cls == Double.TYPE) {
            return new DoubleTransform();
        }
        if (cls == Float.TYPE) {
            return new FloatTransform();
        }
        if (cls == Short.TYPE) {
            return new ShortTransform();
        }
        if (cls == Byte.TYPE) {
            return new ByteTransform();
        }
        if (cls == Character.TYPE) {
            return new CharacterTransform();
        }
        return null;
    }
}
