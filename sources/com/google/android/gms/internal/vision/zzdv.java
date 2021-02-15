package com.google.android.gms.internal.vision;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdv extends AbstractSet<K> {
    private final /* synthetic */ zzdp zzmo;

    zzdv(zzdp zzdp) {
        this.zzmo = zzdp;
    }

    public final int size() {
        return this.zzmo.size();
    }

    public final boolean contains(Object obj) {
        return this.zzmo.containsKey(obj);
    }

    public final boolean remove(@NullableDecl Object obj) {
        Map zzcf = this.zzmo.zzcf();
        if (zzcf != null) {
            return zzcf.keySet().remove(obj);
        }
        return this.zzmo.zze(obj) != zzdp.zzmf;
    }

    public final Iterator<K> iterator() {
        return this.zzmo.zzcj();
    }

    public final void clear() {
        this.zzmo.clear();
    }
}
