package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzagp implements Runnable {
    private final /* synthetic */ PublisherAdView zzdej;
    private final /* synthetic */ zzxc zzdek;
    private final /* synthetic */ zzagq zzdel;

    zzagp(zzagq zzagq, PublisherAdView publisherAdView, zzxc zzxc) {
        this.zzdel = zzagq;
        this.zzdej = publisherAdView;
        this.zzdek = zzxc;
    }

    public final void run() {
        if (this.zzdej.zza(this.zzdek)) {
            this.zzdel.zzdem.onPublisherAdViewLoaded(this.zzdej);
        } else {
            zzbbq.zzfe("Could not bind.");
        }
    }
}
