package com.google.android.gms.internal.icing;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzck extends WeakReference<Throwable> {
    private final int zzef;

    public zzck(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.zzef = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final int hashCode() {
        return this.zzef;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzck zzck = (zzck) obj;
            return this.zzef == zzck.zzef && get() == zzck.get();
        }
    }
}
