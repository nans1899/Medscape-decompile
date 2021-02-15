package com.medscape.android.homescreen.edit_navigation;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: MenuEditActivity.kt */
final class MenuEditActivity$onCreate$2<T> implements Observer<Boolean> {
    final /* synthetic */ MenuEditActivity this$0;

    MenuEditActivity$onCreate$2(MenuEditActivity menuEditActivity) {
        this.this$0 = menuEditActivity;
    }

    public final void onChanged(Boolean bool) {
        this.this$0.invalidateOptionsMenu();
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            this.this$0.getItemTouchHelper().attachToRecyclerView(this.this$0.getRecyclerView());
        } else {
            this.this$0.getItemTouchHelper().attachToRecyclerView((RecyclerView) null);
        }
        this.this$0.getViewAdapter().setEditMode(bool != null ? bool.booleanValue() : false);
        this.this$0.getViewAdapter().setItems(this.this$0.getEditViewModel().getItems(this.this$0));
        this.this$0.getViewAdapter().notifyItemRangeChanged(0, this.this$0.getEditViewModel().getItems(this.this$0).size());
    }
}
