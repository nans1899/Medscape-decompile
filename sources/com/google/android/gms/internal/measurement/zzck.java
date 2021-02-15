package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhz;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzck {

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    public static final class zza extends zzhz<zza, C0038zza> implements zzjj {
        /* access modifiers changed from: private */
        public static final zza zzd;
        private static volatile zzju<zza> zze;
        private zzii<zzb> zzc = zzbr();

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzck$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public static final class C0038zza extends zzhz.zza<zza, C0038zza> implements zzjj {
            private C0038zza() {
                super(zza.zzd);
            }

            /* synthetic */ C0038zza(zzcl zzcl) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcl.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0038zza((zzcl) null);
                case 3:
                    return zza((zzjh) zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzb.class});
                case 4:
                    return zzd;
                case 5:
                    zzju<zza> zzju = zze;
                    if (zzju == null) {
                        synchronized (zza.class) {
                            zzju = zze;
                            if (zzju == null) {
                                zzju = new zzhz.zzc<>(zzd);
                                zze = zzju;
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
            zzd = zza;
            zzhz.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
    public static final class zzb extends zzhz<zzb, C0039zzb> implements zzjj {
        /* access modifiers changed from: private */
        public static final zzb zzk;
        private static volatile zzju<zzb> zzl;
        private int zzc;
        private int zzd;
        private String zze = "";
        private boolean zzf;
        private long zzg;
        private double zzh;
        private zzii<zzb> zzi = zzbr();
        private String zzj = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public enum zza implements zzib {
            UNDEFINED(0),
            NULL(1),
            STRING(2),
            NUMBER(3),
            BOOLEAN(4),
            LIST(5),
            MAP(6),
            STATEMENT(7);
            
            private static final zzie<zza> zzi = null;
            private final int zzj;

            public final int zza() {
                return this.zzj;
            }

            public static zza zza(int i) {
                switch (i) {
                    case 0:
                        return UNDEFINED;
                    case 1:
                        return NULL;
                    case 2:
                        return STRING;
                    case 3:
                        return NUMBER;
                    case 4:
                        return BOOLEAN;
                    case 5:
                        return LIST;
                    case 6:
                        return MAP;
                    case 7:
                        return STATEMENT;
                    default:
                        return null;
                }
            }

            public static zzid zzb() {
                return zzcm.zza;
            }

            public final String toString() {
                return HtmlObject.HtmlMarkUp.OPEN_BRACKER + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzj + " name=" + name() + '>';
            }

            private zza(int i) {
                this.zzj = i;
            }

            static {
                zzi = new zzcn();
            }
        }

        private zzb() {
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzck$zzb$zzb  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
        public static final class C0039zzb extends zzhz.zza<zzb, C0039zzb> implements zzjj {
            private C0039zzb() {
                super(zzb.zzk);
            }

            /* synthetic */ C0039zzb(zzcl zzcl) {
                this();
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            Class<zzb> cls = zzb.class;
            switch (zzcl.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0039zzb((zzcl) null);
                case 3:
                    return zza((zzjh) zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဂ\u0003\u0005က\u0004\u0006\u001b\u0007ဈ\u0005", new Object[]{"zzc", "zzd", zza.zzb(), "zze", "zzf", "zzg", "zzh", "zzi", cls, "zzj"});
                case 4:
                    return zzk;
                case 5:
                    zzju<zzb> zzju = zzl;
                    if (zzju == null) {
                        synchronized (cls) {
                            zzju = zzl;
                            if (zzju == null) {
                                zzju = new zzhz.zzc<>(zzk);
                                zzl = zzju;
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
            zzb zzb = new zzb();
            zzk = zzb;
            zzhz.zza(zzb.class, zzb);
        }
    }
}
