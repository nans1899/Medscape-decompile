package com.google.firebase.inappmessaging.internal;

import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class DataCollectionHelper {
    static final String AUTO_INIT_PREFERENCES = "auto_init";
    static final String MANIFEST_METADATA_AUTO_INIT_ENABLED = "firebase_inapp_messaging_auto_data_collection_enabled";
    private AtomicBoolean isGlobalAutomaticDataCollectionEnabled;
    private SharedPreferencesUtils sharedPreferencesUtils;

    @Inject
    public DataCollectionHelper(FirebaseApp firebaseApp, SharedPreferencesUtils sharedPreferencesUtils2, FirebaseInstanceId firebaseInstanceId, Subscriber subscriber) {
        this.sharedPreferencesUtils = sharedPreferencesUtils2;
        this.isGlobalAutomaticDataCollectionEnabled = new AtomicBoolean(firebaseApp.isDataCollectionDefaultEnabled());
        if (isAutomaticDataCollectionEnabled()) {
            firebaseInstanceId.getToken();
        }
        subscriber.subscribe(DataCollectionDefaultChange.class, DataCollectionHelper$$Lambda$1.lambdaFactory$(this));
    }

    public boolean isAutomaticDataCollectionEnabled() {
        if (isProductManuallySet()) {
            return this.sharedPreferencesUtils.getBooleanPreference(AUTO_INIT_PREFERENCES, true);
        }
        if (isProductManifestSet()) {
            return this.sharedPreferencesUtils.getBooleanManifestValue(MANIFEST_METADATA_AUTO_INIT_ENABLED, true);
        }
        return this.isGlobalAutomaticDataCollectionEnabled.get();
    }

    public void setAutomaticDataCollectionEnabled(boolean z) {
        this.sharedPreferencesUtils.setBooleanPreference(AUTO_INIT_PREFERENCES, z);
    }

    private boolean readAutomaticDataCollectionEnabledFromPreferences() {
        return this.sharedPreferencesUtils.getBooleanPreference(AUTO_INIT_PREFERENCES, true);
    }

    private boolean isProductManuallySet() {
        return this.sharedPreferencesUtils.isPreferenceSet(AUTO_INIT_PREFERENCES);
    }

    private boolean isProductManifestSet() {
        return this.sharedPreferencesUtils.isManifestSet(MANIFEST_METADATA_AUTO_INIT_ENABLED);
    }
}
