package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.branch.referral.BranchViewHandler;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestActionCompleted extends ServerRequest {
    private final BranchViewHandler.IBranchViewEvents callback_;

    public void clearCallbacks() {
    }

    public void handleFailure(int i, String str) {
    }

    public boolean isGAdsParamsRequired() {
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return true;
    }

    public ServerRequestActionCompleted(Context context, String str, JSONObject jSONObject, BranchViewHandler.IBranchViewEvents iBranchViewEvents) {
        super(context, Defines.RequestPath.CompletedAction.getPath());
        this.callback_ = iBranchViewEvents;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject2.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject2.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject2.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            jSONObject2.put(Defines.Jsonkey.Event.getKey(), str);
            if (jSONObject != null) {
                jSONObject2.put(Defines.Jsonkey.Metadata.getKey(), jSONObject);
            }
            updateEnvironment(context, jSONObject2);
            setPost(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
        if (str != null && str.equalsIgnoreCase(FirebaseAnalytics.Event.PURCHASE)) {
            Log.e("BranchSDK", "Warning: You are sending a purchase event with our non-dedicated purchase function. Please see function sendCommerceEvent");
        }
    }

    public ServerRequestActionCompleted(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.callback_ = null;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        if (serverResponse.getObject() != null && serverResponse.getObject().has(Defines.Jsonkey.BranchViewData.getKey()) && Branch.getInstance().currentActivityReference_ != null && Branch.getInstance().currentActivityReference_.get() != null) {
            String str = "";
            try {
                JSONObject post = getPost();
                if (post != null && post.has(Defines.Jsonkey.Event.getKey())) {
                    str = post.getString(Defines.Jsonkey.Event.getKey());
                }
                if (Branch.getInstance().currentActivityReference_ != null) {
                    JSONObject jSONObject = serverResponse.getObject().getJSONObject(Defines.Jsonkey.BranchViewData.getKey());
                    BranchViewHandler.getInstance().showBranchView(jSONObject, str, (Activity) Branch.getInstance().currentActivityReference_.get(), this.callback_);
                }
            } catch (JSONException unused) {
                BranchViewHandler.IBranchViewEvents iBranchViewEvents = this.callback_;
                if (iBranchViewEvents != null) {
                    iBranchViewEvents.onBranchViewError(BranchViewHandler.BRANCH_VIEW_ERR_INVALID_VIEW, "Unable to show branch view. Branch view received is invalid ", str);
                }
            }
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
        return true;
    }
}
