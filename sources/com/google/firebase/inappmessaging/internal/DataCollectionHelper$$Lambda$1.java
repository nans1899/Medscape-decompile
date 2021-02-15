package com.google.firebase.inappmessaging.internal;

import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DataCollectionHelper$$Lambda$1 implements EventHandler {
    private final DataCollectionHelper arg$1;

    private DataCollectionHelper$$Lambda$1(DataCollectionHelper dataCollectionHelper) {
        this.arg$1 = dataCollectionHelper;
    }

    public static EventHandler lambdaFactory$(DataCollectionHelper dataCollectionHelper) {
        return new DataCollectionHelper$$Lambda$1(dataCollectionHelper);
    }

    public void handle(Event event) {
        this.arg$1.isGlobalAutomaticDataCollectionEnabled.set(((DataCollectionDefaultChange) event.getPayload()).enabled);
    }
}
