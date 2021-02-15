package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvh extends zzbyr<zzbuh> {
    public zzbvh(Set<zzcab<zzbuh>> set) {
        super(set);
    }

    public final void onAdClosed() {
        zza(zzbvg.zzfur);
    }

    public final void onAdLeftApplication() {
        zza(zzbvj.zzfur);
    }

    public final void onAdOpened() {
        zza(zzbvi.zzfur);
    }

    public final void onRewardedVideoStarted() {
        zza(zzbvl.zzfur);
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
        zza(new zzbvk(zzatw, str, str2));
    }

    public final void onRewardedVideoCompleted() {
        zza(zzbvn.zzfur);
    }
}
