package com.medscape.android.activity.calc.adapters;

import android.util.Log;
import com.medscape.android.activity.calc.ads.InlineAdLoaded;
import com.wbmd.ads.IAdListener;
import com.wbmd.ads.model.AdContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/medscape/android/activity/calc/adapters/QxRecyclerViewAdapter$requestAD$1", "Lcom/wbmd/ads/IAdListener;", "onAdFailed", "", "adContainer", "Lcom/wbmd/ads/model/AdContainer;", "errorCode", "", "onAdLoaded", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QxRecyclerViewAdapter.kt */
public final class QxRecyclerViewAdapter$requestAD$1 implements IAdListener {
    final /* synthetic */ QxRecyclerViewAdapter this$0;

    QxRecyclerViewAdapter$requestAD$1(QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        this.this$0 = qxRecyclerViewAdapter;
    }

    public void onAdLoaded(AdContainer adContainer) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        this.this$0.getAdsSdkSet().add(adContainer);
        this.this$0.setInlineAdRequested(false);
        this.this$0.setInlineADcallComplete(true);
        this.this$0.notifyDataSetChanged();
        InlineAdLoaded listener = this.this$0.getListener();
        if (listener != null) {
            listener.onInlineAdLoaded();
        }
        Log.d("ADS_SDK**", "Ad Loaded");
    }

    public void onAdFailed(AdContainer adContainer, int i) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        this.this$0.setInlineAdRequested(false);
        this.this$0.setInlineADcallComplete(false);
        com.wbmd.qxcalculator.util.Log.d("ADS_SDK", "AD Load Failed, Google Error code: " + i);
    }
}
