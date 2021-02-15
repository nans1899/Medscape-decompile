package com.afollestad.date.renderers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.afollestad.date.R;
import com.afollestad.date.controllers.MinMaxController;
import com.afollestad.date.data.DayOfWeek;
import com.afollestad.date.data.MonthItem;
import com.afollestad.date.data.snapshot.DateSnapshot;
import com.afollestad.date.util.AttrsKt;
import com.afollestad.date.util.ContextsKt;
import com.afollestad.date.util.DebouncerKt;
import com.afollestad.date.util.Util;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ2\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000f0\u0017J4\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000f0\u0017H\u0002J\u0018\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\f\u0010\u001e\u001a\u00020\u001f*\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/afollestad/date/renderers/MonthItemRenderer;", "", "context", "Landroid/content/Context;", "typedArray", "Landroid/content/res/TypedArray;", "normalFont", "Landroid/graphics/Typeface;", "minMaxController", "Lcom/afollestad/date/controllers/MinMaxController;", "(Landroid/content/Context;Landroid/content/res/TypedArray;Landroid/graphics/Typeface;Lcom/afollestad/date/controllers/MinMaxController;)V", "disabledBackgroundColor", "", "selectionColor", "render", "", "item", "Lcom/afollestad/date/data/MonthItem;", "rootView", "Landroid/view/View;", "textView", "Landroid/widget/TextView;", "onSelection", "Lkotlin/Function1;", "Lcom/afollestad/date/data/MonthItem$DayOfMonth;", "renderDayOfMonth", "dayOfMonth", "renderWeekHeader", "dayOfWeek", "Lcom/afollestad/date/data/DayOfWeek;", "positiveOrEmptyAsString", "", "Companion", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthItemRenderer.kt */
public final class MonthItemRenderer {
    @Deprecated
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final float DEFAULT_DISABLED_BACKGROUND_OPACITY = 0.3f;
    /* access modifiers changed from: private */
    public final Context context;
    private final int disabledBackgroundColor;
    private final MinMaxController minMaxController;
    private final Typeface normalFont;
    private final int selectionColor;

    public MonthItemRenderer(Context context2, TypedArray typedArray, Typeface typeface, MinMaxController minMaxController2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(typedArray, "typedArray");
        Intrinsics.checkParameterIsNotNull(typeface, "normalFont");
        Intrinsics.checkParameterIsNotNull(minMaxController2, "minMaxController");
        this.context = context2;
        this.normalFont = typeface;
        this.minMaxController = minMaxController2;
        this.selectionColor = AttrsKt.color(typedArray, R.styleable.DatePicker_date_picker_selection_color, new MonthItemRenderer$selectionColor$1(this));
        this.disabledBackgroundColor = AttrsKt.color(typedArray, R.styleable.DatePicker_date_picker_disabled_background_color, new MonthItemRenderer$disabledBackgroundColor$1(this));
    }

    public final void render(MonthItem monthItem, View view, TextView textView, Function1<? super MonthItem.DayOfMonth, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(monthItem, ContentParser.ITEM);
        Intrinsics.checkParameterIsNotNull(view, "rootView");
        Intrinsics.checkParameterIsNotNull(textView, "textView");
        Intrinsics.checkParameterIsNotNull(function1, "onSelection");
        if (monthItem instanceof MonthItem.WeekHeader) {
            renderWeekHeader(((MonthItem.WeekHeader) monthItem).getDayOfWeek(), textView);
        } else if (monthItem instanceof MonthItem.DayOfMonth) {
            renderDayOfMonth((MonthItem.DayOfMonth) monthItem, view, textView, function1);
        }
    }

    private final void renderWeekHeader(DayOfWeek dayOfWeek, TextView textView) {
        Context context2 = textView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        textView.setTextColor(ContextsKt.resolveColor$default(context2, 16842808, (Function0) null, 2, (Object) null));
        textView.setText(String.valueOf(StringsKt.first(dayOfWeek.name())));
        textView.setTypeface(this.normalFont);
    }

    private final void renderDayOfMonth(MonthItem.DayOfMonth dayOfMonth, View view, TextView textView, Function1<? super MonthItem.DayOfMonth, Unit> function1) {
        Drawable drawable = null;
        view.setBackground(drawable);
        Util util = Util.INSTANCE;
        Context context2 = textView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        textView.setTextColor(Util.createTextSelector$default(util, context2, this.selectionColor, false, 4, (Object) null));
        textView.setText(positiveOrEmptyAsString(dayOfMonth.getDate()));
        textView.setTypeface(this.normalFont);
        textView.setGravity(17);
        textView.setBackground(drawable);
        textView.setOnClickListener((View.OnClickListener) null);
        boolean z = false;
        if (dayOfMonth.getDate() == -1) {
            view.setEnabled(false);
            textView.setSelected(false);
            return;
        }
        DateSnapshot dateSnapshot = new DateSnapshot(dayOfMonth.getMonth().getMonth(), dayOfMonth.getDate(), dayOfMonth.getMonth().getYear());
        textView.setSelected(dayOfMonth.isSelected());
        if (this.minMaxController.isOutOfMinRange(dateSnapshot)) {
            int outOfMinRangeBackgroundRes = this.minMaxController.getOutOfMinRangeBackgroundRes(dateSnapshot);
            Util util2 = Util.INSTANCE;
            Context context3 = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            view.setBackground(util2.coloredDrawable(context3, outOfMinRangeBackgroundRes, this.disabledBackgroundColor));
            view.setEnabled(false);
        } else if (this.minMaxController.isOutOfMaxRange(dateSnapshot)) {
            int outOfMaxRangeBackgroundRes = this.minMaxController.getOutOfMaxRangeBackgroundRes(dateSnapshot);
            Util util3 = Util.INSTANCE;
            Context context4 = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            view.setBackground(util3.coloredDrawable(context4, outOfMaxRangeBackgroundRes, this.disabledBackgroundColor));
            view.setEnabled(false);
        } else {
            if (textView.getText().toString().length() > 0) {
                z = true;
            }
            view.setEnabled(z);
            textView.setBackground(Util.INSTANCE.createCircularSelector(this.selectionColor));
            DebouncerKt.onClickDebounced(textView, new MonthItemRenderer$renderDayOfMonth$$inlined$apply$lambda$1(this, function1, dayOfMonth));
        }
    }

    private final String positiveOrEmptyAsString(int i) {
        return i < 1 ? "" : String.valueOf(i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/afollestad/date/renderers/MonthItemRenderer$Companion;", "", "()V", "DEFAULT_DISABLED_BACKGROUND_OPACITY", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: MonthItemRenderer.kt */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
