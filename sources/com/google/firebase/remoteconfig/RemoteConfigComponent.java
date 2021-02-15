package com.google.firebase.remoteconfig;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.ConfigStorageClient;
import com.google.firebase.remoteconfig.internal.LegacyConfigsHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public class RemoteConfigComponent {
    public static final String ACTIVATE_FILE_NAME = "activate";
    public static final String DEFAULTS_FILE_NAME = "defaults";
    private static final Clock DEFAULT_CLOCK = DefaultClock.getInstance();
    public static final String DEFAULT_NAMESPACE = "firebase";
    private static final Random DEFAULT_RANDOM = new Random();
    public static final String FETCH_FILE_NAME = "fetch";
    private static final String FIREBASE_REMOTE_CONFIG_FILE_NAME_PREFIX = "frc";
    public static final long NETWORK_CONNECTION_TIMEOUT_IN_SECONDS = 60;
    private static final String PREFERENCES_FILE_NAME = "settings";
    private final AnalyticsConnector analyticsConnector;
    private final String appId;
    private final Context context;
    private Map<String, String> customHeaders;
    private final ExecutorService executorService;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstanceId firebaseInstanceId;
    private final Map<String, FirebaseRemoteConfig> frcNamespaceInstances;

    RemoteConfigComponent(Context context2, FirebaseApp firebaseApp2, FirebaseInstanceId firebaseInstanceId2, FirebaseABTesting firebaseABTesting, AnalyticsConnector analyticsConnector2) {
        this(context2, Executors.newCachedThreadPool(), firebaseApp2, firebaseInstanceId2, firebaseABTesting, analyticsConnector2, new LegacyConfigsHandler(context2, firebaseApp2.getOptions().getApplicationId()), true);
    }

    protected RemoteConfigComponent(Context context2, ExecutorService executorService2, FirebaseApp firebaseApp2, FirebaseInstanceId firebaseInstanceId2, FirebaseABTesting firebaseABTesting, AnalyticsConnector analyticsConnector2, LegacyConfigsHandler legacyConfigsHandler, boolean z) {
        this.frcNamespaceInstances = new HashMap();
        this.customHeaders = new HashMap();
        this.context = context2;
        this.executorService = executorService2;
        this.firebaseApp = firebaseApp2;
        this.firebaseInstanceId = firebaseInstanceId2;
        this.firebaseAbt = firebaseABTesting;
        this.analyticsConnector = analyticsConnector2;
        this.appId = firebaseApp2.getOptions().getApplicationId();
        if (z) {
            Tasks.call(executorService2, RemoteConfigComponent$$Lambda$1.lambdaFactory$(this));
            legacyConfigsHandler.getClass();
            Tasks.call(executorService2, RemoteConfigComponent$$Lambda$4.lambdaFactory$(legacyConfigsHandler));
        }
    }

    /* access modifiers changed from: package-private */
    public FirebaseRemoteConfig getDefault() {
        return get(DEFAULT_NAMESPACE);
    }

    public synchronized FirebaseRemoteConfig get(String str) {
        ConfigCacheClient cacheClient;
        ConfigCacheClient cacheClient2;
        ConfigCacheClient cacheClient3;
        ConfigMetadataClient metadataClient;
        cacheClient = getCacheClient(str, FETCH_FILE_NAME);
        cacheClient2 = getCacheClient(str, ACTIVATE_FILE_NAME);
        cacheClient3 = getCacheClient(str, DEFAULTS_FILE_NAME);
        metadataClient = getMetadataClient(this.context, this.appId, str);
        return get(this.firebaseApp, str, this.firebaseAbt, this.executorService, cacheClient, cacheClient2, cacheClient3, getFetchHandler(str, cacheClient, metadataClient), getGetHandler(cacheClient2, cacheClient3), metadataClient);
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseRemoteConfig get(FirebaseApp firebaseApp2, String str, FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        FirebaseRemoteConfig firebaseRemoteConfig;
        String str2 = str;
        synchronized (this) {
            if (!this.frcNamespaceInstances.containsKey(str2)) {
                FirebaseRemoteConfig firebaseRemoteConfig2 = new FirebaseRemoteConfig(this.context, firebaseApp2, isAbtSupported(firebaseApp2, str) ? firebaseABTesting : null, executor, configCacheClient, configCacheClient2, configCacheClient3, configFetchHandler, configGetParameterHandler, configMetadataClient);
                firebaseRemoteConfig2.startLoadingConfigsFromDisk();
                this.frcNamespaceInstances.put(str2, firebaseRemoteConfig2);
            }
            firebaseRemoteConfig = this.frcNamespaceInstances.get(str2);
        }
        return firebaseRemoteConfig;
    }

    public synchronized void setCustomHeaders(Map<String, String> map) {
        this.customHeaders = map;
    }

    private ConfigCacheClient getCacheClient(String str, String str2) {
        return getCacheClient(this.context, this.appId, str, str2);
    }

    public static ConfigCacheClient getCacheClient(Context context2, String str, String str2, String str3) {
        return ConfigCacheClient.getInstance(Executors.newCachedThreadPool(), ConfigStorageClient.getInstance(context2, String.format("%s_%s_%s_%s.json", new Object[]{"frc", str, str2, str3})));
    }

    /* access modifiers changed from: package-private */
    public ConfigFetchHttpClient getFrcBackendApiClient(String str, String str2, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHttpClient(this.context, this.firebaseApp.getOptions().getApplicationId(), str, str2, configMetadataClient.getFetchTimeoutInSeconds(), 60);
    }

    /* access modifiers changed from: package-private */
    public synchronized ConfigFetchHandler getFetchHandler(String str, ConfigCacheClient configCacheClient, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHandler(this.firebaseInstanceId, isPrimaryApp(this.firebaseApp) ? this.analyticsConnector : null, this.executorService, DEFAULT_CLOCK, DEFAULT_RANDOM, configCacheClient, getFrcBackendApiClient(this.firebaseApp.getOptions().getApiKey(), str, configMetadataClient), configMetadataClient, this.customHeaders);
    }

    private ConfigGetParameterHandler getGetHandler(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        return new ConfigGetParameterHandler(configCacheClient, configCacheClient2);
    }

    static ConfigMetadataClient getMetadataClient(Context context2, String str, String str2) {
        return new ConfigMetadataClient(context2.getSharedPreferences(String.format("%s_%s_%s_%s", new Object[]{"frc", str, str2, "settings"}), 0));
    }

    private static boolean isAbtSupported(FirebaseApp firebaseApp2, String str) {
        return str.equals(DEFAULT_NAMESPACE) && isPrimaryApp(firebaseApp2);
    }

    private static boolean isPrimaryApp(FirebaseApp firebaseApp2) {
        return firebaseApp2.getName().equals(FirebaseApp.DEFAULT_APP_NAME);
    }
}
