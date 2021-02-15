package com.afollestad.materialdialogs.datetime;

import android.widget.TimePicker;
import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/widget/TimePicker;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateTimePickerExt.kt */
final class DateTimePickerExtKt$dateTimePicker$changeListener$1 extends Lambda implements Function1<TimePicker, Unit> {
    final /* synthetic */ boolean $requireFutureDateTime;
    final /* synthetic */ MaterialDialog $this_dateTimePicker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DateTimePickerExtKt$dateTimePicker$changeListener$1(MaterialDialog materialDialog, boolean z) {
        super(1);
        this.$this_dateTimePicker = materialDialog;
        this.$requireFutureDateTime = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TimePicker) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(TimePicker timePicker) {
        Intrinsics.checkParameterIsNotNull(timePicker, "it");
        DatePicker datePicker = ViewExtKt.getDatePicker(this.$this_dateTimePicker);
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "getDatePicker()");
        DialogActionExtKt.setActionButtonEnabled(this.$this_dateTimePicker, WhichButton.POSITIVE, !this.$requireFutureDateTime || DateTimeExtKt.isFutureTime(datePicker, timePicker));
    }
}
