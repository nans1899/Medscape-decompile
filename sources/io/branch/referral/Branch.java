package io.branch.referral;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import com.facebook.internal.ServerProtocol;
import com.tapstream.sdk.http.RequestBuilders;
import io.branch.indexing.BranchUniversalObject;
import io.branch.indexing.ContentDiscoverer;
import io.branch.referral.BranchStrongMatchHelper;
import io.branch.referral.BranchViewHandler;
import io.branch.referral.DeferredAppLinkDataHandler;
import io.branch.referral.Defines;
import io.branch.referral.InstallListener;
import io.branch.referral.ServerRequest;
import io.branch.referral.SharingHelper;
import io.branch.referral.SystemObserver;
import io.branch.referral.network.BranchRemoteInterface;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.CommerceEvent;
import io.branch.referral.util.LinkProperties;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Branch implements BranchViewHandler.IBranchViewEvents, SystemObserver.GAdsParamsFetchEvents, InstallListener.IInstallReferrerEvents {
    public static final String ALWAYS_DEEPLINK = "$always_deeplink";
    private static final String AUTO_DEEP_LINKED = "io.branch.sdk.auto_linked";
    private static final String AUTO_DEEP_LINK_DISABLE = "io.branch.sdk.auto_link_disable";
    private static final String AUTO_DEEP_LINK_KEY = "io.branch.sdk.auto_link_keys";
    private static final String AUTO_DEEP_LINK_PATH = "io.branch.sdk.auto_link_path";
    private static final String AUTO_DEEP_LINK_REQ_CODE = "io.branch.sdk.auto_link_request_code";
    public static final String DEEPLINK_PATH = "$deeplink_path";
    private static final int DEF_AUTO_DEEP_LINK_REQ_CODE = 1501;
    private static final String[] EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST = {"extra_launch_uri", "branch_intent"};
    private static final String FABRIC_BRANCH_API_KEY = "io.branch.apiKey";
    public static final String FEATURE_TAG_DEAL = "deal";
    public static final String FEATURE_TAG_GIFT = "gift";
    public static final String FEATURE_TAG_INVITE = "invite";
    public static final String FEATURE_TAG_REFERRAL = "referral";
    public static final String FEATURE_TAG_SHARE = "share";
    private static int LATCH_WAIT_UNTIL = 2500;
    public static final int LINK_TYPE_ONE_TIME_USE = 1;
    public static final int LINK_TYPE_UNLIMITED_USE = 0;
    public static final long NO_PLAY_STORE_REFERRER_WAIT = 0;
    public static final String OG_APP_ID = "$og_app_id";
    public static final String OG_DESC = "$og_description";
    public static final String OG_IMAGE_URL = "$og_image_url";
    public static final String OG_TITLE = "$og_title";
    public static final String OG_URL = "$og_url";
    public static final String OG_VIDEO = "$og_video";
    private static final int PREVENT_CLOSE_TIMEOUT = 500;
    public static final String REDEEM_CODE = "$redeem_code";
    public static final String REDIRECT_ANDROID_URL = "$android_url";
    public static final String REDIRECT_BLACKBERRY_URL = "$blackberry_url";
    public static final String REDIRECT_DESKTOP_URL = "$desktop_url";
    public static final String REDIRECT_FIRE_URL = "$fire_url";
    public static final String REDIRECT_IOS_URL = "$ios_url";
    public static final String REDIRECT_IPAD_URL = "$ipad_url";
    public static final String REDIRECT_WINDOWS_PHONE_URL = "$windows_phone_url";
    public static final String REFERRAL_BUCKET_DEFAULT = "default";
    public static final String REFERRAL_CODE = "referral_code";
    public static final int REFERRAL_CODE_AWARD_UNIQUE = 0;
    public static final int REFERRAL_CODE_AWARD_UNLIMITED = 1;
    public static final int REFERRAL_CODE_LOCATION_BOTH = 3;
    public static final int REFERRAL_CODE_LOCATION_REFERREE = 0;
    public static final int REFERRAL_CODE_LOCATION_REFERRING_USER = 2;
    public static final String REFERRAL_CODE_TYPE = "credit";
    public static final int REFERRAL_CREATION_SOURCE_SDK = 2;
    private static final int SESSION_KEEPALIVE = 2000;
    private static final String TAG = "BranchSDK";
    /* access modifiers changed from: private */
    public static Branch branchReferral_ = null;
    static boolean checkInstallReferrer_ = true;
    private static String cookieBasedMatchDomain_ = "app.link";
    private static CUSTOM_REFERRABLE_SETTINGS customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private static boolean disableDeviceIDFetch_ = false;
    private static boolean disableInstantDeepLinking = true;
    private static boolean isActivityLifeCycleCallbackRegistered_ = false;
    private static boolean isAutoSessionMode_ = false;
    static boolean isForcedSession_ = false;
    static Boolean isLogging_ = null;
    static boolean isSimulatingInstalls_ = false;
    private static long playStoreReferrerFetchTime = 1500;
    /* access modifiers changed from: private */
    public BranchRemoteInterface branchRemoteInterface_;
    /* access modifiers changed from: private */
    public Context context_;
    WeakReference<Activity> currentActivityReference_;
    private JSONObject deeplinkDebugParams_;
    private boolean enableFacebookAppLinkCheck_ = false;
    /* access modifiers changed from: private */
    public CountDownLatch getFirstReferringParamsLatch = null;
    /* access modifiers changed from: private */
    public CountDownLatch getLatestReferringParamsLatch = null;
    /* access modifiers changed from: private */
    public boolean handleDelayedNewIntents_ = false;
    /* access modifiers changed from: private */
    public boolean hasNetwork_;
    /* access modifiers changed from: private */
    public SESSION_STATE initState_ = SESSION_STATE.UNINITIALISED;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, String> instrumentationExtraData_;
    /* access modifiers changed from: private */
    public INTENT_STATE intentState_ = INTENT_STATE.PENDING;
    /* access modifiers changed from: private */
    public boolean isActivityCreatedAndLaunched = false;
    private boolean isGAParamsFetchInProgress_ = false;
    boolean isInitReportedThroughCallBack = false;
    boolean isInstantDeepLinkPossible = false;
    /* access modifiers changed from: private */
    public Map<BranchLinkData, String> linkCache_;
    final Object lock;
    /* access modifiers changed from: private */
    public int networkCount_;
    private boolean performCookieBasedStrongMatchingOnGAIDAvailable = false;
    /* access modifiers changed from: private */
    public PrefHelper prefHelper_;
    /* access modifiers changed from: private */
    public final ServerRequestQueue requestQueue_;
    private Semaphore serverSema_;
    String sessionReferredLink_;
    /* access modifiers changed from: private */
    public ShareLinkManager shareLinkManager_;
    private final SystemObserver systemObserver_;
    private final TrackingController trackingController;

    public interface BranchLinkCreateListener {
        void onLinkCreate(String str, BranchError branchError);
    }

    public interface BranchLinkShareListener {
        void onChannelSelected(String str);

        void onLinkShareResponse(String str, String str2, BranchError branchError);

        void onShareLinkDialogDismissed();

        void onShareLinkDialogLaunched();
    }

    public interface BranchListResponseListener {
        void onReceivingResponse(JSONArray jSONArray, BranchError branchError);
    }

    public interface BranchReferralInitListener {
        void onInitFinished(JSONObject jSONObject, BranchError branchError);
    }

    public interface BranchReferralStateChangedListener {
        void onStateChanged(boolean z, BranchError branchError);
    }

    public interface BranchUniversalReferralInitListener {
        void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError branchError);
    }

    private enum CUSTOM_REFERRABLE_SETTINGS {
        USE_DEFAULT,
        REFERRABLE,
        NON_REFERRABLE
    }

    public enum CreditHistoryOrder {
        kMostRecentFirst,
        kLeastRecentFirst
    }

    public interface ExtendedBranchLinkShareListener extends BranchLinkShareListener {
        boolean onChannelSelected(String str, BranchUniversalObject branchUniversalObject, LinkProperties linkProperties);
    }

    public interface IBranchViewControl {
        boolean skipBranchViewsOnThisActivity();
    }

    public interface IChannelProperties {
        String getSharingMessageForChannel(String str);

        String getSharingTitleForChannel(String str);
    }

    private enum INTENT_STATE {
        PENDING,
        READY
    }

    public interface LogoutStatusListener {
        void onLogoutFinished(boolean z, BranchError branchError);
    }

    private enum SESSION_STATE {
        INITIALISED,
        INITIALISING,
        UNINITIALISED
    }

    public void disableAppList() {
    }

    public void onBranchViewVisible(String str, String str2) {
    }

    private Branch(Context context) {
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.trackingController = new TrackingController(context);
        this.branchRemoteInterface_ = BranchRemoteInterface.getDefaultBranchRemoteInterface(context);
        this.systemObserver_ = new SystemObserver(context);
        this.requestQueue_ = ServerRequestQueue.getInstance(context);
        this.serverSema_ = new Semaphore(1);
        this.lock = new Object();
        this.networkCount_ = 0;
        this.hasNetwork_ = true;
        this.linkCache_ = new HashMap();
        this.instrumentationExtraData_ = new ConcurrentHashMap<>();
        if (!this.trackingController.isTrackingDisabled()) {
            this.isGAParamsFetchInProgress_ = this.systemObserver_.prefetchGAdsParams(this);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            this.handleDelayedNewIntents_ = true;
            this.intentState_ = INTENT_STATE.PENDING;
            return;
        }
        this.handleDelayedNewIntents_ = false;
        this.intentState_ = INTENT_STATE.READY;
    }

    public Context getApplicationContext() {
        return this.context_;
    }

    public void setBranchRemoteInterface(BranchRemoteInterface branchRemoteInterface) {
        this.branchRemoteInterface_ = branchRemoteInterface;
    }

    public static void enableTestMode() {
        BranchUtil.isCustomDebugEnabled_ = true;
    }

    public static void disableTestMode() {
        BranchUtil.isCustomDebugEnabled_ = false;
    }

    public void setDebug() {
        enableTestMode();
    }

    public void disableTracking(boolean z) {
        this.trackingController.disableTracking(this.context_, z);
    }

    public boolean isTrackingDisabled() {
        return this.trackingController.isTrackingDisabled();
    }

    public static void enablePlayStoreReferrer(long j) {
        setPlayStoreReferrerCheckTimeout(j);
    }

    public static void setPlayStoreReferrerCheckTimeout(long j) {
        checkInstallReferrer_ = j > 0;
        playStoreReferrerFetchTime = j;
    }

    public static void disableInstantDeepLinking(boolean z) {
        disableInstantDeepLinking = z;
    }

    public static Branch getInstance() {
        if (branchReferral_ == null) {
            Log.e(TAG, "Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (isAutoSessionMode_ && !isActivityLifeCycleCallbackRegistered_) {
            Log.e(TAG, "Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return branchReferral_;
    }

    public static Branch getInstance(Context context, String str) {
        if (branchReferral_ == null) {
            branchReferral_ = initInstance(context);
        }
        branchReferral_.context_ = context.getApplicationContext();
        if (!str.startsWith("key_")) {
            Log.e(TAG, "Branch Key is invalid.Please check your BranchKey");
        } else if (branchReferral_.prefHelper_.setBranchKey(str)) {
            branchReferral_.linkCache_.clear();
            branchReferral_.requestQueue_.clear();
        }
        return branchReferral_;
    }

    private static Branch getBranchInstance(Context context, boolean z, String str) {
        boolean z2;
        if (branchReferral_ == null) {
            branchReferral_ = initInstance(context);
            if (TextUtils.isEmpty(str)) {
                str = branchReferral_.prefHelper_.readBranchKey(z);
            }
            if (str == null || str.equalsIgnoreCase("bnc_no_value")) {
                String str2 = null;
                try {
                    Resources resources = context.getResources();
                    str2 = resources.getString(resources.getIdentifier(FABRIC_BRANCH_API_KEY, "string", context.getPackageName()));
                } catch (Exception unused) {
                }
                if (!TextUtils.isEmpty(str2)) {
                    z2 = branchReferral_.prefHelper_.setBranchKey(str2);
                } else {
                    Log.i(TAG, "Branch Warning: Please enter your branch_key in your project's Manifest file!");
                    z2 = branchReferral_.prefHelper_.setBranchKey("bnc_no_value");
                }
            } else {
                z2 = branchReferral_.prefHelper_.setBranchKey(str);
            }
            if (z2) {
                branchReferral_.linkCache_.clear();
                branchReferral_.requestQueue_.clear();
            }
            branchReferral_.context_ = context.getApplicationContext();
            if (context instanceof Application) {
                isAutoSessionMode_ = true;
                branchReferral_.setActivityLifeCycleObserver((Application) context);
            }
        }
        return branchReferral_;
    }

    public static Branch getInstance(Context context) {
        return getBranchInstance(context, true, (String) null);
    }

    public static Branch getTestInstance(Context context) {
        return getBranchInstance(context, false, (String) null);
    }

    public static Branch getAutoInstance(Context context) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        getBranchInstance(context, true ^ BranchUtil.isTestModeEnabled(context), (String) null);
        return branchReferral_;
    }

    public static Branch getAutoInstance(Context context, boolean z) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = z ? CUSTOM_REFERRABLE_SETTINGS.REFERRABLE : CUSTOM_REFERRABLE_SETTINGS.NON_REFERRABLE;
        getBranchInstance(context, !BranchUtil.isTestModeEnabled(context), (String) null);
        return branchReferral_;
    }

    public static Branch getAutoInstance(Context context, String str) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        getBranchInstance(context, true ^ BranchUtil.isTestModeEnabled(context), str);
        if (!str.startsWith("key_")) {
            Log.e(TAG, "Branch Key is invalid.Please check your BranchKey");
        } else if (branchReferral_.prefHelper_.setBranchKey(str)) {
            branchReferral_.linkCache_.clear();
            branchReferral_.requestQueue_.clear();
        }
        return branchReferral_;
    }

    public static Branch getAutoTestInstance(Context context) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        getBranchInstance(context, false, (String) null);
        return branchReferral_;
    }

    public static Branch getAutoTestInstance(Context context, boolean z) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = z ? CUSTOM_REFERRABLE_SETTINGS.REFERRABLE : CUSTOM_REFERRABLE_SETTINGS.NON_REFERRABLE;
        getBranchInstance(context, false, (String) null);
        return branchReferral_;
    }

    private static Branch initInstance(Context context) {
        return new Branch(context.getApplicationContext());
    }

    public void resetUserSession() {
        this.initState_ = SESSION_STATE.UNINITIALISED;
    }

    public void setRetryCount(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper != null && i >= 0) {
            prefHelper.setRetryCount(i);
        }
    }

    public void setRetryInterval(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper != null && i > 0) {
            prefHelper.setRetryInterval(i);
        }
    }

    public void setNetworkTimeout(int i) {
        PrefHelper prefHelper = this.prefHelper_;
        if (prefHelper != null && i > 0) {
            prefHelper.setTimeout(i);
        }
    }

    public static void disableDeviceIDFetch(Boolean bool) {
        disableDeviceIDFetch_ = bool.booleanValue();
    }

    public static boolean isDeviceIDFetchDisabled() {
        return disableDeviceIDFetch_;
    }

    public void setDeepLinkDebugMode(JSONObject jSONObject) {
        this.deeplinkDebugParams_ = jSONObject;
    }

    public void enableFacebookAppLinkCheck() {
        this.enableFacebookAppLinkCheck_ = true;
    }

    public void setLimitFacebookTracking(boolean z) {
        this.prefHelper_.setLimitFacebookTracking(z);
    }

    public void setRequestMetadata(String str, String str2) {
        this.prefHelper_.setRequestMetadata(str, str2);
    }

    public Branch addInstallMetadata(String str, String str2) {
        this.prefHelper_.addInstallMetadata(str, str2);
        return this;
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener) {
        return initSession(branchUniversalReferralInitListener, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener) {
        return initSession(branchReferralInitListener, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(branchUniversalReferralInitListener, activity, true);
        } else {
            initUserSessionInternal(branchUniversalReferralInitListener, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(branchReferralInitListener, activity, true);
        } else {
            initUserSessionInternal(branchReferralInitListener, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, Uri uri) {
        return initSession(branchUniversalReferralInitListener, uri, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, Uri uri) {
        return initSession(branchReferralInitListener, uri, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        initSession(branchUniversalReferralInitListener, activity);
        return true;
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        return initSession(branchReferralInitListener, activity);
    }

    public boolean initSession() {
        return initSession((Activity) null);
    }

    public boolean initSession(Activity activity) {
        return initSession((BranchReferralInitListener) null, activity);
    }

    public boolean initSessionForced(BranchReferralInitListener branchReferralInitListener) {
        enableForcedSession();
        if (!initSession(branchReferralInitListener, (Activity) null)) {
            return false;
        }
        processNextQueueItem();
        return true;
    }

    public boolean initSessionWithData(Uri uri) {
        return initSessionWithData(uri, (Activity) null);
    }

    public boolean initSessionWithData(Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        return initSession((BranchReferralInitListener) null, activity);
    }

    public boolean initSession(boolean z) {
        return initSession((BranchReferralInitListener) null, z, (Activity) null);
    }

    public boolean initSession(boolean z, Activity activity) {
        return initSession((BranchReferralInitListener) null, z, activity);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, boolean z, Uri uri) {
        return initSession(branchUniversalReferralInitListener, z, uri, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, boolean z, Uri uri) {
        return initSession(branchReferralInitListener, z, uri, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, boolean z, Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        return initSession(branchUniversalReferralInitListener, z, activity);
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, boolean z, Uri uri, Activity activity) {
        readAndStripParam(uri, activity);
        return initSession(branchReferralInitListener, z, activity);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, boolean z) {
        return initSession(branchUniversalReferralInitListener, z, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, boolean z) {
        return initSession(branchReferralInitListener, z, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener branchUniversalReferralInitListener, boolean z, Activity activity) {
        initUserSessionInternal(branchUniversalReferralInitListener, activity, z);
        return true;
    }

    public boolean initSession(BranchReferralInitListener branchReferralInitListener, boolean z, Activity activity) {
        initUserSessionInternal(branchReferralInitListener, activity, z);
        return true;
    }

    private void initUserSessionInternal(BranchUniversalReferralInitListener branchUniversalReferralInitListener, Activity activity, boolean z) {
        initUserSessionInternal((BranchReferralInitListener) new BranchUniversalReferralInitWrapper(branchUniversalReferralInitListener), activity, z);
    }

    private void initUserSessionInternal(BranchReferralInitListener branchReferralInitListener, Activity activity, boolean z) {
        if (activity != null) {
            this.currentActivityReference_ = new WeakReference<>(activity);
        }
        if (!hasUser() || !hasSession() || this.initState_ != SESSION_STATE.INITIALISED) {
            if (this.isInstantDeepLinkPossible && reportInitSession(branchReferralInitListener)) {
                addExtraInstrumentationData(Defines.Jsonkey.InstantDeepLinkSession.getKey(), ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                this.isInstantDeepLinkPossible = false;
                checkForAutoDeepLinkConfiguration();
            }
            if (z) {
                this.prefHelper_.setIsReferrable();
            } else {
                this.prefHelper_.clearIsReferrable();
            }
            if (this.initState_ != SESSION_STATE.INITIALISING) {
                this.initState_ = SESSION_STATE.INITIALISING;
                initializeSession(branchReferralInitListener);
            } else if (branchReferralInitListener != null) {
                this.requestQueue_.setInstallOrOpenCallback(branchReferralInitListener);
            }
        } else {
            reportInitSession(branchReferralInitListener);
            this.isInstantDeepLinkPossible = false;
        }
    }

    private boolean reportInitSession(BranchReferralInitListener branchReferralInitListener) {
        if (branchReferralInitListener != null) {
            if (!isAutoSessionMode_) {
                branchReferralInitListener.onInitFinished(new JSONObject(), (BranchError) null);
            } else if (!this.isInitReportedThroughCallBack) {
                branchReferralInitListener.onInitFinished(getLatestReferringParams(), (BranchError) null);
                this.isInitReportedThroughCallBack = true;
            } else {
                branchReferralInitListener.onInitFinished(new JSONObject(), (BranchError) null);
            }
        }
        return this.isInitReportedThroughCallBack;
    }

    public void closeSession() {
        Log.w(TAG, "closeSession() method is deprecated from SDK v1.14.6.Session is  automatically handled by Branch.In case you need to handle sessions manually inorder to support minimum sdk version less than 14 please consider using  SDK version 1.14.5");
    }

    /* access modifiers changed from: package-private */
    public void closeSessionInternal() {
        executeClose();
        this.sessionReferredLink_ = null;
        this.trackingController.updateTrackingState(this.context_);
    }

    /* access modifiers changed from: package-private */
    public void clearPendingRequests() {
        this.requestQueue_.clear();
    }

    public static void enableCookieBasedMatching(String str) {
        cookieBasedMatchDomain_ = str;
    }

    public static void enableCookieBasedMatching(String str, int i) {
        cookieBasedMatchDomain_ = str;
        BranchStrongMatchHelper.getInstance().setStrongMatchUrlHitDelay(i);
    }

    private void executeClose() {
        if (this.initState_ != SESSION_STATE.UNINITIALISED) {
            if (!this.hasNetwork_) {
                ServerRequest peek = this.requestQueue_.peek();
                if ((peek != null && (peek instanceof ServerRequestRegisterInstall)) || (peek instanceof ServerRequestRegisterOpen)) {
                    this.requestQueue_.dequeue();
                }
            } else if (!this.requestQueue_.containsClose()) {
                handleNewRequest(new ServerRequestRegisterClose(this.context_));
            }
            this.initState_ = SESSION_STATE.UNINITIALISED;
        }
    }

    private boolean readAndStripParam(Uri uri, Activity activity) {
        String str;
        String string;
        if (!disableInstantDeepLinking && !((this.intentState_ != INTENT_STATE.READY && !this.isActivityCreatedAndLaunched) || activity == null || activity.getIntent() == null || this.initState_ == SESSION_STATE.INITIALISED || checkIntentForSessionRestart(activity.getIntent()))) {
            Intent intent = activity.getIntent();
            if (intent.getData() == null || (!this.isActivityCreatedAndLaunched && isIntentParamsAlreadyConsumed(activity))) {
                if (!this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(Defines.Jsonkey.Clicked_Branch_Link.getKey(), false);
                        jSONObject.put(Defines.Jsonkey.IsFirstSession.getKey(), false);
                        this.prefHelper_.setSessionParams(jSONObject.toString());
                        this.isInstantDeepLinkPossible = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (!TextUtils.isEmpty(intent.getStringExtra(Defines.Jsonkey.BranchData.getKey()))) {
                try {
                    JSONObject jSONObject2 = new JSONObject(intent.getStringExtra(Defines.Jsonkey.BranchData.getKey()));
                    jSONObject2.put(Defines.Jsonkey.Clicked_Branch_Link.getKey(), true);
                    this.prefHelper_.setSessionParams(jSONObject2.toString());
                    this.isInstantDeepLinkPossible = true;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                intent.removeExtra(Defines.Jsonkey.BranchData.getKey());
                activity.setIntent(intent);
            } else if (uri.getQueryParameterNames() != null && Boolean.valueOf(uri.getQueryParameter(Defines.Jsonkey.Instant.getKey())).booleanValue()) {
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    for (String next : uri.getQueryParameterNames()) {
                        jSONObject3.put(next, uri.getQueryParameter(next));
                    }
                    jSONObject3.put(Defines.Jsonkey.Clicked_Branch_Link.getKey(), true);
                    this.prefHelper_.setSessionParams(jSONObject3.toString());
                    this.isInstantDeepLinkPossible = true;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (this.intentState_ == INTENT_STATE.READY) {
            if (uri != null) {
                try {
                    if (!isIntentParamsAlreadyConsumed(activity)) {
                        String strippedURL = UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString());
                        this.sessionReferredLink_ = strippedURL;
                        this.prefHelper_.setExternalIntentUri(strippedURL);
                        if (!(strippedURL == null || !strippedURL.equals(uri.toString()) || activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
                            Bundle extras = activity.getIntent().getExtras();
                            Set keySet = extras.keySet();
                            if (keySet.size() > 0) {
                                JSONObject jSONObject4 = new JSONObject();
                                for (String str2 : EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST) {
                                    if (keySet.contains(str2)) {
                                        jSONObject4.put(str2, extras.get(str2));
                                    }
                                }
                                if (jSONObject4.length() > 0) {
                                    this.prefHelper_.setExternalIntentExtra(jSONObject4.toString());
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (activity != null) {
                try {
                    if (!(activity.getIntent() == null || activity.getIntent().getExtras() == null || isIntentParamsAlreadyConsumed(activity) || (string = activity.getIntent().getExtras().getString(Defines.Jsonkey.AndroidPushNotificationKey.getKey())) == null || string.length() <= 0)) {
                        this.prefHelper_.setPushIdentifier(string);
                        Intent intent2 = activity.getIntent();
                        intent2.putExtra(Defines.Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent2);
                        return false;
                    }
                } catch (Exception unused2) {
                }
            }
            if (uri != null && uri.isHierarchical() && activity != null && !isActivityLaunchedFromHistory(activity)) {
                try {
                    if (uri.getQueryParameter(Defines.Jsonkey.LinkClickID.getKey()) != null) {
                        this.prefHelper_.setLinkClickIdentifier(uri.getQueryParameter(Defines.Jsonkey.LinkClickID.getKey()));
                        String str3 = "link_click_id=" + uri.getQueryParameter(Defines.Jsonkey.LinkClickID.getKey());
                        String str4 = null;
                        if (activity.getIntent() != null) {
                            str4 = activity.getIntent().getDataString();
                        }
                        if (uri.getQuery().length() == str3.length()) {
                            str = "\\?" + str3;
                        } else {
                            if (str4 != null) {
                                if (str4.length() - str3.length() == str4.indexOf(str3)) {
                                    str = "&" + str3;
                                }
                            }
                            str = str3 + "&";
                        }
                        if (str4 != null) {
                            activity.getIntent().setData(Uri.parse(str4.replaceFirst(str, "")));
                            activity.getIntent().putExtra(Defines.Jsonkey.BranchLinkUsed.getKey(), true);
                        } else {
                            Log.w(TAG, "Branch Warning. URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                        }
                        return true;
                    }
                    String scheme = uri.getScheme();
                    Intent intent3 = activity.getIntent();
                    if (!(scheme == null || intent3 == null || ((!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase(RequestBuilders.DEFAULT_SCHEME)) || uri.getHost() == null || uri.getHost().length() <= 0 || isIntentParamsAlreadyConsumed(activity)))) {
                        if (uri.toString().equalsIgnoreCase(UniversalResourceAnalyser.getInstance(this.context_).getStrippedURL(uri.toString()))) {
                            this.prefHelper_.setAppLink(uri.toString());
                        }
                        intent3.putExtra(Defines.Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent3);
                    }
                } catch (Exception unused3) {
                }
            }
        }
        return false;
    }

    private boolean isIntentParamsAlreadyConsumed(Activity activity) {
        return (activity == null || activity.getIntent() == null || !activity.getIntent().getBooleanExtra(Defines.Jsonkey.BranchLinkUsed.getKey(), false)) ? false : true;
    }

    private boolean isActivityLaunchedFromHistory(Activity activity) {
        return (activity == null || activity.getIntent() == null || (activity.getIntent().getFlags() & 1048576) == 0) ? false : true;
    }

    public void onGAdsFetchFinished() {
        this.isGAParamsFetchInProgress_ = false;
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        if (this.performCookieBasedStrongMatchingOnGAIDAvailable) {
            performCookieBasedStrongMatch();
            this.performCookieBasedStrongMatchingOnGAIDAvailable = false;
            return;
        }
        processNextQueueItem();
    }

    public void onInstallReferrerEventsFinished() {
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
        processNextQueueItem();
    }

    public Branch addWhiteListedScheme(String str) {
        if (str != null) {
            UniversalResourceAnalyser.getInstance(this.context_).addToAcceptURLFormats(str);
        }
        return this;
    }

    public Branch setWhiteListedSchemes(List<String> list) {
        if (list != null) {
            UniversalResourceAnalyser.getInstance(this.context_).addToAcceptURLFormats(list);
        }
        return this;
    }

    public Branch addUriHostsToSkip(String str) {
        if (!TextUtils.isEmpty(str)) {
            UniversalResourceAnalyser.getInstance(this.context_).addToSkipURLFormats(str);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void updateSkipURLFormats() {
        UniversalResourceAnalyser.getInstance(this.context_).checkAndUpdateSkipURLFormats(this.context_);
    }

    public void setIdentity(String str) {
        setIdentity(str, (BranchReferralInitListener) null);
    }

    public void setIdentity(String str, BranchReferralInitListener branchReferralInitListener) {
        ServerRequestIdentifyUserRequest serverRequestIdentifyUserRequest = new ServerRequestIdentifyUserRequest(this.context_, branchReferralInitListener, str);
        if (serverRequestIdentifyUserRequest.constructError_ || serverRequestIdentifyUserRequest.handleErrors(this.context_)) {
            ServerRequestIdentifyUserRequest serverRequestIdentifyUserRequest2 = serverRequestIdentifyUserRequest;
            if (serverRequestIdentifyUserRequest2.isExistingID()) {
                serverRequestIdentifyUserRequest2.handleUserExist(branchReferral_);
                return;
            }
            return;
        }
        handleNewRequest(serverRequestIdentifyUserRequest);
    }

    public boolean isUserIdentified() {
        return !this.prefHelper_.getIdentity().equals("bnc_no_value");
    }

    public void logout() {
        logout((LogoutStatusListener) null);
    }

    public void logout(LogoutStatusListener logoutStatusListener) {
        ServerRequestLogout serverRequestLogout = new ServerRequestLogout(this.context_, logoutStatusListener);
        if (!serverRequestLogout.constructError_ && !serverRequestLogout.handleErrors(this.context_)) {
            handleNewRequest(serverRequestLogout);
        }
    }

    public void loadRewards() {
        loadRewards((BranchReferralStateChangedListener) null);
    }

    public void loadRewards(BranchReferralStateChangedListener branchReferralStateChangedListener) {
        ServerRequestGetRewards serverRequestGetRewards = new ServerRequestGetRewards(this.context_, branchReferralStateChangedListener);
        if (!serverRequestGetRewards.constructError_ && !serverRequestGetRewards.handleErrors(this.context_)) {
            handleNewRequest(serverRequestGetRewards);
        }
    }

    public int getCredits() {
        return this.prefHelper_.getCreditCount();
    }

    public int getCreditsForBucket(String str) {
        return this.prefHelper_.getCreditCount(str);
    }

    public void redeemRewards(int i) {
        redeemRewards(Defines.Jsonkey.DefaultBucket.getKey(), i, (BranchReferralStateChangedListener) null);
    }

    public void redeemRewards(int i, BranchReferralStateChangedListener branchReferralStateChangedListener) {
        redeemRewards(Defines.Jsonkey.DefaultBucket.getKey(), i, branchReferralStateChangedListener);
    }

    public void redeemRewards(String str, int i) {
        redeemRewards(str, i, (BranchReferralStateChangedListener) null);
    }

    public void redeemRewards(String str, int i, BranchReferralStateChangedListener branchReferralStateChangedListener) {
        ServerRequestRedeemRewards serverRequestRedeemRewards = new ServerRequestRedeemRewards(this.context_, str, i, branchReferralStateChangedListener);
        if (!serverRequestRedeemRewards.constructError_ && !serverRequestRedeemRewards.handleErrors(this.context_)) {
            handleNewRequest(serverRequestRedeemRewards);
        }
    }

    public void getCreditHistory(BranchListResponseListener branchListResponseListener) {
        getCreditHistory((String) null, (String) null, 100, CreditHistoryOrder.kMostRecentFirst, branchListResponseListener);
    }

    public void getCreditHistory(String str, BranchListResponseListener branchListResponseListener) {
        getCreditHistory(str, (String) null, 100, CreditHistoryOrder.kMostRecentFirst, branchListResponseListener);
    }

    public void getCreditHistory(String str, int i, CreditHistoryOrder creditHistoryOrder, BranchListResponseListener branchListResponseListener) {
        getCreditHistory((String) null, str, i, creditHistoryOrder, branchListResponseListener);
    }

    public void getCreditHistory(String str, String str2, int i, CreditHistoryOrder creditHistoryOrder, BranchListResponseListener branchListResponseListener) {
        ServerRequestGetRewardHistory serverRequestGetRewardHistory = new ServerRequestGetRewardHistory(this.context_, str, str2, i, creditHistoryOrder, branchListResponseListener);
        if (!serverRequestGetRewardHistory.constructError_ && !serverRequestGetRewardHistory.handleErrors(this.context_)) {
            handleNewRequest(serverRequestGetRewardHistory);
        }
    }

    public void userCompletedAction(String str, JSONObject jSONObject) {
        userCompletedAction(str, jSONObject, (BranchViewHandler.IBranchViewEvents) null);
    }

    public void userCompletedAction(String str) {
        userCompletedAction(str, (JSONObject) null, (BranchViewHandler.IBranchViewEvents) null);
    }

    public void userCompletedAction(String str, BranchViewHandler.IBranchViewEvents iBranchViewEvents) {
        userCompletedAction(str, (JSONObject) null, iBranchViewEvents);
    }

    public void userCompletedAction(String str, JSONObject jSONObject, BranchViewHandler.IBranchViewEvents iBranchViewEvents) {
        ServerRequestActionCompleted serverRequestActionCompleted = new ServerRequestActionCompleted(this.context_, str, jSONObject, iBranchViewEvents);
        if (!serverRequestActionCompleted.constructError_ && !serverRequestActionCompleted.handleErrors(this.context_)) {
            handleNewRequest(serverRequestActionCompleted);
        }
    }

    public void sendCommerceEvent(CommerceEvent commerceEvent, JSONObject jSONObject, BranchViewHandler.IBranchViewEvents iBranchViewEvents) {
        ServerRequestRActionCompleted serverRequestRActionCompleted = new ServerRequestRActionCompleted(this.context_, commerceEvent, jSONObject, iBranchViewEvents);
        if (!serverRequestRActionCompleted.constructError_ && !serverRequestRActionCompleted.handleErrors(this.context_)) {
            handleNewRequest(serverRequestRActionCompleted);
        }
    }

    public void sendCommerceEvent(CommerceEvent commerceEvent) {
        sendCommerceEvent(commerceEvent, (JSONObject) null, (BranchViewHandler.IBranchViewEvents) null);
    }

    public JSONObject getFirstReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
    }

    public JSONObject getFirstReferringParamsSync() {
        this.getFirstReferringParamsLatch = new CountDownLatch(1);
        if (this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
            try {
                this.getFirstReferringParamsLatch.await((long) LATCH_WAIT_UNTIL, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
            }
        }
        JSONObject appendDebugParams = appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
        this.getFirstReferringParamsLatch = null;
        return appendDebugParams;
    }

    public JSONObject getLatestReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
    }

    public JSONObject getLatestReferringParamsSync() {
        this.getLatestReferringParamsLatch = new CountDownLatch(1);
        try {
            if (this.initState_ != SESSION_STATE.INITIALISED) {
                this.getLatestReferringParamsLatch.await((long) LATCH_WAIT_UNTIL, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException unused) {
        }
        JSONObject appendDebugParams = appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
        this.getLatestReferringParamsLatch = null;
        return appendDebugParams;
    }

    private JSONObject appendDebugParams(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (this.deeplinkDebugParams_ != null) {
                    if (this.deeplinkDebugParams_.length() > 0) {
                        Log.w(TAG, "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator<String> keys = this.deeplinkDebugParams_.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject.put(next, this.deeplinkDebugParams_.get(next));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public JSONObject getDeeplinkDebugParams() {
        JSONObject jSONObject = this.deeplinkDebugParams_;
        if (jSONObject != null && jSONObject.length() > 0) {
            Log.w(TAG, "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
        }
        return this.deeplinkDebugParams_;
    }

    /* access modifiers changed from: package-private */
    public String generateShortLinkInternal(ServerRequestCreateUrl serverRequestCreateUrl) {
        if (serverRequestCreateUrl.constructError_ || serverRequestCreateUrl.handleErrors(this.context_)) {
            return null;
        }
        if (this.linkCache_.containsKey(serverRequestCreateUrl.getLinkPost())) {
            String str = this.linkCache_.get(serverRequestCreateUrl.getLinkPost());
            serverRequestCreateUrl.onUrlAvailable(str);
            return str;
        } else if (!serverRequestCreateUrl.isAsync()) {
            return generateShortLinkSync(serverRequestCreateUrl);
        } else {
            generateShortLinkAsync(serverRequestCreateUrl);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void shareLink(ShareLinkBuilder shareLinkBuilder) {
        ShareLinkManager shareLinkManager = this.shareLinkManager_;
        if (shareLinkManager != null) {
            shareLinkManager.cancelShareLinkDialog(true);
        }
        ShareLinkManager shareLinkManager2 = new ShareLinkManager();
        this.shareLinkManager_ = shareLinkManager2;
        shareLinkManager2.shareLink(shareLinkBuilder);
    }

    public void cancelShareLinkDialog(boolean z) {
        ShareLinkManager shareLinkManager = this.shareLinkManager_;
        if (shareLinkManager != null) {
            shareLinkManager.cancelShareLinkDialog(z);
        }
    }

    private String convertDate(Date date) {
        return DateFormat.format("yyyy-MM-dd", date).toString();
    }

    private String generateShortLinkSync(ServerRequestCreateUrl serverRequestCreateUrl) {
        ServerResponse serverResponse;
        if (this.trackingController.isTrackingDisabled()) {
            return serverRequestCreateUrl.getLongUrl();
        }
        String str = null;
        if (this.initState_ == SESSION_STATE.INITIALISED) {
            try {
                serverResponse = (ServerResponse) new getShortLinkTask().execute(new ServerRequest[]{serverRequestCreateUrl}).get((long) (this.prefHelper_.getTimeout() + 2000), TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                serverResponse = null;
            }
            if (serverRequestCreateUrl.isDefaultToLongUrl()) {
                str = serverRequestCreateUrl.getLongUrl();
            }
            if (serverResponse != null && serverResponse.getStatusCode() == 200) {
                try {
                    str = serverResponse.getObject().getString("url");
                    if (serverRequestCreateUrl.getLinkPost() != null) {
                        this.linkCache_.put(serverRequestCreateUrl.getLinkPost(), str);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }
        Log.i(TAG, "Branch Warning: User session has not been initialized");
        return null;
    }

    private void generateShortLinkAsync(ServerRequest serverRequest) {
        handleNewRequest(serverRequest);
    }

    private JSONObject convertParamsStringToDictionary(String str) {
        if (str.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            try {
                return new JSONObject(new String(Base64.decode(str.getBytes(), 2)));
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        }
    }

    /* access modifiers changed from: private */
    public void processNextQueueItem() {
        try {
            this.serverSema_.acquire();
            if (this.networkCount_ != 0 || this.requestQueue_.getSize() <= 0) {
                this.serverSema_.release();
                return;
            }
            this.networkCount_ = 1;
            ServerRequest peek = this.requestQueue_.peek();
            this.serverSema_.release();
            if (peek == null) {
                this.requestQueue_.remove((ServerRequest) null);
            } else if (peek.isWaitingOnProcessToFinish()) {
                this.networkCount_ = 0;
            } else if (!(peek instanceof ServerRequestRegisterInstall) && !hasUser()) {
                Log.i(TAG, "Branch Error: User session has not been initialized!");
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, (int) BranchError.ERR_NO_SESSION);
            } else if ((peek instanceof ServerRequestInitSession) || (hasSession() && hasDeviceFingerPrint())) {
                new BranchPostTask(peek).executeTask(new Void[0]);
            } else {
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, (int) BranchError.ERR_NO_SESSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void handleFailure(int i, int i2) {
        ServerRequest serverRequest;
        if (i >= this.requestQueue_.getSize()) {
            ServerRequestQueue serverRequestQueue = this.requestQueue_;
            serverRequest = serverRequestQueue.peekAt(serverRequestQueue.getSize() - 1);
        } else {
            serverRequest = this.requestQueue_.peekAt(i);
        }
        handleFailure(serverRequest, i2);
    }

    private void handleFailure(ServerRequest serverRequest, int i) {
        if (serverRequest != null) {
            serverRequest.handleFailure(i, "");
        }
    }

    /* access modifiers changed from: private */
    public void updateAllRequestsInQueue() {
        JSONObject post;
        int i = 0;
        while (i < this.requestQueue_.getSize()) {
            try {
                ServerRequest peekAt = this.requestQueue_.peekAt(i);
                if (!(peekAt == null || (post = peekAt.getPost()) == null)) {
                    if (post.has(Defines.Jsonkey.SessionID.getKey())) {
                        peekAt.getPost().put(Defines.Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
                    }
                    if (post.has(Defines.Jsonkey.IdentityID.getKey())) {
                        peekAt.getPost().put(Defines.Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
                    }
                    if (post.has(Defines.Jsonkey.DeviceFingerprintID.getKey())) {
                        peekAt.getPost().put(Defines.Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
                    }
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private boolean hasSession() {
        return !this.prefHelper_.getSessionID().equals("bnc_no_value");
    }

    private boolean hasDeviceFingerPrint() {
        return !this.prefHelper_.getDeviceFingerPrintID().equals("bnc_no_value");
    }

    private boolean hasUser() {
        return !this.prefHelper_.getIdentityID().equals("bnc_no_value");
    }

    private void insertRequestAtFront(ServerRequest serverRequest) {
        if (this.networkCount_ == 0) {
            this.requestQueue_.insert(serverRequest, 0);
        } else {
            this.requestQueue_.insert(serverRequest, 1);
        }
    }

    private void registerInstallOrOpen(ServerRequest serverRequest, BranchReferralInitListener branchReferralInitListener) {
        if (!this.requestQueue_.containsInstallOrOpen()) {
            insertRequestAtFront(serverRequest);
        } else {
            if (branchReferralInitListener != null) {
                this.requestQueue_.setInstallOrOpenCallback(branchReferralInitListener);
            }
            this.requestQueue_.moveInstallOrOpenToFront(serverRequest, this.networkCount_, branchReferralInitListener);
        }
        processNextQueueItem();
    }

    private void initializeSession(BranchReferralInitListener branchReferralInitListener) {
        if (this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
            this.initState_ = SESSION_STATE.UNINITIALISED;
            if (branchReferralInitListener != null) {
                branchReferralInitListener.onInitFinished((JSONObject) null, new BranchError("Trouble initializing Branch.", BranchError.ERR_BRANCH_KEY_INVALID));
            }
            Log.i(TAG, "Branch Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.prefHelper_.getBranchKey() != null && this.prefHelper_.getBranchKey().startsWith("key_test_")) {
            Log.i(TAG, "Branch Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value") || !this.enableFacebookAppLinkCheck_) {
            registerAppInit(branchReferralInitListener, (ServerRequest.PROCESS_WAIT_LOCK) null);
        } else if (DeferredAppLinkDataHandler.fetchDeferredAppLinkData(this.context_, new DeferredAppLinkDataHandler.AppLinkFetchEvents() {
            public void onAppLinkFetchFinished(String str) {
                Branch.this.prefHelper_.setIsAppLinkTriggeredInit(true);
                if (str != null) {
                    String queryParameter = Uri.parse(str).getQueryParameter(Defines.Jsonkey.LinkClickID.getKey());
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Branch.this.prefHelper_.setLinkClickIdentifier(queryParameter);
                    }
                }
                Branch.this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
                Branch.this.processNextQueueItem();
            }
        }).booleanValue()) {
            registerAppInit(branchReferralInitListener, ServerRequest.PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            registerAppInit(branchReferralInitListener, (ServerRequest.PROCESS_WAIT_LOCK) null);
        }
    }

    private void registerAppInit(BranchReferralInitListener branchReferralInitListener, ServerRequest.PROCESS_WAIT_LOCK process_wait_lock) {
        ServerRequest installOrOpenRequest = getInstallOrOpenRequest(branchReferralInitListener);
        installOrOpenRequest.addProcessWaitLock(process_wait_lock);
        if (this.isGAParamsFetchInProgress_) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        if (this.intentState_ != INTENT_STATE.READY && !isForceSessionEnabled()) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        }
        if (checkInstallReferrer_ && (installOrOpenRequest instanceof ServerRequestRegisterInstall) && !InstallListener.unReportedReferrerAvailable) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
            InstallListener.captureInstallReferrer(this.context_, playStoreReferrerFetchTime, this);
        }
        registerInstallOrOpen(installOrOpenRequest, branchReferralInitListener);
    }

    /* access modifiers changed from: package-private */
    public void registerAppReInit() {
        if (!this.trackingController.isTrackingDisabled()) {
            this.isGAParamsFetchInProgress_ = this.systemObserver_.prefetchGAdsParams(this);
        }
        if (this.networkCount_ != 0) {
            this.networkCount_ = 0;
            this.requestQueue_.clear();
        }
        ServerRequest installOrOpenRequest = getInstallOrOpenRequest((BranchReferralInitListener) null);
        if (this.isGAParamsFetchInProgress_) {
            installOrOpenRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        registerInstallOrOpen(installOrOpenRequest, (BranchReferralInitListener) null);
    }

    private ServerRequest getInstallOrOpenRequest(BranchReferralInitListener branchReferralInitListener) {
        if (hasUser()) {
            return new ServerRequestRegisterOpen(this.context_, branchReferralInitListener, this.systemObserver_);
        }
        return new ServerRequestRegisterInstall(this.context_, branchReferralInitListener, this.systemObserver_, InstallListener.getInstallationID());
    }

    /* access modifiers changed from: private */
    public void onIntentReady(Activity activity, boolean z) {
        this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (z) {
            readAndStripParam(activity.getIntent().getData(), activity);
            if (isTrackingDisabled() || cookieBasedMatchDomain_ == null || this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
                processNextQueueItem();
            } else if (this.isGAParamsFetchInProgress_) {
                this.performCookieBasedStrongMatchingOnGAIDAvailable = true;
            } else {
                performCookieBasedStrongMatch();
            }
        } else {
            processNextQueueItem();
        }
    }

    private void performCookieBasedStrongMatch() {
        if (!this.trackingController.isTrackingDisabled()) {
            DeviceInfo instance = DeviceInfo.getInstance(this.prefHelper_.getExternDebug(), this.systemObserver_, disableDeviceIDFetch_);
            WeakReference<Activity> weakReference = this.currentActivityReference_;
            Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
            Context applicationContext = activity != null ? activity.getApplicationContext() : null;
            if (applicationContext != null) {
                this.requestQueue_.setStrongMatchWaitLock();
                BranchStrongMatchHelper.getInstance().checkForStrongMatch(applicationContext, cookieBasedMatchDomain_, instance, this.prefHelper_, this.systemObserver_, new BranchStrongMatchHelper.StrongMatchCheckEvents() {
                    public void onStrongMatchCheckFinished() {
                        Branch.this.requestQueue_.unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                        Branch.this.processNextQueueItem();
                    }
                });
            }
        }
    }

    public void handleNewRequest(ServerRequest serverRequest) {
        if (this.trackingController.isTrackingDisabled()) {
            serverRequest.reportTrackingDisabledError();
            return;
        }
        if (this.initState_ != SESSION_STATE.INITIALISED && !(serverRequest instanceof ServerRequestInitSession)) {
            if (serverRequest instanceof ServerRequestLogout) {
                serverRequest.handleFailure(BranchError.ERR_NO_SESSION, "");
                Log.i(TAG, "Branch is not initialized, cannot logout");
                return;
            } else if (serverRequest instanceof ServerRequestRegisterClose) {
                Log.i(TAG, "Branch is not initialized, cannot close session");
                return;
            } else {
                WeakReference<Activity> weakReference = this.currentActivityReference_;
                Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
                boolean z = true;
                if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    initUserSessionInternal((BranchReferralInitListener) null, activity, true);
                } else {
                    if (customReferrableSettings_ != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                        z = false;
                    }
                    initUserSessionInternal((BranchReferralInitListener) null, activity, z);
                }
            }
        }
        if (!(serverRequest instanceof ServerRequestPing)) {
            this.requestQueue_.enqueue(serverRequest);
            serverRequest.onRequestQueued();
        }
        processNextQueueItem();
    }

    public void notifyNetworkAvailable() {
        handleNewRequest(new ServerRequestPing(this.context_));
    }

    private void setActivityLifeCycleObserver(Application application) {
        try {
            BranchActivityLifeCycleObserver branchActivityLifeCycleObserver = new BranchActivityLifeCycleObserver();
            application.unregisterActivityLifecycleCallbacks(branchActivityLifeCycleObserver);
            application.registerActivityLifecycleCallbacks(branchActivityLifeCycleObserver);
            isActivityLifeCycleCallbackRegistered_ = true;
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
            isActivityLifeCycleCallbackRegistered_ = false;
            isAutoSessionMode_ = false;
            Log.w(TAG, new BranchError("", BranchError.ERR_API_LVL_14_NEEDED).getMessage());
        }
    }

    private class BranchActivityLifeCycleObserver implements Application.ActivityLifecycleCallbacks {
        private int activityCnt_;

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        private BranchActivityLifeCycleObserver() {
            this.activityCnt_ = 0;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Branch branch = Branch.this;
            INTENT_STATE unused = branch.intentState_ = branch.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            boolean unused2 = Branch.this.isActivityCreatedAndLaunched = true;
            if (BranchViewHandler.getInstance().isInstallOrOpenBranchViewPending(activity.getApplicationContext())) {
                BranchViewHandler.getInstance().showPendingBranchView(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            Branch branch = Branch.this;
            INTENT_STATE unused = branch.intentState_ = branch.handleDelayedNewIntents_ ? INTENT_STATE.PENDING : INTENT_STATE.READY;
            if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                try {
                    ContentDiscoverer.getInstance().discoverContent(activity, Branch.this.sessionReferredLink_);
                } catch (Exception unused2) {
                }
            }
            if (this.activityCnt_ < 1) {
                if (Branch.this.initState_ == SESSION_STATE.INITIALISED) {
                    SESSION_STATE unused3 = Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                }
                if (BranchUtil.isTestModeEnabled(Branch.this.context_)) {
                    Branch.this.prefHelper_.setExternDebug();
                }
                Branch.this.startSession(activity);
            } else if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                SESSION_STATE unused4 = Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            this.activityCnt_++;
            boolean unused5 = Branch.this.isActivityCreatedAndLaunched = false;
        }

        public void onActivityResumed(Activity activity) {
            if (Branch.this.checkIntentForSessionRestart(activity.getIntent())) {
                SESSION_STATE unused = Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                Branch.this.startSession(activity);
            }
            Branch.this.currentActivityReference_ = new WeakReference<>(activity);
            if (Branch.this.handleDelayedNewIntents_) {
                INTENT_STATE unused2 = Branch.this.intentState_ = INTENT_STATE.READY;
                Branch.this.onIntentReady(activity, (activity.getIntent() == null || Branch.this.initState_ == SESSION_STATE.INITIALISED) ? false : true);
            }
        }

        public void onActivityPaused(Activity activity) {
            if (Branch.this.shareLinkManager_ != null) {
                Branch.this.shareLinkManager_.cancelShareLinkDialog(true);
            }
        }

        public void onActivityStopped(Activity activity) {
            ContentDiscoverer.getInstance().onActivityStopped(activity);
            int i = this.activityCnt_ - 1;
            this.activityCnt_ = i;
            if (i < 1) {
                Branch.this.isInstantDeepLinkPossible = false;
                Branch.this.closeSessionInternal();
            }
        }

        public void onActivityDestroyed(Activity activity) {
            if (Branch.this.currentActivityReference_ != null && Branch.this.currentActivityReference_.get() == activity) {
                Branch.this.currentActivityReference_.clear();
            }
            BranchViewHandler.getInstance().onCurrentActivityDestroyed(activity);
        }
    }

    /* access modifiers changed from: private */
    public void startSession(Activity activity) {
        Uri data = activity.getIntent() != null ? activity.getIntent().getData() : null;
        this.isInitReportedThroughCallBack = false;
        initSessionWithData(data, activity);
    }

    /* access modifiers changed from: private */
    public boolean checkIntentForSessionRestart(Intent intent) {
        boolean z;
        if (intent == null) {
            return false;
        }
        try {
            z = intent.getBooleanExtra(Defines.Jsonkey.ForceNewBranchSession.getKey(), false);
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            intent.putExtra(Defines.Jsonkey.ForceNewBranchSession.getKey(), false);
        }
        return z;
    }

    private class getShortLinkTask extends AsyncTask<ServerRequest, Void, ServerResponse> {
        private getShortLinkTask() {
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(ServerRequest... serverRequestArr) {
            BranchRemoteInterface access$1400 = Branch.this.branchRemoteInterface_;
            JSONObject post = serverRequestArr[0].getPost();
            return access$1400.make_restful_post(post, Branch.this.prefHelper_.getAPIBaseUrl() + "v1/url", Defines.RequestPath.GetURL.getPath(), Branch.this.prefHelper_.getBranchKey());
        }
    }

    private class BranchPostTask extends BranchAsyncTask<Void, Void, ServerResponse> {
        ServerRequest thisReq_;

        public BranchPostTask(ServerRequest serverRequest) {
            this.thisReq_ = serverRequest;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.thisReq_.onPreExecute();
            this.thisReq_.doFinalUpdateOnMainThread();
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(Void... voidArr) {
            Branch branch = Branch.this;
            branch.addExtraInstrumentationData(this.thisReq_.getRequestPath() + "-" + Defines.Jsonkey.Queue_Wait_Time.getKey(), String.valueOf(this.thisReq_.getQueueWaitTime()));
            this.thisReq_.doFinalUpdateOnBackgroundThread();
            if (Branch.this.isTrackingDisabled() && !this.thisReq_.prepareExecuteWithoutTracking()) {
                return new ServerResponse(this.thisReq_.getRequestPath(), BranchError.ERR_BRANCH_TRACKING_DISABLED);
            }
            if (this.thisReq_.isGetRequest()) {
                return Branch.this.branchRemoteInterface_.make_restful_get(this.thisReq_.getRequestUrl(), this.thisReq_.getGetParams(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey());
            }
            return Branch.this.branchRemoteInterface_.make_restful_post(this.thisReq_.getPostWithInstrumentationValues(Branch.this.instrumentationExtraData_), this.thisReq_.getRequestUrl(), this.thisReq_.getRequestPath(), Branch.this.prefHelper_.getBranchKey());
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ServerResponse serverResponse) {
            boolean z;
            super.onPostExecute(serverResponse);
            if (serverResponse != null) {
                try {
                    int statusCode = serverResponse.getStatusCode();
                    boolean unused = Branch.this.hasNetwork_ = true;
                    if (serverResponse.getStatusCode() == -117) {
                        this.thisReq_.reportTrackingDisabledError();
                        Branch.this.requestQueue_.remove(this.thisReq_);
                    } else if (statusCode != 200) {
                        if (this.thisReq_ instanceof ServerRequestInitSession) {
                            SESSION_STATE unused2 = Branch.this.initState_ = SESSION_STATE.UNINITIALISED;
                        }
                        if (statusCode != 400) {
                            if (statusCode != 409) {
                                boolean unused3 = Branch.this.hasNetwork_ = false;
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < Branch.this.requestQueue_.getSize(); i++) {
                                    arrayList.add(Branch.this.requestQueue_.peekAt(i));
                                }
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    ServerRequest serverRequest = (ServerRequest) it.next();
                                    if (serverRequest == null || !serverRequest.shouldRetryOnFail()) {
                                        Branch.this.requestQueue_.remove(serverRequest);
                                    }
                                }
                                int unused4 = Branch.this.networkCount_ = 0;
                                Iterator it2 = arrayList.iterator();
                                while (it2.hasNext()) {
                                    ServerRequest serverRequest2 = (ServerRequest) it2.next();
                                    if (serverRequest2 != null) {
                                        serverRequest2.handleFailure(statusCode, serverResponse.getFailReason());
                                        if (serverRequest2.shouldRetryOnFail()) {
                                            serverRequest2.clearCallbacks();
                                        }
                                    }
                                }
                            }
                        }
                        Branch.this.requestQueue_.remove(this.thisReq_);
                        if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                            ((ServerRequestCreateUrl) this.thisReq_).handleDuplicateURLError();
                        } else {
                            Log.i(Branch.TAG, "Branch API Error: Conflicting resource error code from API");
                            Branch.this.handleFailure(0, statusCode);
                        }
                    } else {
                        boolean unused5 = Branch.this.hasNetwork_ = true;
                        if (this.thisReq_ instanceof ServerRequestCreateUrl) {
                            if (serverResponse.getObject() != null) {
                                Branch.this.linkCache_.put(((ServerRequestCreateUrl) this.thisReq_).getLinkPost(), serverResponse.getObject().getString("url"));
                            }
                        } else if (this.thisReq_ instanceof ServerRequestLogout) {
                            Branch.this.linkCache_.clear();
                            Branch.this.requestQueue_.clear();
                        }
                        Branch.this.requestQueue_.dequeue();
                        if (!(this.thisReq_ instanceof ServerRequestInitSession)) {
                            if (!(this.thisReq_ instanceof ServerRequestIdentifyUserRequest)) {
                                this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                            }
                        }
                        JSONObject object = serverResponse.getObject();
                        if (object != null) {
                            if (!Branch.this.isTrackingDisabled()) {
                                if (object.has(Defines.Jsonkey.SessionID.getKey())) {
                                    Branch.this.prefHelper_.setSessionID(object.getString(Defines.Jsonkey.SessionID.getKey()));
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (object.has(Defines.Jsonkey.IdentityID.getKey())) {
                                    if (!Branch.this.prefHelper_.getIdentityID().equals(object.getString(Defines.Jsonkey.IdentityID.getKey()))) {
                                        Branch.this.linkCache_.clear();
                                        Branch.this.prefHelper_.setIdentityID(object.getString(Defines.Jsonkey.IdentityID.getKey()));
                                        z = true;
                                    }
                                }
                                if (object.has(Defines.Jsonkey.DeviceFingerprintID.getKey())) {
                                    Branch.this.prefHelper_.setDeviceFingerPrintID(object.getString(Defines.Jsonkey.DeviceFingerprintID.getKey()));
                                    z = true;
                                }
                            } else {
                                z = false;
                            }
                            if (z) {
                                Branch.this.updateAllRequestsInQueue();
                            }
                            if (this.thisReq_ instanceof ServerRequestInitSession) {
                                SESSION_STATE unused6 = Branch.this.initState_ = SESSION_STATE.INITIALISED;
                                this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                                if (!Branch.this.isInitReportedThroughCallBack && !((ServerRequestInitSession) this.thisReq_).handleBranchViewIfAvailable(serverResponse)) {
                                    Branch.this.checkForAutoDeepLinkConfiguration();
                                }
                                if (((ServerRequestInitSession) this.thisReq_).hasCallBack()) {
                                    Branch.this.isInitReportedThroughCallBack = true;
                                }
                                if (Branch.this.getLatestReferringParamsLatch != null) {
                                    Branch.this.getLatestReferringParamsLatch.countDown();
                                }
                                if (Branch.this.getFirstReferringParamsLatch != null) {
                                    Branch.this.getFirstReferringParamsLatch.countDown();
                                }
                            } else {
                                this.thisReq_.onRequestSucceeded(serverResponse, Branch.branchReferral_);
                            }
                        }
                    }
                    int unused7 = Branch.this.networkCount_ = 0;
                    if (Branch.this.hasNetwork_ && Branch.this.initState_ != SESSION_STATE.UNINITIALISED) {
                        Branch.this.processNextQueueItem();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isAutoDeepLinkLaunch(Activity activity) {
        return activity.getIntent().getStringExtra(AUTO_DEEP_LINKED) != null;
    }

    /* access modifiers changed from: private */
    public void checkForAutoDeepLinkConfiguration() {
        ActivityInfo activityInfo;
        JSONObject latestReferringParams = getLatestReferringParams();
        String str = null;
        try {
            if (!latestReferringParams.has(Defines.Jsonkey.Clicked_Branch_Link.getKey())) {
                return;
            }
            if (latestReferringParams.getBoolean(Defines.Jsonkey.Clicked_Branch_Link.getKey())) {
                if (latestReferringParams.length() > 0) {
                    ApplicationInfo applicationInfo = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128);
                    int i = 0;
                    if (applicationInfo.metaData == null || !applicationInfo.metaData.getBoolean(AUTO_DEEP_LINK_DISABLE, false)) {
                        ActivityInfo[] activityInfoArr = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 129).activities;
                        int i2 = DEF_AUTO_DEEP_LINK_REQ_CODE;
                        if (activityInfoArr != null) {
                            int length = activityInfoArr.length;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                activityInfo = activityInfoArr[i];
                                if (activityInfo == null || activityInfo.metaData == null || ((activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY) == null && activityInfo.metaData.getString(AUTO_DEEP_LINK_PATH) == null) || (!checkForAutoDeepLinkKeys(latestReferringParams, activityInfo) && !checkForAutoDeepLinkPath(latestReferringParams, activityInfo)))) {
                                    i++;
                                }
                            }
                            str = activityInfo.name;
                            i2 = activityInfo.metaData.getInt(AUTO_DEEP_LINK_REQ_CODE, DEF_AUTO_DEEP_LINK_REQ_CODE);
                        }
                        if (str != null && this.currentActivityReference_ != null) {
                            Activity activity = (Activity) this.currentActivityReference_.get();
                            if (activity != null) {
                                Intent intent = new Intent(activity, Class.forName(str));
                                intent.putExtra(AUTO_DEEP_LINKED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                intent.putExtra(Defines.Jsonkey.ReferringData.getKey(), latestReferringParams.toString());
                                Iterator<String> keys = latestReferringParams.keys();
                                while (keys.hasNext()) {
                                    String next = keys.next();
                                    intent.putExtra(next, latestReferringParams.getString(next));
                                }
                                activity.startActivityForResult(intent, i2);
                                return;
                            }
                            Log.w(TAG, "No activity reference to launch deep linked activity");
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "Branch Warning: Please make sure Activity names set for auto deep link are correct!");
        } catch (ClassNotFoundException unused2) {
            Log.i(TAG, "Branch Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity " + null);
        } catch (Exception unused3) {
        }
    }

    private boolean checkForAutoDeepLinkKeys(JSONObject jSONObject, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY) != null) {
            for (String has : activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY).split(",")) {
                if (jSONObject.has(has)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkForAutoDeepLinkPath(org.json.JSONObject r5, android.content.pm.ActivityInfo r6) {
        /*
            r4 = this;
            r0 = 0
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            boolean r1 = r5.has(r1)     // Catch:{ JSONException -> 0x0030 }
            if (r1 == 0) goto L_0x0019
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.AndroidDeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r5 = r5.getString(r1)     // Catch:{ JSONException -> 0x0030 }
        L_0x0017:
            r0 = r5
            goto L_0x0031
        L_0x0019:
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            boolean r1 = r5.has(r1)     // Catch:{ JSONException -> 0x0030 }
            if (r1 == 0) goto L_0x0031
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.DeepLinkPath     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r1 = r1.getKey()     // Catch:{ JSONException -> 0x0030 }
            java.lang.String r5 = r5.getString(r1)     // Catch:{ JSONException -> 0x0030 }
            goto L_0x0017
        L_0x0030:
        L_0x0031:
            android.os.Bundle r5 = r6.metaData
            java.lang.String r1 = "io.branch.sdk.auto_link_path"
            java.lang.String r5 = r5.getString(r1)
            r2 = 0
            if (r5 == 0) goto L_0x005f
            if (r0 == 0) goto L_0x005f
            android.os.Bundle r5 = r6.metaData
            java.lang.String r5 = r5.getString(r1)
            java.lang.String r6 = ","
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r1 = 0
        L_0x004c:
            if (r1 >= r6) goto L_0x005f
            r3 = r5[r1]
            java.lang.String r3 = r3.trim()
            boolean r3 = r4.pathMatch(r3, r0)
            if (r3 == 0) goto L_0x005c
            r5 = 1
            return r5
        L_0x005c:
            int r1 = r1 + 1
            goto L_0x004c
        L_0x005f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.checkForAutoDeepLinkPath(org.json.JSONObject, android.content.pm.ActivityInfo):boolean");
    }

    private boolean pathMatch(String str, String str2) {
        String[] split = str.split("\\?")[0].split("/");
        String[] split2 = str2.split("\\?")[0].split("/");
        if (split.length != split2.length) {
            return false;
        }
        int i = 0;
        while (i < split.length && i < split2.length) {
            String str3 = split[i];
            if (!str3.equals(split2[i]) && !str3.contains("*")) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void enableSimulateInstalls() {
        isSimulatingInstalls_ = true;
    }

    public static void disableSimulateInstalls() {
        isSimulatingInstalls_ = false;
    }

    public static void enableLogging() {
        isLogging_ = true;
    }

    public static void disableLogging() {
        isLogging_ = false;
    }

    public static void enableForcedSession() {
        isForcedSession_ = true;
    }

    public static void disableForcedSession() {
        isForcedSession_ = false;
    }

    public static boolean isForceSessionEnabled() {
        return isForcedSession_;
    }

    public static class ShareLinkBuilder {
        private final Activity activity_;
        private final Branch branch_;
        private BranchLinkShareListener callback_;
        private IChannelProperties channelPropertiesCallback_;
        private String copyURlText_;
        private Drawable copyUrlIcon_;
        private String defaultURL_;
        private int dialogThemeResourceID_;
        private int dividerHeight;
        private List<String> excludeFromShareSheet;
        private int iconSize_;
        private List<String> includeInShareSheet;
        private Drawable moreOptionIcon_;
        private String moreOptionText_;
        private ArrayList<SharingHelper.SHARE_WITH> preferredOptions_;
        private boolean setFullWidthStyle_;
        private String shareMsg_;
        private String shareSub_;
        private String sharingTitle;
        private View sharingTitleView;
        BranchShortLinkBuilder shortLinkBuilder_;
        private int styleResourceID_;
        private String urlCopiedMessage_;

        public ShareLinkBuilder(Activity activity, JSONObject jSONObject) {
            this.callback_ = null;
            this.channelPropertiesCallback_ = null;
            this.dividerHeight = -1;
            this.sharingTitle = null;
            this.sharingTitleView = null;
            this.iconSize_ = 50;
            this.includeInShareSheet = new ArrayList();
            this.excludeFromShareSheet = new ArrayList();
            this.activity_ = activity;
            this.branch_ = Branch.branchReferral_;
            this.shortLinkBuilder_ = new BranchShortLinkBuilder(activity);
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    this.shortLinkBuilder_.addParameters(next, (String) jSONObject.get(next));
                }
            } catch (Exception unused) {
            }
            this.shareMsg_ = "";
            this.callback_ = null;
            this.channelPropertiesCallback_ = null;
            this.preferredOptions_ = new ArrayList<>();
            this.defaultURL_ = null;
            this.moreOptionIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301573);
            this.moreOptionText_ = "More...";
            this.copyUrlIcon_ = BranchUtil.getDrawable(activity.getApplicationContext(), 17301582);
            this.copyURlText_ = "Copy link";
            this.urlCopiedMessage_ = "Copied link to clipboard!";
        }

        public ShareLinkBuilder(Activity activity, BranchShortLinkBuilder branchShortLinkBuilder) {
            this(activity, new JSONObject());
            this.shortLinkBuilder_ = branchShortLinkBuilder;
        }

        public ShareLinkBuilder setMessage(String str) {
            this.shareMsg_ = str;
            return this;
        }

        public ShareLinkBuilder setSubject(String str) {
            this.shareSub_ = str;
            return this;
        }

        public ShareLinkBuilder addTag(String str) {
            this.shortLinkBuilder_.addTag(str);
            return this;
        }

        public ShareLinkBuilder addTags(ArrayList<String> arrayList) {
            this.shortLinkBuilder_.addTags(arrayList);
            return this;
        }

        public ShareLinkBuilder setFeature(String str) {
            this.shortLinkBuilder_.setFeature(str);
            return this;
        }

        public ShareLinkBuilder setStage(String str) {
            this.shortLinkBuilder_.setStage(str);
            return this;
        }

        public ShareLinkBuilder setCallback(BranchLinkShareListener branchLinkShareListener) {
            this.callback_ = branchLinkShareListener;
            return this;
        }

        public ShareLinkBuilder setChannelProperties(IChannelProperties iChannelProperties) {
            this.channelPropertiesCallback_ = iChannelProperties;
            return this;
        }

        public ShareLinkBuilder addPreferredSharingOption(SharingHelper.SHARE_WITH share_with) {
            this.preferredOptions_.add(share_with);
            return this;
        }

        public ShareLinkBuilder addPreferredSharingOptions(ArrayList<SharingHelper.SHARE_WITH> arrayList) {
            this.preferredOptions_.addAll(arrayList);
            return this;
        }

        public ShareLinkBuilder addParam(String str, String str2) {
            try {
                this.shortLinkBuilder_.addParameters(str, str2);
            } catch (Exception unused) {
            }
            return this;
        }

        public ShareLinkBuilder setDefaultURL(String str) {
            this.defaultURL_ = str;
            return this;
        }

        public ShareLinkBuilder setMoreOptionStyle(Drawable drawable, String str) {
            this.moreOptionIcon_ = drawable;
            this.moreOptionText_ = str;
            return this;
        }

        public ShareLinkBuilder setMoreOptionStyle(int i, int i2) {
            this.moreOptionIcon_ = BranchUtil.getDrawable(this.activity_.getApplicationContext(), i);
            this.moreOptionText_ = this.activity_.getResources().getString(i2);
            return this;
        }

        public ShareLinkBuilder setCopyUrlStyle(Drawable drawable, String str, String str2) {
            this.copyUrlIcon_ = drawable;
            this.copyURlText_ = str;
            this.urlCopiedMessage_ = str2;
            return this;
        }

        public ShareLinkBuilder setCopyUrlStyle(int i, int i2, int i3) {
            this.copyUrlIcon_ = BranchUtil.getDrawable(this.activity_.getApplicationContext(), i);
            this.copyURlText_ = this.activity_.getResources().getString(i2);
            this.urlCopiedMessage_ = this.activity_.getResources().getString(i3);
            return this;
        }

        public ShareLinkBuilder setAlias(String str) {
            this.shortLinkBuilder_.setAlias(str);
            return this;
        }

        public ShareLinkBuilder setMatchDuration(int i) {
            this.shortLinkBuilder_.setDuration(i);
            return this;
        }

        public ShareLinkBuilder setAsFullWidthStyle(boolean z) {
            this.setFullWidthStyle_ = z;
            return this;
        }

        public ShareLinkBuilder setDialogThemeResourceID(int i) {
            this.dialogThemeResourceID_ = i;
            return this;
        }

        public ShareLinkBuilder setDividerHeight(int i) {
            this.dividerHeight = i;
            return this;
        }

        public ShareLinkBuilder setSharingTitle(String str) {
            this.sharingTitle = str;
            return this;
        }

        public ShareLinkBuilder setSharingTitle(View view) {
            this.sharingTitleView = view;
            return this;
        }

        public ShareLinkBuilder setIconSize(int i) {
            this.iconSize_ = i;
            return this;
        }

        public ShareLinkBuilder excludeFromShareSheet(String str) {
            this.excludeFromShareSheet.add(str);
            return this;
        }

        public ShareLinkBuilder excludeFromShareSheet(String[] strArr) {
            this.excludeFromShareSheet.addAll(Arrays.asList(strArr));
            return this;
        }

        public ShareLinkBuilder excludeFromShareSheet(List<String> list) {
            this.excludeFromShareSheet.addAll(list);
            return this;
        }

        public ShareLinkBuilder includeInShareSheet(String str) {
            this.includeInShareSheet.add(str);
            return this;
        }

        public ShareLinkBuilder includeInShareSheet(String[] strArr) {
            this.includeInShareSheet.addAll(Arrays.asList(strArr));
            return this;
        }

        public ShareLinkBuilder includeInShareSheet(List<String> list) {
            this.includeInShareSheet.addAll(list);
            return this;
        }

        public void setStyleResourceID(int i) {
            this.styleResourceID_ = i;
        }

        public void setShortLinkBuilderInternal(BranchShortLinkBuilder branchShortLinkBuilder) {
            this.shortLinkBuilder_ = branchShortLinkBuilder;
        }

        public void shareLink() {
            Branch.branchReferral_.shareLink(this);
        }

        public Activity getActivity() {
            return this.activity_;
        }

        public ArrayList<SharingHelper.SHARE_WITH> getPreferredOptions() {
            return this.preferredOptions_;
        }

        /* access modifiers changed from: package-private */
        public List<String> getExcludedFromShareSheet() {
            return this.excludeFromShareSheet;
        }

        /* access modifiers changed from: package-private */
        public List<String> getIncludedInShareSheet() {
            return this.includeInShareSheet;
        }

        public Branch getBranch() {
            return this.branch_;
        }

        public String getShareMsg() {
            return this.shareMsg_;
        }

        public String getShareSub() {
            return this.shareSub_;
        }

        public BranchLinkShareListener getCallback() {
            return this.callback_;
        }

        public IChannelProperties getChannelPropertiesCallback() {
            return this.channelPropertiesCallback_;
        }

        public String getDefaultURL() {
            return this.defaultURL_;
        }

        public Drawable getMoreOptionIcon() {
            return this.moreOptionIcon_;
        }

        public String getMoreOptionText() {
            return this.moreOptionText_;
        }

        public Drawable getCopyUrlIcon() {
            return this.copyUrlIcon_;
        }

        public String getCopyURlText() {
            return this.copyURlText_;
        }

        public String getUrlCopiedMessage() {
            return this.urlCopiedMessage_;
        }

        public BranchShortLinkBuilder getShortLinkBuilder() {
            return this.shortLinkBuilder_;
        }

        public boolean getIsFullWidthStyle() {
            return this.setFullWidthStyle_;
        }

        public int getDialogThemeResourceID() {
            return this.dialogThemeResourceID_;
        }

        public int getDividerHeight() {
            return this.dividerHeight;
        }

        public String getSharingTitle() {
            return this.sharingTitle;
        }

        public View getSharingTitleView() {
            return this.sharingTitleView;
        }

        public int getStyleResourceID() {
            return this.styleResourceID_;
        }

        public int getIconSize() {
            return this.iconSize_;
        }
    }

    public void registerView(BranchUniversalObject branchUniversalObject, BranchUniversalObject.RegisterViewStatusListener registerViewStatusListener) {
        if (this.context_ != null) {
            new BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM).addContentItems(branchUniversalObject).logEvent(this.context_);
        }
    }

    public void addExtraInstrumentationData(HashMap<String, String> hashMap) {
        this.instrumentationExtraData_.putAll(hashMap);
    }

    public void addExtraInstrumentationData(String str, String str2) {
        this.instrumentationExtraData_.put(str, str2);
    }

    public void onBranchViewAccepted(String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewCancelled(String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewError(int i, String str, String str2) {
        if (ServerRequestInitSession.isInitSessionAction(str2)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public static boolean isInstantApp(Context context) {
        return InstantAppUtil.isInstantApp(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean showInstallPrompt(android.app.Activity r4, int r5) {
        /*
            io.branch.referral.Branch r0 = getInstance()
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0073
            io.branch.referral.Branch r0 = getInstance()
            org.json.JSONObject r0 = r0.getLatestReferringParams()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "~"
            r2.append(r3)
            io.branch.referral.Defines$Jsonkey r3 = io.branch.referral.Defines.Jsonkey.ReferringLink
            java.lang.String r3 = r3.getKey()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            if (r0 == 0) goto L_0x0073
            boolean r3 = r0.has(r2)
            if (r3 == 0) goto L_0x0073
            java.lang.String r0 = r0.getString(r2)     // Catch:{ JSONException -> 0x0040, UnsupportedEncodingException -> 0x003e }
            java.lang.String r2 = "UTF-8"
            java.lang.String r0 = java.net.URLEncoder.encode(r0, r2)     // Catch:{ JSONException -> 0x003c, UnsupportedEncodingException -> 0x003a }
            goto L_0x0045
        L_0x003a:
            r2 = move-exception
            goto L_0x0042
        L_0x003c:
            r2 = move-exception
            goto L_0x0042
        L_0x003e:
            r2 = move-exception
            goto L_0x0041
        L_0x0040:
            r2 = move-exception
        L_0x0041:
            r0 = r1
        L_0x0042:
            r2.printStackTrace()
        L_0x0045:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0073
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            io.branch.referral.Defines$Jsonkey r2 = io.branch.referral.Defines.Jsonkey.IsFullAppConv
            java.lang.String r2 = r2.getKey()
            r1.append(r2)
            java.lang.String r2 = "=true&"
            r1.append(r2)
            io.branch.referral.Defines$Jsonkey r2 = io.branch.referral.Defines.Jsonkey.ReferringLink
            java.lang.String r2 = r2.getKey()
            r1.append(r2)
            java.lang.String r2 = "="
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
        L_0x0073:
            boolean r4 = io.branch.referral.InstantAppUtil.doShowInstallPrompt(r4, r5, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.Branch.showInstallPrompt(android.app.Activity, int):boolean");
    }

    public static boolean showInstallPrompt(Activity activity, int i, String str) {
        return InstantAppUtil.doShowInstallPrompt(activity, i, Defines.Jsonkey.IsFullAppConv.getKey() + "=true&" + str);
    }

    public static boolean showInstallPrompt(Activity activity, int i, BranchUniversalObject branchUniversalObject) {
        if (branchUniversalObject == null) {
            return false;
        }
        String str = Defines.Jsonkey.ReferringLink.getKey() + "=" + branchUniversalObject.getShortUrl(activity, new LinkProperties());
        if (!TextUtils.isEmpty(str)) {
            return showInstallPrompt(activity, i, str);
        }
        return showInstallPrompt(activity, i, "");
    }
}
