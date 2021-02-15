package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class GrpcChannelModule_ProvidesServiceHostFactory implements Factory<String> {
    private final GrpcChannelModule module;

    public GrpcChannelModule_ProvidesServiceHostFactory(GrpcChannelModule grpcChannelModule) {
        this.module = grpcChannelModule;
    }

    public String get() {
        return providesServiceHost(this.module);
    }

    public static GrpcChannelModule_ProvidesServiceHostFactory create(GrpcChannelModule grpcChannelModule) {
        return new GrpcChannelModule_ProvidesServiceHostFactory(grpcChannelModule);
    }

    public static String providesServiceHost(GrpcChannelModule grpcChannelModule) {
        return (String) Preconditions.checkNotNull(grpcChannelModule.providesServiceHost(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
