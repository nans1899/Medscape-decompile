package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class SystemClockModule_ProvidesSystemClockModuleFactory implements Factory<Clock> {
    private final SystemClockModule module;

    public SystemClockModule_ProvidesSystemClockModuleFactory(SystemClockModule systemClockModule) {
        this.module = systemClockModule;
    }

    public Clock get() {
        return providesSystemClockModule(this.module);
    }

    public static SystemClockModule_ProvidesSystemClockModuleFactory create(SystemClockModule systemClockModule) {
        return new SystemClockModule_ProvidesSystemClockModuleFactory(systemClockModule);
    }

    public static Clock providesSystemClockModule(SystemClockModule systemClockModule) {
        return (Clock) Preconditions.checkNotNull(systemClockModule.providesSystemClockModule(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
