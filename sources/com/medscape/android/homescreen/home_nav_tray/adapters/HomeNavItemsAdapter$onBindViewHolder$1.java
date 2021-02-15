package com.medscape.android.homescreen.home_nav_tray.adapters;

import android.view.View;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigModel;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import com.medscape.android.provider.SharedPreferenceProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeNavItemsAdapter.kt */
final class HomeNavItemsAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ NavItem $item;
    final /* synthetic */ int $position;
    final /* synthetic */ HomeNavItemsAdapter this$0;

    HomeNavItemsAdapter$onBindViewHolder$1(HomeNavItemsAdapter homeNavItemsAdapter, NavItem navItem, int i) {
        this.this$0 = homeNavItemsAdapter;
        this.$item = navItem;
        this.$position = i;
    }

    public final void onClick(View view) {
        INavItemClickListener clickListener = this.this$0.getClickListener();
        if (clickListener != null) {
            clickListener.onNavItemClicked(this.$item, this.$position);
        }
        FeatureConfigModel featureConfigModel = this.this$0.getReferenceModel().get(this.$item.getType());
        if (Intrinsics.areEqual((Object) featureConfigModel != null ? featureConfigModel.getType() : null, (Object) this.$item.getType())) {
            StringBuilder sb = new StringBuilder();
            sb.append(featureConfigModel.getType());
            sb.append("_");
            AuthenticationManager instance = AuthenticationManager.getInstance(MedscapeApplication.get());
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.ge…edscapeApplication.get())");
            sb.append(instance.getMaskedGuid());
            SharedPreferenceProvider.get().save(sb.toString(), true);
            this.$item.setShouldDisplayRedDot(false);
            this.this$0.notifyDataSetChanged();
        }
    }
}
