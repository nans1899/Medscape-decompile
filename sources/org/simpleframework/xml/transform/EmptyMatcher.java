package org.simpleframework.xml.transform;

class EmptyMatcher implements Matcher {
    public Transform match(Class cls) throws Exception {
        return null;
    }

    EmptyMatcher() {
    }
}
