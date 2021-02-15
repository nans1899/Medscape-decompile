package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefi extends zzejz<zzefi, zza> implements zzell {
    private static volatile zzelw<zzefi> zzel;
    /* access modifiers changed from: private */
    public static final zzefi zziae;
    private String zzhzh = "";
    private String zziaa = "";
    private int zziab;
    private boolean zziac;
    private String zziad = "";

    private zzefi() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefi, zza> implements zzell {
        private zza() {
            super(zzefi.zziae);
        }

        /* synthetic */ zza(zzefh zzefh) {
            this();
        }
    }

    public final String zzbcb() {
        return this.zziaa;
    }

    public final String zzbbt() {
        return this.zzhzh;
    }

    public final int zzbcc() {
        return this.zziab;
    }

    public final boolean zzbcd() {
        return this.zziac;
    }

    public final String zzbce() {
        return this.zziad;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefh.zzdv[i - 1]) {
            case 1:
                return new zzefi();
            case 2:
                return new zza((zzefh) null);
            case 3:
                return zza((zzelj) zziae, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zziaa", "zzhzh", "zziab", "zziac", "zziad"});
            case 4:
                return zziae;
            case 5:
                zzelw<zzefi> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefi.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zziae);
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
        zzefi zzefi = new zzefi();
        zziae = zzefi;
        zzejz.zza(zzefi.class, zzefi);
    }
}
