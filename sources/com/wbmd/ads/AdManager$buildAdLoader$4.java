package com.wbmd.ads;

import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/wbmd/ads/AdManager$buildAdLoader$4", "Lcom/google/android/gms/ads/AdListener;", "onAdFailedToLoad", "", "errorCode", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
public final class AdManager$buildAdLoader$4 extends AdListener {
    final /* synthetic */ IAdListener $listener;

    AdManager$buildAdLoader$4(IAdListener iAdListener) {
        this.$listener = iAdListener;
    }

    public void onAdFailedToLoad(int i) {
        this.$listener.onAdFailed(new AdContainer(AdStatus.failed, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null), i);
        Log.d("ERROR_CODE", String.valueOf(i) + "");
    }
}
