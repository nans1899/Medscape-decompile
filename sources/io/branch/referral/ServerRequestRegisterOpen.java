package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.Constants;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterOpen extends ServerRequestInitSession {
    Branch.BranchReferralInitListener callback_;

    public String getRequestActionName() {
        return Constants.OMNITURE_MLINK_OPEN;
    }

    public boolean isGetRequest() {
        return false;
    }

    ServerRequestRegisterOpen(Context context, Branch.BranchReferralInitListener branchReferralInitListener, SystemObserver systemObserver) {
        super(context, Defines.RequestPath.RegisterOpen.getPath(), systemObserver);
        this.callback_ = branchReferralInitListener;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    ServerRequestRegisterOpen(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void onPreExecute() {
        super.onPreExecute();
        if (Branch.getInstance().isInstantDeepLinkPossible) {
            this.callback_.onInitFinished(Branch.getInstance().getLatestReferringParams(), (BranchError) null);
            Branch.getInstance().addExtraInstrumentationData(Defines.Jsonkey.InstantDeepLinkSession.getKey(), ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            Branch.getInstance().isInstantDeepLinkPossible = false;
            Branch.getInstance().isInitReportedThroughCallBack = true;
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        super.onRequestSucceeded(serverResponse, branch);
        try {
            if (serverResponse.getObject().has(Defines.Jsonkey.LinkClickID.getKey())) {
                this.prefHelper_.setLinkClickID(serverResponse.getObject().getString(Defines.Jsonkey.LinkClickID.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
            }
            if (serverResponse.getObject().has(Defines.Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
                if (jSONObject.has(Defines.Jsonkey.Clicked_Branch_Link.getKey()) && jSONObject.getBoolean(Defines.Jsonkey.Clicked_Branch_Link.getKey()) && this.prefHelper_.getInstallParams().equals("bnc_no_value") && this.prefHelper_.getIsReferrable() == 1) {
                    this.prefHelper_.setInstallParams(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
                }
            }
            if (serverResponse.getObject().has(Defines.Jsonkey.Data.getKey())) {
                this.prefHelper_.setSessionParams(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
            } else {
                this.prefHelper_.setSessionParams("bnc_no_value");
            }
            if (this.callback_ != null && !branch.isInitReportedThroughCallBack) {
                this.callback_.onInitFinished(branch.getLatestReferringParams(), (BranchError) null);
            }
            this.prefHelper_.setAppVersion(this.systemObserver_.getAppVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onInitSessionCompleted(serverResponse, branch);
    }

    /* access modifiers changed from: package-private */
    public void setInitFinishedCallback(Branch.BranchReferralInitListener branchReferralInitListener) {
        if (branchReferralInitListener != null) {
            this.callback_ = branchReferralInitListener;
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
            branchReferralInitListener.onInitFinished(jSONObject, new BranchError("Trouble initializing Branch. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchReferralInitListener branchReferralInitListener = this.callback_;
        if (branchReferralInitListener == null) {
            return true;
        }
        branchReferralInitListener.onInitFinished((JSONObject) null, new BranchError("Trouble initializing Branch.", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public boolean hasCallBack() {
        return this.callback_ != null;
    }
}
