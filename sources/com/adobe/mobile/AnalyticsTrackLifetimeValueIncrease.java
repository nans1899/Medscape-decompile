package com.adobe.mobile;

import android.content.SharedPreferences;
import com.adobe.mobile.StaticMethods;
import com.facebook.appevents.AppEventsConstants;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

final class AnalyticsTrackLifetimeValueIncrease {
    private static final String LOCAL_STORAGE_LIFETIME_VALUE_KEY = "ADB_LIFETIME_VALUE";
    private static final String LTV_ACTION_NAME = "LifetimeValueIncrease";
    protected static final String LTV_AMOUNT_KEY = "a.ltv.amount";
    private static final String LTV_INCREASE_KEY = "a.ltv.increase";
    private static final Object _lifetimeValueMutex = new Object();

    AnalyticsTrackLifetimeValueIncrease() {
    }

    public static void trackLifetimeValueIncrease(BigDecimal bigDecimal, Map<String, Object> map) {
        if (bigDecimal == null || bigDecimal.signum() == -1) {
            StaticMethods.logWarningFormat("Analytics - trackLifetimeValueIncrease failed, invalid amount specified '%f'", bigDecimal);
            return;
        }
        incrementLifetimeValue(bigDecimal);
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (getLifetimeValue() != null) {
            Lifecycle.updateContextData(new HashMap<String, Object>() {
                {
                    put(AnalyticsTrackLifetimeValueIncrease.LTV_AMOUNT_KEY, AnalyticsTrackLifetimeValueIncrease.getLifetimeValue());
                }
            });
            hashMap.put(LTV_AMOUNT_KEY, getLifetimeValue());
            hashMap.put(LTV_INCREASE_KEY, bigDecimal);
            AnalyticsTrackInternal.trackInternal(LTV_ACTION_NAME, hashMap, StaticMethods.getTimeSince1970());
        }
    }

    protected static BigDecimal getLifetimeValue() {
        BigDecimal bigDecimal;
        synchronized (_lifetimeValueMutex) {
            try {
                bigDecimal = new BigDecimal(StaticMethods.getSharedPreferences().getString(LOCAL_STORAGE_LIFETIME_VALUE_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            } catch (NumberFormatException unused) {
                bigDecimal = new BigDecimal(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Analytics - Error getting current lifetime value:(%s).", e.getMessage());
                bigDecimal = null;
            }
        }
        return bigDecimal;
    }

    protected static void setLifetimeValue(BigDecimal bigDecimal) {
        synchronized (_lifetimeValueMutex) {
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                if (bigDecimal != null) {
                    if (bigDecimal.signum() != -1) {
                        sharedPreferencesEditor.putString(LOCAL_STORAGE_LIFETIME_VALUE_KEY, bigDecimal.toString());
                        sharedPreferencesEditor.commit();
                    }
                }
                sharedPreferencesEditor.putString(LOCAL_STORAGE_LIFETIME_VALUE_KEY, "0.00");
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Analytics - Error updating lifetime value: (%s).", e.getMessage());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void incrementLifetimeValue(java.math.BigDecimal r4) {
        /*
            java.lang.Object r0 = _lifetimeValueMutex
            monitor-enter(r0)
            java.math.BigDecimal r1 = getLifetimeValue()     // Catch:{ all -> 0x001e }
            if (r4 == 0) goto L_0x001c
            int r2 = r4.signum()     // Catch:{ all -> 0x001e }
            r3 = -1
            if (r2 == r3) goto L_0x001c
            if (r1 != 0) goto L_0x0013
            goto L_0x001c
        L_0x0013:
            java.math.BigDecimal r4 = r1.add(r4)     // Catch:{ all -> 0x001e }
            setLifetimeValue(r4)     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x001e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.AnalyticsTrackLifetimeValueIncrease.incrementLifetimeValue(java.math.BigDecimal):void");
    }
}
