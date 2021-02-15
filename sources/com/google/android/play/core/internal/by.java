package com.google.android.play.core.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class by extends WeakReference<Throwable> {
    private final int a;

    public by(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        by byVar = (by) obj;
        return this.a == byVar.a && get() == byVar.get();
    }

    public final int hashCode() {
        return this.a;
    }
}
