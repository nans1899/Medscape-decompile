package com.medscape.android.auth;

import com.medscape.android.Constants;
import com.medscape.android.parser.model.UserProfile;
import java.util.ArrayList;
import java.util.List;

public class OAuthResponse {
    private String mAccessToken;
    private List<String> mCookieNames = new ArrayList();
    private int mErrorCode = 200;
    private String mErrorString;
    private String mMaskedGuid;
    private int mPatientInstructionStatus = Constants.PATIENT_INSTRUCTION_STATE_UNKNOWN;
    private UserProfile mProfile;
    private String mProfileSegVarDfp;
    private String mRefreshToken;
    private int mStatus;
    private String mTcids;
    private String mToken;

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setRefreshToken(String str) {
        this.mRefreshToken = str;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.mProfile = userProfile;
    }

    public UserProfile getUserProfile() {
        return this.mProfile;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setCookieNames(List<String> list) {
        this.mCookieNames = list;
    }

    public List<String> getCookieNames() {
        return this.mCookieNames;
    }

    public void setPatientInstructionStatus(int i) {
        this.mPatientInstructionStatus = i;
    }

    public int getPatientInstructionStatus() {
        return this.mPatientInstructionStatus;
    }

    public void setTcids(String str) {
        this.mTcids = str;
    }

    public String getTcids() {
        return this.mTcids;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public String getToken() {
        return this.mToken;
    }

    public void setMaskedGuid(String str) {
        this.mMaskedGuid = str;
    }

    public String getMaskedGuid() {
        return this.mMaskedGuid;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorString(String str) {
        this.mErrorString = str;
    }

    public String getErrorString() {
        return this.mErrorString;
    }
}
