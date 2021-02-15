package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeei extends zzejz<zzeei, zza> implements zzell {
    private static volatile zzelw<zzeei> zzel;
    /* access modifiers changed from: private */
    public static final zzeei zzhye;
    private zzeep zzhyb;
    private zzeed zzhyc;
    private int zzhyd;

    private zzeei() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeei, zza> implements zzell {
        private zza() {
            super(zzeei.zzhye);
        }

        /* synthetic */ zza(zzeej zzeej) {
            this();
        }
    }

    public final zzeep zzbas() {
        zzeep zzeep = this.zzhyb;
        return zzeep == null ? zzeep.zzbbi() : zzeep;
    }

    public final zzeed zzbat() {
        zzeed zzeed = this.zzhyc;
        return zzeed == null ? zzeed.zzbao() : zzeed;
    }

    public final zzeec zzbau() {
        zzeec zzey = zzeec.zzey(this.zzhyd);
        return zzey == null ? zzeec.UNRECOGNIZED : zzey;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeej.zzdv[i - 1]) {
            case 1:
                return new zzeei();
            case 2:
                return new zza((zzeej) null);
            case 3:
                return zza((zzelj) zzhye, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzhyb", "zzhyc", "zzhyd"});
            case 4:
                return zzhye;
            case 5:
                zzelw<zzeei> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeei.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhye);
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

    public static zzeei zzbav() {
        return zzhye;
    }

    static {
        zzeei zzeei = new zzeei();
        zzhye = zzeei;
        zzejz.zza(zzeei.class, zzeei);
    }
}
