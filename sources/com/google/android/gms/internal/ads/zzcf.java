package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.medscape.android.drugs.helper.SearchHelper;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzcf {

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zza extends zzejz<zza, C0009zza> implements zzell {
        private static volatile zzelw<zza> zzel;
        /* access modifiers changed from: private */
        public static final zza zzik;
        private int zzdw;
        private String zzeg = "";
        private String zzfg = "";
        private String zzfi = "";
        private String zzfj = SearchHelper.TYPE_DRUG;
        private String zzfk = SearchHelper.TYPE_DRUG;
        private int zzfn;
        private int zzfo;
        private String zzfp = "";
        private long zzfq;
        private long zzfr;
        private long zzfs;
        private long zzft;
        private long zzfu;
        private long zzfv;
        private long zzfw;
        private long zzfx;
        private long zzfy;
        private long zzfz;
        private String zzga = "";
        private long zzgb;
        private long zzgc;
        private long zzgd;
        private long zzge;
        private long zzgf;
        private long zzgg;
        private long zzgh;
        private long zzgi;
        private long zzgj;
        private String zzgk = SearchHelper.TYPE_DRUG;
        private String zzgl = "";
        private long zzgm;
        private long zzgn;
        private long zzgo;
        private long zzgp;
        private long zzgq = -1;
        private long zzgr = -1;
        private zzb zzgs;
        private long zzgt = -1;
        private long zzgu = -1;
        private long zzgv = -1;
        private long zzgw = -1;
        private long zzgx = -1;
        private long zzgy = -1;
        private long zzgz = -1;
        private int zzha = 1000;
        private int zzhb = 1000;
        private long zzhc = -1;
        private long zzhd = -1;
        private long zzhe = -1;
        private long zzhf = -1;
        private long zzhg = -1;
        private int zzhh = 1000;
        private zze zzhi;
        private zzekk<zze> zzhj = zzbgk();
        private zzf zzhk;
        private long zzhl = -1;
        private long zzhm = -1;
        private long zzhn = -1;
        private long zzho = -1;
        private long zzhp = -1;
        private long zzhq = -1;
        private long zzhr = -1;
        private long zzhs = -1;
        private String zzht = SearchHelper.TYPE_DRUG;
        private long zzhu = -1;
        private int zzhv;
        private int zzhw;
        private int zzhx;
        private zze zzhy;
        private long zzhz = -1;
        private int zzia = 1000;
        private int zzib = 1000;
        private String zzic = SearchHelper.TYPE_DRUG;
        private long zzid;
        private String zzie = "";
        private int zzif = 2;
        private boolean zzig;
        private String zzih = "";
        private long zzii;
        private zzd zzij;

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public enum zzb implements zzeke {
            DEBUGGER_STATE_UNSPECIFIED(0),
            DEBUGGER_STATE_NOT_INSTALLED(1),
            DEBUGGER_STATE_INSTALLED(2),
            DEBUGGER_STATE_ACTIVE(3),
            DEBUGGER_STATE_ENVVAR(4),
            DEBUGGER_STATE_MACHPORT(5),
            DEBUGGER_STATE_ENVVAR_MACHPORT(6);
            
            private static final zzekd<zzb> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public static zzb zzk(int i) {
                switch (i) {
                    case 0:
                        return DEBUGGER_STATE_UNSPECIFIED;
                    case 1:
                        return DEBUGGER_STATE_NOT_INSTALLED;
                    case 2:
                        return DEBUGGER_STATE_INSTALLED;
                    case 3:
                        return DEBUGGER_STATE_ACTIVE;
                    case 4:
                        return DEBUGGER_STATE_ENVVAR;
                    case 5:
                        return DEBUGGER_STATE_MACHPORT;
                    case 6:
                        return DEBUGGER_STATE_ENVVAR_MACHPORT;
                    default:
                        return null;
                }
            }

            public static zzekg zzw() {
                return zzch.zzeu;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzes = new zzcg();
            }
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public enum zzc implements zzeke {
            DEVICE_IDENTIFIER_NO_ID(0),
            DEVICE_IDENTIFIER_APP_SPECIFIC_ID(1),
            DEVICE_IDENTIFIER_GLOBAL_ID(2),
            DEVICE_IDENTIFIER_ADVERTISER_ID(3),
            DEVICE_IDENTIFIER_ADVERTISER_ID_UNHASHED(4),
            DEVICE_IDENTIFIER_ANDROID_AD_ID(5),
            DEVICE_IDENTIFIER_GFIBER_ADVERTISING_ID(6);
            
            private static final zzekd<zzc> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public static zzc zzl(int i) {
                switch (i) {
                    case 0:
                        return DEVICE_IDENTIFIER_NO_ID;
                    case 1:
                        return DEVICE_IDENTIFIER_APP_SPECIFIC_ID;
                    case 2:
                        return DEVICE_IDENTIFIER_GLOBAL_ID;
                    case 3:
                        return DEVICE_IDENTIFIER_ADVERTISER_ID;
                    case 4:
                        return DEVICE_IDENTIFIER_ADVERTISER_ID_UNHASHED;
                    case 5:
                        return DEVICE_IDENTIFIER_ANDROID_AD_ID;
                    case 6:
                        return DEVICE_IDENTIFIER_GFIBER_ADVERTISING_ID;
                    default:
                        return null;
                }
            }

            public static zzekg zzw() {
                return zzci.zzeu;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzes = new zzcj();
            }
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public enum zzd implements zzeke {
            ERROR_ENCODE_SIZE_FAIL(1),
            ERROR_UNKNOWN(3),
            ERROR_NO_SIGNALS(5),
            ERROR_ENCRYPTION(7),
            ERROR_MEMORY(9),
            ERROR_SIMULATOR(11),
            ERROR_SERVICE(13),
            ERROR_THREAD(15),
            PSN_WEB64_FAIL(2),
            PSN_DECRYPT_SIZE_FAIL(4),
            PSN_MD5_CHECK_FAIL(8),
            PSN_MD5_SIZE_FAIL(16),
            PSN_MD5_FAIL(32),
            PSN_DECODE_FAIL(64),
            PSN_SALT_FAIL(128),
            PSN_BITSLICER_FAIL(256),
            PSN_REQUEST_TYPE_FAIL(512),
            PSN_INVALID_ERROR_CODE(1024),
            PSN_TIMESTAMP_EXPIRED(2048),
            PSN_ENCODE_SIZE_FAIL(4096),
            PSN_BLANK_VALUE(8192),
            PSN_INITIALIZATION_FAIL(16384),
            PSN_GASS_CLIENT_FAIL(32768),
            PSN_SIGNALS_TIMEOUT(65536),
            PSN_TINK_FAIL(131072);
            
            private static final zzekd<zzd> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzd(int i) {
                this.value = i;
            }

            static {
                zzes = new zzck();
            }
        }

        private zza() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zze extends zzejz<zze, C0010zza> implements zzell {
            private static volatile zzelw<zze> zzel;
            /* access modifiers changed from: private */
            public static final zze zzku;
            private int zzdw;
            private long zzgb = -1;
            private long zzgc = -1;
            private long zzkb = -1;
            private long zzkc = -1;
            private long zzkd = -1;
            private long zzke = -1;
            private int zzkf = 1000;
            private long zzkg = -1;
            private long zzkh = -1;
            private long zzki = -1;
            private int zzkj = 1000;
            private long zzkk = -1;
            private long zzkl = -1;
            private long zzkm = -1;
            private long zzkn = -1;
            private long zzko;
            private long zzkp;
            private long zzkq = -1;
            private long zzkr = -1;
            private long zzks = -1;
            private long zzkt = -1;

            private zze() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzcf$zza$zze$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
            public static final class C0010zza extends zzejz.zzb<zze, C0010zza> implements zzell {
                private C0010zza() {
                    super(zze.zzku);
                }

                public final C0010zza zzbs(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzaq(j);
                    return this;
                }

                public final C0010zza zzbt(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzar(j);
                    return this;
                }

                public final C0010zza zzbu(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcj(j);
                    return this;
                }

                public final C0010zza zzbv(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzck(j);
                    return this;
                }

                public final C0010zza zzat() {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzau();
                    return this;
                }

                public final C0010zza zzbw(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcl(j);
                    return this;
                }

                public final C0010zza zzbx(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcm(j);
                    return this;
                }

                public final C0010zza zzk(zzcn zzcn) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzm(zzcn);
                    return this;
                }

                public final C0010zza zzby(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcn(j);
                    return this;
                }

                public final C0010zza zzbz(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzco(j);
                    return this;
                }

                public final C0010zza zzca(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcp(j);
                    return this;
                }

                public final C0010zza zzl(zzcn zzcn) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzn(zzcn);
                    return this;
                }

                public final C0010zza zzcb(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcq(j);
                    return this;
                }

                public final C0010zza zzcc(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcr(j);
                    return this;
                }

                public final C0010zza zzcd(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcs(j);
                    return this;
                }

                public final C0010zza zzce(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzct(j);
                    return this;
                }

                public final C0010zza zzcf(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcu(j);
                    return this;
                }

                public final C0010zza zzcg(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcv(j);
                    return this;
                }

                public final C0010zza zzch(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcw(j);
                    return this;
                }

                public final C0010zza zzci(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zze) this.zzijh).zzcx(j);
                    return this;
                }

                /* synthetic */ C0010zza(zzce zzce) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzaq(long j) {
                this.zzdw |= 1;
                this.zzgb = j;
            }

            /* access modifiers changed from: private */
            public final void zzar(long j) {
                this.zzdw |= 2;
                this.zzgc = j;
            }

            /* access modifiers changed from: private */
            public final void zzcj(long j) {
                this.zzdw |= 4;
                this.zzkb = j;
            }

            /* access modifiers changed from: private */
            public final void zzck(long j) {
                this.zzdw |= 8;
                this.zzkc = j;
            }

            /* access modifiers changed from: private */
            public final void zzau() {
                this.zzdw &= -9;
                this.zzkc = -1;
            }

            /* access modifiers changed from: private */
            public final void zzcl(long j) {
                this.zzdw |= 16;
                this.zzkd = j;
            }

            /* access modifiers changed from: private */
            public final void zzcm(long j) {
                this.zzdw |= 32;
                this.zzke = j;
            }

            /* access modifiers changed from: private */
            public final void zzm(zzcn zzcn) {
                this.zzkf = zzcn.zzv();
                this.zzdw |= 64;
            }

            /* access modifiers changed from: private */
            public final void zzcn(long j) {
                this.zzdw |= 128;
                this.zzkg = j;
            }

            /* access modifiers changed from: private */
            public final void zzco(long j) {
                this.zzdw |= 256;
                this.zzkh = j;
            }

            /* access modifiers changed from: private */
            public final void zzcp(long j) {
                this.zzdw |= 512;
                this.zzki = j;
            }

            /* access modifiers changed from: private */
            public final void zzn(zzcn zzcn) {
                this.zzkj = zzcn.zzv();
                this.zzdw |= 1024;
            }

            /* access modifiers changed from: private */
            public final void zzcq(long j) {
                this.zzdw |= 2048;
                this.zzkk = j;
            }

            /* access modifiers changed from: private */
            public final void zzcr(long j) {
                this.zzdw |= 4096;
                this.zzkl = j;
            }

            /* access modifiers changed from: private */
            public final void zzcs(long j) {
                this.zzdw |= 8192;
                this.zzkm = j;
            }

            /* access modifiers changed from: private */
            public final void zzct(long j) {
                this.zzdw |= 16384;
                this.zzkn = j;
            }

            /* access modifiers changed from: private */
            public final void zzcu(long j) {
                this.zzdw |= 32768;
                this.zzko = j;
            }

            /* access modifiers changed from: private */
            public final void zzcv(long j) {
                this.zzdw |= 65536;
                this.zzkp = j;
            }

            /* access modifiers changed from: private */
            public final void zzcw(long j) {
                this.zzdw |= 131072;
                this.zzkq = j;
            }

            /* access modifiers changed from: private */
            public final void zzcx(long j) {
                this.zzdw |= 262144;
                this.zzkr = j;
            }

            public static C0010zza zzav() {
                return (C0010zza) zzku.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzce.zzdv[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new C0010zza((zzce) null);
                    case 3:
                        return zza((zzelj) zzku, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဌ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဌ\n\fဂ\u000b\rဂ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂ\u0014", new Object[]{"zzdw", "zzgb", "zzgc", "zzkb", "zzkc", "zzkd", "zzke", "zzkf", zzcn.zzw(), "zzkg", "zzkh", "zzki", "zzkj", zzcn.zzw(), "zzkk", "zzkl", "zzkm", "zzkn", "zzko", "zzkp", "zzkq", "zzkr", "zzks", "zzkt"});
                    case 4:
                        return zzku;
                    case 5:
                        zzelw<zze> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zze.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzku);
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
                zze zze = new zze();
                zzku = zze;
                zzejz.zza(zze.class, zze);
            }
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zzf extends zzejz<zzf, C0011zza> implements zzell {
            private static volatile zzelw<zzf> zzel;
            /* access modifiers changed from: private */
            public static final zzf zzkz;
            private int zzdw;
            private long zzhf = -1;
            private long zzhg = -1;
            private long zzkv = -1;
            private long zzkw = -1;
            private long zzkx = -1;
            private long zzky = -1;

            private zzf() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzcf$zza$zzf$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
            public static final class C0011zza extends zzejz.zzb<zzf, C0011zza> implements zzell {
                private C0011zza() {
                    super(zzf.zzkz);
                }

                public final C0011zza zzcy(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzdc(j);
                    return this;
                }

                public final C0011zza zzcz(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzdd(j);
                    return this;
                }

                public final C0011zza zzda(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzde(j);
                    return this;
                }

                public final C0011zza zzdb(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzdf(j);
                    return this;
                }

                /* synthetic */ C0011zza(zzce zzce) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzdc(long j) {
                this.zzdw |= 4;
                this.zzkv = j;
            }

            /* access modifiers changed from: private */
            public final void zzdd(long j) {
                this.zzdw |= 8;
                this.zzkw = j;
            }

            /* access modifiers changed from: private */
            public final void zzde(long j) {
                this.zzdw |= 16;
                this.zzkx = j;
            }

            /* access modifiers changed from: private */
            public final void zzdf(long j) {
                this.zzdw |= 32;
                this.zzky = j;
            }

            public static C0011zza zzax() {
                return (C0011zza) zzkz.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzce.zzdv[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new C0011zza((zzce) null);
                    case 3:
                        return zza((zzelj) zzkz, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005", new Object[]{"zzdw", "zzhf", "zzhg", "zzkv", "zzkw", "zzkx", "zzky"});
                    case 4:
                        return zzkz;
                    case 5:
                        zzelw<zzf> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzf.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzkz);
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
                zzf zzf = new zzf();
                zzkz = zzf;
                zzejz.zza(zzf.class, zzf);
            }
        }

        /* renamed from: com.google.android.gms.internal.ads.zzcf$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class C0009zza extends zzejz.zzb<zza, C0009zza> implements zzell {
            private C0009zza() {
                super(zza.zzik);
            }

            public final C0009zza zzu(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzad(str);
                return this;
            }

            public final C0009zza zzv(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzae(str);
                return this;
            }

            public final C0009zza zze(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzal(j);
                return this;
            }

            public final C0009zza zzf(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzam(j);
                return this;
            }

            public final C0009zza zzg(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzan(j);
                return this;
            }

            public final C0009zza zzh(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzao(j);
                return this;
            }

            public final C0009zza zzi(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzap(j);
                return this;
            }

            public final C0009zza zzj(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzaq(j);
                return this;
            }

            public final C0009zza zzk(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzar(j);
                return this;
            }

            public final C0009zza zzl(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzas(j);
                return this;
            }

            public final C0009zza zzm(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzat(j);
                return this;
            }

            public final C0009zza zzn(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzau(j);
                return this;
            }

            public final C0009zza zzo(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzav(j);
                return this;
            }

            public final C0009zza zzp(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzaw(j);
                return this;
            }

            public final C0009zza zzw(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzaf(str);
                return this;
            }

            public final C0009zza zzx(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzag(str);
                return this;
            }

            public final C0009zza zzq(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzax(j);
                return this;
            }

            public final C0009zza zzr(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzay(j);
                return this;
            }

            public final C0009zza zzs(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzaz(j);
                return this;
            }

            public final C0009zza zzy(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzah(str);
                return this;
            }

            public final C0009zza zzt(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzba(j);
                return this;
            }

            @Deprecated
            public final C0009zza zzu(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbb(j);
                return this;
            }

            public final C0009zza zzv(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbc(j);
                return this;
            }

            public final C0009zza zzw(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbd(j);
                return this;
            }

            public final C0009zza zzx(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbe(j);
                return this;
            }

            public final C0009zza zzy(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbf(j);
                return this;
            }

            public final C0009zza zzz(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbg(j);
                return this;
            }

            public final C0009zza zzaa(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbh(j);
                return this;
            }

            public final C0009zza zzab(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbi(j);
                return this;
            }

            public final C0009zza zzz(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzai(str);
                return this;
            }

            public final C0009zza zzaa(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzaj(str);
                return this;
            }

            public final C0009zza zza(zzcn zzcn) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzf(zzcn);
                return this;
            }

            public final C0009zza zzb(zzcn zzcn) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzg(zzcn);
                return this;
            }

            public final C0009zza zzac(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbj(j);
                return this;
            }

            public final C0009zza zzad(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbk(j);
                return this;
            }

            public final C0009zza zzae(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbl(j);
                return this;
            }

            public final C0009zza zzc(zzcn zzcn) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzh(zzcn);
                return this;
            }

            public final C0009zza zza(zze zze) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzc(zze);
                return this;
            }

            public final C0009zza zzb(zze zze) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzd(zze);
                return this;
            }

            public final C0009zza zzai() {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzak();
                return this;
            }

            public final C0009zza zza(zzf zzf) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzb(zzf);
                return this;
            }

            public final C0009zza zzaf(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbm(j);
                return this;
            }

            public final C0009zza zzag(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbn(j);
                return this;
            }

            public final C0009zza zzah(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbo(j);
                return this;
            }

            public final C0009zza zzai(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbp(j);
                return this;
            }

            public final C0009zza zzaj(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbq(j);
                return this;
            }

            public final C0009zza zzab(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzak(str);
                return this;
            }

            public final C0009zza zzd(zzcn zzcn) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzi(zzcn);
                return this;
            }

            public final C0009zza zze(zzcn zzcn) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzj(zzcn);
                return this;
            }

            public final C0009zza zzac(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzal(str);
                return this;
            }

            public final C0009zza zza(zzc zzc) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzb(zzc);
                return this;
            }

            public final C0009zza zza(boolean z) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzb(z);
                return this;
            }

            public final C0009zza zzak(long j) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zza) this.zzijh).zzbr(j);
                return this;
            }

            /* synthetic */ C0009zza(zzce zzce) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzad(String str) {
            str.getClass();
            this.zzdw |= 1;
            this.zzfp = str;
        }

        /* access modifiers changed from: private */
        public final void zzae(String str) {
            str.getClass();
            this.zzdw |= 2;
            this.zzeg = str;
        }

        /* access modifiers changed from: private */
        public final void zzal(long j) {
            this.zzdw |= 4;
            this.zzfq = j;
        }

        /* access modifiers changed from: private */
        public final void zzam(long j) {
            this.zzdw |= 16;
            this.zzfs = j;
        }

        /* access modifiers changed from: private */
        public final void zzan(long j) {
            this.zzdw |= 32;
            this.zzft = j;
        }

        /* access modifiers changed from: private */
        public final void zzao(long j) {
            this.zzdw |= 1024;
            this.zzfy = j;
        }

        /* access modifiers changed from: private */
        public final void zzap(long j) {
            this.zzdw |= 2048;
            this.zzfz = j;
        }

        /* access modifiers changed from: private */
        public final void zzaq(long j) {
            this.zzdw |= 8192;
            this.zzgb = j;
        }

        /* access modifiers changed from: private */
        public final void zzar(long j) {
            this.zzdw |= 16384;
            this.zzgc = j;
        }

        /* access modifiers changed from: private */
        public final void zzas(long j) {
            this.zzdw |= 32768;
            this.zzgd = j;
        }

        /* access modifiers changed from: private */
        public final void zzat(long j) {
            this.zzdw |= 65536;
            this.zzge = j;
        }

        /* access modifiers changed from: private */
        public final void zzau(long j) {
            this.zzdw |= 524288;
            this.zzgh = j;
        }

        /* access modifiers changed from: private */
        public final void zzav(long j) {
            this.zzdw |= 1048576;
            this.zzgi = j;
        }

        /* access modifiers changed from: private */
        public final void zzaw(long j) {
            this.zzdw |= 2097152;
            this.zzgj = j;
        }

        public final boolean zzaj() {
            return (this.zzdw & 4194304) != 0;
        }

        public final String zzaf() {
            return this.zzfg;
        }

        /* access modifiers changed from: private */
        public final void zzaf(String str) {
            str.getClass();
            this.zzdw |= 4194304;
            this.zzfg = str;
        }

        /* access modifiers changed from: private */
        public final void zzag(String str) {
            str.getClass();
            this.zzdw |= 16777216;
            this.zzgl = str;
        }

        /* access modifiers changed from: private */
        public final void zzax(long j) {
            this.zzdw |= 33554432;
            this.zzgm = j;
        }

        /* access modifiers changed from: private */
        public final void zzay(long j) {
            this.zzdw |= 67108864;
            this.zzgn = j;
        }

        /* access modifiers changed from: private */
        public final void zzaz(long j) {
            this.zzdw |= 134217728;
            this.zzgo = j;
        }

        /* access modifiers changed from: private */
        public final void zzah(String str) {
            str.getClass();
            this.zzdw |= 268435456;
            this.zzfi = str;
        }

        /* access modifiers changed from: private */
        public final void zzba(long j) {
            this.zzdw |= 536870912;
            this.zzgp = j;
        }

        /* access modifiers changed from: private */
        public final void zzbb(long j) {
            this.zzdw |= 1073741824;
            this.zzgq = j;
        }

        /* access modifiers changed from: private */
        public final void zzbc(long j) {
            this.zzdw |= Integer.MIN_VALUE;
            this.zzgr = j;
        }

        /* access modifiers changed from: private */
        public final void zzbd(long j) {
            this.zzfn |= 2;
            this.zzgt = j;
        }

        /* access modifiers changed from: private */
        public final void zzbe(long j) {
            this.zzfn |= 4;
            this.zzgu = j;
        }

        /* access modifiers changed from: private */
        public final void zzbf(long j) {
            this.zzfn |= 8;
            this.zzgv = j;
        }

        /* access modifiers changed from: private */
        public final void zzbg(long j) {
            this.zzfn |= 16;
            this.zzgw = j;
        }

        /* access modifiers changed from: private */
        public final void zzbh(long j) {
            this.zzfn |= 32;
            this.zzgx = j;
        }

        /* access modifiers changed from: private */
        public final void zzbi(long j) {
            this.zzfn |= 64;
            this.zzgy = j;
        }

        /* access modifiers changed from: private */
        public final void zzai(String str) {
            str.getClass();
            this.zzfn |= 128;
            this.zzfj = str;
        }

        /* access modifiers changed from: private */
        public final void zzaj(String str) {
            str.getClass();
            this.zzfn |= 256;
            this.zzfk = str;
        }

        /* access modifiers changed from: private */
        public final void zzf(zzcn zzcn) {
            this.zzha = zzcn.zzv();
            this.zzfn |= 1024;
        }

        /* access modifiers changed from: private */
        public final void zzg(zzcn zzcn) {
            this.zzhb = zzcn.zzv();
            this.zzfn |= 2048;
        }

        /* access modifiers changed from: private */
        public final void zzbj(long j) {
            this.zzfn |= 4096;
            this.zzhc = j;
        }

        /* access modifiers changed from: private */
        public final void zzbk(long j) {
            this.zzfn |= 8192;
            this.zzhd = j;
        }

        /* access modifiers changed from: private */
        public final void zzbl(long j) {
            this.zzfn |= 16384;
            this.zzhe = j;
        }

        /* access modifiers changed from: private */
        public final void zzh(zzcn zzcn) {
            this.zzhh = zzcn.zzv();
            this.zzfn |= 131072;
        }

        /* access modifiers changed from: private */
        public final void zzc(zze zze2) {
            zze2.getClass();
            this.zzhi = zze2;
            this.zzfn |= 262144;
        }

        /* access modifiers changed from: private */
        public final void zzd(zze zze2) {
            zze2.getClass();
            zzekk<zze> zzekk = this.zzhj;
            if (!zzekk.zzbeb()) {
                this.zzhj = zzejz.zza(zzekk);
            }
            this.zzhj.add(zze2);
        }

        /* access modifiers changed from: private */
        public final void zzak() {
            this.zzhj = zzbgk();
        }

        /* access modifiers changed from: private */
        public final void zzb(zzf zzf2) {
            zzf2.getClass();
            this.zzhk = zzf2;
            this.zzfn |= 524288;
        }

        /* access modifiers changed from: private */
        public final void zzbm(long j) {
            this.zzfn |= 2097152;
            this.zzhm = j;
        }

        /* access modifiers changed from: private */
        public final void zzbn(long j) {
            this.zzfn |= 4194304;
            this.zzhn = j;
        }

        /* access modifiers changed from: private */
        public final void zzbo(long j) {
            this.zzfn |= 8388608;
            this.zzho = j;
        }

        /* access modifiers changed from: private */
        public final void zzbp(long j) {
            this.zzfn |= 67108864;
            this.zzhr = j;
        }

        /* access modifiers changed from: private */
        public final void zzbq(long j) {
            this.zzfn |= 134217728;
            this.zzhs = j;
        }

        /* access modifiers changed from: private */
        public final void zzak(String str) {
            str.getClass();
            this.zzfn |= 268435456;
            this.zzht = str;
        }

        /* access modifiers changed from: private */
        public final void zzi(zzcn zzcn) {
            this.zzia = zzcn.zzv();
            this.zzfo |= 8;
        }

        /* access modifiers changed from: private */
        public final void zzj(zzcn zzcn) {
            this.zzib = zzcn.zzv();
            this.zzfo |= 16;
        }

        public final String zzal() {
            return this.zzie;
        }

        /* access modifiers changed from: private */
        public final void zzal(String str) {
            str.getClass();
            this.zzfo |= 128;
            this.zzie = str;
        }

        public final zzc zzam() {
            zzc zzl = zzc.zzl(this.zzif);
            return zzl == null ? zzc.DEVICE_IDENTIFIER_GLOBAL_ID : zzl;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzc zzc2) {
            this.zzif = zzc2.zzv();
            this.zzfo |= 256;
        }

        public final boolean zzan() {
            return this.zzig;
        }

        /* access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzfo |= 512;
            this.zzig = z;
        }

        /* access modifiers changed from: private */
        public final void zzbr(long j) {
            this.zzfo |= 2048;
            this.zzii = j;
        }

        public final boolean zzao() {
            return (this.zzfo & 4096) != 0;
        }

        public final zzd zzap() {
            zzd zzd2 = this.zzij;
            return zzd2 == null ? zzd.zzbi() : zzd2;
        }

        public static zza zza(byte[] bArr, zzejm zzejm) throws zzekj {
            return (zza) zzejz.zza(zzik, bArr, zzejm);
        }

        public static C0009zza zzaq() {
            return (C0009zza) zzik.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0009zza((zzce) null);
                case 3:
                    return zza((zzelj) zzik, "\u0001N\u0000\u0003\u0001ÉN\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဂ\n\fဂ\u000b\rဈ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂF\u0016ဂ\u0014\u0017ဂ\u0015\u0018ဈG\u0019ဂK\u001aဌH\u001bဈ\u0016\u001cဇI\u001dဈ\u0018\u001eဈJ\u001fဂ\u0019 ဂ\u001a!ဂ\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဂ\u001f&ဉ 'ဂ!(ဂ\")ဂ#*ဂ$+\u001b,ဂ%-ဂ&.ဈ'/ဈ(0ဌ*1ဌ+2ဉ23ဂ,4ဂ-5ဂ.6ဂ/7ဂ08ဌ19ဉ3:ဂ4;ဂ5<ဂ6=ဂ7>ဂ:?ဂ;@ဂ=Aဌ>Bဌ?Cဈ<Dဌ@EဉAFဂBGဂ8Hဂ9IဌCJဂ)Kဈ\u0017LဌDMဈEÉဉL", new Object[]{"zzdw", "zzfn", "zzfo", "zzfp", "zzeg", "zzfq", "zzfr", "zzfs", "zzft", "zzfu", "zzfv", "zzfw", "zzfx", "zzfy", "zzfz", "zzga", "zzgb", "zzgc", "zzgd", "zzge", "zzgf", "zzgg", "zzgh", "zzid", "zzgi", "zzgj", "zzie", "zzii", "zzif", zzc.zzw(), "zzfg", "zzig", "zzgl", "zzih", "zzgm", "zzgn", "zzgo", "zzfi", "zzgp", "zzgq", "zzgr", "zzgs", "zzgt", "zzgu", "zzgv", "zzgw", "zzhj", zze.class, "zzgx", "zzgy", "zzfj", "zzfk", "zzha", zzcn.zzw(), "zzhb", zzcn.zzw(), "zzhi", "zzhc", "zzhd", "zzhe", "zzhf", "zzhg", "zzhh", zzcn.zzw(), "zzhk", "zzhl", "zzhm", "zzhn", "zzho", "zzhr", "zzhs", "zzhu", "zzhv", zzcm.zzw(), "zzhw", zzcs.zzw(), "zzht", "zzhx", zzb.zzw(), "zzhy", "zzhz", "zzhp", "zzhq", "zzia", zzcn.zzw(), "zzgz", "zzgk", "zzib", zzcn.zzw(), "zzic", "zzij"});
                case 4:
                    return zzik;
                case 5:
                    zzelw<zza> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zza.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzik);
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

        public static zza zzar() {
            return zzik;
        }

        static {
            zza zza = new zza();
            zzik = zza;
            zzejz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzb extends zzejz<zzb, zza> implements zzell {
        private static volatile zzelw<zzb> zzel;
        /* access modifiers changed from: private */
        public static final zzb zzlf;
        private int zzdw;
        private long zzla;
        private int zzlb;
        private boolean zzlc;
        private zzekf zzld = zzbgi();
        private long zzle;

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzb, zza> implements zzell {
            private zza() {
                super(zzb.zzlf);
            }

            /* synthetic */ zza(zzce zzce) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzce) null);
                case 3:
                    return zza((zzelj) zzlf, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဂ\u0000\u0002င\u0001\u0003ဇ\u0002\u0004\u0016\u0005ဃ\u0003", new Object[]{"zzdw", "zzla", "zzlb", "zzlc", "zzld", "zzle"});
                case 4:
                    return zzlf;
                case 5:
                    zzelw<zzb> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzb.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzlf);
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
            zzlf = zzb;
            zzejz.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzd extends zzejz<zzd, zza> implements zzell {
        private static volatile zzelw<zzd> zzel;
        /* access modifiers changed from: private */
        public static final zzd zzme;
        private int zzdw;
        private long zzla;
        private String zzmc = "";
        private zzeip zzmd = zzeip.zzier;

        private zzd() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzd, zza> implements zzell {
            private zza() {
                super(zzd.zzme);
            }

            /* synthetic */ zza(zzce zzce) {
                this();
            }
        }

        public final boolean zzbg() {
            return (this.zzdw & 1) != 0;
        }

        public final long zzbh() {
            return this.zzla;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza((zzce) null);
                case 3:
                    return zza((zzelj) zzme, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zzdw", "zzla", "zzmc", "zzmd"});
                case 4:
                    return zzme;
                case 5:
                    zzelw<zzd> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzd.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzme);
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

        public static zzd zzbi() {
            return zzme;
        }

        static {
            zzd zzd = new zzd();
            zzme = zzd;
            zzejz.zza(zzd.class, zzd);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zze extends zzejz<zze, zza> implements zzell {
        private static volatile zzelw<zze> zzel;
        /* access modifiers changed from: private */
        public static final zze zzmf;
        private int zzdw;
        private String zzfl = "";

        private zze() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zze, zza> implements zzell {
            private zza() {
                super(zze.zzmf);
            }

            /* synthetic */ zza(zzce zzce) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzce) null);
                case 3:
                    return zza((zzelj) zzmf, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzdw", "zzfl"});
                case 4:
                    return zzmf;
                case 5:
                    zzelw<zze> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zze.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzmf);
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
            zze zze = new zze();
            zzmf = zze;
            zzejz.zza(zze.class, zze);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzc extends zzejz<zzc, zza> implements zzell {
        private static volatile zzelw<zzc> zzel;
        /* access modifiers changed from: private */
        public static final zzc zzlk;
        private int zzdw;
        private zzeip zzlg = zzeip.zzier;
        private zzeip zzlh = zzeip.zzier;
        private zzeip zzli = zzeip.zzier;
        private zzeip zzlj = zzeip.zzier;

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzc, zza> implements zzell {
            private zza() {
                super(zzc.zzlk);
            }

            public final zza zza(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzc) this.zzijh).zze(zzeip);
                return this;
            }

            public final zza zzb(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzc) this.zzijh).zzf(zzeip);
                return this;
            }

            public final zza zzc(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzc) this.zzijh).zzg(zzeip);
                return this;
            }

            public final zza zzd(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzc) this.zzijh).zzh(zzeip);
                return this;
            }

            /* synthetic */ zza(zzce zzce) {
                this();
            }
        }

        public final zzeip zzba() {
            return this.zzlg;
        }

        /* access modifiers changed from: private */
        public final void zze(zzeip zzeip) {
            zzeip.getClass();
            this.zzdw |= 1;
            this.zzlg = zzeip;
        }

        public final zzeip zzbb() {
            return this.zzlh;
        }

        /* access modifiers changed from: private */
        public final void zzf(zzeip zzeip) {
            zzeip.getClass();
            this.zzdw |= 2;
            this.zzlh = zzeip;
        }

        public final zzeip zzbc() {
            return this.zzli;
        }

        /* access modifiers changed from: private */
        public final void zzg(zzeip zzeip) {
            zzeip.getClass();
            this.zzdw |= 4;
            this.zzli = zzeip;
        }

        public final zzeip zzbd() {
            return this.zzlj;
        }

        /* access modifiers changed from: private */
        public final void zzh(zzeip zzeip) {
            zzeip.getClass();
            this.zzdw |= 8;
            this.zzlj = zzeip;
        }

        public static zzc zzb(byte[] bArr, zzejm zzejm) throws zzekj {
            return (zzc) zzejz.zza(zzlk, bArr, zzejm);
        }

        public static zza zzbe() {
            return (zza) zzlk.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzce) null);
                case 3:
                    return zza((zzelj) zzlk, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zzdw", "zzlg", "zzlh", "zzli", "zzlj"});
                case 4:
                    return zzlk;
                case 5:
                    zzelw<zzc> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzc.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzlk);
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
            zzc zzc = new zzc();
            zzlk = zzc;
            zzejz.zza(zzc.class, zzc);
        }
    }

    /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
    public static final class zzf extends zzejz<zzf, zza> implements zzell {
        private static volatile zzelw<zzf> zzel;
        /* access modifiers changed from: private */
        public static final zzf zzmh;
        private int zzdw;
        private int zzhv = 1;
        private int zzhw = 1;
        private zzeip zzlh = zzeip.zzier;
        private zzekk<zzeip> zzmg = zzbgk();

        private zzf() {
        }

        /* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzf, zza> implements zzell {
            private zza() {
                super(zzf.zzmh);
            }

            public final zza zzj(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzf) this.zzijh).zzi(zzeip);
                return this;
            }

            public final zza zzk(zzeip zzeip) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzf) this.zzijh).zzf(zzeip);
                return this;
            }

            public final zza zzb(zzcm zzcm) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzf) this.zzijh).zza(zzcm);
                return this;
            }

            /* synthetic */ zza(zzce zzce) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzi(zzeip zzeip) {
            zzeip.getClass();
            zzekk<zzeip> zzekk = this.zzmg;
            if (!zzekk.zzbeb()) {
                this.zzmg = zzejz.zza(zzekk);
            }
            this.zzmg.add(zzeip);
        }

        /* access modifiers changed from: private */
        public final void zzf(zzeip zzeip) {
            zzeip.getClass();
            this.zzdw |= 1;
            this.zzlh = zzeip;
        }

        /* access modifiers changed from: private */
        public final void zza(zzcm zzcm) {
            this.zzhv = zzcm.zzv();
            this.zzdw |= 4;
        }

        public static zza zzbl() {
            return (zza) zzmh.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzce.zzdv[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza((zzce) null);
                case 3:
                    return zza((zzelj) zzmh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001c\u0002ည\u0000\u0003ဌ\u0001\u0004ဌ\u0002", new Object[]{"zzdw", "zzmg", "zzlh", "zzhw", zzcs.zzw(), "zzhv", zzcm.zzw()});
                case 4:
                    return zzmh;
                case 5:
                    zzelw<zzf> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzf.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzmh);
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
            zzf zzf = new zzf();
            zzmh = zzf;
            zzejz.zza(zzf.class, zzf);
        }
    }
}
