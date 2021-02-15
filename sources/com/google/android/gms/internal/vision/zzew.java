package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzew extends zzee<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zznd;

    zzew(Object[] objArr, int i, int i2) {
        this.zznd = objArr;
        this.offset = i;
        this.size = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return true;
    }

    public final Object get(int i) {
        zzde.zzd(i, this.size);
        return this.zznd[(i * 2) + this.offset];
    }

    public final int size() {
        return this.size;
    }
}
