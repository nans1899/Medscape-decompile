package io.branch.referral;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.util.LinkProperties;
import org.json.JSONObject;

class BranchUniversalReferralInitWrapper implements Branch.BranchReferralInitListener {
    private final Branch.BranchUniversalReferralInitListener universalReferralInitListener_;

    public BranchUniversalReferralInitWrapper(Branch.BranchUniversalReferralInitListener branchUniversalReferralInitListener) {
        this.universalReferralInitListener_ = branchUniversalReferralInitListener;
    }

    public void onInitFinished(JSONObject jSONObject, BranchError branchError) {
        Branch.BranchUniversalReferralInitListener branchUniversalReferralInitListener = this.universalReferralInitListener_;
        if (branchUniversalReferralInitListener == null) {
            return;
        }
        if (branchError != null) {
            branchUniversalReferralInitListener.onInitFinished((BranchUniversalObject) null, (LinkProperties) null, branchError);
            return;
        }
        this.universalReferralInitListener_.onInitFinished(BranchUniversalObject.getReferredBranchUniversalObject(), LinkProperties.getReferredLinkProperties(), branchError);
    }
}
