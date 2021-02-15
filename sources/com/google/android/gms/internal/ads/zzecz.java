package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzecz extends zzejz<zzecz, zza> implements zzell {
    private static volatile zzelw<zzecz> zzel;
    /* access modifiers changed from: private */
    public static final zzecz zzhwt;
    private int zzhwq;
    private zzeip zzhwr = zzeip.zzier;
    private zzedd zzhws;

    private zzecz() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzecz, zza> implements zzell {
        private zza() {
            super(zzecz.zzhwt);
        }

        public final zza zzes(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzecz) this.zzijh).setVersion(0);
            return this;
        }

        public final zza zzt(zzeip zzeip) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzecz) this.zzijh).zzs(zzeip);
            return this;
        }

        public final zza zzd(zzedd zzedd) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzecz) this.zzijh).zzc(zzedd);
            return this;
        }

        /* synthetic */ zza(zzecy zzecy) {
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

    public final zzedd zzaze() {
        zzedd zzedd = this.zzhws;
        return zzedd == null ? zzedd.zzazj() : zzedd;
    }

    /* access modifiers changed from: private */
    public final void zzc(zzedd zzedd) {
        zzedd.getClass();
        this.zzhws = zzedd;
    }

    public static zzecz zzc(zzeip zzeip, zzejm zzejm) throws zzekj {
        return (zzecz) zzejz.zza(zzhwt, zzeip, zzejm);
    }

    public static zza zzazf() {
        return (zza) zzhwt.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzecy.zzdv[i - 1]) {
            case 1:
                return new zzecz();
            case 2:
                return new zza((zzecy) null);
            case 3:
                return zza((zzelj) zzhwt, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zzhwq", "zzhwr", "zzhws"});
            case 4:
                return zzhwt;
            case 5:
                zzelw<zzecz> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzecz.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzhwt);
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
        zzecz zzecz = new zzecz();
        zzhwt = zzecz;
        zzejz.zza(zzecz.class, zzecz);
    }
}
