package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzend {
    private static final Logger logger = Logger.getLogger(zzend.class.getName());
    private static final Unsafe zzhsa = zzbja();
    private static final Class<?> zziei = zzeii.zzbea();
    private static final boolean zzifu = zzbjb();
    private static final boolean zzinz = zzm(Long.TYPE);
    private static final boolean zzioa = zzm(Integer.TYPE);
    private static final zzd zziob;
    private static final boolean zzioc = zzbjc();
    private static final long zziod = ((long) zzk(byte[].class));
    private static final long zzioe;
    private static final long zziof;
    private static final long zziog;
    private static final long zzioh;
    private static final long zzioi;
    private static final long zzioj;
    private static final long zziok;
    private static final long zziol;
    private static final long zziom;
    private static final long zzion;
    private static final long zzioo = ((long) zzk(Object[].class));
    private static final long zziop = ((long) zzl(Object[].class));
    private static final long zzioq;
    private static final int zzior = ((int) (zziod & 7));
    static final boolean zzios = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    private zzend() {
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzend.zzios) {
                return zzend.zzq(obj, j);
            }
            return zzend.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzend.zzios) {
                zzend.zza(obj, j, b);
            } else {
                zzend.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzend.zzios) {
                return zzend.zzs(obj, j);
            }
            return zzend.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzend.zzios) {
                zzend.zzb(obj, j, z);
            } else {
                zzend.zzc(obj, j, z);
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

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            return this.zziov.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zziov.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zziov.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zziov.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zziov.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zziov.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zziov.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zziov.putDouble(obj, j, d);
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzy(Object obj, long j) {
            if (zzend.zzios) {
                return zzend.zzq(obj, j);
            }
            return zzend.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzend.zzios) {
                zzend.zza(obj, j, b);
            } else {
                zzend.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zzend.zzios) {
                return zzend.zzs(obj, j);
            }
            return zzend.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzend.zzios) {
                zzend.zzb(obj, j, z);
            } else {
                zzend.zzc(obj, j, z);
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

    static boolean zzbiy() {
        return zzifu;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static abstract class zzd {
        Unsafe zziov;

        zzd(Unsafe unsafe) {
            this.zziov = unsafe;
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
            return this.zziov.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zziov.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zziov.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zziov.putLong(obj, j, j2);
        }
    }

    static boolean zzbiz() {
        return zzioc;
    }

    static <T> T zzj(Class<T> cls) {
        try {
            return zzhsa.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzk(Class<?> cls) {
        if (zzifu) {
            return zziob.zziov.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzl(Class<?> cls) {
        if (zzifu) {
            return zziob.zziov.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zziob.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zziob.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zziob.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zziob.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zziob.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zziob.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zziob.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zziob.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zziob.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zziob.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zziob.zziov.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zziob.zziov.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zziob.zzy(bArr, zziod + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zziob.zze(bArr, zziod + j, b);
    }

    static Unsafe zzbja() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzenf());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzbjb() {
        Unsafe unsafe = zzhsa;
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
            if (zzeii.zzbdz()) {
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

    private static boolean zzbjc() {
        Unsafe unsafe = zzhsa;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzbjd() == null) {
                return false;
            }
            if (zzeii.zzbdz()) {
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

    private static boolean zzm(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzeii.zzbdz()) {
            return false;
        }
        try {
            Class<?> cls3 = zziei;
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

    private static Field zzbjd() {
        Field zzb2;
        if (zzeii.zzbdz() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
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
        r1 = zziob;
     */
    static {
        /*
            java.lang.Class<double[]> r0 = double[].class
            java.lang.Class<float[]> r1 = float[].class
            java.lang.Class<long[]> r2 = long[].class
            java.lang.Class<int[]> r3 = int[].class
            java.lang.Class<boolean[]> r4 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.ads.zzend> r5 = com.google.android.gms.internal.ads.zzend.class
            java.lang.String r5 = r5.getName()
            java.util.logging.Logger r5 = java.util.logging.Logger.getLogger(r5)
            logger = r5
            sun.misc.Unsafe r5 = zzbja()
            zzhsa = r5
            java.lang.Class r5 = com.google.android.gms.internal.ads.zzeii.zzbea()
            zziei = r5
            java.lang.Class r5 = java.lang.Long.TYPE
            boolean r5 = zzm(r5)
            zzinz = r5
            java.lang.Class r5 = java.lang.Integer.TYPE
            boolean r5 = zzm(r5)
            zzioa = r5
            sun.misc.Unsafe r5 = zzhsa
            r6 = 0
            if (r5 != 0) goto L_0x0038
            goto L_0x005d
        L_0x0038:
            boolean r5 = com.google.android.gms.internal.ads.zzeii.zzbdz()
            if (r5 == 0) goto L_0x0056
            boolean r5 = zzinz
            if (r5 == 0) goto L_0x004a
            com.google.android.gms.internal.ads.zzend$zzc r6 = new com.google.android.gms.internal.ads.zzend$zzc
            sun.misc.Unsafe r5 = zzhsa
            r6.<init>(r5)
            goto L_0x005d
        L_0x004a:
            boolean r5 = zzioa
            if (r5 == 0) goto L_0x005d
            com.google.android.gms.internal.ads.zzend$zza r6 = new com.google.android.gms.internal.ads.zzend$zza
            sun.misc.Unsafe r5 = zzhsa
            r6.<init>(r5)
            goto L_0x005d
        L_0x0056:
            com.google.android.gms.internal.ads.zzend$zzb r6 = new com.google.android.gms.internal.ads.zzend$zzb
            sun.misc.Unsafe r5 = zzhsa
            r6.<init>(r5)
        L_0x005d:
            zziob = r6
            boolean r5 = zzbjc()
            zzioc = r5
            boolean r5 = zzbjb()
            zzifu = r5
            java.lang.Class<byte[]> r5 = byte[].class
            int r5 = zzk(r5)
            long r5 = (long) r5
            zziod = r5
            int r5 = zzk(r4)
            long r5 = (long) r5
            zzioe = r5
            int r4 = zzl(r4)
            long r4 = (long) r4
            zziof = r4
            int r4 = zzk(r3)
            long r4 = (long) r4
            zziog = r4
            int r3 = zzl(r3)
            long r3 = (long) r3
            zzioh = r3
            int r3 = zzk(r2)
            long r3 = (long) r3
            zzioi = r3
            int r2 = zzl(r2)
            long r2 = (long) r2
            zzioj = r2
            int r2 = zzk(r1)
            long r2 = (long) r2
            zziok = r2
            int r1 = zzl(r1)
            long r1 = (long) r1
            zziol = r1
            int r1 = zzk(r0)
            long r1 = (long) r1
            zziom = r1
            int r0 = zzl(r0)
            long r0 = (long) r0
            zzion = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzk(r0)
            long r0 = (long) r0
            zzioo = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzl(r0)
            long r0 = (long) r0
            zziop = r0
            java.lang.reflect.Field r0 = zzbjd()
            if (r0 == 0) goto L_0x00de
            com.google.android.gms.internal.ads.zzend$zzd r1 = zziob
            if (r1 != 0) goto L_0x00d7
            goto L_0x00de
        L_0x00d7:
            sun.misc.Unsafe r1 = r1.zziov
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00e0
        L_0x00de:
            r0 = -1
        L_0x00e0:
            zzioq = r0
            long r0 = zziod
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzior = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00f4
            r0 = 1
            goto L_0x00f5
        L_0x00f4:
            r0 = 0
        L_0x00f5:
            zzios = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzend.<clinit>():void");
    }
}
