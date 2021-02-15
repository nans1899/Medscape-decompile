package com.google.firebase.remoteconfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public class RemoteConfigConstants {
    public static final String FETCH_REGEX_URL = "https://firebaseremoteconfig.googleapis.com/v1/projects/%s/namespaces/%s:fetch";

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public @interface ExperimentDescriptionFieldKey {
        public static final String EXPERIMENT_ID = "experimentId";
        public static final String VARIANT_ID = "variantId";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public @interface RequestFieldKey {
        public static final String ANALYTICS_USER_PROPERTIES = "analyticsUserProperties";
        public static final String APP_ID = "appId";
        public static final String APP_VERSION = "appVersion";
        public static final String COUNTRY_CODE = "countryCode";
        public static final String INSTANCE_ID = "appInstanceId";
        public static final String INSTANCE_ID_TOKEN = "appInstanceIdToken";
        public static final String LANGUAGE_CODE = "languageCode";
        public static final String PACKAGE_NAME = "packageName";
        public static final String PLATFORM_VERSION = "platformVersion";
        public static final String SDK_VERSION = "sdkVersion";
        public static final String TIME_ZONE = "timeZone";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public @interface ResponseFieldKey {
        public static final String ENTRIES = "entries";
        public static final String EXPERIMENT_DESCRIPTIONS = "experimentDescriptions";
        public static final String STATE = "state";
    }

    private RemoteConfigConstants() {
    }
}
