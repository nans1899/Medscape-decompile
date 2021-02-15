package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbtn;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdlp<R extends zzbtn<? extends zzbqo>> {
    private final Executor executor;
    private final zzdlx<R> zzhbq;
    private final zzdpz zzhbu;
    private final zzdlu zzhcb;
    /* access modifiers changed from: private */
    public zzdlt zzhcc;

    public zzdlp(zzdpz zzdpz, zzdlu zzdlu, zzdlx<R> zzdlx, Executor executor2) {
        this.zzhbu = zzdpz;
        this.zzhcb = zzdlu;
        this.zzhbq = zzdlx;
        this.executor = executor2;
    }

    public final zzdzc<zzdlt> zzata() {
        zzdzc zzdzc;
        zzdlt zzdlt = this.zzhcc;
        if (zzdlt != null) {
            return zzdyq.zzaf(zzdlt);
        }
        if (!zzada.zzdca.get().booleanValue()) {
            zzdlt zzdlt2 = new zzdlt((zzatc) null, zzatb(), (zzdlr) null);
            this.zzhcc = zzdlt2;
            zzdzc = zzdyq.zzaf(zzdlt2);
        } else {
            zzdzc = zzdyl.zzg(((zzbtn) this.zzhbq.zzc(this.zzhcb).zza(new zzdlf(this.zzhbu.zzaty().zzhib)).zzaey()).zzaev().zza(this.zzhbu.zzaty())).zza(new zzdlq(this), this.executor).zza(zzcqm.class, new zzdlr(this), this.executor);
        }
        return zzdyq.zzb(zzdzc, zzdlo.zzdwz, this.executor);
    }

    /* access modifiers changed from: private */
    @Deprecated
    public final zzdqj zzatb() {
        zzdok zzafs = ((zzbtn) this.zzhbq.zzc(this.zzhcb).zzaey()).zzafs();
        return this.zzhbu.zza(zzafs.zzhfa, zzafs.zzhfb, zzafs.zzhfe);
    }
}
