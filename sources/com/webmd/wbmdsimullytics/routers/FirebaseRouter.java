package com.webmd.wbmdsimullytics.routers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.webmd.wbmdsimullytics.callbacks.IUserIdListener;

public class FirebaseRouter implements IRouter {
    /* access modifiers changed from: private */
    public static final String TAG = FirebaseRouter.class.getSimpleName();
    private FirebaseAnalytics mAnalyticsInstance;
    private FirebaseMessaging mMessagingInstance = FirebaseMessaging.getInstance();

    public FirebaseRouter(Context context) {
        this.mAnalyticsInstance = FirebaseAnalytics.getInstance(context);
    }

    public void routeEvent(String str) {
        this.mAnalyticsInstance.logEvent(str, (Bundle) null);
    }

    public void routeEvent(String str, Bundle bundle) {
        this.mAnalyticsInstance.logEvent(str, bundle);
    }

    public void routeUserAttribute(String str, String str2) {
        this.mAnalyticsInstance.setUserProperty(str, str2);
    }

    public void routeUserAttribute(String str, boolean z) {
        this.mAnalyticsInstance.setUserProperty(str, z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
    }

    public void routeUserAttribute(String str, String[] strArr) {
        this.mAnalyticsInstance.setUserProperty(str, strArr.toString());
    }

    public void unrouteUserAttribute(String str) {
        this.mAnalyticsInstance.setUserProperty(str, (String) null);
    }

    public void routeUserId(String str) {
        this.mAnalyticsInstance.setUserId(str);
        if (str == null || !str.matches("[a-zA-Z0-9-_.~%]{1,900}")) {
            Log.i(TAG, "Firebase subscribeToTopic Failed, userId doesn't match the format");
        } else {
            this.mMessagingInstance.subscribeToTopic(str).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.i(FirebaseRouter.TAG, "Success");
                    }
                }
            });
        }
    }

    public void getUserId(Activity activity, final IUserIdListener iUserIdListener) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity, new OnSuccessListener<InstanceIdResult>() {
            public void onSuccess(InstanceIdResult instanceIdResult) {
                iUserIdListener.onUserIdReceived(instanceIdResult.getToken());
            }
        });
    }
}
