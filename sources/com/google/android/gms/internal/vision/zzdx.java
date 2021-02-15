package com.google.android.gms.internal.vision;

import java.util.AbstractCollection;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdx extends AbstractCollection<V> {
    private final /* synthetic */ zzdp zzmo;

    zzdx(zzdp zzdp) {
        this.zzmo = zzdp;
    }

    public final int size() {
        return this.zzmo.size();
    }

    public final void clear() {
        this.zzmo.clear();
    }

    public final Iterator<V> iterator() {
        return this.zzmo.zzcl();
    }
}
