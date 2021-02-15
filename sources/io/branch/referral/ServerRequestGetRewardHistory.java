package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestGetRewardHistory extends ServerRequest {
    Branch.BranchListResponseListener callback_;

    public boolean isGetRequest() {
        return false;
    }

    public ServerRequestGetRewardHistory(Context context, String str, String str2, int i, Branch.CreditHistoryOrder creditHistoryOrder, Branch.BranchListResponseListener branchListResponseListener) {
        super(context, Defines.RequestPath.GetCreditHistory.getPath());
        this.callback_ = branchListResponseListener;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            jSONObject.put(Defines.Jsonkey.Length.getKey(), i);
            jSONObject.put(Defines.Jsonkey.Direction.getKey(), creditHistoryOrder.ordinal());
            if (str != null) {
                jSONObject.put(Defines.Jsonkey.Bucket.getKey(), str);
            }
            if (str2 != null) {
                jSONObject.put(Defines.Jsonkey.BeginAfterID.getKey(), str2);
            }
            setPost(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestGetRewardHistory(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener != null) {
            branchListResponseListener.onReceivingResponse(serverResponse.getArray(), (BranchError) null);
        }
    }

    public void handleFailure(int i, String str) {
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener != null) {
            branchListResponseListener.onReceivingResponse((JSONArray) null, new BranchError("Trouble retrieving user credit history. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchListResponseListener branchListResponseListener = this.callback_;
        if (branchListResponseListener == null) {
            return true;
        }
        branchListResponseListener.onReceivingResponse((JSONArray) null, new BranchError("Trouble retrieving user credit history.", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
