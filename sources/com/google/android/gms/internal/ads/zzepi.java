package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzepi<T> implements zzeph<T> {
    private static final Object zziuo = new Object();
    private volatile Object zzedi = zziuo;
    private volatile zzeph<T> zziup;

    private zzepi(zzeph<T> zzeph) {
        this.zziup = zzeph;
    }

    public final T get() {
        T t = this.zzedi;
        if (t != zziuo) {
            return t;
        }
        zzeph<T> zzeph = this.zziup;
        if (zzeph == null) {
            return this.zzedi;
        }
        T t2 = zzeph.get();
        this.zzedi = t2;
        this.zziup = null;
        return t2;
    }

    public static <P extends zzeph<T>, T> zzeph<T> zzas(P p) {
        return ((p instanceof zzepi) || (p instanceof zzeov)) ? p : new zzepi((zzeph) zzepe.checkNotNull(p));
    }
}
