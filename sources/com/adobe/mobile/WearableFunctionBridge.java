package com.adobe.mobile;

final class WearableFunctionBridge {
    private static Class<?> configSynchronizerClassLoader;
    private static Class<?> wearableFunctionClassLoader;

    WearableFunctionBridge() {
    }

    private static Class<?> getWearableFunctionClass() {
        Class<?> cls = wearableFunctionClassLoader;
        if (cls != null) {
            return cls;
        }
        try {
            wearableFunctionClassLoader = WearableFunctionBridge.class.getClassLoader().loadClass("com.adobe.mobile.WearableFunction");
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Failed to load class com.adobe.mobile.WearableFunction", e.getLocalizedMessage());
        }
        return wearableFunctionClassLoader;
    }

    private static Class<?> getConfigSynchronizerClass() {
        Class<?> cls = configSynchronizerClassLoader;
        if (cls != null) {
            return cls;
        }
        try {
            configSynchronizerClassLoader = WearableFunctionBridge.class.getClassLoader().loadClass("com.adobe.mobile.ConfigSynchronizer");
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Failed to load class com.adobe.mobile.ConfigSynchronizer", e.getLocalizedMessage());
        }
        return configSynchronizerClassLoader;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0052 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static boolean isGooglePlayServicesEnabled() {
        /*
            java.lang.String r0 = "isGooglePlayServicesAvailable"
            java.lang.String r1 = "Wearable - Google Play Services is not enabled in your app's AndroidManifest.xml"
            java.lang.Class<com.adobe.mobile.WearableFunctionBridge> r2 = com.adobe.mobile.WearableFunctionBridge.class
            r3 = 0
            r4 = 1
            r5 = 0
            java.lang.ClassLoader r6 = r2.getClassLoader()     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.String r7 = "com.google.android.gms.common.GoogleApiAvailability"
            java.lang.Class r6 = r6.loadClass(r7)     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.String r7 = "getInstance"
            java.lang.Class[] r8 = new java.lang.Class[r5]     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.reflect.Method r7 = r6.getDeclaredMethod(r7, r8)     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Object r7 = r7.invoke(r3, r8)     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Class[] r8 = new java.lang.Class[r4]     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Class<android.content.Context> r9 = android.content.Context.class
            r8[r5] = r9     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.reflect.Method r6 = r6.getDeclaredMethod(r0, r8)     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            android.content.Context r9 = com.adobe.mobile.StaticMethods.getSharedContext()     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            r8[r5] = r9     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            java.lang.Object r6 = r6.invoke(r7, r8)     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            boolean r7 = r6 instanceof java.lang.Integer     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            if (r7 == 0) goto L_0x0052
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            int r0 = r6.intValue()     // Catch:{ IllegalStateException -> 0x0046, Exception -> 0x0052 }
            if (r0 != 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r4 = 0
        L_0x0045:
            return r4
        L_0x0046:
            r6 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r4]
            java.lang.String r6 = r6.getLocalizedMessage()
            r7[r5] = r6
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r7)
        L_0x0052:
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.String r6 = "com.google.android.gms.common.GooglePlayServicesUtil"
            java.lang.Class r2 = r2.loadClass(r6)     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r5] = r7     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.reflect.Method r0 = r2.getDeclaredMethod(r0, r6)     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            android.content.Context r6 = com.adobe.mobile.StaticMethods.getSharedContext()     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            r2[r5] = r6     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            java.lang.Object r0 = r0.invoke(r3, r2)     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            boolean r2 = r0 instanceof java.lang.Integer     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            if (r2 == 0) goto L_0x008d
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            int r0 = r0.intValue()     // Catch:{ IllegalStateException -> 0x0081, Exception -> 0x008d }
            if (r0 != 0) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            r4 = 0
        L_0x0080:
            return r4
        L_0x0081:
            r0 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.String r0 = r0.getLocalizedMessage()
            r2[r5] = r0
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r2)
        L_0x008d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.WearableFunctionBridge.isGooglePlayServicesEnabled():boolean");
    }

    protected static boolean shouldSendHit() {
        if (!StaticMethods.isWearableApp()) {
            return true;
        }
        try {
            Object invoke = getWearableFunctionClass().getDeclaredMethod("shouldSendHit", new Class[0]).invoke((Object) null, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Error checking status of handheld app (%s)", e.getLocalizedMessage());
        }
        return true;
    }

    protected static void sendGenericRequest(String str, int i, String str2) {
        try {
            getWearableFunctionClass().getDeclaredMethod("sendGenericRequest", new Class[]{String.class, Integer.TYPE}).invoke((Object) null, new Object[]{str, Integer.valueOf(i)});
            StaticMethods.logDebugFormat("%s - Request Sent(%s)", str2, str);
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Error sending request (%s)", e.getLocalizedMessage());
        }
    }

    protected static byte[] retrieveData(String str, int i) {
        try {
            Object invoke = getWearableFunctionClass().getDeclaredMethod("retrieveData", new Class[]{String.class, Integer.TYPE}).invoke((Object) null, new Object[]{str, Integer.valueOf(i)});
            if (invoke instanceof byte[]) {
                return (byte[]) invoke;
            }
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Error sending request (%s)", e.getLocalizedMessage());
        }
        return null;
    }

    protected static byte[] retrieveAnalyticsRequestData(String str, String str2, int i, String str3) {
        try {
            Object invoke = getWearableFunctionClass().getDeclaredMethod("retrieveAnalyticsRequestData", new Class[]{String.class, String.class, Integer.TYPE}).invoke((Object) null, new Object[]{str, str2, Integer.valueOf(i)});
            if (invoke instanceof byte[]) {
                return (byte[]) invoke;
            }
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Error sending request (%s)", e.getLocalizedMessage());
        }
        return null;
    }

    protected static boolean sendThirdPartyRequest(String str, String str2, int i, String str3, String str4) {
        try {
            Object invoke = getWearableFunctionClass().getDeclaredMethod("sendThirdPartyRequest", new Class[]{String.class, String.class, Integer.TYPE, String.class}).invoke((Object) null, new Object[]{str, str2, Integer.valueOf(i), str3});
            if (invoke instanceof Boolean) {
                if (((Boolean) invoke).booleanValue()) {
                    StaticMethods.logDebugFormat("%s - Successfully forwarded hit (url:%s body:%s contentType:%s)", str4, str, str2, str3);
                } else {
                    StaticMethods.logDebugFormat("%s - Failed to forwarded hit (url:%s body:%s contentType:%s)", str4, str, str2, str3);
                }
                return ((Boolean) invoke).booleanValue();
            }
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Wearable - Error sending request (%s)", e.getLocalizedMessage());
        }
        return false;
    }

    protected static void syncVisitorIDToWearable(String str) {
        if (!StaticMethods.isWearableApp() && MobileConfig.getInstance().mobileUsingGooglePlayServices()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncVisitorID", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync visitor id (%s)", e.getLocalizedMessage());
            }
        }
    }

    protected static void syncAdvertisingIdentifierToWearable(String str) {
        if (!StaticMethods.isWearableApp() && MobileConfig.getInstance().mobileUsingGooglePlayServices()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncAdvertisingIdentifier", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync advertisingIdentifier id (%s)", e.getLocalizedMessage());
            }
        }
    }

    protected static void syncPushEnabledToWearable(boolean z) {
        if (!StaticMethods.isWearableApp() && MobileConfig.getInstance().mobileUsingGooglePlayServices()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncPushEnabled", new Class[]{Boolean.TYPE}).invoke((Object) null, new Object[]{Boolean.valueOf(z)});
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync push enabled status (%s)", e.getLocalizedMessage());
            }
        }
    }

    protected static void syncVidServiceToWearable(String str, String str2, String str3, long j, long j2, String str4) {
        if (!StaticMethods.isWearableApp() && MobileConfig.getInstance().mobileUsingGooglePlayServices()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncVidService", new Class[]{String.class, String.class, String.class, Long.TYPE, Long.TYPE, String.class}).invoke((Object) null, new Object[]{str, str2, str3, Long.valueOf(j), Long.valueOf(j2), str4});
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync visitor id service (%s)", e.getLocalizedMessage());
            }
        }
    }

    protected static void syncPrivacyStatusToWearable(int i) {
        if (!StaticMethods.isWearableApp() && MobileConfig.getInstance().mobileUsingGooglePlayServices()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncPrivacyStatus", new Class[]{Integer.TYPE}).invoke((Object) null, new Object[]{Integer.valueOf(i)});
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync privacy status (%s)", e.getLocalizedMessage());
            }
        }
    }

    protected static void syncConfigFromHandheld() {
        if (StaticMethods.isWearableApp()) {
            try {
                getConfigSynchronizerClass().getDeclaredMethod("syncConfigFromHandheld", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception e) {
                StaticMethods.logDebugFormat("Wearable - Unable to sync config (%s)", e.getLocalizedMessage());
            }
        }
    }
}
