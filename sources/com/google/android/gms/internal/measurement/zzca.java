package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzhz;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzca {

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    public static final class zza extends zzhz<zza, C0036zza> implements zzjj {
        /* access modifiers changed from: private */
        public static final zza zzh;
        private static volatile zzju<zza> zzi;
        private int zzc;
        private String zzd = "";
        private boolean zze;
        private boolean zzf;
        private int zzg;

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzca$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public static final class C0036zza extends zzhz.zza<zza, C0036zza> implements zzjj {
            private C0036zza() {
                super(zza.zzh);
            }

            public final String zza() {
                return ((zza) this.zza).zza();
            }

            public final C0036zza zza(String str) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public final boolean zzb() {
                return ((zza) this.zza).zzb();
            }

            public final boolean zzc() {
                return ((zza) this.zza).zzc();
            }

            public final boolean zzd() {
                return ((zza) this.zza).zzd();
            }

            public final int zze() {
                return ((zza) this.zza).zze();
            }

            /* synthetic */ C0036zza(zzcc zzcc) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public final boolean zzb() {
            return this.zze;
        }

        public final boolean zzc() {
            return this.zzf;
        }

        public final boolean zzd() {
            return (this.zzc & 8) != 0;
        }

        public final int zze() {
            return this.zzg;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0036zza((zzcc) null);
                case 3:
                    return zza((zzjh) zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzju<zza> zzju = zzi;
                    if (zzju == null) {
                        synchronized (zza.class) {
                            zzju = zzi;
                            if (zzju == null) {
                                zzju = new zzhz.zzc<>(zzh);
                                zzi = zzju;
                            }
                        }
                    }
                    return zzju;
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
            zzh = zza;
            zzhz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    public static final class zzb extends zzhz<zzb, zza> implements zzjj {
        /* access modifiers changed from: private */
        public static final zzb zzm;
        private static volatile zzju<zzb> zzn;
        private int zzc;
        private long zzd;
        private String zze = "";
        private int zzf;
        private zzii<zzc> zzg = zzbr();
        private zzii<zza> zzh = zzbr();
        private zzii<zzbv.zza> zzi = zzbr();
        private String zzj = "";
        private boolean zzk;
        private zzii<zzck.zza> zzl = zzbr();

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public static final class zza extends zzhz.zza<zzb, zza> implements zzjj {
            private zza() {
                super(zzb.zzm);
            }

            public final int zza() {
                return ((zzb) this.zza).zzf();
            }

            public final zza zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final zza zza(int i, zza.C0036zza zza) {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i, (zza) ((zzhz) zza.zzz()));
                return this;
            }

            public final List<zzbv.zza> zzb() {
                return Collections.unmodifiableList(((zzb) this.zza).zzg());
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzu();
                    this.zzb = false;
                }
                ((zzb) this.zza).zzl();
                return this;
            }

            /* synthetic */ zza(zzcc zzcc) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzh.size();
        }

        public final zza zza(int i) {
            return (zza) this.zzh.get(i);
        }

        /* access modifiers changed from: private */
        public final void zza(int i, zza zza2) {
            zza2.getClass();
            zzii<zza> zzii = this.zzh;
            if (!zzii.zza()) {
                this.zzh = zzhz.zza(zzii);
            }
            this.zzh.set(i, zza2);
        }

        public final List<zzbv.zza> zzg() {
            return this.zzi;
        }

        /* access modifiers changed from: private */
        public final void zzl() {
            this.zzi = zzbr();
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public static zza zzi() {
            return (zza) zzm.zzbm();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzcc) null);
                case 3:
                    return zza((zzjh) zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0004\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzc.class, "zzh", zza.class, "zzi", zzbv.zza.class, "zzj", "zzk", "zzl", zzck.zza.class});
                case 4:
                    return zzm;
                case 5:
                    zzju<zzb> zzju = zzn;
                    if (zzju == null) {
                        synchronized (zzb.class) {
                            zzju = zzn;
                            if (zzju == null) {
                                zzju = new zzhz.zzc<>(zzm);
                                zzn = zzju;
                            }
                        }
                    }
                    return zzju;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzb zzj() {
            return zzm;
        }

        static {
            zzb zzb = new zzb();
            zzm = zzb;
            zzhz.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    public static final class zzc extends zzhz<zzc, zza> implements zzjj {
        /* access modifiers changed from: private */
        public static final zzc zzf;
        private static volatile zzju<zzc> zzg;
        private int zzc;
        private String zzd = "";
        private String zze = "";

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public static final class zza extends zzhz.zza<zzc, zza> implements zzjj {
            private zza() {
                super(zzc.zzf);
            }

            /* synthetic */ zza(zzcc zzcc) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzcc) null);
                case 3:
                    return zza((zzjh) zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzju<zzc> zzju = zzg;
                    if (zzju == null) {
                        synchronized (zzc.class) {
                            zzju = zzg;
                            if (zzju == null) {
                                zzju = new zzhz.zzc<>(zzf);
                                zzg = zzju;
                            }
                        }
                    }
                    return zzju;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzc2 = new zzc();
            zzf = zzc2;
            zzhz.zza(zzc.class, zzc2);
        }
    }
}
