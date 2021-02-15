package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$3 implements SuccessContinuation {
    private final FirebaseRemoteConfig arg$1;

    private FirebaseRemoteConfig$$Lambda$3(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.arg$1 = firebaseRemoteConfig;
    }

    public static SuccessContinuation lambdaFactory$(FirebaseRemoteConfig firebaseRemoteConfig) {
        return new FirebaseRemoteConfig$$Lambda$3(firebaseRemoteConfig);
    }

    public Task then(Object obj) {
        return this.arg$1.activate();
    }
}
