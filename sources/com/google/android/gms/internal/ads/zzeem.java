package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeem extends zzejz<zzeem, zza> implements zzell {
    private static volatile zzelw<zzeem> zzel;
    /* access modifiers changed from: private */
    public static final zzeem zzhyj;
    private int zzhwq;
    private zzeei zzhxz;
    private zzeip zzhyh = zzeip.zzier;
    private zzeip zzhyi = zzeip.zzier;

    private zzeem() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeem, zza> implements zzell {
        private zza() {
            super(zzeem.zzhyj);
        }

        public final zza zzfa(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeem) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzc(zzeei zzeei) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeem) this.zzijh).zzb(zzeei);
            return this;
        }

        public final zza zzab(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeem) this.zzijh).zzy(zzeip);
            return this;
        }

        public final zza zzac(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeem) this.zzijh).zzz(zzeip);
            return this;
        }

        /* synthetic */ zza(zzeen zzeen) {
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

    public final zzeei zzbaq() {
        zzeei zzeei = this.zzhxz;
        return zzeei == null ? zzeei.zzbav() : zzeei;
    }

    /* access modifiers changed from: private */
    public final void zzb(zzeei zzeei) {
        zzeei.getClass();
        this.zzhxz = zzeei;
    }

    public final zzeip zzbba() {
        return this.zzhyh;
    }

    /* access modifiers changed from: private */
    public final void zzy(zzeip zzeip) {
        zzeip.getClass();
        this.zzhyh = zzeip;
    }

    public final zzeip zzbbb() {
        return this.zzhyi;
    }

    /* access modifiers changed from: private */
    public final void zzz(zzeip zzeip) {
        zzeip.getClass();
        this.zzhyi = zzeip;
    }

    public static zzeem zzq(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeem) zzejz.zza(zzhyj, zzeip, zzejm);
    }

    public static zza zzbbc() {
        return (zza) zzhyj.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeen.zzdv[i - 1]) {
            case 1:
                return new zzeem();
            case 2:
                return new zza((zzeen) null);
            case 3:
                return zza((zzelj) zzhyj, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zzhwq", "zzhxz", "zzhyh", "zzhyi"});
            case 4:
                return zzhyj;
            case 5:
                zzelw<zzeem> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeem.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhyj);
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

    public static zzeem zzbbd() {
        return zzhyj;
    }

    static {
        zzeem zzeem = new zzeem();
        zzhyj = zzeem;
        zzejz.zza(zzeem.class, zzeem);
    }
}
