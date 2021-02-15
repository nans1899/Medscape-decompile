package com.afollestad.date.managers;

import android.widget.TextView;
import com.afollestad.date.managers.DatePickerLayoutManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/widget/TextView;", "invoke", "com/afollestad/date/managers/DatePickerLayoutManager$setupHeaderViews$1$1"}, k = 3, mv = {1, 1, 15})
/* compiled from: DatePickerLayoutManager.kt */
final class DatePickerLayoutManager$setupHeaderViews$$inlined$apply$lambda$1 extends Lambda implements Function1<TextView, Unit> {
    final /* synthetic */ DatePickerLayoutManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatePickerLayoutManager$setupHeaderViews$$inlined$apply$lambda$1(DatePickerLayoutManager datePickerLayoutManager) {
        super(1);
        this.this$0 = datePickerLayoutManager;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TextView) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "it");
        this.this$0.setMode(DatePickerLayoutManager.Mode.YEAR_LIST);
    }
}
