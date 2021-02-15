package com.afollestad.date;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/util/Calendar;", "newDate", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: DatePicker.kt */
final class DatePicker$onDateChanged$1 extends Lambda implements Function2<Calendar, Calendar, Unit> {
    final /* synthetic */ Function1 $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePicker$onDateChanged$1(Function1 function1) {
        super(2);
        this.$block = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Calendar) obj, (Calendar) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Calendar calendar, Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(calendar2, "newDate");
        this.$block.invoke(calendar2);
    }
}
