package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzhj {

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zza extends zzdx<zza, C0033zza> implements zzfj {
        private static volatile zzfr<zza> zzbc;
        /* access modifiers changed from: private */
        public static final zza zzqr;
        private int zzbd;
        private boolean zzqn;
        private int zzqo;
        private String zzqp = "";
        private zzee<zzb> zzqq = zzbp();

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.icing.zzhj$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class C0033zza extends zzdx.zzb<zza, C0033zza> implements zzfj {
            private C0033zza() {
                super(zza.zzqr);
            }

            /* synthetic */ C0033zza(zzhi zzhi) {
                this();
            }
        }

        public final boolean zzdv() {
            return this.zzqn;
        }

        public final int getScore() {
            return this.zzqo;
        }

        public final String zzdw() {
            return this.zzqp;
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhj$zza>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zza> zzfr;
            switch (zzhi.zzaz[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0033zza((zzhi) null);
                case 3:
                    return zza((zzfh) zzqr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0007\u0000\u0002\u0004\u0001\u0003\b\u0002\u0004\u001b", new Object[]{"zzbd", "zzqn", "zzqo", "zzqp", "zzqq", zzb.class});
                case 4:
                    return zzqr;
                case 5:
                    zzfr<zza> zzfr2 = zzbc;
                    zzfr<zza> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zza.class) {
                            zzfr<zza> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza = new zzdx.zza(zzqr);
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

        public static zza zzdx() {
            return zzqr;
        }

        static {
            zza zza = new zza();
            zzqr = zza;
            zzdx.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zzb extends zzdx<zzb, zza> implements zzfj {
        private static volatile zzfr<zzb> zzbc;
        /* access modifiers changed from: private */
        public static final zzb zzqz;
        private int zzbd;
        private String zzqs = "";
        private zzea zzqt = zzbo();
        private zzef zzqu = zzbm();
        private zzee<String> zzqv = zzdx.zzbp();
        private zzee<zzc> zzqw = zzbp();
        private zzct zzqx = zzct.zzgi;
        private zzed zzqy = zzbn();

        private zzb() {
        }

        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class zza extends zzdx.zzb<zzb, zza> implements zzfj {
            private zza() {
                super(zzb.zzqz);
            }

            /* synthetic */ zza(zzhi zzhi) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhj$zzb>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zzb> zzfr;
            switch (zzhi.zzaz[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzhi) null);
                case 3:
                    return zza((zzfh) zzqz, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0005\u0000\u0001\b\u0000\u0002\u0019\u0003\u0014\u0004\u001a\u0005\u001b\u0006\n\u0001\u0007\u0012", new Object[]{"zzbd", "zzqs", "zzqt", "zzqu", "zzqv", "zzqw", zzc.class, "zzqx", "zzqy"});
                case 4:
                    return zzqz;
                case 5:
                    zzfr<zzb> zzfr2 = zzbc;
                    zzfr<zzb> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zzb.class) {
                            zzfr<zzb> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza2 = new zzdx.zza(zzqz);
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
            zzqz = zzb;
            zzdx.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zzc extends zzdx<zzc, zza> implements zzfj {
        private static volatile zzfr<zzc> zzbc;
        /* access modifiers changed from: private */
        public static final zzc zzrd;
        private int zzbd;
        private zzee<zzb> zzqq = zzbp();
        private String zzra = "";
        private String zzrb = "";
        private zza zzrc;

        private zzc() {
        }

        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class zza extends zzdx.zzb<zzc, zza> implements zzfj {
            private zza() {
                super(zzc.zzrd);
            }

            /* synthetic */ zza(zzhi zzhi) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzhj$zzc>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zzc> zzfr;
            switch (zzhi.zzaz[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzhi) null);
                case 3:
                    return zza((zzfh) zzrd, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u001b\u0004\t\u0002", new Object[]{"zzbd", "zzra", "zzrb", "zzqq", zzb.class, "zzrc"});
                case 4:
                    return zzrd;
                case 5:
                    zzfr<zzc> zzfr2 = zzbc;
                    zzfr<zzc> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zzc.class) {
                            zzfr<zzc> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza2 = new zzdx.zza(zzrd);
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
            zzrd = zzc;
            zzdx.zza(zzc.class, zzc);
        }
    }
}
