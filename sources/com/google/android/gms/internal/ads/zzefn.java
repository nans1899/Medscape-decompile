package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefn extends zzejz<zzefn, zza> implements zzell {
    private static volatile zzelw<zzefn> zzel;
    /* access modifiers changed from: private */
    public static final zzefn zziaq;
    private int zzhwq;
    private zzefq zziap;

    private zzefn() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefn, zza> implements zzell {
        private zza() {
            super(zzefn.zziaq);
        }

        public final zza zzfk(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefn) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzb(zzefq zzefq) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefn) this.zzijh).zza(zzefq);
            return this;
        }

        /* synthetic */ zza(zzefo zzefo) {
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

    public final zzefq zzbcs() {
        zzefq zzefq = this.zziap;
        return zzefq == null ? zzefq.zzbcw() : zzefq;
    }

    /* access modifiers changed from: private */
    public final void zza(zzefq zzefq) {
        zzefq.getClass();
        this.zziap = zzefq;
    }

    public static zzefn zzt(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzefn) zzejz.zza(zziaq, zzeip, zzejm);
    }

    public static zza zzbct() {
        return (zza) zziaq.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefo.zzdv[i - 1]) {
            case 1:
                return new zzefn();
            case 2:
                return new zza((zzefo) null);
            case 3:
                return zza((zzelj) zziaq, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzhwq", "zziap"});
            case 4:
                return zziaq;
            case 5:
                zzelw<zzefn> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefn.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zziaq);
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
        zzefn zzefn = new zzefn();
        zziaq = zzefn;
        zzejz.zza(zzefn.class, zzefn);
    }
}
