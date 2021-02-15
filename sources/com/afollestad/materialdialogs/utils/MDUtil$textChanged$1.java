package com.afollestad.materialdialogs.utils;

import android.text.Editable;
import android.text.TextWatcher;
import com.appboy.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/afollestad/materialdialogs/utils/MDUtil$textChanged$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: MDUtil.kt */
public final class MDUtil$textChanged$1 implements TextWatcher {
    final /* synthetic */ Function1 $callback;

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
    }

    MDUtil$textChanged$1(Function1 function1) {
        this.$callback = function1;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        this.$callback.invoke(charSequence);
    }
}
