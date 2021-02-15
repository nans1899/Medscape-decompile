package com.google.android.gms.internal.ads;

import android.os.Binder;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcql {
    private final zzdzb zzgcz;
    private final zzeos<zzcqv> zzgjf;
    private final zzcqc zzgkw;

    public zzcql(zzdzb zzdzb, zzcqc zzcqc, zzeos<zzcqv> zzeos) {
        this.zzgcz = zzdzb;
        this.zzgkw = zzcqc;
        this.zzgjf = zzeos;
    }

    private final <RetT> zzdzc<RetT> zza(zzatc zzatc, zzcqw<InputStream> zzcqw, zzcqw<InputStream> zzcqw2, zzdya<InputStream, RetT> zzdya) {
        zzdzc<V> zzdzc;
        String str = zzatc.packageName;
        zzp.zzkr();
        if (zzayu.zzer(str)) {
            zzdzc = zzdyq.immediateFailedFuture(new zzcqm(zzdpg.INTERNAL_ERROR));
        } else {
            zzdzc = zzdyq.zzb(zzcqw.zzq(zzatc), ExecutionException.class, zzcqo.zzboo, this.zzgcz);
        }
        return zzdyl.zzg(zzdzc).zzb(zzdya, this.zzgcz).zza(zzcqm.class, new zzcqn(this, zzcqw2, zzatc, zzdya), (Executor) this.zzgcz);
    }

    public final zzdzc<zzatc> zzl(zzatc zzatc) {
        zzcqq zzcqq = new zzcqq(zzatc);
        zzcqc zzcqc = this.zzgkw;
        zzcqc.getClass();
        return zza(zzatc, (zzcqw<InputStream>) zzcqp.zza(zzcqc), (zzcqw<InputStream>) new zzcqs(this), zzcqq);
    }

    public final zzdzc<Void> zzm(zzatc zzatc) {
        if (zzfg.zzas(zzatc.zzduc)) {
            return zzdyq.immediateFailedFuture(new zzcof(zzdpg.INVALID_REQUEST, "Pool key missing from removeUrl call."));
        }
        return zza(zzatc, (zzcqw<InputStream>) new zzcqu(this), (zzcqw<InputStream>) new zzcqt(this), zzcqr.zzboo);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzn(zzatc zzatc) {
        return this.zzgjf.get().zzgn(zzatc.zzduc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzo(zzatc zzatc) {
        return this.zzgkw.zzgm(zzatc.zzduc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzp(zzatc zzatc) {
        return this.zzgjf.get().zzc(zzatc, Binder.getCallingUid());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzcqw zzcqw, zzatc zzatc, zzdya zzdya, zzcqm zzcqm) throws Exception {
        return zzdyq.zzb(zzcqw.zzq(zzatc), zzdya, (Executor) this.zzgcz);
    }
}
