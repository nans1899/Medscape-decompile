package com.google.firebase.inappmessaging.display.internal.injection.modules;

import com.google.firebase.inappmessaging.model.InAppMessage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class InflaterModule_ProvidesBannerMessageFactory implements Factory<InAppMessage> {
    private final InflaterModule module;

    public InflaterModule_ProvidesBannerMessageFactory(InflaterModule inflaterModule) {
        this.module = inflaterModule;
    }

    public InAppMessage get() {
        return providesBannerMessage(this.module);
    }

    public static InflaterModule_ProvidesBannerMessageFactory create(InflaterModule inflaterModule) {
        return new InflaterModule_ProvidesBannerMessageFactory(inflaterModule);
    }

    public static InAppMessage providesBannerMessage(InflaterModule inflaterModule) {
        return (InAppMessage) Preconditions.checkNotNull(inflaterModule.providesBannerMessage(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
