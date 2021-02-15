package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeda extends zzejz<zzeda, zza> implements zzell {
    private static volatile zzelw<zzeda> zzel;
    /* access modifiers changed from: private */
    public static final zzeda zzhwv;
    private zzedd zzhws;
    private int zzhwu;

    private zzeda() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeda, zza> implements zzell {
        private zza() {
            super(zzeda.zzhwv);
        }

        /* synthetic */ zza(zzedb zzedb) {
            this();
        }
    }

    public final int getKeySize() {
        return this.zzhwu;
    }

    public final zzedd zzaze() {
        zzedd zzedd = this.zzhws;
        return zzedd == null ? zzedd.zzazj() : zzedd;
    }

    public static zzeda zzd(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeda) zzejz.zza(zzhwv, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedb.zzdv[i - 1]) {
            case 1:
                return new zzeda();
            case 2:
                return new zza((zzedb) null);
            case 3:
                return zza((zzelj) zzhwv, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzhwu", "zzhws"});
            case 4:
                return zzhwv;
            case 5:
                zzelw<zzeda> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeda.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhwv);
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
        zzeda zzeda = new zzeda();
        zzhwv = zzeda;
        zzejz.zza(zzeda.class, zzeda);
    }
}
