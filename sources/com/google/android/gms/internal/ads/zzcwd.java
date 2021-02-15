package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.ViewGroup;
import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwd extends zzcwe<zzboq> {
    private final zzccw zzeve;
    private final zzbys zzfbr;
    private final zzcyn zzfig;
    private final ViewGroup zzfpw;
    private final zzbwk zzfrk;
    private final zzbix zzgpt;
    private final zzbtp.zza zzgpu;

    public zzcwd(zzbix zzbix, zzbtp.zza zza, zzcyn zzcyn, zzbys zzbys, zzccw zzccw, zzbwk zzbwk, ViewGroup viewGroup) {
        this.zzgpt = zzbix;
        this.zzgpu = zza;
        this.zzfig = zzcyn;
        this.zzfbr = zzbys;
        this.zzeve = zzccw;
        this.zzfrk = zzbwk;
        this.zzfpw = viewGroup;
    }

    /* access modifiers changed from: protected */
    public final zzdzc<zzboq> zza(zzdok zzdok, Bundle bundle) {
        return this.zzgpt.zzadp().zzd(this.zzgpu.zza(zzdok).zzf(bundle).zzajv()).zzd(this.zzfbr).zza(this.zzfig).zzb(this.zzeve).zza(new zzbqh(this.zzfrk)).zzc(new zzbol(this.zzfpw)).zzafw().zzaev().zzajh();
    }
}
