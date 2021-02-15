package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeow<T> implements zzeoy<T> {
    private zzeph<T> zziuq;

    public final T get() {
        zzeph<T> zzeph = this.zziuq;
        if (zzeph != null) {
            return zzeph.get();
        }
        throw new IllegalStateException();
    }

    public static <T> void zzbk(zzeph<T> zzeph, zzeph<T> zzeph2) {
        zzepe.checkNotNull(zzeph2);
        zzeow zzeow = (zzeow) zzeph;
        if (zzeow.zziuq == null) {
            zzeow.zziuq = zzeph2;
            return;
        }
        throw new IllegalStateException();
    }
}
