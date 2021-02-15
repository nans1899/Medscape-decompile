package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import com.google.android.gms.internal.ads.zzejz.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzejz<MessageType extends zzejz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzeif<MessageType, BuilderType> {
    private static Map<Object, zzejz<?, ?>> zzije = new ConcurrentHashMap();
    protected zzena zzijc = zzena.zzbiv();
    private int zzijd = -1;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static class zza<T extends zzejz<T, ?>> extends zzeig<T> {
        private final T zzijg;

        public zza(T t) {
            this.zzijg = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zzc implements zzeju<zzc> {
        public final int zzv() {
            throw new NoSuchMethodError();
        }

        public final zzeno zzbga() {
            throw new NoSuchMethodError();
        }

        public final zzenr zzbgb() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbgc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbgd() {
            throw new NoSuchMethodError();
        }

        public final zzelm zza(zzelm zzelm, zzelj zzelj) {
            throw new NoSuchMethodError();
        }

        public final zzels zza(zzels zzels, zzels zzels2) {
            throw new NoSuchMethodError();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class zze {
        public static final int zzijk = 1;
        public static final int zzijl = 2;
        public static final int zzijm = 3;
        public static final int zzijn = 4;
        public static final int zzijo = 5;
        public static final int zzijp = 6;
        public static final int zzijq = 7;
        private static final /* synthetic */ int[] zzijr = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzijs = 1;
        public static final int zzijt = 2;
        private static final /* synthetic */ int[] zziju = {1, 2};
        public static final int zzijv = 1;
        public static final int zzijw = 2;
        private static final /* synthetic */ int[] zzijx = {1, 2};

        public static int[] zzbgv() {
            return (int[]) zzijr.clone();
        }
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static class zzf<ContainingType extends zzelj, Type> extends zzejn<ContainingType, Type> {
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzejz<MessageType, BuilderType> implements zzell {
        protected zzejs<zzc> zzijj = zzejs.zzbfy();

        /* access modifiers changed from: package-private */
        public final zzejs<zzc> zzbgu() {
            if (this.zzijj.isImmutable()) {
                this.zzijj = (zzejs) this.zzijj.clone();
            }
            return this.zzijj;
        }
    }

    public String toString() {
        return zzelo.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zziee != 0) {
            return this.zziee;
        }
        this.zziee = zzely.zzbic().zzaw(this).hashCode(this);
        return this.zziee;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class zzb<MessageType extends zzejz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzeie<MessageType, BuilderType> {
        private final MessageType zzijg;
        protected MessageType zzijh;
        protected boolean zziji = false;

        protected zzb(MessageType messagetype) {
            this.zzijg = messagetype;
            this.zzijh = (zzejz) messagetype.zza(zze.zzijn, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzbgp() {
            MessageType messagetype = (zzejz) this.zzijh.zza(zze.zzijn, (Object) null, (Object) null);
            zza(messagetype, this.zzijh);
            this.zzijh = messagetype;
        }

        public final boolean isInitialized() {
            return zzejz.zza(this.zzijh, false);
        }

        /* renamed from: zzbgq */
        public MessageType zzbgs() {
            if (this.zziji) {
                return this.zzijh;
            }
            MessageType messagetype = this.zzijh;
            zzely.zzbic().zzaw(messagetype).zzaj(messagetype);
            this.zziji = true;
            return this.zzijh;
        }

        /* renamed from: zzbgr */
        public final MessageType zzbgt() {
            MessageType messagetype = (zzejz) zzbgs();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzemy(messagetype);
        }

        /* renamed from: zzb */
        public final BuilderType zza(MessageType messagetype) {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            zza(this.zzijh, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzely.zzbic().zzaw(messagetype).zzg(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzejm zzejm) throws zzekj {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            try {
                zzely.zzbic().zzaw(this.zzijh).zza(this.zzijh, bArr, 0, i2 + 0, new zzeik(zzejm));
                return this;
            } catch (zzekj e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw zzekj.zzbgx();
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzeja zzeja, zzejm zzejm) throws IOException {
            if (this.zziji) {
                zzbgp();
                this.zziji = false;
            }
            try {
                zzely.zzbic().zzaw(this.zzijh).zza(this.zzijh, zzejh.zza(zzeja), zzejm);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final /* synthetic */ zzeie zza(byte[] bArr, int i, int i2, zzejm zzejm) throws zzekj {
            return zzb(bArr, 0, i2, zzejm);
        }

        public final /* synthetic */ zzeie zzbdv() {
            return (zzb) clone();
        }

        public final /* synthetic */ zzelj zzbgn() {
            return this.zzijg;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzb = (zzb) ((zzejz) this.zzijg).zza(zze.zzijo, (Object) null, (Object) null);
            zzb.zza((zzejz) zzbgs());
            return zzb;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzely.zzbic().zzaw(this).equals(this, (zzejz) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzejz<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzbgf() {
        return (zzb) zza(zze.zzijo, (Object) null, (Object) null);
    }

    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    public final BuilderType zzbgg() {
        BuilderType buildertype = (zzb) zza(zze.zzijo, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    /* access modifiers changed from: package-private */
    public final int zzbdx() {
        return this.zzijd;
    }

    /* access modifiers changed from: package-private */
    public final void zzfq(int i) {
        this.zzijd = i;
    }

    public final void zzb(zzejj zzejj) throws IOException {
        zzely.zzbic().zzaw(this).zza(this, zzejl.zza(zzejj));
    }

    public final int zzbgh() {
        if (this.zzijd == -1) {
            this.zzijd = zzely.zzbic().zzaw(this).zzat(this);
        }
        return this.zzijd;
    }

    static <T extends zzejz<?, ?>> T zzd(Class<T> cls) {
        T t = (zzejz) zzije.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzejz) zzije.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzejz) ((zzejz) zzend.zzj(cls)).zza(zze.zzijp, (Object) null, (Object) null);
            if (t != null) {
                zzije.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzejz<?, ?>> void zza(Class<T> cls, T t) {
        zzije.put(cls, t);
    }

    protected static Object zza(zzelj zzelj, String str, Object[] objArr) {
        return new zzema(zzelj, str, objArr);
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

    protected static final <T extends zzejz<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzijk, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzav = zzely.zzbic().zzaw(t).zzav(t);
        if (z) {
            t.zza(zze.zzijl, (Object) zzav ? t : null, (Object) null);
        }
        return zzav;
    }

    protected static zzekf zzbgi() {
        return zzekc.zzbgw();
    }

    protected static zzekf zza(zzekf zzekf) {
        int size = zzekf.size();
        return zzekf.zzhb(size == 0 ? 10 : size << 1);
    }

    protected static zzekh zzbgj() {
        return zzekx.zzbhn();
    }

    protected static <E> zzekk<E> zzbgk() {
        return zzelx.zzbib();
    }

    protected static <E> zzekk<E> zza(zzekk<E> zzekk) {
        int size = zzekk.size();
        return zzekk.zzft(size == 0 ? 10 : size << 1);
    }

    private static <T extends zzejz<T, ?>> T zza(T t, zzeja zzeja, zzejm zzejm) throws zzekj {
        T t2 = (zzejz) t.zza(zze.zzijn, (Object) null, (Object) null);
        try {
            zzemf zzaw = zzely.zzbic().zzaw(t2);
            zzaw.zza(t2, zzejh.zza(zzeja), zzejm);
            zzaw.zzaj(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzekj) {
                throw ((zzekj) e.getCause());
            }
            throw new zzekj(e.getMessage()).zzl(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzekj) {
                throw ((zzekj) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzejz<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzejm zzejm) throws zzekj {
        T t2 = (zzejz) t.zza(zze.zzijn, (Object) null, (Object) null);
        try {
            zzemf zzaw = zzely.zzbic().zzaw(t2);
            zzaw.zza(t2, bArr, 0, i2, new zzeik(zzejm));
            zzaw.zzaj(t2);
            if (t2.zziee == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzekj) {
                throw ((zzekj) e.getCause());
            }
            throw new zzekj(e.getMessage()).zzl(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzekj.zzbgx().zzl(t2);
        }
    }

    private static <T extends zzejz<T, ?>> T zza(T t) throws zzekj {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzekj(new zzemy(t).getMessage()).zzl(t);
    }

    protected static <T extends zzejz<T, ?>> T zza(T t, zzeip zzeip) throws zzekj {
        return zza(zza(zzb(t, zzeip, zzejm.zzbft())));
    }

    protected static <T extends zzejz<T, ?>> T zza(T t, zzeip zzeip, zzejm zzejm) throws zzekj {
        return zza(zzb(t, zzeip, zzejm));
    }

    private static <T extends zzejz<T, ?>> T zzb(T t, zzeip zzeip, zzejm zzejm) throws zzekj {
        T zza2;
        try {
            zzeja zzbeh = zzeip.zzbeh();
            zza2 = zza(t, zzbeh, zzejm);
            zzbeh.zzfy(0);
            return zza2;
        } catch (zzekj e) {
            throw e.zzl(zza2);
        } catch (zzekj e2) {
            throw e2;
        }
    }

    protected static <T extends zzejz<T, ?>> T zza(T t, byte[] bArr) throws zzekj {
        return zza(zza(t, bArr, 0, bArr.length, zzejm.zzbft()));
    }

    protected static <T extends zzejz<T, ?>> T zza(T t, byte[] bArr, zzejm zzejm) throws zzekj {
        return zza(zza(t, bArr, 0, bArr.length, zzejm));
    }

    public final /* synthetic */ zzelm zzbgl() {
        zzb zzb2 = (zzb) zza(zze.zzijo, (Object) null, (Object) null);
        zzb2.zza(this);
        return zzb2;
    }

    public final /* synthetic */ zzelm zzbgm() {
        return (zzb) zza(zze.zzijo, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzelj zzbgn() {
        return (zzejz) zza(zze.zzijp, (Object) null, (Object) null);
    }
}
