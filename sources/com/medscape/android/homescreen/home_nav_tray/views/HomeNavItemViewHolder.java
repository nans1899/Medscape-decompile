package com.medscape.android.homescreen.home_nav_tray.views;

import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/views/HomeNavItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "item", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeNavItemViewHolder.kt */
public final class HomeNavItemViewHolder extends RecyclerView.ViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HomeNavItemViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void bind(NavItem navItem) {
        if (navItem != null) {
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            ((ImageView) view.findViewById(R.id.home_nav_icon)).setImageDrawable(ContextCompat.getDrawable(view2.getContext(), navItem.getDrawable()));
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            FixedWidthTextView fixedWidthTextView = (FixedWidthTextView) view3.findViewById(R.id.home_nav_text);
            Intrinsics.checkNotNullExpressionValue(fixedWidthTextView, "itemView.home_nav_text");
            fixedWidthTextView.setText(navItem.getText());
            if (navItem.getShouldDisplayRedDot()) {
                View view4 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view4, "itemView");
                View findViewById = view4.findViewById(R.id.home_nav_dot);
                Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.home_nav_dot");
                findViewById.setVisibility(0);
                return;
            }
            View view5 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view5, "itemView");
            View findViewById2 = view5.findViewById(R.id.home_nav_dot);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.home_nav_dot");
            findViewById2.setVisibility(8);
        }
    }
}
