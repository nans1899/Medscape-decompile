package com.medscape.android.contentviewer;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.medscape.android.R;
import com.medscape.android.activity.calc.ads.AdParams;
import com.medscape.android.ads.NativeADViewModel;
import com.wbmd.ads.AdManager;
import com.wbmd.ads.IAdListener;
import com.wbmd.ads.model.AdContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t¸\u0006\u0000"}, d2 = {"com/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment$requestBottomBannerAd$2$1", "Lcom/wbmd/ads/IAdListener;", "onAdFailed", "", "adContainer", "Lcom/wbmd/ads/model/AdContainer;", "errorCode", "", "onAdLoaded", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
public final class ClinicalReferenceArticleLandingFragment$requestBottomBannerAd$$inlined$let$lambda$1 implements IAdListener {
    final /* synthetic */ AdManager $adManager$inlined;
    final /* synthetic */ AdParams $adParams$inlined;
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$requestBottomBannerAd$$inlined$let$lambda$1(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment, AdManager adManager, AdParams adParams) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
        this.$adManager$inlined = adManager;
        this.$adParams$inlined = adParams;
    }

    public void onAdLoaded(AdContainer adContainer) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        if (this.this$0._$_findCachedViewById(R.id.ad_container_layout) != null) {
            View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.ad_container_layout);
            Intrinsics.checkNotNullExpressionValue(_$_findCachedViewById, "ad_container_layout");
            _$_findCachedViewById.setVisibility(0);
            if (adContainer.getAdView() != null) {
                LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(R.id.ad_layout);
                Intrinsics.checkNotNullExpressionValue(linearLayout, "ad_layout");
                linearLayout.setVisibility(0);
                View _$_findCachedViewById2 = this.this$0._$_findCachedViewById(R.id.native_ad_layout);
                Intrinsics.checkNotNullExpressionValue(_$_findCachedViewById2, "native_ad_layout");
                _$_findCachedViewById2.setVisibility(8);
                PublisherAdView adView = adContainer.getAdView();
                if (adView != null) {
                    adView.setVisibility(0);
                }
                ((FrameLayout) this.this$0._$_findCachedViewById(R.id.ad_root_view)).removeAllViews();
                ((FrameLayout) this.this$0._$_findCachedViewById(R.id.ad_root_view)).addView(adContainer.getAdView());
            } else if (adContainer.getNativeAd() != null) {
                LinearLayout linearLayout2 = (LinearLayout) this.this$0._$_findCachedViewById(R.id.ad_layout);
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "ad_layout");
                linearLayout2.setVisibility(8);
                View _$_findCachedViewById3 = this.this$0._$_findCachedViewById(R.id.native_ad_layout);
                Intrinsics.checkNotNullExpressionValue(_$_findCachedViewById3, "native_ad_layout");
                _$_findCachedViewById3.setVisibility(0);
                NativeADViewModel nativeADViewModel = new NativeADViewModel();
                nativeADViewModel.bindVariables(adContainer.getNativeAd());
                ViewDataBinding binding = this.this$0.getBinding();
                if (binding != null) {
                    binding.setVariable(1, nativeADViewModel);
                }
            }
        }
    }

    public void onAdFailed(AdContainer adContainer, int i) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        if (this.this$0._$_findCachedViewById(R.id.ad_container_layout) != null) {
            View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.ad_container_layout);
            Intrinsics.checkNotNullExpressionValue(_$_findCachedViewById, "ad_container_layout");
            _$_findCachedViewById.setVisibility(8);
        }
    }
}
