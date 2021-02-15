package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.events.Subscriber;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class AppMeasurementModule_ProvidesSubsriberFactory implements Factory<Subscriber> {
    private final AppMeasurementModule module;

    public AppMeasurementModule_ProvidesSubsriberFactory(AppMeasurementModule appMeasurementModule) {
        this.module = appMeasurementModule;
    }

    public Subscriber get() {
        return providesSubsriber(this.module);
    }

    public static AppMeasurementModule_ProvidesSubsriberFactory create(AppMeasurementModule appMeasurementModule) {
        return new AppMeasurementModule_ProvidesSubsriberFactory(appMeasurementModule);
    }

    public static Subscriber providesSubsriber(AppMeasurementModule appMeasurementModule) {
        return (Subscriber) Preconditions.checkNotNull(appMeasurementModule.providesSubsriber(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
