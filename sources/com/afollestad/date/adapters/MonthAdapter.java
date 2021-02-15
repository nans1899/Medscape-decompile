package com.afollestad.date.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.date.CalendarsKt;
import com.afollestad.date.R;
import com.afollestad.date.data.DateFormatter;
import com.afollestad.date.util.Util;
import com.afollestad.date.util.ViewsKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BJ\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\u0002\u0010\u0010J\b\u0010\u001b\u001a\u00020\u0004H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0004H\u0016J\u0018\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0004H\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004H\u0016J\u0015\u0010%\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0004H\u0000¢\u0006\u0002\b&J\f\u0010'\u001a\u00020(*\u00020\u0004H\u0002R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u000bX\u0004¢\u0006\u0002\n\u0000R*\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004@FX\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/afollestad/date/adapters/MonthAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/afollestad/date/adapters/MonthViewHolder;", "selectionColor", "", "normalFont", "Landroid/graphics/Typeface;", "mediumFont", "dateFormatter", "Lcom/afollestad/date/data/DateFormatter;", "onSelection", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "month", "", "(ILandroid/graphics/Typeface;Landroid/graphics/Typeface;Lcom/afollestad/date/data/DateFormatter;Lkotlin/jvm/functions/Function1;)V", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "value", "selectedMonth", "getSelectedMonth", "()Ljava/lang/Integer;", "setSelectedMonth", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getItemCount", "getItemId", "", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onRowClicked", "onRowClicked$com_afollestad_date_picker", "nameOfMonth", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthAdapter.kt */
public final class MonthAdapter extends RecyclerView.Adapter<MonthViewHolder> {
    private final Calendar calendar = Calendar.getInstance();
    private final DateFormatter dateFormatter;
    private final Typeface mediumFont;
    private final Typeface normalFont;
    private final Function1<Integer, Unit> onSelection;
    private Integer selectedMonth;
    private final int selectionColor;

    public long getItemId(int i) {
        return (long) i;
    }

    public MonthAdapter(int i, Typeface typeface, Typeface typeface2, DateFormatter dateFormatter2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(typeface, "normalFont");
        Intrinsics.checkParameterIsNotNull(typeface2, "mediumFont");
        Intrinsics.checkParameterIsNotNull(dateFormatter2, "dateFormatter");
        Intrinsics.checkParameterIsNotNull(function1, "onSelection");
        this.selectionColor = i;
        this.normalFont = typeface;
        this.mediumFont = typeface2;
        this.dateFormatter = dateFormatter2;
        this.onSelection = function1;
        setHasStableIds(true);
    }

    public final Integer getSelectedMonth() {
        return this.selectedMonth;
    }

    public final void setSelectedMonth(Integer num) {
        Integer num2 = this.selectedMonth;
        this.selectedMonth = num;
        if (num2 != null) {
            notifyItemChanged(num2.intValue());
        }
        if (num != null) {
            notifyItemChanged(num.intValue());
        }
    }

    public MonthViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        Context context = viewGroup.getContext();
        MonthViewHolder monthViewHolder = new MonthViewHolder(ViewsKt.inflate(viewGroup, R.layout.year_list_row), this);
        TextView textView = monthViewHolder.getTextView();
        Util util = Util.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        textView.setTextColor(util.createTextSelector(context, this.selectionColor, false));
        return monthViewHolder;
    }

    public int getItemCount() {
        return this.calendar.getActualMaximum(2) + 1;
    }

    public void onBindViewHolder(MonthViewHolder monthViewHolder, int i) {
        int i2;
        Typeface typeface;
        Intrinsics.checkParameterIsNotNull(monthViewHolder, "holder");
        Integer num = this.selectedMonth;
        boolean z = num != null && i == num.intValue();
        View view = monthViewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "holder.itemView.context");
        Resources resources = context.getResources();
        monthViewHolder.getTextView().setText(nameOfMonth(i));
        monthViewHolder.getTextView().setSelected(z);
        TextView textView = monthViewHolder.getTextView();
        if (z) {
            i2 = R.dimen.year_month_list_text_size_selected;
        } else {
            i2 = R.dimen.year_month_list_text_size;
        }
        textView.setTextSize(0, resources.getDimension(i2));
        TextView textView2 = monthViewHolder.getTextView();
        if (z) {
            typeface = this.mediumFont;
        } else {
            typeface = this.normalFont;
        }
        textView2.setTypeface(typeface);
    }

    public final void onRowClicked$com_afollestad_date_picker(int i) {
        Integer valueOf = Integer.valueOf(i);
        this.onSelection.invoke(Integer.valueOf(valueOf.intValue()));
        setSelectedMonth(valueOf);
    }

    private final String nameOfMonth(int i) {
        Calendar calendar2 = this.calendar;
        Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
        CalendarsKt.setMonth(calendar2, i);
        DateFormatter dateFormatter2 = this.dateFormatter;
        Calendar calendar3 = this.calendar;
        Intrinsics.checkExpressionValueIsNotNull(calendar3, "calendar");
        return dateFormatter2.month(calendar3);
    }
}
