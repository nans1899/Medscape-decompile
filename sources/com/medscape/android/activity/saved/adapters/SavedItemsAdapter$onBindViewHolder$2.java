package com.medscape.android.activity.saved.adapters;

import android.view.View;
import com.medscape.android.activity.saved.adapters.SavedItemsAdapter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: SavedItemsAdapter.kt */
final class SavedItemsAdapter$onBindViewHolder$2 implements View.OnClickListener {
    final /* synthetic */ SavedItemsAdapter.ViewHolder $holder;
    final /* synthetic */ int $position;
    final /* synthetic */ SavedItemsAdapter this$0;

    SavedItemsAdapter$onBindViewHolder$2(SavedItemsAdapter savedItemsAdapter, SavedItemsAdapter.ViewHolder viewHolder, int i) {
        this.this$0 = savedItemsAdapter;
        this.$holder = viewHolder;
        this.$position = i;
    }

    public final void onClick(View view) {
        this.this$0.getDeleteListener().onSavedItemDelete(this.$holder.getNewsDelete(), this.$position);
    }
}
