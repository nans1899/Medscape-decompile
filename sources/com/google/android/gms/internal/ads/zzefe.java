package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefe extends zzejz<zzefe, zza> implements zzell {
    private static volatile zzelw<zzefe> zzel;
    /* access modifiers changed from: private */
    public static final zzefe zzhzz;
    private String zzhzh = "";
    private zzeip zzhzi = zzeip.zzier;
    private int zzhzy;

    private zzefe() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefe, zza> implements zzell {
        private zza() {
            super(zzefe.zzhzz);
        }

        /* synthetic */ zza(zzefg zzefg) {
            this();
        }
    }

    public final String zzbbt() {
        return this.zzhzh;
    }

    public final zzeip zzbbu() {
        return this.zzhzi;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefg.zzdv[i - 1]) {
            case 1:
                return new zzefe();
            case 2:
                return new zza((zzefg) null);
            case 3:
                return zza((zzelj) zzhzz, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzhzh", "zzhzi", "zzhzy"});
            case 4:
                return zzhzz;
            case 5:
                zzelw<zzefe> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefe.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhzz);
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

    public static zzefe zzbbz() {
        return zzhzz;
    }

    static {
        zzefe zzefe = new zzefe();
        zzhzz = zzefe;
        zzejz.zza(zzefe.class, zzefe);
    }
}
