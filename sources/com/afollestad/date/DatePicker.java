package com.afollestad.date;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.afollestad.date.adapters.MonthAdapter;
import com.afollestad.date.adapters.MonthItemAdapter;
import com.afollestad.date.adapters.YearAdapter;
import com.afollestad.date.controllers.DatePickerController;
import com.afollestad.date.controllers.MinMaxController;
import com.afollestad.date.controllers.VibratorController;
import com.afollestad.date.data.DateFormatter;
import com.afollestad.date.data.MonthItem;
import com.afollestad.date.managers.DatePickerLayoutManager;
import com.afollestad.date.renderers.MonthItemRenderer;
import com.afollestad.date.util.AttrsKt;
import com.afollestad.date.view.DatePickerSavedState;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 H2\u00020\u0001:\u0001HB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006JB\u0010\u0019\u001a\u00020\u001a2:\u0010\u001b\u001a6\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001a0\u001cj\u0002`\"J\u0006\u0010#\u001a\u00020\u001aJ\n\u0010$\u001a\u0004\u0018\u00010\u001dH\u0007J\b\u0010%\u001a\u0004\u0018\u00010\u001dJ\b\u0010&\u001a\u0004\u0018\u00010\u001dJ\b\u0010'\u001a\u00020\u001aH\u0014J+\u0010(\u001a\u00020\u001a2!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001a0)H\u0007J\b\u0010*\u001a\u00020\u001aH\u0014J0\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020/H\u0014J\u0018\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020/H\u0014J\u0012\u00106\u001a\u00020\u001a2\b\u00107\u001a\u0004\u0018\u000108H\u0014J\n\u00109\u001a\u0004\u0018\u000108H\u0014J\u0016\u0010:\u001a\u00020\u001a2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020=0<H\u0002J\u0018\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001d2\b\b\u0002\u0010@\u001a\u00020-J7\u0010>\u001a\u00020\u001a2\n\b\u0003\u0010A\u001a\u0004\u0018\u00010/2\b\b\u0001\u0010B\u001a\u00020/2\n\b\u0003\u0010C\u001a\u0004\u0018\u00010/2\b\b\u0002\u0010@\u001a\u00020-¢\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001dJ$\u0010E\u001a\u00020\u001a2\b\b\u0001\u0010A\u001a\u00020/2\b\b\u0001\u0010B\u001a\u00020/2\b\b\u0001\u0010F\u001a\u00020/J\u000e\u0010G\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001dJ$\u0010G\u001a\u00020\u001a2\b\b\u0001\u0010A\u001a\u00020/2\b\b\u0001\u0010B\u001a\u00020/2\b\b\u0001\u0010F\u001a\u00020/R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/afollestad/date/DatePicker;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "controller", "Lcom/afollestad/date/controllers/DatePickerController;", "getController$com_afollestad_date_picker", "()Lcom/afollestad/date/controllers/DatePickerController;", "layoutManager", "Lcom/afollestad/date/managers/DatePickerLayoutManager;", "minMaxController", "Lcom/afollestad/date/controllers/MinMaxController;", "getMinMaxController$com_afollestad_date_picker", "()Lcom/afollestad/date/controllers/MinMaxController;", "monthAdapter", "Lcom/afollestad/date/adapters/MonthAdapter;", "monthItemAdapter", "Lcom/afollestad/date/adapters/MonthItemAdapter;", "monthItemRenderer", "Lcom/afollestad/date/renderers/MonthItemRenderer;", "yearAdapter", "Lcom/afollestad/date/adapters/YearAdapter;", "addOnDateChanged", "", "block", "Lkotlin/Function2;", "Ljava/util/Calendar;", "Lkotlin/ParameterName;", "name", "previous", "date", "Lcom/afollestad/date/OnDateChanged;", "clearOnDateChanged", "getDate", "getMaxDate", "getMinDate", "onAttachedToWindow", "onDateChanged", "Lkotlin/Function1;", "onFinishInflate", "onLayout", "changed", "", "left", "", "top", "right", "bottom", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onRestoreInstanceState", "state", "Landroid/os/Parcelable;", "onSaveInstanceState", "renderMonthItems", "days", "", "Lcom/afollestad/date/data/MonthItem;", "setDate", "calendar", "notifyListeners", "year", "month", "selectedDate", "(Ljava/lang/Integer;ILjava/lang/Integer;Z)V", "setMaxDate", "dayOfMonth", "setMinDate", "Companion", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DatePicker.kt */
public final class DatePicker extends ViewGroup {
    @Deprecated
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long MONTH_MAX = 11;
    public static final long MONTH_MIN = 0;
    private final DatePickerController controller;
    /* access modifiers changed from: private */
    public final DatePickerLayoutManager layoutManager;
    private final MinMaxController minMaxController = new MinMaxController();
    private final MonthAdapter monthAdapter;
    private final MonthItemAdapter monthItemAdapter;
    private final MonthItemRenderer monthItemRenderer;
    private final YearAdapter yearAdapter;

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DatePicker);
        try {
            DatePickerLayoutManager.Companion companion = DatePickerLayoutManager.Companion;
            Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "ta");
            this.layoutManager = companion.inflateInto(context, obtainStyledAttributes, this);
            this.controller = new DatePickerController(new VibratorController(context, obtainStyledAttributes), this.minMaxController, new Function2<Calendar, Calendar, Unit>(this.layoutManager) {
                public final String getName() {
                    return "setHeadersContent";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(DatePickerLayoutManager.class);
                }

                public final String getSignature() {
                    return "setHeadersContent(Ljava/util/Calendar;Ljava/util/Calendar;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Calendar) obj, (Calendar) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(Calendar calendar, Calendar calendar2) {
                    Intrinsics.checkParameterIsNotNull(calendar, "p1");
                    Intrinsics.checkParameterIsNotNull(calendar2, "p2");
                    ((DatePickerLayoutManager) this.receiver).setHeadersContent(calendar, calendar2);
                }
            }, new Function1<List<? extends MonthItem>, Unit>(this) {
                public final String getName() {
                    return "renderMonthItems";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(DatePicker.class);
                }

                public final String getSignature() {
                    return "renderMonthItems(Ljava/util/List;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((List<? extends MonthItem>) (List) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<? extends MonthItem> list) {
                    Intrinsics.checkParameterIsNotNull(list, "p1");
                    ((DatePicker) this.receiver).renderMonthItems(list);
                }
            }, new Function1<Boolean, Unit>(this.layoutManager) {
                public final String getName() {
                    return "showOrHideGoPrevious";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(DatePickerLayoutManager.class);
                }

                public final String getSignature() {
                    return "showOrHideGoPrevious(Z)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    ((DatePickerLayoutManager) this.receiver).showOrHideGoPrevious(z);
                }
            }, new Function1<Boolean, Unit>(this.layoutManager) {
                public final String getName() {
                    return "showOrHideGoNext";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(DatePickerLayoutManager.class);
                }

                public final String getSignature() {
                    return "showOrHideGoNext(Z)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    ((DatePickerLayoutManager) this.receiver).showOrHideGoNext(z);
                }
            }, new Function0<Unit>(this) {
                final /* synthetic */ DatePicker this$0;

                {
                    this.this$0 = r1;
                }

                public final void invoke() {
                    this.this$0.layoutManager.setMode(DatePickerLayoutManager.Mode.CALENDAR);
                }
            }, (Function0) null, 128, (DefaultConstructorMarker) null);
            Typeface font = AttrsKt.font(obtainStyledAttributes, context, R.styleable.DatePicker_date_picker_medium_font, AnonymousClass6.INSTANCE);
            Typeface font2 = AttrsKt.font(obtainStyledAttributes, context, R.styleable.DatePicker_date_picker_normal_font, AnonymousClass7.INSTANCE);
            this.monthItemRenderer = new MonthItemRenderer(context, obtainStyledAttributes, font2, this.minMaxController);
            obtainStyledAttributes.recycle();
            this.monthItemAdapter = new MonthItemAdapter(this.monthItemRenderer, new Function1<MonthItem.DayOfMonth, Unit>(this) {
                final /* synthetic */ DatePicker this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((MonthItem.DayOfMonth) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(MonthItem.DayOfMonth dayOfMonth) {
                    Intrinsics.checkParameterIsNotNull(dayOfMonth, "it");
                    this.this$0.getController$com_afollestad_date_picker().setDayOfMonth(dayOfMonth.getDate());
                }
            });
            this.yearAdapter = new YearAdapter(font2, font, this.layoutManager.getSelectionColor(), new Function1<Integer, Unit>(this) {
                final /* synthetic */ DatePicker this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.getController$com_afollestad_date_picker().setYear(i);
                }
            });
            MonthAdapter monthAdapter2 = new MonthAdapter(this.layoutManager.getSelectionColor(), font2, font, new DateFormatter(), new Function1<Integer, Unit>(this) {
                final /* synthetic */ DatePicker this$0;

                {
                    this.this$0 = r1;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    this.this$0.getController$com_afollestad_date_picker().setMonth(i);
                }
            });
            this.monthAdapter = monthAdapter2;
            this.layoutManager.setAdapters(this.monthItemAdapter, this.yearAdapter, monthAdapter2);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final DatePickerController getController$com_afollestad_date_picker() {
        return this.controller;
    }

    public final MinMaxController getMinMaxController$com_afollestad_date_picker() {
        return this.minMaxController;
    }

    public static /* synthetic */ void setDate$default(DatePicker datePicker, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        datePicker.setDate(calendar, z);
    }

    public final void setDate(Calendar calendar, boolean z) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        this.controller.setFullDate(calendar, z);
    }

    public static /* synthetic */ void setDate$default(DatePicker datePicker, Integer num, int i, Integer num2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = null;
        }
        if ((i2 & 4) != 0) {
            num2 = null;
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        datePicker.setDate(num, i, num2, z);
    }

    public final void setDate(Integer num, int i, Integer num2, boolean z) {
        this.controller.setFullDate(num, i, num2, z);
    }

    public final Calendar getDate() {
        return this.controller.getFullDate();
    }

    public final Calendar getMinDate() {
        return this.minMaxController.getMinDate();
    }

    public final void setMinDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        this.minMaxController.setMinDate(calendar);
    }

    public final void setMinDate(int i, int i2, int i3) {
        this.minMaxController.setMinDate(i, i2, i3);
    }

    public final Calendar getMaxDate() {
        return this.minMaxController.getMaxDate();
    }

    public final void setMaxDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        this.minMaxController.setMaxDate(calendar);
    }

    public final void setMaxDate(int i, int i2, int i3) {
        this.minMaxController.setMaxDate(i, i2, i3);
    }

    @Deprecated(message = "Use addOnDateChanged instead.", replaceWith = @ReplaceWith(expression = "addOnDateChanged(block)", imports = {}))
    public final void onDateChanged(Function1<? super Calendar, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "block");
        this.controller.addDateChangedListener(new DatePicker$onDateChanged$1(function1));
    }

    public final void addOnDateChanged(Function2<? super Calendar, ? super Calendar, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "block");
        this.controller.addDateChangedListener(function2);
    }

    public final void clearOnDateChanged() {
        this.controller.clearDateChangedListeners();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controller.maybeInit();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new DatePickerSavedState(getDate(), super.onSaveInstanceState());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof DatePickerSavedState) {
            DatePickerSavedState datePickerSavedState = (DatePickerSavedState) parcelable;
            super.onRestoreInstanceState(datePickerSavedState.getSuperState());
            Calendar selectedDate = datePickerSavedState.getSelectedDate();
            if (selectedDate != null) {
                this.controller.setFullDate(selectedDate, false);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.layoutManager.onNavigate(new DatePicker$onFinishInflate$1(this.controller), new DatePicker$onFinishInflate$2(this.controller));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        DatePickerLayoutManager.Size onMeasure = this.layoutManager.onMeasure(i, i2);
        setMeasuredDimension(onMeasure.component1(), onMeasure.component2());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.layoutManager.onLayout(i, i2, i3);
    }

    /* access modifiers changed from: private */
    public final void renderMonthItems(List<? extends MonthItem> list) {
        for (Object next : list) {
            if (((MonthItem) next) instanceof MonthItem.DayOfMonth) {
                if (next != null) {
                    MonthItem.DayOfMonth dayOfMonth = (MonthItem.DayOfMonth) next;
                    this.yearAdapter.setSelectedYear(Integer.valueOf(dayOfMonth.getMonth().getYear()));
                    Integer selectedPosition = this.yearAdapter.getSelectedPosition();
                    if (selectedPosition != null) {
                        this.layoutManager.scrollToYearPosition(selectedPosition.intValue());
                    }
                    this.monthAdapter.setSelectedMonth(Integer.valueOf(dayOfMonth.getMonth().getMonth()));
                    Integer selectedMonth = this.monthAdapter.getSelectedMonth();
                    if (selectedMonth != null) {
                        this.layoutManager.scrollToMonthPosition(selectedMonth.intValue());
                    }
                    this.monthItemAdapter.setItems(list);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.afollestad.date.data.MonthItem.DayOfMonth");
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/afollestad/date/DatePicker$Companion;", "", "()V", "MONTH_MAX", "", "MONTH_MIN", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: DatePicker.kt */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
