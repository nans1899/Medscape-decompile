package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfd extends zzfb<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzfb zzc;

    zzfd(zzfb zzfb, int i, int i2) {
        this.zzc = zzfb;
        this.zza = i;
        this.zzb = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }

    public final int size() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzb() {
        return this.zzc.zzb();
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    public final int zzd() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    public final E get(int i) {
        zzeb.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    public final zzfb<E> zza(int i, int i2) {
        zzeb.zza(i, i2, this.zzb);
        zzfb zzfb = this.zzc;
        int i3 = this.zza;
        return (zzfb) zzfb.subList(i + i3, i2 + i3);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
