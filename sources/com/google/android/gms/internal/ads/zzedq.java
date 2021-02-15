package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedq extends zzejz<zzedq, zza> implements zzell {
    private static volatile zzelw<zzedq> zzel;
    /* access modifiers changed from: private */
    public static final zzedq zzhxl;
    private int zzhwu;
    private zzedt zzhxj;

    private zzedq() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedq, zza> implements zzell {
        private zza() {
            super(zzedq.zzhxl);
        }

        /* synthetic */ zza(zzedr zzedr) {
            this();
        }
    }

    public final zzedt zzbab() {
        zzedt zzedt = this.zzhxj;
        return zzedt == null ? zzedt.zzbaf() : zzedt;
    }

    public final int getKeySize() {
        return this.zzhwu;
    }

    public static zzedq zzj(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedq) zzejz.zza(zzhxl, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedr.zzdv[i - 1]) {
            case 1:
                return new zzedq();
            case 2:
                return new zza((zzedr) null);
            case 3:
                return zza((zzelj) zzhxl, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzhxj", "zzhwu"});
            case 4:
                return zzhxl;
            case 5:
                zzelw<zzedq> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedq.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxl);
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
        zzedq zzedq = new zzedq();
        zzhxl = zzedq;
        zzejz.zza(zzedq.class, zzedq);
    }
}
