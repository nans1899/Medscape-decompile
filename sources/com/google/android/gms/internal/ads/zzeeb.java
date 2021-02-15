package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeeb extends zzejz<zzeeb, zza> implements zzell {
    private static volatile zzelw<zzeeb> zzel;
    /* access modifiers changed from: private */
    public static final zzeeb zzhxq;

    private zzeeb() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeeb, zza> implements zzell {
        private zza() {
            super(zzeeb.zzhxq);
        }

        /* synthetic */ zza(zzeea zzeea) {
            this();
        }
    }

    public static zzeeb zzn(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeeb) zzejz.zza(zzhxq, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeea.zzdv[i - 1]) {
            case 1:
                return new zzeeb();
            case 2:
                return new zza((zzeea) null);
            case 3:
                return zza((zzelj) zzhxq, "\u0000\u0000", (Object[]) null);
            case 4:
                return zzhxq;
            case 5:
                zzelw<zzeeb> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeeb.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxq);
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

    static {
        zzeeb zzeeb = new zzeeb();
        zzhxq = zzeeb;
        zzejz.zza(zzeeb.class, zzeeb);
    }
}
