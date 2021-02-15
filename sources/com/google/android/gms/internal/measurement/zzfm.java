package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfm<E> extends zzfb<E> {
    static final zzfb<Object> zza = new zzfm(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    zzfm(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return false;
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzb() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzd() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    public final E get(int i) {
        zzeb.zza(i, this.zzc);
        return this.zzb[i];
    }
}
