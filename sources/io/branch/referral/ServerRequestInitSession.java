package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import io.branch.indexing.ContentDiscoverer;
import io.branch.indexing.ContentDiscoveryManifest;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import io.branch.referral.validators.DeepLinkRoutingValidator;
import org.json.JSONException;
import org.json.JSONObject;

abstract class ServerRequestInitSession extends ServerRequest {
    static final String ACTION_INSTALL = "install";
    static final String ACTION_OPEN = "open";
    private static final int STATE_FRESH_INSTALL = 0;
    private static final int STATE_NO_CHANGE = 1;
    private static final int STATE_UPDATE = 2;
    private final ContentDiscoveryManifest contentDiscoveryManifest_;
    private final Context context_;
    final SystemObserver systemObserver_;

    public abstract String getRequestActionName();

    public abstract boolean hasCallBack();

    public boolean isGAdsParamsRequired() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return true;
    }

    ServerRequestInitSession(Context context, String str, SystemObserver systemObserver) {
        super(context, str);
        this.context_ = context;
        this.systemObserver_ = systemObserver;
        this.contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(context);
    }

    ServerRequestInitSession(String str, JSONObject jSONObject, Context context) {
        super(str, jSONObject, context);
        this.context_ = context;
        this.systemObserver_ = new SystemObserver(context);
        this.contentDiscoveryManifest_ = ContentDiscoveryManifest.getInstance(this.context_);
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject jSONObject) throws JSONException {
        super.setPost(jSONObject);
        if (!this.systemObserver_.getAppVersion().equals("bnc_no_value")) {
            jSONObject.put(Defines.Jsonkey.AppVersion.getKey(), this.systemObserver_.getAppVersion());
        }
        jSONObject.put(Defines.Jsonkey.FaceBookAppLinkChecked.getKey(), this.prefHelper_.getIsAppLinkTriggeredInit());
        jSONObject.put(Defines.Jsonkey.IsReferrable.getKey(), this.prefHelper_.getIsReferrable());
        jSONObject.put(Defines.Jsonkey.Debug.getKey(), this.prefHelper_.getExternDebug());
        updateInstallStateAndTimestamps(jSONObject);
        updateEnvironment(this.context_, jSONObject);
    }

    static boolean isInitSessionAction(String str) {
        if (str != null) {
            return str.equalsIgnoreCase("open") || str.equalsIgnoreCase(ACTION_INSTALL);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean handleBranchViewIfAvailable(ServerResponse serverResponse) {
        if (!(serverResponse == null || serverResponse.getObject() == null || !serverResponse.getObject().has(Defines.Jsonkey.BranchViewData.getKey()))) {
            try {
                JSONObject jSONObject = serverResponse.getObject().getJSONObject(Defines.Jsonkey.BranchViewData.getKey());
                String requestActionName = getRequestActionName();
                if (Branch.getInstance().currentActivityReference_ == null || Branch.getInstance().currentActivityReference_.get() == null) {
                    return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(jSONObject, requestActionName);
                }
                Activity activity = (Activity) Branch.getInstance().currentActivityReference_.get();
                boolean z = true;
                if (activity instanceof Branch.IBranchViewControl) {
                    z = true ^ ((Branch.IBranchViewControl) activity).skipBranchViewsOnThisActivity();
                }
                if (z) {
                    return BranchViewHandler.getInstance().showBranchView(jSONObject, requestActionName, activity, Branch.getInstance());
                }
                return BranchViewHandler.getInstance().markInstallOrOpenBranchViewPending(jSONObject, requestActionName);
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        try {
            this.prefHelper_.setLinkClickIdentifier("bnc_no_value");
            this.prefHelper_.setGoogleSearchInstallIdentifier("bnc_no_value");
            this.prefHelper_.setGooglePlayReferrer("bnc_no_value");
            this.prefHelper_.setExternalIntentUri("bnc_no_value");
            this.prefHelper_.setExternalIntentExtra("bnc_no_value");
            this.prefHelper_.setAppLink("bnc_no_value");
            this.prefHelper_.setPushIdentifier("bnc_no_value");
            this.prefHelper_.setIsAppLinkTriggeredInit(false);
            this.prefHelper_.setInstallReferrerParams("bnc_no_value");
            this.prefHelper_.setIsFullAppConversion(false);
            if (serverResponse.getObject() != null && serverResponse.getObject().has(Defines.Jsonkey.Data.getKey())) {
                JSONObject jSONObject = new JSONObject(serverResponse.getObject().getString(Defines.Jsonkey.Data.getKey()));
                if (jSONObject.optBoolean(Defines.Jsonkey.Clicked_Branch_Link.getKey())) {
                    new ExtendedAnswerProvider().provideData(this instanceof ServerRequestRegisterInstall ? ExtendedAnswerProvider.KIT_EVENT_INSTALL : ExtendedAnswerProvider.KIT_EVENT_OPEN, jSONObject, this.prefHelper_.getIdentityID());
                }
            }
        } catch (JSONException unused) {
        }
        if (this.prefHelper_.getLong("bnc_previous_update_time") == 0) {
            this.prefHelper_.setLong("bnc_previous_update_time", this.prefHelper_.getLong("bnc_last_known_update_time"));
        }
    }

    /* access modifiers changed from: package-private */
    public void onInitSessionCompleted(ServerResponse serverResponse, Branch branch) {
        ContentDiscoveryManifest contentDiscoveryManifest = this.contentDiscoveryManifest_;
        if (contentDiscoveryManifest != null) {
            contentDiscoveryManifest.onBranchInitialised(serverResponse.getObject());
            if (branch.currentActivityReference_ != null) {
                try {
                    ContentDiscoverer.getInstance().onSessionStarted((Activity) branch.currentActivityReference_.get(), branch.sessionReferredLink_);
                } catch (Exception unused) {
                }
            }
        }
        DeepLinkRoutingValidator.validate(branch.currentActivityReference_);
        branch.updateSkipURLFormats();
    }

    /* access modifiers changed from: package-private */
    public void updateLinkReferrerParams() {
        String linkClickIdentifier = this.prefHelper_.getLinkClickIdentifier();
        if (!linkClickIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines.Jsonkey.LinkIdentifier.getKey(), linkClickIdentifier);
                getPost().put(Defines.Jsonkey.FaceBookAppLinkChecked.getKey(), this.prefHelper_.getIsAppLinkTriggeredInit());
            } catch (JSONException unused) {
            }
        }
        String googleSearchInstallIdentifier = this.prefHelper_.getGoogleSearchInstallIdentifier();
        if (!googleSearchInstallIdentifier.equals("bnc_no_value")) {
            try {
                getPost().put(Defines.Jsonkey.GoogleSearchInstallReferrer.getKey(), googleSearchInstallIdentifier);
            } catch (JSONException unused2) {
            }
        }
        String googlePlayReferrer = this.prefHelper_.getGooglePlayReferrer();
        if (!googlePlayReferrer.equals("bnc_no_value")) {
            try {
                getPost().put(Defines.Jsonkey.GooglePlayInstallReferrer.getKey(), googlePlayReferrer);
            } catch (JSONException unused3) {
            }
        }
        if (this.prefHelper_.isFullAppConversion()) {
            try {
                getPost().put(Defines.Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
                getPost().put(Defines.Jsonkey.IsFullAppConv.getKey(), true);
            } catch (JSONException unused4) {
            }
        }
    }

    public void onPreExecute() {
        JSONObject post = getPost();
        try {
            if (!this.prefHelper_.getAppLink().equals("bnc_no_value")) {
                post.put(Defines.Jsonkey.AndroidAppLinkURL.getKey(), this.prefHelper_.getAppLink());
            }
            if (!this.prefHelper_.getPushIdentifier().equals("bnc_no_value")) {
                post.put(Defines.Jsonkey.AndroidPushIdentifier.getKey(), this.prefHelper_.getPushIdentifier());
            }
            if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value")) {
                post.put(Defines.Jsonkey.External_Intent_URI.getKey(), this.prefHelper_.getExternalIntentUri());
            }
            if (!this.prefHelper_.getExternalIntentExtra().equals("bnc_no_value")) {
                post.put(Defines.Jsonkey.External_Intent_Extra.getKey(), this.prefHelper_.getExternalIntentExtra());
            }
            if (this.contentDiscoveryManifest_ != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ContentDiscoveryManifest.MANIFEST_VERSION_KEY, this.contentDiscoveryManifest_.getManifestVersion());
                jSONObject.put(ContentDiscoveryManifest.PACKAGE_NAME_KEY, this.context_.getPackageName());
                post.put(ContentDiscoveryManifest.CONTENT_DISCOVER_KEY, jSONObject);
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        if ((r2.lastUpdateTime - r2.firstInstallTime) >= com.medscape.android.Constants.DAY_IN_MILLIS) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateInstallStateAndTimestamps(org.json.JSONObject r10) throws org.json.JSONException {
        /*
            r9 = this;
            io.branch.referral.SystemObserver r0 = r9.systemObserver_
            java.lang.String r0 = r0.getAppVersion()
            r1 = 0
            android.content.Context r2 = r9.context_     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.Context r3 = r9.context_     // Catch:{ NameNotFoundException -> 0x0018 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x0018 }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch:{ NameNotFoundException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r2 = 0
        L_0x0019:
            io.branch.referral.PrefHelper r3 = r9.prefHelper_
            java.lang.String r3 = r3.getAppVersion()
            java.lang.String r4 = "bnc_no_value"
            boolean r3 = r4.equals(r3)
            r4 = 2
            if (r3 == 0) goto L_0x0037
            if (r2 == 0) goto L_0x0046
            long r5 = r2.lastUpdateTime
            long r7 = r2.firstInstallTime
            long r5 = r5 - r7
            r7 = 86400000(0x5265c00, double:4.2687272E-316)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x0046
            goto L_0x0043
        L_0x0037:
            io.branch.referral.PrefHelper r1 = r9.prefHelper_
            java.lang.String r1 = r1.getAppVersion()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0045
        L_0x0043:
            r1 = 2
            goto L_0x0046
        L_0x0045:
            r1 = 1
        L_0x0046:
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.Update
            java.lang.String r0 = r0.getKey()
            r10.put(r0, r1)
            if (r2 == 0) goto L_0x00b2
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.FirstInstallTime
            java.lang.String r0 = r0.getKey()
            long r3 = r2.firstInstallTime
            r10.put(r0, r3)
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.LastUpdateTime
            java.lang.String r0 = r0.getKey()
            long r3 = r2.lastUpdateTime
            r10.put(r0, r3)
            io.branch.referral.PrefHelper r0 = r9.prefHelper_
            java.lang.String r1 = "bnc_original_install_time"
            long r3 = r0.getLong(r1)
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x007e
            long r3 = r2.firstInstallTime
            io.branch.referral.PrefHelper r0 = r9.prefHelper_
            long r5 = r2.firstInstallTime
            r0.setLong(r1, r5)
        L_0x007e:
            io.branch.referral.Defines$Jsonkey r0 = io.branch.referral.Defines.Jsonkey.OriginalInstallTime
            java.lang.String r0 = r0.getKey()
            r10.put(r0, r3)
            io.branch.referral.PrefHelper r0 = r9.prefHelper_
            java.lang.String r1 = "bnc_last_known_update_time"
            long r3 = r0.getLong(r1)
            long r5 = r2.lastUpdateTime
            java.lang.String r0 = "bnc_previous_update_time"
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x00a3
            io.branch.referral.PrefHelper r5 = r9.prefHelper_
            r5.setLong(r0, r3)
            io.branch.referral.PrefHelper r3 = r9.prefHelper_
            long r4 = r2.lastUpdateTime
            r3.setLong(r1, r4)
        L_0x00a3:
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.PreviousUpdateTime
            java.lang.String r1 = r1.getKey()
            io.branch.referral.PrefHelper r2 = r9.prefHelper_
            long r2 = r2.getLong(r0)
            r10.put(r1, r2)
        L_0x00b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestInitSession.updateInstallStateAndTimestamps(org.json.JSONObject):void");
    }

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        JSONObject post = getPost();
        if (!post.has(Defines.Jsonkey.AndroidAppLinkURL.getKey()) && !post.has(Defines.Jsonkey.AndroidPushIdentifier.getKey()) && !post.has(Defines.Jsonkey.LinkIdentifier.getKey())) {
            return super.prepareExecuteWithoutTracking();
        }
        post.remove(Defines.Jsonkey.DeviceFingerprintID.getKey());
        post.remove(Defines.Jsonkey.IdentityID.getKey());
        post.remove(Defines.Jsonkey.FaceBookAppLinkChecked.getKey());
        post.remove(Defines.Jsonkey.External_Intent_Extra.getKey());
        post.remove(Defines.Jsonkey.External_Intent_URI.getKey());
        post.remove(Defines.Jsonkey.FirstInstallTime.getKey());
        post.remove(Defines.Jsonkey.LastUpdateTime.getKey());
        post.remove(Defines.Jsonkey.OriginalInstallTime.getKey());
        post.remove(Defines.Jsonkey.PreviousUpdateTime.getKey());
        post.remove(Defines.Jsonkey.InstallBeginTimeStamp.getKey());
        post.remove(Defines.Jsonkey.ClickedReferrerTimeStamp.getKey());
        post.remove(Defines.Jsonkey.HardwareID.getKey());
        post.remove(Defines.Jsonkey.IsHardwareIDReal.getKey());
        post.remove(Defines.Jsonkey.LocalIP.getKey());
        try {
            post.put(Defines.Jsonkey.TrackingDisabled.getKey(), true);
        } catch (JSONException unused) {
        }
        return true;
    }
}
