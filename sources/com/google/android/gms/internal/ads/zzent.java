package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzent {

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza extends zzejz<zza, zzb> implements zzell {
        private static volatile zzelw<zza> zzel;
        /* access modifiers changed from: private */
        public static final zza zziqi;
        private int zzdw;
        private int zziqb;
        private C0013zza zziqc;
        private zzeip zziqd = zzeip.zzier;
        private zzeip zziqe = zzeip.zzier;
        private boolean zziqf;
        private boolean zziqg;
        private byte zziqh = 2;

        /* renamed from: com.google.android.gms.internal.ads.zzent$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class C0013zza extends zzejz<C0013zza, C0014zza> implements zzell {
            private static volatile zzelw<C0013zza> zzel;
            /* access modifiers changed from: private */
            public static final C0013zza zziqn;
            private int zzdw;
            private String zziqj = "";
            private String zziqk = "";
            private String zziql = "";
            private int zziqm;

            private C0013zza() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzent$zza$zza$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class C0014zza extends zzejz.zzb<C0013zza, C0014zza> implements zzell {
                private C0014zza() {
                    super(C0013zza.zziqn);
                }

                /* synthetic */ C0014zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new C0013zza();
                    case 2:
                        return new C0014zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zziqn, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003", new Object[]{"zzdw", "zziqj", "zziqk", "zziql", "zziqm"});
                    case 4:
                        return zziqn;
                    case 5:
                        zzelw<C0013zza> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (C0013zza.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zziqn);
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
                C0013zza zza = new C0013zza();
                zziqn = zza;
                zzejz.zza(C0013zza.class, zza);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public enum zzc implements zzeke {
            SAFE(0),
            DANGEROUS(1),
            UNKNOWN(2),
            POTENTIALLY_UNWANTED(3),
            DANGEROUS_HOST(4);
            
            private static final zzekd<zzc> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public static zzc zzht(int i) {
                if (i == 0) {
                    return SAFE;
                }
                if (i == 1) {
                    return DANGEROUS;
                }
                if (i == 2) {
                    return UNKNOWN;
                }
                if (i == 3) {
                    return POTENTIALLY_UNWANTED;
                }
                if (i != 4) {
                    return null;
                }
                return DANGEROUS_HOST;
            }

            public static zzekg zzw() {
                return zzenw.zzeu;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzes = new zzenx();
            }
        }

        private zza() {
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzb extends zzejz.zzb<zza, zzb> implements zzell {
            private zzb() {
                super(zza.zziqi);
            }

            /* synthetic */ zzb(zzenv zzenv) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzenv.zzdv[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb((zzenv) null);
                case 3:
                    return zza((zzelj) zziqi, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001ᔌ\u0000\u0002ဉ\u0001\u0003ည\u0002\u0004ည\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zzdw", "zziqb", zzc.zzw(), "zziqc", "zziqd", "zziqe", "zziqf", "zziqg"});
                case 4:
                    return zziqi;
                case 5:
                    zzelw<zza> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zza.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zziqi);
                                zzel = zzelw;
                            }
                        }
                    }
                    return zzelw;
                case 6:
                    return Byte.valueOf(this.zziqh);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zziqh = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zza = new zza();
            zziqi = zza;
            zzejz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zzb extends zzejz<zzb, zza> implements zzell {
        private static volatile zzelw<zzb> zzel;
        /* access modifiers changed from: private */
        public static final zzb zzirj;
        private int zzbzr;
        private int zzdw;
        private zzeip zziqd = zzeip.zzier;
        private byte zziqh = 2;
        private String zziqk = "";
        private int zziqu;
        private String zziqv = "";
        private String zziqw = "";
        private C0015zzb zziqx;
        private zzekk<zzh> zziqy = zzbgk();
        private String zziqz = "";
        private zzf zzira;
        private boolean zzirb;
        private zzekk<String> zzirc = zzejz.zzbgk();
        private String zzird = "";
        private boolean zzire;
        private boolean zzirf;
        private zzi zzirg;
        private zzekk<String> zzirh = zzejz.zzbgk();
        private zzekk<String> zziri = zzejz.zzbgk();

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zze extends zzejz<zze, zza> implements zzell {
            private static volatile zzelw<zze> zzel;
            /* access modifiers changed from: private */
            public static final zze zzisa;
            private int zzdw;
            private byte zziqh = 2;
            private zzekk<zzc> zzirp = zzbgk();
            private zzeip zzirq = zzeip.zzier;
            private zzeip zzirr = zzeip.zzier;
            private int zzirs;
            private C0017zzb zziry;
            private zzeip zzirz = zzeip.zzier;

            /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zze$zzb  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class C0017zzb extends zzejz<C0017zzb, zza> implements zzell {
                private static volatile zzelw<C0017zzb> zzel;
                /* access modifiers changed from: private */
                public static final C0017zzb zzisd;
                private int zzdw;
                private zzeip zzirw = zzeip.zzier;
                private int zzisb;
                private zzeip zzisc = zzeip.zzier;

                private C0017zzb() {
                }

                /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zze$zzb$zza */
                /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
                public static final class zza extends zzejz.zzb<C0017zzb, zza> implements zzell {
                    private zza() {
                        super(C0017zzb.zzisd);
                    }

                    /* synthetic */ zza(zzenv zzenv) {
                        this();
                    }
                }

                /* access modifiers changed from: protected */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzenv.zzdv[i - 1]) {
                        case 1:
                            return new C0017zzb();
                        case 2:
                            return new zza((zzenv) null);
                        case 3:
                            return zza((zzelj) zzisd, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ည\u0001\u0003ည\u0002", new Object[]{"zzdw", "zzisb", "zzisc", "zzirw"});
                        case 4:
                            return zzisd;
                        case 5:
                            zzelw<C0017zzb> zzelw = zzel;
                            if (zzelw == null) {
                                synchronized (C0017zzb.class) {
                                    zzelw = zzel;
                                    if (zzelw == null) {
                                        zzelw = new zzejz.zza<>(zzisd);
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
                    C0017zzb zzb = new C0017zzb();
                    zzisd = zzb;
                    zzejz.zza(C0017zzb.class, zzb);
                }
            }

            private zze() {
            }

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<zze, zza> implements zzell {
                private zza() {
                    super(zze.zzisa);
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzisa, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003\u0006ည\u0004", new Object[]{"zzdw", "zziry", "zzirp", zzc.class, "zzirq", "zzirr", "zzirs", "zzirz"});
                    case 4:
                        return zzisa;
                    case 5:
                        zzelw<zze> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zze.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzisa);
                                    zzel = zzelw;
                                }
                            }
                        }
                        return zzelw;
                    case 6:
                        return Byte.valueOf(this.zziqh);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zziqh = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zze zze = new zze();
                zzisa = zze;
                zzejz.zza(zze.class, zze);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public enum zzg implements zzeke {
            UNKNOWN(0),
            URL_PHISHING(1),
            URL_MALWARE(2),
            URL_UNWANTED(3),
            CLIENT_SIDE_PHISHING_URL(4),
            CLIENT_SIDE_MALWARE_URL(5),
            DANGEROUS_DOWNLOAD_RECOVERY(6),
            DANGEROUS_DOWNLOAD_WARNING(7),
            OCTAGON_AD(8),
            OCTAGON_AD_SB_MATCH(9);
            
            private static final zzekd<zzg> zzes = null;
            private final int value;

            public final int zzv() {
                return this.value;
            }

            public static zzg zzhv(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return URL_PHISHING;
                    case 2:
                        return URL_MALWARE;
                    case 3:
                        return URL_UNWANTED;
                    case 4:
                        return CLIENT_SIDE_PHISHING_URL;
                    case 5:
                        return CLIENT_SIDE_MALWARE_URL;
                    case 6:
                        return DANGEROUS_DOWNLOAD_RECOVERY;
                    case 7:
                        return DANGEROUS_DOWNLOAD_WARNING;
                    case 8:
                        return OCTAGON_AD;
                    case 9:
                        return OCTAGON_AD_SB_MATCH;
                    default:
                        return null;
                }
            }

            public static zzekg zzw() {
                return zzeoa.zzeu;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            private zzg(int i) {
                this.value = i;
            }

            static {
                zzes = new zzeob();
            }
        }

        private zzb() {
        }

        /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzb  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class C0015zzb extends zzejz<C0015zzb, zza> implements zzell {
            private static volatile zzelw<C0015zzb> zzel;
            /* access modifiers changed from: private */
            public static final C0015zzb zzirl;
            private int zzdw;
            private String zzirk = "";

            private C0015zzb() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzb$zza */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<C0015zzb, zza> implements zzell {
                private zza() {
                    super(C0015zzb.zzirl);
                }

                public final zza zzic(String str) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((C0015zzb) this.zzijh).zzid(str);
                    return this;
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzid(String str) {
                str.getClass();
                this.zzdw |= 1;
                this.zzirk = str;
            }

            public static zza zzbjo() {
                return (zza) zzirl.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new C0015zzb();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzirl, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzdw", "zzirk"});
                    case 4:
                        return zzirl;
                    case 5:
                        zzelw<C0015zzb> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (C0015zzb.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzirl);
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
                C0015zzb zzb = new C0015zzb();
                zzirl = zzb;
                zzejz.zza(C0015zzb.class, zzb);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzc extends zzejz<zzc, zza> implements zzell {
            private static volatile zzelw<zzc> zzel;
            /* access modifiers changed from: private */
            public static final zzc zzirn;
            private int zzdw;
            private zzeip zzhzi = zzeip.zzier;
            private byte zziqh = 2;
            private zzeip zzirm = zzeip.zzier;

            private zzc() {
            }

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<zzc, zza> implements zzell {
                private zza() {
                    super(zzc.zzirn);
                }

                public final zza zzan(zzeip zzeip) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzc) this.zzijh).zzap(zzeip);
                    return this;
                }

                public final zza zzao(zzeip zzeip) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzc) this.zzijh).zzae(zzeip);
                    return this;
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzap(zzeip zzeip) {
                zzeip.getClass();
                this.zzdw |= 1;
                this.zzirm = zzeip;
            }

            /* access modifiers changed from: private */
            public final void zzae(zzeip zzeip) {
                zzeip.getClass();
                this.zzdw |= 2;
                this.zzhzi = zzeip;
            }

            public static zza zzbjq() {
                return (zza) zzirn.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzirn, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᔊ\u0000\u0002ည\u0001", new Object[]{"zzdw", "zzirm", "zzhzi"});
                    case 4:
                        return zzirn;
                    case 5:
                        zzelw<zzc> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzc.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzirn);
                                    zzel = zzelw;
                                }
                            }
                        }
                        return zzelw;
                    case 6:
                        return Byte.valueOf(this.zziqh);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zziqh = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzc zzc = new zzc();
                zzirn = zzc;
                zzejz.zza(zzc.class, zzc);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzd extends zzejz<zzd, zza> implements zzell {
            private static volatile zzelw<zzd> zzel;
            /* access modifiers changed from: private */
            public static final zzd zzirt;
            private int zzdw;
            private byte zziqh = 2;
            private C0016zzb zziro;
            private zzekk<zzc> zzirp = zzbgk();
            private zzeip zzirq = zzeip.zzier;
            private zzeip zzirr = zzeip.zzier;
            private int zzirs;

            /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzd$zzb  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class C0016zzb extends zzejz<C0016zzb, zza> implements zzell {
                private static volatile zzelw<C0016zzb> zzel;
                /* access modifiers changed from: private */
                public static final C0016zzb zzirx;
                private int zzdw;
                private zzeip zziru = zzeip.zzier;
                private zzeip zzirv = zzeip.zzier;
                private zzeip zzirw = zzeip.zzier;

                private C0016zzb() {
                }

                /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzd$zzb$zza */
                /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
                public static final class zza extends zzejz.zzb<C0016zzb, zza> implements zzell {
                    private zza() {
                        super(C0016zzb.zzirx);
                    }

                    /* synthetic */ zza(zzenv zzenv) {
                        this();
                    }
                }

                /* access modifiers changed from: protected */
                public final Object zza(int i, Object obj, Object obj2) {
                    switch (zzenv.zzdv[i - 1]) {
                        case 1:
                            return new C0016zzb();
                        case 2:
                            return new zza((zzenv) null);
                        case 3:
                            return zza((zzelj) zzirx, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001\u0003ည\u0002", new Object[]{"zzdw", "zziru", "zzirv", "zzirw"});
                        case 4:
                            return zzirx;
                        case 5:
                            zzelw<C0016zzb> zzelw = zzel;
                            if (zzelw == null) {
                                synchronized (C0016zzb.class) {
                                    zzelw = zzel;
                                    if (zzelw == null) {
                                        zzelw = new zzejz.zza<>(zzirx);
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
                    C0016zzb zzb = new C0016zzb();
                    zzirx = zzb;
                    zzejz.zza(C0016zzb.class, zzb);
                }
            }

            private zzd() {
            }

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<zzd, zza> implements zzell {
                private zza() {
                    super(zzd.zzirt);
                }

                public final zza zza(zzc zzc) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzd) this.zzijh).zzb(zzc);
                    return this;
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzb(zzc zzc) {
                zzc.getClass();
                zzekk<zzc> zzekk = this.zzirp;
                if (!zzekk.zzbeb()) {
                    this.zzirp = zzejz.zza(zzekk);
                }
                this.zzirp.add(zzc);
            }

            public static zza zzbjs() {
                return (zza) zzirt.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzirt, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003", new Object[]{"zzdw", "zziro", "zzirp", zzc.class, "zzirq", "zzirr", "zzirs"});
                    case 4:
                        return zzirt;
                    case 5:
                        zzelw<zzd> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzd.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzirt);
                                    zzel = zzelw;
                                }
                            }
                        }
                        return zzelw;
                    case 6:
                        return Byte.valueOf(this.zziqh);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zziqh = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzd zzd = new zzd();
                zzirt = zzd;
                zzejz.zza(zzd.class, zzd);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzf extends zzejz<zzf, zza> implements zzell {
            private static volatile zzelw<zzf> zzel;
            /* access modifiers changed from: private */
            public static final zzf zzisg;
            private int zzbzr;
            private int zzdw;
            private String zzise = "";
            private zzeip zzisf = zzeip.zzier;

            /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzf$zzb  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public enum C0018zzb implements zzeke {
                TYPE_UNKNOWN(0),
                TYPE_CREATIVE(1);
                
                private static final zzekd<C0018zzb> zzes = null;
                private final int value;

                public final int zzv() {
                    return this.value;
                }

                public static C0018zzb zzhu(int i) {
                    if (i == 0) {
                        return TYPE_UNKNOWN;
                    }
                    if (i != 1) {
                        return null;
                    }
                    return TYPE_CREATIVE;
                }

                public static zzekg zzw() {
                    return zzenz.zzeu;
                }

                public final String toString() {
                    return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
                }

                private C0018zzb(int i) {
                    this.value = i;
                }

                static {
                    zzes = new zzeny();
                }
            }

            private zzf() {
            }

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<zzf, zza> implements zzell {
                private zza() {
                    super(zzf.zzisg);
                }

                public final zza zza(C0018zzb zzb) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzb(zzb);
                    return this;
                }

                public final zza zzie(String str) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).setMimeType(str);
                    return this;
                }

                public final zza zzaq(zzeip zzeip) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzf) this.zzijh).zzar(zzeip);
                    return this;
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzb(C0018zzb zzb) {
                this.zzbzr = zzb.zzv();
                this.zzdw |= 1;
            }

            /* access modifiers changed from: private */
            public final void setMimeType(String str) {
                str.getClass();
                this.zzdw |= 2;
                this.zzise = str;
            }

            /* access modifiers changed from: private */
            public final void zzar(zzeip zzeip) {
                zzeip.getClass();
                this.zzdw |= 4;
                this.zzisf = zzeip;
            }

            public static zza zzbjx() {
                return (zza) zzisg.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzisg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ည\u0002", new Object[]{"zzdw", "zzbzr", C0018zzb.zzw(), "zzise", "zzisf"});
                    case 4:
                        return zzisg;
                    case 5:
                        zzelw<zzf> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzf.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzisg);
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
                zzisg = zzf;
                zzejz.zza(zzf.class, zzf);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzh extends zzejz<zzh, C0019zzb> implements zzell {
            private static volatile zzelw<zzh> zzel;
            /* access modifiers changed from: private */
            public static final zzh zziti;
            private int zzdw;
            private byte zziqh = 2;
            private String zziqk = "";
            private int zzita;
            private zzd zzitb;
            private zze zzitc;
            private int zzitd;
            private zzekf zzite = zzbgi();
            private String zzitf = "";
            private int zzitg;
            private zzekk<String> zzith = zzejz.zzbgk();

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public enum zza implements zzeke {
                AD_RESOURCE_UNKNOWN(0),
                AD_RESOURCE_CREATIVE(1),
                AD_RESOURCE_POST_CLICK(2),
                AD_RESOURCE_AUTO_CLICK_DESTINATION(3);
                
                private static final zzekd<zza> zzes = null;
                private final int value;

                public final int zzv() {
                    return this.value;
                }

                public static zza zzhw(int i) {
                    if (i == 0) {
                        return AD_RESOURCE_UNKNOWN;
                    }
                    if (i == 1) {
                        return AD_RESOURCE_CREATIVE;
                    }
                    if (i == 2) {
                        return AD_RESOURCE_POST_CLICK;
                    }
                    if (i != 3) {
                        return null;
                    }
                    return AD_RESOURCE_AUTO_CLICK_DESTINATION;
                }

                public static zzekg zzw() {
                    return zzeoc.zzeu;
                }

                public final String toString() {
                    return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
                }

                private zza(int i) {
                    this.value = i;
                }

                static {
                    zzes = new zzeod();
                }
            }

            private zzh() {
            }

            /* renamed from: com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class C0019zzb extends zzejz.zzb<zzh, C0019zzb> implements zzell {
                private C0019zzb() {
                    super(zzh.zziti);
                }

                public final C0019zzb zzhx(int i) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzh) this.zzijh).setId(i);
                    return this;
                }

                public final C0019zzb zzih(String str) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzh) this.zzijh).setUrl(str);
                    return this;
                }

                public final C0019zzb zzb(zzd zzd) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzh) this.zzijh).zza(zzd);
                    return this;
                }

                public final C0019zzb zzb(zza zza) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzh) this.zzijh).zza(zza);
                    return this;
                }

                public final C0019zzb zzii(String str) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzh) this.zzijh).zzif(str);
                    return this;
                }

                /* synthetic */ C0019zzb(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void setId(int i) {
                this.zzdw |= 1;
                this.zzita = i;
            }

            public final String getUrl() {
                return this.zziqk;
            }

            /* access modifiers changed from: private */
            public final void setUrl(String str) {
                str.getClass();
                this.zzdw |= 2;
                this.zziqk = str;
            }

            /* access modifiers changed from: private */
            public final void zza(zzd zzd) {
                zzd.getClass();
                this.zzitb = zzd;
                this.zzdw |= 4;
            }

            /* access modifiers changed from: private */
            public final void zza(zza zza2) {
                this.zzitg = zza2.zzv();
                this.zzdw |= 64;
            }

            public final int zzbjz() {
                return this.zzith.size();
            }

            /* access modifiers changed from: private */
            public final void zzif(String str) {
                str.getClass();
                zzekk<String> zzekk = this.zzith;
                if (!zzekk.zzbeb()) {
                    this.zzith = zzejz.zza(zzekk);
                }
                this.zzith.add(str);
            }

            public static C0019zzb zzbka() {
                return (C0019zzb) zziti.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                int i2 = 1;
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zzh();
                    case 2:
                        return new C0019zzb((zzenv) null);
                    case 3:
                        return zza((zzelj) zziti, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001ᔄ\u0000\u0002ဈ\u0001\u0003ᐉ\u0002\u0004ᐉ\u0003\u0005င\u0004\u0006\u0016\u0007ဈ\u0005\bဌ\u0006\t\u001a", new Object[]{"zzdw", "zzita", "zziqk", "zzitb", "zzitc", "zzitd", "zzite", "zzitf", "zzitg", zza.zzw(), "zzith"});
                    case 4:
                        return zziti;
                    case 5:
                        zzelw<zzh> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzh.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zziti);
                                    zzel = zzelw;
                                }
                            }
                        }
                        return zzelw;
                    case 6:
                        return Byte.valueOf(this.zziqh);
                    case 7:
                        if (obj == null) {
                            i2 = 0;
                        }
                        this.zziqh = (byte) i2;
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzh zzh = new zzh();
                zziti = zzh;
                zzejz.zza(zzh.class, zzh);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zzi extends zzejz<zzi, zza> implements zzell {
            private static volatile zzelw<zzi> zzel;
            /* access modifiers changed from: private */
            public static final zzi zzitm;
            private int zzdw;
            private String zzitj = "";
            private long zzitk;
            private boolean zzitl;

            private zzi() {
            }

            /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
            public static final class zza extends zzejz.zzb<zzi, zza> implements zzell {
                private zza() {
                    super(zzi.zzitm);
                }

                public final zza zzij(String str) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzi) this.zzijh).zzig(str);
                    return this;
                }

                public final zza zzfu(long j) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzi) this.zzijh).zzft(j);
                    return this;
                }

                public final zza zzbx(boolean z) {
                    if (this.zziji) {
                        zzbgp();
                        this.zziji = false;
                    }
                    ((zzi) this.zzijh).zzbw(z);
                    return this;
                }

                /* synthetic */ zza(zzenv zzenv) {
                    this();
                }
            }

            /* access modifiers changed from: private */
            public final void zzig(String str) {
                str.getClass();
                this.zzdw |= 1;
                this.zzitj = str;
            }

            /* access modifiers changed from: private */
            public final void zzft(long j) {
                this.zzdw |= 2;
                this.zzitk = j;
            }

            /* access modifiers changed from: private */
            public final void zzbw(boolean z) {
                this.zzdw |= 4;
                this.zzitl = z;
            }

            public static zza zzbkc() {
                return (zza) zzitm.zzbgf();
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzenv.zzdv[i - 1]) {
                    case 1:
                        return new zzi();
                    case 2:
                        return new zza((zzenv) null);
                    case 3:
                        return zza((zzelj) zzitm, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဇ\u0002", new Object[]{"zzdw", "zzitj", "zzitk", "zzitl"});
                    case 4:
                        return zzitm;
                    case 5:
                        zzelw<zzi> zzelw = zzel;
                        if (zzelw == null) {
                            synchronized (zzi.class) {
                                zzelw = zzel;
                                if (zzelw == null) {
                                    zzelw = new zzejz.zza<>(zzitm);
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
                zzi zzi = new zzi();
                zzitm = zzi;
                zzejz.zza(zzi.class, zzi);
            }
        }

        /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
        public static final class zza extends zzejz.zzb<zzb, zza> implements zzell {
            private zza() {
                super(zzb.zzirj);
            }

            public final zza zza(zzg zzg) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzb(zzg);
                return this;
            }

            public final String getUrl() {
                return ((zzb) this.zzijh).getUrl();
            }

            public final zza zzhy(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).setUrl(str);
                return this;
            }

            public final zza zzhz(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzib(str);
                return this;
            }

            public final zza zza(C0015zzb zzb) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzb(zzb);
                return this;
            }

            public final List<zzh> zzbji() {
                return Collections.unmodifiableList(((zzb) this.zzijh).zzbji());
            }

            public final zza zza(zzh zzh) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzb(zzh);
                return this;
            }

            public final String zzbjj() {
                return ((zzb) this.zzijh).zzbjj();
            }

            public final zza zzia(String str) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzea(str);
                return this;
            }

            public final zza zzbjk() {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzbjl();
                return this;
            }

            public final zza zza(zzf zzf) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzb(zzf);
                return this;
            }

            public final zza zza(zzi zzi) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzb(zzi);
                return this;
            }

            public final zza zzm(Iterable<String> iterable) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzo(iterable);
                return this;
            }

            public final zza zzn(Iterable<String> iterable) {
                if (this.zziji) {
                    zzbgp();
                    this.zziji = false;
                }
                ((zzb) this.zzijh).zzp(iterable);
                return this;
            }

            /* synthetic */ zza(zzenv zzenv) {
                this();
            }
        }

        /* access modifiers changed from: private */
        public final void zzb(zzg zzg2) {
            this.zzbzr = zzg2.zzv();
            this.zzdw |= 1;
        }

        public final String getUrl() {
            return this.zziqk;
        }

        /* access modifiers changed from: private */
        public final void setUrl(String str) {
            str.getClass();
            this.zzdw |= 4;
            this.zziqk = str;
        }

        /* access modifiers changed from: private */
        public final void zzib(String str) {
            str.getClass();
            this.zzdw |= 8;
            this.zziqv = str;
        }

        /* access modifiers changed from: private */
        public final void zzb(C0015zzb zzb) {
            zzb.getClass();
            this.zziqx = zzb;
            this.zzdw |= 32;
        }

        public final List<zzh> zzbji() {
            return this.zziqy;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzh zzh2) {
            zzh2.getClass();
            zzekk<zzh> zzekk = this.zziqy;
            if (!zzekk.zzbeb()) {
                this.zziqy = zzejz.zza(zzekk);
            }
            this.zziqy.add(zzh2);
        }

        public final String zzbjj() {
            return this.zziqz;
        }

        /* access modifiers changed from: private */
        public final void zzea(String str) {
            str.getClass();
            this.zzdw |= 64;
            this.zziqz = str;
        }

        /* access modifiers changed from: private */
        public final void zzbjl() {
            this.zzdw &= -65;
            this.zziqz = zzirj.zziqz;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzf zzf2) {
            zzf2.getClass();
            this.zzira = zzf2;
            this.zzdw |= 128;
        }

        /* access modifiers changed from: private */
        public final void zzb(zzi zzi2) {
            zzi2.getClass();
            this.zzirg = zzi2;
            this.zzdw |= 8192;
        }

        /* access modifiers changed from: private */
        public final void zzo(Iterable<String> iterable) {
            zzekk<String> zzekk = this.zzirh;
            if (!zzekk.zzbeb()) {
                this.zzirh = zzejz.zza(zzekk);
            }
            zzeif.zza(iterable, this.zzirh);
        }

        /* access modifiers changed from: private */
        public final void zzp(Iterable<String> iterable) {
            zzekk<String> zzekk = this.zziri;
            if (!zzekk.zzbeb()) {
                this.zziri = zzejz.zza(zzekk);
            }
            zzeif.zza(iterable, this.zziri);
        }

        public static zza zzbjm() {
            return (zza) zzirj.zzbgf();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzenv.zzdv[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzenv) null);
                case 3:
                    return zza((zzelj) zzirj, "\u0001\u0012\u0000\u0001\u0001\u0015\u0012\u0000\u0004\u0001\u0001ဈ\u0002\u0002ဈ\u0003\u0003ဈ\u0004\u0004Л\u0005ဇ\b\u0006\u001a\u0007ဈ\t\bဇ\n\tဇ\u000b\nဌ\u0000\u000bဌ\u0001\fဉ\u0005\rဈ\u0006\u000eဉ\u0007\u000fည\f\u0011ဉ\r\u0014\u001a\u0015\u001a", new Object[]{"zzdw", "zziqk", "zziqv", "zziqw", "zziqy", zzh.class, "zzirb", "zzirc", "zzird", "zzire", "zzirf", "zzbzr", zzg.zzw(), "zziqu", zza.zzc.zzw(), "zziqx", "zziqz", "zzira", "zziqd", "zzirg", "zzirh", "zziri"});
                case 4:
                    return zzirj;
                case 5:
                    zzelw<zzb> zzelw = zzel;
                    if (zzelw == null) {
                        synchronized (zzb.class) {
                            zzelw = zzel;
                            if (zzelw == null) {
                                zzelw = new zzejz.zza<>(zzirj);
                                zzel = zzelw;
                            }
                        }
                    }
                    return zzelw;
                case 6:
                    return Byte.valueOf(this.zziqh);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zziqh = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzb zzb = new zzb();
            zzirj = zzb;
            zzejz.zza(zzb.class, zzb);
        }
    }
}
