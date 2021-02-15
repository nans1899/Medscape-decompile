package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.View;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.util.DateFilterManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.DayOfWeek;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: FilterButtonRecyclerViewAdapter.kt */
final class FilterButtonRecyclerViewAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ String $item;
    final /* synthetic */ FilterButtonRecyclerViewAdapter this$0;

    FilterButtonRecyclerViewAdapter$onBindViewHolder$1(FilterButtonRecyclerViewAdapter filterButtonRecyclerViewAdapter, String str) {
        this.this$0 = filterButtonRecyclerViewAdapter;
        this.$item = str;
    }

    public final void onClick(View view) {
        OnFilterSelectedListener access$getListener$p;
        String str = this.$item;
        if (Intrinsics.areEqual((Object) str, (Object) this.this$0.todayFilter)) {
            OnFilterSelectedListener access$getListener$p2 = this.this$0.listener;
            if (access$getListener$p2 != null) {
                access$getListener$p2.onClick(this.this$0.todayFilter, DateFilterManager.INSTANCE.getToday());
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) this.this$0.tomorrowFilter)) {
            OnFilterSelectedListener access$getListener$p3 = this.this$0.listener;
            if (access$getListener$p3 != null) {
                access$getListener$p3.onClick(this.this$0.tomorrowFilter, DateFilterManager.INSTANCE.getTommorow());
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) this.this$0.thisWeekFilter)) {
            OnFilterSelectedListener access$getListener$p4 = this.this$0.listener;
            if (access$getListener$p4 != null) {
                access$getListener$p4.onClick(this.this$0.thisWeekFilter, DateFilterManager.INSTANCE.getThisWeek());
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) this.this$0.thisWeekendFilter)) {
            OnFilterSelectedListener access$getListener$p5 = this.this$0.listener;
            if (access$getListener$p5 != null) {
                access$getListener$p5.onClick(this.this$0.thisWeekendFilter, DateFilterManager.INSTANCE.getThisWeekend());
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) this.this$0.nextWeekFilter)) {
            OnFilterSelectedListener access$getListener$p6 = this.this$0.listener;
            if (access$getListener$p6 != null) {
                access$getListener$p6.onClick(this.this$0.nextWeekFilter, DateFilterManager.INSTANCE.getNextWeek(DayOfWeek.SUNDAY));
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) this.this$0.allUpcomingFilter) && (access$getListener$p = this.this$0.listener) != null) {
            access$getListener$p.onClick(this.this$0.allUpcomingFilter, DateFilterManager.INSTANCE.allUpcoming());
        }
    }
}
