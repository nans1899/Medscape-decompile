package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.View;
import android.widget.Button;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.DropdownFilterViewHolder;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/webmd/medscape/live/explorelivevents/ui/adapters/DropdownFiltersRecyclerViewAdapter$onBindViewHolder$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: DropdownFiltersRecyclerViewAdapter.kt */
final class DropdownFiltersRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ DropdownFilterButton $filter$inlined;
    final /* synthetic */ Button $filterButton;
    final /* synthetic */ DropdownFilterViewHolder $holder$inlined;
    final /* synthetic */ DropdownFilterButton $this_with;
    final /* synthetic */ DropdownFiltersRecyclerViewAdapter this$0;

    DropdownFiltersRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(DropdownFilterButton dropdownFilterButton, Button button, DropdownFiltersRecyclerViewAdapter dropdownFiltersRecyclerViewAdapter, DropdownFilterViewHolder dropdownFilterViewHolder, DropdownFilterButton dropdownFilterButton2) {
        this.$this_with = dropdownFilterButton;
        this.$filterButton = button;
        this.this$0 = dropdownFiltersRecyclerViewAdapter;
        this.$holder$inlined = dropdownFilterViewHolder;
        this.$filter$inlined = dropdownFilterButton2;
    }

    public final void onClick(View view) {
        this.this$0.enableFilter(this.$filterButton, this.$holder$inlined, this.$filter$inlined);
        if (this.$this_with.getCanceled()) {
            this.$this_with.setCanceled(false);
        }
    }
}
