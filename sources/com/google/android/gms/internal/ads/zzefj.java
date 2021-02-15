package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefj extends zzejz<zzefj, zza> implements zzell {
    private static volatile zzelw<zzefj> zzel;
    /* access modifiers changed from: private */
    public static final zzefj zziah;
    private int zziaf;
    private zzekk<zzb> zziag = zzbgk();

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zzb extends zzejz<zzb, zza> implements zzell {
        private static volatile zzelw<zzb> zzel;
        /* access modifiers changed from: private */
        public static final zzb zzial;
        private int zzhzy;
        private zzefb zziai;
        private int zziaj;
        private int zziak;

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzb, zza> implements zzell {
            private zza() {
                super(zzb.zzial);
            }

            /* synthetic */ zza(zzefk zzefk) {
                this();
            }
        }

        public final boolean zzbck() {
            return this.zziai != null;
        }

        public final zzefb zzbcl() {
            zzefb zzefb = this.zziai;
            return zzefb == null ? zzefb.zzbbx() : zzefb;
        }

        public final zzefc zzayu() {
            zzefc zzff = zzefc.zzff(this.zziaj);
            return zzff == null ? zzefc.UNRECOGNIZED : zzff;
        }

        public final int zzbcm() {
            return this.zziak;
        }

        public final zzefv zzayv() {
            zzefv zzfm = zzefv.zzfm(this.zzhzy);
            return zzfm == null ? zzefv.UNRECOGNIZED : zzfm;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzefk.zzdv[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzefk) null);
                case 3:
                    return zza((zzelj) zzial, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zziai", "zziaj", "zziak", "zzhzy"});
                case 4:
                    return zzial;
                case 5:
                    zzelw<zzb> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzb.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzial);
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
            zzb zzb = new zzb();
            zzial = zzb;
            zzejz.zza(zzb.class, zzb);
        }
    }

    private zzefj() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz.zzb<zzefj, zza> implements zzell {
        private zza() {
            super(zzefj.zziah);
        }

        /* synthetic */ zza(zzefk zzefk) {
            this();
        }
    }

    public final int zzbcg() {
        return this.zziaf;
    }

    public final List<zzb> zzbch() {
        return this.zziag;
    }

    public final int zzbci() {
        return this.zziag.size();
    }

    public static zzefj zzc(byte[] bArr, zzejm zzejm) throws zzekj {
        return (zzefj) zzejz.zza(zziah, bArr, zzejm);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefk.zzdv[i - 1]) {
            case 1:
                return new zzefj();
            case 2:
                return new zza((zzefk) null);
            case 3:
                return zza((zzelj) zziah, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zziaf", "zziag", zzb.class});
            case 4:
                return zziah;
            case 5:
                zzelw<zzefj> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefj.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zziah);
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
        zzefj zzefj = new zzefj();
        zziah = zzefj;
        zzejz.zza(zzefj.class, zzefj);
    }
}
