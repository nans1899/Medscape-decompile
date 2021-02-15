package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.model.TriggeredInAppMessage;
import io.reactivex.functions.Consumer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class FirebaseInAppMessaging$$Lambda$1 implements Consumer {
    private final FirebaseInAppMessaging arg$1;

    private FirebaseInAppMessaging$$Lambda$1(FirebaseInAppMessaging firebaseInAppMessaging) {
        this.arg$1 = firebaseInAppMessaging;
    }

    public static Consumer lambdaFactory$(FirebaseInAppMessaging firebaseInAppMessaging) {
        return new FirebaseInAppMessaging$$Lambda$1(firebaseInAppMessaging);
    }

    public void accept(Object obj) {
        this.arg$1.triggerInAppMessage((TriggeredInAppMessage) obj);
    }
}
