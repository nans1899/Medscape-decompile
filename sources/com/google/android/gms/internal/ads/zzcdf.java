package com.google.android.gms.internal.ads;

import android.view.ViewGroup;
import com.google.android.gms.internal.ads.zzbtp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdf implements zzeoy<zzbqp<zzboq>> {
    private final zzeph<zzbwk> zzftr;
    private final zzeph<zzbix> zzfxn;
    private final zzeph<zzbtp.zza> zzfxo;
    private final zzeph<zzbys> zzfxp;
    private final zzeph<zzccw> zzfxq;

    public zzcdf(zzeph<zzbix> zzeph, zzeph<zzbtp.zza> zzeph2, zzeph<zzbys> zzeph3, zzeph<zzccw> zzeph4, zzeph<zzbwk> zzeph5) {
        this.zzfxn = zzeph;
        this.zzfxo = zzeph2;
        this.zzfxp = zzeph3;
        this.zzfxq = zzeph4;
        this.zzftr = zzeph5;
    }

    public final /* synthetic */ Object get() {
        return (zzbqp) zzepe.zza(this.zzfxn.get().zzadp().zzd(this.zzfxo.get().zzajv()).zzd(this.zzfxp.get()).zzb(this.zzfxq.get()).zza(new zzcyn((zzaby) null)).zza(new zzbqh(this.zzftr.get())).zzc(new zzbol((ViewGroup) null)).zzafw().zzage(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
