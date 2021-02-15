package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;

public class BranchReferralUrlBuilder extends BranchUrlBuilder<BranchReferralUrlBuilder> {
    public BranchReferralUrlBuilder(Context context, String str) {
        super(context);
        this.channel_ = str;
        this.type_ = 0;
        this.feature_ = Branch.FEATURE_TAG_REFERRAL;
    }

    public String getReferralUrl() {
        return super.getUrl();
    }

    public void generateReferralUrl(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        super.generateUrl(branchLinkCreateListener);
    }
}
