package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads-base@@19.4.0 */
final class zzeia {
    private final ConcurrentHashMap<zzehz, List<Throwable>> zzidy = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzidz = new ReferenceQueue<>();

    zzeia() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.zzidz.poll();
        while (poll != null) {
            this.zzidy.remove(poll);
            poll = this.zzidz.poll();
        }
        List<Throwable> list = this.zzidy.get(new zzehz(th, (ReferenceQueue<Throwable>) null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.zzidy.putIfAbsent(new zzehz(th, this.zzidz), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
