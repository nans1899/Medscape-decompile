package io.branch.referral;

import android.content.Context;
import android.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRedeemRewards extends ServerRequest {
    int actualNumOfCreditsToRedeem_ = 0;
    Branch.BranchReferralStateChangedListener callback_;

    public boolean isGetRequest() {
        return false;
    }

    public ServerRequestRedeemRewards(Context context, String str, int i, Branch.BranchReferralStateChangedListener branchReferralStateChangedListener) {
        super(context, Defines.RequestPath.RedeemRewards.getPath());
        this.callback_ = branchReferralStateChangedListener;
        int creditCount = this.prefHelper_.getCreditCount(str);
        this.actualNumOfCreditsToRedeem_ = i;
        if (i > creditCount) {
            this.actualNumOfCreditsToRedeem_ = creditCount;
            Log.i("BranchSDK", "Branch Warning: You're trying to redeem more credits than are available. Have you updated loaded rewards");
        }
        if (this.actualNumOfCreditsToRedeem_ > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
                jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
                jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
                if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                    jSONObject.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
                }
                jSONObject.put(Defines.Jsonkey.Bucket.getKey(), str);
                jSONObject.put(Defines.Jsonkey.Amount.getKey(), this.actualNumOfCreditsToRedeem_);
                setPost(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
                this.constructError_ = true;
            }
        }
    }

    public ServerRequestRedeemRewards(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
            if (branchReferralStateChangedListener != null) {
                branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble redeeming rewards.", BranchError.ERR_NO_INTERNET_PERMISSION));
            }
            return true;
        } else if (this.actualNumOfCreditsToRedeem_ > 0) {
            return false;
        } else {
            Branch.BranchReferralStateChangedListener branchReferralStateChangedListener2 = this.callback_;
            if (branchReferralStateChangedListener2 != null) {
                branchReferralStateChangedListener2.onStateChanged(false, new BranchError("Trouble redeeming rewards.", BranchError.ERR_BRANCH_REDEEM_REWARD));
            }
            return true;
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        BranchError branchError;
        JSONObject post = getPost();
        boolean z = false;
        if (post != null && post.has(Defines.Jsonkey.Bucket.getKey()) && post.has(Defines.Jsonkey.Amount.getKey())) {
            try {
                int i = post.getInt(Defines.Jsonkey.Amount.getKey());
                String string = post.getString(Defines.Jsonkey.Bucket.getKey());
                if (i > 0) {
                    z = true;
                }
                this.prefHelper_.setCreditCount(string, this.prefHelper_.getCreditCount(string) - i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.callback_ != null) {
            if (z) {
                branchError = null;
            } else {
                branchError = new BranchError("Trouble redeeming rewards.", BranchError.ERR_BRANCH_REDEEM_REWARD);
            }
            this.callback_.onStateChanged(z, branchError);
        }
    }

    public void handleFailure(int i, String str) {
        Branch.BranchReferralStateChangedListener branchReferralStateChangedListener = this.callback_;
        if (branchReferralStateChangedListener != null) {
            branchReferralStateChangedListener.onStateChanged(false, new BranchError("Trouble redeeming rewards. " + str, i));
        }
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }
}
