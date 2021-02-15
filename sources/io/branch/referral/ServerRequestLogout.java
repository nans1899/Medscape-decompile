package io.branch.referral;

import android.content.Context;
import android.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestLogout extends ServerRequest {
    private Branch.LogoutStatusListener callback_;

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return false;
    }

    public ServerRequestLogout(Context context, Branch.LogoutStatusListener logoutStatusListener) {
        super(context, Defines.RequestPath.Logout.getPath());
        this.callback_ = logoutStatusListener;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestLogout(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Branch.LogoutStatusListener logoutStatusListener;
        try {
            this.prefHelper_.setSessionID(serverResponse.getObject().getString(Defines.Jsonkey.SessionID.getKey()));
            this.prefHelper_.setIdentityID(serverResponse.getObject().getString(Defines.Jsonkey.IdentityID.getKey()));
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines.Jsonkey.Link.getKey()));
            this.prefHelper_.setInstallParams("bnc_no_value");
            this.prefHelper_.setSessionParams("bnc_no_value");
            this.prefHelper_.setIdentity("bnc_no_value");
            this.prefHelper_.clearUserValues();
            logoutStatusListener = this.callback_;
            if (logoutStatusListener == null) {
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            logoutStatusListener = this.callback_;
            if (logoutStatusListener == null) {
                return;
            }
        } catch (Throwable th) {
            Branch.LogoutStatusListener logoutStatusListener2 = this.callback_;
            if (logoutStatusListener2 != null) {
                logoutStatusListener2.onLogoutFinished(true, (BranchError) null);
            }
            throw th;
        }
        logoutStatusListener.onLogoutFinished(true, (BranchError) null);
    }

    public void handleFailure(int i, String str) {
        Branch.LogoutStatusListener logoutStatusListener = this.callback_;
        if (logoutStatusListener != null) {
            logoutStatusListener.onLogoutFinished(false, new BranchError("Logout error. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        Branch.LogoutStatusListener logoutStatusListener = this.callback_;
        if (logoutStatusListener == null) {
            return true;
        }
        logoutStatusListener.onLogoutFinished(false, new BranchError("Logout failed", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
