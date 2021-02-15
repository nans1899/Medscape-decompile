package com.google.firebase.inappmessaging;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.DisplayCallbacksFactory;
import com.google.firebase.inappmessaging.internal.InAppMessageStreamManager;
import com.google.firebase.inappmessaging.internal.Logging;
import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.ProgrammaticTrigger;
import com.google.firebase.inappmessaging.model.TriggeredInAppMessage;
import java.util.concurrent.Executor;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class FirebaseInAppMessaging {
    private boolean areMessagesSuppressed = false;
    private final DataCollectionHelper dataCollectionHelper;
    private final DeveloperListenerManager developerListenerManager;
    private final DisplayCallbacksFactory displayCallbacksFactory;
    private FirebaseInAppMessagingDisplay fiamDisplay;
    private final InAppMessageStreamManager inAppMessageStreamManager;
    private final ProgramaticContextualTriggers programaticContextualTriggers;

    @Inject
    FirebaseInAppMessaging(InAppMessageStreamManager inAppMessageStreamManager2, @ProgrammaticTrigger ProgramaticContextualTriggers programaticContextualTriggers2, DataCollectionHelper dataCollectionHelper2, DisplayCallbacksFactory displayCallbacksFactory2, DeveloperListenerManager developerListenerManager2) {
        this.inAppMessageStreamManager = inAppMessageStreamManager2;
        this.programaticContextualTriggers = programaticContextualTriggers2;
        this.dataCollectionHelper = dataCollectionHelper2;
        this.displayCallbacksFactory = displayCallbacksFactory2;
        this.developerListenerManager = developerListenerManager2;
        Logging.logi("Starting InAppMessaging runtime with Instance ID " + FirebaseInstanceId.getInstance().getId());
        inAppMessageStreamManager2.createFirebaseInAppMessageStream().subscribe(FirebaseInAppMessaging$$Lambda$1.lambdaFactory$(this));
    }

    public static FirebaseInAppMessaging getInstance() {
        return (FirebaseInAppMessaging) FirebaseApp.getInstance().get(FirebaseInAppMessaging.class);
    }

    public boolean isAutomaticDataCollectionEnabled() {
        return this.dataCollectionHelper.isAutomaticDataCollectionEnabled();
    }

    public void setAutomaticDataCollectionEnabled(boolean z) {
        this.dataCollectionHelper.setAutomaticDataCollectionEnabled(z);
    }

    public void setMessagesSuppressed(Boolean bool) {
        this.areMessagesSuppressed = bool.booleanValue();
    }

    public boolean areMessagesSuppressed() {
        return this.areMessagesSuppressed;
    }

    public void setMessageDisplayComponent(FirebaseInAppMessagingDisplay firebaseInAppMessagingDisplay) {
        Logging.logi("Setting display event component");
        this.fiamDisplay = firebaseInAppMessagingDisplay;
    }

    public void clearDisplayListener() {
        Logging.logi("Removing display event component");
        this.fiamDisplay = null;
    }

    public void addImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener) {
        this.developerListenerManager.addImpressionListener(firebaseInAppMessagingImpressionListener);
    }

    public void addClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener) {
        this.developerListenerManager.addClickListener(firebaseInAppMessagingClickListener);
    }

    public void addDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener) {
        this.developerListenerManager.addDisplayErrorListener(firebaseInAppMessagingDisplayErrorListener);
    }

    public void addImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener, Executor executor) {
        this.developerListenerManager.addImpressionListener(firebaseInAppMessagingImpressionListener, executor);
    }

    public void addClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener, Executor executor) {
        this.developerListenerManager.addClickListener(firebaseInAppMessagingClickListener, executor);
    }

    public void addDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener, Executor executor) {
        this.developerListenerManager.addDisplayErrorListener(firebaseInAppMessagingDisplayErrorListener, executor);
    }

    public void removeImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener) {
        this.developerListenerManager.removeImpressionListener(firebaseInAppMessagingImpressionListener);
    }

    public void removeClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener) {
        this.developerListenerManager.removeClickListener(firebaseInAppMessagingClickListener);
    }

    public void removeDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener) {
        this.developerListenerManager.removeDisplayErrorListener(firebaseInAppMessagingDisplayErrorListener);
    }

    public void triggerEvent(String str) {
        this.programaticContextualTriggers.triggerEvent(str);
    }

    /* access modifiers changed from: private */
    public void triggerInAppMessage(TriggeredInAppMessage triggeredInAppMessage) {
        FirebaseInAppMessagingDisplay firebaseInAppMessagingDisplay = this.fiamDisplay;
        if (firebaseInAppMessagingDisplay != null) {
            firebaseInAppMessagingDisplay.displayMessage(triggeredInAppMessage.getInAppMessage(), this.displayCallbacksFactory.generateDisplayCallback(triggeredInAppMessage.getInAppMessage(), triggeredInAppMessage.getTriggeringEvent()));
        }
    }
}
