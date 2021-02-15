package com.webmd.wbmdsimullytics.routers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.appboy.Appboy;
import com.appboy.models.outgoing.AppboyProperties;
import com.webmd.wbmdsimullytics.callbacks.IUserIdListener;
import org.json.JSONObject;

public class BrazeRouter implements IRouter {
    private static final String TAG = BrazeRouter.class.getSimpleName();
    private Activity mActivity;
    private Appboy mInstance;

    public BrazeRouter(Activity activity) {
        this.mInstance = Appboy.getInstance(activity);
        this.mActivity = activity;
    }

    public void routeEvent(String str) {
        Appboy appboy = this.mInstance;
        if (appboy != null) {
            appboy.logCustomEvent(str);
        }
    }

    public void routeEvent(String str, Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str2 : bundle.keySet()) {
                jSONObject.put(str2, bundle.get(str2));
                if (this.mInstance != null) {
                    this.mInstance.logCustomEvent(str, new AppboyProperties(jSONObject));
                }
            }
        } catch (Throwable th) {
            Log.e(TAG, th.getMessage());
        }
    }

    public void routeUserAttribute(String str, String str2) {
        Appboy appboy = this.mInstance;
        if (appboy != null && appboy.getCurrentUser() != null) {
            this.mInstance.getCurrentUser().setCustomUserAttribute(str, str2);
        }
    }

    public void routeUserAttribute(String str, boolean z) {
        Appboy appboy = this.mInstance;
        if (appboy != null && appboy.getCurrentUser() != null) {
            this.mInstance.getCurrentUser().setCustomUserAttribute(str, z);
        }
    }

    public void routeUserAttribute(String str, String[] strArr) {
        Appboy appboy = this.mInstance;
        if (appboy != null && appboy.getCurrentUser() != null) {
            this.mInstance.getCurrentUser().setCustomAttributeArray(str, strArr);
        }
    }

    public void unrouteUserAttribute(String str) {
        Appboy appboy = this.mInstance;
        if (appboy != null && appboy.getCurrentUser() != null) {
            this.mInstance.getCurrentUser().unsetCustomUserAttribute(str);
        }
    }

    public void routeUserId(String str) {
        Appboy appboy = this.mInstance;
        if (appboy != null) {
            appboy.changeUser(str);
        }
    }

    public void getUserId(IUserIdListener iUserIdListener) {
        iUserIdListener.onUserIdReceived(this.mInstance.getDeviceId());
    }
}
