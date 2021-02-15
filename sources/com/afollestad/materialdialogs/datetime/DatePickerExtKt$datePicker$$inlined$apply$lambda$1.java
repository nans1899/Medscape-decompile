package com.afollestad.materialdialogs.datetime;

import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/util/Calendar;", "<anonymous parameter 1>", "invoke", "com/afollestad/materialdialogs/datetime/DatePickerExtKt$datePicker$3$4"}, k = 3, mv = {1, 1, 16})
/* compiled from: DatePickerExt.kt */
final class DatePickerExtKt$datePicker$$inlined$apply$lambda$1 extends Lambda implements Function2<Calendar, Calendar, Unit> {
    final /* synthetic */ Calendar $currentDate$inlined;
    final /* synthetic */ Calendar $maxDate$inlined;
    final /* synthetic */ Calendar $minDate$inlined;
    final /* synthetic */ boolean $requireFutureDate$inlined;
    final /* synthetic */ MaterialDialog $this_datePicker$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePickerExtKt$datePicker$$inlined$apply$lambda$1(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, Calendar calendar3, boolean z) {
        super(2);
        this.$this_datePicker$inlined = materialDialog;
        this.$minDate$inlined = calendar;
        this.$maxDate$inlined = calendar2;
        this.$currentDate$inlined = calendar3;
        this.$requireFutureDate$inlined = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Calendar) obj, (Calendar) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Calendar calendar, Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(calendar2, "<anonymous parameter 1>");
        DatePicker datePicker = ViewExtKt.getDatePicker(this.$this_datePicker$inlined);
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "getDatePicker()");
        DialogActionExtKt.setActionButtonEnabled(this.$this_datePicker$inlined, WhichButton.POSITIVE, !this.$requireFutureDate$inlined || DateTimeExtKt.isFutureDate(datePicker));
    }
}
