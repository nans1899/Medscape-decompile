package com.afollestad.materialdialogs.datetime;

import android.view.View;
import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.callbacks.DialogCallbackExtKt;
import com.afollestad.materialdialogs.customview.DialogCustomViewExtKt;
import com.afollestad.materialdialogs.datetime.internal.TimeChangeListener;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001ax\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072>\b\u0002\u0010\b\u001a8\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tj\u0002`\u000f\u001a\f\u0010\u0010\u001a\u00020\u0003*\u00020\u0001H\u0007¨\u0006\r"}, d2 = {"datePicker", "Lcom/afollestad/materialdialogs/MaterialDialog;", "minDate", "Ljava/util/Calendar;", "maxDate", "currentDate", "requireFutureDate", "", "dateCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "dialog", "datetime", "", "Lcom/afollestad/materialdialogs/datetime/DateTimeCallback;", "selectedDate"}, k = 2, mv = {1, 1, 16})
/* compiled from: DatePickerExt.kt */
public final class DatePickerExtKt {
    public static /* synthetic */ MaterialDialog datePicker$default(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, Calendar calendar3, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            calendar = null;
        }
        if ((i & 2) != 0) {
            calendar2 = null;
        }
        Calendar calendar4 = calendar2;
        if ((i & 4) != 0) {
            calendar3 = null;
        }
        Calendar calendar5 = calendar3;
        boolean z2 = (i & 8) != 0 ? false : z;
        if ((i & 16) != 0) {
            function2 = null;
        }
        return datePicker(materialDialog, calendar, calendar4, calendar5, z2, function2);
    }

    public static final MaterialDialog datePicker(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, Calendar calendar3, boolean z, Function2<? super MaterialDialog, ? super Calendar, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$datePicker");
        DialogCustomViewExtKt.customView$default(materialDialog, Integer.valueOf(R.layout.md_datetime_picker_date), (View) null, false, true, false, MDUtil.INSTANCE.isLandscape(materialDialog.getWindowContext()), 22, (Object) null);
        boolean z2 = true;
        if (calendar == null || calendar3 == null || calendar.before(calendar3)) {
            if (!(calendar2 == null || calendar3 == null || calendar2.after(calendar3))) {
                z2 = false;
            }
            if (z2) {
                DatePicker datePicker = ViewExtKt.getDatePicker(materialDialog);
                if (calendar != null) {
                    datePicker.setMinDate(calendar);
                }
                if (calendar2 != null) {
                    datePicker.setMaxDate(calendar2);
                }
                if (calendar3 != null) {
                    DatePicker.setDate$default(datePicker, calendar3, false, 2, (Object) null);
                }
                datePicker.addOnDateChanged(new DatePickerExtKt$datePicker$$inlined$apply$lambda$1(materialDialog, calendar, calendar2, calendar3, z));
                MaterialDialog materialDialog2 = materialDialog;
                MaterialDialog.positiveButton$default(materialDialog2, 17039370, (CharSequence) null, new DatePickerExtKt$datePicker$4(materialDialog, function2), 2, (Object) null);
                MaterialDialog.negativeButton$default(materialDialog2, 17039360, (CharSequence) null, (Function1) null, 6, (Object) null);
                if (z) {
                    DialogCallbackExtKt.onDismiss(materialDialog, new DatePickerExtKt$datePicker$5(new TimeChangeListener(materialDialog.getWindowContext(), ViewExtKt.getDatePicker(materialDialog), new DatePickerExtKt$datePicker$changeListener$1(materialDialog, z))));
                }
                return materialDialog;
            }
            throw new IllegalStateException("Your `maxDate` must be bigger than `currentDate`.".toString());
        }
        throw new IllegalStateException("Your `minDate` must be less than `currentDate`.".toString());
    }

    public static final Calendar selectedDate(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$selectedDate");
        Calendar date = ViewExtKt.getDatePicker(materialDialog).getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        return date;
    }
}
