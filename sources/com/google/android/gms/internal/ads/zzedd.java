package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedd extends zzejz<zzedd, zza> implements zzell {
    private static volatile zzelw<zzedd> zzel;
    /* access modifiers changed from: private */
    public static final zzedd zzhwx;
    private int zzhww;

    private zzedd() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedd, zza> implements zzell {
        private zza() {
            super(zzedd.zzhwx);
        }

        /* synthetic */ zza(zzedc zzedc) {
            this();
        }
    }

    public final int zzazi() {
        return this.zzhww;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedc.zzdv[i - 1]) {
            case 1:
                return new zzedd();
            case 2:
                return new zza((zzedc) null);
            case 3:
                return zza((zzelj) zzhwx, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzhww"});
            case 4:
                return zzhwx;
            case 5:
                zzelw<zzedd> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedd.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhwx);
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

    public static zzedd zzazj() {
        return zzhwx;
    }

    static {
        zzedd zzedd = new zzedd();
        zzhwx = zzedd;
        zzejz.zza(zzedd.class, zzedd);
    }
}
