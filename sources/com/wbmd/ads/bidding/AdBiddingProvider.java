package com.wbmd.ads.bidding;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.wbmd.ads.AdManager;
import com.wbmd.ads.IAdParams;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J \u0010\u0013\u001a\u00020\u00042\u0018\u0010\u0014\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u0015R(\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/wbmd/ads/bidding/AdBiddingProvider;", "Lcom/wbmd/ads/bidding/IAdBiddingProvider;", "()V", "value", "Landroid/os/Bundle;", "bids", "getBids", "()Landroid/os/Bundle;", "setBids", "(Landroid/os/Bundle;)V", "cachedBidsData", "getCachedBidsData", "setCachedBidsData", "clearCache", "", "createAdRequestBuilder", "Lcom/google/android/gms/ads/doubleclick/PublisherAdRequest;", "adParams", "Lcom/wbmd/ads/IAdParams;", "getBundleFromMap", "dataMap", "Ljava/util/HashMap;", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdBiddingProvider.kt */
public abstract class AdBiddingProvider implements IAdBiddingProvider {
    private Bundle bids;
    private Bundle cachedBidsData;

    public final Bundle getCachedBidsData() {
        return this.cachedBidsData;
    }

    public final void setCachedBidsData(Bundle bundle) {
        this.cachedBidsData = bundle;
    }

    public final Bundle getBids() {
        return this.bids;
    }

    public final void setBids(Bundle bundle) {
        this.bids = bundle;
        if (bundle != null && !bundle.isEmpty() && shouldCacheBids()) {
            Bundle bundle2 = new Bundle();
            this.cachedBidsData = bundle2;
            if (bundle2 != null) {
                bundle2.putAll(bundle);
            }
        }
    }

    public final Bundle getBundleFromMap(HashMap<String, String> hashMap) {
        Bundle bundle = new Bundle();
        if (hashMap != null) {
            for (Map.Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (!(str == null || str2 == null)) {
                    bundle.putString(str, str2);
                }
            }
        }
        return bundle;
    }

    public final PublisherAdRequest createAdRequestBuilder(IAdParams iAdParams) {
        Intrinsics.checkNotNullParameter(iAdParams, "adParams");
        PublisherAdRequest.Builder addNetworkExtrasBundle = new PublisherAdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, AdManager.Companion.getAdExtrasBundle$wbmdadsdk_release(iAdParams));
        CharSequence currentPageName = iAdParams.getCurrentPageName();
        if (!(currentPageName == null || currentPageName.length() == 0)) {
            addNetworkExtrasBundle.setContentUrl(iAdParams.getCurrentPageName());
        }
        PublisherAdRequest build = addNetworkExtrasBundle.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public final void clearCache() {
        Bundle bundle = null;
        this.cachedBidsData = bundle;
        setBids(bundle);
    }
}
