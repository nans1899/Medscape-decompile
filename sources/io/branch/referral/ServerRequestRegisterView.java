package io.branch.referral;

import android.content.Context;
import android.os.Build;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Defines;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class ServerRequestRegisterView extends ServerRequest {
    BranchUniversalObject.RegisterViewStatusListener callback_;

    public boolean isGAdsParamsRequired() {
        return true;
    }

    public boolean isGetRequest() {
        return false;
    }

    public ServerRequestRegisterView(Context context, BranchUniversalObject branchUniversalObject, SystemObserver systemObserver, BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener) {
        super(context, Defines.RequestPath.RegisterView.getPath());
        this.callback_ = registerViewStatusListener;
        try {
            setPost(createContentViewJson(branchUniversalObject, systemObserver));
        } catch (JSONException e) {
            e.printStackTrace();
            this.constructError_ = true;
        }
    }

    public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener = this.callback_;
        if (registerViewStatusListener != null) {
            registerViewStatusListener.onRegisterViewFinished(true, (BranchError) null);
        }
    }

    public void handleFailure(int i, String str) {
        BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener = this.callback_;
        if (registerViewStatusListener != null) {
            registerViewStatusListener.onRegisterViewFinished(false, new BranchError("Unable to register content view. " + str, i));
        }
    }

    public boolean handleErrors(Context context) {
        if (super.doesAppHasInternetPermission(context)) {
            return false;
        }
        BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener = this.callback_;
        if (registerViewStatusListener == null) {
            return true;
        }
        registerViewStatusListener.onRegisterViewFinished(false, new BranchError("Unable to register content view", BranchError.ERR_NO_INTERNET_PERMISSION));
        return true;
    }

    public void clearCallbacks() {
        this.callback_ = null;
    }

    private JSONObject createContentViewJson(BranchUniversalObject branchUniversalObject, SystemObserver systemObserver) throws JSONException {
        String str;
        JSONObject jSONObject = new JSONObject();
        int i = Build.VERSION.SDK_INT;
        jSONObject.put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
        jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
        if (DeviceInfo.getInstance() != null) {
            str = DeviceInfo.getInstance().getHardwareID();
        } else {
            str = systemObserver.getUniqueID(this.prefHelper_.getExternDebug());
        }
        if (!str.equals("bnc_no_value") && systemObserver.hasRealHardwareId()) {
            jSONObject.put(Defines.Jsonkey.HardwareID.getKey(), str);
        }
        String appVersion = systemObserver.getAppVersion();
        if (!appVersion.equals("bnc_no_value")) {
            jSONObject.put(Defines.Jsonkey.AppVersion.getKey(), appVersion);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(Defines.Jsonkey.ContentKeyWords.getKey(), branchUniversalObject.getKeywordsJsonArray());
        jSONObject2.put(Defines.Jsonkey.PublicallyIndexable.getKey(), branchUniversalObject.isPublicallyIndexable());
        if (branchUniversalObject.getPrice() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            jSONObject2.put(Defines.Jsonkey.Price.getKey(), branchUniversalObject.getPrice());
        }
        String canonicalIdentifier = branchUniversalObject.getCanonicalIdentifier();
        if (canonicalIdentifier != null && canonicalIdentifier.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.CanonicalIdentifier.getKey(), canonicalIdentifier);
        }
        String canonicalUrl = branchUniversalObject.getCanonicalUrl();
        if (canonicalUrl != null && canonicalUrl.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.CanonicalUrl.getKey(), canonicalUrl);
        }
        String title = branchUniversalObject.getTitle();
        if (title != null && title.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.ContentTitle.getKey(), branchUniversalObject.getTitle());
        }
        String description = branchUniversalObject.getDescription();
        if (description != null && description.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.ContentDesc.getKey(), description);
        }
        String imageUrl = branchUniversalObject.getImageUrl();
        if (imageUrl != null && imageUrl.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.ContentImgUrl.getKey(), imageUrl);
        }
        String type = branchUniversalObject.getType();
        if (type != null && type.trim().length() > 0) {
            jSONObject2.put(Defines.Jsonkey.ContentType.getKey(), type);
        }
        if (branchUniversalObject.getExpirationTime() > 0) {
            jSONObject2.put(Defines.Jsonkey.ContentExpiryTime.getKey(), branchUniversalObject.getExpirationTime());
        }
        jSONObject.put(Defines.Jsonkey.Params.getKey(), jSONObject2);
        HashMap<String, String> metadata = branchUniversalObject.getMetadata();
        Set<String> keySet = metadata.keySet();
        JSONObject jSONObject3 = new JSONObject();
        for (String next : keySet) {
            jSONObject3.put(next, metadata.get(next));
        }
        jSONObject.put(Defines.Jsonkey.Metadata.getKey(), jSONObject3);
        return jSONObject;
    }
}
