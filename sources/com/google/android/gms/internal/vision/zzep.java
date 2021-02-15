package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzep<E> extends zzee<E> {
    static final zzee<Object> zznk = new zzep(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zznl;

    zzep(Object[] objArr, int i) {
        this.zznl = objArr;
        this.size = i;
    }

    /* access modifiers changed from: package-private */
    public final int zzcr() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return false;
    }

    public final int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzcq() {
        return this.zznl;
    }

    /* access modifiers changed from: package-private */
    public final int zzcs() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zznl, 0, objArr, i, this.size);
        return i + this.size;
    }

    public final E get(int i) {
        zzde.zzd(i, this.size);
        return this.zznl[i];
    }
}
