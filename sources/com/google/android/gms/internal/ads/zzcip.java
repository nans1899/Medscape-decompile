package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcip implements zzdyr<zzbgj> {
    private final /* synthetic */ String zzenm;
    private final /* synthetic */ zzahq zzgem;

    zzcip(zzcil zzcil, String str, zzahq zzahq) {
        this.zzenm = str;
        this.zzgem = zzahq;
    }

    public final void zzb(Throwable th) {
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        ((zzbgj) obj).zza(this.zzenm, (zzahq<? super zzbgj>) this.zzgem);
    }
}
