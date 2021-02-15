package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$11 implements Continuation {
    private final FirebaseRemoteConfig arg$1;

    private FirebaseRemoteConfig$$Lambda$11(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.arg$1 = firebaseRemoteConfig;
    }

    public static Continuation lambdaFactory$(FirebaseRemoteConfig firebaseRemoteConfig) {
        return new FirebaseRemoteConfig$$Lambda$11(firebaseRemoteConfig);
    }

    public Object then(Task task) {
        return Boolean.valueOf(this.arg$1.processActivatePutTask(task));
    }
}
