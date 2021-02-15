package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedu extends zzejz<zzedu, zza> implements zzell {
    private static volatile zzelw<zzedu> zzel;
    /* access modifiers changed from: private */
    public static final zzedu zzhxn;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;

    private zzedu() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedu, zza> implements zzell {
        private zza() {
            super(zzedu.zzhxn);
        }

        public final zza zzew(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedu) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzw(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedu) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzedv zzedv) {
            this();
        }
    }

    public final int getVersion() {
        return this.zzhwq;
    }

    /* access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzhwq = i;
    }

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzedu zzk(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedu) zzejz.zza(zzhxn, zzeip, zzejm);
    }

    public static zza zzbah() {
        return (zza) zzhxn.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedv.zzdv[i - 1]) {
            case 1:
                return new zzedu();
            case 2:
                return new zza((zzedv) null);
            case 3:
                return zza((zzelj) zzhxn, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzhwq", "zzhwr"});
            case 4:
                return zzhxn;
            case 5:
                zzelw<zzedu> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedu.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxn);
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
        zzedu zzedu = new zzedu();
        zzhxn = zzedu;
        zzejz.zza(zzedu.class, zzedu);
    }
}
