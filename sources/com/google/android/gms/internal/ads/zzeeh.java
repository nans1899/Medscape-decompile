package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeeh extends zzejz<zzeeh, zza> implements zzell {
    private static volatile zzelw<zzeeh> zzel;
    /* access modifiers changed from: private */
    public static final zzeeh zzhya;
    private zzeei zzhxz;

    private zzeeh() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeeh, zza> implements zzell {
        private zza() {
            super(zzeeh.zzhya);
        }

        /* synthetic */ zza(zzeeg zzeeg) {
            this();
        }
    }

    public final zzeei zzbaq() {
        zzeei zzeei = this.zzhxz;
        return zzeei == null ? zzeei.zzbav() : zzeei;
    }

    public static zzeeh zzo(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeeh) zzejz.zza(zzhya, zzeip, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeeg.zzdv[i - 1]) {
            case 1:
                return new zzeeh();
            case 2:
                return new zza((zzeeg) null);
            case 3:
                return zza((zzelj) zzhya, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzhxz"});
            case 4:
                return zzhya;
            case 5:
                zzelw<zzeeh> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeeh.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhya);
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
        zzeeh zzeeh = new zzeeh();
        zzhya = zzeeh;
        zzejz.zza(zzeeh.class, zzeeh);
    }
}
