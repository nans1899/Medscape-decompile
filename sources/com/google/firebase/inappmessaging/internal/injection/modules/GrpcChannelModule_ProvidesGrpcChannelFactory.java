package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.grpc.Channel;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class GrpcChannelModule_ProvidesGrpcChannelFactory implements Factory<Channel> {
    private final Provider<String> hostProvider;
    private final GrpcChannelModule module;

    public GrpcChannelModule_ProvidesGrpcChannelFactory(GrpcChannelModule grpcChannelModule, Provider<String> provider) {
        this.module = grpcChannelModule;
        this.hostProvider = provider;
    }

    public Channel get() {
        return providesGrpcChannel(this.module, this.hostProvider.get());
    }

    public static GrpcChannelModule_ProvidesGrpcChannelFactory create(GrpcChannelModule grpcChannelModule, Provider<String> provider) {
        return new GrpcChannelModule_ProvidesGrpcChannelFactory(grpcChannelModule, provider);
    }

    public static Channel providesGrpcChannel(GrpcChannelModule grpcChannelModule, String str) {
        return (Channel) Preconditions.checkNotNull(grpcChannelModule.providesGrpcChannel(str), "Cannot return null from a non-@Nullable @Provides method");
    }
}
