package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.util.DisplayMetrics;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class InflaterConfigModule_ProvidesModalPortraitConfigFactory implements Factory<InAppMessageLayoutConfig> {
    private final Provider<DisplayMetrics> displayMetricsProvider;
    private final InflaterConfigModule module;

    public InflaterConfigModule_ProvidesModalPortraitConfigFactory(InflaterConfigModule inflaterConfigModule, Provider<DisplayMetrics> provider) {
        this.module = inflaterConfigModule;
        this.displayMetricsProvider = provider;
    }

    public InAppMessageLayoutConfig get() {
        return providesModalPortraitConfig(this.module, this.displayMetricsProvider.get());
    }

    public static InflaterConfigModule_ProvidesModalPortraitConfigFactory create(InflaterConfigModule inflaterConfigModule, Provider<DisplayMetrics> provider) {
        return new InflaterConfigModule_ProvidesModalPortraitConfigFactory(inflaterConfigModule, provider);
    }

    public static InAppMessageLayoutConfig providesModalPortraitConfig(InflaterConfigModule inflaterConfigModule, DisplayMetrics displayMetrics) {
        return (InAppMessageLayoutConfig) Preconditions.checkNotNull(inflaterConfigModule.providesModalPortraitConfig(displayMetrics), "Cannot return null from a non-@Nullable @Provides method");
    }
}
