package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwh extends zzcwe<zzcjg> {
    private final zzbys zzfbr;
    private final zzbix zzgpt;
    private final zzbtp.zza zzgpu;

    public zzcwh(zzbix zzbix, zzbtp.zza zza, zzbys zzbys) {
        this.zzgpt = zzbix;
        this.zzgpu = zza;
        this.zzfbr = zzbys;
    }

    /* access modifiers changed from: protected */
    public final zzdzc<zzcjg> zza(zzdok zzdok, Bundle bundle) {
        return this.zzgpt.zzadv().zzf(this.zzgpu.zza(zzdok).zzf(bundle).zzajv()).zzf(this.zzfbr).zzagl().zzaev().zzajh();
    }
}
