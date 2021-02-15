package com.google.firebase.inappmessaging.display.internal.injection.modules;

import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class InflaterModule_InAppMessageLayoutConfigFactory implements Factory<InAppMessageLayoutConfig> {
    private final InflaterModule module;

    public InflaterModule_InAppMessageLayoutConfigFactory(InflaterModule inflaterModule) {
        this.module = inflaterModule;
    }

    public InAppMessageLayoutConfig get() {
        return inAppMessageLayoutConfig(this.module);
    }

    public static InflaterModule_InAppMessageLayoutConfigFactory create(InflaterModule inflaterModule) {
        return new InflaterModule_InAppMessageLayoutConfigFactory(inflaterModule);
    }

    public static InAppMessageLayoutConfig inAppMessageLayoutConfig(InflaterModule inflaterModule) {
        return (InAppMessageLayoutConfig) Preconditions.checkNotNull(inflaterModule.inAppMessageLayoutConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
