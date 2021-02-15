package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwc extends zzcwe<zzbqu> {
    private final zzccw zzeve;
    private final zzbys zzfbr;
    private final zzbix zzgpt;
    private final zzbtp.zza zzgpu;

    public zzcwc(zzbix zzbix, zzccw zzccw, zzbtp.zza zza, zzbys zzbys) {
        this.zzgpt = zzbix;
        this.zzeve = zzccw;
        this.zzgpu = zza;
        this.zzfbr = zzbys;
    }

    /* access modifiers changed from: protected */
    public final zzdzc<zzbqu> zza(zzdok zzdok, Bundle bundle) {
        return this.zzgpt.zzadu().zza(this.zzgpu.zza(zzdok).zzf(bundle).zzajv()).zza(this.zzfbr).zza(this.zzeve).zzaex().zzaev().zzajh();
    }
}
