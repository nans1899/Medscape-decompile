package com.medscape.android.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "dfpAD", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "kotlin.jvm.PlatformType", "onPublisherAdViewLoaded"}, k = 3, mv = {1, 4, 0})
/* compiled from: NativeAdAction.kt */
final class NativeAdAction$prepADWithCombinedRequests$1 implements OnPublisherAdViewLoadedListener {
    final /* synthetic */ INativeDFPAdLoadListener $nativeDFPAdLoadListener;
    final /* synthetic */ NativeAdAction this$0;

    NativeAdAction$prepADWithCombinedRequests$1(NativeAdAction nativeAdAction, INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        this.this$0 = nativeAdAction;
        this.$nativeDFPAdLoadListener = iNativeDFPAdLoadListener;
    }

    public final void onPublisherAdViewLoaded(PublisherAdView publisherAdView) {
        NativeAdAction nativeAdAction = this.this$0;
        Intrinsics.checkNotNullExpressionValue(publisherAdView, "dfpAD");
        nativeAdAction.handleDFPAd(publisherAdView, this.$nativeDFPAdLoadListener);
    }
}
