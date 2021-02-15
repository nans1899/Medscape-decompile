package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedt extends zzejz<zzedt, zza> implements zzell {
    private static volatile zzelw<zzedt> zzel;
    /* access modifiers changed from: private */
    public static final zzedt zzhxm;
    private int zzhxh;

    private zzedt() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedt, zza> implements zzell {
        private zza() {
            super(zzedt.zzhxm);
        }

        /* synthetic */ zza(zzeds zzeds) {
            this();
        }
    }

    public final int zzazy() {
        return this.zzhxh;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeds.zzdv[i - 1]) {
            case 1:
                return new zzedt();
            case 2:
                return new zza((zzeds) null);
            case 3:
                return zza((zzelj) zzhxm, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzhxh"});
            case 4:
                return zzhxm;
            case 5:
                zzelw<zzedt> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedt.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxm);
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

    public static zzedt zzbaf() {
        return zzhxm;
    }

    static {
        zzedt zzedt = new zzedt();
        zzhxm = zzedt;
        zzejz.zza(zzedt.class, zzedt);
    }
}
