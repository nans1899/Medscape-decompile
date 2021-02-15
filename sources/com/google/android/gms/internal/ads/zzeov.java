package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeov<T> implements zzeos<T>, zzeph<T> {
    private static final Object zziuo = new Object();
    private volatile Object zzedi = zziuo;
    private volatile zzeph<T> zziup;

    private zzeov(zzeph<T> zzeph) {
        this.zziup = zzeph;
    }

    public final T get() {
        T t = this.zzedi;
        if (t == zziuo) {
            synchronized (this) {
                t = this.zzedi;
                if (t == zziuo) {
                    t = this.zziup.get();
                    T t2 = this.zzedi;
                    if (t2 != zziuo && !(t2 instanceof zzepb)) {
                        if (t2 != t) {
                            String valueOf = String.valueOf(t2);
                            String valueOf2 = String.valueOf(t);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
                            sb.append("Scoped provider was invoked recursively returning different results: ");
                            sb.append(valueOf);
                            sb.append(" & ");
                            sb.append(valueOf2);
                            sb.append(". This is likely due to a circular dependency.");
                            throw new IllegalStateException(sb.toString());
                        }
                    }
                    this.zzedi = t;
                    this.zziup = null;
                }
            }
        }
        return t;
    }

    public static <P extends zzeph<T>, T> zzeph<T> zzas(P p) {
        zzepe.checkNotNull(p);
        if (p instanceof zzeov) {
            return p;
        }
        return new zzeov(p);
    }

    public static <P extends zzeph<T>, T> zzeos<T> zzat(P p) {
        if (p instanceof zzeos) {
            return (zzeos) p;
        }
        return new zzeov((zzeph) zzepe.checkNotNull(p));
    }
}
