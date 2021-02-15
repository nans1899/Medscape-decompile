package org.simpleframework.xml.core;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

class EmptyMatcher implements Matcher {
    public Transform match(Class cls) throws Exception {
        return null;
    }

    EmptyMatcher() {
    }
}
