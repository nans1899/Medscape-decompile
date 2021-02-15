package com.medscape.android.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.adobe.mobile.Config;
import com.comscore.Analytics;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.ib.foreceup.util.Util;
import com.medscape.android.BI.BIManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.formulary.FormularyMyPlanPage;
import com.medscape.android.activity.formulary.IndexedDrugFormularyListActivity;
import com.medscape.android.activity.formulary.SelectBrandActivity;
import com.medscape.android.activity.search.SearchActivity;
import com.medscape.android.ads.AdPreLoadWebViewActivity;
import com.medscape.android.ads.AdWebView;
import com.medscape.android.ads.AdWebViewAcitivity;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPAdListener;
import com.medscape.android.ads.DFPNewsAdListener;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.analytics.NotificationAnalyticsHandler;
import com.medscape.android.consult.activity.ConsultPostActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.drugs.IndexedDrugListActivity;
import com.medscape.android.notifications.NotificationAuthenticationGateActivity;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceFolderActivity;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.util.BackgroundHelper;
import com.medscape.android.util.GDPRFailureLog;
import com.medscape.android.view.CustomWebView;
import com.medscape.android.webmdrx.activity.IndexedRxDrugListActivity;
import com.medscape.android.welcome.WelcomeActivity;
import com.wbmd.wbmddatacompliance.callbacks.IShowAcceptDialogCallback;
import com.wbmd.wbmddatacompliance.gdpr.GDPRStateManager;
import com.wbmd.wbmddatacompliance.utils.Constants;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {
    public static final int DIALOG_KEEP_ALL_ACTIVITIES = 9999;
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected DFPAdAction adAction;
    /* access modifiers changed from: protected */
    public View adLayout;
    protected PublisherAdView adView;
    protected View dfpAdLayout;
    /* access modifiers changed from: private */
    public BroadcastReceiver gdprReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent != null && (action = intent.getAction()) != null) {
                if (action.equals(Constants.BROADCAST_ACCEPT_ACTION)) {
                    OmnitureManager.get().trackModuleAbsolute(BaseActivity.this.getApplicationContext(), (String) null, "gdpr-accept", (String) null, (Map<String, Object>) null);
                } else if (action.equals(Constants.BROADCAST_ACTIVITY_VIEW)) {
                    OmnitureManager.get().trackPageView(BaseActivity.this.getApplicationContext(), (String) null, "gdpr-roadblock", (String) null, (String) null, (String) null, (Map<String, Object>) null);
                }
            }
        }
    };
    protected boolean isPause;
    private boolean isScreenVisible;
    protected boolean isSessionValid = true;
    protected BIManager mBIManager;
    private BroadcastReceiver mBroadcastReceiver;
    public BroadcastReceiver mForceupReceiver;
    public String mPvid;
    protected View mSearchFilterDropDown;
    protected PopupWindow mSearchFilterPopupWindow;
    /* access modifiers changed from: protected */
    public NativeAdAction nativeAdAction;
    protected boolean skipForceUpAutoRegister;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBIManager = new BIManager();
        Settings.singleton(this).saveSetting(com.medscape.android.Constants.PREF_IS_FULL_SCREEN_IS_IN_FRONT, ((this instanceof AdWebViewAcitivity) || (this instanceof AdPreLoadWebViewActivity)) ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CLOSE_ALL");
        AnonymousClass1 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                BaseActivity.this.finish();
            }
        };
        this.mBroadcastReceiver = r0;
        registerReceiver(r0, intentFilter);
        if (!this.skipForceUpAutoRegister) {
            registerForceUp();
        }
    }

    private void registerForceUp() {
        if (!(this instanceof WelcomeActivity) && !(this instanceof NotificationAuthenticationGateActivity)) {
            this.mForceupReceiver = Util.registerForceupReceiver(this);
        }
    }

    /* access modifiers changed from: protected */
    public void setupAd() {
        PublisherAdView publisherAdView = new PublisherAdView(this);
        this.adView = publisherAdView;
        if (this instanceof DFPAdListener) {
            publisherAdView.setAdUnitId(this instanceof DFPNewsAdListener ? DFPNewsAdListener.AD_UNIT_ID : DFPReferenceAdListener.AD_UNIT_ID);
            View findViewById = findViewById(R.id.ad);
            this.adLayout = findViewById;
            if (findViewById != null) {
                ((LinearLayout) findViewById).addView(this.adView);
            }
            this.adAction = new DFPAdAction(this, this.adLayout, this.adView);
        }
    }

    /* access modifiers changed from: protected */
    public void setupNativeBottomBannerAd(String str) {
        this.adLayout = findViewById(R.id.ad);
        this.dfpAdLayout = findViewById(R.id.dfp_adLayout);
        this.nativeAdAction = new NativeAdAction(this, str, this.dfpAdLayout);
        TextView textView = (TextView) findViewById(R.id.dfp_adLabel);
        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            textView.setBackgroundResource(R.color.black);
            textView.setPadding(0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.adView != null) {
                this.adView.destroy();
            }
            unregisterReceiver(this.mBroadcastReceiver);
            super.onDestroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.gdprReceiver);
        if (!(this instanceof WelcomeActivity) && !(this instanceof NotificationAuthenticationGateActivity)) {
            Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (motionEvent.getAction() == 0) {
                if (!(this.mSearchFilterPopupWindow == null || !this.mSearchFilterPopupWindow.isShowing() || this.mSearchFilterPopupWindow.getContentView() == null)) {
                    int[] iArr = new int[2];
                    this.mSearchFilterPopupWindow.getContentView().getLocationOnScreen(iArr);
                    if (!new Rect(iArr[0], iArr[1], iArr[0] + this.mSearchFilterPopupWindow.getContentView().getWidth(), iArr[1] + this.mSearchFilterPopupWindow.getContentView().getHeight()).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                        this.mSearchFilterPopupWindow.dismiss();
                        return true;
                    }
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.isScreenVisible = false;
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.pause();
        }
        super.onPause();
        Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        this.mForceupReceiver = null;
        Analytics.notifyExitForeground();
        Config.pauseCollectingLifecycleData();
        if (AdWebView.getInstance(this).getClassName() != null && !AdWebView.getInstance(this).getClassName().equals(getClass().getName())) {
            AdWebView.getInstance(this).setWebview((CustomWebView) null);
        }
        PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(com.medscape.android.Constants.PREF_LAST_PAUSE, System.currentTimeMillis()).apply();
        this.isPause = true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        boolean z;
        boolean z2;
        super.onResume();
        this.isScreenVisible = true;
        if (!this.skipForceUpAutoRegister && this.mForceupReceiver == null) {
            registerForceUp();
        } else if (this.skipForceUpAutoRegister) {
            this.skipForceUpAutoRegister = false;
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    BaseActivity.this.lambda$onResume$0$BaseActivity();
                }
            }, 500);
        }
        Analytics.notifyEnterForeground();
        OmnitureManager.get().collectOmnitureLifeCycleData(this);
        if (isDontKeepActivitiesFlagSet()) {
            showDialog(9999);
            this.mBIManager.startBI(this, "view", "developererror", "");
        }
        this.isPause = false;
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.resume();
        }
        if (!(this instanceof AdWebViewAcitivity) && !(this instanceof ClinicalReferenceFolderActivity) && !(this instanceof ClinicalReferenceArticleActivity) && !(this instanceof IndexedDrugListActivity) && !(this instanceof DrugMonographMainActivity) && !((z = this instanceof SelectBrandActivity)) && !(this instanceof IndexedDrugFormularyListActivity) && !z && !((z2 = this instanceof FormularyMyPlanPage)) && !(this instanceof SearchActivity) && !z2 && !(this instanceof ConsultTimelineActivity) && !(this instanceof ConsultPostActivity) && !(this instanceof IndexedRxDrugListActivity)) {
            overridePendingTransition(0, 0);
        }
        if (MedscapeApplication.get() != null) {
            SharedPreferences sharedPreferences = getSharedPreferences(UpdateManager.SETTINGS_PREFS, 0);
            if (!MedscapeApplication.get().isBackgroundUpdateInProgress() && sharedPreferences.getFloat("version", -1.0f) >= 0.0f && !MedscapeApplication.get().isDatabaseValid()) {
                Settings.singleton(this).saveSetting(com.medscape.android.Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
            sessionValidation(this);
        }
        if (!(this instanceof WelcomeActivity) && !(this instanceof NotificationAuthenticationGateActivity)) {
            new GDPRStateManager(getApplicationContext(), new GDPRFailureLog(this)).shouldShowAcceptancePrompt(new IShowAcceptDialogCallback() {
                public void shouldShowAcceptancePromptResult(boolean z) {
                    if (z) {
                        Intent gDPRRoadBlock = com.medscape.android.util.Util.getGDPRRoadBlock(BaseActivity.this);
                        LocalBroadcastManager.getInstance(BaseActivity.this.getApplicationContext()).registerReceiver(BaseActivity.this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACCEPT_ACTION));
                        LocalBroadcastManager.getInstance(BaseActivity.this.getApplicationContext()).registerReceiver(BaseActivity.this.gdprReceiver, new IntentFilter(Constants.BROADCAST_ACTIVITY_VIEW));
                        BaseActivity.this.startActivity(gDPRRoadBlock);
                        return;
                    }
                    OmnitureManager.get().setOmniturePrivacyOnly(true);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onResume$0$BaseActivity() {
        if (this.isScreenVisible) {
            registerForceUp();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        sendHomeBIPing();
        NotificationAnalyticsHandler.INSTANCE.openSession(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (BackgroundHelper.isAppInBackground(this)) {
            Settings.singleton(this).saveSetting("appBackground", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        NotificationAnalyticsHandler.INSTANCE.closeSession(this);
    }

    private void sendHomeBIPing() {
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(Settings.singleton(this).getSetting("appBackground", AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            Settings.singleton(this).saveSetting("appBackground", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            if (!risingFromColdStart()) {
                OmnitureManager.get().setmCurrentPageName((String) null);
                boolean z = this.isSessionValid;
                if (z) {
                    OmnitureManager.get().trackModule(this, "other", "launch", "bgrnd", (Map<String, Object>) null);
                } else if (!z && Settings.singleton(this).getSetting(com.medscape.android.Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                    OmnitureManager.get().trackModule(this, "other", "launch", com.medscape.android.Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                }
            }
            Settings.singleton(this).saveSetting(com.medscape.android.Constants.PREF_NEWS_SESSION_MARKER, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
    }

    /* access modifiers changed from: protected */
    public boolean risingFromColdStart() {
        return Settings.singleton(this).getSetting(com.medscape.android.Constants.PREF_NEWS_SESSION_MARKER, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    public void sessionValidation(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append("Running from: ");
        sb.append(getClass().getName());
        sb.append(". The time diff is: ");
        sb.append(System.currentTimeMillis() - MedscapeApplication.get().getPreferences().getLong(com.medscape.android.Constants.PREF_LAST_PAUSE, 0));
        sb.append(". Condition:");
        sb.append(System.currentTimeMillis() - MedscapeApplication.get().getPreferences().getLong(com.medscape.android.Constants.PREF_LAST_PAUSE, 0) > com.medscape.android.Constants.BG_UPDATE_TIME_IN_MILLIS);
        Log.d("RestartIsh", sb.toString());
        Log.d("RestartIsh", "Running from: " + getClass().getName() + ". Should restart session: " + Settings.singleton(activity).getSetting(com.medscape.android.Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES));
        if (System.currentTimeMillis() - MedscapeApplication.get().getPreferences().getLong(com.medscape.android.Constants.PREF_LAST_PAUSE, 0) > com.medscape.android.Constants.BG_UPDATE_TIME_IN_MILLIS || (Settings.singleton(activity).getSetting(com.medscape.android.Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) && !isDemoModeOn())) {
            this.isSessionValid = false;
            Settings.singleton(activity).saveSetting(com.medscape.android.Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            Settings.singleton(activity).saveSetting(com.medscape.android.Constants.PREF_SIX_HOUR_RESET_AD_BLOCKER, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            Settings.singleton(this).saveSetting(com.medscape.android.Constants.PREF_SEARCH_FILTER_CACHE, ExifInterface.GPS_MEASUREMENT_2D);
            Log.d("RestartIsh", "Running from: " + getClass().getName() + ". We are about to restart the activity.");
            startActivity(new Intent(activity, WelcomeActivity.class).setFlags(335544320).setAction("android.intent.action.MAIN"));
            activity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isSessionValid() {
        return this.isSessionValid;
    }

    public Dialog onCreateDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i != 9999) {
            return null;
        }
        return builder.setMessage("\"Don't Keep Activities\" is enabled in your Developer Settings.\n\nThis option must be turned off in order to use Medscape. Please go to Settings > Developer Options and uncheck \"Don't Keep Activities.\"").setCancelable(false).setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseActivity.this.mBIManager.startBI(BaseActivity.this, "click", "developererror", "");
                try {
                    BaseActivity.this.startActivityForResult(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"), 0);
                } catch (Throwable unused) {
                }
            }
        }).create();
    }

    public boolean isDontKeepActivitiesFlagSet() {
        if (Build.VERSION.SDK_INT <= 10) {
            return false;
        }
        new Settings.System();
        try {
            if (Settings.System.getInt(getContentResolver(), "always_finish_activities") == 1) {
                return true;
            }
            return false;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(charSequence);
        }
    }

    public boolean isDemoModeOn() {
        return MedscapeApplication.get().getPreferences().getBoolean(com.medscape.android.Constants.PREF_DEMO, false);
    }

    public String getCurrentPvid() {
        return this.mPvid;
    }

    public void setCurrentPvid(String str) {
        this.mPvid = str;
    }

    /* access modifiers changed from: protected */
    public void onAdNotAvilable() {
        View view = this.adLayout;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
