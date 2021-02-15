package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzamk {
    private zzakr zzdjb;
    private zzdzc<zzalp> zzdjc;

    zzamk(zzakr zzakr) {
        this.zzdjb = zzakr;
    }

    private final void zztq() {
        if (this.zzdjc == null) {
            zzbcg zzbcg = new zzbcg();
            this.zzdjc = zzbcg;
            this.zzdjb.zzb((zzeg) null).zza(new zzamj(zzbcg), new zzamm(zzbcg));
        }
    }

    public final <I, O> zzamn<I, O> zzb(String str, zzaly<I> zzaly, zzalv<O> zzalv) {
        zztq();
        return new zzamn<>(this.zzdjc, str, zzaly, zzalv);
    }

    public final void zzc(String str, zzahq<? super zzalp> zzahq) {
        zztq();
        this.zzdjc = zzdyq.zzb(this.zzdjc, new zzaml(str, zzahq), (Executor) zzbbz.zzeeu);
    }

    public final void zzd(String str, zzahq<? super zzalp> zzahq) {
        this.zzdjc = zzdyq.zzb(this.zzdjc, new zzamo(str, zzahq), (Executor) zzbbz.zzeeu);
    }
}
