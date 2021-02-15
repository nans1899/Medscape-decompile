package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicLong;

class AtomicLongTransform implements Transform<AtomicLong> {
    AtomicLongTransform() {
    }

    public AtomicLong read(String str) {
        return new AtomicLong(Long.valueOf(str).longValue());
    }

    public String write(AtomicLong atomicLong) {
        return atomicLong.toString();
    }
}
