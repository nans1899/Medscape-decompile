package com.google.android.gms.internal.icing;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzch {
    private final ConcurrentHashMap<zzck, List<Throwable>> zzeb = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzec = new ReferenceQueue<>();

    zzch() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.zzec.poll();
        while (poll != null) {
            this.zzeb.remove(poll);
            poll = this.zzec.poll();
        }
        List<Throwable> list = this.zzeb.get(new zzck(th, (ReferenceQueue<Throwable>) null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.zzeb.putIfAbsent(new zzck(th, this.zzec), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
