package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeep extends zzejz<zzeep, zza> implements zzell {
    private static volatile zzelw<zzeep> zzel;
    /* access modifiers changed from: private */
    public static final zzeep zzhyn;
    private int zzhyk;
    private int zzhyl;
    private zzeip zzhym = zzeip.zzier;

    private zzeep() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeep, zza> implements zzell {
        private zza() {
            super(zzeep.zzhyn);
        }

        /* synthetic */ zza(zzeeo zzeeo) {
            this();
        }
    }

    public final zzeeq zzbbf() {
        zzeeq zzfb = zzeeq.zzfb(this.zzhyk);
        return zzfb == null ? zzeeq.UNRECOGNIZED : zzfb;
    }

    public final zzeer zzbbg() {
        zzeer zzfc = zzeer.zzfc(this.zzhyl);
        return zzfc == null ? zzeer.UNRECOGNIZED : zzfc;
    }

    public final zzeip zzbbh() {
        return this.zzhym;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeeo.zzdv[i - 1]) {
            case 1:
                return new zzeep();
            case 2:
                return new zza((zzeeo) null);
            case 3:
                return zza((zzelj) zzhyn, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzhyk", "zzhyl", "zzhym"});
            case 4:
                return zzhyn;
            case 5:
                zzelw<zzeep> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeep.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhyn);
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

    public static zzeep zzbbi() {
        return zzhyn;
    }

    static {
        zzeep zzeep = new zzeep();
        zzhyn = zzeep;
        zzejz.zza(zzeep.class, zzeep);
    }
}
