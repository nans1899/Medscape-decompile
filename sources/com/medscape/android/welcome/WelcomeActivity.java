package com.medscape.android.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.AdBlockerWebViewAcitivity;
import com.medscape.android.activity.DebugSettingsActivity;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.forceup.ForceUpManager;
import com.medscape.android.helper.FileCopyService;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.util.RedirectConstants;
import com.medscape.android.util.RedirectHandler;
import com.medscape.android.util.Util;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddatacompliance.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.activities.LandingActivity;
import com.webmd.wbmdproffesionalauthentication.activities.LoginActivity;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;
import com.webmd.wbmdsimullytics.platform.PlatformConfigurationManager;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013*\u0001\b\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\"\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00182\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0018H\u0014J\b\u0010#\u001a\u00020\u0018H\u0016J\b\u0010$\u001a\u00020\u0018H\u0014J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010'\u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\u001f\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u00152\b\u0010+\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020\u0018H\u0002J\b\u0010/\u001a\u00020\u0018H\u0002J\b\u00100\u001a\u00020\u0018H\u0002J\u0010\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/medscape/android/welcome/WelcomeActivity;", "Lcom/medscape/android/base/BaseActivity;", "Lcom/medscape/android/welcome/LoginCompletedCallback;", "()V", "LOGIN_REQUEST", "", "LOGIN_RESULT", "gdprReceiver", "com/medscape/android/welcome/WelcomeActivity$gdprReceiver$1", "Lcom/medscape/android/welcome/WelcomeActivity$gdprReceiver$1;", "goToDebug", "", "isForceUpDialogShown", "isFreshLogin", "isGDPRRegistered", "isLoginRegistered", "isNotificationEnabled", "()Z", "mLoginReceiver", "Lcom/medscape/android/welcome/LoginReceiver;", "redirectPayload", "", "reloadScreen", "initializeForceUp", "", "initializeSimuLlyticsPlatforms", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLoginComplete", "onResume", "performAppStartup", "registerGDPRReceiver", "registerLoginReceiver", "remoteConfigFetching", "setCustomAttributes", "key", "value", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "startAdBlocker", "startCopyProcess", "startHomeScreen", "startLoginProcess", "startMedscape", "authDone", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WelcomeActivity.kt */
public final class WelcomeActivity extends BaseActivity implements LoginCompletedCallback {
    private static final int AD_BLOCKER_REQUEST_CODE = 12390;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "WelcomeActivity";
    private final int LOGIN_REQUEST = 1011;
    private final int LOGIN_RESULT = 11101;
    private HashMap _$_findViewCache;
    private final WelcomeActivity$gdprReceiver$1 gdprReceiver = new WelcomeActivity$gdprReceiver$1(this);
    private boolean goToDebug;
    private boolean isForceUpDialogShown;
    private boolean isFreshLogin;
    private boolean isGDPRRegistered;
    private boolean isLoginRegistered;
    private LoginReceiver mLoginReceiver;
    private String redirectPayload = "";
    private boolean reloadScreen;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: private */
    public final boolean isNotificationEnabled() {
        return NotificationManagerCompat.from(getApplicationContext()).areNotificationsEnabled();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x008e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.getStatus(), (java.lang.Object) com.wbmd.wbmddatacompliance.gdpr.GDPRState.STATE_SHOWING_ROADBLOCK) != false) goto L_0x0090;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            super.onCreate(r9)
            android.content.Intent r9 = r8.getIntent()
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            int r9 = r9.getFlags()
            r0 = 4194304(0x400000, float:5.877472E-39)
            r9 = r9 & r0
            r0 = 0
            if (r9 == 0) goto L_0x0018
            r9 = 1
            goto L_0x0019
        L_0x0018:
            r9 = 0
        L_0x0019:
            r1 = r8
            android.content.Context r1 = (android.content.Context) r1
            boolean r2 = com.webmd.wbmdproffesionalauthentication.providers.AccountProvider.isUserLoggedIn(r1)
            r9 = r9 & r2
            if (r9 == 0) goto L_0x0027
            r8.finish()
            return
        L_0x0027:
            r8.registerGDPRReceiver()
            r8.remoteConfigFetching()
            kotlinx.coroutines.GlobalScope r9 = kotlinx.coroutines.GlobalScope.INSTANCE
            r2 = r9
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getDefault()
            r3 = r9
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            r4 = 0
            com.medscape.android.welcome.WelcomeActivity$onCreate$1 r9 = new com.medscape.android.welcome.WelcomeActivity$onCreate$1
            r5 = 0
            r9.<init>(r8, r5)
            r5 = r9
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 2
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            r8.initializeSimuLlyticsPlatforms()
            com.medscape.android.util.GetAdvertisingIdTask r9 = new com.medscape.android.util.GetAdvertisingIdTask
            r9.<init>(r1)
            java.lang.Void[] r2 = new java.lang.Void[r0]
            r9.execute(r2)
            com.wbmd.wbmddatacompliance.gdpr.GDPRStateManager r9 = new com.wbmd.wbmddatacompliance.gdpr.GDPRStateManager
            android.content.Context r2 = r8.getApplicationContext()
            com.medscape.android.util.GDPRFailureLog r3 = new com.medscape.android.util.GDPRFailureLog
            r4 = r8
            android.app.Activity r4 = (android.app.Activity) r4
            r3.<init>(r4)
            com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback r3 = (com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback) r3
            r9.<init>(r2, r3)
            com.wbmd.wbmddatacompliance.gdpr.GDPRState r2 = com.wbmd.wbmddatacompliance.gdpr.GDPRState.getInstance(r1)
            java.lang.String r3 = "GDPRState.getInstance(this)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r2 = r2.getStatus()
            java.lang.String r4 = "Not Determined"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r2 != 0) goto L_0x0090
            com.wbmd.wbmddatacompliance.gdpr.GDPRState r2 = com.wbmd.wbmddatacompliance.gdpr.GDPRState.getInstance(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.String r2 = r2.getStatus()
            java.lang.String r3 = "Is EU, GDRP has not been accepted"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x0097
        L_0x0090:
            com.medscape.android.BI.omniture.OmnitureManager r2 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r2.setOmniturePrivacyStatus(r0, r1)
        L_0x0097:
            com.medscape.android.welcome.WelcomeActivity$onCreate$2 r0 = new com.medscape.android.welcome.WelcomeActivity$onCreate$2
            r0.<init>(r8)
            com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback r0 = (com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback) r0
            r9.shouldShowAcceptancePrompt(r0)
            com.medscape.android.activity.cme.CMEHelper r9 = com.medscape.android.activity.cme.CMEHelper.INSTANCE
            com.medscape.android.MedscapeApplication r0 = com.medscape.android.MedscapeApplication.get()
            android.content.Context r0 = (android.content.Context) r0
            r9.unRegisterSaveReceiver(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.welcome.WelcomeActivity.onCreate(android.os.Bundle):void");
    }

    private final void registerGDPRReceiver() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACCEPT_ACTION));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACTIVITY_VIEW));
        this.isGDPRRegistered = true;
    }

    /* access modifiers changed from: private */
    public final void registerLoginReceiver() {
        this.mLoginReceiver = new LoginReceiver(this, this);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        LoginReceiver loginReceiver = this.mLoginReceiver;
        Intrinsics.checkNotNull(loginReceiver);
        instance.registerReceiver(loginReceiver, new IntentFilter(AuthComponentConstants.EXTRA_LOGIN_BROADCAST));
        this.isLoginRegistered = true;
    }

    private final void remoteConfigFetching() {
        new RemoteConfig().fetchRemoteConfig(this);
    }

    public void onLoginComplete() {
        startMedscape(true);
    }

    /* access modifiers changed from: private */
    public final void initializeForceUp() {
        boolean booleanExtra = getIntent().getBooleanExtra(com.medscape.android.Constants.EXTRA_FORCE_UP, false);
        this.isForceUpDialogShown = booleanExtra;
        if (!booleanExtra) {
            Log.d(TAG, "Configuring Force up: starting forceup");
            new ForceUpManager().initializeForceup(this);
        }
    }

    /* access modifiers changed from: private */
    public final void performAppStartup() {
        boolean z;
        Context context = this;
        OmnitureManager.get().setOmniturePrivacyStatus(true, context);
        if (Util.isAdBlockerInstalled()) {
            startAdBlocker();
            return;
        }
        Intent intent = getIntent();
        if (intent == null || (intent.getFlags() & 1048576) != 0) {
            z = false;
        } else {
            this.redirectPayload = getIntent().getStringExtra(RedirectConstants.REDIRECT_BUNDLE_KEY);
            z = intent.getBooleanExtra(com.medscape.android.Constants.EXTRA_GO_TO_LOGIN, false);
        }
        if (z) {
            Intent intent2 = new Intent(context, LoginActivity.class);
            intent2.putExtra(com.webmd.wbmdproffesionalauthentication.constants.Constants.LOGIN_FROM_LOGOUT, true);
            startActivityForResult(intent2, this.LOGIN_REQUEST);
        } else if (AccountProvider.isUserLoggedIn(context)) {
            startMedscape(false);
        } else {
            startLoginProcess();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.goToDebug) {
            this.goToDebug = false;
            this.reloadScreen = true;
            startActivity(new Intent(this, DebugSettingsActivity.class));
        } else if (this.reloadScreen) {
            this.reloadScreen = false;
            performAppStartup();
        }
    }

    private final void startLoginProcess() {
        Intent intent = new Intent(this, LandingActivity.class);
        intent.putExtra(LandingActivity.DEBUG_EXTRA, false);
        startActivityForResult(intent, this.LOGIN_RESULT);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.LOGIN_RESULT) {
            if (i2 == 0) {
                finish();
            }
            if (i2 == 11011) {
                this.goToDebug = true;
            }
        }
        if (i == 12390) {
            performAppStartup();
        }
        if (i == this.LOGIN_REQUEST && i2 != -1) {
            startLoginProcess();
        }
    }

    private final void startAdBlocker() {
        startActivityForResult(new Intent(this, AdBlockerWebViewAcitivity.class), 12390);
    }

    private final void startMedscape(boolean z) {
        Context context = this;
        Util.updateAuthStatus(context, z);
        String str = this.redirectPayload;
        if (!(str == null || str == null)) {
            if (str.length() > 0) {
                new RedirectHandler(true).handleRedirect(context, this.redirectPayload, true);
                LoginReceiver loginReceiver = this.mLoginReceiver;
                if (loginReceiver != null) {
                    LocalBroadcastManager.getInstance(context).unregisterReceiver(loginReceiver);
                }
                this.isLoginRegistered = false;
                finish();
                return;
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new WelcomeActivity$startMedscape$2(this, (Continuation) null), 2, (Object) null);
        startHomeScreen();
    }

    /* access modifiers changed from: private */
    public final void startCopyProcess() {
        Context context = this;
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            if (new File(Environment.getExternalStorageDirectory().toString() + "/Medscape/").exists()) {
                int i = SharedPreferenceProvider.get().get(com.medscape.android.Constants.COPY_STATUS, (int) com.medscape.android.Constants.COPY_STATUS_UNKNOWN);
                if (i == 924602) {
                    Intent intent = new Intent(context, FileCopyService.class);
                    intent.putExtra(com.medscape.android.Constants.EXTRA_COPY_BUNDLE, true);
                    startService(intent);
                    SharedPreferenceProvider.get().save(com.medscape.android.Constants.COPY_STATUS, (int) com.medscape.android.Constants.COPY_IN_PROGRESS);
                } else if (i == 924605 || i == 924603) {
                    Intent intent2 = new Intent(context, FileCopyService.class);
                    intent2.putExtra(com.medscape.android.Constants.EXTRA_COPY_BUNDLE, false);
                    startService(intent2);
                    SharedPreferenceProvider.get().save(com.medscape.android.Constants.COPY_STATUS, (int) com.medscape.android.Constants.COPY_COMPLETED);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LoginReceiver loginReceiver = this.mLoginReceiver;
        if (loginReceiver != null && this.isLoginRegistered) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(loginReceiver);
        }
        if (this.isGDPRRegistered) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.gdprReceiver);
        }
        super.onDestroy();
    }

    private final void initializeSimuLlyticsPlatforms() {
        PlatformConfigurationManager instance = PlatformConfigurationManager.getInstance();
        instance.fetchPlatforms(this, new WelcomeActivity$initializeSimuLlyticsPlatforms$1(this, instance));
    }

    /* access modifiers changed from: private */
    public final void setCustomAttributes(String str, Boolean bool) {
        PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(this);
        Intrinsics.checkNotNull(bool);
        platformRouteDispatcher.routeUserAttribute(str, bool.booleanValue());
        Trace.d(TAG, "setCustomUserAttribute : " + str + " : " + bool + " in NotificationsActivity");
    }

    private final void startHomeScreen() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new WelcomeActivity$startHomeScreen$1(this, (Continuation) null), 2, (Object) null);
        Context context = this;
        Intent intent = new Intent(context, HomeScreenActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.putExtra(com.medscape.android.Constants.EXTRA_FRESH_LOGIN, this.isFreshLogin);
        startActivity(intent);
        LoginReceiver loginReceiver = this.mLoginReceiver;
        if (loginReceiver != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(loginReceiver);
        }
        if (risingFromColdStart()) {
            OmnitureManager.get().setmCurrentPageName((String) null);
            OmnitureManager.get().trackModule(context, "other", "launch", com.medscape.android.Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        }
        finish();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/welcome/WelcomeActivity$Companion;", "", "()V", "AD_BLOCKER_REQUEST_CODE", "", "TAG", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: WelcomeActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
