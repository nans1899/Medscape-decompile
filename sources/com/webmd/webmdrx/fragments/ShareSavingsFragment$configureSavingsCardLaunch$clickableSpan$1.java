package com.webmd.webmdrx.fragments;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.webmd.webmdrx.activities.RxBaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/webmd/webmdrx/fragments/ShareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "textView", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
public final class ShareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1 extends ClickableSpan {
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$configureSavingsCardLaunch$clickableSpan$1(ShareSavingsFragment shareSavingsFragment) {
        this.this$0 = shareSavingsFragment;
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "textView");
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null && this.this$0.isAdded() && (activity instanceof RxBaseActivity)) {
            ((RxBaseActivity) activity).showSavingsCard((Context) activity, this.this$0.mPrice, this.this$0.mForm, this.this$0.mDosage, this.this$0.mQuantity, this.this$0.mDrugName, this.this$0.mDrugPackageSize, this.this$0.mIcd);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "ds");
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(true);
    }
}
