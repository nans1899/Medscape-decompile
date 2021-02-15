package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import net.bytebuddy.pool.TypePool;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfu<E> extends zzfg<E> {
    private final transient E zza;
    private transient int zzb;

    zzfu(E e) {
        this.zza = zzeb.zza(e);
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return false;
    }

    zzfu(E e, int i) {
        this.zza = e;
        this.zzb = i;
    }

    public final boolean contains(Object obj) {
        return this.zza.equals(obj);
    }

    public final zzfx<E> zza() {
        return new zzfl(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final zzfb<E> zzh() {
        return zzfb.zza(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        objArr[i] = this.zza;
        return i + 1;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int hashCode = this.zza.hashCode();
        this.zzb = hashCode;
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg() {
        return this.zzb != 0;
    }

    public final String toString() {
        String obj = this.zza.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
