package com.medscape.android.welcome;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.medscape.android.util.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/welcome/LoginReceiver;", "Landroid/content/BroadcastReceiver;", "activity", "Landroid/app/Activity;", "callback", "Lcom/medscape/android/welcome/LoginCompletedCallback;", "(Landroid/app/Activity;Lcom/medscape/android/welcome/LoginCompletedCallback;)V", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "getCallback", "()Lcom/medscape/android/welcome/LoginCompletedCallback;", "setCallback", "(Lcom/medscape/android/welcome/LoginCompletedCallback;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LoginReceiver.kt */
public final class LoginReceiver extends BroadcastReceiver {
    private Activity activity;
    private LoginCompletedCallback callback;

    public LoginReceiver(Activity activity2, LoginCompletedCallback loginCompletedCallback) {
        Intrinsics.checkNotNullParameter(activity2, "activity");
        Intrinsics.checkNotNullParameter(loginCompletedCallback, "callback");
        this.activity = activity2;
        this.callback = loginCompletedCallback;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final LoginCompletedCallback getCallback() {
        return this.callback;
    }

    public final void setActivity(Activity activity2) {
        Intrinsics.checkNotNullParameter(activity2, "<set-?>");
        this.activity = activity2;
    }

    public final void setCallback(LoginCompletedCallback loginCompletedCallback) {
        Intrinsics.checkNotNullParameter(loginCompletedCallback, "<set-?>");
        this.callback = loginCompletedCallback;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String stringExtra = intent.getStringExtra(AuthComponentConstants.LOGIN_STATUS);
        int intExtra = intent.getIntExtra(AuthComponentConstants.TRIGGER_SCREEN, -1);
        String stringExtra2 = intent.getStringExtra(AuthComponentConstants.EXTRA_LOGIN_DATA);
        if (Intrinsics.areEqual((Object) stringExtra, (Object) AuthComponentConstants.LOGIN_SUCCESS)) {
            Util.onLoginSuccess(this.activity, stringExtra2, intExtra, this.callback);
        }
    }
}
