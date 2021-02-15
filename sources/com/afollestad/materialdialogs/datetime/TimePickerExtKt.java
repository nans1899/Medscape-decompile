package com.afollestad.materialdialogs.datetime;

import android.view.View;
import android.widget.TimePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.callbacks.DialogCallbackExtKt;
import com.afollestad.materialdialogs.customview.DialogCustomViewExtKt;
import com.afollestad.materialdialogs.datetime.internal.TimeChangeListener;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001aj\u0010\u0003\u001a\u00020\u0002*\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062>\b\u0002\u0010\b\u001a8\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tj\u0002`\u000f¨\u0006\r"}, d2 = {"selectedTime", "Ljava/util/Calendar;", "Lcom/afollestad/materialdialogs/MaterialDialog;", "timePicker", "currentTime", "requireFutureTime", "", "show24HoursView", "timeCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "dialog", "datetime", "", "Lcom/afollestad/materialdialogs/datetime/DateTimeCallback;"}, k = 2, mv = {1, 1, 16})
/* compiled from: TimePickerExt.kt */
public final class TimePickerExtKt {
    public static /* synthetic */ MaterialDialog timePicker$default(MaterialDialog materialDialog, Calendar calendar, boolean z, boolean z2, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            calendar = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        if ((i & 8) != 0) {
            function2 = null;
        }
        return timePicker(materialDialog, calendar, z, z2, function2);
    }

    public static final MaterialDialog timePicker(MaterialDialog materialDialog, Calendar calendar, boolean z, boolean z2, Function2<? super MaterialDialog, ? super Calendar, Unit> function2) {
        MaterialDialog materialDialog2 = materialDialog;
        Calendar calendar2 = calendar;
        boolean z3 = z;
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$timePicker");
        DialogCustomViewExtKt.customView$default(materialDialog, Integer.valueOf(R.layout.md_datetime_picker_time), (View) null, false, true, false, MDUtil.INSTANCE.isLandscape(materialDialog.getWindowContext()), 22, (Object) null);
        TimePicker timePicker = ViewExtKt.getTimePicker(materialDialog);
        timePicker.setIs24HourView(Boolean.valueOf(z2));
        if (calendar2 != null) {
            ViewExtKt.hour(timePicker, calendar.get(11));
            ViewExtKt.minute(timePicker, calendar.get(12));
        }
        timePicker.setOnTimeChangedListener(new TimePickerExtKt$timePicker$$inlined$with$lambda$1(timePicker, materialDialog, z2, calendar, z));
        MaterialDialog.positiveButton$default(materialDialog, 17039370, (CharSequence) null, new TimePickerExtKt$timePicker$2(materialDialog, function2), 2, (Object) null);
        MaterialDialog.negativeButton$default(materialDialog, 17039360, (CharSequence) null, (Function1) null, 6, (Object) null);
        if (z3) {
            DialogCallbackExtKt.onDismiss(materialDialog, new TimePickerExtKt$timePicker$3(new TimeChangeListener(materialDialog.getWindowContext(), ViewExtKt.getTimePicker(materialDialog), new TimePickerExtKt$timePicker$changeListener$1(materialDialog, z))));
        }
        return materialDialog2;
    }

    public static final Calendar selectedTime(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$selectedTime");
        TimePicker timePicker = ViewExtKt.getTimePicker(materialDialog);
        Intrinsics.checkExpressionValueIsNotNull(timePicker, "getTimePicker()");
        return DateTimeExtKt.toCalendar(timePicker);
    }
}
