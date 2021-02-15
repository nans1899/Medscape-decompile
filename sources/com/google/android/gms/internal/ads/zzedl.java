package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedl extends zzejz<zzedl, zza> implements zzell {
    private static volatile zzelw<zzedl> zzel;
    /* access modifiers changed from: private */
    public static final zzedl zzhxg;
    private int zzhwu;
    private zzedm zzhxe;

    private zzedl() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedl, zza> implements zzell {
        private zza() {
            super(zzedl.zzhxg);
        }

        /* synthetic */ zza(zzedk zzedk) {
            this();
        }
    }

    public final zzedm zzazs() {
        zzedm zzedm = this.zzhxe;
        return zzedm == null ? zzedm.zzazz() : zzedm;
    }

    public final int getKeySize() {
        return this.zzhwu;
    }

    public static zzedl zzh(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedl) zzejz.zza(zzhxg, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedk.zzdv[i - 1]) {
            case 1:
                return new zzedl();
            case 2:
                return new zza((zzedk) null);
            case 3:
                return zza((zzelj) zzhxg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzhxe", "zzhwu"});
            case 4:
                return zzhxg;
            case 5:
                zzelw<zzedl> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedl.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxg);
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

    public static zzedl zzazw() {
        return zzhxg;
    }

    static {
        zzedl zzedl = new zzedl();
        zzhxg = zzedl;
        zzejz.zza(zzedl.class, zzedl);
    }
}
