package com.afollestad.date.data;

import androidx.recyclerview.widget.DiffUtil;
import com.afollestad.date.data.MonthItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/afollestad/date/data/MonthItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldItems", "", "Lcom/afollestad/date/data/MonthItem;", "newItems", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthItemCallback.kt */
public final class MonthItemCallback extends DiffUtil.Callback {
    private final List<MonthItem> newItems;
    private final List<MonthItem> oldItems;

    public MonthItemCallback(List<? extends MonthItem> list, List<? extends MonthItem> list2) {
        Intrinsics.checkParameterIsNotNull(list, "oldItems");
        Intrinsics.checkParameterIsNotNull(list2, "newItems");
        this.oldItems = list;
        this.newItems = list2;
    }

    public boolean areItemsTheSame(int i, int i2) {
        MonthItem monthItem = this.oldItems.get(i);
        MonthItem monthItem2 = this.newItems.get(i2);
        if (!(monthItem instanceof MonthItem.WeekHeader) || !(monthItem2 instanceof MonthItem.WeekHeader)) {
            if ((monthItem instanceof MonthItem.DayOfMonth) && (monthItem2 instanceof MonthItem.DayOfMonth)) {
                MonthItem.DayOfMonth dayOfMonth = (MonthItem.DayOfMonth) monthItem;
                MonthItem.DayOfMonth dayOfMonth2 = (MonthItem.DayOfMonth) monthItem2;
                if (!Intrinsics.areEqual((Object) dayOfMonth.getMonth(), (Object) dayOfMonth2.getMonth()) || dayOfMonth.getDate() != dayOfMonth2.getDate()) {
                    return false;
                }
                return true;
            }
        } else if (((MonthItem.WeekHeader) monthItem).getDayOfWeek() == ((MonthItem.WeekHeader) monthItem2).getDayOfWeek()) {
            return true;
        }
        return false;
    }

    public boolean areContentsTheSame(int i, int i2) {
        MonthItem monthItem = this.oldItems.get(i);
        MonthItem monthItem2 = this.newItems.get(i2);
        if (!(monthItem instanceof MonthItem.WeekHeader) || !(monthItem2 instanceof MonthItem.WeekHeader)) {
            if ((monthItem instanceof MonthItem.DayOfMonth) && (monthItem2 instanceof MonthItem.DayOfMonth)) {
                MonthItem.DayOfMonth dayOfMonth = (MonthItem.DayOfMonth) monthItem;
                MonthItem.DayOfMonth dayOfMonth2 = (MonthItem.DayOfMonth) monthItem2;
                if (Intrinsics.areEqual((Object) dayOfMonth.getMonth(), (Object) dayOfMonth2.getMonth()) && dayOfMonth.getDate() == dayOfMonth2.getDate() && dayOfMonth.isSelected() == dayOfMonth2.isSelected()) {
                    return true;
                }
                return false;
            }
        } else if (((MonthItem.WeekHeader) monthItem).getDayOfWeek() == ((MonthItem.WeekHeader) monthItem2).getDayOfWeek()) {
            return true;
        }
        return false;
    }

    public int getOldListSize() {
        return this.oldItems.size();
    }

    public int getNewListSize() {
        return this.newItems.size();
    }
}
