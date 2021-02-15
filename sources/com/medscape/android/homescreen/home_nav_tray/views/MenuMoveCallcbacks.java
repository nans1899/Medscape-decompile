package com.medscape.android.homescreen.home_nav_tray.views;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.homescreen.edit_navigation.adapter.EditNavAdapter;
import com.medscape.android.provider.SharedPreferenceProvider;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/views/MenuMoveCallcbacks;", "Landroidx/recyclerview/widget/ItemTouchHelper$Callback;", "adapter", "Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;", "(Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;)V", "getAdapter", "()Lcom/medscape/android/homescreen/edit_navigation/adapter/EditNavAdapter;", "setAdapter", "clearView", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getMovementFlags", "", "onMove", "", "target", "onSwiped", "direction", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MenuMoveCallcbacks.kt */
public final class MenuMoveCallcbacks extends ItemTouchHelper.Callback {
    private EditNavAdapter adapter;

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public MenuMoveCallcbacks(EditNavAdapter editNavAdapter) {
        Intrinsics.checkNotNullParameter(editNavAdapter, "adapter");
        this.adapter = editNavAdapter;
    }

    public final EditNavAdapter getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(EditNavAdapter editNavAdapter) {
        Intrinsics.checkNotNullParameter(editNavAdapter, "<set-?>");
        this.adapter = editNavAdapter;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        return ItemTouchHelper.Callback.makeFlag(2, 51);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(viewHolder2, "target");
        this.adapter.getItems().remove(viewHolder.getAdapterPosition());
        this.adapter.getItems().add(viewHolder2.getAdapterPosition(), this.adapter.getItems().get(viewHolder.getAdapterPosition()));
        this.adapter.notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        SharedPreferenceProvider.get().save(Constants.HOME_NAV_ORDER, CollectionsKt.joinToString$default(this.adapter.getItems(), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, MenuMoveCallcbacks$clearView$order$1.INSTANCE, 30, (Object) null));
    }
}
