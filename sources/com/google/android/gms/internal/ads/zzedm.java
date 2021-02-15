package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedm extends zzejz<zzedm, zza> implements zzell {
    private static volatile zzelw<zzedm> zzel;
    /* access modifiers changed from: private */
    public static final zzedm zzhxi;
    private int zzhxh;

    private zzedm() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedm, zza> implements zzell {
        private zza() {
            super(zzedm.zzhxi);
        }

        /* synthetic */ zza(zzedn zzedn) {
            this();
        }
    }

    public final int zzazy() {
        return this.zzhxh;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedn.zzdv[i - 1]) {
            case 1:
                return new zzedm();
            case 2:
                return new zza((zzedn) null);
            case 3:
                return zza((zzelj) zzhxi, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzhxh"});
            case 4:
                return zzhxi;
            case 5:
                zzelw<zzedm> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedm.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxi);
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

    public static zzedm zzazz() {
        return zzhxi;
    }

    static {
        zzedm zzedm = new zzedm();
        zzhxi = zzedm;
        zzejz.zza(zzedm.class, zzedm);
    }
}
