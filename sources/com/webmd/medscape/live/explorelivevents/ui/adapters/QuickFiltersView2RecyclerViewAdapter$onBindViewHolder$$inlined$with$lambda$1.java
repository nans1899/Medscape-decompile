package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.View;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.QuickFilterViewHolder;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/webmd/medscape/live/explorelivevents/ui/adapters/QuickFiltersView2RecyclerViewAdapter$onBindViewHolder$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: QuickFiltersView2RecyclerViewAdapter.kt */
final class QuickFiltersView2RecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ QuickFilterViewHolder $holder$inlined;
    final /* synthetic */ int $position$inlined;
    final /* synthetic */ QuickFiltersView2RecyclerViewAdapter this$0;

    QuickFiltersView2RecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(QuickFiltersView2RecyclerViewAdapter quickFiltersView2RecyclerViewAdapter, QuickFilterViewHolder quickFilterViewHolder, int i) {
        this.this$0 = quickFiltersView2RecyclerViewAdapter;
        this.$holder$inlined = quickFilterViewHolder;
        this.$position$inlined = i;
    }

    public final void onClick(View view) {
        OnFilterSelectedListener access$getListener$p;
        int i = this.$position$inlined;
        if (i == 0) {
            OnFilterSelectedListener access$getListener$p2 = this.this$0.listener;
            if (access$getListener$p2 != null) {
                access$getListener$p2.onSpecialtyFilterSelected();
            }
        } else if (i == 1) {
            OnFilterSelectedListener access$getListener$p3 = this.this$0.listener;
            if (access$getListener$p3 != null) {
                access$getListener$p3.onDateRangeFilterSelected();
            }
        } else if (i == 2 && (access$getListener$p = this.this$0.listener) != null) {
            access$getListener$p.onLocationFilterSelected();
        }
    }
}
