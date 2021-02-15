package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzagm extends zzafj {
    private final /* synthetic */ zzagk zzdeg;

    private zzagm(zzagk zzagk) {
        this.zzdeg = zzagk;
    }

    public final void zza(zzafa zzafa, String str) {
        if (this.zzdeg.zzdee != null) {
            this.zzdeg.zzdee.onCustomClick(this.zzdeg.zzb(zzafa), str);
        }
    }
}
