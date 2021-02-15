package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzwp extends AdListener {
    private final Object lock = new Object();
    private AdListener zzcji;

    public final void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzcji = adListener;
        }
    }

    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdFailedToLoad(LoadAdError loadAdError) {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdFailedToLoad(loadAdError);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdLeftApplication();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdOpened();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzcji != null) {
                this.zzcji.onAdLoaded();
            }
        }
    }
}
