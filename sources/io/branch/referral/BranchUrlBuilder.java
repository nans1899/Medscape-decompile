package io.branch.referral;

import android.content.Context;
import android.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.BranchUrlBuilder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

abstract class BranchUrlBuilder<T extends BranchUrlBuilder> {
    protected String alias_;
    protected Branch branchReferral_ = Branch.getInstance();
    protected String campaign_;
    protected String channel_;
    private final Context context_;
    private boolean defaultToLongUrl_;
    protected int duration_ = 0;
    protected String feature_;
    protected JSONObject params_;
    protected String stage_;
    protected ArrayList<String> tags_;
    protected int type_ = 0;

    protected BranchUrlBuilder(Context context) {
        this.context_ = context.getApplicationContext();
        this.defaultToLongUrl_ = true;
    }

    public T addTag(String str) {
        if (this.tags_ == null) {
            this.tags_ = new ArrayList<>();
        }
        this.tags_.add(str);
        return this;
    }

    public T addTags(List<String> list) {
        if (this.tags_ == null) {
            this.tags_ = new ArrayList<>();
        }
        this.tags_.addAll(list);
        return this;
    }

    public T addParameters(String str, Object obj) {
        try {
            if (this.params_ == null) {
                this.params_ = new JSONObject();
            }
            this.params_.put(str, obj);
        } catch (JSONException unused) {
        }
        return this;
    }

    public T setDefaultToLongUrl(boolean z) {
        this.defaultToLongUrl_ = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        if (this.branchReferral_ == null) {
            return null;
        }
        return this.branchReferral_.generateShortLinkInternal(new ServerRequestCreateUrl(this.context_, this.alias_, this.type_, this.duration_, this.tags_, this.channel_, this.feature_, this.stage_, this.campaign_, BranchUtil.formatLinkParam(this.params_), (Branch.BranchLinkCreateListener) null, false, this.defaultToLongUrl_));
    }

    /* access modifiers changed from: protected */
    public void generateUrl(Branch.BranchLinkCreateListener branchLinkCreateListener) {
        generateUrlInternal(branchLinkCreateListener, false);
    }

    /* access modifiers changed from: protected */
    public void generateUrlInternal(Branch.BranchLinkCreateListener branchLinkCreateListener, boolean z) {
        Branch.BranchLinkCreateListener branchLinkCreateListener2 = branchLinkCreateListener;
        if (this.branchReferral_ != null) {
            ServerRequestCreateUrl serverRequestCreateUrl = new ServerRequestCreateUrl(this.context_, this.alias_, this.type_, this.duration_, this.tags_, this.channel_, this.feature_, this.stage_, this.campaign_, BranchUtil.formatLinkParam(this.params_), branchLinkCreateListener, true, this.defaultToLongUrl_);
            serverRequestCreateUrl.setIsReqStartedFromBranchShareSheet(z);
            this.branchReferral_.generateShortLinkInternal(serverRequestCreateUrl);
            return;
        }
        if (branchLinkCreateListener2 != null) {
            branchLinkCreateListener2.onLinkCreate((String) null, new BranchError("session has not been initialized", BranchError.ERR_NO_SESSION));
        }
        Log.i("BranchSDK", "Branch Warning: User session has not been initialized");
    }
}
