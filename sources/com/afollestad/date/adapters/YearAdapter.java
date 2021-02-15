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
import com.afollestad.date.util.Util;
import com.afollestad.date.util.ViewsKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BB\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t¢\u0006\u0002\u0010\u000eJ\b\u0010\u0018\u001a\u00020\u0007H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\r\u0010\u001c\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0012J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0007H\u0016J\u0018\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0007H\u0016J\u0015\u0010#\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0007H\u0000¢\u0006\u0002\b$J\f\u0010%\u001a\u00020\u0007*\u00020\u0007H\u0002J\f\u0010&\u001a\u00020\u0007*\u00020\u0007H\u0002R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R)\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tX\u0004¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007@FX\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0017X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/afollestad/date/adapters/YearAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/afollestad/date/adapters/YearViewHolder;", "normalFont", "Landroid/graphics/Typeface;", "mediumFont", "selectionColor", "", "onSelection", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "year", "", "(Landroid/graphics/Typeface;Landroid/graphics/Typeface;ILkotlin/jvm/functions/Function1;)V", "value", "selectedYear", "getSelectedYear", "()Ljava/lang/Integer;", "setSelectedYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "yearRange", "Lkotlin/Pair;", "getItemCount", "getItemId", "", "position", "getSelectedPosition", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onRowClicked", "onRowClicked$com_afollestad_date_picker", "asPosition", "asYear", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: YearAdapter.kt */
public final class YearAdapter extends RecyclerView.Adapter<YearViewHolder> {
    private final Typeface mediumFont;
    private final Typeface normalFont;
    private final Function1<Integer, Unit> onSelection;
    private Integer selectedYear;
    private final int selectionColor;
    private final Pair<Integer, Integer> yearRange;

    public YearAdapter(Typeface typeface, Typeface typeface2, int i, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(typeface, "normalFont");
        Intrinsics.checkParameterIsNotNull(typeface2, "mediumFont");
        Intrinsics.checkParameterIsNotNull(function1, "onSelection");
        this.normalFont = typeface;
        this.mediumFont = typeface2;
        this.selectionColor = i;
        this.onSelection = function1;
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "Calendar.getInstance()");
        int year = CalendarsKt.getYear(instance);
        this.yearRange = new Pair<>(Integer.valueOf(year - 100), Integer.valueOf(year + 100));
        setHasStableIds(true);
    }

    public final Integer getSelectedYear() {
        return this.selectedYear;
    }

    public final void setSelectedYear(Integer num) {
        Integer num2 = this.selectedYear;
        this.selectedYear = num;
        if (num2 != null) {
            notifyItemChanged(asPosition(num2.intValue()));
        }
        if (num != null) {
            notifyItemChanged(asPosition(num.intValue()));
        }
    }

    public long getItemId(int i) {
        return (long) asYear(i);
    }

    public YearViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        Context context = viewGroup.getContext();
        YearViewHolder yearViewHolder = new YearViewHolder(ViewsKt.inflate(viewGroup, R.layout.year_list_row), this);
        TextView textView = yearViewHolder.getTextView();
        Util util = Util.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        textView.setTextColor(util.createTextSelector(context, this.selectionColor, false));
        return yearViewHolder;
    }

    public int getItemCount() {
        return this.yearRange.getSecond().intValue() - this.yearRange.getFirst().intValue();
    }

    public void onBindViewHolder(YearViewHolder yearViewHolder, int i) {
        int i2;
        Typeface typeface;
        Intrinsics.checkParameterIsNotNull(yearViewHolder, "holder");
        int asYear = asYear(i);
        Integer num = this.selectedYear;
        boolean z = num != null && asYear == num.intValue();
        View view = yearViewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "holder.itemView.context");
        Resources resources = context.getResources();
        yearViewHolder.getTextView().setText(String.valueOf(asYear));
        yearViewHolder.getTextView().setSelected(z);
        TextView textView = yearViewHolder.getTextView();
        if (z) {
            i2 = R.dimen.year_month_list_text_size_selected;
        } else {
            i2 = R.dimen.year_month_list_text_size;
        }
        textView.setTextSize(0, resources.getDimension(i2));
        TextView textView2 = yearViewHolder.getTextView();
        if (z) {
            typeface = this.mediumFont;
        } else {
            typeface = this.normalFont;
        }
        textView2.setTypeface(typeface);
    }

    public final Integer getSelectedPosition() {
        Integer num = this.selectedYear;
        if (num != null) {
            return Integer.valueOf(asPosition(num.intValue()));
        }
        return null;
    }

    public final void onRowClicked$com_afollestad_date_picker(int i) {
        Integer valueOf = Integer.valueOf(asYear(i));
        this.onSelection.invoke(Integer.valueOf(valueOf.intValue()));
        setSelectedYear(valueOf);
    }

    private final int asPosition(int i) {
        return (i - this.yearRange.getFirst().intValue()) - 1;
    }

    private final int asYear(int i) {
        return i + 1 + this.yearRange.getFirst().intValue();
    }
}
