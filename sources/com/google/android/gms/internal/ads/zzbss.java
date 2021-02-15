package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbss implements zzeoy<zzcab<zzp>> {
    private final zzeph<zzbrh> zzfpj;
    private final zzbsj zzftg;

    private zzbss(zzbsj zzbsj, zzeph<zzbrh> zzeph) {
        this.zzftg = zzbsj;
        this.zzfpj = zzeph;
    }

    public static zzbss zza(zzbsj zzbsj, zzeph<zzbrh> zzeph) {
        return new zzbss(zzbsj, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
