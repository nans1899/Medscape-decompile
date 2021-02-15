package com.medscape.android.activity;

import android.text.Editable;
import android.text.TextWatcher;
import com.appboy.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e"}, d2 = {"com/medscape/android/activity/PromoDebugActivity$onCreate$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PromoDebugActivity.kt */
public final class PromoDebugActivity$onCreate$1 implements TextWatcher {
    final /* synthetic */ PromoDebugActivity this$0;

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkNotNullParameter(editable, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
    }

    PromoDebugActivity$onCreate$1(PromoDebugActivity promoDebugActivity) {
        this.this$0 = promoDebugActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        if (charSequence.length() > 0) {
            this.this$0.getBtnLaunch().setEnabled(true);
            this.this$0.getBtnLaunch().setClickable(true);
            this.this$0.getBtnRestore().setEnabled(true);
            this.this$0.getBtnRestore().setClickable(true);
            return;
        }
        this.this$0.getBtnLaunch().setEnabled(false);
        this.this$0.getBtnLaunch().setClickable(false);
        this.this$0.getBtnRestore().setEnabled(false);
        this.this$0.getBtnRestore().setClickable(false);
    }
}
