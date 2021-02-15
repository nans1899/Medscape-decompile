package com.google.android.gms.internal.icing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzgs {
    private static final Logger logger = Logger.getLogger(zzgs.class.getName());
    private static final Class<?> zzgg = zzcs.zzam();
    private static final boolean zzgx = zzdq();
    private static final Unsafe zzms = zzdp();
    private static final boolean zzol = zzj(Long.TYPE);
    private static final boolean zzom = zzj(Integer.TYPE);
    private static final zzd zzon;
    private static final boolean zzoo = zzdr();
    private static final long zzop = ((long) zzh(byte[].class));
    private static final long zzoq;
    private static final long zzor;
    private static final long zzos;
    private static final long zzot;
    private static final long zzou;
    private static final long zzov;
    private static final long zzow;
    private static final long zzox;
    private static final long zzoy;
    private static final long zzoz;
    private static final long zzpa = ((long) zzh(Object[].class));
    private static final long zzpb = ((long) zzi(Object[].class));
    private static final long zzpc;
    private static final int zzpd = ((int) (zzop & 7));
    static final boolean zzpe = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    private zzgs() {
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgs.zzpe) {
                return zzgs.zzp(obj, j);
            }
            return zzgs.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgs.zzpe) {
                zzgs.zza(obj, j, b);
            } else {
                zzgs.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgs.zzpe) {
                return zzgs.zzr(obj, j);
            }
            return zzgs.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgs.zzpe) {
                zzgs.zzb(obj, j, z);
            } else {
                zzgs.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzph.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzph.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzph.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzph.putBoolean(obj, j, z);
        }

        public final float zzm(Object obj, long j) {
            return this.zzph.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzph.putFloat(obj, j, f);
        }

        public final double zzn(Object obj, long j) {
            return this.zzph.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzph.putDouble(obj, j, d);
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgs.zzpe) {
                return zzgs.zzp(obj, j);
            }
            return zzgs.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgs.zzpe) {
                zzgs.zza(obj, j, b);
            } else {
                zzgs.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgs.zzpe) {
                return zzgs.zzr(obj, j);
            }
            return zzgs.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgs.zzpe) {
                zzgs.zzb(obj, j, z);
            } else {
                zzgs.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static boolean zzdn() {
        return zzgx;
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static abstract class zzd {
        Unsafe zzph;

        zzd(Unsafe unsafe) {
            this.zzph = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);

        public final int zzj(Object obj, long j) {
            return this.zzph.getInt(obj, j);
        }

        public final void zza(Object obj, long j, int i) {
            this.zzph.putInt(obj, j, i);
        }

        public final long zzk(Object obj, long j) {
            return this.zzph.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzph.putLong(obj, j, j2);
        }
    }

    static boolean zzdo() {
        return zzoo;
    }

    static <T> T zzg(Class<T> cls) {
        try {
            return zzms.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzh(Class<?> cls) {
        if (zzgx) {
            return zzon.zzph.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzi(Class<?> cls) {
        if (zzgx) {
            return zzon.zzph.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzj(Object obj, long j) {
        return zzon.zzj(obj, j);
    }

    static void zza(Object obj, long j, int i) {
        zzon.zza(obj, j, i);
    }

    static long zzk(Object obj, long j) {
        return zzon.zzk(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzon.zza(obj, j, j2);
    }

    static boolean zzl(Object obj, long j) {
        return zzon.zzl(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzon.zza(obj, j, z);
    }

    static float zzm(Object obj, long j) {
        return zzon.zzm(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzon.zza(obj, j, f);
    }

    static double zzn(Object obj, long j) {
        return zzon.zzn(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzon.zza(obj, j, d);
    }

    static Object zzo(Object obj, long j) {
        return zzon.zzph.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzon.zzph.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzon.zzx(bArr, zzop + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzon.zze(bArr, zzop + j, b);
    }

    static Unsafe zzdp() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzgu());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdq() {
        Unsafe unsafe = zzms;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzcs.zzal()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzdr() {
        Unsafe unsafe = zzms;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzds() == null) {
                return false;
            }
            if (zzcs.zzal()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzj(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzcs.zzal()) {
            return false;
        }
        try {
            Class<?> cls3 = zzgg;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzds() {
        Field zzb2;
        if (zzcs.zzal() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int zzj = zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00d2, code lost:
        r1 = zzon;
     */
    static {
        /*
            java.lang.Class<double[]> r0 = double[].class
            java.lang.Class<float[]> r1 = float[].class
            java.lang.Class<long[]> r2 = long[].class
            java.lang.Class<int[]> r3 = int[].class
            java.lang.Class<boolean[]> r4 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.icing.zzgs> r5 = com.google.android.gms.internal.icing.zzgs.class
            java.lang.String r5 = r5.getName()
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)
            logger = r5
            sun.misc.Unsafe r5 = zzdp()
            zzms = r5
            java.lang.Class r5 = com.google.android.gms.internal.icing.zzcs.zzam()
            zzgg = r5
            java.lang.Class r5 = java.lang.Long.TYPE
            boolean r5 = zzj(r5)
            zzol = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            boolean r5 = zzj(r5)
            zzom = r5
            sun.misc.Unsafe r5 = zzms
            r6 = 0
            if (r5 != 0) goto L_0x0038
            goto L_0x005d
        L_0x0038:
            boolean r5 = com.google.android.gms.internal.icing.zzcs.zzal()
            if (r5 == 0) goto L_0x0056
            boolean r5 = zzol
            if (r5 == 0) goto L_0x004a
            com.google.android.gms.internal.icing.zzgs$zzc r6 = new com.google.android.gms.internal.icing.zzgs$zzc
            sun.misc.Unsafe r5 = zzms
            r6.<init>(r5)
            goto L_0x005d
        L_0x004a:
            boolean r5 = zzom
            if (r5 == 0) goto L_0x005d
            com.google.android.gms.internal.icing.zzgs$zza r6 = new com.google.android.gms.internal.icing.zzgs$zza
            sun.misc.Unsafe r5 = zzms
            r6.<init>(r5)
            goto L_0x005d
        L_0x0056:
            com.google.android.gms.internal.icing.zzgs$zzb r6 = new com.google.android.gms.internal.icing.zzgs$zzb
            sun.misc.Unsafe r5 = zzms
            r6.<init>(r5)
        L_0x005d:
            zzon = r6
            boolean r5 = zzdr()
            zzoo = r5
            boolean r5 = zzdq()
            zzgx = r5
            java.lang.Class<byte[]> r5 = byte[].class
            int r5 = zzh(r5)
            long r5 = (long) r5
            zzop = r5
            int r5 = zzh(r4)
            long r5 = (long) r5
            zzoq = r5
            int r4 = zzi(r4)
            long r4 = (long) r4
            zzor = r4
            int r4 = zzh(r3)
            long r4 = (long) r4
            zzos = r4
            int r3 = zzi(r3)
            long r3 = (long) r3
            zzot = r3
            int r3 = zzh(r2)
            long r3 = (long) r3
            zzou = r3
            int r2 = zzi(r2)
            long r2 = (long) r2
            zzov = r2
            int r2 = zzh(r1)
            long r2 = (long) r2
            zzow = r2
            int r1 = zzi(r1)
            long r1 = (long) r1
            zzox = r1
            int r1 = zzh(r0)
            long r1 = (long) r1
            zzoy = r1
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzoz = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzpa = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzpb = r0
            java.lang.reflect.Field r0 = zzds()
            if (r0 == 0) goto L_0x00de
            com.google.android.gms.internal.icing.zzgs$zzd r1 = zzon
            if (r1 != 0) goto L_0x00d7
            goto L_0x00de
        L_0x00d7:
            sun.misc.Unsafe r1 = r1.zzph
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00e0
        L_0x00de:
            r0 = -1
        L_0x00e0:
            zzpc = r0
            long r0 = zzop
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzpd = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f4
            r0 = 1
            goto L_0x00f5
        L_0x00f4:
            r0 = 0
        L_0x00f5:
            zzpe = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzgs.<clinit>():void");
    }
}
