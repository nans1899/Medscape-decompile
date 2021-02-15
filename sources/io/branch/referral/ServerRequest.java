package io.branch.referral;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import io.branch.referral.Defines;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ServerRequest {
    private static final String POST_KEY = "REQ_POST";
    private static final String POST_PATH_KEY = "REQ_POST_PATH";
    public boolean constructError_ = false;
    private final Context context_;
    private boolean disableAndroidIDFetch_;
    final Set<PROCESS_WAIT_LOCK> locks_;
    private JSONObject params_;
    protected final PrefHelper prefHelper_;
    long queueWaitTime_ = 0;
    protected String requestPath_;
    boolean skipOnTimeOut = false;
    private final SystemObserver systemObserver_;
    private int waitLockCnt = 0;

    public enum BRANCH_API_VERSION {
        V1,
        V2
    }

    enum PROCESS_WAIT_LOCK {
        FB_APP_LINK_WAIT_LOCK,
        GAID_FETCH_WAIT_LOCK,
        INTENT_PENDING_WAIT_LOCK,
        STRONG_MATCH_PENDING_WAIT_LOCK,
        INSTALL_REFERRER_FETCH_WAIT_LOCK
    }

    public abstract void clearCallbacks();

    public abstract boolean handleErrors(Context context);

    public abstract void handleFailure(int i, String str);

    public boolean isGAdsParamsRequired() {
        return false;
    }

    public abstract boolean isGetRequest();

    /* access modifiers changed from: package-private */
    public boolean isPersistable() {
        return true;
    }

    public void onPreExecute() {
    }

    public abstract void onRequestSucceeded(ServerResponse serverResponse, Branch branch);

    /* access modifiers changed from: protected */
    public boolean prepareExecuteWithoutTracking() {
        return false;
    }

    public boolean shouldRetryOnFail() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUpdateLimitFacebookTracking() {
        return false;
    }

    public ServerRequest(Context context, String str) {
        this.context_ = context;
        this.requestPath_ = str;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.systemObserver_ = new SystemObserver(context);
        this.params_ = new JSONObject();
        this.disableAndroidIDFetch_ = Branch.isDeviceIDFetchDisabled();
        this.locks_ = new HashSet();
    }

    protected ServerRequest(String str, JSONObject jSONObject, Context context) {
        this.context_ = context;
        this.requestPath_ = str;
        this.params_ = jSONObject;
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.systemObserver_ = new SystemObserver(context);
        this.disableAndroidIDFetch_ = Branch.isDeviceIDFetchDisabled();
        this.locks_ = new HashSet();
    }

    public final String getRequestPath() {
        return this.requestPath_;
    }

    public String getRequestUrl() {
        return this.prefHelper_.getAPIBaseUrl() + this.requestPath_;
    }

    /* access modifiers changed from: protected */
    public void setPost(JSONObject jSONObject) throws JSONException {
        this.params_ = jSONObject;
        if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V2) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                this.params_.put(Defines.Jsonkey.UserData.getKey(), jSONObject2);
                DeviceInfo.getInstance(this.prefHelper_.getExternDebug(), this.systemObserver_, this.disableAndroidIDFetch_).updateRequestWithUserData(this.context_, this.prefHelper_, jSONObject2);
            } catch (JSONException unused) {
            }
        } else {
            DeviceInfo.getInstance(this.prefHelper_.getExternDebug(), this.systemObserver_, this.disableAndroidIDFetch_).updateRequestWithDeviceParams(this.params_);
        }
    }

    public JSONObject getPost() {
        return this.params_;
    }

    public JSONObject getPostWithInstrumentationValues(ConcurrentHashMap<String, String> concurrentHashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.params_ != null) {
                JSONObject jSONObject2 = new JSONObject(this.params_.toString());
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject.put(next, jSONObject2.get(next));
                }
            }
            if (concurrentHashMap.size() <= 0) {
                return jSONObject;
            }
            JSONObject jSONObject3 = new JSONObject();
            for (String next2 : concurrentHashMap.keySet()) {
                jSONObject3.put(next2, concurrentHashMap.get(next2));
                concurrentHashMap.remove(next2);
            }
            jSONObject.put(Defines.Jsonkey.Branch_Instrumentation.getKey(), jSONObject3);
            return jSONObject;
        } catch (JSONException unused) {
            return jSONObject;
        } catch (ConcurrentModificationException unused2) {
            return this.params_;
        }
    }

    public JSONObject getGetParams() {
        return this.params_;
    }

    /* access modifiers changed from: protected */
    public void addGetParam(String str, String str2) {
        try {
            this.params_.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(POST_KEY, this.params_);
            jSONObject.put(POST_PATH_KEY, this.requestPath_);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019 A[Catch:{ JSONException -> 0x001f }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static io.branch.referral.ServerRequest fromJSON(org.json.JSONObject r5, android.content.Context r6) {
        /*
            java.lang.String r0 = "REQ_POST_PATH"
            java.lang.String r1 = "REQ_POST"
            java.lang.String r2 = ""
            r3 = 0
            boolean r4 = r5.has(r1)     // Catch:{ JSONException -> 0x0012 }
            if (r4 == 0) goto L_0x0012
            org.json.JSONObject r1 = r5.getJSONObject(r1)     // Catch:{ JSONException -> 0x0012 }
            goto L_0x0013
        L_0x0012:
            r1 = r3
        L_0x0013:
            boolean r4 = r5.has(r0)     // Catch:{ JSONException -> 0x001f }
            if (r4 == 0) goto L_0x0020
            java.lang.String r5 = r5.getString(r0)     // Catch:{ JSONException -> 0x001f }
            r2 = r5
            goto L_0x0020
        L_0x001f:
        L_0x0020:
            if (r2 == 0) goto L_0x002d
            int r5 = r2.length()
            if (r5 <= 0) goto L_0x002d
            io.branch.referral.ServerRequest r5 = getExtendedServerRequest(r2, r1, r6)
            return r5
        L_0x002d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequest.fromJSON(org.json.JSONObject, android.content.Context):io.branch.referral.ServerRequest");
    }

    private static ServerRequest getExtendedServerRequest(String str, JSONObject jSONObject, Context context) {
        if (str.equalsIgnoreCase(Defines.RequestPath.CompletedAction.getPath())) {
            return new ServerRequestActionCompleted(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.GetURL.getPath())) {
            return new ServerRequestCreateUrl(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.GetCreditHistory.getPath())) {
            return new ServerRequestGetRewardHistory(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.GetCredits.getPath())) {
            return new ServerRequestGetRewards(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.IdentifyUser.getPath())) {
            return new ServerRequestIdentifyUserRequest(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.Logout.getPath())) {
            return new ServerRequestLogout(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.RedeemRewards.getPath())) {
            return new ServerRequestRedeemRewards(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.RegisterClose.getPath())) {
            return new ServerRequestRegisterClose(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.RegisterInstall.getPath())) {
            return new ServerRequestRegisterInstall(str, jSONObject, context);
        }
        if (str.equalsIgnoreCase(Defines.RequestPath.RegisterOpen.getPath())) {
            return new ServerRequestRegisterOpen(str, jSONObject, context);
        }
        return null;
    }

    private void updateGAdsParams() {
        JSONObject optJSONObject;
        BRANCH_API_VERSION branchRemoteAPIVersion = getBranchRemoteAPIVersion();
        if (!TextUtils.isEmpty(SystemObserver.GAIDString_)) {
            try {
                if (branchRemoteAPIVersion == BRANCH_API_VERSION.V2) {
                    JSONObject optJSONObject2 = this.params_.optJSONObject(Defines.Jsonkey.UserData.getKey());
                    if (optJSONObject2 != null) {
                        optJSONObject2.put(Defines.Jsonkey.AAID.getKey(), SystemObserver.GAIDString_);
                        optJSONObject2.put(Defines.Jsonkey.LimitedAdTracking.getKey(), this.systemObserver_.LATVal_);
                        optJSONObject2.remove(Defines.Jsonkey.UnidentifiedDevice.getKey());
                        return;
                    }
                    return;
                }
                this.params_.put(Defines.Jsonkey.GoogleAdvertisingID.getKey(), SystemObserver.GAIDString_);
                this.params_.put(Defines.Jsonkey.LATVal.getKey(), this.systemObserver_.LATVal_);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (branchRemoteAPIVersion == BRANCH_API_VERSION.V2) {
            try {
                if (branchRemoteAPIVersion == BRANCH_API_VERSION.V2 && (optJSONObject = this.params_.optJSONObject(Defines.Jsonkey.UserData.getKey())) != null && !optJSONObject.has(Defines.Jsonkey.AndroidID.getKey())) {
                    optJSONObject.put(Defines.Jsonkey.UnidentifiedDevice.getKey(), true);
                }
            } catch (JSONException unused) {
            }
        }
    }

    private void updateDeviceInfo() {
        JSONObject optJSONObject;
        if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V2 && (optJSONObject = this.params_.optJSONObject(Defines.Jsonkey.UserData.getKey())) != null) {
            try {
                optJSONObject.put(Defines.Jsonkey.DeveloperIdentity.getKey(), this.prefHelper_.getIdentity());
                optJSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
            } catch (JSONException unused) {
            }
        }
    }

    private void updateRequestMetadata() {
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator<String> keys = this.prefHelper_.getRequestMetadata().keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, this.prefHelper_.getRequestMetadata().get(next));
            }
            JSONObject optJSONObject = this.params_.optJSONObject(Defines.Jsonkey.Metadata.getKey());
            if (optJSONObject != null) {
                Iterator<String> keys2 = optJSONObject.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    jSONObject.put(next2, optJSONObject.get(next2));
                }
            }
            if ((this instanceof ServerRequestRegisterInstall) && this.prefHelper_.getInstallMetadata().length() > 0) {
                this.params_.putOpt(Defines.Jsonkey.InstallMetadata.getKey(), this.prefHelper_.getInstallMetadata());
            }
            this.params_.put(Defines.Jsonkey.Metadata.getKey(), jSONObject);
        } catch (JSONException unused) {
            Log.e("BranchSDK", "Could not merge metadata, ignoring user metadata.");
        }
    }

    private void updateLimitFacebookTracking() {
        boolean isAppTrackingLimited;
        JSONObject optJSONObject = getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V1 ? this.params_ : this.params_.optJSONObject(Defines.Jsonkey.UserData.getKey());
        if (optJSONObject != null && (isAppTrackingLimited = this.prefHelper_.isAppTrackingLimited())) {
            try {
                optJSONObject.putOpt(Defines.Jsonkey.limitFacebookTracking.getKey(), Boolean.valueOf(isAppTrackingLimited));
            } catch (JSONException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void doFinalUpdateOnMainThread() {
        updateRequestMetadata();
        if (shouldUpdateLimitFacebookTracking()) {
            updateLimitFacebookTracking();
        }
    }

    /* access modifiers changed from: package-private */
    public void doFinalUpdateOnBackgroundThread() {
        if (this instanceof ServerRequestInitSession) {
            ((ServerRequestInitSession) this).updateLinkReferrerParams();
        }
        updateDeviceInfo();
        if (isGAdsParamsRequired() && !BranchUtil.isTestModeEnabled(this.context_)) {
            updateGAdsParams();
        }
    }

    /* access modifiers changed from: protected */
    public boolean doesAppHasInternetPermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
    }

    public void onRequestQueued() {
        this.queueWaitTime_ = System.currentTimeMillis();
    }

    public long getQueueWaitTime() {
        if (this.queueWaitTime_ > 0) {
            return System.currentTimeMillis() - this.queueWaitTime_;
        }
        return 0;
    }

    public void addProcessWaitLock(PROCESS_WAIT_LOCK process_wait_lock) {
        if (process_wait_lock != null) {
            this.locks_.add(process_wait_lock);
        }
    }

    public void removeProcessWaitLock(PROCESS_WAIT_LOCK process_wait_lock) {
        this.locks_.remove(process_wait_lock);
    }

    public boolean isWaitingOnProcessToFinish() {
        return this.locks_.size() > 0;
    }

    /* access modifiers changed from: protected */
    public void updateEnvironment(Context context, JSONObject jSONObject) {
        try {
            String key = (isPackageInstalled(context) ? Defines.Jsonkey.NativeApp : Defines.Jsonkey.InstantApp).getKey();
            if (getBranchRemoteAPIVersion() == BRANCH_API_VERSION.V2) {
                JSONObject optJSONObject = jSONObject.optJSONObject(Defines.Jsonkey.UserData.getKey());
                if (optJSONObject != null) {
                    optJSONObject.put(Defines.Jsonkey.Environment.getKey(), key);
                    return;
                }
                return;
            }
            jSONObject.put(Defines.Jsonkey.Environment.getKey(), key);
        } catch (Exception unused) {
        }
    }

    private static boolean isPackageInstalled(Context context) {
        List<ResolveInfo> queryIntentActivities;
        PackageManager packageManager = context.getPackageManager();
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null || (queryIntentActivities = packageManager.queryIntentActivities(launchIntentForPackage, 65536)) == null || queryIntentActivities.size() <= 0) {
            return false;
        }
        return true;
    }

    public BRANCH_API_VERSION getBranchRemoteAPIVersion() {
        return BRANCH_API_VERSION.V1;
    }

    public void reportTrackingDisabledError() {
        PrefHelper.Debug("BranchSDK", "Requested operation cannot be completed since tracking is disabled [" + this.requestPath_ + "]");
        handleFailure(BranchError.ERR_BRANCH_TRACKING_DISABLED, "");
    }
}
