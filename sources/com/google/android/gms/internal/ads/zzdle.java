package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbtn;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdle<R extends zzbtn<AdT>, AdT extends zzbqo> implements zzdlv<R, zzdli<AdT>> {
    private final Executor executor;
    private final zzdpz zzhbu;
    private zzdyr<Void> zzhbv = new zzdlj(this);

    public zzdle(zzdpz zzdpz, Executor executor2) {
        this.zzhbu = zzdpz;
        this.executor = executor2;
    }

    public final /* bridge */ /* synthetic */ Object zzasv() {
        return null;
    }

    public final zzdzc<zzdli<AdT>> zza(zzdlw zzdlw, zzdlx<R> zzdlx) {
        return zzdyl.zzg(new zzdlp(this.zzhbu, zzdlw.zzhcf, zzdlx, this.executor).zzata()).zzb(new zzdlh(this, zzdlw, zzdlx), this.executor).zza(Exception.class, new zzdlg(this), this.executor);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdlw zzdlw, zzdlx zzdlx, zzdlt zzdlt) throws Exception {
        zzdqj zzdqj = zzdlt.zzhbs;
        zzatc zzatc = zzdlt.zzgkg;
        zzdqk<?> zza = zzdqj != null ? this.zzhbu.zza(zzdqj) : null;
        if (zzdqj == null) {
            return zzdyq.zzaf(null);
        }
        if (!(zza == null || zzatc == null)) {
            zzdyq.zza(((zzbtn) zzdlx.zzc(zzdlw.zzhcf).zzaey()).zzaev().zzc(zzatc), this.zzhbv, this.executor);
        }
        return zzdyq.zzaf(new zzdli(zzdqj, zzatc, zza));
    }
}
