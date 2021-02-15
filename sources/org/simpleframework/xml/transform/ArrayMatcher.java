package org.simpleframework.xml.transform;

class ArrayMatcher implements Matcher {
    private final Matcher primary;

    public ArrayMatcher(Matcher matcher) {
        this.primary = matcher;
    }

    public Transform match(Class cls) throws Exception {
        Class<?> componentType = cls.getComponentType();
        if (componentType == Character.TYPE) {
            return new CharacterArrayTransform(componentType);
        }
        if (componentType == Character.class) {
            return new CharacterArrayTransform(componentType);
        }
        if (componentType == String.class) {
            return new StringArrayTransform();
        }
        return matchArray(componentType);
    }

    private Transform matchArray(Class cls) throws Exception {
        Transform match = this.primary.match(cls);
        if (match == null) {
            return null;
        }
        return new ArrayTransform(match, cls);
    }
}
