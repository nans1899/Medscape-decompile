package com.wbmd.ads;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "kotlin.jvm.PlatformType", "onCustomTemplateAdLoaded", "com/wbmd/ads/AdManager$buildAdLoader$3$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
final class AdManager$buildAdLoader$$inlined$let$lambda$1 implements NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener {
    final /* synthetic */ AdLoader.Builder $adLoader$inlined;
    final /* synthetic */ IAdListener $listener$inlined;

    AdManager$buildAdLoader$$inlined$let$lambda$1(AdLoader.Builder builder, IAdListener iAdListener) {
        this.$adLoader$inlined = builder;
        this.$listener$inlined = iAdListener;
    }

    public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.$listener$inlined.onAdLoaded(new AdContainer(AdStatus.loaded, (PublisherAdView) null, nativeCustomTemplateAd, 2, (DefaultConstructorMarker) null));
    }
}
