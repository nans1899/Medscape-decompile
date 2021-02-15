package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzhm {

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zza extends zzdx<zza, C0034zza> implements zzfj {
        private static volatile zzfr<zza> zzbc;
        /* access modifiers changed from: private */
        public static final zza zzrs;
        private int zzbd;
        private String zzqs = "";
        private zzc zzrr;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.icing.zzhm$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class C0034zza extends zzdx.zzb<zza, C0034zza> implements zzfj {
            private C0034zza() {
                super(zza.zzrs);
            }

            public final C0034zza zzu(String str) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zza) this.zzkh).setName(str);
                return this;
            }

            public final C0034zza zzb(zzc zzc) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zza) this.zzkh).zza(zzc);
                return this;
            }

            /* synthetic */ C0034zza(zzho zzho) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void setName(String str) {
            str.getClass();
            this.zzbd |= 1;
            this.zzqs = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zzc zzc) {
            zzc.getClass();
            this.zzrr = zzc;
            this.zzbd |= 2;
        }

        public static C0034zza zzec() {
            return (C0034zza) zzrs.zzbk();
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhm$zza>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zza> zzfr;
            switch (zzho.zzaz[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0034zza((zzho) null);
                case 3:
                    return zza((zzfh) zzrs, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\t\u0001", new Object[]{"zzbd", "zzqs", "zzrr"});
                case 4:
                    return zzrs;
                case 5:
                    zzfr<zza> zzfr2 = zzbc;
                    zzfr<zza> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zza.class) {
                            zzfr<zza> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza = new zzdx.zza(zzrs);
                                zzbc = zza;
                                zzfr = zza;
                            }
                        }
                        zzfr3 = zzfr;
                    }
                    return zzfr3;
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
            zzrs = zza;
            zzdx.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zzb extends zzdx<zzb, zza> implements zzfj {
        private static volatile zzfr<zzb> zzbc;
        /* access modifiers changed from: private */
        public static final zzb zzru;
        private int zzbd;
        private String zzra = "";
        private zzee<zza> zzrt = zzbp();

        private zzb() {
        }

        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class zza extends zzdx.zzb<zzb, zza> implements zzfj {
            private zza() {
                super(zzb.zzru);
            }

            public final zza zzw(String str) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zzb) this.zzkh).zzt(str);
                return this;
            }

            public final zza zzb(zza zza) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zzb) this.zzkh).zza(zza);
                return this;
            }

            /* synthetic */ zza(zzho zzho) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzt(String str) {
            str.getClass();
            this.zzbd |= 1;
            this.zzra = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zza zza2) {
            zza2.getClass();
            if (!this.zzrt.zzah()) {
                this.zzrt = zzdx.zza(this.zzrt);
            }
            this.zzrt.add(zza2);
        }

        public static zza zzee() {
            return (zza) zzru.zzbk();
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhm$zzb>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zzb> zzfr;
            switch (zzho.zzaz[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzho) null);
                case 3:
                    return zza((zzfh) zzru, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b", new Object[]{"zzbd", "zzra", "zzrt", zza.class});
                case 4:
                    return zzru;
                case 5:
                    zzfr<zzb> zzfr2 = zzbc;
                    zzfr<zzb> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zzb.class) {
                            zzfr<zzb> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza2 = new zzdx.zza(zzru);
                                zzbc = zza2;
                                zzfr = zza2;
                            }
                        }
                        zzfr3 = zzfr;
                    }
                    return zzfr3;
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
            zzru = zzb;
            zzdx.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zzc extends zzdx<zzc, zza> implements zzfj {
        private static volatile zzfr<zzc> zzbc;
        /* access modifiers changed from: private */
        public static final zzc zzsa;
        private int zzbd;
        private boolean zzrv;
        private String zzrw = "";
        private long zzrx;
        private double zzry;
        private zzb zzrz;

        private zzc() {
        }

        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class zza extends zzdx.zzb<zzc, zza> implements zzfj {
            private zza() {
                super(zzc.zzsa);
            }

            public final zza zzj(boolean z) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zzc) this.zzkh).zzi(z);
                return this;
            }

            public final zza zzx(String str) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zzc) this.zzkh).zzv(str);
                return this;
            }

            public final zza zzb(zzb zzb) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zzc) this.zzkh).zza(zzb);
                return this;
            }

            /* synthetic */ zza(zzho zzho) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzi(boolean z) {
            this.zzbd |= 1;
            this.zzrv = z;
        }

        /* access modifiers changed from: private */
        public final void zzv(String str) {
            str.getClass();
            this.zzbd |= 2;
            this.zzrw = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zzb zzb) {
            zzb.getClass();
            this.zzrz = zzb;
            this.zzbd |= 16;
        }

        public static zza zzeg() {
            return (zza) zzsa.zzbk();
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhm$zzc>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zzc> zzfr;
            switch (zzho.zzaz[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzho) null);
                case 3:
                    return zza((zzfh) zzsa, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u0007\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0000\u0003\u0005\t\u0004", new Object[]{"zzbd", "zzrv", "zzrw", "zzrx", "zzry", "zzrz"});
                case 4:
                    return zzsa;
                case 5:
                    zzfr<zzc> zzfr2 = zzbc;
                    zzfr<zzc> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zzc.class) {
                            zzfr<zzc> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza2 = new zzdx.zza(zzsa);
                                zzbc = zza2;
                                zzfr = zza2;
                            }
                        }
                        zzfr3 = zzfr;
                    }
                    return zzfr3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzc = new zzc();
            zzsa = zzc;
            zzdx.zza(zzc.class, zzc);
        }
    }
}
