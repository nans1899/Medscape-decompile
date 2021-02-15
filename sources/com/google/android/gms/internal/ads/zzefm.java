package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzefm extends zzejz<zzefm, zzb> implements zzell {
    private static volatile zzelw<zzefm> zzel;
    /* access modifiers changed from: private */
    public static final zzefm zzian;
    private int zziaf;
    private zzekk<zza> zziam = zzbgk();

    private zzefm() {
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz<zza, C0012zza> implements zzell {
        private static volatile zzelw<zza> zzel;
        /* access modifiers changed from: private */
        public static final zza zziao;
        private String zzhzh = "";
        private int zzhzy;
        private int zziaj;
        private int zziak;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.ads.zzefm$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class C0012zza extends zzejz.zzb<zza, C0012zza> implements zzell {
            private C0012zza() {
                super(zza.zziao);
            }

            public final C0012zza zzhr(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzhp(str);
                return this;
            }

            public final C0012zza zzb(zzefc zzefc) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zza(zzefc);
                return this;
            }

            public final C0012zza zzfj(int i) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzfh(i);
                return this;
            }

            public final C0012zza zzb(zzefv zzefv) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zza(zzefv);
                return this;
            }

            /* synthetic */ C0012zza(zzefl zzefl) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzhp(String str) {
            str.getClass();
            this.zzhzh = str;
        }

        /* access modifiers changed from: private */
        public final void zza(zzefc zzefc) {
            this.zziaj = zzefc.zzv();
        }

        /* access modifiers changed from: private */
        public final void zzfh(int i) {
            this.zziak = i;
        }

        /* access modifiers changed from: private */
        public final void zza(zzefv zzefv) {
            this.zzhzy = zzefv.zzv();
        }

        public static C0012zza zzbcq() {
            return (C0012zza) zziao.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzefl.zzdv[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0012zza((zzefl) null);
                case 3:
                    return zza((zzelj) zziao, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzhzh", "zziaj", "zziak", "zzhzy"});
                case 4:
                    return zziao;
                case 5:
                    zzelw<zza> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zza.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zziao);
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
            zziao = zza;
            zzejz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zzb extends zzejz.zzb<zzefm, zzb> implements zzell {
        private zzb() {
            super(zzefm.zzian);
        }

        public final zzb zzfi(int i) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefm) this.zzijh).zzfg(i);
            return this;
        }

        public final zzb zzb(zza zza) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            ((zzefm) this.zzijh).zza(zza);
            return this;
        }

        /* synthetic */ zzb(zzefl zzefl) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public final void zzfg(int i) {
        this.zziaf = i;
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        zza2.getClass();
        zzekk<zza> zzekk = this.zziam;
        if (!zzekk.zzbeb()) {
            this.zziam = zzejz.zza(zzekk);
        }
        this.zziam.add(zza2);
    }

    public static zzb zzbco() {
        return (zzb) zzian.zzbgf();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzefl.zzdv[i - 1]) {
            case 1:
                return new zzefm();
            case 2:
                return new zzb((zzefl) null);
            case 3:
                return zza((zzelj) zzian, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zziaf", "zziam", zza.class});
            case 4:
                return zzian;
            case 5:
                zzelw<zzefm> zzelw = zzel;
                if (zzelw == null) {
                    synchronized (zzefm.class) {
                        zzelw = zzel;
                        if (zzelw == null) {
                            zzelw = new zzejz.zza<>(zzian);
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
        zzefm zzefm = new zzefm();
        zzian = zzefm;
        zzejz.zza(zzefm.class, zzefm);
    }
}
