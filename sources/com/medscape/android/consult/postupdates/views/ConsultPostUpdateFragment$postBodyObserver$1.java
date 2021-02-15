package com.medscape.android.consult.postupdates.views;

import androidx.lifecycle.Observer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: ConsultPostUpdateFragment.kt */
final class ConsultPostUpdateFragment$postBodyObserver$1<T> implements Observer<String> {
    final /* synthetic */ ConsultPostUpdateFragment this$0;

    ConsultPostUpdateFragment$postBodyObserver$1(ConsultPostUpdateFragment consultPostUpdateFragment) {
        this.this$0 = consultPostUpdateFragment;
    }

    public final void onChanged(String str) {
        this.this$0.checkFields();
    }
}
