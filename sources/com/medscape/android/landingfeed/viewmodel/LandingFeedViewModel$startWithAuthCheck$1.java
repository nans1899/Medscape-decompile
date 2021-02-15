package com.medscape.android.landingfeed.viewmodel;

import androidx.fragment.app.FragmentActivity;
import com.android.volley.AuthFailureError;
import com.medscape.android.activity.login.LogoutHandler;
import com.medscape.android.util.Util;
import com.medscape.android.welcome.LoginCompletedCallback;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0014\u0010\b\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0003j\u0002`\u0004H\u0016¨\u0006\t"}, d2 = {"com/medscape/android/landingfeed/viewmodel/LandingFeedViewModel$startWithAuthCheck$1", "Lcom/wbmd/wbmdcommons/callbacks/ICallbackEvent;", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onCompleted", "", "obj", "onError", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
public final class LandingFeedViewModel$startWithAuthCheck$1 implements ICallbackEvent<Object, Exception> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ LandingFeedViewModel this$0;

    LandingFeedViewModel$startWithAuthCheck$1(LandingFeedViewModel landingFeedViewModel, FragmentActivity fragmentActivity) {
        this.this$0 = landingFeedViewModel;
        this.$activity = fragmentActivity;
    }

    public void onError(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "obj");
        if (!(exc.getCause() instanceof AuthFailureError)) {
            this.this$0.refreshFailedFeed(true);
        } else if (this.this$0.getMPayload() != null) {
            new LogoutHandler().handleLogoutWithRedirect(this.$activity, this.this$0.getMPayload());
        } else {
            new LogoutHandler().handleLogout(this.$activity);
        }
    }

    public void onCompleted(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Util.onLoginSuccess(this.$activity, obj.toString(), -1, (LoginCompletedCallback) null);
        this.this$0.refreshFailedFeed(true);
    }
}
