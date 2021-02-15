package com.google.firebase.inappmessaging.display.internal;

import dagger.internal.Factory;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class RenewableTimer_Factory implements Factory<RenewableTimer> {
    private static final RenewableTimer_Factory INSTANCE = new RenewableTimer_Factory();

    public RenewableTimer get() {
        return new RenewableTimer();
    }

    public static RenewableTimer_Factory create() {
        return INSTANCE;
    }

    public static RenewableTimer newInstance() {
        return new RenewableTimer();
    }
}
