package com.medscape.android.homescreen.edit_navigation.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/homescreen/edit_navigation/view_holders/EditNavViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "navItem", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "editMode", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EditNavViewHolder.kt */
public final class EditNavViewHolder extends RecyclerView.ViewHolder {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EditNavViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void bind(NavItem navItem, boolean z) {
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        View view = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "itemView");
        TextView textView = (TextView) view.findViewById(R.id.rowTitle);
        Intrinsics.checkNotNullExpressionValue(textView, "itemView.rowTitle");
        textView.setText(navItem.getText());
        if (z) {
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            ImageView imageView = (ImageView) view2.findViewById(R.id.editButton);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.editButton");
            imageView.setVisibility(0);
            return;
        }
        View view3 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view3, "itemView");
        ImageView imageView2 = (ImageView) view3.findViewById(R.id.editButton);
        Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.editButton");
        imageView2.setVisibility(4);
    }
}
