package com.medscape.android.activity.calc;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.util.Util;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.wbmd.qxcalculator.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/medscape/android/activity/calc/MedscapeCalculatorActivity$requestBottomBannerAD$1", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "onAdFailedToLoad", "", "errorCode", "", "onAdLoaded", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeCalculatorActivity.kt */
public final class MedscapeCalculatorActivity$requestBottomBannerAD$1 implements INativeDFPAdLoadListener {
    final /* synthetic */ MedscapeCalculatorActivity this$0;

    MedscapeCalculatorActivity$requestBottomBannerAD$1(MedscapeCalculatorActivity medscapeCalculatorActivity) {
        this.this$0 = medscapeCalculatorActivity;
    }

    public void onAdLoaded(NativeDFPAD nativeDFPAD) {
        Intrinsics.checkNotNullParameter(nativeDFPAD, "nativeDFPAD");
        if (this.this$0.getAdLayout().getParent() != null) {
            this.this$0.getMContainer().removeView(this.this$0.getAdLayout());
        }
        this.this$0.getMContainer().addView(this.this$0.getAdLayout());
        this.this$0.setAdded(true);
        this.this$0.getAdLayout().setVisibility(0);
        ADBindingHelper.Companion.bindCombinedAD(this.this$0.getAdLayout(), nativeDFPAD);
        Util.setContainerRule(true, this.this$0.getMContainer(), R.id.ad);
        UtilCalc utilCalc = new UtilCalc();
        QxRecyclerViewAdapter access$getAdapter$p = this.this$0.adapter;
        Intrinsics.checkNotNullExpressionValue(access$getAdapter$p, "adapter");
        RecyclerView.Adapter adapter = access$getAdapter$p;
        QxRecyclerView access$getListView$p = this.this$0.listView;
        Intrinsics.checkNotNullExpressionValue(access$getListView$p, "listView");
        RecyclerView recyclerView = access$getListView$p;
        View adLayout = this.this$0.getAdLayout();
        QxRecyclerViewAdapter access$getAdapter$p2 = this.this$0.adapter;
        if (access$getAdapter$p2 != null) {
            utilCalc.setBannerAdVisibility(adapter, recyclerView, adLayout, ((com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter) access$getAdapter$p2).isInlineADcallComplete());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
    }

    public void onAdFailedToLoad(int i) {
        String tag = this.this$0.getTAG();
        Log.d(tag, "BannerAD failed to load with errorCode: " + i);
    }
}