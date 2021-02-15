package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import android.util.DisplayMetrics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class InflaterConfigModule_ProvidesDisplayMetricsFactory implements Factory<DisplayMetrics> {
    private final Provider<Application> applicationProvider;
    private final InflaterConfigModule module;

    public InflaterConfigModule_ProvidesDisplayMetricsFactory(InflaterConfigModule inflaterConfigModule, Provider<Application> provider) {
        this.module = inflaterConfigModule;
        this.applicationProvider = provider;
    }

    public DisplayMetrics get() {
        return providesDisplayMetrics(this.module, this.applicationProvider.get());
    }

    public static InflaterConfigModule_ProvidesDisplayMetricsFactory create(InflaterConfigModule inflaterConfigModule, Provider<Application> provider) {
        return new InflaterConfigModule_ProvidesDisplayMetricsFactory(inflaterConfigModule, provider);
    }

    public static DisplayMetrics providesDisplayMetrics(InflaterConfigModule inflaterConfigModule, Application application) {
        return (DisplayMetrics) Preconditions.checkNotNull(inflaterConfigModule.providesDisplayMetrics(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
