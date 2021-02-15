package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzby {

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz<zza, C0008zza> implements zzell {
        private static volatile zzelw<zza> zzel;
        /* access modifiers changed from: private */
        public static final zza zzex;
        private int zzdw;
        private zzb zzev;
        private zzc zzew;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.ads.zzby$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class C0008zza extends zzejz.zzb<zza, C0008zza> implements zzell {
            private C0008zza() {
                super(zza.zzex);
            }

            /* synthetic */ C0008zza(zzca zzca) {
                this();
            }
        }

        public final boolean zzx() {
            return (this.zzdw & 1) != 0;
        }

        public final zzb zzy() {
            zzb zzb = this.zzev;
            return zzb == null ? zzb.zzad() : zzb;
        }

        public final boolean zzz() {
            return (this.zzdw & 2) != 0;
        }

        public final zzc zzaa() {
            zzc zzc = this.zzew;
            return zzc == null ? zzc.zzag() : zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzca.zzdv[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0008zza((zzca) null);
                case 3:
                    return zza((zzelj) zzex, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzdw", "zzev", "zzew"});
                case 4:
                    return zzex;
                case 5:
                    zzelw<zza> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zza.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzex);
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
            zza zza = new zza();
            zzex = zza;
            zzejz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzb extends zzejz<zzb, zza> implements zzell {
        private static volatile zzelw<zzb> zzel;
        /* access modifiers changed from: private */
        public static final zzb zzez;
        private int zzdw;
        private int zzey = 2;

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzb, zza> implements zzell {
            private zza() {
                super(zzb.zzez);
            }

            /* synthetic */ zza(zzca zzca) {
                this();
            }
        }

        public final zzcb zzac() {
            zzcb zzj = zzcb.zzj(this.zzey);
            return zzj == null ? zzcb.ENUM_SIGNAL_SOURCE_ADSHIELD : zzj;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzca.zzdv[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzca) null);
                case 3:
                    return zza((zzelj) zzez, "\u0001\u0001\u0000\u0001\u001b\u001b\u0001\u0000\u0000\u0000\u001bဌ\u0000", new Object[]{"zzdw", "zzey", zzcb.zzw()});
                case 4:
                    return zzez;
                case 5:
                    zzelw<zzb> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzb.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzez);
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

        public static zzb zzad() {
            return zzez;
        }

        static {
            zzb zzb = new zzb();
            zzez = zzb;
            zzejz.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzc extends zzejz<zzc, zza> implements zzell {
        private static volatile zzelw<zzc> zzel;
        /* access modifiers changed from: private */
        public static final zzc zzfm;
        private int zzdw;
        private String zzfg = "";
        private String zzfh = "";
        private String zzfi = "";
        private String zzfj = "";
        private String zzfk = "";
        private String zzfl = "";

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzc, zza> implements zzell {
            private zza() {
                super(zzc.zzfm);
            }

            /* synthetic */ zza(zzca zzca) {
                this();
            }
        }

        public final String zzaf() {
            return this.zzfg;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzca.zzdv[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzca) null);
                case 3:
                    return zza((zzelj) zzfm, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005", new Object[]{"zzdw", "zzfg", "zzfh", "zzfi", "zzfj", "zzfk", "zzfl"});
                case 4:
                    return zzfm;
                case 5:
                    zzelw<zzc> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzc.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzfm);
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

        public static zzc zzag() {
            return zzfm;
        }

        static {
            zzc zzc = new zzc();
            zzfm = zzc;
            zzejz.zza(zzc.class, zzc);
        }
    }
}
