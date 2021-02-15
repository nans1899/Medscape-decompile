package com.medscape.android.homescreen.edit_navigation.adapter;

import android.view.View;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: EditNavAdapter.kt */
final class EditNavAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ NavItem $item;
    final /* synthetic */ int $position;
    final /* synthetic */ EditNavAdapter this$0;

    EditNavAdapter$onBindViewHolder$1(EditNavAdapter editNavAdapter, NavItem navItem, int i) {
        this.this$0 = editNavAdapter;
        this.$item = navItem;
        this.$position = i;
    }

    public final void onClick(View view) {
        INavItemClickListener clickListener;
        if (!this.this$0.getEditMode() && (clickListener = this.this$0.getClickListener()) != null) {
            clickListener.onNavItemClicked(this.$item, this.$position);
        }
    }
}
