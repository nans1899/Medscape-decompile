package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedx extends zzejz<zzedx, zza> implements zzell {
    private static volatile zzelw<zzedx> zzel;
    /* access modifiers changed from: private */
    public static final zzedx zzhxo;
    private int zzhwq;
    private int zzhwu;

    private zzedx() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedx, zza> implements zzell {
        private zza() {
            super(zzedx.zzhxo);
        }

        /* synthetic */ zza(zzedw zzedw) {
            this();
        }
    }

    public final int getKeySize() {
        return this.zzhwu;
    }

    public static zzedx zzl(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedx) zzejz.zza(zzhxo, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedw.zzdv[i - 1]) {
            case 1:
                return new zzedx();
            case 2:
                return new zza((zzedw) null);
            case 3:
                return zza((zzelj) zzhxo, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzhwu", "zzhwq"});
            case 4:
                return zzhxo;
            case 5:
                zzelw<zzedx> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedx.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxo);
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
        zzedx zzedx = new zzedx();
        zzhxo = zzedx;
        zzejz.zza(zzedx.class, zzedx);
    }
}
