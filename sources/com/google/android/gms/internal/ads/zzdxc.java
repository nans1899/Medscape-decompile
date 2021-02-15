package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxc extends zzdwp<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzhqz;

    zzdxc(Object[] objArr, int i, int i2) {
        this.zzhqz = objArr;
        this.offset = i;
        this.size = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return true;
    }

    public final Object get(int i) {
        zzdwd.zzs(i, this.size);
        return this.zzhqz[(i * 2) + this.offset];
    }

    public final int size() {
        return this.size;
    }
}
