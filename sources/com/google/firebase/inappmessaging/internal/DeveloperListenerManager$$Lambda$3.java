package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.InAppMessage;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DeveloperListenerManager$$Lambda$3 implements Runnable {
    private final DeveloperListenerManager.ClicksExecutorAndListener arg$1;
    private final InAppMessage arg$2;
    private final Action arg$3;

    private DeveloperListenerManager$$Lambda$3(DeveloperListenerManager.ClicksExecutorAndListener clicksExecutorAndListener, InAppMessage inAppMessage, Action action) {
        this.arg$1 = clicksExecutorAndListener;
        this.arg$2 = inAppMessage;
        this.arg$3 = action;
    }

    public static Runnable lambdaFactory$(DeveloperListenerManager.ClicksExecutorAndListener clicksExecutorAndListener, InAppMessage inAppMessage, Action action) {
        return new DeveloperListenerManager$$Lambda$3(clicksExecutorAndListener, inAppMessage, action);
    }

    public void run() {
        this.arg$1.getListener().messageClicked(this.arg$2, this.arg$3);
    }
}
