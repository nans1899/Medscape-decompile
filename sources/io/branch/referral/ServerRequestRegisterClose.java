package io.branch.referral;

import android.content.Context;
import android.util.Log;
import io.branch.indexing.ContentDiscoverer;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterClose extends ServerRequest {
    public void clearCallbacks() {
    }

    public void handleFailure(int i, String str) {
    }

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return false;
    }

    public ServerRequestRegisterClose(Context context) {
        super(context, Defines.RequestPath.RegisterClose.getPath());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            JSONObject contentDiscoverDataForCloseRequest = ContentDiscoverer.getInstance().getContentDiscoverDataForCloseRequest(context);
            if (contentDiscoverDataForCloseRequest != null) {
                jSONObject.put(Defines.Jsonkey.ContentDiscovery.getKey(), contentDiscoverDataForCloseRequest);
            }
            if (DeviceInfo.getInstance() != null) {
                jSONObject.put(Defines.Jsonkey.AppVersion.getKey(), DeviceInfo.getInstance().getAppVersion());
            }
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestRegisterClose(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        this.prefHelper_.setSessionParams("bnc_no_value");
    }
}
