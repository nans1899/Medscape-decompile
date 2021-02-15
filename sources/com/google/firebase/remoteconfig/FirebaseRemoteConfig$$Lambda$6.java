package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$6 implements SuccessContinuation {
    private static final FirebaseRemoteConfig$$Lambda$6 instance = new FirebaseRemoteConfig$$Lambda$6();

    private FirebaseRemoteConfig$$Lambda$6() {
    }

    public static SuccessContinuation lambdaFactory$() {
        return instance;
    }

    public Task then(Object obj) {
        return Tasks.forResult(null);
    }
}
