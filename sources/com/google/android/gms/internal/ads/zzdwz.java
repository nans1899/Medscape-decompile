package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdwz<E> extends zzdwp<E> {
    static final zzdwp<Object> zzhra = new zzdwz(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzhrb;

    zzdwz(Object[] objArr, int i) {
        this.zzhrb = objArr;
        this.size = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzaxk() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return false;
    }

    public final int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzaxj() {
        return this.zzhrb;
    }

    /* access modifiers changed from: package-private */
    public final int zzaxl() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzhrb, 0, objArr, i, this.size);
        return i + this.size;
    }

    public final E get(int i) {
        zzdwd.zzs(i, this.size);
        return this.zzhrb[i];
    }
}
