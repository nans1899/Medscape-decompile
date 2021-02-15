package com.google.android.gms.internal.vision;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzff {
    private final ConcurrentHashMap<zzfi, List<Throwable>> zzny = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zznz = new ReferenceQueue<>();

    zzff() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Reference<? extends Throwable> poll = this.zznz.poll();
        while (poll != null) {
            this.zzny.remove(poll);
            poll = this.zznz.poll();
        }
        List<Throwable> list = this.zzny.get(new zzfi(th, (ReferenceQueue<Throwable>) null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List<Throwable> putIfAbsent = this.zzny.putIfAbsent(new zzfi(th, this.zznz), vector);
        return putIfAbsent == null ? vector : putIfAbsent;
    }
}
