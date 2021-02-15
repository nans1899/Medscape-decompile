package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzega extends zzejz<zzega, zza> implements zzell {
    private static volatile zzelw<zzega> zzel;
    /* access modifiers changed from: private */
    public static final zzega zzibi;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;

    private zzega() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzega, zza> implements zzell {
        private zza() {
            super(zzega.zzibi);
        }

        public final zza zzfn(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzega) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzag(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzega) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzefz zzefz) {
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

    public static zzega zzx(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzega) zzejz.zza(zzibi, zzeip, zzejm);
    }

    public static zza zzbdi() {
        return (zza) zzibi.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefz.zzdv[i - 1]) {
            case 1:
                return new zzega();
            case 2:
                return new zza((zzefz) null);
            case 3:
                return zza((zzelj) zzibi, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzhwq", "zzhwr"});
            case 4:
                return zzibi;
            case 5:
                zzelw<zzega> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzega.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzibi);
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
        zzega zzega = new zzega();
        zzibi = zzega;
        zzejz.zza(zzega.class, zzega);
    }
}
