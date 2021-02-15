package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTransform implements Transform<AtomicInteger> {
    AtomicIntegerTransform() {
    }

    public AtomicInteger read(String str) {
        return new AtomicInteger(Integer.valueOf(str).intValue());
    }

    public String write(AtomicInteger atomicInteger) {
        return atomicInteger.toString();
    }
}
