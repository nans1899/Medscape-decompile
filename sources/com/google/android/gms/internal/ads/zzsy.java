package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzsy implements zzrh {
    private final /* synthetic */ zzsw zzbuz;

    zzsy(zzsw zzsw) {
        this.zzbuz = zzsw;
    }

    public final void zzp(boolean z) {
        if (z) {
            this.zzbuz.connect();
        } else {
            this.zzbuz.disconnect();
        }
    }
}
