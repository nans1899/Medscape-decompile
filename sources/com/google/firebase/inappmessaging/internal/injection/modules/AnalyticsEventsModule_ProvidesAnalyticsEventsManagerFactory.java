package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inappmessaging.internal.AnalyticsEventsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory implements Factory<AnalyticsEventsManager> {
    private final Provider<AnalyticsConnector> analyticsConnectorProvider;
    private final AnalyticsEventsModule module;

    public AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory(AnalyticsEventsModule analyticsEventsModule, Provider<AnalyticsConnector> provider) {
        this.module = analyticsEventsModule;
        this.analyticsConnectorProvider = provider;
    }

    public AnalyticsEventsManager get() {
        return providesAnalyticsEventsManager(this.module, this.analyticsConnectorProvider.get());
    }

    public static AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory create(AnalyticsEventsModule analyticsEventsModule, Provider<AnalyticsConnector> provider) {
        return new AnalyticsEventsModule_ProvidesAnalyticsEventsManagerFactory(analyticsEventsModule, provider);
    }

    public static AnalyticsEventsManager providesAnalyticsEventsManager(AnalyticsEventsModule analyticsEventsModule, AnalyticsConnector analyticsConnector) {
        return (AnalyticsEventsManager) Preconditions.checkNotNull(analyticsEventsModule.providesAnalyticsEventsManager(analyticsConnector), "Cannot return null from a non-@Nullable @Provides method");
    }
}
