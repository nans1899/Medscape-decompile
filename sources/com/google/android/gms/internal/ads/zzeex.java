package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeex extends zzejz<zzeex, zza> implements zzell {
    private static volatile zzelw<zzeex> zzel;
    /* access modifiers changed from: private */
    public static final zzeex zzhze;
    private int zzhwq;
    private int zzhwu;
    private zzeey zzhzc;

    private zzeex() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeex, zza> implements zzell {
        private zza() {
            super(zzeex.zzhze);
        }

        /* synthetic */ zza(zzeew zzeew) {
            this();
        }
    }

    public final zzeey zzbbk() {
        zzeey zzeey = this.zzhzc;
        return zzeey == null ? zzeey.zzbbr() : zzeey;
    }

    public final int getKeySize() {
        return this.zzhwu;
    }

    public static zzeex zzs(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeex) zzejz.zza(zzhze, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeew.zzdv[i - 1]) {
            case 1:
                return new zzeex();
            case 2:
                return new zza((zzeew) null);
            case 3:
                return zza((zzelj) zzhze, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zzhzc", "zzhwu", "zzhwq"});
            case 4:
                return zzhze;
            case 5:
                zzelw<zzeex> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeex.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhze);
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

    public static zzeex zzbbo() {
        return zzhze;
    }

    static {
        zzeex zzeex = new zzeex();
        zzhze = zzeex;
        zzejz.zza(zzeex.class, zzeex);
    }
}
