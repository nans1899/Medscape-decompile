package com.google.firebase.inappmessaging.internal.time;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class SystemClock implements Clock {
    public long now() {
        return System.currentTimeMillis();
    }
}
