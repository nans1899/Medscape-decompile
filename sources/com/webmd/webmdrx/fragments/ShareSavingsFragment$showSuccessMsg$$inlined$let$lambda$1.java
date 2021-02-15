package com.webmd.webmdrx.fragments;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "which", "", "onClick", "com/webmd/webmdrx/fragments/ShareSavingsFragment$showSuccessMsg$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
final class ShareSavingsFragment$showSuccessMsg$$inlined$let$lambda$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ String $message$inlined;
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$showSuccessMsg$$inlined$let$lambda$1(ShareSavingsFragment shareSavingsFragment, String str) {
        this.this$0 = shareSavingsFragment;
        this.$message$inlined = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
