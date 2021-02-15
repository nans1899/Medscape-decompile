package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdlz {
    public static zzdlv<zzbnq, zzbnv> zza(Context context, zzdpw zzdpw, zzdqo zzdqo) {
        return zzc(context, zzdpw, zzdqo);
    }

    public static zzdlv<zzbob, zzboh> zzb(Context context, zzdpw zzdpw, zzdqo zzdqo) {
        return zzc(context, zzdpw, zzdqo);
    }

    private static <AppOpenAdRequestComponent extends zzbtn<AppOpenAd>, AppOpenAd extends zzbqo> zzdlv<AppOpenAdRequestComponent, AppOpenAd> zzc(Context context, zzdpw zzdpw, zzdqo zzdqo) {
        if (((Integer) zzwm.zzpx().zzd(zzabb.zzcxf)).intValue() <= 0) {
            return new zzdln();
        }
        zzdqn zza = zzdqo.zza(zzdqf.AppOpen, context, zzdpw, new zzdld(new zzdku()));
        return new zzdkz(new zzdlm(new zzdln()), new zzdle(zza.zzhbu, zzbbz.zzeep), zza.zzhiu, zzbbz.zzeep);
    }
}
