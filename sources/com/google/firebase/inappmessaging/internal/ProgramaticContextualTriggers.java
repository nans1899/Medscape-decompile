package com.google.firebase.inappmessaging.internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ProgramaticContextualTriggers {
    private Listener listener;

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface Listener {
        void onEventTrigger(String str);
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public void removeListener(Listener listener2) {
        this.listener = null;
    }

    public void triggerEvent(String str) {
        Logging.logd("Programmatically trigger: " + str);
        this.listener.onEventTrigger(str);
    }
}
