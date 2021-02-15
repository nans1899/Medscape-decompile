package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import org.json.JSONObject;

public class BranchShortLinkBuilder extends BranchUrlBuilder<BranchShortLinkBuilder> {
    public BranchShortLinkBuilder(Context context) {
        super(context);
    }

    public BranchShortLinkBuilder setAlias(String str) {
        this.alias_ = str;
        return this;
    }

    public BranchShortLinkBuilder setChannel(String str) {
        this.channel_ = str;
        return this;
    }

    public BranchShortLinkBuilder setDuration(int i) {
        this.duration_ = i;
        return this;
    }

    public BranchShortLinkBuilder setFeature(String str) {
        this.feature_ = str;
        return this;
    }

    public BranchShortLinkBuilder setParameters(JSONObject jSONObject) {
        this.params_ = jSONObject;
        return this;
    }

    public BranchShortLinkBuilder setStage(String str) {
        this.stage_ = str;
        return this;
    }

    public BranchShortLinkBuilder setCampaign(String str) {
        this.campaign_ = str;
        return this;
    }

    public BranchShortLinkBuilder setType(int i) {
        this.type_ = i;
        return this;
    }

    public String getShortUrl() {
        return super.getUrl();
    }

    public void generateShortUrl(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        super.generateUrl(branchLinkCreateListener);
    }

    /* access modifiers changed from: package-private */
    public void generateShortUrlInternal(Branch.BranchLinkCreateListener branchLinkCreateListener, boolean z) {
        super.generateUrlInternal(branchLinkCreateListener, z);
    }
}
