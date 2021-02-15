package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeet extends zzejz<zzeet, zza> implements zzell {
    private static volatile zzelw<zzeet> zzel;
    /* access modifiers changed from: private */
    public static final zzeet zzhzd;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;
    private zzeey zzhzc;

    private zzeet() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzeet, zza> implements zzell {
        private zza() {
            super(zzeet.zzhzd);
        }

        public final zza zzfd(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeet) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzd(zzeey zzeey) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeet) this.zzijh).zzc(zzeey);
            return this;
        }

        public final zza zzad(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzeet) this.zzijh).zzs(zzeip);
            return this;
        }

        /* synthetic */ zza(zzeev zzeev) {
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

    public final zzeey zzbbk() {
        zzeey zzeey = this.zzhzc;
        return zzeey == null ? zzeey.zzbbr() : zzeey;
    }

    /* access modifiers changed from: private */
    public final void zzc(zzeey zzeey) {
        zzeey.getClass();
        this.zzhzc = zzeey;
    }

    public final zzeip zzazd() {
        return this.zzhwr;
    }

    /* access modifiers changed from: private */
    public final void zzs(zzeip zzeip) {
        zzeip.getClass();
        this.zzhwr = zzeip;
    }

    public static zzeet zzr(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzeet) zzejz.zza(zzhzd, zzeip, zzejm);
    }

    public static zza zzbbl() {
        return (zza) zzhzd.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzeev.zzdv[i - 1]) {
            case 1:
                return new zzeet();
            case 2:
                return new zza((zzeev) null);
            case 3:
                return zza((zzelj) zzhzd, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzhwq", "zzhzc", "zzhwr"});
            case 4:
                return zzhzd;
            case 5:
                zzelw<zzeet> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzeet.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhzd);
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

    public static zzeet zzbbm() {
        return zzhzd;
    }

    static {
        zzeet zzeet = new zzeet();
        zzhzd = zzeet;
        zzejz.zza(zzeet.class, zzeet);
    }
}
