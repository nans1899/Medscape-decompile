package com.afollestad.date;

import com.afollestad.date.controllers.DatePickerController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: DatePicker.kt */
final /* synthetic */ class DatePicker$onFinishInflate$1 extends FunctionReference implements Function0<Unit> {
    DatePicker$onFinishInflate$1(DatePickerController datePickerController) {
        super(0, datePickerController);
    }

    public final String getName() {
        return "previousMonth";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(DatePickerController.class);
    }

    public final String getSignature() {
        return "previousMonth()V";
    }

    public final void invoke() {
        ((DatePickerController) this.receiver).previousMonth();
    }
}
