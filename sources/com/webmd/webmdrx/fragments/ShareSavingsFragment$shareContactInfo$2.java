package com.webmd.webmdrx.fragments;

import android.widget.EditText;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.intf.IRxShareSavingsCardListener;
import com.webmd.webmdrx.util.WebMDException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/webmd/webmdrx/fragments/ShareSavingsFragment$shareContactInfo$2", "Lcom/webmd/webmdrx/intf/IRxShareSavingsCardListener;", "onFailure", "", "exception", "Lcom/webmd/webmdrx/util/WebMDException;", "onSuccess", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareSavingsFragment.kt */
public final class ShareSavingsFragment$shareContactInfo$2 implements IRxShareSavingsCardListener {
    final /* synthetic */ ShareSavingsFragment this$0;

    ShareSavingsFragment$shareContactInfo$2(ShareSavingsFragment shareSavingsFragment) {
        this.this$0 = shareSavingsFragment;
    }

    public void onSuccess() {
        int i;
        ShareSavingsFragment shareSavingsFragment;
        if (this.this$0.isFragmentAttached()) {
            this.this$0.hideLoadingSpinner();
            EditText access$getMContactInfoEditText$p = this.this$0.mContactInfoEditText;
            Intrinsics.checkNotNull(access$getMContactInfoEditText$p);
            access$getMContactInfoEditText$p.setText("");
            if (this.this$0.mIsShareviaEmail) {
                shareSavingsFragment = this.this$0;
                i = R.string.email_sent;
            } else {
                shareSavingsFragment = this.this$0;
                i = R.string.sms_sent;
            }
            String string = shareSavingsFragment.getString(i);
            Intrinsics.checkNotNullExpressionValue(string, "if (mIsShareviaEmail) ge…String(R.string.sms_sent)");
            this.this$0.showSuccessMsg(string);
        }
    }

    public void onFailure(WebMDException webMDException) {
        if (this.this$0.isFragmentAttached()) {
            this.this$0.hideLoadingSpinner();
            if (webMDException == null || webMDException.getMessage() == null) {
                ShareSavingsFragment shareSavingsFragment = this.this$0;
                String string = shareSavingsFragment.getString(R.string.error_sharing_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error_sharing_failed)");
                shareSavingsFragment.showSimpleDialog(string);
                return;
            }
            ShareSavingsFragment shareSavingsFragment2 = this.this$0;
            String message = webMDException.getMessage();
            Intrinsics.checkNotNull(message);
            Intrinsics.checkNotNullExpressionValue(message, "exception!!.message!!");
            shareSavingsFragment2.showSimpleDialog(message);
        }
    }
}
