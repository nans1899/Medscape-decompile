package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzri implements Runnable {
    private final /* synthetic */ zzrf zzbtc;

    zzri(zzrf zzrf) {
        this.zzbtc = zzrf;
    }

    public final void run() {
        synchronized (this.zzbtc.lock) {
            if (!this.zzbtc.foreground || !this.zzbtc.zzbsu) {
                zzayp.zzef("App is still foreground");
            } else {
                boolean unused = this.zzbtc.foreground = false;
                zzayp.zzef("App went background");
                for (zzrh zzp : this.zzbtc.zzbsv) {
                    try {
                        zzp.zzp(false);
                    } catch (Exception e) {
                        zzbbq.zzc("", e);
                    }
                }
            }
        }
    }
}
