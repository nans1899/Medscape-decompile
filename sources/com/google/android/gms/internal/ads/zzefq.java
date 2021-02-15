package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefq extends zzejz<zzefq, zza> implements zzell {
    private static volatile zzelw<zzefq> zzel;
    /* access modifiers changed from: private */
    public static final zzefq zzias;
    private String zziar = "";

    private zzefq() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefq, zza> implements zzell {
        private zza() {
            super(zzefq.zzias);
        }

        /* synthetic */ zza(zzefp zzefp) {
            this();
        }
    }

    public final String zzbcv() {
        return this.zziar;
    }

    public static zzefq zzu(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzefq) zzejz.zza(zzias, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefp.zzdv[i - 1]) {
            case 1:
                return new zzefq();
            case 2:
                return new zza((zzefp) null);
            case 3:
                return zza((zzelj) zzias, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zziar"});
            case 4:
                return zzias;
            case 5:
                zzelw<zzefq> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefq.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzias);
                            zzel = zzelw;
                        }
                    }
                }
                return zzelw;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzefq zzbcw() {
        return zzias;
    }

    static {
        zzefq zzefq = new zzefq();
        zzias = zzefq;
        zzejz.zza(zzefq.class, zzefq);
    }
}
