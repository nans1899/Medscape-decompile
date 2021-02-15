package com.medscape.android.drugs.viewholders;

import android.content.Context;
import android.view.View;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.drugs.model.DrugManufacturer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugManufacturerViewHolder.kt */
final class DrugManufacturerViewHolder$bindDrugManufacturerAd$1 implements View.OnClickListener {
    final /* synthetic */ DrugManufacturer $dm;
    final /* synthetic */ DrugManufacturerViewHolder this$0;

    DrugManufacturerViewHolder$bindDrugManufacturerAd$1(DrugManufacturerViewHolder drugManufacturerViewHolder, DrugManufacturer drugManufacturer) {
        this.this$0 = drugManufacturerViewHolder;
        this.$dm = drugManufacturer;
    }

    public final void onClick(View view) {
        WebviewUtil.Companion companion = WebviewUtil.Companion;
        View view2 = this.this$0.itemView;
        Intrinsics.checkNotNullExpressionValue(view2, "itemView");
        Context context = view2.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "itemView.context");
        String url = this.$dm.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "dm.url");
        companion.launchPlainWebView(context, url, this.$dm.getTitle(), "", "", "", "", 1, false);
    }
}
