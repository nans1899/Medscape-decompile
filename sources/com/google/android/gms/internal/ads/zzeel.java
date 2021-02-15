package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeel extends zzejz<zzeel, zza> implements zzell {
    private static volatile zzelw<zzeel> zzel;
    /* access modifiers changed from: private */
    public static final zzeel zzhyg;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;
    private zzeem zzhyf;

    private zzeel() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeel, zza> implements zzell {
        private zza() {
            super(zzeel.zzhyg);
        }

        public final zza zzez(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeel) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzb(zzeem zzeem) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeel) this.zzijh).zza(zzeem);
            return this;
        }

        public final zza zzaa(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeel) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzeek zzeek) {
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

    public final zzeem zzbax() {
        zzeem zzeem = this.zzhyf;
        return zzeem == null ? zzeem.zzbbd() : zzeem;
    }

    /* access modifiers changed from: private */
    public final void zza(zzeem zzeem) {
        zzeem.getClass();
        this.zzhyf = zzeem;
    }

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzeel zzp(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeel) zzejz.zza(zzhyg, zzeip, zzejm);
    }

    public static zza zzbay() {
        return (zza) zzhyg.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeek.zzdv[i - 1]) {
            case 1:
                return new zzeel();
            case 2:
                return new zza((zzeek) null);
            case 3:
                return zza((zzelj) zzhyg, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzhwq", "zzhyf", "zzhwr"});
            case 4:
                return zzhyg;
            case 5:
                zzelw<zzeel> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeel.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhyg);
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
        zzeel zzeel = new zzeel();
        zzhyg = zzeel;
        zzejz.zza(zzeel.class, zzeel);
    }
}
