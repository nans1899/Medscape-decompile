package com.afollestad.date.controllers;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/util/Calendar;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: DatePickerController.kt */
final class DatePickerController$setFullDate$1 extends Lambda implements Function0<Calendar> {
    final /* synthetic */ Calendar $calendar;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePickerController$setFullDate$1(Calendar calendar) {
        super(0);
        this.$calendar = calendar;
    }

    public final Calendar invoke() {
        Object clone = this.$calendar.clone();
        if (clone != null) {
            return (Calendar) clone;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
    }
}
