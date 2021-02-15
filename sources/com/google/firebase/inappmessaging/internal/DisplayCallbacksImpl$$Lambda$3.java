package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$3 implements Action {
    private final DisplayCallbacksImpl arg$1;
    private final FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType arg$2;

    private DisplayCallbacksImpl$$Lambda$3(DisplayCallbacksImpl displayCallbacksImpl, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType inAppMessagingDismissType) {
        this.arg$1 = displayCallbacksImpl;
        this.arg$2 = inAppMessagingDismissType;
    }

    public static Action lambdaFactory$(DisplayCallbacksImpl displayCallbacksImpl, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingDismissType inAppMessagingDismissType) {
        return new DisplayCallbacksImpl$$Lambda$3(displayCallbacksImpl, inAppMessagingDismissType);
    }

    public void run() {
        this.arg$1.metricsLoggerClient.logDismiss(this.arg$1.inAppMessage, this.arg$2);
    }
}
