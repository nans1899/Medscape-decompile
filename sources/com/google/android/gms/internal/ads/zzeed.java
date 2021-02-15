package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeed extends zzejz<zzeed, zza> implements zzell {
    private static volatile zzelw<zzeed> zzel;
    /* access modifiers changed from: private */
    public static final zzeed zzhxy;
    private zzefe zzhxx;

    private zzeed() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeed, zza> implements zzell {
        private zza() {
            super(zzeed.zzhxy);
        }

        /* synthetic */ zza(zzeef zzeef) {
            this();
        }
    }

    public final zzefe zzban() {
        zzefe zzefe = this.zzhxx;
        return zzefe == null ? zzefe.zzbbz() : zzefe;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeef.zzdv[i - 1]) {
            case 1:
                return new zzeed();
            case 2:
                return new zza((zzeef) null);
            case 3:
                return zza((zzelj) zzhxy, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzhxx"});
            case 4:
                return zzhxy;
            case 5:
                zzelw<zzeed> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeed.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxy);
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

    public static zzeed zzbao() {
        return zzhxy;
    }

    static {
        zzeed zzeed = new zzeed();
        zzhxy = zzeed;
        zzejz.zza(zzeed.class, zzeed);
    }
}
