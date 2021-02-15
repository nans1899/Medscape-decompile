package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczh extends zzwy {
    private zzwt zzbpj;
    private final zzbix zzgpt;
    private final Context zzgsl;
    private final zzdom zzgsm = new zzdom();
    private final zzcer zzgsn = new zzcer();

    public zzczh(zzbix zzbix, Context context, String str) {
        this.zzgpt = zzbix;
        this.zzgsm.zzgt(str);
        this.zzgsl = context;
    }

    public final zzwu zzqf() {
        zzcep zzamw = this.zzgsn.zzamw();
        this.zzgsm.zzc(zzamw.zzamu());
        this.zzgsm.zzd(zzamw.zzamv());
        zzdom zzdom = this.zzgsm;
        if (zzdom.zzkg() == null) {
            zzdom.zze(zzvn.zzpk());
        }
        return new zzczk(this.zzgsl, this.zzgpt, this.zzgsm, zzamw, this.zzbpj);
    }

    public final void zzb(zzwt zzwt) {
        this.zzbpj = zzwt;
    }

    public final void zza(zzafe zzafe) {
        this.zzgsn.zzb(zzafe);
    }

    public final void zza(zzaft zzaft) {
        this.zzgsn.zzb(zzaft);
    }

    public final void zza(zzaff zzaff) {
        this.zzgsn.zzb(zzaff);
    }

    public final void zza(String str, zzafl zzafl, zzafk zzafk) {
        this.zzgsn.zzb(str, zzafl, zzafk);
    }

    public final void zza(zzadu zzadu) {
        this.zzgsm.zzb(zzadu);
    }

    public final void zza(zzajc zzajc) {
        this.zzgsm.zzb(zzajc);
    }

    public final void zza(zzajk zzajk) {
        this.zzgsn.zzb(zzajk);
    }

    public final void zzb(zzxq zzxq) {
        this.zzgsm.zzc(zzxq);
    }

    public final void zza(zzafs zzafs, zzvn zzvn) {
        this.zzgsn.zza(zzafs);
        this.zzgsm.zze(zzvn);
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzgsm.zzb(publisherAdViewOptions);
    }
}
