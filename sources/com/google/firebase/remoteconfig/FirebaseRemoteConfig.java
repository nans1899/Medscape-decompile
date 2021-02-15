package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final String TAG = "FirebaseRemoteConfig";
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    private final ConfigCacheClient activatedConfigsCache;
    private final Context context;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final ConfigFetchHandler fetchHandler;
    private final ConfigCacheClient fetchedConfigsCache;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final ConfigMetadataClient frcMetadata;
    private final ConfigGetParameterHandler getHandler;

    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseRemoteConfig getInstance(FirebaseApp firebaseApp2) {
        return ((RemoteConfigComponent) firebaseApp2.get(RemoteConfigComponent.class)).getDefault();
    }

    FirebaseRemoteConfig(Context context2, FirebaseApp firebaseApp2, FirebaseABTesting firebaseABTesting, Executor executor2, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        this.context = context2;
        this.firebaseApp = firebaseApp2;
        this.firebaseAbt = firebaseABTesting;
        this.executor = executor2;
        this.fetchedConfigsCache = configCacheClient;
        this.activatedConfigsCache = configCacheClient2;
        this.defaultConfigsCache = configCacheClient3;
        this.fetchHandler = configFetchHandler;
        this.getHandler = configGetParameterHandler;
        this.frcMetadata = configMetadataClient;
    }

    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<ConfigContainer> task = this.activatedConfigsCache.get();
        Task<ConfigContainer> task2 = this.defaultConfigsCache.get();
        Task<ConfigContainer> task3 = this.fetchedConfigsCache.get();
        Task call = Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$1.lambdaFactory$(this));
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2, task3, call}).continueWith(this.executor, FirebaseRemoteConfig$$Lambda$2.lambdaFactory$(call));
    }

    static /* synthetic */ FirebaseRemoteConfigInfo lambda$ensureInitialized$0(Task task, Task task2) throws Exception {
        return (FirebaseRemoteConfigInfo) task.getResult();
    }

    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.executor, FirebaseRemoteConfig$$Lambda$3.lambdaFactory$(this));
    }

    @Deprecated
    public boolean activateFetched() {
        ConfigContainer blocking = this.fetchedConfigsCache.getBlocking();
        if (blocking == null || !isFetchedFresh(blocking, this.activatedConfigsCache.getBlocking())) {
            return false;
        }
        this.activatedConfigsCache.putWithoutWaitingForDiskWrite(blocking).addOnSuccessListener(this.executor, FirebaseRemoteConfig$$Lambda$4.lambdaFactory$(this));
        return true;
    }

    static /* synthetic */ void lambda$activateFetched$2(FirebaseRemoteConfig firebaseRemoteConfig, ConfigContainer configContainer) {
        firebaseRemoteConfig.fetchedConfigsCache.clear();
        firebaseRemoteConfig.updateAbtWithActivatedExperiments(configContainer.getAbtExperiments());
    }

    public Task<Boolean> activate() {
        Task<ConfigContainer> task = this.fetchedConfigsCache.get();
        Task<ConfigContainer> task2 = this.activatedConfigsCache.get();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{task, task2}).continueWithTask(this.executor, FirebaseRemoteConfig$$Lambda$5.lambdaFactory$(this, task, task2));
    }

    static /* synthetic */ Task lambda$activate$3(FirebaseRemoteConfig firebaseRemoteConfig, Task task, Task task2, Task task3) throws Exception {
        if (!task.isSuccessful() || task.getResult() == null) {
            return Tasks.forResult(false);
        }
        ConfigContainer configContainer = (ConfigContainer) task.getResult();
        if (!task2.isSuccessful() || isFetchedFresh(configContainer, (ConfigContainer) task2.getResult())) {
            return firebaseRemoteConfig.activatedConfigsCache.put(configContainer).continueWith(firebaseRemoteConfig.executor, FirebaseRemoteConfig$$Lambda$11.lambdaFactory$(firebaseRemoteConfig));
        }
        return Tasks.forResult(false);
    }

    public Task<Void> fetch() {
        return this.fetchHandler.fetch().onSuccessTask(FirebaseRemoteConfig$$Lambda$6.lambdaFactory$());
    }

    public Task<Void> fetch(long j) {
        return this.fetchHandler.fetch(j).onSuccessTask(FirebaseRemoteConfig$$Lambda$7.lambdaFactory$());
    }

    public String getString(String str) {
        return this.getHandler.getString(str);
    }

    public boolean getBoolean(String str) {
        return this.getHandler.getBoolean(str);
    }

    @Deprecated
    public byte[] getByteArray(String str) {
        return this.getHandler.getByteArray(str);
    }

    public double getDouble(String str) {
        return this.getHandler.getDouble(str);
    }

    public long getLong(String str) {
        return this.getHandler.getLong(str);
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return this.getHandler.getValue(str);
    }

    public Set<String> getKeysByPrefix(String str) {
        return this.getHandler.getKeysByPrefix(str);
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.getHandler.getAll();
    }

    public FirebaseRemoteConfigInfo getInfo() {
        return this.frcMetadata.getInfo();
    }

    @Deprecated
    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.frcMetadata.setConfigSettingsWithoutWaitingOnDiskWrite(firebaseRemoteConfigSettings);
    }

    public Task<Void> setConfigSettingsAsync(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$8.lambdaFactory$(this, firebaseRemoteConfigSettings));
    }

    @Deprecated
    public void setDefaults(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            if (value instanceof byte[]) {
                hashMap.put((String) next.getKey(), new String((byte[]) value));
            } else {
                hashMap.put((String) next.getKey(), value.toString());
            }
        }
        setDefaultsWithStringsMap(hashMap);
    }

    public Task<Void> setDefaultsAsync(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            if (value instanceof byte[]) {
                hashMap.put((String) next.getKey(), new String((byte[]) value));
            } else {
                hashMap.put((String) next.getKey(), value.toString());
            }
        }
        return setDefaultsWithStringsMapAsync(hashMap);
    }

    @Deprecated
    public void setDefaults(int i) {
        setDefaultsWithStringsMap(DefaultsXmlParser.getDefaultsFromXml(this.context, i));
    }

    public Task<Void> setDefaultsAsync(int i) {
        return setDefaultsWithStringsMapAsync(DefaultsXmlParser.getDefaultsFromXml(this.context, i));
    }

    public Task<Void> reset() {
        return Tasks.call(this.executor, FirebaseRemoteConfig$$Lambda$9.lambdaFactory$(this));
    }

    static /* synthetic */ Void lambda$reset$7(FirebaseRemoteConfig firebaseRemoteConfig) throws Exception {
        firebaseRemoteConfig.activatedConfigsCache.clear();
        firebaseRemoteConfig.fetchedConfigsCache.clear();
        firebaseRemoteConfig.defaultConfigsCache.clear();
        firebaseRemoteConfig.frcMetadata.clear();
        return null;
    }

    /* access modifiers changed from: package-private */
    public void startLoadingConfigsFromDisk() {
        this.activatedConfigsCache.get();
        this.defaultConfigsCache.get();
        this.fetchedConfigsCache.get();
    }

    /* access modifiers changed from: private */
    public boolean processActivatePutTask(Task<ConfigContainer> task) {
        if (!task.isSuccessful()) {
            return false;
        }
        this.fetchedConfigsCache.clear();
        if (task.getResult() != null) {
            updateAbtWithActivatedExperiments(task.getResult().getAbtExperiments());
            return true;
        }
        Log.e(TAG, "Activated configs written to disk are null.");
        return true;
    }

    private void setDefaultsWithStringsMap(Map<String, String> map) {
        try {
            this.defaultConfigsCache.putWithoutWaitingForDiskWrite(ConfigContainer.newBuilder().replaceConfigsWith(map).build());
        } catch (JSONException e) {
            Log.e(TAG, "The provided defaults map could not be processed.", e);
        }
    }

    private Task<Void> setDefaultsWithStringsMapAsync(Map<String, String> map) {
        try {
            return this.defaultConfigsCache.put(ConfigContainer.newBuilder().replaceConfigsWith(map).build()).onSuccessTask(FirebaseRemoteConfig$$Lambda$10.lambdaFactory$());
        } catch (JSONException e) {
            Log.e(TAG, "The provided defaults map could not be processed.", e);
            return Tasks.forResult(null);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAbtWithActivatedExperiments(JSONArray jSONArray) {
        if (this.firebaseAbt != null) {
            try {
                this.firebaseAbt.replaceAllExperiments(toExperimentInfoMaps(jSONArray));
            } catch (JSONException e) {
                Log.e(TAG, "Could not parse ABT experiments from the JSON response.", e);
            } catch (AbtException e2) {
                Log.w(TAG, "Could not update ABT experiments.", e2);
            }
        }
    }

    static List<Map<String, String>> toExperimentInfoMaps(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private static boolean isFetchedFresh(ConfigContainer configContainer, ConfigContainer configContainer2) {
        return configContainer2 == null || !configContainer.getFetchTime().equals(configContainer2.getFetchTime());
    }
}
