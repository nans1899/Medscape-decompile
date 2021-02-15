package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdma implements zzeoy<zzdlv<zzcjj, zzcjg>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdpw> zzeuw;
    private final zzeph<zzdqo> zzeux;

    public zzdma(zzeph<Context> zzeph, zzeph<zzdpw> zzeph2, zzeph<zzdqo> zzeph3) {
        this.zzesu = zzeph;
        this.zzeuw = zzeph2;
        this.zzeux = zzeph3;
    }

    public final /* synthetic */ Object get() {
        Object obj;
        Context context = this.zzesu.get();
        zzdpw zzdpw = this.zzeuw.get();
        zzdqo zzdqo = this.zzeux.get();
        if (((Integer) zzwm.zzpx().zzd(zzabb.zzcwr)).intValue() > 0) {
            zzdqn zza = zzdqo.zza(zzdqf.Rewarded, context, zzdpw, new zzdld(new zzdku()));
            obj = new zzdkz(new zzdlm(new zzdln()), new zzdle(zza.zzhbu, zzbbz.zzeep), zza.zzhiu, zzbbz.zzeep);
        } else {
            obj = new zzdln();
        }
        return (zzdlv) zzepe.zza(obj, "Cannot return null from a non-@Nullable @Provides method");
    }
}
