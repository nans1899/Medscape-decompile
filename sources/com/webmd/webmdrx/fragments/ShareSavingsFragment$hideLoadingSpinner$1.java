package com.webmd.webmdrx.fragments;

import android.widget.ProgressBar;
import com.webmd.webmdrx.fragments.ShareSavingsFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
final class ShareSavingsFragment$hideLoadingSpinner$1 implements Runnable {
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$hideLoadingSpinner$1(ShareSavingsFragment shareSavingsFragment) {
        this.this$0 = shareSavingsFragment;
    }

    public final void run() {
        ProgressBar access$getMProgressBar$p = this.this$0.mProgressBar;
        Intrinsics.checkNotNull(access$getMProgressBar$p);
        access$getMProgressBar$p.setVisibility(8);
        ShareSavingsFragment.ScreenClickablity access$getMScreenClickability$p = this.this$0.mScreenClickability;
        Intrinsics.checkNotNull(access$getMScreenClickability$p);
        access$getMScreenClickability$p.setScreenClickability(true);
    }
}
