package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzld {
    private static final Logger logger = Logger.getLogger(zzld.class.getName());
    private static final Unsafe zzaaj = zzjg();
    private static final boolean zzacj = zzk(Long.TYPE);
    private static final boolean zzack = zzk(Integer.TYPE);
    private static final zzc zzacl;
    private static final boolean zzacm = zzji();
    private static final long zzacn = ((long) zzi(byte[].class));
    private static final long zzaco;
    private static final long zzacp;
    private static final long zzacq;
    private static final long zzacr;
    private static final long zzacs;
    private static final long zzact;
    private static final long zzacu;
    private static final long zzacv;
    private static final long zzacw;
    private static final long zzacx;
    private static final long zzacy = ((long) zzi(Object[].class));
    private static final long zzacz = ((long) zzj(Object[].class));
    private static final long zzada;
    private static final int zzadb = ((int) (zzacn & 7));
    static final boolean zzadc = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Class<?> zzti = zzgl.zzem();
    private static final boolean zzun = zzjh();

    private zzld() {
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static final class zza extends zzc {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzld.zzadc) {
                return zzld.zzq(obj, j);
            }
            return zzld.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzld.zzadc) {
                zzld.zza(obj, j, b);
            } else {
                zzld.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzld.zzadc) {
                return zzld.zzs(obj, j);
            }
            return zzld.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzld.zzadc) {
                zzld.zzb(obj, j, z);
            } else {
                zzld.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static final class zzb extends zzc {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzld.zzadc) {
                return zzld.zzq(obj, j);
            }
            return zzld.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzld.zzadc) {
                zzld.zza(obj, j, b);
            } else {
                zzld.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzld.zzadc) {
                return zzld.zzs(obj, j);
            }
            return zzld.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzld.zzadc) {
                zzld.zzb(obj, j, z);
            } else {
                zzld.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static final class zzd extends zzc {
        zzd(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzadd.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzadd.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzadd.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzadd.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzadd.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzadd.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzadd.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzadd.putDouble(obj, j, d);
        }
    }

    static boolean zzje() {
        return zzun;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static abstract class zzc {
        Unsafe zzadd;

        zzc(Unsafe unsafe) {
            this.zzadd = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzadd.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzadd.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzadd.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzadd.putLong(obj, j, j2);
        }
    }

    static boolean zzjf() {
        return zzacm;
    }

    static <T> T zzh(Class<T> cls) {
        try {
            return zzaaj.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzun) {
            return zzacl.zzadd.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzun) {
            return zzacl.zzadd.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzacl.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzacl.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzacl.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzacl.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzacl.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzacl.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzacl.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzacl.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzacl.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzacl.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzacl.zzadd.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzacl.zzadd.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzacl.zzy(bArr, zzacn + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzacl.zze(bArr, zzacn + j, b);
    }

    static Unsafe zzjg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzlc());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzjh() {
        Unsafe unsafe = zzaaj;
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
            if (zzgl.zzel()) {
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

    private static boolean zzji() {
        Unsafe unsafe = zzaaj;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzjj() == null) {
                return false;
            }
            if (zzgl.zzel()) {
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

    private static boolean zzk(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzgl.zzel()) {
            return false;
        }
        try {
            Class<?> cls3 = zzti;
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

    private static Field zzjj() {
        Field zzb2;
        if (zzgl.zzel() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
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
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int zzk = zzk(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
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
        r1 = zzacl;
     */
    static {
        /*
            java.lang.Class<double[]> r0 = double[].class
            java.lang.Class<float[]> r1 = float[].class
            java.lang.Class<long[]> r2 = long[].class
            java.lang.Class<int[]> r3 = int[].class
            java.lang.Class<boolean[]> r4 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.vision.zzld> r5 = com.google.android.gms.internal.vision.zzld.class
            java.lang.String r5 = r5.getName()
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)
            logger = r5
            sun.misc.Unsafe r5 = zzjg()
            zzaaj = r5
            java.lang.Class r5 = com.google.android.gms.internal.vision.zzgl.zzem()
            zzti = r5
            java.lang.Class r5 = java.lang.Long.TYPE
            boolean r5 = zzk(r5)
            zzacj = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            boolean r5 = zzk(r5)
            zzack = r5
            sun.misc.Unsafe r5 = zzaaj
            r6 = 0
            if (r5 != 0) goto L_0x0038
            goto L_0x005d
        L_0x0038:
            boolean r5 = com.google.android.gms.internal.vision.zzgl.zzel()
            if (r5 == 0) goto L_0x0056
            boolean r5 = zzacj
            if (r5 == 0) goto L_0x004a
            com.google.android.gms.internal.vision.zzld$zza r6 = new com.google.android.gms.internal.vision.zzld$zza
            sun.misc.Unsafe r5 = zzaaj
            r6.<init>(r5)
            goto L_0x005d
        L_0x004a:
            boolean r5 = zzack
            if (r5 == 0) goto L_0x005d
            com.google.android.gms.internal.vision.zzld$zzb r6 = new com.google.android.gms.internal.vision.zzld$zzb
            sun.misc.Unsafe r5 = zzaaj
            r6.<init>(r5)
            goto L_0x005d
        L_0x0056:
            com.google.android.gms.internal.vision.zzld$zzd r6 = new com.google.android.gms.internal.vision.zzld$zzd
            sun.misc.Unsafe r5 = zzaaj
            r6.<init>(r5)
        L_0x005d:
            zzacl = r6
            boolean r5 = zzji()
            zzacm = r5
            boolean r5 = zzjh()
            zzun = r5
            java.lang.Class<byte[]> r5 = byte[].class
            int r5 = zzi(r5)
            long r5 = (long) r5
            zzacn = r5
            int r5 = zzi(r4)
            long r5 = (long) r5
            zzaco = r5
            int r4 = zzj(r4)
            long r4 = (long) r4
            zzacp = r4
            int r4 = zzi(r3)
            long r4 = (long) r4
            zzacq = r4
            int r3 = zzj(r3)
            long r3 = (long) r3
            zzacr = r3
            int r3 = zzi(r2)
            long r3 = (long) r3
            zzacs = r3
            int r2 = zzj(r2)
            long r2 = (long) r2
            zzact = r2
            int r2 = zzi(r1)
            long r2 = (long) r2
            zzacu = r2
            int r1 = zzj(r1)
            long r1 = (long) r1
            zzacv = r1
            int r1 = zzi(r0)
            long r1 = (long) r1
            zzacw = r1
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzacx = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacy = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzj(r0)
            long r0 = (long) r0
            zzacz = r0
            java.lang.reflect.Field r0 = zzjj()
            if (r0 == 0) goto L_0x00de
            com.google.android.gms.internal.vision.zzld$zzc r1 = zzacl
            if (r1 != 0) goto L_0x00d7
            goto L_0x00de
        L_0x00d7:
            sun.misc.Unsafe r1 = r1.zzadd
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00e0
        L_0x00de:
            r0 = -1
        L_0x00e0:
            zzada = r0
            long r0 = zzacn
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzadb = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f4
            r0 = 1
            goto L_0x00f5
        L_0x00f4:
            r0 = 0
        L_0x00f5:
            zzadc = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzld.<clinit>():void");
    }
}
