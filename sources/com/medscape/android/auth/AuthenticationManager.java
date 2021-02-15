package com.medscape.android.auth;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.Constants;
import com.medscape.android.activity.login.LoginManager;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.wbmdcommons.logging.Trace;
import org.json.JSONObject;

public class AuthenticationManager {
    static String TAG = AuthenticationManager.class.getSimpleName();
    private static AuthenticationManager mManager;
    private int mAuthStatus;
    private String mAuthenticationToken;
    private Context mContext;
    private boolean mIsProcessing = false;
    private String mMaskedGuid;
    private String mRefreshToken;

    public static AuthenticationManager getInstance(Context context) {
        if (mManager == null) {
            AuthenticationManager authenticationManager = new AuthenticationManager();
            mManager = authenticationManager;
            authenticationManager.setIsProcessing(false);
            mManager.setAuthStatus(3008);
        }
        mManager.setContext(context);
        return mManager;
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    private void setIsProcessing(boolean z) {
        this.mIsProcessing = z;
    }

    public void setAuthStatus(int i) {
        this.mAuthStatus = i;
    }

    public int getAuthStatus() {
        return this.mAuthStatus;
    }

    public void setAuthenticationToken(String str) {
        this.mAuthenticationToken = str;
        Util.saveStringToSharedPreference(this.mContext, str, Constants.PROFILE_AUTHENTICATION_TOKEN);
    }

    public String getAuthenticationToken() {
        if (StringUtil.isNullOrEmpty(this.mAuthenticationToken)) {
            this.mAuthenticationToken = Util.getStringFromSharedPreference(this.mContext, Constants.PROFILE_AUTHENTICATION_TOKEN);
        }
        return this.mAuthenticationToken;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setMaskedGuid(String str) {
        this.mMaskedGuid = str;
    }

    public String getMaskedGuid() {
        if (StringUtil.isNullOrEmpty(this.mMaskedGuid)) {
            this.mMaskedGuid = LoginManager.getStoredGUID(this.mContext);
        }
        return this.mMaskedGuid;
    }

    public boolean refreshAuthToken(Context context) {
        Trace.i(TAG, "Refreshing auth token");
        if (!StringUtil.isNotEmpty(this.mRefreshToken)) {
            return false;
        }
        try {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setRequestMethod("POST");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.OAUTH_REFRESH_TOKEN, this.mRefreshToken);
            httpRequestObject.setRequestBody(jSONObject.toString());
            httpRequestObject.setUrl(ConsultUrls.getRefreshAuthUrl(context));
            httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
            HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
            if (sendHttpRequest == null) {
                return false;
            }
            String responseData = sendHttpRequest.getResponseData();
            if (!StringUtil.isNotEmpty(responseData)) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject(responseData);
            String optString = jSONObject2.optString(Constants.OAUTH_ACCESS_TOKEN);
            String optString2 = jSONObject2.optString(Constants.OAUTH_REFRESH_TOKEN);
            if (!StringUtil.isNotEmpty(optString) || !StringUtil.isNotEmpty(optString2)) {
                return false;
            }
            setAuthenticationToken(optString);
            setRefreshToken(optString2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void resetAuthenticationStatus() {
        setAuthStatus(3008);
        this.mAuthenticationToken = null;
        this.mRefreshToken = null;
    }
}
