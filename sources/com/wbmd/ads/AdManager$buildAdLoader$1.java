package com.wbmd.ads;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "adView", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "kotlin.jvm.PlatformType", "onPublisherAdViewLoaded"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
final class AdManager$buildAdLoader$1 implements OnPublisherAdViewLoadedListener {
    final /* synthetic */ IAdListener $listener;
    final /* synthetic */ AdManager this$0;

    AdManager$buildAdLoader$1(AdManager adManager, IAdListener iAdListener) {
        this.this$0 = adManager;
        this.$listener = iAdListener;
    }

    public final void onPublisherAdViewLoaded(PublisherAdView publisherAdView) {
        boolean z;
        HashMap<AdSize, AdSize> adConversionSizes;
        AdSize[] blockerAdSize;
        IAdConversions access$getAdConversions$p = this.this$0.adConversions;
        if (access$getAdConversions$p == null || (blockerAdSize = access$getAdConversions$p.getBlockerAdSize()) == null) {
            z = false;
        } else {
            Intrinsics.checkNotNullExpressionValue(publisherAdView, "adView");
            z = ArraysKt.contains((T[]) blockerAdSize, publisherAdView.getAdSize());
        }
        if (!z) {
            IAdConversions access$getAdConversions$p2 = this.this$0.adConversions;
            if (!(access$getAdConversions$p2 == null || (adConversionSizes = access$getAdConversions$p2.getAdConversionSizes()) == null)) {
                Intrinsics.checkNotNullExpressionValue(publisherAdView, "adView");
                AdSize adSize = adConversionSizes.get(publisherAdView.getAdSize());
                if (adSize != null) {
                    publisherAdView.setAdSizes(adSize);
                }
            }
            this.$listener.onAdLoaded(new AdContainer(AdStatus.loaded, publisherAdView, (NativeCustomTemplateAd) null, 4, (DefaultConstructorMarker) null));
            return;
        }
        this.$listener.onAdFailed(new AdContainer(AdStatus.failed, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null), -99);
    }
}
