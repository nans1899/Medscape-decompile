package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;

public class BranchContentUrlBuilder extends BranchUrlBuilder<BranchContentUrlBuilder> {
    public BranchContentUrlBuilder(Context context, String str) {
        super(context);
        this.channel_ = str;
        this.type_ = 0;
        this.feature_ = "share";
    }

    public String getContentUrl() {
        return getUrl();
    }

    public void generateContentUrl(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        super.generateUrl(branchLinkCreateListener);
    }
}
