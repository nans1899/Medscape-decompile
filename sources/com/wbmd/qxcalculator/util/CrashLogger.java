package com.wbmd.qxcalculator.util;

import android.content.Context;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.util.legacy.LegacyUpdateHelper;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.HashMap;
import java.util.Map;

public class CrashLogger {
    private static CrashLogger ourInstance = new CrashLogger();
    private boolean hasInitialized;

    public void setEmailAddress(String str) {
        if (str == null) {
        }
    }

    public static CrashLogger getInstance() {
        return ourInstance;
    }

    private CrashLogger() {
    }

    public void initialize(Context context, DBUser dBUser) {
        if (!this.hasInitialized) {
            if (dBUser != null) {
                if (dBUser.getDebugGroups() == null || dBUser.getDebugGroups().isEmpty()) {
                    FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
                } else if (FirebaseCrashlytics.getInstance().didCrashOnPreviousExecution()) {
                    setUserData(dBUser);
                }
                setUserData(dBUser);
            } else {
                FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
            }
            this.hasInitialized = true;
        }
    }

    public boolean getHasInitialized() {
        return this.hasInitialized;
    }

    public void setUserData(DBUser dBUser) {
        if (this.hasInitialized) {
            HashMap hashMap = new HashMap();
            if (!(dBUser.getProfession() == null || dBUser.getProfession().getName() == null)) {
                hashMap.put("profession", dBUser.getProfession().getName());
            }
            if (!(dBUser.getSpecialty() == null || dBUser.getSpecialty().getName() == null)) {
                hashMap.put(OmnitureConstants.OMNITURE_FILTER_SPECIALTY, dBUser.getSpecialty().getName());
            }
            if (!(dBUser.getLocation() == null || dBUser.getLocation().getName() == null)) {
                hashMap.put("location", dBUser.getLocation().getName());
            }
            if (LegacyUpdateHelper.getInstance().doesLegacyVersionExist()) {
                hashMap.put("legacy exists", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            } else {
                hashMap.put("legacy exists", "false");
            }
            addMetaData(hashMap);
        }
    }

    public void setName(String str, String str2) {
        String str3 = "";
        if (str != null) {
            str3 = str3 + str;
        }
        if (str2 != null) {
            if (!str3.isEmpty()) {
                str3 = str3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            str3 = str3 + str2;
        }
        str3.isEmpty();
    }

    public void setUserName(String str) {
        if (str != null) {
            FirebaseCrashlytics.getInstance().setUserId(str);
        }
    }

    public void leaveBreadcrumb(String str) {
        if (this.hasInitialized) {
            Log.d("CRITT", "breadcrumb: " + str);
            FirebaseCrashlytics.getInstance().log(str);
        }
    }

    public void logHandledException(Exception exc) {
        if (this.hasInitialized) {
            FirebaseCrashlytics.getInstance().recordException(exc);
        }
    }

    public void addMetaData(Map<String, String> map) {
        if (this.hasInitialized) {
            for (Map.Entry next : map.entrySet()) {
                FirebaseCrashlytics.getInstance().setCustomKey((String) next.getKey(), (String) next.getValue());
            }
        }
    }
}
