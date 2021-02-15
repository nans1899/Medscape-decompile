package com.google.firebase.inappmessaging.model;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class TriggeredInAppMessage {
    private InAppMessage inAppMessage;
    private String triggeringEvent;

    public TriggeredInAppMessage(InAppMessage inAppMessage2, String str) {
        this.inAppMessage = inAppMessage2;
        this.triggeringEvent = str;
    }

    public InAppMessage getInAppMessage() {
        return this.inAppMessage;
    }

    public String getTriggeringEvent() {
        return this.triggeringEvent;
    }
}
