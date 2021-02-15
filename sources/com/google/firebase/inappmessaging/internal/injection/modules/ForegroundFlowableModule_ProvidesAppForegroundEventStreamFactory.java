package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.reactivex.flowables.ConnectableFlowable;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory implements Factory<ConnectableFlowable<String>> {
    private final Provider<Application> applicationProvider;
    private final ForegroundFlowableModule module;

    public ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory(ForegroundFlowableModule foregroundFlowableModule, Provider<Application> provider) {
        this.module = foregroundFlowableModule;
        this.applicationProvider = provider;
    }

    public ConnectableFlowable<String> get() {
        return providesAppForegroundEventStream(this.module, this.applicationProvider.get());
    }

    public static ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory create(ForegroundFlowableModule foregroundFlowableModule, Provider<Application> provider) {
        return new ForegroundFlowableModule_ProvidesAppForegroundEventStreamFactory(foregroundFlowableModule, provider);
    }

    public static ConnectableFlowable<String> providesAppForegroundEventStream(ForegroundFlowableModule foregroundFlowableModule, Application application) {
        return (ConnectableFlowable) Preconditions.checkNotNull(foregroundFlowableModule.providesAppForegroundEventStream(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
