package com.afollestad.date.adapters;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.date.R;
import com.afollestad.date.data.DayOfMonthKt;
import com.afollestad.date.data.MonthItem;
import com.afollestad.date.renderers.MonthItemRenderer;
import com.afollestad.date.util.ViewsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0018\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R4\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R)\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/afollestad/date/adapters/MonthItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/afollestad/date/adapters/MonthItemViewHolder;", "itemRenderer", "Lcom/afollestad/date/renderers/MonthItemRenderer;", "onSelection", "Lkotlin/Function1;", "Lcom/afollestad/date/data/MonthItem$DayOfMonth;", "Lkotlin/ParameterName;", "name", "day", "", "(Lcom/afollestad/date/renderers/MonthItemRenderer;Lkotlin/jvm/functions/Function1;)V", "value", "", "Lcom/afollestad/date/data/MonthItem;", "items", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "getItemCount", "", "getItemId", "", "position", "getItemViewType", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthItemAdapter.kt */
public final class MonthItemAdapter extends RecyclerView.Adapter<MonthItemViewHolder> {
    private final MonthItemRenderer itemRenderer;
    private List<? extends MonthItem> items;
    private final Function1<MonthItem.DayOfMonth, Unit> onSelection;

    public long getItemId(int i) {
        return (long) i;
    }

    public MonthItemAdapter(MonthItemRenderer monthItemRenderer, Function1<? super MonthItem.DayOfMonth, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(monthItemRenderer, "itemRenderer");
        Intrinsics.checkParameterIsNotNull(function1, "onSelection");
        this.itemRenderer = monthItemRenderer;
        this.onSelection = function1;
        setHasStableIds(true);
    }

    public final List<MonthItem> getItems() {
        return this.items;
    }

    public final void setItems(List<? extends MonthItem> list) {
        List<? extends MonthItem> list2 = this.items;
        this.items = list;
        DayOfMonthKt.applyDiffTo(list2, list, this);
    }

    public int getItemViewType(int i) {
        List<? extends MonthItem> list = this.items;
        if ((list != null ? (MonthItem) list.get(i) : null) instanceof MonthItem.WeekHeader) {
            return R.layout.month_grid_header;
        }
        return R.layout.month_grid_item;
    }

    public MonthItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new MonthItemViewHolder(ViewsKt.inflate(viewGroup, i));
    }

    public int getItemCount() {
        List<? extends MonthItem> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void onBindViewHolder(MonthItemViewHolder monthItemViewHolder, int i) {
        MonthItem monthItem;
        Intrinsics.checkParameterIsNotNull(monthItemViewHolder, "holder");
        List<? extends MonthItem> list = this.items;
        if (list == null || (monthItem = (MonthItem) list.get(i)) == null) {
            throw new IllegalStateException("Impossible!".toString());
        }
        MonthItemRenderer monthItemRenderer = this.itemRenderer;
        View view = monthItemViewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        monthItemRenderer.render(monthItem, view, monthItemViewHolder.getTextView(), this.onSelection);
    }
}
