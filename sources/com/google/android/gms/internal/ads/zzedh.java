package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedh extends zzejz<zzedh, zza> implements zzell {
    private static volatile zzelw<zzedh> zzel;
    /* access modifiers changed from: private */
    public static final zzedh zzhxd;
    private zzedl zzhxb;
    private zzeex zzhxc;

    private zzedh() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedh, zza> implements zzell {
        private zza() {
            super(zzedh.zzhxd);
        }

        /* synthetic */ zza(zzedg zzedg) {
            this();
        }
    }

    public final zzedl zzazp() {
        zzedl zzedl = this.zzhxb;
        return zzedl == null ? zzedl.zzazw() : zzedl;
    }

    public final zzeex zzazq() {
        zzeex zzeex = this.zzhxc;
        return zzeex == null ? zzeex.zzbbo() : zzeex;
    }

    public static zzedh zzf(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedh) zzejz.zza(zzhxd, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedg.zzdv[i - 1]) {
            case 1:
                return new zzedh();
            case 2:
                return new zza((zzedg) null);
            case 3:
                return zza((zzelj) zzhxd, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzhxb", "zzhxc"});
            case 4:
                return zzhxd;
            case 5:
                zzelw<zzedh> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedh.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxd);
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
        zzedh zzedh = new zzedh();
        zzhxd = zzedh;
        zzejz.zza(zzedh.class, zzedh);
    }
}
