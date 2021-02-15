package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegb extends zzejz<zzegb, zza> implements zzell {
    private static volatile zzelw<zzegb> zzel;
    /* access modifiers changed from: private */
    public static final zzegb zzibj;

    private zzegb() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzegb, zza> implements zzell {
        private zza() {
            super(zzegb.zzibj);
        }

        /* synthetic */ zza(zzegc zzegc) {
            this();
        }
    }

    public static zzegb zzy(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzegb) zzejz.zza(zzibj, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzegc.zzdv[i - 1]) {
            case 1:
                return new zzegb();
            case 2:
                return new zza((zzegc) null);
            case 3:
                return zza((zzelj) zzibj, "\u0000\u0000", (Object[]) null);
            case 4:
                return zzibj;
            case 5:
                zzelw<zzegb> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzegb.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzibj);
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
        zzegb zzegb = new zzegb();
        zzibj = zzegb;
        zzejz.zza(zzegb.class, zzegb);
    }
}
