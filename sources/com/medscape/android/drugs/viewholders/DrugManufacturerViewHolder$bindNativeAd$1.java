package com.medscape.android.drugs.viewholders;

import android.content.Context;
import android.view.View;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugManufacturerViewHolder.kt */
final class DrugManufacturerViewHolder$bindNativeAd$1 implements View.OnClickListener {
    final /* synthetic */ CharSequence $clickURL;
    final /* synthetic */ Context $context;
    final /* synthetic */ NativeCustomTemplateAd $nativeAD;

    DrugManufacturerViewHolder$bindNativeAd$1(NativeCustomTemplateAd nativeCustomTemplateAd, CharSequence charSequence, Context context) {
        this.$nativeAD = nativeCustomTemplateAd;
        this.$clickURL = charSequence;
        this.$context = context;
    }

    public final void onClick(View view) {
        this.$nativeAD.performClick("Title");
        Map hashMap = new HashMap();
        CharSequence charSequence = this.$clickURL;
        Intrinsics.checkNotNullExpressionValue(charSequence, "clickURL");
        hashMap.put("exiturl", charSequence);
        OmnitureManager.get().trackModule(this.$context, Constants.OMNITURE_CHANNEL_REFERENCE, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "link", hashMap);
    }
}
