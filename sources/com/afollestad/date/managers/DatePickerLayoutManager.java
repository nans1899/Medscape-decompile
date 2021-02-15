package com.afollestad.date.managers;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.date.R;
import com.afollestad.date.adapters.MonthAdapter;
import com.afollestad.date.adapters.MonthItemAdapter;
import com.afollestad.date.adapters.YearAdapter;
import com.afollestad.date.controllers.VibratorController;
import com.afollestad.date.data.DateFormatter;
import com.afollestad.date.util.AttrsKt;
import com.afollestad.date.util.DebouncerKt;
import com.afollestad.date.util.RecyclerViewsKt;
import com.afollestad.date.util.Util;
import com.afollestad.date.util.ViewsKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 P2\u00020\u0001:\u0004PQRSB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fJ\u0018\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\fH\u0007J\"\u00104\u001a\u00020-2\f\u00105\u001a\b\u0012\u0004\u0012\u00020-062\f\u00107\u001a\b\u0012\u0004\u0012\u00020-06J\u000e\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020\fJ\u000e\u0010:\u001a\u00020-2\u0006\u00109\u001a\u00020\fJ\u001e\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AJ\u0016\u0010B\u001a\u00020-2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DJ\u000e\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020HJ\b\u0010I\u001a\u00020-H\u0002J\b\u0010J\u001a\u00020-H\u0002J\b\u0010K\u001a\u00020-H\u0002J\u000e\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u00020-2\u0006\u0010M\u001a\u00020NR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager;", "", "context", "Landroid/content/Context;", "typedArray", "Landroid/content/res/TypedArray;", "root", "Landroid/view/ViewGroup;", "vibrator", "Lcom/afollestad/date/controllers/VibratorController;", "(Landroid/content/Context;Landroid/content/res/TypedArray;Landroid/view/ViewGroup;Lcom/afollestad/date/controllers/VibratorController;)V", "calendarHorizontalPadding", "", "chevronsTopMargin", "currentMonthHeight", "currentMonthTopMargin", "dateFormatter", "Lcom/afollestad/date/data/DateFormatter;", "daysRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dividerHeight", "goNextMonthView", "Landroid/widget/ImageView;", "goPreviousMonthView", "headerBackgroundColor", "headersWithFactor", "listsDividerView", "Landroid/view/View;", "mediumFont", "Landroid/graphics/Typeface;", "monthRecyclerView", "normalFont", "orientation", "Lcom/afollestad/date/managers/DatePickerLayoutManager$Orientation;", "selectedDateView", "Landroid/widget/TextView;", "selectedYearView", "selectionColor", "getSelectionColor", "()I", "size", "Lcom/afollestad/date/managers/DatePickerLayoutManager$Size;", "visibleMonthView", "yearsRecyclerView", "onLayout", "", "left", "top", "right", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onNavigate", "onGoToPrevious", "Lkotlin/Function0;", "onGoToNext", "scrollToMonthPosition", "pos", "scrollToYearPosition", "setAdapters", "monthItemAdapter", "Lcom/afollestad/date/adapters/MonthItemAdapter;", "yearAdapter", "Lcom/afollestad/date/adapters/YearAdapter;", "monthAdapter", "Lcom/afollestad/date/adapters/MonthAdapter;", "setHeadersContent", "currentMonth", "Ljava/util/Calendar;", "selectedDate", "setMode", "mode", "Lcom/afollestad/date/managers/DatePickerLayoutManager$Mode;", "setupHeaderViews", "setupListViews", "setupNavigationViews", "showOrHideGoNext", "show", "", "showOrHideGoPrevious", "Companion", "Mode", "Orientation", "Size", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DatePickerLayoutManager.kt */
public final class DatePickerLayoutManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DAYS_IN_WEEK = 7;
    private final int calendarHorizontalPadding;
    private final int chevronsTopMargin;
    private final int currentMonthHeight;
    private final int currentMonthTopMargin;
    private final DateFormatter dateFormatter = new DateFormatter();
    private RecyclerView daysRecyclerView;
    private final int dividerHeight;
    private ImageView goNextMonthView;
    private ImageView goPreviousMonthView;
    private final int headerBackgroundColor;
    private final int headersWithFactor;
    private View listsDividerView;
    private final Typeface mediumFont;
    private RecyclerView monthRecyclerView;
    private final Typeface normalFont;
    private final Orientation orientation;
    private TextView selectedDateView;
    private TextView selectedYearView;
    private final int selectionColor;
    private final Size size = new Size(0, 0);
    private final VibratorController vibrator;
    private TextView visibleMonthView;
    private RecyclerView yearsRecyclerView;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager$Mode;", "", "(Ljava/lang/String;I)V", "CALENDAR", "MONTH_LIST", "YEAR_LIST", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePickerLayoutManager.kt */
    public enum Mode {
        CALENDAR,
        MONTH_LIST,
        YEAR_LIST
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Mode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Mode.CALENDAR.ordinal()] = 1;
            $EnumSwitchMapping$0[Mode.MONTH_LIST.ordinal()] = 2;
            $EnumSwitchMapping$0[Mode.YEAR_LIST.ordinal()] = 3;
        }
    }

    public DatePickerLayoutManager(Context context, TypedArray typedArray, ViewGroup viewGroup, VibratorController vibratorController) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(typedArray, "typedArray");
        Intrinsics.checkParameterIsNotNull(viewGroup, "root");
        Intrinsics.checkParameterIsNotNull(vibratorController, "vibrator");
        this.vibrator = vibratorController;
        this.selectionColor = AttrsKt.color(typedArray, R.styleable.DatePicker_date_picker_selection_color, new DatePickerLayoutManager$selectionColor$1(context));
        this.headerBackgroundColor = AttrsKt.color(typedArray, R.styleable.DatePicker_date_picker_header_background_color, new DatePickerLayoutManager$headerBackgroundColor$1(context));
        this.normalFont = AttrsKt.font(typedArray, context, R.styleable.DatePicker_date_picker_normal_font, DatePickerLayoutManager$normalFont$1.INSTANCE);
        this.mediumFont = AttrsKt.font(typedArray, context, R.styleable.DatePicker_date_picker_medium_font, DatePickerLayoutManager$mediumFont$1.INSTANCE);
        this.calendarHorizontalPadding = typedArray.getDimensionPixelSize(R.styleable.DatePicker_date_picker_calendar_horizontal_padding, 0);
        View findViewById = viewGroup.findViewById(R.id.current_year);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "root.findViewById(R.id.current_year)");
        this.selectedYearView = (TextView) findViewById;
        View findViewById2 = viewGroup.findViewById(R.id.current_date);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "root.findViewById(R.id.current_date)");
        this.selectedDateView = (TextView) findViewById2;
        View findViewById3 = viewGroup.findViewById(R.id.left_chevron);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "root.findViewById(R.id.left_chevron)");
        this.goPreviousMonthView = (ImageView) findViewById3;
        View findViewById4 = viewGroup.findViewById(R.id.current_month);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "root.findViewById(R.id.current_month)");
        this.visibleMonthView = (TextView) findViewById4;
        View findViewById5 = viewGroup.findViewById(R.id.right_chevron);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "root.findViewById(R.id.right_chevron)");
        this.goNextMonthView = (ImageView) findViewById5;
        View findViewById6 = viewGroup.findViewById(R.id.year_month_list_divider);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "root.findViewById(R.id.year_month_list_divider)");
        this.listsDividerView = findViewById6;
        View findViewById7 = viewGroup.findViewById(R.id.day_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById7, "root.findViewById(R.id.day_list)");
        this.daysRecyclerView = (RecyclerView) findViewById7;
        View findViewById8 = viewGroup.findViewById(R.id.year_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById8, "root.findViewById(R.id.year_list)");
        this.yearsRecyclerView = (RecyclerView) findViewById8;
        View findViewById9 = viewGroup.findViewById(R.id.month_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById9, "root.findViewById(R.id.month_list)");
        this.monthRecyclerView = (RecyclerView) findViewById9;
        this.currentMonthTopMargin = context.getResources().getDimensionPixelSize(R.dimen.current_month_top_margin);
        this.chevronsTopMargin = context.getResources().getDimensionPixelSize(R.dimen.chevrons_top_margin);
        this.currentMonthHeight = context.getResources().getDimensionPixelSize(R.dimen.current_month_header_height);
        this.dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        this.headersWithFactor = context.getResources().getInteger(R.integer.headers_width_factor);
        this.orientation = Orientation.Companion.get(context);
        setupHeaderViews();
        setupNavigationViews();
        setupListViews();
    }

    public final int getSelectionColor() {
        return this.selectionColor;
    }

    public final Size onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int size2 = View.MeasureSpec.getSize(i);
        int size3 = View.MeasureSpec.getSize(i2);
        int i7 = size2 / this.headersWithFactor;
        this.selectedYearView.measure(View.MeasureSpec.makeMeasureSpec(i7, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        TextView textView = this.selectedDateView;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        if (size3 <= 0 || this.orientation == Orientation.PORTRAIT) {
            i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            i3 = View.MeasureSpec.makeMeasureSpec(size3 - this.selectedYearView.getMeasuredHeight(), 1073741824);
        }
        textView.measure(makeMeasureSpec, i3);
        int i8 = this.orientation == Orientation.PORTRAIT ? size2 : size2 - i7;
        this.visibleMonthView.measure(View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.currentMonthHeight, 1073741824));
        this.listsDividerView.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(this.dividerHeight, 1073741824));
        if (this.orientation == Orientation.PORTRAIT) {
            i5 = this.selectedYearView.getMeasuredHeight() + this.selectedDateView.getMeasuredHeight() + this.visibleMonthView.getMeasuredHeight();
            i4 = this.listsDividerView.getMeasuredHeight();
        } else {
            i5 = this.visibleMonthView.getMeasuredHeight();
            i4 = this.listsDividerView.getMeasuredHeight();
        }
        int i9 = i5 + i4;
        int i10 = i8 - (this.calendarHorizontalPadding * 2);
        RecyclerView recyclerView = this.daysRecyclerView;
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i10, 1073741824);
        if (size3 > 0) {
            i6 = View.MeasureSpec.makeMeasureSpec(size3 - i9, Integer.MIN_VALUE);
        } else {
            i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        recyclerView.measure(makeMeasureSpec2, i6);
        int i11 = i10 / 7;
        this.goPreviousMonthView.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
        this.goNextMonthView.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), View.MeasureSpec.makeMeasureSpec(i11, 1073741824));
        this.yearsRecyclerView.measure(View.MeasureSpec.makeMeasureSpec(this.daysRecyclerView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.daysRecyclerView.getMeasuredHeight(), 1073741824));
        this.monthRecyclerView.measure(View.MeasureSpec.makeMeasureSpec(this.daysRecyclerView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.daysRecyclerView.getMeasuredHeight(), 1073741824));
        Size size4 = this.size;
        size4.setWidth(size2);
        size4.setHeight(i9 + this.daysRecyclerView.getMeasuredHeight() + this.chevronsTopMargin + this.currentMonthTopMargin);
        return size4;
    }

    public final void onLayout(int i, int i2, int i3) {
        int i4;
        ViewsKt.placeAt$default(this.selectedYearView, i2, 0, 0, 0, 14, (Object) null);
        ViewsKt.placeAt$default(this.selectedDateView, this.selectedYearView.getBottom(), 0, 0, 0, 14, (Object) null);
        if (this.orientation != Orientation.PORTRAIT) {
            i = this.selectedDateView.getRight();
        }
        TextView textView = this.visibleMonthView;
        View view = textView;
        int measuredWidth = (i3 - ((i3 - i) / 2)) - (textView.getMeasuredWidth() / 2);
        if (this.orientation == Orientation.PORTRAIT) {
            i4 = this.selectedDateView.getBottom() + this.currentMonthTopMargin;
        } else {
            i4 = this.currentMonthTopMargin;
        }
        ViewsKt.placeAt$default(view, i4, measuredWidth, 0, 0, 12, (Object) null);
        ViewsKt.placeAt$default(this.listsDividerView, this.visibleMonthView.getBottom(), i, 0, 0, 12, (Object) null);
        ViewsKt.placeAt$default(this.daysRecyclerView, this.listsDividerView.getBottom(), i + this.calendarHorizontalPadding, 0, 0, 12, (Object) null);
        int bottom = ((this.visibleMonthView.getBottom() - (this.visibleMonthView.getMeasuredHeight() / 2)) - (this.goPreviousMonthView.getMeasuredHeight() / 2)) + this.chevronsTopMargin;
        ViewsKt.placeAt$default(this.goPreviousMonthView, bottom, this.daysRecyclerView.getLeft() + this.calendarHorizontalPadding, 0, 0, 12, (Object) null);
        ViewsKt.placeAt$default(this.goNextMonthView, bottom, (this.daysRecyclerView.getRight() - this.goNextMonthView.getMeasuredWidth()) - this.calendarHorizontalPadding, 0, 0, 12, (Object) null);
        this.yearsRecyclerView.layout(this.daysRecyclerView.getLeft(), this.daysRecyclerView.getTop(), this.daysRecyclerView.getRight(), this.daysRecyclerView.getBottom());
        this.monthRecyclerView.layout(this.daysRecyclerView.getLeft(), this.daysRecyclerView.getTop(), this.daysRecyclerView.getRight(), this.daysRecyclerView.getBottom());
    }

    public final void setAdapters(MonthItemAdapter monthItemAdapter, YearAdapter yearAdapter, MonthAdapter monthAdapter) {
        Intrinsics.checkParameterIsNotNull(monthItemAdapter, "monthItemAdapter");
        Intrinsics.checkParameterIsNotNull(yearAdapter, "yearAdapter");
        Intrinsics.checkParameterIsNotNull(monthAdapter, "monthAdapter");
        this.daysRecyclerView.setAdapter(monthItemAdapter);
        this.yearsRecyclerView.setAdapter(yearAdapter);
        this.monthRecyclerView.setAdapter(monthAdapter);
    }

    public final void showOrHideGoPrevious(boolean z) {
        ViewsKt.showOrConceal(this.goPreviousMonthView, z);
    }

    public final void showOrHideGoNext(boolean z) {
        ViewsKt.showOrConceal(this.goNextMonthView, z);
    }

    public final void setHeadersContent(Calendar calendar, Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar, "currentMonth");
        Intrinsics.checkParameterIsNotNull(calendar2, "selectedDate");
        this.visibleMonthView.setText(this.dateFormatter.monthAndYear(calendar));
        this.selectedYearView.setText(this.dateFormatter.year(calendar2));
        this.selectedDateView.setText(this.dateFormatter.date(calendar2));
    }

    public final void scrollToYearPosition(int i) {
        this.yearsRecyclerView.scrollToPosition(i - 2);
    }

    public final void scrollToMonthPosition(int i) {
        this.monthRecyclerView.scrollToPosition(i - 2);
    }

    public final void onNavigate(Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkParameterIsNotNull(function0, "onGoToPrevious");
        Intrinsics.checkParameterIsNotNull(function02, "onGoToNext");
        DebouncerKt.onClickDebounced(this.goPreviousMonthView, new DatePickerLayoutManager$onNavigate$1(function0));
        DebouncerKt.onClickDebounced(this.goNextMonthView, new DatePickerLayoutManager$onNavigate$2(function02));
    }

    private final void setupHeaderViews() {
        TextView textView = this.selectedYearView;
        textView.setBackground(new ColorDrawable(this.headerBackgroundColor));
        textView.setTypeface(this.normalFont);
        DebouncerKt.onClickDebounced(textView, new DatePickerLayoutManager$setupHeaderViews$$inlined$apply$lambda$1(this));
        TextView textView2 = this.selectedDateView;
        textView2.setSelected(true);
        textView2.setBackground(new ColorDrawable(this.headerBackgroundColor));
        textView2.setTypeface(this.mediumFont);
        DebouncerKt.onClickDebounced(textView2, new DatePickerLayoutManager$setupHeaderViews$$inlined$apply$lambda$2(this));
    }

    private final void setupNavigationViews() {
        this.goPreviousMonthView.setBackground(Util.INSTANCE.createCircularSelector(this.selectionColor));
        TextView textView = this.visibleMonthView;
        textView.setTypeface(this.mediumFont);
        DebouncerKt.onClickDebounced(textView, new DatePickerLayoutManager$setupNavigationViews$$inlined$apply$lambda$1(this));
        this.goNextMonthView.setBackground(Util.INSTANCE.createCircularSelector(this.selectionColor));
    }

    private final void setupListViews() {
        RecyclerView recyclerView = this.daysRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), recyclerView.getResources().getInteger(R.integer.day_grid_span)));
        RecyclerViewsKt.attachTopDivider(recyclerView, this.listsDividerView);
        int i = this.calendarHorizontalPadding;
        ViewsKt.updatePadding$default(recyclerView, i, 0, i, 0, 10, (Object) null);
        RecyclerView recyclerView2 = this.yearsRecyclerView;
        recyclerView2.setLayoutManager(new LinearLayoutManager(recyclerView2.getContext()));
        recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView2.getContext(), 1));
        RecyclerViewsKt.attachTopDivider(recyclerView2, this.listsDividerView);
        RecyclerView recyclerView3 = this.monthRecyclerView;
        recyclerView3.setLayoutManager(new LinearLayoutManager(recyclerView3.getContext()));
        recyclerView3.addItemDecoration(new DividerItemDecoration(recyclerView3.getContext(), 1));
        RecyclerViewsKt.attachTopDivider(recyclerView3, this.listsDividerView);
    }

    public final void setMode(Mode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        boolean z = false;
        ViewsKt.showOrConceal(this.daysRecyclerView, mode == Mode.CALENDAR);
        ViewsKt.showOrConceal(this.yearsRecyclerView, mode == Mode.YEAR_LIST);
        ViewsKt.showOrConceal(this.monthRecyclerView, mode == Mode.MONTH_LIST);
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            RecyclerViewsKt.invalidateTopDividerNow(this.daysRecyclerView, this.listsDividerView);
        } else if (i == 2) {
            RecyclerViewsKt.invalidateTopDividerNow(this.monthRecyclerView, this.listsDividerView);
        } else if (i == 3) {
            RecyclerViewsKt.invalidateTopDividerNow(this.yearsRecyclerView, this.listsDividerView);
        }
        TextView textView = this.selectedYearView;
        textView.setSelected(mode == Mode.YEAR_LIST);
        textView.setTypeface(mode == Mode.YEAR_LIST ? this.mediumFont : this.normalFont);
        TextView textView2 = this.selectedDateView;
        if (mode == Mode.CALENDAR) {
            z = true;
        }
        textView2.setSelected(z);
        textView2.setTypeface(mode == Mode.CALENDAR ? this.mediumFont : this.normalFont);
        this.vibrator.vibrateForSelection();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0006"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager$Orientation;", "", "(Ljava/lang/String;I)V", "PORTRAIT", "LANDSCAPE", "Companion", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePickerLayoutManager.kt */
    public enum Orientation {
        PORTRAIT,
        LANDSCAPE;
        
        public static final Companion Companion = null;

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager$Orientation$Companion;", "", "()V", "get", "Lcom/afollestad/date/managers/DatePickerLayoutManager$Orientation;", "context", "Landroid/content/Context;", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
        /* compiled from: DatePickerLayoutManager.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Orientation get(Context context) {
                Intrinsics.checkParameterIsNotNull(context, "context");
                Resources resources = context.getResources();
                Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
                if (resources.getConfiguration().orientation == 1) {
                    return Orientation.PORTRAIT;
                }
                return Orientation.LANDSCAPE;
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager$Size;", "", "width", "", "height", "(II)V", "getHeight", "()I", "setHeight", "(I)V", "getWidth", "setWidth", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePickerLayoutManager.kt */
    public static final class Size {
        private int height;
        private int width;

        public static /* synthetic */ Size copy$default(Size size, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = size.width;
            }
            if ((i3 & 2) != 0) {
                i2 = size.height;
            }
            return size.copy(i, i2);
        }

        public final int component1() {
            return this.width;
        }

        public final int component2() {
            return this.height;
        }

        public final Size copy(int i, int i2) {
            return new Size(i, i2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Size) {
                    Size size = (Size) obj;
                    if (this.width == size.width) {
                        if (this.height == size.height) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.width * 31) + this.height;
        }

        public String toString() {
            return "Size(width=" + this.width + ", height=" + this.height + ")";
        }

        public Size(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        public final int getHeight() {
            return this.height;
        }

        public final void setHeight(int i) {
            this.height = i;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/afollestad/date/managers/DatePickerLayoutManager$Companion;", "", "()V", "DAYS_IN_WEEK", "", "inflateInto", "Lcom/afollestad/date/managers/DatePickerLayoutManager;", "context", "Landroid/content/Context;", "typedArray", "Landroid/content/res/TypedArray;", "container", "Landroid/view/ViewGroup;", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePickerLayoutManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DatePickerLayoutManager inflateInto(Context context, TypedArray typedArray, ViewGroup viewGroup) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(typedArray, "typedArray");
            Intrinsics.checkParameterIsNotNull(viewGroup, "container");
            View.inflate(context, R.layout.date_picker, viewGroup);
            return new DatePickerLayoutManager(context, typedArray, viewGroup, new VibratorController(context, typedArray));
        }
    }
}
