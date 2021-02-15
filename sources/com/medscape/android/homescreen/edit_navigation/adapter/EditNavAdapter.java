package com.medscape.android.homescreen.edit_navigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.R;
import com.medscape.android.homescreen.edit_navigation.view_holders.EditNavViewHolder;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/medscape/android/homescreen/edit_navigation/view_holders/EditNavViewHolder;", "items", "", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "editMode", "", "clickListener", "Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "(Ljava/util/List;ZLcom/medscape/android/homescreen/interfaces/INavItemClickListener;)V", "getClickListener", "()Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "getEditMode", "()Z", "setEditMode", "(Z)V", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EditNavAdapter.kt */
public final class EditNavAdapter extends RecyclerView.Adapter<EditNavViewHolder> {
    private final INavItemClickListener clickListener;
    private boolean editMode;
    private List<NavItem> items;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EditNavAdapter(List list, boolean z, INavItemClickListener iNavItemClickListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list, (i & 2) != 0 ? false : z, iNavItemClickListener);
    }

    public final boolean getEditMode() {
        return this.editMode;
    }

    public final List<NavItem> getItems() {
        return this.items;
    }

    public final void setEditMode(boolean z) {
        this.editMode = z;
    }

    public final void setItems(List<NavItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.items = list;
    }

    public EditNavAdapter(List<NavItem> list, boolean z, INavItemClickListener iNavItemClickListener) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        this.items = list;
        this.editMode = z;
        this.clickListener = iNavItemClickListener;
    }

    public final INavItemClickListener getClickListener() {
        return this.clickListener;
    }

    public EditNavViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.edit_nav_item_layout, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new EditNavViewHolder(inflate);
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(EditNavViewHolder editNavViewHolder, int i) {
        Intrinsics.checkNotNullParameter(editNavViewHolder, "holder");
        NavItem navItem = this.items.get(i);
        editNavViewHolder.bind(navItem, this.editMode);
        editNavViewHolder.itemView.setOnClickListener(new EditNavAdapter$onBindViewHolder$1(this, navItem, i));
    }
}
