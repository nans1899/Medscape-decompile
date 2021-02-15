package com.medscape.android.myinvites;

import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: MyInvitationsActivity.kt */
final class MyInvitationsActivity$observeViewModel$1<T> implements Observer<Boolean> {
    final /* synthetic */ MyInvitationsActivity this$0;

    MyInvitationsActivity$observeViewModel$1(MyInvitationsActivity myInvitationsActivity) {
        this.this$0 = myInvitationsActivity;
    }

    public final void onChanged(Boolean bool) {
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            ProgressBar progressBar = (ProgressBar) this.this$0._$_findCachedViewById(R.id.progress_bar);
            Intrinsics.checkNotNullExpressionValue(progressBar, "progress_bar");
            progressBar.setVisibility(0);
            return;
        }
        ProgressBar progressBar2 = (ProgressBar) this.this$0._$_findCachedViewById(R.id.progress_bar);
        Intrinsics.checkNotNullExpressionValue(progressBar2, "progress_bar");
        progressBar2.setVisibility(8);
    }
}
