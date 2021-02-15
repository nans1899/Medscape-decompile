package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefu extends zzejz<zzefu, zza> implements zzell {
    private static volatile zzelw<zzefu> zzel;
    /* access modifiers changed from: private */
    public static final zzefu zziax;
    private String zziav = "";
    private zzefe zziaw;

    private zzefu() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefu, zza> implements zzell {
        private zza() {
            super(zzefu.zziax);
        }

        /* synthetic */ zza(zzeft zzeft) {
            this();
        }
    }

    public final String zzbdb() {
        return this.zziav;
    }

    public final zzefe zzbdc() {
        zzefe zzefe = this.zziaw;
        return zzefe == null ? zzefe.zzbbz() : zzefe;
    }

    public static zzefu zzw(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzefu) zzejz.zza(zziax, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeft.zzdv[i - 1]) {
            case 1:
                return new zzefu();
            case 2:
                return new zza((zzeft) null);
            case 3:
                return zza((zzelj) zziax, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zziav", "zziaw"});
            case 4:
                return zziax;
            case 5:
                zzelw<zzefu> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefu.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zziax);
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

    public static zzefu zzbdd() {
        return zziax;
    }

    static {
        zzefu zzefu = new zzefu();
        zziax = zzefu;
        zzejz.zza(zzefu.class, zzefu);
    }
}
