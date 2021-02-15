package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzawf implements zzdyr<Void> {
    private final /* synthetic */ zzdzc zzdxb;

    zzawf(zzavy zzavy, zzdzc zzdzc) {
        this.zzdxb = zzdzc;
    }

    public final void zzb(Throwable th) {
        zzavy.zzdwk.remove(this.zzdxb);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        Void voidR = (Void) obj;
        zzavy.zzdwk.remove(this.zzdxb);
    }
}
