package com.wbmd.wbmddatacompliance.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.wbmd.wbmddatacompliance.gdpr.GDPRState;
import com.wbmd.wbmddatacompliance.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public class SharedPreferenceManager {
    private static final String TAG = SharedPreferenceManager.class.getSimpleName();

    public GDPRState getSavedGDRPState(Context context) {
        GDPRState reInitialize = GDPRState.reInitialize();
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences != null) {
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_GEO_LOCATED)) {
                    reInitialize.setGeoLocated(defaultSharedPreferences.getBoolean(Constants.SHARED_PREF_KEY_IS_GEO_LOCATED, false));
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_GEO_CODE_EU)) {
                    reInitialize.setGeoCodeEU(defaultSharedPreferences.getBoolean(Constants.SHARED_PREF_KEY_IS_GEO_CODE_EU, false));
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_ACCEPTED)) {
                    reInitialize.setIsAccepted(defaultSharedPreferences.getBoolean(Constants.SHARED_PREF_KEY_IS_ACCEPTED, false));
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_FORCE_OVERRIDE)) {
                    reInitialize.setForeceShowOverride(defaultSharedPreferences.getBoolean(Constants.SHARED_PREF_KEY_IS_FORCE_OVERRIDE, false));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return reInitialize;
    }

    public void saveGDRPState(GDPRState gDPRState, Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putBoolean(Constants.SHARED_PREF_KEY_IS_GEO_LOCATED, gDPRState.isGeoLocated());
            edit.putBoolean(Constants.SHARED_PREF_KEY_IS_GEO_CODE_EU, gDPRState.isGeoCodeEU());
            edit.putBoolean(Constants.SHARED_PREF_KEY_IS_ACCEPTED, gDPRState.isAccepted());
            edit.putBoolean(Constants.SHARED_PREF_KEY_IS_FORCE_OVERRIDE, gDPRState.isForceShowOverride());
            edit.apply();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void deleteGDRPState(Context context) {
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            if (defaultSharedPreferences != null) {
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_GEO_LOCATED)) {
                    edit.remove(Constants.SHARED_PREF_KEY_IS_GEO_LOCATED);
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_GEO_CODE_EU)) {
                    edit.remove(Constants.SHARED_PREF_KEY_IS_GEO_CODE_EU);
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_ACCEPTED)) {
                    edit.remove(Constants.SHARED_PREF_KEY_IS_ACCEPTED);
                }
                if (defaultSharedPreferences.contains(Constants.SHARED_PREF_KEY_IS_FORCE_OVERRIDE)) {
                    edit.remove(Constants.SHARED_PREF_KEY_IS_FORCE_OVERRIDE);
                }
                edit.apply();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void setSnoozeTimerValue(Context context, String str) {
        try {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putString(Constants.SHARED_PREF_KEY_SNOOZE_TIMER_VALUE, str);
            edit.apply();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public String getSnoozeTimerValue(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.SHARED_PREF_KEY_SNOOZE_TIMER_VALUE, UserProfile.OHCP_ID);
    }
}
