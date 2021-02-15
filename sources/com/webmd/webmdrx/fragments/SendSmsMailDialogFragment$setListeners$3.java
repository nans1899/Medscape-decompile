package com.webmd.webmdrx.fragments;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.webmd.webmdrx.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: SendSmsMailDialogFragment.kt */
final class SendSmsMailDialogFragment$setListeners$3 implements View.OnClickListener {
    final /* synthetic */ SendSmsMailDialogFragment this$0;

    SendSmsMailDialogFragment$setListeners$3(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        this.this$0 = sendSmsMailDialogFragment;
    }

    public final void onClick(View view) {
        if (!this.this$0.isRequiredFieldsEmpty()) {
            SendSmsMailDialogFragment.access$getProgressBar$p(this.this$0).setVisibility(0);
            if (Intrinsics.areEqual((Object) SendSmsMailDialogFragment.access$getViewMode$p(this.this$0), (Object) this.this$0.SMS)) {
                if (SendSmsMailDialogFragment.access$getViewModel$p(this.this$0).isValidPhone(SendSmsMailDialogFragment.access$getSmsOrMailText$p(this.this$0).getText().toString())) {
                    SendSmsMailDialogFragment.access$getViewModel$p(this.this$0).sendSMS(SendSmsMailDialogFragment.access$getSmsOrMailText$p(this.this$0).getText().toString(), SendSmsMailDialogFragment.access$getCardCookie$p(this.this$0));
                    return;
                }
                SendSmsMailDialogFragment sendSmsMailDialogFragment = this.this$0;
                FragmentActivity requireActivity = sendSmsMailDialogFragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                sendSmsMailDialogFragment.displayError(requireActivity.getResources().getString(R.string.error_invalid_phone));
            } else if (SendSmsMailDialogFragment.access$getViewModel$p(this.this$0).isValidEmail(SendSmsMailDialogFragment.access$getSmsOrMailText$p(this.this$0).getText().toString())) {
                SendSmsMailDialogFragment.access$getViewModel$p(this.this$0).sendMail(SendSmsMailDialogFragment.access$getSmsOrMailText$p(this.this$0).getText().toString(), SendSmsMailDialogFragment.access$getCardCookie$p(this.this$0));
            } else {
                SendSmsMailDialogFragment sendSmsMailDialogFragment2 = this.this$0;
                FragmentActivity requireActivity2 = sendSmsMailDialogFragment2.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                sendSmsMailDialogFragment2.displayError(requireActivity2.getResources().getString(R.string.error_invalid_email));
            }
        }
    }
}
