package com.medscape.android.drugs.details.adapters;

import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.drugs.details.datamodels.InlineAdLineItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/medscape/android/drugs/details/adapters/DrugSectionAdapter$requestShareThroughAD$1", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "onAdFailedToLoad", "", "errorCode", "", "onAdLoaded", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugSectionAdapter.kt */
public final class DrugSectionAdapter$requestShareThroughAD$1 implements INativeDFPAdLoadListener {
    final /* synthetic */ InlineAdLineItem $sharethroughADItem;
    final /* synthetic */ DrugSectionAdapter this$0;

    DrugSectionAdapter$requestShareThroughAD$1(DrugSectionAdapter drugSectionAdapter, InlineAdLineItem inlineAdLineItem) {
        this.this$0 = drugSectionAdapter;
        this.$sharethroughADItem = inlineAdLineItem;
    }

    public void onAdLoaded(NativeDFPAD nativeDFPAD) {
        Intrinsics.checkNotNullParameter(nativeDFPAD, "nativeDFPAD");
        this.$sharethroughADItem.setAdView(nativeDFPAD);
        this.this$0.setSharethroughADLoadingDone(true);
        this.this$0.notifyDataSetChanged();
    }

    public void onAdFailedToLoad(int i) {
        this.this$0.setSharethroughADLoadingDone(true);
        this.$sharethroughADItem.setAdView((NativeDFPAD) null);
        this.this$0.notifyDataSetChanged();
    }
}
