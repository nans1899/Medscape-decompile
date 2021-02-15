package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzgq extends zzejz<zzgq, zza> implements zzell {
    /* access modifiers changed from: private */
    public static final zzgq zzacb;
    private static volatile zzelw<zzgq> zzel;
    private zzgr zzaby;
    private zzeip zzabz = zzeip.zzier;
    private zzeip zzaca = zzeip.zzier;
    private int zzdw;

    private zzgq() {
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzgq, zza> implements zzell {
        private zza() {
            super(zzgq.zzacb);
        }

        /* synthetic */ zza(zzgp zzgp) {
            this();
        }
    }

    public final zzgr zzdc() {
        zzgr zzgr = this.zzaby;
        return zzgr == null ? zzgr.zzdm() : zzgr;
    }

    public final zzeip zzdd() {
        return this.zzabz;
    }

    public final zzeip zzde() {
        return this.zzaca;
    }

    public static zzgq zza(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzgq) zzejz.zza(zzacb, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzgp.zzdv[i - 1]) {
            case 1:
                return new zzgq();
            case 2:
                return new zza((zzgp) null);
            case 3:
                return zza((zzelj) zzacb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ည\u0001\u0003ည\u0002", new Object[]{"zzdw", "zzaby", "zzabz", "zzaca"});
            case 4:
                return zzacb;
            case 5:
                zzelw<zzgq> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzgq.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzacb);
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
        zzgq zzgq = new zzgq();
        zzacb = zzgq;
        zzejz.zza(zzgq.class, zzgq);
    }
}
