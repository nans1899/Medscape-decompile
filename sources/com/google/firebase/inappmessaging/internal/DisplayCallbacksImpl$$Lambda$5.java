package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import io.reactivex.functions.Action;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$5 implements Action {
    private final DisplayCallbacksImpl arg$1;
    private final FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason arg$2;

    private DisplayCallbacksImpl$$Lambda$5(DisplayCallbacksImpl displayCallbacksImpl, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        this.arg$1 = displayCallbacksImpl;
        this.arg$2 = inAppMessagingErrorReason;
    }

    public static Action lambdaFactory$(DisplayCallbacksImpl displayCallbacksImpl, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        return new DisplayCallbacksImpl$$Lambda$5(displayCallbacksImpl, inAppMessagingErrorReason);
    }

    public void run() {
        this.arg$1.metricsLoggerClient.logRenderError(this.arg$1.inAppMessage, this.arg$2);
    }
}
