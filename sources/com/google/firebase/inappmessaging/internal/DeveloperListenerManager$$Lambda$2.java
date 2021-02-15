package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.model.InAppMessage;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DeveloperListenerManager$$Lambda$2 implements Runnable {
    private final DeveloperListenerManager.ErrorsExecutorAndListener arg$1;
    private final InAppMessage arg$2;
    private final FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason arg$3;

    private DeveloperListenerManager$$Lambda$2(DeveloperListenerManager.ErrorsExecutorAndListener errorsExecutorAndListener, InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        this.arg$1 = errorsExecutorAndListener;
        this.arg$2 = inAppMessage;
        this.arg$3 = inAppMessagingErrorReason;
    }

    public static Runnable lambdaFactory$(DeveloperListenerManager.ErrorsExecutorAndListener errorsExecutorAndListener, InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        return new DeveloperListenerManager$$Lambda$2(errorsExecutorAndListener, inAppMessage, inAppMessagingErrorReason);
    }

    public void run() {
        this.arg$1.getListener().displayErrorEncountered(this.arg$2, this.arg$3);
    }
}
