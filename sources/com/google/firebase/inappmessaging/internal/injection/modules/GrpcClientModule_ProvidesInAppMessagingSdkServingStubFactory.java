package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.InAppMessagingSdkServingGrpc;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.grpc.Channel;
import io.grpc.Metadata;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory implements Factory<InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub> {
    private final Provider<Channel> channelProvider;
    private final Provider<Metadata> metadataProvider;
    private final GrpcClientModule module;

    public GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory(GrpcClientModule grpcClientModule, Provider<Channel> provider, Provider<Metadata> provider2) {
        this.module = grpcClientModule;
        this.channelProvider = provider;
        this.metadataProvider = provider2;
    }

    public InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub get() {
        return providesInAppMessagingSdkServingStub(this.module, this.channelProvider.get(), this.metadataProvider.get());
    }

    public static GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory create(GrpcClientModule grpcClientModule, Provider<Channel> provider, Provider<Metadata> provider2) {
        return new GrpcClientModule_ProvidesInAppMessagingSdkServingStubFactory(grpcClientModule, provider, provider2);
    }

    public static InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub providesInAppMessagingSdkServingStub(GrpcClientModule grpcClientModule, Channel channel, Metadata metadata) {
        return (InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub) Preconditions.checkNotNull(grpcClientModule.providesInAppMessagingSdkServingStub(channel, metadata), "Cannot return null from a non-@Nullable @Provides method");
    }
}
