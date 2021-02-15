package com.webmd.medscape.live.explorelivevents.util;

import android.app.Dialog;
import androidx.core.util.Pair;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012F\u0010\u0002\u001aB\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005* \u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Landroidx/core/util/Pair;", "", "kotlin.jvm.PlatformType", "onPositiveButtonClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: Extensions.kt */
final class ExtensionsKt$showDateRangePicker$3<S> implements MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> {
    final /* synthetic */ MaterialDatePicker $picker;
    final /* synthetic */ Function1 $successCallback;

    ExtensionsKt$showDateRangePicker$3(MaterialDatePicker materialDatePicker, Function1 function1) {
        this.$picker = materialDatePicker;
        this.$successCallback = function1;
    }

    public final void onPositiveButtonClick(Pair<Long, Long> pair) {
        String str;
        String str2 = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Long l = (Long) pair.first;
        if (l != null) {
            Intrinsics.checkNotNullExpressionValue(l, "startTime");
            str = simpleDateFormat.format(new Date(l.longValue()));
        } else {
            str = str2;
        }
        Long l2 = (Long) pair.second;
        if (l2 != null) {
            Intrinsics.checkNotNullExpressionValue(l2, "endTime");
            str2 = simpleDateFormat.format(new Date(l2.longValue()));
        }
        this.$picker.dismiss();
        Dialog dialog = this.$picker.getDialog();
        if (dialog != null) {
            dialog.dismiss();
        }
        this.$successCallback.invoke(new kotlin.Pair(str, str2));
    }
}
