package com.google.android.gms.internal.vision;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzet<K> extends zzej<K> {
    private final transient zzee<K> zzmv;
    private final transient zzef<K, ?> zznc;

    zzet(zzef<K, ?> zzef, zzee<K> zzee) {
        this.zznc = zzef;
        this.zzmv = zzee;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return true;
    }

    public final zzfa<K> zzcp() {
        return (zzfa) zzct().iterator();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzct().zza(objArr, i);
    }

    public final zzee<K> zzct() {
        return this.zzmv;
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.zznc.get(obj) != null;
    }

    public final int size() {
        return this.zznc.size();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
