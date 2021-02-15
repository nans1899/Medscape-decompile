package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrl<T> {
    private final Executor executor;
    private final zzcpf zzfsi;
    private final zzcpp zzfsj;
    private final zzdok zzfsk;
    private final zzdsi zzfsl;
    private final zzblx zzfsm;
    private final zzcwq<T> zzfsn;
    /* access modifiers changed from: private */
    public final zzbyg zzfso;
    private final zzdog zzfsp;
    private final zzcql zzfsq;
    private final zzbtm zzfsr;
    private final zzcqi zzfss;
    private final zzctd zzfst;

    zzbrl(zzcpf zzcpf, zzcpp zzcpp, zzdok zzdok, zzdsi zzdsi, zzblx zzblx, zzcwq<T> zzcwq, zzbyg zzbyg, zzdog zzdog, zzcql zzcql, zzbtm zzbtm, Executor executor2, zzcqi zzcqi, zzctd zzctd) {
        this.zzfsi = zzcpf;
        this.zzfsj = zzcpp;
        this.zzfsk = zzdok;
        this.zzfsl = zzdsi;
        this.zzfsm = zzblx;
        this.zzfsn = zzcwq;
        this.zzfso = zzbyg;
        this.zzfsp = zzdog;
        this.zzfsq = zzcql;
        this.zzfsr = zzbtm;
        this.executor = executor2;
        this.zzfss = zzcqi;
        this.zzfst = zzctd;
    }

    private final zzdzc<zzdog> zza(zzdzc<zzatc> zzdzc) {
        if (this.zzfsp != null) {
            return this.zzfsl.zzu(zzdsf.SERVER_TRANSACTION).zze(zzdyq.zzaf(this.zzfsp)).zzavs();
        }
        zzp.zzkx().zzmv();
        if (this.zzfsk.zzhfa.zzcho != null) {
            return this.zzfsl.zzu(zzdsf.SERVER_TRANSACTION).zze(this.zzfsj.zzapy()).zzavs();
        }
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcyj)).booleanValue()) {
            return this.zzfsl.zza(zzdsf.SERVER_TRANSACTION, zzdzc).zza(this.zzfsi).zzavs();
        }
        zzdsa<I> zza = this.zzfsl.zza(zzdsf.SERVER_TRANSACTION, zzdzc);
        zzcqi zzcqi = this.zzfss;
        zzcqi.getClass();
        return zza.zza(zzbro.zza(zzcqi)).zzavs();
    }

    public final zzdzc<zzdog> zza(zzatc zzatc) {
        return zza((zzdzc<zzatc>) zzdyq.zzaf(zzatc));
    }

    public final zzdzc<zzdog> zzajg() {
        return zza(this.zzfsr.zzajp());
    }

    public final zzdzc<T> zzb(zzdzc<zzdog> zzdzc) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcva)).booleanValue()) {
            return this.zzfsl.zza(zzdsf.RENDERER, zzdzc).zza(this.zzfsm).zza(this.zzfsn).zzavs();
        }
        return this.zzfsl.zza(zzdsf.RENDERER, zzdzc).zza(this.zzfsm).zza(this.zzfsn).zza((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcvb)).intValue(), TimeUnit.SECONDS).zzavs();
    }

    public final zzdzc<T> zzb(zzatc zzatc) {
        return zzb(zza(zzatc));
    }

    public final zzdzc<T> zzajh() {
        return zzb(zzajg());
    }

    public final zzbyg zzaji() {
        return this.zzfso;
    }

    public final zzdzc<zzatc> zza(zzdqg zzdqg) {
        zzdrr<E, O2> zzavs = this.zzfsl.zza(zzdsf.GET_CACHE_KEY, this.zzfsr.zzajp()).zza(new zzbrn(this, zzdqg)).zzavs();
        zzdyq.zza(zzavs, new zzbrq(this), this.executor);
        return zzavs;
    }

    public final zzdzc<Void> zzc(zzatc zzatc) {
        zzdrr<E, I> zzavs = this.zzfsl.zza(zzdsf.NOTIFY_CACHE_HIT, this.zzfsq.zzm(zzatc)).zzavs();
        zzdyq.zza(zzavs, new zzbrp(this), this.executor);
        return zzavs;
    }

    public final zzva zze(Throwable th) {
        return zzdpe.zza(th, this.zzfst);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdqg zzdqg, zzatc zzatc) throws Exception {
        zzatc.zzdub = zzdqg;
        return this.zzfsq.zzl(zzatc);
    }
}
