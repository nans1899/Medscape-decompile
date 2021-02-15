package com.google.firebase.inappmessaging.display;

import android.app.Activity;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplay;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.model.InAppMessage;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
final /* synthetic */ class FirebaseInAppMessagingDisplay$$Lambda$1 implements FirebaseInAppMessagingDisplay {
    private final FirebaseInAppMessagingDisplay arg$1;
    private final Activity arg$2;

    private FirebaseInAppMessagingDisplay$$Lambda$1(FirebaseInAppMessagingDisplay firebaseInAppMessagingDisplay, Activity activity) {
        this.arg$1 = firebaseInAppMessagingDisplay;
        this.arg$2 = activity;
    }

    public static FirebaseInAppMessagingDisplay lambdaFactory$(FirebaseInAppMessagingDisplay firebaseInAppMessagingDisplay, Activity activity) {
        return new FirebaseInAppMessagingDisplay$$Lambda$1(firebaseInAppMessagingDisplay, activity);
    }

    public void displayMessage(InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks firebaseInAppMessagingDisplayCallbacks) {
        FirebaseInAppMessagingDisplay.lambda$onActivityStarted$0(this.arg$1, this.arg$2, inAppMessage, firebaseInAppMessagingDisplayCallbacks);
    }
}
