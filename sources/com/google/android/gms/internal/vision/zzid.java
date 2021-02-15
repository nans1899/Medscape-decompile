package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzid;
import com.google.android.gms.internal.vision.zzid.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzid<MessageType extends zzid<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzge<MessageType, BuilderType> {
    private static Map<Object, zzid<?, ?>> zzyb = new ConcurrentHashMap();
    protected zzkw zzxz = zzkw.zzja();
    private int zzya = -1;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    protected static class zzc<T extends zzid<T, ?>> extends zzgj<T> {
        private final T zzxw;

        public zzc(T t) {
            this.zzxw = t;
        }
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static final class zzf {
        public static final int zzyh = 1;
        public static final int zzyi = 2;
        public static final int zzyj = 3;
        public static final int zzyk = 4;
        public static final int zzyl = 5;
        public static final int zzym = 6;
        public static final int zzyn = 7;
        private static final /* synthetic */ int[] zzyo = {1, 2, 3, 4, 5, 6, 7};

        public static int[] zzhf() {
            return (int[]) zzyo.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static abstract class zzb<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzjp {
        protected zzb(MessageType messagetype) {
            super(messagetype);
        }

        /* access modifiers changed from: protected */
        public void zzgs() {
            super.zzgs();
            ((zze) this.zzxx).zzyg = (zzht) ((zze) this.zzxx).zzyg.clone();
        }

        public /* synthetic */ zzid zzgt() {
            return (zze) zzgv();
        }

        public /* synthetic */ zzjn zzgv() {
            if (this.zzxy) {
                return (zze) this.zzxx;
            }
            ((zze) this.zzxx).zzyg.zzej();
            return (zze) super.zzgv();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static abstract class zze<MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzid<MessageType, BuilderType> implements zzjp {
        protected zzht<zzd> zzyg = zzht.zzgh();

        /* access modifiers changed from: package-private */
        public final zzht<zzd> zzhe() {
            if (this.zzyg.isImmutable()) {
                this.zzyg = (zzht) this.zzyg.clone();
            }
            return this.zzyg;
        }

        public final <Type> Type zzc(zzhp<MessageType, Type> zzhp) {
            zzg zzb = zzid.zza(zzhp);
            if (zzb.zzyp == ((zzid) zzgx())) {
                Type zza = this.zzyg.zza(zzb.zzyr);
                if (zza == null) {
                    return zzb.zzgk;
                }
                if (!zzb.zzyr.zzye) {
                    return zzb.zzl(zza);
                }
                if (zzb.zzyr.zzyd.zzjk() != zzlr.ENUM) {
                    return zza;
                }
                Type arrayList = new ArrayList();
                for (Object zzl : (List) zza) {
                    arrayList.add(zzb.zzl(zzl));
                }
                return arrayList;
            }
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    public String toString() {
        return zzjo.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzte != 0) {
            return this.zzte;
        }
        this.zzte = zzkb.zzik().zzx(this).hashCode(this);
        return this.zzte;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static abstract class zza<MessageType extends zzid<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzgh<MessageType, BuilderType> {
        private final MessageType zzxw;
        protected MessageType zzxx;
        protected boolean zzxy = false;

        protected zza(MessageType messagetype) {
            this.zzxw = messagetype;
            this.zzxx = (zzid) messagetype.zza(zzf.zzyk, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzgs() {
            MessageType messagetype = (zzid) this.zzxx.zza(zzf.zzyk, (Object) null, (Object) null);
            zza(messagetype, this.zzxx);
            this.zzxx = messagetype;
        }

        public final boolean isInitialized() {
            return zzid.zza(this.zzxx, false);
        }

        /* renamed from: zzgt */
        public MessageType zzgv() {
            if (this.zzxy) {
                return this.zzxx;
            }
            MessageType messagetype = this.zzxx;
            zzkb.zzik().zzx(messagetype).zzj(messagetype);
            this.zzxy = true;
            return this.zzxx;
        }

        /* renamed from: zzgu */
        public final MessageType zzgw() {
            MessageType messagetype = (zzid) zzgv();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzku(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzxy) {
                zzgs();
                this.zzxy = false;
            }
            zza(this.zzxx, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzkb.zzik().zzx(messagetype).zzd(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzho zzho) throws zzin {
            if (this.zzxy) {
                zzgs();
                this.zzxy = false;
            }
            try {
                zzkb.zzik().zzx(this.zzxx).zza(this.zzxx, bArr, 0, i2, new zzgm(zzho));
                return this;
            } catch (zzin e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw zzin.zzhh();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzhe zzhe, zzho zzho) throws IOException {
            if (this.zzxy) {
                zzgs();
                this.zzxy = false;
            }
            try {
                zzkb.zzik().zzx(this.zzxx).zza(this.zzxx, zzhj.zza(zzhe), zzho);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final /* synthetic */ zzgh zza(byte[] bArr, int i, int i2, zzho zzho) throws zzin {
            return zzb(bArr, 0, i2, zzho);
        }

        public final /* synthetic */ zzgh zzeh() {
            return (zza) clone();
        }

        public final /* synthetic */ zzjn zzgx() {
            return this.zzxw;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) ((zzid) this.zzxw).zza(zzf.zzyl, (Object) null, (Object) null);
            zza.zza((zzid) zzgv());
            return zza;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    static final class zzd implements zzhv<zzd> {
        final int number = 202056002;
        final zzig<?> zzyc = null;
        final zzlk zzyd;
        final boolean zzye;
        final boolean zzyf;

        zzd(zzig<?> zzig, int i, zzlk zzlk, boolean z, boolean z2) {
            this.zzyd = zzlk;
            this.zzye = true;
            this.zzyf = false;
        }

        public final boolean zzgp() {
            return false;
        }

        public final int zzak() {
            return this.number;
        }

        public final zzlk zzgm() {
            return this.zzyd;
        }

        public final zzlr zzgn() {
            return this.zzyd.zzjk();
        }

        public final boolean zzgo() {
            return this.zzye;
        }

        public final zzjm zza(zzjm zzjm, zzjn zzjn) {
            return ((zza) zzjm).zza((zzid) zzjn);
        }

        public final zzjs zza(zzjs zzjs, zzjs zzjs2) {
            throw new UnsupportedOperationException();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.number - ((zzd) obj).number;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class zzg<ContainingType extends zzjn, Type> extends zzhp<ContainingType, Type> {
        final Type zzgk;
        final ContainingType zzyp;
        final zzjn zzyq;
        final zzd zzyr;

        zzg(ContainingType containingtype, Type type, zzjn zzjn, zzd zzd, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (zzd.zzyd == zzlk.MESSAGE && zzjn == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.zzyp = containingtype;
                this.zzgk = type;
                this.zzyq = zzjn;
                this.zzyr = zzd;
            }
        }

        /* access modifiers changed from: package-private */
        public final Object zzl(Object obj) {
            if (this.zzyr.zzyd.zzjk() != zzlr.ENUM) {
                return obj;
            }
            zzig zzig = null;
            return zzig.zzh(((Integer) obj).intValue());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzkb.zzik().zzx(this).equals(this, (zzid) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzid<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzgy() {
        return (zza) zza(zzf.zzyl, (Object) null, (Object) null);
    }

    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: package-private */
    public final int zzef() {
        return this.zzya;
    }

    /* access modifiers changed from: package-private */
    public final void zzak(int i) {
        this.zzya = i;
    }

    public final void zzb(zzhl zzhl) throws IOException {
        zzkb.zzik().zzx(this).zza(this, zzhn.zza(zzhl));
    }

    public final int zzgz() {
        if (this.zzya == -1) {
            this.zzya = zzkb.zzik().zzx(this).zzu(this);
        }
        return this.zzya;
    }

    static <T extends zzid<?, ?>> T zzd(Class<T> cls) {
        T t = (zzid) zzyb.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzid) zzyb.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzid) ((zzid) zzld.zzh(cls)).zza(zzf.zzym, (Object) null, (Object) null);
            if (t != null) {
                zzyb.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzid<?, ?>> void zza(Class<T> cls, T t) {
        zzyb.put(cls, t);
    }

    protected static Object zza(zzjn zzjn, String str, Object[] objArr) {
        return new zzkd(zzjn, str, objArr);
    }

    public static <ContainingType extends zzjn, Type> zzg<ContainingType, Type> zza(ContainingType containingtype, zzjn zzjn, zzig<?> zzig, int i, zzlk zzlk, boolean z, Class cls) {
        return new zzg(containingtype, Collections.emptyList(), zzjn, new zzd((zzig<?>) null, 202056002, zzlk, true, false), cls);
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

    /* access modifiers changed from: private */
    public static <MessageType extends zze<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>, T> zzg<MessageType, T> zza(zzhp<MessageType, T> zzhp) {
        return (zzg) zzhp;
    }

    protected static final <T extends zzid<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zzyh, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzw = zzkb.zzik().zzx(t).zzw(t);
        if (z) {
            t.zza(zzf.zzyi, (Object) zzw ? t : null, (Object) null);
        }
        return zzw;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.vision.zzii, com.google.android.gms.internal.vision.zzif] */
    protected static zzii zzha() {
        return zzif.zzhg();
    }

    protected static <E> zzik<E> zzhb() {
        return zzka.zzij();
    }

    protected static <E> zzik<E> zza(zzik<E> zzik) {
        int size = zzik.size();
        return zzik.zzan(size == 0 ? 10 : size << 1);
    }

    private static <T extends zzid<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzho zzho) throws zzin {
        T t2 = (zzid) t.zza(zzf.zzyk, (Object) null, (Object) null);
        try {
            zzkf zzx = zzkb.zzik().zzx(t2);
            zzx.zza(t2, bArr, 0, i2, new zzgm(zzho));
            zzx.zzj(t2);
            if (t2.zzte == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzin) {
                throw ((zzin) e.getCause());
            }
            throw new zzin(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzin.zzhh().zzg(t2);
        }
    }

    private static <T extends zzid<T, ?>> T zzb(T t) throws zzin {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzin(new zzku(t).getMessage()).zzg(t);
    }

    protected static <T extends zzid<T, ?>> T zza(T t, byte[] bArr) throws zzin {
        return zzb(zza(t, bArr, 0, bArr.length, zzho.zzgf()));
    }

    protected static <T extends zzid<T, ?>> T zza(T t, byte[] bArr, zzho zzho) throws zzin {
        return zzb(zza(t, bArr, 0, bArr.length, zzho));
    }

    public final /* synthetic */ zzjm zzhc() {
        zza zza2 = (zza) zza(zzf.zzyl, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzjm zzhd() {
        return (zza) zza(zzf.zzyl, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzjn zzgx() {
        return (zzid) zza(zzf.zzym, (Object) null, (Object) null);
    }
}
