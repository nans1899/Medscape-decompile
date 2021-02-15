package com.medscape.android.drugs.details.views;

import android.view.View;
import com.medscape.android.R;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/medscape/android/drugs/details/views/DrugDetailsActivity$getAd$1", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "onAdFailedToLoad", "", "errorCode", "", "onAdLoaded", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
public final class DrugDetailsActivity$getAd$1 implements INativeDFPAdLoadListener {
    final /* synthetic */ DrugDetailsActivity this$0;

    DrugDetailsActivity$getAd$1(DrugDetailsActivity drugDetailsActivity) {
        this.this$0 = drugDetailsActivity;
    }

    public void onAdLoaded(NativeDFPAD nativeDFPAD) {
        Intrinsics.checkNotNullParameter(nativeDFPAD, "nativeDFPAD");
        this.this$0.mIsBannerADavailable = true;
        if (this.this$0.adLayout != null) {
            ADBindingHelper.Companion.bindCombinedAD(this.this$0.adLayout, nativeDFPAD);
        }
        if (this.this$0.nativeAdAction.isForceHideAD) {
            View access$getAdLayout$p = this.this$0.adLayout;
            Intrinsics.checkNotNullExpressionValue(access$getAdLayout$p, "adLayout");
            access$getAdLayout$p.setVisibility(8);
            Util.setContainerRule(false, DrugDetailsActivity.access$getContentContainer$p(this.this$0), R.id.ad);
            return;
        }
        View access$getAdLayout$p2 = this.this$0.adLayout;
        Intrinsics.checkNotNullExpressionValue(access$getAdLayout$p2, "adLayout");
        access$getAdLayout$p2.setVisibility(0);
        Util.setContainerRule(true, DrugDetailsActivity.access$getContentContainer$p(this.this$0), R.id.ad);
    }

    public void onAdFailedToLoad(int i) {
        this.this$0.onAdNotAvilable();
    }
}
