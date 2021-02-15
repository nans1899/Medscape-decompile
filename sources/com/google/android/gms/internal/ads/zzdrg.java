package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdrg implements zzeoy<zzdzb> {
    public static zzdrg zzave() {
        return zzdrf.zzhjr;
    }

    public final /* synthetic */ Object get() {
        zzdzb zzdzb;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvh)).booleanValue()) {
            zzdzb = zzbbz.zzeer;
        } else {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvg)).booleanValue()) {
                zzdzb = zzbbz.zzeep;
            } else {
                zzdzb = zzbbz.zzeet;
            }
        }
        return (zzdzb) zzepe.zza(zzdzb, "Cannot return null from a non-@Nullable @Provides method");
    }
}
