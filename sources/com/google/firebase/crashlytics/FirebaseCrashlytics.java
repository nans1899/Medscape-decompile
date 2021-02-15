package com.google.firebase.crashlytics;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;

public class FirebaseCrashlytics {
    private static final int APP_EXCEPTION_CALLBACK_TIMEOUT_MS = 500;
    private static final String FIREBASE_CRASHLYTICS_ANALYTICS_ORIGIN = "clx";
    private static final String LEGACY_CRASH_ANALYTICS_ORIGIN = "crash";
    private final CrashlyticsCore core;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.google.firebase.crashlytics.internal.MissingNativeComponent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger} */
    /* JADX WARNING: type inference failed for: r0v16, types: [com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver, com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver] */
    /* JADX WARNING: type inference failed for: r6v2, types: [com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver, com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.firebase.crashlytics.FirebaseCrashlytics init(com.google.firebase.FirebaseApp r16, com.google.firebase.installations.FirebaseInstallationsApi r17, com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent r18, com.google.firebase.analytics.connector.AnalyticsConnector r19) {
        /*
            r8 = r16
            r0 = r19
            android.content.Context r9 = r16.getApplicationContext()
            java.lang.String r1 = r9.getPackageName()
            com.google.firebase.crashlytics.internal.common.IdManager r2 = new com.google.firebase.crashlytics.internal.common.IdManager
            r3 = r17
            r2.<init>(r9, r1, r3)
            com.google.firebase.crashlytics.internal.common.DataCollectionArbiter r4 = new com.google.firebase.crashlytics.internal.common.DataCollectionArbiter
            r4.<init>(r8)
            if (r18 != 0) goto L_0x0021
            com.google.firebase.crashlytics.internal.MissingNativeComponent r1 = new com.google.firebase.crashlytics.internal.MissingNativeComponent
            r1.<init>()
            r3 = r1
            goto L_0x0023
        L_0x0021:
            r3 = r18
        L_0x0023:
            com.google.firebase.crashlytics.internal.Onboarding r11 = new com.google.firebase.crashlytics.internal.Onboarding
            r11.<init>(r8, r9, r2, r4)
            if (r0 == 0) goto L_0x0071
            com.google.firebase.crashlytics.internal.Logger r1 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r5 = "Firebase Analytics is available."
            r1.d(r5)
            com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger r1 = new com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger
            r1.<init>(r0)
            com.google.firebase.crashlytics.CrashlyticsAnalyticsListener r5 = new com.google.firebase.crashlytics.CrashlyticsAnalyticsListener
            r5.<init>()
            com.google.firebase.analytics.connector.AnalyticsConnector$AnalyticsConnectorHandle r0 = subscribeToAnalyticsEvents(r0, r5)
            if (r0 == 0) goto L_0x0062
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r6 = "Firebase Analytics listener registered successfully."
            r0.d(r6)
            com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver r0 = new com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver
            r0.<init>()
            com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger r6 = new com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger
            r7 = 500(0x1f4, float:7.0E-43)
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS
            r6.<init>(r1, r7, r10)
            r5.setBreadcrumbEventReceiver(r0)
            r5.setCrashlyticsOriginEventReceiver(r6)
            r1 = r6
            goto L_0x0084
        L_0x0062:
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r5 = "Firebase Analytics listener registration failed."
            r0.d(r5)
            com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource r0 = new com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource
            r0.<init>()
            goto L_0x0084
        L_0x0071:
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r1 = "Firebase Analytics is unavailable."
            r0.d(r1)
            com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource r0 = new com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource
            r0.<init>()
            com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger r1 = new com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger
            r1.<init>()
        L_0x0084:
            r5 = r0
            r6 = r1
            java.lang.String r0 = "Crashlytics Exception Handler"
            java.util.concurrent.ExecutorService r7 = com.google.firebase.crashlytics.internal.common.ExecutorUtils.buildSingleThreadExecutorService(r0)
            com.google.firebase.crashlytics.internal.common.CrashlyticsCore r15 = new com.google.firebase.crashlytics.internal.common.CrashlyticsCore
            r0 = r15
            r1 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            boolean r0 = r11.onPreExecute()
            if (r0 != 0) goto L_0x00a5
            com.google.firebase.crashlytics.internal.Logger r0 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.String r1 = "Unable to start Crashlytics."
            r0.e(r1)
            r0 = 0
            return r0
        L_0x00a5:
            java.lang.String r0 = "com.google.firebase.crashlytics.startup"
            java.util.concurrent.ExecutorService r0 = com.google.firebase.crashlytics.internal.common.ExecutorUtils.buildSingleThreadExecutorService(r0)
            com.google.firebase.crashlytics.internal.settings.SettingsController r13 = r11.retrieveSettingsData(r9, r8, r0)
            boolean r14 = r15.onPreExecute(r13)
            com.google.firebase.crashlytics.FirebaseCrashlytics$1 r1 = new com.google.firebase.crashlytics.FirebaseCrashlytics$1
            r10 = r1
            r12 = r0
            r2 = r15
            r10.<init>(r12, r13, r14, r15)
            com.google.android.gms.tasks.Tasks.call(r0, r1)
            com.google.firebase.crashlytics.FirebaseCrashlytics r0 = new com.google.firebase.crashlytics.FirebaseCrashlytics
            r0.<init>(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.FirebaseCrashlytics.init(com.google.firebase.FirebaseApp, com.google.firebase.installations.FirebaseInstallationsApi, com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent, com.google.firebase.analytics.connector.AnalyticsConnector):com.google.firebase.crashlytics.FirebaseCrashlytics");
    }

    private static AnalyticsConnector.AnalyticsConnectorHandle subscribeToAnalyticsEvents(AnalyticsConnector analyticsConnector, CrashlyticsAnalyticsListener crashlyticsAnalyticsListener) {
        AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener(FIREBASE_CRASHLYTICS_ANALYTICS_ORIGIN, crashlyticsAnalyticsListener);
        if (registerAnalyticsConnectorListener == null) {
            Logger.getLogger().d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
            registerAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener("crash", crashlyticsAnalyticsListener);
            if (registerAnalyticsConnectorListener != null) {
                Logger.getLogger().w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
            }
        }
        return registerAnalyticsConnectorListener;
    }

    private FirebaseCrashlytics(CrashlyticsCore crashlyticsCore) {
        this.core = crashlyticsCore;
    }

    public static FirebaseCrashlytics getInstance() {
        FirebaseCrashlytics firebaseCrashlytics = (FirebaseCrashlytics) FirebaseApp.getInstance().get(FirebaseCrashlytics.class);
        if (firebaseCrashlytics != null) {
            return firebaseCrashlytics;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    public void recordException(Throwable th) {
        if (th == null) {
            Logger.getLogger().w("Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.core.logException(th);
        }
    }

    public void log(String str) {
        this.core.log(str);
    }

    public void setUserId(String str) {
        this.core.setUserId(str);
    }

    public void setCustomKey(String str, boolean z) {
        this.core.setCustomKey(str, Boolean.toString(z));
    }

    public void setCustomKey(String str, double d) {
        this.core.setCustomKey(str, Double.toString(d));
    }

    public void setCustomKey(String str, float f) {
        this.core.setCustomKey(str, Float.toString(f));
    }

    public void setCustomKey(String str, int i) {
        this.core.setCustomKey(str, Integer.toString(i));
    }

    public void setCustomKey(String str, long j) {
        this.core.setCustomKey(str, Long.toString(j));
    }

    public void setCustomKey(String str, String str2) {
        this.core.setCustomKey(str, str2);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.core.checkForUnsentReports();
    }

    public void sendUnsentReports() {
        this.core.sendUnsentReports();
    }

    public void deleteUnsentReports() {
        this.core.deleteUnsentReports();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.core.didCrashOnPreviousExecution();
    }

    public void setCrashlyticsCollectionEnabled(boolean z) {
        this.core.setCrashlyticsCollectionEnabled(Boolean.valueOf(z));
    }

    public void setCrashlyticsCollectionEnabled(Boolean bool) {
        this.core.setCrashlyticsCollectionEnabled(bool);
    }
}
