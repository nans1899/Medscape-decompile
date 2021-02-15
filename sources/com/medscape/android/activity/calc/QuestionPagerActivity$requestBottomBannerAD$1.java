package com.medscape.android.activity.calc;

import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/medscape/android/activity/calc/QuestionPagerActivity$requestBottomBannerAD$1", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "onAdFailedToLoad", "", "errorCode", "", "onAdLoaded", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionPagerActivity.kt */
public final class QuestionPagerActivity$requestBottomBannerAD$1 implements INativeDFPAdLoadListener {
    final /* synthetic */ QuestionPagerActivity this$0;

    public void onAdFailedToLoad(int i) {
    }

    QuestionPagerActivity$requestBottomBannerAD$1(QuestionPagerActivity questionPagerActivity) {
        this.this$0 = questionPagerActivity;
    }

    public void onAdLoaded(NativeDFPAD nativeDFPAD) {
        RecyclerView listView;
        Intrinsics.checkNotNullParameter(nativeDFPAD, "nativeDFPAD");
        this.this$0.getMContainer().removeView(this.this$0.getAdLayout());
        this.this$0.getMContainer().addView(this.this$0.getAdLayout());
        this.this$0.isAdded = true;
        this.this$0.getAdLayout().setVisibility(0);
        ADBindingHelper.Companion.bindCombinedAD(this.this$0.getAdLayout(), nativeDFPAD);
        Util.setContainerRule(true, this.this$0.getMContainer(), R.id.ad);
        if (this.this$0.getAdapter() != null && (listView = this.this$0.getListView()) != null) {
            UtilCalc utilCalc = new UtilCalc();
            QxRecyclerViewAdapter adapter = this.this$0.getAdapter();
            Intrinsics.checkNotNull(adapter);
            utilCalc.setBannerAdVisibility(adapter, listView, this.this$0.getAdLayout(), this.this$0.isInlineADcallComplete());
        }
    }
}
