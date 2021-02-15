package com.wbmd.wbmddatacompliance.gdpr;

import android.content.Context;
import com.wbmd.wbmddatacompliance.sharepreferences.SharedPreferenceManager;

public class GDPRState {
    public static final String STATE_EU_EXEMPT = "Exempt - Non-EU";
    public static final String STATE_GDPR_ACCEPTED = "GDPR Accepted";
    public static final String STATE_GEO_LOCATION_ERROR = "Geo Lookup not made";
    public static final String STATE_NOT_DETERMINED = "Not Determined";
    public static final String STATE_SHOWING_ROADBLOCK = "Is EU, GDRP has not been accepted";
    private static GDPRState mGDPRState;
    private boolean mForceShowOverride;
    private boolean mIsAccepted;
    private boolean mIsGeoCodeEU;
    private boolean mIsGeoLocated;

    private GDPRState() {
    }

    public static GDPRState getInstance(Context context) {
        if (mGDPRState == null) {
            GDPRState savedGDRPState = new SharedPreferenceManager().getSavedGDRPState(context);
            mGDPRState = savedGDRPState;
            if (savedGDRPState == null) {
                mGDPRState = new GDPRState();
            }
        }
        return mGDPRState;
    }

    public static GDPRState reInitialize() {
        GDPRState gDPRState = new GDPRState();
        mGDPRState = gDPRState;
        return gDPRState;
    }

    public void clearState() {
        mGDPRState.setGeoCodeEU(false);
        mGDPRState.setGeoLocated(false);
        mGDPRState.setIsAccepted(false);
    }

    public boolean isAccepted() {
        return this.mIsAccepted;
    }

    public void setIsAccepted(boolean z) {
        mGDPRState.mIsAccepted = z;
    }

    public boolean isGeoLocated() {
        return mGDPRState.mIsGeoLocated;
    }

    public void setGeoLocated(boolean z) {
        mGDPRState.mIsGeoLocated = z;
    }

    public boolean isGeoCodeEU() {
        return mGDPRState.mIsGeoCodeEU;
    }

    public void setGeoCodeEU(boolean z) {
        mGDPRState.mIsGeoCodeEU = z;
    }

    public boolean isForceShowOverride() {
        return mGDPRState.mForceShowOverride;
    }

    public void setForeceShowOverride(boolean z) {
        mGDPRState.mForceShowOverride = z;
    }

    public String getStatus() {
        GDPRState gDPRState = mGDPRState;
        if (!gDPRState.mIsGeoLocated && !gDPRState.mIsGeoCodeEU && !gDPRState.mIsAccepted) {
            return STATE_GEO_LOCATION_ERROR;
        }
        GDPRState gDPRState2 = mGDPRState;
        if (gDPRState2.mIsGeoLocated && !gDPRState2.mIsGeoCodeEU) {
            return STATE_EU_EXEMPT;
        }
        GDPRState gDPRState3 = mGDPRState;
        boolean z = gDPRState3.mIsAccepted;
        if (z) {
            return STATE_GDPR_ACCEPTED;
        }
        return (z || !gDPRState3.mIsGeoLocated || !gDPRState3.mIsGeoCodeEU) ? STATE_NOT_DETERMINED : STATE_SHOWING_ROADBLOCK;
    }
}
