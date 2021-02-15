package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.inappmessaging.internal.AnalyticsEventsManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory implements Factory<ConnectableFlowable<String>> {
    private final Provider<AnalyticsEventsManager> analyticsEventsManagerProvider;
    private final AnalyticsEventsModule module;

    public AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory(AnalyticsEventsModule analyticsEventsModule, Provider<AnalyticsEventsManager> provider) {
        this.module = analyticsEventsModule;
        this.analyticsEventsManagerProvider = provider;
    }

    public ConnectableFlowable<String> get() {
        return providesAnalyticsConnectorEvents(this.module, this.analyticsEventsManagerProvider.get());
    }

    public static AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory create(AnalyticsEventsModule analyticsEventsModule, Provider<AnalyticsEventsManager> provider) {
        return new AnalyticsEventsModule_ProvidesAnalyticsConnectorEventsFactory(analyticsEventsModule, provider);
    }

    public static ConnectableFlowable<String> providesAnalyticsConnectorEvents(AnalyticsEventsModule analyticsEventsModule, AnalyticsEventsManager analyticsEventsManager) {
        return (ConnectableFlowable) Preconditions.checkNotNull(analyticsEventsModule.providesAnalyticsConnectorEvents(analyticsEventsManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
