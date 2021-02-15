package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.MetricsLoggerClient;
import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class TransportClientModule_ProvidesApiClientFactory implements Factory<MetricsLoggerClient> {
    private final Provider<AnalyticsConnector> analyticsConnectorProvider;
    private final Provider<FirebaseApp> appProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<DeveloperListenerManager> developerListenerManagerProvider;
    private final Provider<FirebaseInstanceId> firebaseInstanceIdProvider;
    private final Provider<TransportFactory> transportFactoryProvider;

    public TransportClientModule_ProvidesApiClientFactory(Provider<FirebaseApp> provider, Provider<TransportFactory> provider2, Provider<AnalyticsConnector> provider3, Provider<FirebaseInstanceId> provider4, Provider<Clock> provider5, Provider<DeveloperListenerManager> provider6) {
        this.appProvider = provider;
        this.transportFactoryProvider = provider2;
        this.analyticsConnectorProvider = provider3;
        this.firebaseInstanceIdProvider = provider4;
        this.clockProvider = provider5;
        this.developerListenerManagerProvider = provider6;
    }

    public MetricsLoggerClient get() {
        return providesApiClient(this.appProvider.get(), this.transportFactoryProvider.get(), this.analyticsConnectorProvider.get(), this.firebaseInstanceIdProvider.get(), this.clockProvider.get(), this.developerListenerManagerProvider.get());
    }

    public static TransportClientModule_ProvidesApiClientFactory create(Provider<FirebaseApp> provider, Provider<TransportFactory> provider2, Provider<AnalyticsConnector> provider3, Provider<FirebaseInstanceId> provider4, Provider<Clock> provider5, Provider<DeveloperListenerManager> provider6) {
        return new TransportClientModule_ProvidesApiClientFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static MetricsLoggerClient providesApiClient(FirebaseApp firebaseApp, TransportFactory transportFactory, AnalyticsConnector analyticsConnector, FirebaseInstanceId firebaseInstanceId, Clock clock, DeveloperListenerManager developerListenerManager) {
        return (MetricsLoggerClient) Preconditions.checkNotNull(TransportClientModule.providesApiClient(firebaseApp, transportFactory, analyticsConnector, firebaseInstanceId, clock, developerListenerManager), "Cannot return null from a non-@Nullable @Provides method");
    }
}
