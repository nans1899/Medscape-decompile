package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbdg implements Runnable {
    private final zzbdf zzegg;

    private zzbdg(zzbdf zzbdf) {
        this.zzegg = zzbdf;
    }

    static Runnable zza(zzbdf zzbdf) {
        return new zzbdg(zzbdf);
    }

    public final void run() {
        this.zzegg.stop();
    }
}
