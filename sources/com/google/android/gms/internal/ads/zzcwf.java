package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwf extends zzcwe<zzcbe> {
    private final zzbys zzfbr;
    private final zzcyn zzfig;
    private final zzbix zzgpt;
    private final zzbtp.zza zzgpu;

    public zzcwf(zzbix zzbix, zzbtp.zza zza, zzcyn zzcyn, zzbys zzbys) {
        this.zzgpt = zzbix;
        this.zzgpu = zza;
        this.zzfig = zzcyn;
        this.zzfbr = zzbys;
    }

    /* access modifiers changed from: protected */
    public final zzdzc<zzcbe> zza(zzdok zzdok, Bundle bundle) {
        return this.zzgpt.zzadt().zze(this.zzgpu.zza(zzdok).zzf(bundle).zzajv()).zze(this.zzfbr).zzb(this.zzfig).zzagh().zzaev().zzajh();
    }
}
