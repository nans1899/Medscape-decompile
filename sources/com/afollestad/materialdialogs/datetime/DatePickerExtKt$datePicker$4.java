package com.afollestad.materialdialogs.datetime;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/afollestad/materialdialogs/MaterialDialog;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DatePickerExt.kt */
final class DatePickerExtKt$datePicker$4 extends Lambda implements Function1<MaterialDialog, Unit> {
    final /* synthetic */ Function2 $dateCallback;
    final /* synthetic */ MaterialDialog $this_datePicker;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePickerExtKt$datePicker$4(MaterialDialog materialDialog, Function2 function2) {
        super(1);
        this.$this_datePicker = materialDialog;
        this.$dateCallback = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MaterialDialog) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MaterialDialog materialDialog) {
        Function2 function2;
        Intrinsics.checkParameterIsNotNull(materialDialog, "it");
        Calendar date = ViewExtKt.getDatePicker(this.$this_datePicker).getDate();
        if (date != null && (function2 = this.$dateCallback) != null) {
            Unit unit = (Unit) function2.invoke(materialDialog, date);
        }
    }
}
