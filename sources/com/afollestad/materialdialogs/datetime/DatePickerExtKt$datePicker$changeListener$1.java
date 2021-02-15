package com.afollestad.materialdialogs.datetime;

import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/afollestad/date/DatePicker;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DatePickerExt.kt */
final class DatePickerExtKt$datePicker$changeListener$1 extends Lambda implements Function1<DatePicker, Unit> {
    final /* synthetic */ boolean $requireFutureDate;
    final /* synthetic */ MaterialDialog $this_datePicker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePickerExtKt$datePicker$changeListener$1(MaterialDialog materialDialog, boolean z) {
        super(1);
        this.$this_datePicker = materialDialog;
        this.$requireFutureDate = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DatePicker) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(DatePicker datePicker) {
        Intrinsics.checkParameterIsNotNull(datePicker, "it");
        DialogActionExtKt.setActionButtonEnabled(this.$this_datePicker, WhichButton.POSITIVE, !this.$requireFutureDate || DateTimeExtKt.isFutureDate(datePicker));
    }
}
