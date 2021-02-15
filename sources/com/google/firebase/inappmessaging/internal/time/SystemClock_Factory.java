package com.google.firebase.inappmessaging.internal.time;

import dagger.internal.Factory;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SystemClock_Factory implements Factory<SystemClock> {
    private static final SystemClock_Factory INSTANCE = new SystemClock_Factory();

    public SystemClock get() {
        return new SystemClock();
    }

    public static SystemClock_Factory create() {
        return INSTANCE;
    }

    public static SystemClock newInstance() {
        return new SystemClock();
    }
}
