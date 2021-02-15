package com.webmd.webmdrx.fragments;

import android.widget.Toast;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.webmd.webmdrx.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/webmd/webmdrx/fragments/SendSmsMailDialogFragment$setListeners$2", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "onPropertyChanged", "", "sender", "Landroidx/databinding/Observable;", "propertyId", "", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SendSmsMailDialogFragment.kt */
public final class SendSmsMailDialogFragment$setListeners$2 extends Observable.OnPropertyChangedCallback {
    final /* synthetic */ SendSmsMailDialogFragment this$0;

    SendSmsMailDialogFragment$setListeners$2(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        this.this$0 = sendSmsMailDialogFragment;
    }

    public void onPropertyChanged(Observable observable, int i) {
        Intrinsics.checkNotNullParameter(observable, "sender");
        ObservableBoolean isSending = SendSmsMailDialogFragment.access$getViewModel$p(this.this$0).isSending();
        Intrinsics.checkNotNull(isSending);
        if (isSending.get()) {
            SendSmsMailDialogFragment.access$getProgressBar$p(this.this$0).setVisibility(0);
            return;
        }
        SendSmsMailDialogFragment.access$getProgressBar$p(this.this$0).setVisibility(8);
        Toast.makeText(this.this$0.getActivity(), R.string.sms_mail_sent, 0).show();
        SendSmsMailDialogFragment sendSmsMailDialogFragment = this.this$0;
        sendSmsMailDialogFragment.sendOmnitureAction(SendSmsMailDialogFragment.access$getViewMode$p(sendSmsMailDialogFragment).equals(this.this$0.SMS) ? this.this$0.OMNI_RX_SHARE_TEXT : this.this$0.OMNI_RX_SHARE_EMAIL);
        this.this$0.dismiss();
    }
}
