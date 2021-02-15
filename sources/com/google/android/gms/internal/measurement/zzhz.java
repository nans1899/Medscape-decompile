package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhz;
import com.google.android.gms.internal.measurement.zzhz.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public abstract class zzhz<MessageType extends zzhz<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzgh<MessageType, BuilderType> {
    private static Map<Object, zzhz<?, ?>> zzd = new ConcurrentHashMap();
    protected zzku zzb = zzku.zza();
    private int zzc = -1;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    protected static class zzc<T extends zzhz<T, ?>> extends zzgi<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    public static class zzd<ContainingType extends zzjh, Type> extends zzhk<ContainingType, Type> {
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    static final class zze implements zzhr<zze> {
        public final int zza() {
            throw new NoSuchMethodError();
        }

        public final zzli zzb() {
            throw new NoSuchMethodError();
        }

        public final zzll zzc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        public final zzjk zza(zzjk zzjk, zzjh zzjh) {
            throw new NoSuchMethodError();
        }

        public final zzjq zza(zzjq zzjq, zzjq zzjq2) {
            throw new NoSuchMethodError();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    public static final class zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzh = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zza() {
            return (int[]) zzh.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzhz<MessageType, BuilderType> implements zzjj {
        protected zzhp<zze> zzc = zzhp.zza();

        /* access modifiers changed from: package-private */
        public final zzhp<zze> zza() {
            if (this.zzc.zzc()) {
                this.zzc = (zzhp) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public String toString() {
        return zzjm.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzjv.zza().zza(this).zza(this);
        return this.zza;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    public static abstract class zza<MessageType extends zzhz<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzgg<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        protected zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (zzhz) messagetype.zza(zzf.zzd, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzu() {
            MessageType messagetype = (zzhz) this.zza.zza(zzf.zzd, (Object) null, (Object) null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        public final boolean i_() {
            return zzhz.zza(this.zza, false);
        }

        /* renamed from: zzw */
        public MessageType zzy() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzjv.zza().zza(messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        /* renamed from: zzx */
        public final MessageType zzz() {
            MessageType messagetype = (zzhz) zzy();
            if (messagetype.i_()) {
                return messagetype;
            }
            throw new zzks(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjv.zza().zza(messagetype).zzb(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzhm zzhm) throws zzih {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            try {
                zzjv.zza().zza(this.zza).zza(this.zza, bArr, 0, i2, new zzgm(zzhm));
                return this;
            } catch (zzih e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw zzih.zza();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzhd zzhd, zzhm zzhm) throws IOException {
            if (this.zzb) {
                zzu();
                this.zzb = false;
            }
            try {
                zzjv.zza().zza(this.zza).zza(this.zza, zzhe.zza(zzhd), zzhm);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final /* synthetic */ zzgg zza(byte[] bArr, int i, int i2, zzhm zzhm) throws zzih {
            return zzb(bArr, 0, i2, zzhm);
        }

        public final /* synthetic */ zzgg zza(byte[] bArr, int i, int i2) throws zzih {
            return zzb(bArr, 0, i2, zzhm.zza());
        }

        public final /* synthetic */ zzgg zzt() {
            return (zza) clone();
        }

        public final /* synthetic */ zzjh zzaa() {
            return this.zzc;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza2 = (zza) ((zzhz) this.zzc).zza(zzf.zze, (Object) null, (Object) null);
            zza2.zza((zzhz) zzy());
            return zza2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzjv.zza().zza(this).zza(this, (zzhz) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzhz<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzbm() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    public final boolean i_() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    public final BuilderType zzbn() {
        BuilderType buildertype = (zza) zza(zzf.zze, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    /* access modifiers changed from: package-private */
    public final int zzbl() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i) {
        this.zzc = i;
    }

    public final void zza(zzhg zzhg) throws IOException {
        zzjv.zza().zza(this).zza(this, (zzlo) zzhj.zza(zzhg));
    }

    public final int zzbo() {
        if (this.zzc == -1) {
            this.zzc = zzjv.zza().zza(this).zzb(this);
        }
        return this.zzc;
    }

    static <T extends zzhz<?, ?>> T zza(Class<T> cls) {
        T t = (zzhz) zzd.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzhz) zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzhz) ((zzhz) zzkx.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null);
            if (t != null) {
                zzd.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzhz<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    protected static Object zza(zzjh zzjh, String str, Object[] objArr) {
        return new zzjx(zzjh, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzhz<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zza, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzd2 = zzjv.zza().zza(t).zzd(t);
        if (z) {
            t.zza(zzf.zzb, (Object) zzd2 ? t : null, (Object) null);
        }
        return zzd2;
    }

    protected static zzig zzbp() {
        return zzia.zzd();
    }

    protected static zzif zzbq() {
        return zziv.zzd();
    }

    protected static zzif zza(zzif zzif) {
        int size = zzif.size();
        return zzif.zzc(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzii<E> zzbr() {
        return zzjy.zzd();
    }

    protected static <E> zzii<E> zza(zzii<E> zzii) {
        int size = zzii.size();
        return zzii.zza(size == 0 ? 10 : size << 1);
    }

    public final /* synthetic */ zzjk zzbs() {
        zza zza2 = (zza) zza(zzf.zze, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzjk zzbt() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzjh zzaa() {
        return (zzhz) zza(zzf.zzf, (Object) null, (Object) null);
    }
}
