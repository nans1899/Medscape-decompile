package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefr extends zzejz<zzefr, zza> implements zzell {
    private static volatile zzelw<zzefr> zzel;
    /* access modifiers changed from: private */
    public static final zzefr zziau;
    private int zzhwq;
    private zzefu zziat;

    private zzefr() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefr, zza> implements zzell {
        private zza() {
            super(zzefr.zziau);
        }

        public final zza zzfl(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefr) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzb(zzefu zzefu) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefr) this.zzijh).zza(zzefu);
            return this;
        }

        /* synthetic */ zza(zzefs zzefs) {
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

    public final zzefu zzbcy() {
        zzefu zzefu = this.zziat;
        return zzefu == null ? zzefu.zzbdd() : zzefu;
    }

    /* access modifiers changed from: private */
    public final void zza(zzefu zzefu) {
        zzefu.getClass();
        this.zziat = zzefu;
    }

    public static zzefr zzv(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzefr) zzejz.zza(zziau, zzeip, zzejm);
    }

    public static zza zzbcz() {
        return (zza) zziau.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefs.zzdv[i - 1]) {
            case 1:
                return new zzefr();
            case 2:
                return new zza((zzefs) null);
            case 3:
                return zza((zzelj) zziau, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzhwq", "zziat"});
            case 4:
                return zziau;
            case 5:
                zzelw<zzefr> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefr.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zziau);
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
        zzefr zzefr = new zzefr();
        zziau = zzefr;
        zzejz.zza(zzefr.class, zzefr);
    }
}
