package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestIdentifyUserRequest extends ServerRequest {
    Branch.BranchReferralInitListener callback_;
    String userId_ = null;

    public boolean isGetRequest() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return true;
    }

    public ServerRequestIdentifyUserRequest(Context context, Branch.BranchReferralInitListener branchReferralInitListener, String str) {
        super(context, Defines.RequestPath.IdentifyUser.getPath());
        this.callback_ = branchReferralInitListener;
        this.userId_ = str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            jSONObject.put(Defines.Jsonkey.Identity.getKey(), str);
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestIdentifyUserRequest(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            if (getPost() != null && getPost().has(Defines.Jsonkey.Identity.getKey())) {
                this.prefHelper_.setIdentity(getPost().getString(Defines.Jsonkey.Identity.getKey()));
            }
            this.prefHelper_.setIdentityID(serverResponse.getObject().getString(Defines.Jsonkey.IdentityID.getKey()));
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines.Jsonkey.Link.getKey()));
            if (serverResponse.getObject().has(Defines.Jsonkey.ReferringData.getKey())) {
                this.prefHelper_.setInstallParams(serverResponse.getObject().getString(Defines.Jsonkey.ReferringData.getKey()));
            }
            if (this.callback_ != null) {
                this.callback_.onInitFinished(branch.getFirstReferringParams(), (BranchError) null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "Trouble reaching server. Please try again in a few minutes");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            branchReferralInitListener.onInitFinished(jSONObject, new BranchError("Trouble setting the user alias. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished((JSONObject) null, new BranchError("Trouble setting the user alias.", BranchError.ERR_NO_INTERNET_PERMISSION));
            }
            return true;
        }
        try {
            String string = getPost().getString(Defines.Jsonkey.Identity.getKey());
            if (string == null || string.length() == 0 || string.equals(this.prefHelper_.getIdentity())) {
                return true;
            }
            return false;
        } catch (JSONException unused) {
        }
    }

    public boolean isExistingID() {
        try {
            String string = getPost().getString(Defines.Jsonkey.Identity.getKey());
            if (string == null || !string.equals(this.prefHelper_.getIdentity())) {
                return false;
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void handleUserExist(Branch branch) {
        Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
        if (branchReferralInitListener != null) {
            branchReferralInitListener.onInitFinished(branch.getFirstReferringParams(), (BranchError) null);
        }
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
