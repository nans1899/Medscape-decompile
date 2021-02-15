package com.google.android.gms.ads.instream;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzajo;
import com.google.android.gms.internal.ads.zzajp;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class InstreamAd {

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class InstreamAdLoadCallback {
        @Deprecated
        public void onInstreamAdFailedToLoad(int i) {
        }

        public void onInstreamAdFailedToLoad(LoadAdError loadAdError) {
        }

        public void onInstreamAdLoaded(InstreamAd instreamAd) {
        }
    }

    public abstract void destroy();

    @Deprecated
    public abstract float getAspectRatio();

    public abstract MediaContent getMediaContent();

    @Deprecated
    public abstract VideoController getVideoController();

    @Deprecated
    public abstract float getVideoCurrentTime();

    @Deprecated
    public abstract float getVideoDuration();

    /* access modifiers changed from: protected */
    public abstract void zza(InstreamAdView instreamAdView);

    public static void load(Context context, String str, AdRequest adRequest, int i, InstreamAdLoadCallback instreamAdLoadCallback) {
        Preconditions.checkArgument(i == 2 || i == 3, "Instream ads only support Landscape and Portrait media aspect ratios");
        new zzajp(context, str).zza(instreamAdLoadCallback).zza(new zzajo(i)).zztg().loadAd(adRequest);
    }

    public static void load(Context context, String str, InstreamAdLoadCallback instreamAdLoadCallback) {
        new zzajp(context, "").zza(instreamAdLoadCallback).zza(new zzajo(str)).zztg().loadAd(new PublisherAdRequest.Builder().build());
    }
}
