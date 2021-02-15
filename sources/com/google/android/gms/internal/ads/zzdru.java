package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdru<E> {
    /* access modifiers changed from: private */
    public static final zzdzc<?> zzhka = zzdyq.zzaf(null);
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzfoz;
    /* access modifiers changed from: private */
    public final zzdzb zzgcz;
    /* access modifiers changed from: private */
    public final zzdsg<E> zzhkb;

    public zzdru(zzdzb zzdzb, ScheduledExecutorService scheduledExecutorService, zzdsg<E> zzdsg) {
        this.zzgcz = zzdzb;
        this.zzfoz = scheduledExecutorService;
        this.zzhkb = zzdsg;
    }

    /* access modifiers changed from: protected */
    public abstract String zzv(E e);

    public final zzdry zzu(E e) {
        return new zzdry(this, e);
    }

    public final <I> zzdsa<I> zza(E e, zzdzc<I> zzdzc) {
        return new zzdsa(this, e, (String) null, zzdzc, Collections.singletonList(zzdzc), zzdzc, (zzdrt) null);
    }

    public final zzdrw zza(E e, zzdzc<?>... zzdzcArr) {
        return new zzdrw(this, e, Arrays.asList(zzdzcArr));
    }
}
