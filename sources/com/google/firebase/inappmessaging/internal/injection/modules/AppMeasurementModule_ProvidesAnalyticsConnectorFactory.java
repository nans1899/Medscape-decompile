package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.analytics.connector.AnalyticsConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class AppMeasurementModule_ProvidesAnalyticsConnectorFactory implements Factory<AnalyticsConnector> {
    private final AppMeasurementModule module;

    public AppMeasurementModule_ProvidesAnalyticsConnectorFactory(AppMeasurementModule appMeasurementModule) {
        this.module = appMeasurementModule;
    }

    public AnalyticsConnector get() {
        return providesAnalyticsConnector(this.module);
    }

    public static AppMeasurementModule_ProvidesAnalyticsConnectorFactory create(AppMeasurementModule appMeasurementModule) {
        return new AppMeasurementModule_ProvidesAnalyticsConnectorFactory(appMeasurementModule);
    }

    public static AnalyticsConnector providesAnalyticsConnector(AppMeasurementModule appMeasurementModule) {
        return (AnalyticsConnector) Preconditions.checkNotNull(appMeasurementModule.providesAnalyticsConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
