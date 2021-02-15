package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzedp extends zzejz<zzedp, zza> implements zzell {
    private static volatile zzelw<zzedp> zzel;
    /* access modifiers changed from: private */
    public static final zzedp zzhxk;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;
    private zzedt zzhxj;

    private zzedp() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzedp, zza> implements zzell {
        private zza() {
            super(zzedp.zzhxk);
        }

        public final zza zzev(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedp) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzb(zzedt zzedt) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedp) this.zzijh).zza(zzedt);
            return this;
        }

        public final zza zzv(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzedp) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzedo zzedo) {
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

    public final zzedt zzbab() {
        zzedt zzedt = this.zzhxj;
        return zzedt == null ? zzedt.zzbaf() : zzedt;
    }

    /* access modifiers changed from: private */
    public final void zza(zzedt zzedt) {
        zzedt.getClass();
        this.zzhxj = zzedt;
    }

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzedp zzi(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzedp) zzejz.zza(zzhxk, zzeip, zzejm);
    }

    public static zza zzbac() {
        return (zza) zzhxk.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzedo.zzdv[i - 1]) {
            case 1:
                return new zzedp();
            case 2:
                return new zza((zzedo) null);
            case 3:
                return zza((zzelj) zzhxk, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzhwq", "zzhxj", "zzhwr"});
            case 4:
                return zzhxk;
            case 5:
                zzelw<zzedp> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzedp.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhxk);
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
        zzedp zzedp = new zzedp();
        zzhxk = zzedp;
        zzejz.zza(zzedp.class, zzedp);
    }
}
