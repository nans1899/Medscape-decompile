package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$8 implements Callable {
    private final FirebaseRemoteConfig arg$1;
    private final FirebaseRemoteConfigSettings arg$2;

    private FirebaseRemoteConfig$$Lambda$8(FirebaseRemoteConfig firebaseRemoteConfig, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.arg$1 = firebaseRemoteConfig;
        this.arg$2 = firebaseRemoteConfigSettings;
    }

    public static Callable lambdaFactory$(FirebaseRemoteConfig firebaseRemoteConfig, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return new FirebaseRemoteConfig$$Lambda$8(firebaseRemoteConfig, firebaseRemoteConfigSettings);
    }

    public Object call() {
        return this.arg$1.frcMetadata.setConfigSettings(this.arg$2);
    }
}
