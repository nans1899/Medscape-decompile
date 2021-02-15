package com.afollestad.materialdialogs.datetime;

import android.widget.TimePicker;
import androidx.viewpager.widget.ViewPager;
import com.afollestad.date.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.WhichButton;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.datetime.utils.DateTimeExtKt;
import com.afollestad.materialdialogs.datetime.utils.ViewExtKt;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "previous", "Ljava/util/Calendar;", "date", "invoke", "com/afollestad/materialdialogs/datetime/DateTimePickerExtKt$dateTimePicker$2$3"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateTimePickerExt.kt */
final class DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$1 extends Lambda implements Function2<Calendar, Calendar, Unit> {
    final /* synthetic */ boolean $autoFlipToTime$inlined;
    final /* synthetic */ Calendar $currentDateTime$inlined;
    final /* synthetic */ Calendar $minDateTime$inlined;
    final /* synthetic */ boolean $requireFutureDateTime$inlined;
    final /* synthetic */ MaterialDialog $this_dateTimePicker$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DateTimePickerExtKt$dateTimePicker$$inlined$apply$lambda$1(MaterialDialog materialDialog, Calendar calendar, Calendar calendar2, boolean z, boolean z2) {
        super(2);
        this.$this_dateTimePicker$inlined = materialDialog;
        this.$minDateTime$inlined = calendar;
        this.$currentDateTime$inlined = calendar2;
        this.$requireFutureDateTime$inlined = z;
        this.$autoFlipToTime$inlined = z2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Calendar) obj, (Calendar) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Calendar calendar, Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar, "previous");
        Intrinsics.checkParameterIsNotNull(calendar2, OmnitureConstants.OMNITURE_FILTER_DATE);
        DatePicker datePicker = ViewExtKt.getDatePicker(this.$this_dateTimePicker$inlined);
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "getDatePicker()");
        TimePicker timePicker = ViewExtKt.getTimePicker(this.$this_dateTimePicker$inlined);
        Intrinsics.checkExpressionValueIsNotNull(timePicker, "getTimePicker()");
        DialogActionExtKt.setActionButtonEnabled(this.$this_dateTimePicker$inlined, WhichButton.POSITIVE, !this.$requireFutureDateTime$inlined || DateTimeExtKt.isFutureTime(datePicker, timePicker));
        if (this.$autoFlipToTime$inlined && DateTimePickerExtKt.didDateChange(calendar, calendar2)) {
            ViewPager pager = ViewExtKt.getPager(this.$this_dateTimePicker$inlined);
            Intrinsics.checkExpressionValueIsNotNull(pager, "getPager()");
            pager.setCurrentItem(1);
        }
    }
}
