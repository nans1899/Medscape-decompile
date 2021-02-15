package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzal {

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zza extends zzdx<zza, C0031zza> implements zzfj {
        /* access modifiers changed from: private */
        public static final zza zzbb;
        private static volatile zzfr<zza> zzbc;
        private zzee<zzb> zzba = zzbp();

        private zza() {
        }

        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class zzb extends zzdx<zzb, C0032zza> implements zzfj {
            private static volatile zzfr<zzb> zzbc;
            /* access modifiers changed from: private */
            public static final zzb zzbh;
            private int zzbd;
            private String zzbe = "";
            private String zzbf = "";
            private int zzbg;

            private zzb() {
            }

            /* renamed from: com.google.android.gms.internal.icing.zzal$zza$zzb$zza  reason: collision with other inner class name */
            /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
            public static final class C0032zza extends zzdx.zzb<zzb, C0032zza> implements zzfj {
                private C0032zza() {
                    super(zzb.zzbh);
                }

                public final C0032zza zze(String str) {
                    if (this.zzki) {
                        zzbt();
                        this.zzki = false;
                    }
                    ((zzb) this.zzkh).zzg(str);
                    return this;
                }

                public final C0032zza zzf(String str) {
                    if (this.zzki) {
                        zzbt();
                        this.zzki = false;
                    }
                    ((zzb) this.zzkh).zzh(str);
                    return this;
                }

                public final C0032zza zzd(int i) {
                    if (this.zzki) {
                        zzbt();
                        this.zzki = false;
                    }
                    ((zzb) this.zzkh).zze(i);
                    return this;
                }

                /* synthetic */ C0032zza(zzak zzak) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzg(String str) {
                str.getClass();
                this.zzbd |= 1;
                this.zzbe = str;
            }

            /* access modifiers changed from: private */
            public final void zzh(String str) {
                str.getClass();
                this.zzbd |= 2;
                this.zzbf = str;
            }

            /* access modifiers changed from: private */
            public final void zze(int i) {
                this.zzbd |= 4;
                this.zzbg = i;
            }

            public static C0032zza zzh() {
                return (C0032zza) zzbh.zzbk();
            }

            /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzal$zza$zzb>] */
            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                zzfr<zzb> zzfr;
                switch (zzak.zzaz[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0032zza((zzak) null);
                    case 3:
                        return zza((zzfh) zzbh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0004\u0002", new Object[]{"zzbd", "zzbe", "zzbf", "zzbg"});
                    case 4:
                        return zzbh;
                    case 5:
                        zzfr<zzb> zzfr2 = zzbc;
                        zzfr<zzb> zzfr3 = zzfr2;
                        if (zzfr2 == null) {
                            synchronized (zzb.class) {
                                zzfr<zzb> zzfr4 = zzbc;
                                zzfr = zzfr4;
                                if (zzfr4 == null) {
                                    ? zza = new zzdx.zza(zzbh);
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
                zzb zzb = new zzb();
                zzbh = zzb;
                zzdx.zza(zzb.class, zzb);
            }
        }

        /* renamed from: com.google.android.gms.internal.icing.zzal$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
        public static final class C0031zza extends zzdx.zzb<zza, C0031zza> implements zzfj {
            private C0031zza() {
                super(zza.zzbb);
            }

            public final C0031zza zza(Iterable<? extends zzb> iterable) {
                if (this.zzki) {
                    zzbt();
                    this.zzki = false;
                }
                ((zza) this.zzkh).zzb(iterable);
                return this;
            }

            /* synthetic */ C0031zza(zzak zzak) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzb> iterable) {
            if (!this.zzba.zzah()) {
                this.zzba = zzdx.zza(this.zzba);
            }
            zzcm.zza(iterable, this.zzba);
        }

        public static C0031zza zzf() {
            return (C0031zza) zzbb.zzbk();
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.icing.zzdx$zza, com.google.android.gms.internal.icing.zzfr<com.google.android.gms.internal.icing.zzal$zza>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zzfr<zza> zzfr;
            switch (zzak.zzaz[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0031zza((zzak) null);
                case 3:
                    return zza((zzfh) zzbb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzba", zzb.class});
                case 4:
                    return zzbb;
                case 5:
                    zzfr<zza> zzfr2 = zzbc;
                    zzfr<zza> zzfr3 = zzfr2;
                    if (zzfr2 == null) {
                        synchronized (zza.class) {
                            zzfr<zza> zzfr4 = zzbc;
                            zzfr = zzfr4;
                            if (zzfr4 == null) {
                                ? zza = new zzdx.zza(zzbb);
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
            zzbb = zza;
            zzdx.zza(zza.class, zza);
        }
    }
}
