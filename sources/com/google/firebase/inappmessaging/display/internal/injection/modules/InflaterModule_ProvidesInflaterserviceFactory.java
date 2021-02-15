package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.view.LayoutInflater;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class InflaterModule_ProvidesInflaterserviceFactory implements Factory<LayoutInflater> {
    private final InflaterModule module;

    public InflaterModule_ProvidesInflaterserviceFactory(InflaterModule inflaterModule) {
        this.module = inflaterModule;
    }

    public LayoutInflater get() {
        return providesInflaterservice(this.module);
    }

    public static InflaterModule_ProvidesInflaterserviceFactory create(InflaterModule inflaterModule) {
        return new InflaterModule_ProvidesInflaterserviceFactory(inflaterModule);
    }

    public static LayoutInflater providesInflaterservice(InflaterModule inflaterModule) {
        return (LayoutInflater) Preconditions.checkNotNull(inflaterModule.providesInflaterservice(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
