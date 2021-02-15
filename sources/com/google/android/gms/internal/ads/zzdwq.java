package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdwq extends zzdwp<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzdwp zzhqn;

    zzdwq(zzdwp zzdwp, int i, int i2) {
        this.zzhqn = zzdwp;
        this.offset = i;
        this.length = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return true;
    }

    public final int size() {
        return this.length;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzaxj() {
        return this.zzhqn.zzaxj();
    }

    /* access modifiers changed from: package-private */
    public final int zzaxk() {
        return this.zzhqn.zzaxk() + this.offset;
    }

    /* access modifiers changed from: package-private */
    public final int zzaxl() {
        return this.zzhqn.zzaxk() + this.offset + this.length;
    }

    public final E get(int i) {
        zzdwd.zzs(i, this.length);
        return this.zzhqn.get(i + this.offset);
    }

    public final zzdwp<E> zzu(int i, int i2) {
        zzdwd.zzf(i, i2, this.length);
        zzdwp zzdwp = this.zzhqn;
        int i3 = this.offset;
        return (zzdwp) zzdwp.subList(i + i3, i2 + i3);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
