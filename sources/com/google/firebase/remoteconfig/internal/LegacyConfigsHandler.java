package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.proto.ConfigPersistence;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import developers.mobile.abt.FirebaseAbt;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public class LegacyConfigsHandler {
    static final String ACTIVATE_FILE_NAME = "activate";
    static final String DEFAULTS_FILE_NAME = "defaults";
    public static final String EXPERIMENT_ID_KEY = "experimentId";
    public static final String EXPERIMENT_START_TIME_KEY = "experimentStartTime";
    public static final String EXPERIMENT_TIME_TO_LIVE_KEY = "timeToLiveMillis";
    public static final String EXPERIMENT_TRIGGER_EVENT_KEY = "triggerEvent";
    public static final String EXPERIMENT_TRIGGER_TIMEOUT_KEY = "triggerTimeoutMillis";
    public static final String EXPERIMENT_VARIANT_ID_KEY = "variantId";
    static final String FETCH_FILE_NAME = "fetch";
    private static final String FRC_3P_NAMESPACE = "firebase";
    static final String LEGACY_CONFIGS_FILE_NAME = "persisted_config";
    static final String LEGACY_FRC_NAMESPACE_PREFIX = "configns:";
    private static final String LEGACY_SETTINGS_FILE_NAME = "com.google.firebase.remoteconfig_legacy_settings";
    private static final Charset PROTO_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
    private static final String SAVE_LEGACY_CONFIGS_FLAG_NAME = "save_legacy_configs";
    static final ThreadLocal<DateFormat> protoTimestampStringParser = new ThreadLocal<DateFormat>() {
        /* access modifiers changed from: protected */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        }
    };
    private final String appId;
    private final Context context;
    private final SharedPreferences legacySettings;

    public LegacyConfigsHandler(Context context2, String str) {
        this.context = context2;
        this.appId = str;
        this.legacySettings = context2.getSharedPreferences(LEGACY_SETTINGS_FILE_NAME, 0);
    }

    public boolean saveLegacyConfigsIfNecessary() {
        if (!this.legacySettings.getBoolean(SAVE_LEGACY_CONFIGS_FLAG_NAME, true)) {
            return false;
        }
        saveLegacyConfigs(getConvertedLegacyConfigs());
        this.legacySettings.edit().putBoolean(SAVE_LEGACY_CONFIGS_FLAG_NAME, false).commit();
        return true;
    }

    private void saveLegacyConfigs(Map<String, NamespaceLegacyConfigs> map) {
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            NamespaceLegacyConfigs namespaceLegacyConfigs = (NamespaceLegacyConfigs) next.getValue();
            ConfigCacheClient cacheClient = getCacheClient(str, "fetch");
            ConfigCacheClient cacheClient2 = getCacheClient(str, "activate");
            ConfigCacheClient cacheClient3 = getCacheClient(str, "defaults");
            if (namespaceLegacyConfigs.getFetchedConfigs() != null) {
                cacheClient.put(namespaceLegacyConfigs.getFetchedConfigs());
            }
            if (namespaceLegacyConfigs.getActivatedConfigs() != null) {
                cacheClient2.put(namespaceLegacyConfigs.getActivatedConfigs());
            }
            if (namespaceLegacyConfigs.getDefaultsConfigs() != null) {
                cacheClient3.put(namespaceLegacyConfigs.getDefaultsConfigs());
            }
        }
    }

    private Map<String, NamespaceLegacyConfigs> getConvertedLegacyConfigs() {
        ConfigPersistence.PersistedConfig readPersistedConfig = readPersistedConfig();
        HashMap hashMap = new HashMap();
        if (readPersistedConfig == null) {
            return hashMap;
        }
        Map<String, ConfigContainer> convertConfigHolder = convertConfigHolder(readPersistedConfig.getActiveConfigHolder());
        Map<String, ConfigContainer> convertConfigHolder2 = convertConfigHolder(readPersistedConfig.getFetchedConfigHolder());
        Map<String, ConfigContainer> convertConfigHolder3 = convertConfigHolder(readPersistedConfig.getDefaultsConfigHolder());
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(convertConfigHolder.keySet());
        hashSet.addAll(convertConfigHolder2.keySet());
        hashSet.addAll(convertConfigHolder3.keySet());
        for (String str : hashSet) {
            NamespaceLegacyConfigs namespaceLegacyConfigs = new NamespaceLegacyConfigs();
            if (convertConfigHolder.containsKey(str)) {
                namespaceLegacyConfigs.setActivatedConfigs(convertConfigHolder.get(str));
            }
            if (convertConfigHolder2.containsKey(str)) {
                namespaceLegacyConfigs.setFetchedConfigs(convertConfigHolder2.get(str));
            }
            if (convertConfigHolder3.containsKey(str)) {
                namespaceLegacyConfigs.setDefaultsConfigs(convertConfigHolder3.get(str));
            }
            hashMap.put(str, namespaceLegacyConfigs);
        }
        return hashMap;
    }

    private Map<String, ConfigContainer> convertConfigHolder(ConfigPersistence.ConfigHolder configHolder) {
        HashMap hashMap = new HashMap();
        Date date = new Date(configHolder.getTimestamp());
        JSONArray convertLegacyAbtExperiments = convertLegacyAbtExperiments(configHolder.getExperimentPayloadList());
        for (ConfigPersistence.NamespaceKeyValue next : configHolder.getNamespaceKeyValueList()) {
            String namespace = next.getNamespace();
            if (namespace.startsWith(LEGACY_FRC_NAMESPACE_PREFIX)) {
                namespace = namespace.substring(9);
            }
            ConfigContainer.Builder withFetchTime = ConfigContainer.newBuilder().replaceConfigsWith(convertKeyValueList(next.getKeyValueList())).withFetchTime(date);
            if (namespace.equals("firebase")) {
                withFetchTime.withAbtExperiments(convertLegacyAbtExperiments);
            }
            try {
                hashMap.put(namespace, withFetchTime.build());
            } catch (JSONException unused) {
                Log.d(FirebaseRemoteConfig.TAG, "A set of legacy configs could not be converted.");
            }
        }
        return hashMap;
    }

    private JSONArray convertLegacyAbtExperiments(List<ByteString> list) {
        JSONArray jSONArray = new JSONArray();
        for (ByteString deserializePayload : list) {
            FirebaseAbt.ExperimentPayload deserializePayload2 = deserializePayload(deserializePayload);
            if (deserializePayload2 != null) {
                try {
                    jSONArray.put(convertLegacyAbtExperiment(deserializePayload2));
                } catch (JSONException e) {
                    Log.d(FirebaseRemoteConfig.TAG, "A legacy ABT experiment could not be parsed.", e);
                }
            }
        }
        return jSONArray;
    }

    private FirebaseAbt.ExperimentPayload deserializePayload(ByteString byteString) {
        try {
            ByteString.ByteIterator it = byteString.iterator();
            int size = byteString.size();
            byte[] bArr = new byte[size];
            for (int i = 0; i < size; i++) {
                bArr[i] = ((Byte) it.next()).byteValue();
            }
            return FirebaseAbt.ExperimentPayload.parseFrom(bArr);
        } catch (InvalidProtocolBufferException e) {
            Log.d(FirebaseRemoteConfig.TAG, "Payload was not defined or could not be deserialized.", e);
            return null;
        }
    }

    private JSONObject convertLegacyAbtExperiment(FirebaseAbt.ExperimentPayload experimentPayload) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("experimentId", experimentPayload.getExperimentId());
        jSONObject.put("variantId", experimentPayload.getVariantId());
        jSONObject.put(EXPERIMENT_START_TIME_KEY, protoTimestampStringParser.get().format(new Date(experimentPayload.getExperimentStartTimeMillis())));
        jSONObject.put(EXPERIMENT_TRIGGER_EVENT_KEY, experimentPayload.getTriggerEvent());
        jSONObject.put(EXPERIMENT_TRIGGER_TIMEOUT_KEY, experimentPayload.getTriggerTimeoutMillis());
        jSONObject.put(EXPERIMENT_TIME_TO_LIVE_KEY, experimentPayload.getTimeToLiveMillis());
        return jSONObject;
    }

    private Map<String, String> convertKeyValueList(List<ConfigPersistence.KeyValue> list) {
        HashMap hashMap = new HashMap();
        for (ConfigPersistence.KeyValue next : list) {
            hashMap.put(next.getKey(), next.getValue().toString(PROTO_BYTE_ARRAY_ENCODING));
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0031 A[SYNTHETIC, Splitter:B:24:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0043 A[SYNTHETIC, Splitter:B:35:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x004f A[SYNTHETIC, Splitter:B:42:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfig readPersistedConfig() {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to close persisted config file."
            java.lang.String r1 = "FirebaseRemoteConfig"
            android.content.Context r2 = r7.context
            r3 = 0
            if (r2 != 0) goto L_0x000a
            return r3
        L_0x000a:
            java.lang.String r4 = "persisted_config"
            java.io.FileInputStream r2 = r2.openFileInput(r4)     // Catch:{ FileNotFoundException -> 0x003a, IOException -> 0x0028, all -> 0x0023 }
            com.google.firebase.remoteconfig.proto.ConfigPersistence$PersistedConfig r3 = com.google.firebase.remoteconfig.proto.ConfigPersistence.PersistedConfig.parseFrom((java.io.InputStream) r2)     // Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x001f }
            if (r2 == 0) goto L_0x001e
            r2.close()     // Catch:{ IOException -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            android.util.Log.d(r1, r0, r2)
        L_0x001e:
            return r3
        L_0x001f:
            r4 = move-exception
            goto L_0x002a
        L_0x0021:
            r4 = move-exception
            goto L_0x003c
        L_0x0023:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x004d
        L_0x0028:
            r4 = move-exception
            r2 = r3
        L_0x002a:
            java.lang.String r5 = "Cannot initialize from persisted config."
            android.util.Log.d(r1, r5, r4)     // Catch:{ all -> 0x004c }
            if (r2 == 0) goto L_0x0039
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r2 = move-exception
            android.util.Log.d(r1, r0, r2)
        L_0x0039:
            return r3
        L_0x003a:
            r4 = move-exception
            r2 = r3
        L_0x003c:
            java.lang.String r5 = "Persisted config file was not found."
            android.util.Log.d(r1, r5, r4)     // Catch:{ all -> 0x004c }
            if (r2 == 0) goto L_0x004b
            r2.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r2 = move-exception
            android.util.Log.d(r1, r0, r2)
        L_0x004b:
            return r3
        L_0x004c:
            r3 = move-exception
        L_0x004d:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r2 = move-exception
            android.util.Log.d(r1, r0, r2)
        L_0x0057:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.LegacyConfigsHandler.readPersistedConfig():com.google.firebase.remoteconfig.proto.ConfigPersistence$PersistedConfig");
    }

    /* access modifiers changed from: package-private */
    public ConfigCacheClient getCacheClient(String str, String str2) {
        return RemoteConfigComponent.getCacheClient(this.context, this.appId, str, str2);
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    private static class NamespaceLegacyConfigs {
        private ConfigContainer activatedConfigs;
        private ConfigContainer defaultsConfigs;
        private ConfigContainer fetchedConfigs;

        private NamespaceLegacyConfigs() {
        }

        /* access modifiers changed from: private */
        public void setFetchedConfigs(ConfigContainer configContainer) {
            this.fetchedConfigs = configContainer;
        }

        /* access modifiers changed from: private */
        public void setActivatedConfigs(ConfigContainer configContainer) {
            this.activatedConfigs = configContainer;
        }

        /* access modifiers changed from: private */
        public void setDefaultsConfigs(ConfigContainer configContainer) {
            this.defaultsConfigs = configContainer;
        }

        /* access modifiers changed from: private */
        public ConfigContainer getFetchedConfigs() {
            return this.fetchedConfigs;
        }

        /* access modifiers changed from: private */
        public ConfigContainer getActivatedConfigs() {
            return this.activatedConfigs;
        }

        /* access modifiers changed from: private */
        public ConfigContainer getDefaultsConfigs() {
            return this.defaultsConfigs;
        }
    }
}
