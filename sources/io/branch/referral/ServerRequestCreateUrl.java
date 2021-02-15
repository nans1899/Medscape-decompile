package io.branch.referral;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import net.bytebuddy.description.type.TypeDescription;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestCreateUrl extends ServerRequest {
    private static final String DEF_BASE_URL = "https://bnc.lt/a/";
    private Branch.BranchLinkCreateListener callback_;
    private boolean defaultToLongUrl_ = true;
    private boolean isAsync_ = true;
    private boolean isReqStartedFromBranchShareSheet_;
    private BranchLinkData linkPost_;

    public boolean isGetRequest() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return false;
    }

    public ServerRequestCreateUrl(Context context, String str, int i, int i2, Collection<String> collection, String str2, String str3, String str4, String str5, JSONObject jSONObject, Branch.BranchLinkCreateListener branchLinkCreateListener, boolean z, boolean z2) {
        super(context, Defines.RequestPath.GetURL.getPath());
        this.callback_ = branchLinkCreateListener;
        this.isAsync_ = z;
        this.defaultToLongUrl_ = z2;
        BranchLinkData branchLinkData = new BranchLinkData();
        this.linkPost_ = branchLinkData;
        try {
            branchLinkData.put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
            this.linkPost_.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            this.linkPost_.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
            if (!this.prefHelper_.getLinkClickID().equals("bnc_no_value")) {
                this.linkPost_.put(Defines.Jsonkey.LinkClickID.getKey(), this.prefHelper_.getLinkClickID());
            }
            this.linkPost_.putType(i);
            this.linkPost_.putDuration(i2);
            this.linkPost_.putTags(collection);
            this.linkPost_.putAlias(str);
            this.linkPost_.putChannel(str2);
            this.linkPost_.putFeature(str3);
            this.linkPost_.putStage(str4);
            this.linkPost_.putCampaign(str5);
            this.linkPost_.putParams(jSONObject);
            setPost(this.linkPost_);
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public ServerRequestCreateUrl(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
    }

    public BranchLinkData getLinkPost() {
        return this.linkPost_;
    }

    /* access modifiers changed from: package-private */
    public boolean isDefaultToLongUrl() {
        return this.defaultToLongUrl_;
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener == null) {
            return true;
        }
        branchLinkCreateListener.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            String string = serverResponse.getObject().getString("url");
            if (this.callback_ != null) {
                this.callback_.onLinkCreate(string, (BranchError) null);
            }
            updateShareEventToFabric(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUrlAvailable(String str) {
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener != null) {
            branchLinkCreateListener.onLinkCreate(str, (BranchError) null);
        }
        updateShareEventToFabric(str);
    }

    public void handleFailure(int i, String str) {
        if (this.callback_ != null) {
            String str2 = null;
            if (this.defaultToLongUrl_) {
                str2 = getLongUrl();
            }
            Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
            branchLinkCreateListener.onLinkCreate(str2, new BranchError("Trouble creating a URL. " + str, i));
        }
    }

    public String getLongUrl() {
        if (!this.prefHelper_.getUserURL().equals("bnc_no_value")) {
            return generateLongUrlWithParams(this.prefHelper_.getUserURL());
        }
        return generateLongUrlWithParams(DEF_BASE_URL + this.prefHelper_.getBranchKey());
    }

    public void handleDuplicateURLError() {
        Branch.BranchLinkCreateListener branchLinkCreateListener = this.callback_;
        if (branchLinkCreateListener != null) {
            branchLinkCreateListener.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", BranchError.ERR_BRANCH_DUPLICATE_URL));
        }
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    public boolean isAsync() {
        return this.isAsync_;
    }

    private String generateLongUrlWithParams(String str) {
        try {
            String str2 = "";
            if (Branch.getInstance().isTrackingDisabled()) {
                if (!str.contains(DEF_BASE_URL)) {
                    str = str.replace(new URL(str).getQuery(), str2);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str.contains(TypeDescription.Generic.OfWildcardType.SYMBOL) ? str2 : TypeDescription.Generic.OfWildcardType.SYMBOL);
            str = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (!str.endsWith(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
                str2 = "&";
            }
            sb2.append(str2);
            str = sb2.toString();
            Collection<String> tags = this.linkPost_.getTags();
            if (tags != null) {
                for (String next : tags) {
                    if (next != null && next.length() > 0) {
                        str = str + Defines.LinkParam.Tags + "=" + URLEncoder.encode(next, "UTF8") + "&";
                    }
                }
            }
            String alias = this.linkPost_.getAlias();
            if (alias != null && alias.length() > 0) {
                str = str + Defines.LinkParam.Alias + "=" + URLEncoder.encode(alias, "UTF8") + "&";
            }
            String channel = this.linkPost_.getChannel();
            if (channel != null && channel.length() > 0) {
                str = str + Defines.LinkParam.Channel + "=" + URLEncoder.encode(channel, "UTF8") + "&";
            }
            String feature = this.linkPost_.getFeature();
            if (feature != null && feature.length() > 0) {
                str = str + Defines.LinkParam.Feature + "=" + URLEncoder.encode(feature, "UTF8") + "&";
            }
            String stage = this.linkPost_.getStage();
            if (stage != null && stage.length() > 0) {
                str = str + Defines.LinkParam.Stage + "=" + URLEncoder.encode(stage, "UTF8") + "&";
            }
            String campaign = this.linkPost_.getCampaign();
            if (campaign != null && campaign.length() > 0) {
                str = str + Defines.LinkParam.Campaign + "=" + URLEncoder.encode(campaign, "UTF8") + "&";
            }
            String str3 = (str + Defines.LinkParam.Type + "=" + ((long) this.linkPost_.getType()) + "&") + Defines.LinkParam.Duration + "=" + ((long) this.linkPost_.getDuration());
            String jSONObject = this.linkPost_.getParams().toString();
            if (jSONObject == null || jSONObject.length() <= 0) {
                return str3;
            }
            return str3 + "&source=android&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.getBytes(), 2), "UTF8");
        } catch (Exception unused) {
            this.callback_.onLinkCreate((String) null, new BranchError("Trouble creating a URL.", BranchError.ERR_BRANCH_INVALID_REQUEST));
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    public void setIsReqStartedFromBranchShareSheet(boolean z) {
        this.isReqStartedFromBranchShareSheet_ = z;
    }

    /* access modifiers changed from: package-private */
    public boolean isReqStartedFromBranchShareSheet() {
        return this.isReqStartedFromBranchShareSheet_;
    }

    private void updateShareEventToFabric(String str) {
        JSONObject linkDataJsonObject = this.linkPost_.getLinkDataJsonObject();
        if (isReqStartedFromBranchShareSheet() && linkDataJsonObject != null) {
            new ExtendedAnswerProvider().provideData(ExtendedAnswerProvider.KIT_EVENT_SHARE, linkDataJsonObject, this.prefHelper_.getIdentityID());
        }
    }
}
