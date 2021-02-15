package com.medscape.android.homescreen.views;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.share.internal.ShareConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BottomNavBaseActivity;
import com.medscape.android.base.InternetBroadcastReceiver;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.homescreen.interfaces.IDialogShowListener;
import com.medscape.android.homescreen.viewmodel.HomeScreenViewModel;
import com.medscape.android.homescreen.views.HomeScreenFragment;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.model.Update;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 52\u00020\u00012\u00020\u00022\u00020\u0003:\u00015B\u0005¢\u0006\u0002\u0010\u0004J\"\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0013H\u0014J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0015H\u0016J\b\u0010 \u001a\u00020\u0013H\u0014J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0015H\u0016J\b\u0010#\u001a\u00020\u0013H\u0014J(\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00152\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'2\u0006\u0010)\u001a\u00020\u0015H\u0016J\u001a\u0010*\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00152\b\u0010+\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0015H\u0016J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u0015H\u0016J\u0012\u0010/\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u000100H\u0016J\u0018\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u000200H\u0016J\b\u00104\u001a\u00020\u0013H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenActivity;", "Lcom/medscape/android/base/BottomNavBaseActivity;", "Lcom/medscape/android/homescreen/interfaces/IDialogShowListener;", "Lcom/medscape/android/updater/OnUpdateListener;", "()V", "homeViewModel", "Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "getHomeViewModel", "()Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "landingViewModel", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "getLandingViewModel", "()Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "landingViewModel$delegate", "loginInternetReceiver", "Lcom/medscape/android/base/InternetBroadcastReceiver;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNetworkError", "msg", "onPause", "onProgressUpdate", "d", "onResume", "onUpdateAvailable", "type", "updateList", "", "Lcom/medscape/android/updater/model/Update;", "downloadType", "onUpdateComplete", "update", "onUpdateNotAvailable", "setMaxProgress", "max", "setProgressMessage", "", "showDialog", "msgID", "message", "updateClinicalReference", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeScreenActivity.kt */
public final class HomeScreenActivity extends BottomNavBaseActivity implements IDialogShowListener, OnUpdateListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int START_CLINICAL_REFERENCE_DOWNLOAD_REQUEST = 3;
    public static final int START_DRUG_UPDATE_REQUEST = 6;
    private HashMap _$_findViewCache;
    private final Lazy homeViewModel$delegate = LazyKt.lazy(new HomeScreenActivity$homeViewModel$2(this));
    private final Lazy landingViewModel$delegate = LazyKt.lazy(new HomeScreenActivity$landingViewModel$2(this));
    private InternetBroadcastReceiver loginInternetReceiver;

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

    public final HomeScreenViewModel getHomeViewModel() {
        return (HomeScreenViewModel) this.homeViewModel$delegate.getValue();
    }

    public final LandingFeedViewModel getLandingViewModel() {
        return (LandingFeedViewModel) this.landingViewModel$delegate.getValue();
    }

    public void onProgressUpdate(int i) {
    }

    public void setMaxProgress(int i) {
    }

    public void setProgressMessage(String str) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.skipForceUpAutoRegister = true;
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            toolbar.setVisibility(8);
        }
        getHomeViewModel().setDialogListener(this);
        HomeScreenFragment.Companion companion = HomeScreenFragment.Companion;
        HomeScreenViewModel homeViewModel = getHomeViewModel();
        Intrinsics.checkNotNullExpressionValue(homeViewModel, "homeViewModel");
        setBaseFragment(companion.newInstance(homeViewModel));
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        BaseLandingFragment baseFragment = getBaseFragment();
        if (baseFragment != null) {
            beginTransaction.replace(R.id.home_container, (HomeScreenFragment) baseFragment);
            beginTransaction.commit();
            if (Intrinsics.areEqual((Object) Settings.singleton(this).getSetting(Constants.PREF_COOKIE_STRING, ""), (Object) "")) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                InternetBroadcastReceiver internetBroadcastReceiver = new InternetBroadcastReceiver();
                this.loginInternetReceiver = internetBroadcastReceiver;
                registerReceiver(internetBroadcastReceiver, intentFilter);
            }
            getHomeViewModel().setFreshLogin(getIntent().getBooleanExtra(Constants.EXTRA_FRESH_LOGIN, false));
            getLandingViewModel().setMPayload(getIntent().getStringExtra(Constants.EXTRA_DEEPLINK_PAYLOAD));
            getHomeViewModel().setFromOnCreate(true);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.homescreen.views.HomeScreenFragment");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getHomeViewModel().onResume(isSessionValid());
        Context context = this;
        if (!AccountProvider.isUserLoggedIn(context)) {
            finish();
            return;
        }
        AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_HOME_VIEWED);
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "this.intent");
        boolean z = true;
        if (intent.getAction() != null) {
            Intent intent2 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent2, "this.intent");
            String action = intent2.getAction();
            Intrinsics.checkNotNull(action);
            if (StringsKt.equals(action, "android.intent.action.MAIN", true)) {
                MedscapeApplication.get().listenForUpdates(this);
                getHomeViewModel().setupUpdateManager(this);
            }
        }
        if (Util.isOnline(context)) {
            Intent intent3 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent3, "this.intent");
            intent3.setAction("");
        }
        getHomeViewModel().handleNativeClinicalUpgrade();
        boolean z2 = false;
        if (getHomeViewModel().isFreshLogin() || getHomeViewModel().isFromOnCreate()) {
            getHomeViewModel().setFreshLogin(false);
            getHomeViewModel().setFromOnCreate(false);
            z2 = true;
        }
        if (!getHomeViewModel().updateSpecialityChange()) {
            z = z2;
        }
        if (z) {
            BaseLandingFragment baseFragment = getBaseFragment();
            if (baseFragment != null) {
                baseFragment.loadFeed(this);
                return;
            }
            return;
        }
        BaseLandingFragment baseFragment2 = getBaseFragment();
        if (baseFragment2 != null) {
            baseFragment2.oneTimeLoadFeed(this);
        }
    }

    public void showDialog(int i, String str) {
        Intrinsics.checkNotNullParameter(str, ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.root_view);
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "root_view");
        new HomeScreenDialog(this, constraintLayout).showDialogs(i, str);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        InternetBroadcastReceiver internetBroadcastReceiver;
        super.onPause();
        if (isFinishing() && (internetBroadcastReceiver = this.loginInternetReceiver) != null) {
            unregisterReceiver(internetBroadcastReceiver);
        }
        getHomeViewModel().onPause(isFinishing());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 6) {
            if (i2 == 1) {
                getHomeViewModel().dataUpdateFinish();
                return;
            }
            if (getHomeViewModel().isClinicalReferenceUpdateAvailable() && i2 != 0) {
                updateClinicalReference();
            }
            BaseLandingFragment baseFragment = getBaseFragment();
            if (baseFragment != null) {
                baseFragment.loadFeed(this);
            }
            CapabilitiesManager.getInstance(this).getCapabilitiesFromServer();
        } else if (i == 9) {
            getHomeViewModel().handleSpecialityChange();
            BaseLandingFragment baseFragment2 = getBaseFragment();
            if (baseFragment2 != null) {
                baseFragment2.loadFeed(this);
            }
        }
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        Context context = this;
        if ((!Util.isTestDriveTimeSet(context) || Util.isTestDriveTimeFinished(context)) && !AccountProvider.isUserLoggedIn(context)) {
            finish();
            return;
        }
        getHomeViewModel().setDownloadType(i2);
        if (!Util.isSDCardAvailable()) {
            if (!isFinishing()) {
                DialogUtil.showAlertDialog(9, (String) null, getString(R.string.alert_dialog_sdcard_required_message), context).show();
            }
        } else if (i != 2) {
            Deferred unused = BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new HomeScreenActivity$onUpdateAvailable$1(this, (Continuation) null), 2, (Object) null);
        } else if (!Util.isTestDriveTimeSet(context) || Util.isTestDriveTimeFinished(context)) {
            Intent intent = new Intent(context, ClinicalReferenceInstallationRequestActivity.class);
            if (getHomeViewModel().isClinicalReferenceResume()) {
                intent.putExtra("isResume", true);
            } else {
                intent.putExtra("isResume", false);
            }
            startActivityForResult(intent, 3);
        }
    }

    public void onUpdateComplete(int i, Update update) {
        if (i == 1 && !getHomeViewModel().isFirstInstall()) {
            float f = getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0).getFloat("version", -1.0f);
            OmnitureManager.get().trackModule(this, "other", "content-finish", "" + f, (Map<String, Object>) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0158, code lost:
        if (r3.isWifiConnected() != false) goto L_0x015a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpdateNotAvailable(int r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r7 = r0
            android.content.Context r7 = (android.content.Context) r7
            boolean r2 = com.medscape.android.util.Util.isTestDriveTimeSet(r7)
            if (r2 == 0) goto L_0x0013
            boolean r2 = com.medscape.android.util.Util.isTestDriveTimeFinished(r7)
            if (r2 == 0) goto L_0x0205
        L_0x0013:
            r8 = 3
            java.lang.String r2 = "-1"
            r9 = 1
            if (r1 != r9) goto L_0x004c
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r3 = "clinical_reference_version"
            java.lang.String r4 = "-11"
            java.lang.String r1 = r1.getSetting((java.lang.String) r3, (java.lang.String) r4)
            int r1 = java.lang.Integer.parseInt(r1)
            r4 = -11
            if (r1 != r4) goto L_0x0205
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            r1.saveSetting((java.lang.String) r3, (java.lang.String) r2)
            boolean r1 = com.medscape.android.util.Util.isOnline(r7)
            if (r1 == 0) goto L_0x0205
            boolean r1 = com.medscape.android.util.Util.isTestDriveTimeSet(r7)
            if (r1 == 0) goto L_0x0205
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class<com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity> r2 = com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity.class
            r1.<init>(r7, r2)
            r0.startActivityForResult(r1, r8)
            goto L_0x0205
        L_0x004c:
            r3 = 4
            if (r1 != r3) goto L_0x0205
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r3 = "optionServerDataVersion"
            java.lang.String r1 = r1.getSetting((java.lang.String) r3, (java.lang.String) r2)
            float r10 = java.lang.Float.parseFloat(r1)
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r3 = "minServerDataVersion"
            java.lang.String r1 = r1.getSetting((java.lang.String) r3, (java.lang.String) r2)
            float r11 = java.lang.Float.parseFloat(r1)
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r2 = "optionalDataUpdateTime"
            java.lang.String r12 = "0"
            java.lang.String r1 = r1.getSetting((java.lang.String) r2, (java.lang.String) r12)
            long r3 = java.lang.Long.parseLong(r1)
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            java.util.Calendar r5 = java.util.Calendar.getInstance()
            r13 = 0
            java.lang.String r15 = ""
            int r6 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r6 != 0) goto L_0x00ba
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            java.lang.String r3 = "calendar"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            long r3 = java.lang.System.currentTimeMillis()
            r1.setTimeInMillis(r3)
            r3 = 2
            r1.add(r3, r8)
            com.medscape.android.Settings r3 = com.medscape.android.Settings.singleton(r7)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r15)
            long r5 = r1.getTimeInMillis()
            r4.append(r5)
            java.lang.String r1 = r4.toString()
            r3.saveSetting((java.lang.String) r2, (java.lang.String) r1)
            goto L_0x00ce
        L_0x00ba:
            java.lang.String r2 = "currentDate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            long r13 = java.lang.System.currentTimeMillis()
            r1.setTimeInMillis(r13)
            java.lang.String r1 = "expireDate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            r5.setTimeInMillis(r3)
        L_0x00ce:
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            float r13 = r1.getClientVersion()
            r1 = 946(0x3b2, float:1.326E-42)
            float r1 = (float) r1
            r14 = 6
            int r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x00ea
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class<com.medscape.android.activity.install.DrugInstallationActivity> r2 = com.medscape.android.activity.install.DrugInstallationActivity.class
            r1.<init>(r7, r2)
            r0.startActivityForResult(r1, r14)
            goto L_0x0205
        L_0x00ea:
            float r16 = r10 - r13
            r6 = 0
            float r1 = (float) r6
            int r1 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x011d
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            boolean r1 = r1.isFirstInstall()
            if (r1 != 0) goto L_0x011d
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r15)
            r2.append(r13)
            java.lang.String r5 = r2.toString()
            r17 = 0
            java.lang.String r3 = "other"
            java.lang.String r4 = "data-found"
            r2 = r7
            r9 = 0
            r6 = r17
            r1.trackModule(r2, r3, r4, r5, r6)
            goto L_0x011e
        L_0x011d:
            r9 = 0
        L_0x011e:
            com.medscape.android.MedscapeApplication r1 = com.medscape.android.MedscapeApplication.get()
            java.lang.String r2 = "MedscapeApplication.get()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = r1.isBackgroundUpdateInProgress()
            if (r1 != 0) goto L_0x01bd
            float r1 = (float) r8
            int r3 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x0194
            int r3 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r3 < 0) goto L_0x013a
            int r3 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r3 >= 0) goto L_0x0194
        L_0x013a:
            com.medscape.android.MedscapeApplication r3 = com.medscape.android.MedscapeApplication.get()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            android.content.SharedPreferences r3 = r3.getPreferences()
            java.lang.String r4 = "pref_download_over_network"
            boolean r3 = r3.getBoolean(r4, r9)
            if (r3 != 0) goto L_0x015a
            com.medscape.android.MedscapeApplication r3 = com.medscape.android.MedscapeApplication.get()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            boolean r2 = r3.isWifiConnected()
            if (r2 == 0) goto L_0x0194
        L_0x015a:
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r15)
            r2.append(r13)
            java.lang.String r5 = r2.toString()
            r6 = 0
            java.lang.String r3 = "other"
            java.lang.String r4 = "data-start"
            r2 = r7
            r1.trackModule(r2, r3, r4, r5, r6)
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L_0x0188
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class<com.medscape.android.update.BackgroundDataUpdateService> r2 = com.medscape.android.update.BackgroundDataUpdateService.class
            r1.<init>(r7, r2)
            r0.startForegroundService(r1)
            goto L_0x0205
        L_0x0188:
            android.content.Intent r1 = new android.content.Intent
            java.lang.Class<com.medscape.android.update.BackgroundDataUpdateService> r2 = com.medscape.android.update.BackgroundDataUpdateService.class
            r1.<init>(r7, r2)
            r0.startService(r1)
            goto L_0x0205
        L_0x0194:
            int r1 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x01b5
            int r1 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x01a9
            r1 = 7
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r2 = r18.getHomeViewModel()
            java.lang.String r2 = r2.getUpdateMessage()
            r0.showDialog(r1, r2)
            goto L_0x0205
        L_0x01a9:
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            java.lang.String r1 = r1.getUpdateMessage()
            r0.showDialog(r14, r1)
            goto L_0x0205
        L_0x01b5:
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            r1.dataUpdateFinish()
            goto L_0x0205
        L_0x01bd:
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r2 = "clinicalInstalltionFail"
            java.lang.String r1 = r1.getSetting((java.lang.String) r2, (java.lang.String) r12)
            java.lang.String r2 = "1"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x01fe
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r7)
            java.lang.String r2 = "clinicalInstalletionPlistText"
            java.lang.String r1 = r1.getSetting((java.lang.String) r2, (java.lang.String) r15)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r15)
            r2 = 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x01fe
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            r1.setClinicalReferenceResume(r2)
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            com.medscape.android.updater.UpdateManager r1 = r1.getUpdateManager()
            r1.isVersionCheck = r2
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            com.medscape.android.updater.UpdateManager r1 = r1.getUpdateManager()
            r1.getReferencePList()
            goto L_0x0205
        L_0x01fe:
            com.medscape.android.homescreen.viewmodel.HomeScreenViewModel r1 = r18.getHomeViewModel()
            r1.dataUpdateFinish()
        L_0x0205:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.homescreen.views.HomeScreenActivity.onUpdateNotAvailable(int):void");
    }

    public void onNetworkError(int i) {
        if (i == 11) {
            getHomeViewModel().handleNetworkError();
        }
    }

    public void onBackPressed() {
        if (getNavigationDrawer().isDrawerOpen((int) GravityCompat.START)) {
            getNavigationDrawer().closeDrawer((int) GravityCompat.START);
            return;
        }
        super.onBackPressed();
        finishAffinity();
    }

    private final void updateClinicalReference() {
        Intent intent = new Intent(this, ClinicalReferenceInstallationRequestActivity.class);
        intent.putExtra("isResume", false);
        startActivityForResult(intent, 3);
        getHomeViewModel().setInActivityResultCallback(true);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenActivity$Companion;", "", "()V", "START_CLINICAL_REFERENCE_DOWNLOAD_REQUEST", "", "START_DRUG_UPDATE_REQUEST", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HomeScreenActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.d("API", "home activity onDestroy");
    }
}
