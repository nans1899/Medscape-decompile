package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeey extends zzejz<zzeey, zza> implements zzell {
    private static volatile zzelw<zzeey> zzel;
    /* access modifiers changed from: private */
    public static final zzeey zzhzg;
    private int zzhww;
    private int zzhzf;

    private zzeey() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeey, zza> implements zzell {
        private zza() {
            super(zzeey.zzhzg);
        }

        /* synthetic */ zza(zzeez zzeez) {
            this();
        }
    }

    public final zzeer zzbbq() {
        zzeer zzfc = zzeer.zzfc(this.zzhzf);
        return zzfc == null ? zzeer.UNRECOGNIZED : zzfc;
    }

    public final int zzazi() {
        return this.zzhww;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeez.zzdv[i - 1]) {
            case 1:
                return new zzeey();
            case 2:
                return new zza((zzeez) null);
            case 3:
                return zza((zzelj) zzhzg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzhzf", "zzhww"});
            case 4:
                return zzhzg;
            case 5:
                zzelw<zzeey> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeey.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhzg);
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

    public static zzeey zzbbr() {
        return zzhzg;
    }

    static {
        zzeey zzeey = new zzeey();
        zzhzg = zzeey;
        zzejz.zza(zzeey.class, zzeey);
    }
}
