package com.medscape.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.collection.LruCache;
import androidx.multidex.MultiDex;
import com.adobe.mobile.Config;
import com.comscore.Analytics;
import com.comscore.PublisherConfiguration;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.medscape.android.BI.BIManager;
import com.medscape.android.activity.calc.MedscapeAppConfiguration;
import com.medscape.android.activity.calc.MedscapeFilesProvider;
import com.medscape.android.activity.calc.MedscapeStyleProvider;
import com.medscape.android.activity.calc.OmnitureAppSettings;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.cache.ImageCacheManager;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.media.SampledBitmap;
import com.qxmd.eventssdkandroid.managers.QXEEventsManager;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.omniture.OmnitureState;
import com.wbmd.omniture.OmnitureTracker;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.QxContentStyle;
import com.wbmd.qxcalculator.managers.AuthManager;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentHelper;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.managers.InternetConnectivityManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import com.wbmd.qxcalculator.util.jsevaluator.QxJsEvaluator;
import com.wbmd.qxcalculator.util.legacy.LegacyUpdateHelper;
import com.wbmd.wbmdcommons.caching.CacheProvider;
import com.wbmd.wbmdcommons.caching.ICacheProvider;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import java.io.File;
import net.media.android.bidder.base.MNet;

public class MedscapeApplication extends Application {
    private static final String TAG = "MedscapeApplication ";
    private static MedscapeApplication sInstance;
    private BIManager mBIManager;
    private LruCache<String, SampledBitmap> mBitmapCache;
    private ImageCacheManager mCacheManager;
    private ICacheProvider mCacheProvider;
    private ConnectivityManager mConnManager;
    private boolean mIsBackgroundUpdating = false;
    private SharedPreferences mPrefs;
    private UpdateManager mUpdateManager;

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initFirebase();
        initCrashlytics();
        initOmniture();
        Settings.singleton(this).saveSetting(Constants.PREF_SESSION_END, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        getPreferences().edit().putLong(Constants.PREF_LAST_PAUSE, System.currentTimeMillis()).apply();
        OldConstants.POP_UP_WINDOW_WIDTH *= (int) getResources().getDisplayMetrics().density;
        setBackgroundUpdate(isBackgroundServiceRunning());
        boolean z = (getResources().getConfiguration().screenLayout & 15) >= 4;
        Settings.singleton(this).saveSetting("isTablet", z + "");
        Settings.singleton(this).saveSetting(Constants.PREF_NEWS_SESSION_MARKER, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initCacheProvider();
        initializeSingletons();
        EnvironmentManager.Companion.config(new EnvironmentConfig());
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                MedscapeApplication.this.lazyInitializationOfSdks();
            }
        }, 200);
        CMEHelper.registerSaveReceiver(this);
    }

    /* access modifiers changed from: private */
    public void lazyInitializationOfSdks() {
        initFacebookSDK();
        try {
            initComScore();
        } catch (Throwable unused) {
            Log.e("Medscape", "Comscore Failed to Load");
        }
        initConnManager();
        isDatabaseValid();
        initCacheManager();
        initBitmapCache();
        AppboyEventsHandler.resetDailyLogEvents();
        try {
            initMedianet();
        } catch (Throwable unused2) {
            Log.e("Medscape", "Medianet Failed to Load");
        }
        setUpCMEComponentValues();
        AndroidThreeTen.init((Application) this);
    }

    private void initializeSingletons() {
        QXEEventsManager.initializeApp(this);
        UserManager.initializeInstance(this);
        InternetConnectivityManager.initializeInstance(this);
        ContentHelper.getInstance().setContext(this);
        SharedPreferenceHelper.initializeInstance(this);
        RowItemBuilder.getInstance().setContext(this);
        LegacyUpdateHelper.getInstance().setContext(this);
        FileHelper.initializeWithContext(this);
        QxJsEvaluator.getInstance().setContext(getApplicationContext());
        AnalyticsHandler.initialize(this);
        DataManager.getInstance().setContext(getApplicationContext());
        AuthManager.getInstance().setContext(getApplicationContext());
        QxContentStyle.getInstance().setProvider(new MedscapeStyleProvider());
        ContentDataManager.getInstance().setContext(getApplicationContext());
        AppConfiguration.getInstance().setProvider(new MedscapeAppConfiguration());
        AppConfiguration.getInstance().setFilesProvider(new MedscapeFilesProvider());
    }

    private void initOmniture() {
        Config.setContext(getApplicationContext());
        OmnitureTracker.Companion.setDebugLogging(false);
        OmnitureState instance = OmnitureState.Companion.getInstance();
        instance.appSettings = new OmnitureAppSettings();
        instance.setCurrentSection(instance.appSettings.getDefaultSection());
    }

    public void initCrashlytics() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
    }

    public void initFirebase() {
        FirebaseApp.initializeApp(this);
    }

    public void onTerminate() {
        this.mCacheManager.shutdown();
        super.onTerminate();
    }

    public void initCacheProvider() {
        if (this.mCacheProvider == null) {
            this.mCacheProvider = new CacheProvider(get());
            this.mCacheProvider.initialize(new File(com.medscape.android.helper.FileHelper.getDataDirectory(get()), "MedscapeCache"), 2097152);
        }
        ICacheProvider cacheProvider = getCacheProvider();
        if (cacheProvider != null) {
            cacheProvider.removeKey("profregData");
        }
    }

    public void initMedianet() {
        MNet.init(this, getString(R.string.medianet_id));
    }

    public static ICacheProvider getCacheProvider() {
        return get().mCacheProvider;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0088, code lost:
        if (r0.size() != 0) goto L_0x008b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isDatabaseValid() {
        /*
            r9 = this;
            boolean r0 = com.medscape.android.util.Util.isSDCardAvailable()
            r1 = 1
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.medscape.android.MedscapeApplication r2 = get()
            java.lang.String r2 = com.medscape.android.helper.FileHelper.getDataDirectory(r2)
            r0.append(r2)
            java.lang.String r2 = "/Medscape/Medscape.sql"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = "settings"
            r4 = 0
            android.content.SharedPreferences r3 = r9.getSharedPreferences(r3, r4)
            android.content.SharedPreferences$Editor r5 = r3.edit()
            java.io.File r6 = new java.io.File
            r6.<init>(r0)
            boolean r0 = r6.exists()
            java.lang.String r7 = "version"
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 == 0) goto L_0x0046
            float r0 = r3.getFloat(r7, r8)
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0046
            r6.delete()
        L_0x0046:
            boolean r0 = r6.exists()
            if (r0 != 0) goto L_0x007a
            java.lang.String r0 = "android.permission.WRITE_EXTERNAL_STORAGE"
            int r0 = androidx.core.content.ContextCompat.checkSelfPermission(r9, r0)
            if (r0 != 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r1 = 0
        L_0x0056:
            if (r1 == 0) goto L_0x008a
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r3 = r3.getPath()
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            boolean r0 = r0.exists()
            r1 = r0
            goto L_0x008b
        L_0x007a:
            r0 = 25
            java.lang.String r2 = "cpr"
            java.util.List r0 = com.medscape.android.drugs.helper.SearchHelper.wordConditionMatching(r2, r0, r4, r9)
            if (r0 == 0) goto L_0x008a
            int r0 = r0.size()
            if (r0 != 0) goto L_0x008b
        L_0x008a:
            r1 = 0
        L_0x008b:
            if (r1 != 0) goto L_0x0093
            r5.putFloat(r7, r8)
            r5.apply()
        L_0x0093:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.MedscapeApplication.isDatabaseValid():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBackgroundServiceRunning() {
        /*
            r4 = this;
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            r1 = 2147483647(0x7fffffff, float:NaN)
            java.util.List r0 = r0.getRunningServices(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x0013:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0045
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$RunningServiceInfo r1 = (android.app.ActivityManager.RunningServiceInfo) r1
            java.lang.Class<com.medscape.android.update.BackgroundDataUpdateService> r2 = com.medscape.android.update.BackgroundDataUpdateService.class
            java.lang.String r2 = r2.getName()
            android.content.ComponentName r3 = r1.service
            java.lang.String r3 = r3.getClassName()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0043
            java.lang.Class<com.medscape.android.update.BackgroundClinicalUpdateService> r2 = com.medscape.android.update.BackgroundClinicalUpdateService.class
            java.lang.String r2 = r2.getName()
            android.content.ComponentName r1 = r1.service
            java.lang.String r1 = r1.getClassName()
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0013
        L_0x0043:
            r0 = 1
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.MedscapeApplication.isBackgroundServiceRunning():boolean");
    }

    public BIManager getBIManager() {
        if (this.mBIManager == null) {
            this.mBIManager = new BIManager();
        }
        return this.mBIManager;
    }

    public UpdateManager getUpdateManager() {
        if (this.mUpdateManager == null) {
            this.mUpdateManager = new UpdateManager(this);
        }
        return this.mUpdateManager;
    }

    public void listenForUpdates(OnUpdateListener onUpdateListener) {
        getUpdateManager();
        this.mUpdateManager.setOnUpdateListener(onUpdateListener);
    }

    public void initConnManager() {
        if (this.mConnManager == null) {
            this.mConnManager = (ConnectivityManager) getSystemService("connectivity");
        }
    }

    public boolean isWifiConnected() {
        return this.mConnManager.getNetworkInfo(1).isConnected();
    }

    public static MedscapeApplication get() {
        return sInstance;
    }

    public static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferences getPreferences() {
        if (this.mPrefs == null) {
            synchronized (this) {
                if (this.mPrefs == null) {
                    this.mPrefs = getPreferences(this);
                }
            }
        }
        return this.mPrefs;
    }

    public void setBackgroundUpdate(boolean z) {
        this.mIsBackgroundUpdating = z;
    }

    public boolean isBackgroundUpdateInProgress() {
        return this.mIsBackgroundUpdating;
    }

    public void initCacheManager() {
        ImageCacheManager imageCacheManager = new ImageCacheManager(this);
        this.mCacheManager = imageCacheManager;
        imageCacheManager.startup();
    }

    public void initBitmapCache() {
        this.mBitmapCache = new LruCache<String, SampledBitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 7) {
            /* access modifiers changed from: protected */
            public int sizeOf(String str, SampledBitmap sampledBitmap) {
                Bitmap bitmap = sampledBitmap.getBitmap();
                return (Build.VERSION.SDK_INT < 12 ? bitmap.getRowBytes() * bitmap.getHeight() : bitmap.getByteCount()) / 1024;
            }
        };
    }

    public void initComScore() {
        Analytics.getConfiguration().addClient(new PublisherConfiguration.Builder().publisherId(getResources().getString(R.string.comscore_client_id)).build());
        Analytics.start(getApplicationContext());
    }

    public ImageCacheManager getCacheManager() {
        return this.mCacheManager;
    }

    public SampledBitmap getSampledBitmap(String str) {
        return this.mBitmapCache.get(str);
    }

    public synchronized void addSampledBitmapToMemory(String str, SampledBitmap sampledBitmap) {
        try {
            this.mBitmapCache.put(str, sampledBitmap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public synchronized void removeSampledBitmap(String str) {
        this.mBitmapCache.remove(str);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void initFacebookSDK() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp((Application) this);
    }

    public void setUpCMEComponentValues() {
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        MedscapeApplication medscapeApplication = sInstance;
        sharedPreferenceProvider.saveString(medscapeApplication, Constants.PREF_KEY_APP_PACKAGE_NAME, medscapeApplication.getPackageName());
        SharedPreferenceProvider.get().saveString(sInstance, Constants.PREF_KEY_HOMEACTIVITY_NAME, CMELandingActivity.class.getCanonicalName());
    }
}
