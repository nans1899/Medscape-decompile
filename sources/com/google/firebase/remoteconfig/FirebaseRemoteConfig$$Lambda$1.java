package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$1 implements Callable {
    private final FirebaseRemoteConfig arg$1;

    private FirebaseRemoteConfig$$Lambda$1(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.arg$1 = firebaseRemoteConfig;
    }

    public static Callable lambdaFactory$(FirebaseRemoteConfig firebaseRemoteConfig) {
        return new FirebaseRemoteConfig$$Lambda$1(firebaseRemoteConfig);
    }

    public Object call() {
        return this.arg$1.getInfo();
    }
}
