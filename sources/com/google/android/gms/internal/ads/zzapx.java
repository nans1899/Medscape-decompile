package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzapx implements MediationInterstitialAdapter {
    private Uri uri;
    /* access modifiers changed from: private */
    public Activity zzdms;
    /* access modifiers changed from: private */
    public MediationInterstitialListener zzdmt;

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdmt = mediationInterstitialListener;
        if (mediationInterstitialListener == null) {
            zzbbq.zzfe("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzbbq.zzfe("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzdmt.onAdFailedToLoad((MediationInterstitialAdapter) this, 0);
        } else {
            if (!(PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzacc.zzk(context))) {
                zzbbq.zzfe("Default browser does not support custom tabs. Bailing out.");
                this.zzdmt.onAdFailedToLoad((MediationInterstitialAdapter) this, 0);
                return;
            }
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzbbq.zzfe("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzdmt.onAdFailedToLoad((MediationInterstitialAdapter) this, 0);
                return;
            }
            this.zzdms = (Activity) context;
            this.uri = Uri.parse(string);
            this.zzdmt.onAdLoaded(this);
        }
    }

    public final void showInterstitial() {
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.setData(this.uri);
        zzayu.zzeba.post(new zzapz(this, new AdOverlayInfoParcel(new zzb(build.intent), (zzux) null, new zzaqa(this), (zzv) null, new zzbbx(0, 0, false))));
        zzp.zzkv().zzwp();
    }

    public final void onDestroy() {
        zzbbq.zzef("Destroying AdMobCustomTabsAdapter adapter.");
    }

    public final void onPause() {
        zzbbq.zzef("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public final void onResume() {
        zzbbq.zzef("Resuming AdMobCustomTabsAdapter adapter.");
    }
}
