package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzeg extends zzee<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzee zznb;

    zzeg(zzee zzee, int i, int i2) {
        this.zznb = zzee;
        this.offset = i;
        this.length = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return true;
    }

    public final int size() {
        return this.length;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzcq() {
        return this.zznb.zzcq();
    }

    /* access modifiers changed from: package-private */
    public final int zzcr() {
        return this.zznb.zzcr() + this.offset;
    }

    /* access modifiers changed from: package-private */
    public final int zzcs() {
        return this.zznb.zzcr() + this.offset + this.length;
    }

    public final E get(int i) {
        zzde.zzd(i, this.length);
        return this.zznb.get(i + this.offset);
    }

    public final zzee<E> zzh(int i, int i2) {
        zzde.zza(i, i2, this.length);
        zzee zzee = this.zznb;
        int i3 = this.offset;
        return (zzee) zzee.subList(i + i3, i2 + i3);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
