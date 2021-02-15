package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzbw {

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz<zza, C0006zza> implements zzell {
        /* access modifiers changed from: private */
        public static final zza zzek;
        private static volatile zzelw<zza> zzel;
        private int zzdw;
        private String zzdx = "";
        private long zzdy;
        private String zzdz = "";
        private String zzea = "";
        private String zzeb = "";
        private long zzec;
        private long zzed;
        private String zzee = "";
        private long zzef;
        private String zzeg = "";
        private String zzeh = "";
        private zzekk<zzb> zzei = zzbgk();
        private int zzej;

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zzb extends zzejz<zzb, C0007zza> implements zzell {
            private static volatile zzelw<zzb> zzel;
            /* access modifiers changed from: private */
            public static final zzb zzeo;
            private int zzdw;
            private String zzem = "";
            private String zzen = "";

            private zzb() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzbw$zza$zzb$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
            public static final class C0007zza extends zzejz.zzb<zzb, C0007zza> implements zzell {
                private C0007zza() {
                    super(zzb.zzeo);
                }

                /* synthetic */ C0007zza(zzbv zzbv) {
                    this();
                }
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzbv.zzdv[i - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0007zza((zzbv) null);
                    case 3:
                        return zza((zzelj) zzeo, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzdw", "zzem", "zzen"});
                    case 4:
                        return zzeo;
                    case 5:
                        zzelw<zzb> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzb.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzeo);
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
                zzeo = zzb;
                zzejz.zza(zzb.class, zzb);
            }
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public enum zzc implements zzeke {
            UNKNOWN(0),
            ENABLED(1),
            DISABLED(2);
            
            private static final zzekd<zzc> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public static zzc zzh(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return ENABLED;
                }
                if (i != 2) {
                    return null;
                }
                return DISABLED;
            }

            public static zzekg zzw() {
                return zzbz.zzeu;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzes = new zzbx();
            }
        }

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.ads.zzbw$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class C0006zza extends zzejz.zzb<zza, C0006zza> implements zzell {
            private C0006zza() {
                super(zza.zzek);
            }

            public final C0006zza zzk(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzp(str);
                return this;
            }

            public final C0006zza zzc(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzd(j);
                return this;
            }

            public final C0006zza zzl(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzq(str);
                return this;
            }

            public final C0006zza zzm(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzr(str);
                return this;
            }

            public final C0006zza zzn(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzs(str);
                return this;
            }

            public final C0006zza zzo(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzt(str);
                return this;
            }

            public final C0006zza zza(zzc zzc) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzb(zzc);
                return this;
            }

            /* synthetic */ C0006zza(zzbv zzbv) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzp(String str) {
            str.getClass();
            this.zzdw |= 1;
            this.zzdx = str;
        }

        /* access modifiers changed from: private */
        public final void zzd(long j) {
            this.zzdw |= 2;
            this.zzdy = j;
        }

        /* access modifiers changed from: private */
        public final void zzq(String str) {
            str.getClass();
            this.zzdw |= 4;
            this.zzdz = str;
        }

        /* access modifiers changed from: private */
        public final void zzr(String str) {
            str.getClass();
            this.zzdw |= 8;
            this.zzea = str;
        }

        /* access modifiers changed from: private */
        public final void zzs(String str) {
            str.getClass();
            this.zzdw |= 16;
            this.zzeb = str;
        }

        /* access modifiers changed from: private */
        public final void zzt(String str) {
            str.getClass();
            this.zzdw |= 1024;
            this.zzeh = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzc zzc2) {
            this.zzej = zzc2.zzv();
            this.zzdw |= 2048;
        }

        public static C0006zza zzs() {
            return (C0006zza) zzek.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzbv.zzdv[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0006zza((zzbv) null);
                case 3:
                    return zza((zzelj) zzek, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဈ\u0007\tဂ\b\nဈ\t\u000bဈ\n\f\u001b\rဌ\u000b", new Object[]{"zzdw", "zzdx", "zzdy", "zzdz", "zzea", "zzeb", "zzec", "zzed", "zzee", "zzef", "zzeg", "zzeh", "zzei", zzb.class, "zzej", zzc.zzw()});
                case 4:
                    return zzek;
                case 5:
                    zzelw<zza> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zza.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzek);
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
            zzek = zza;
            zzejz.zza(zza.class, zza);
        }
    }
}
