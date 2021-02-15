package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpt implements zzeoy<Boolean> {
    private final zzeph<zzdok> zzfqr;

    public zzbpt(zzeph<zzdok> zzeph) {
        this.zzfqr = zzeph;
    }

    public final /* synthetic */ Object get() {
        boolean z;
        if (this.zzfqr.get().zzati() != null) {
            z = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcpc)).booleanValue();
        } else {
            z = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcvc)).booleanValue();
        }
        return Boolean.valueOf(z);
    }
}
