package io.branch.referral;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterInstall extends ServerRequestInitSession {
    Branch.BranchReferralInitListener callback_;

    public String getRequestActionName() {
        return "install";
    }

    public boolean isGetRequest() {
        return false;
    }

    ServerRequestRegisterInstall(Context context, Branch.BranchReferralInitListener branchReferralInitListener, SystemObserver systemObserver, String str) {
        super(context, Defines.RequestPath.RegisterInstall.getPath(), systemObserver);
        this.callback_ = branchReferralInitListener;
        JSONObject jSONObject = new JSONObject();
        try {
            if (!str.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), str);
            }
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestRegisterInstall(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void onPreExecute() {
        super.onPreExecute();
        long j = this.prefHelper_.getLong("bnc_referrer_click_ts");
        long j2 = this.prefHelper_.getLong("bnc_install_begin_ts");
        if (j > 0) {
            try {
                getPost().put(Defines.Jsonkey.ClickedReferrerTimeStamp.getKey(), j);
            } catch (JSONException unused) {
                return;
            }
        }
        if (j2 > 0) {
            getPost().put(Defines.Jsonkey.InstallBeginTimeStamp.getKey(), j2);
        }
    }

    public boolean hasCallBack() {
        return this.callback_ != null;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        super.onRequestSucceeded(serverResponse, branch);
        try {
            this.prefHelper_.setUserURL(serverResponse.getObject().getString(Defines.Jsonkey.Link.getKey()));
            if (serverResponse.getObject().has(Defines.Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
                if (jSONObject.has(Defines.Jsonkey.Clicked_Branch_Link.getKey()) && jSONObject.getBoolean(Defines.Jsonkey.Clicked_Branch_Link.getKey()) && this.prefHelper_.getInstallParams().equals("bnc_no_value") && this.prefHelper_.getIsReferrable() == 1) {
                    this.prefHelper_.setInstallParams(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
                }
            }
            if (serverResponse.getObject().has(Defines.Jsonkey.LinkClickID.getKey())) {
                this.prefHelper_.setLinkClickID(serverResponse.getObject().getString(Defines.Jsonkey.LinkClickID.getKey()));
            } else {
                this.prefHelper_.setLinkClickID("bnc_no_value");
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
}
