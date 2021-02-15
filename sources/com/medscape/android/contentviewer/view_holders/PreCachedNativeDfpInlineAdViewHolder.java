package com.medscape.android.contentviewer.view_holders;

import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.R;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/contentviewer/view_holders/PreCachedNativeDfpInlineAdViewHolder;", "Lcom/medscape/android/contentviewer/view_holders/DataViewHolder;", "mAdContainer", "Landroid/view/View;", "(Landroid/view/View;)V", "mRootLayout", "applyFullScreenADWidth", "", "adView", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "bind", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PreCachedNativeDfpInlineAdViewHolder.kt */
public final class PreCachedNativeDfpInlineAdViewHolder extends DataViewHolder {
    private View mAdContainer;
    private View mRootLayout;

    public String toString() {
        return "inlineadviewholder";
    }

    public PreCachedNativeDfpInlineAdViewHolder(View view) {
        super(view);
        this.mAdContainer = view;
        this.mRootLayout = view != null ? view.findViewById(R.id.ad_root_layout) : null;
    }

    public final void bind(NativeDFPAD nativeDFPAD) {
        View view = null;
        PublisherAdView dfpAD = nativeDFPAD != null ? nativeDFPAD.getDfpAD() : null;
        NativeCustomTemplateAd nativeAD = nativeDFPAD != null ? nativeDFPAD.getNativeAD() : null;
        if (!(nativeDFPAD == null || dfpAD == null)) {
            View view2 = this.mAdContainer;
            if (view2 != null) {
                view = view2.findViewById(R.id.dfp_adLabel);
            }
            if (view != null) {
                AdSize[] adSizeArr = DFPAdAction.advLabelSizes;
                Intrinsics.checkNotNullExpressionValue(adSizeArr, "advLabelSizes");
                if (ArraysKt.contains((T[]) adSizeArr, dfpAD.getAdSize())) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(8);
                }
            }
            applyFullScreenADWidth(dfpAD);
        }
        ADBindingHelper.Companion.bindCombinedAD(this.mRootLayout, nativeDFPAD);
        if (nativeAD != null) {
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            LinearLayout linearLayout = (LinearLayout) view3.findViewById(R.id.native_ad_container);
            Intrinsics.checkNotNullExpressionValue(linearLayout, "itemView.native_ad_container");
            linearLayout.setVisibility(0);
            View view4 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view4, "itemView");
            View findViewById = view4.findViewById(R.id.top_margin);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.top_margin");
            findViewById.setVisibility(0);
            View view5 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view5, "itemView");
            View findViewById2 = view5.findViewById(R.id.bottom_margin);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.bottom_margin");
            findViewById2.setVisibility(8);
            return;
        }
        View view6 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view6, "itemView");
        LinearLayout linearLayout2 = (LinearLayout) view6.findViewById(R.id.native_ad_container);
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "itemView.native_ad_container");
        linearLayout2.setVisibility(8);
        View view7 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view7, "itemView");
        View findViewById3 = view7.findViewById(R.id.top_margin);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.top_margin");
        findViewById3.setVisibility(8);
        View view8 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view8, "itemView");
        View findViewById4 = view8.findViewById(R.id.bottom_margin);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.bottom_margin");
        findViewById4.setVisibility(8);
    }

    private final void applyFullScreenADWidth(PublisherAdView publisherAdView) {
        if (publisherAdView != null && Intrinsics.areEqual((Object) publisherAdView.getAdSize(), (Object) DFPAdAction.ADSIZE_3x1)) {
            publisherAdView.setAdSizes(new AdSize(Util.getDisplayWidthInDp(publisherAdView.getContext()), 400));
        }
    }
}
