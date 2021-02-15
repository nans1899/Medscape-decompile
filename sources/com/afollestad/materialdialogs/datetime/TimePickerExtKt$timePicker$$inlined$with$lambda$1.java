package com.afollestad.materialdialogs.datetime;

import android.widget.TimePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import java.util.Calendar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/TimePicker;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "onTimeChanged", "com/afollestad/materialdialogs/datetime/TimePickerExtKt$timePicker$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: TimePickerExt.kt */
final class TimePickerExtKt$timePicker$$inlined$with$lambda$1 implements TimePicker.OnTimeChangedListener {
    final /* synthetic */ Calendar $currentTime$inlined;
    final /* synthetic */ boolean $requireFutureTime$inlined;
    final /* synthetic */ boolean $show24HoursView$inlined;
    final /* synthetic */ MaterialDialog $this_timePicker$inlined;
    final /* synthetic */ TimePicker $this_with;

    TimePickerExtKt$timePicker$$inlined$with$lambda$1(TimePicker timePicker, MaterialDialog materialDialog, boolean z, Calendar calendar, boolean z2) {
        this.$this_with = timePicker;
        this.$this_timePicker$inlined = materialDialog;
        this.$show24HoursView$inlined = z;
        this.$currentTime$inlined = calendar;
        this.$requireFutureTime$inlined = z2;
    }

    public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
        DialogActionExtKt.setActionButtonEnabled(this.$this_timePicker$inlined, WhichButton.POSITIVE, !this.$requireFutureTime$inlined || DateTimeExtKt.isFutureTime(this.$this_with));
    }
}
