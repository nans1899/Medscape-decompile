package com.afollestad.materialdialogs.datetime;

import android.widget.TimePicker;
import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/TimePicker;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "onTimeChanged", "com/afollestad/materialdialogs/datetime/DateTimePickerExtKt$dateTimePicker$3$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateTimePickerExt.kt */
final class DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$2 implements TimePicker.OnTimeChangedListener {
    final /* synthetic */ Calendar $currentDateTime$inlined;
    final /* synthetic */ boolean $requireFutureDateTime$inlined;
    final /* synthetic */ boolean $show24HoursView$inlined;
    final /* synthetic */ TimePicker $this_apply;
    final /* synthetic */ MaterialDialog $this_dateTimePicker$inlined;

    DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$2(TimePicker timePicker, MaterialDialog materialDialog, boolean z, Calendar calendar, boolean z2) {
        this.$this_apply = timePicker;
        this.$this_dateTimePicker$inlined = materialDialog;
        this.$show24HoursView$inlined = z;
        this.$currentDateTime$inlined = calendar;
        this.$requireFutureDateTime$inlined = z2;
    }

    public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
        DatePicker datePicker = ViewExtKt.getDatePicker(this.$this_dateTimePicker$inlined);
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "getDatePicker()");
        TimePicker timePicker2 = this.$this_apply;
        Intrinsics.checkExpressionValueIsNotNull(timePicker2, "this");
        DialogActionExtKt.setActionButtonEnabled(this.$this_dateTimePicker$inlined, WhichButton.POSITIVE, !this.$requireFutureDateTime$inlined || DateTimeExtKt.isFutureTime(datePicker, timePicker2));
    }
}
