package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.remoteconfig.internal.ConfigContainer;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class FirebaseRemoteConfig$$Lambda$4 implements OnSuccessListener {
    private final FirebaseRemoteConfig arg$1;

    private FirebaseRemoteConfig$$Lambda$4(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.arg$1 = firebaseRemoteConfig;
    }

    public static OnSuccessListener lambdaFactory$(FirebaseRemoteConfig firebaseRemoteConfig) {
        return new FirebaseRemoteConfig$$Lambda$4(firebaseRemoteConfig);
    }

    public void onSuccess(Object obj) {
        FirebaseRemoteConfig.lambda$activateFetched$2(this.arg$1, (ConfigContainer) obj);
    }
}
