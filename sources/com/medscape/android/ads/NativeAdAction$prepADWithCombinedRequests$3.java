package com.medscape.android.ads;

import android.util.Log;
import com.google.android.gms.ads.AdListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/medscape/android/ads/NativeAdAction$prepADWithCombinedRequests$3", "Lcom/google/android/gms/ads/AdListener;", "onAdFailedToLoad", "", "errorCode", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NativeAdAction.kt */
public final class NativeAdAction$prepADWithCombinedRequests$3 extends AdListener {
    final /* synthetic */ INativeDFPAdLoadListener $nativeDFPAdLoadListener;

    NativeAdAction$prepADWithCombinedRequests$3(INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        this.$nativeDFPAdLoadListener = iNativeDFPAdLoadListener;
    }

    public void onAdFailedToLoad(int i) {
        this.$nativeDFPAdLoadListener.onAdFailedToLoad(i);
        Log.d("ERROR_CODE", String.valueOf(i) + "");
    }
}
