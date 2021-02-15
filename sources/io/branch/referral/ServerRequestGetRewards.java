package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestGetRewards extends ServerRequest {
    Branch.BranchReferralStateChangedListener callback_;

    public boolean isGetRequest() {
        return true;
    }

    public ServerRequestGetRewards(Context context, Branch.BranchReferralStateChangedListener branchReferralStateChangedListener) {
        super(context, Defines.RequestPath.GetCredits.getPath());
        this.callback_ = branchReferralStateChangedListener;
    }

    public ServerRequestGetRewards(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public String getRequestUrl() {
        return super.getRequestUrl() + this.prefHelper_.getIdentityID();
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        Iterator<String> keys = serverResponse.getObject().keys();
        boolean z = false;
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                int i = serverResponse.getObject().getInt(next);
                if (i != this.prefHelper_.getCreditCount(next)) {
                    z = true;
                }
                this.prefHelper_.setCreditCount(next, i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(z, (BranchError) null);
        }
    }

    public void handleFailure(int i, String str) {
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble retrieving user credits. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener == null) {
            return true;
        }
        branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble retrieving user credits.", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
