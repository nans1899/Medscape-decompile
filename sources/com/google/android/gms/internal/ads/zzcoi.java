package com.google.android.gms.internal.ads;

import android.os.Binder;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcoi {
    private final zzdzb zzgcz;
    private final zzdzb zzgjd;
    private final zzcpt zzgje;
    private final zzeos<zzcqv> zzgjf;

    public zzcoi(zzdzb zzdzb, zzdzb zzdzb2, zzcpt zzcpt, zzeos<zzcqv> zzeos) {
        this.zzgjd = zzdzb;
        this.zzgcz = zzdzb2;
        this.zzgje = zzcpt;
        this.zzgjf = zzeos;
    }

    public final zzdzc<InputStream> zze(zzatc zzatc) {
        zzdzc<V> zzdzc;
        String str = zzatc.packageName;
        zzp.zzkr();
        if (zzayu.zzer(str)) {
            zzdzc = zzdyq.immediateFailedFuture(new zzcqm(zzdpg.INTERNAL_ERROR));
        } else {
            zzdzc = zzdyq.zzb(this.zzgjd.zze(new zzcoh(this, zzatc)), ExecutionException.class, zzcok.zzboo, this.zzgcz);
        }
        return zzdyq.zzb(zzdzc, zzcqm.class, new zzcoj(this, zzatc, Binder.getCallingUid()), this.zzgcz);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzatc zzatc, int i, zzcqm zzcqm) throws Exception {
        return this.zzgjf.get().zzb(zzatc, i);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ InputStream zzf(zzatc zzatc) throws Exception {
        return (InputStream) this.zzgje.zzi(zzatc).get((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcvb)).intValue(), TimeUnit.SECONDS);
    }
}
