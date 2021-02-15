package com.webmd.webmdrx.fragments;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
final class ShareSavingsFragment$shareContactInfo$3 implements View.OnClickListener {
    final /* synthetic */ String $contactInfo;
    final /* synthetic */ boolean $isEmail;
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$shareContactInfo$3(ShareSavingsFragment shareSavingsFragment, String str, boolean z) {
        this.this$0 = shareSavingsFragment;
        this.$contactInfo = str;
        this.$isEmail = z;
    }

    public final void onClick(View view) {
        this.this$0.showLoadingSpinner();
        this.this$0.shareContactInfo(this.$contactInfo, this.$isEmail);
    }
}
