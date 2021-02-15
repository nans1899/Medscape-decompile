package com.afollestad.materialdialogs.datetime;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.datetime.internal.TimeChangeListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/afollestad/materialdialogs/MaterialDialog;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateTimePickerExt.kt */
final class DateTimePickerExtKt$dateTimePicker$5 extends Lambda implements Function1<MaterialDialog, Unit> {
    final /* synthetic */ TimeChangeListener $changeListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DateTimePickerExtKt$dateTimePicker$5(TimeChangeListener timeChangeListener) {
        super(1);
        this.$changeListener = timeChangeListener;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MaterialDialog) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MaterialDialog materialDialog) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "it");
        this.$changeListener.dispose();
    }
}
