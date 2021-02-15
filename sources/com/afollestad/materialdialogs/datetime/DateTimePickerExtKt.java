package com.afollestad.materialdialogs.datetime;

import android.view.View;
import android.widget.TimePicker;
import androidx.viewpager.widget.ViewPager;
import com.afollestad.date.CalendarsKt;
import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.callbacks.DialogCallbackExtKt;
import com.afollestad.materialdialogs.customview.DialogCustomViewExtKt;
import com.afollestad.materialdialogs.datetime.internal.DateTimePickerAdapter;
import com.afollestad.materialdialogs.datetime.internal.TimeChangeListener;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import com.afollestad.viewpagerdots.DotsIndicator;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0001\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\u00012>\b\u0002\u0010\f\u001a8\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rj\u0002`\u0013\u001a\f\u0010\u0014\u001a\u00020\u0003*\u00020\u0006H\u0007*n\u0010\u0015\"4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\r24\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\r¨\u0006\u0011"}, d2 = {"didDateChange", "", "from", "Ljava/util/Calendar;", "to", "dateTimePicker", "Lcom/afollestad/materialdialogs/MaterialDialog;", "minDateTime", "currentDateTime", "requireFutureDateTime", "show24HoursView", "autoFlipToTime", "dateTimeCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "dialog", "datetime", "", "Lcom/afollestad/materialdialogs/datetime/DateTimeCallback;", "selectedDateTime", "DateTimeCallback"}, k = 2, mv = {1, 1, 16})
/* compiled from: DateTimePickerExt.kt */
public final class DateTimePickerExtKt {
    public static /* synthetic */ MaterialDialog dateTimePicker$default(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, boolean z, boolean z2, boolean z3, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            calendar = null;
        }
        if ((i & 2) != 0) {
            calendar2 = null;
        }
        Calendar calendar3 = calendar2;
        boolean z4 = false;
        boolean z5 = (i & 4) != 0 ? false : z;
        if ((i & 8) == 0) {
            z4 = z2;
        }
        boolean z6 = (i & 16) != 0 ? true : z3;
        if ((i & 32) != 0) {
            function2 = null;
        }
        return dateTimePicker(materialDialog, calendar, calendar3, z5, z4, z6, function2);
    }

    public static final MaterialDialog dateTimePicker(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, boolean z, boolean z2, boolean z3, Function2<? super MaterialDialog, ? super Calendar, Unit> function2) {
        MaterialDialog materialDialog2 = materialDialog;
        Calendar calendar3 = calendar;
        Calendar calendar4 = calendar2;
        boolean z4 = z;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$dateTimePicker");
        DialogCustomViewExtKt.customView$default(materialDialog, Integer.valueOf(R.layout.md_datetime_picker_pager), (View) null, false, true, false, MDUtil.INSTANCE.isLandscape(materialDialog.getWindowContext()), 22, (Object) null);
        ViewPager pager = ViewExtKt.getPager(materialDialog);
        pager.setAdapter(new DateTimePickerAdapter());
        DotsIndicator pageIndicator = ViewExtKt.getPageIndicator(materialDialog);
        if (pageIndicator != null) {
            pageIndicator.attachViewPager(pager);
            pageIndicator.setDotTint(MDUtil.resolveColor$default(MDUtil.INSTANCE, materialDialog.getWindowContext(), (Integer) null, 16842806, (Function0) null, 10, (Object) null));
        }
        DatePicker datePicker = ViewExtKt.getDatePicker(materialDialog);
        if (calendar3 != null) {
            datePicker.setMinDate(calendar);
        }
        int i = 0;
        if (calendar4 != null) {
            DatePicker.setDate$default(datePicker, calendar2, false, 2, (Object) null);
        }
        datePicker.addOnDateChanged(new DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$1(materialDialog, calendar, calendar2, z, z3));
        TimePicker timePicker = ViewExtKt.getTimePicker(materialDialog);
        timePicker.setIs24HourView(Boolean.valueOf(z2));
        ViewExtKt.hour(timePicker, calendar4 != null ? calendar2.get(11) : 12);
        if (calendar4 != null) {
            i = calendar2.get(12);
        }
        ViewExtKt.minute(timePicker, i);
        timePicker.setOnTimeChangedListener(new DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$2(timePicker, materialDialog, z2, calendar2, z));
        MaterialDialog.positiveButton$default(materialDialog, 17039370, (CharSequence) null, new DateTimePickerExtKt$dateTimePicker$4(materialDialog, function2), 2, (Object) null);
        MaterialDialog.negativeButton$default(materialDialog, 17039360, (CharSequence) null, (Function1) null, 6, (Object) null);
        if (z4) {
            DialogCallbackExtKt.onDismiss(materialDialog, new DateTimePickerExtKt$dateTimePicker$5(new TimeChangeListener(materialDialog.getWindowContext(), ViewExtKt.getTimePicker(materialDialog), new DateTimePickerExtKt$dateTimePicker$changeListener$1(materialDialog, z4))));
        }
        return materialDialog2;
    }

    /* access modifiers changed from: private */
    public static final boolean didDateChange(Calendar calendar, Calendar calendar2) {
        return (calendar == null || CalendarsKt.getDayOfMonth(calendar) == CalendarsKt.getDayOfMonth(calendar2)) ? false : true;
    }

    public static final Calendar selectedDateTime(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$selectedDateTime");
        DatePicker datePicker = ViewExtKt.getDatePicker(materialDialog);
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "getDatePicker()");
        TimePicker timePicker = ViewExtKt.getTimePicker(materialDialog);
        Intrinsics.checkExpressionValueIsNotNull(timePicker, "getTimePicker()");
        return DateTimeExtKt.toCalendar(datePicker, timePicker);
    }
}
