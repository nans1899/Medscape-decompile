package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;
import com.google.android.gms.internal.icing.zzdx.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzdx<MessageType extends zzdx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzcm<MessageType, BuilderType> {
    private static Map<Object, zzdx<?, ?>> zzke = new ConcurrentHashMap();
    protected zzgp zzkc = zzgp.zzdl();
    private int zzkd = -1;

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static class zza<T extends zzdx<T, ?>> extends zzcq<T> {
        private final T zzkg;

        public zza(T t) {
            this.zzkg = t;
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    static final class zzc implements zzdu<zzc> {
        public final int zzbf() {
            throw new NoSuchMethodError();
        }

        public final zzha zzbg() {
            throw new NoSuchMethodError();
        }

        public final zzhh zzbh() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbi() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbj() {
            throw new NoSuchMethodError();
        }

        public final zzfg zza(zzfg zzfg, zzfh zzfh) {
            throw new NoSuchMethodError();
        }

        public final zzfn zza(zzfn zzfn, zzfn zzfn2) {
            throw new NoSuchMethodError();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzdx<MessageType, BuilderType> implements zzfj {
        protected zzds<zzc> zzkj = zzds.zzbd();
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static final class zze {
        public static final int zzkm = 1;
        public static final int zzkn = 2;
        public static final int zzko = 3;
        public static final int zzkp = 4;
        public static final int zzkq = 5;
        public static final int zzkr = 6;
        public static final int zzks = 7;
        private static final /* synthetic */ int[] zzkt = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzku = 1;
        public static final int zzkv = 2;
        private static final /* synthetic */ int[] zzkw = {1, 2};
        public static final int zzkx = 1;
        public static final int zzky = 2;
        private static final /* synthetic */ int[] zzkz = {1, 2};

        public static int[] zzby() {
            return (int[]) zzkt.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzfi.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzga != 0) {
            return this.zzga;
        }
        this.zzga = zzft.zzcv().zzo(this).hashCode(this);
        return this.zzga;
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static abstract class zzb<MessageType extends zzdx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzco<MessageType, BuilderType> {
        private final MessageType zzkg;
        protected MessageType zzkh;
        protected boolean zzki = false;

        protected zzb(MessageType messagetype) {
            this.zzkg = messagetype;
            this.zzkh = (zzdx) messagetype.zza(zze.zzkp, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzbt() {
            MessageType messagetype = (zzdx) this.zzkh.zza(zze.zzkp, (Object) null, (Object) null);
            zza(messagetype, this.zzkh);
            this.zzkh = messagetype;
        }

        public final boolean isInitialized() {
            return zzdx.zza(this.zzkh, false);
        }

        /* renamed from: zzbu */
        public MessageType zzbw() {
            if (this.zzki) {
                return this.zzkh;
            }
            MessageType messagetype = this.zzkh;
            zzft.zzcv().zzo(messagetype).zzf(messagetype);
            this.zzki = true;
            return this.zzkh;
        }

        /* renamed from: zzbv */
        public final MessageType zzbx() {
            MessageType messagetype = (zzdx) zzbw();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzgn(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzki) {
                zzbt();
                this.zzki = false;
            }
            zza(this.zzkh, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzft.zzcv().zzo(messagetype).zzc(messagetype, messagetype2);
        }

        public final /* synthetic */ zzco zzag() {
            return (zzb) clone();
        }

        public final /* synthetic */ zzfh zzbr() {
            return this.zzkg;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzb = (zzb) ((zzdx) this.zzkg).zza(zze.zzkq, (Object) null, (Object) null);
            zzb.zza((zzdx) zzbw());
            return zzb;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzft.zzcv().zzo(this).equals(this, (zzdx) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzdx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzbk() {
        return (zzb) zza(zze.zzkq, (Object) null, (Object) null);
    }

    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: package-private */
    public final int zzae() {
        return this.zzkd;
    }

    /* access modifiers changed from: package-private */
    public final void zzg(int i) {
        this.zzkd = i;
    }

    public final void zzb(zzdk zzdk) throws IOException {
        zzft.zzcv().zzo(this).zza(this, zzdm.zza(zzdk));
    }

    public final int zzbl() {
        if (this.zzkd == -1) {
            this.zzkd = zzft.zzcv().zzo(this).zzn(this);
        }
        return this.zzkd;
    }

    static <T extends zzdx<?, ?>> T zza(Class<T> cls) {
        T t = (zzdx) zzke.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzdx) zzke.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzdx) ((zzdx) zzgs.zzg(cls)).zza(zze.zzkr, (Object) null, (Object) null);
            if (t != null) {
                zzke.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzdx<?, ?>> void zza(Class<T> cls, T t) {
        zzke.put(cls, t);
    }

    protected static Object zza(zzfh zzfh, String str, Object[] objArr) {
        return new zzfv(zzfh, str, objArr);
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

    protected static final <T extends zzdx<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzkm, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzm = zzft.zzcv().zzo(t).zzm(t);
        if (z) {
            t.zza(zze.zzkn, (Object) zzm ? t : null, (Object) null);
        }
        return zzm;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzev, com.google.android.gms.internal.icing.zzef] */
    protected static zzef zzbm() {
        return zzev.zzci();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzed, com.google.android.gms.internal.icing.zzdl] */
    protected static zzed zzbn() {
        return zzdl.zzax();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.icing.zzcr, com.google.android.gms.internal.icing.zzea] */
    protected static zzea zzbo() {
        return zzcr.zzak();
    }

    protected static <E> zzee<E> zzbp() {
        return zzfs.zzcu();
    }

    protected static <E> zzee<E> zza(zzee<E> zzee) {
        int size = zzee.size();
        return zzee.zzj(size == 0 ? 10 : size << 1);
    }

    public final /* synthetic */ zzfg zzbq() {
        zzb zzb2 = (zzb) zza(zze.zzkq, (Object) null, (Object) null);
        zzb2.zza(this);
        return zzb2;
    }

    public final /* synthetic */ zzfh zzbr() {
        return (zzdx) zza(zze.zzkr, (Object) null, (Object) null);
    }
}
