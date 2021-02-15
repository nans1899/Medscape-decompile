package com.medscape.android.myinvites;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.medscape.android.R;
import com.medscape.android.util.MedscapeException;
import java.net.UnknownHostException;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: MyInvitationsActivity.kt */
final class MyInvitationsActivity$observeViewModel$3<T> implements Observer<Throwable> {
    final /* synthetic */ MyInvitationsActivity this$0;

    MyInvitationsActivity$observeViewModel$3(MyInvitationsActivity myInvitationsActivity) {
        this.this$0 = myInvitationsActivity;
    }

    public final void onChanged(Throwable th) {
        MedscapeException medscapeException = new MedscapeException(this.this$0.getResources().getString(R.string.error_connection_required));
        if (th == null) {
            medscapeException.setMessage(this.this$0.getResources().getString(R.string.service_unavailable));
        } else if (th.getCause() instanceof UnknownHostException) {
            medscapeException.setMessage(this.this$0.getResources().getString(R.string.error_connection_required));
        } else {
            medscapeException.setMessage(this.this$0.getResources().getString(R.string.service_unavailable));
        }
        medscapeException.showSnackBar((TextView) this.this$0._$_findCachedViewById(R.id.text_no_data), -2, this.this$0.getResources().getString(R.string.retry), new View.OnClickListener(this) {
            final /* synthetic */ MyInvitationsActivity$observeViewModel$3 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                MyInvitationsActivity.access$getViewModel$p(this.this$0.this$0).load(this.this$0.this$0);
            }
        });
    }
}
