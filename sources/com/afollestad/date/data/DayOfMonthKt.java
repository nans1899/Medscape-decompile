package com.afollestad.date.data;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0002\u001a\u00020\u0003*\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"NO_DATE", "", "applyDiffTo", "", "", "Lcom/afollestad/date/data/MonthItem;", "withNewDays", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: DayOfMonth.kt */
public final class DayOfMonthKt {
    public static final int NO_DATE = -1;

    public static final void applyDiffTo(List<? extends MonthItem> list, List<? extends MonthItem> list2, RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        if (list == null || list2 == null) {
            adapter.notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new MonthItemCallback(list, list2));
        Intrinsics.checkExpressionValueIsNotNull(calculateDiff, "DiffUtil.calculateDiff(\n…thNewDays\n        )\n    )");
        calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) adapter);
    }
}
