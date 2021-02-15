package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.grpc.Metadata;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class GrpcClientModule_ProvidesApiKeyHeadersFactory implements Factory<Metadata> {
    private final GrpcClientModule module;

    public GrpcClientModule_ProvidesApiKeyHeadersFactory(GrpcClientModule grpcClientModule) {
        this.module = grpcClientModule;
    }

    public Metadata get() {
        return providesApiKeyHeaders(this.module);
    }

    public static GrpcClientModule_ProvidesApiKeyHeadersFactory create(GrpcClientModule grpcClientModule) {
        return new GrpcClientModule_ProvidesApiKeyHeadersFactory(grpcClientModule);
    }

    public static Metadata providesApiKeyHeaders(GrpcClientModule grpcClientModule) {
        return (Metadata) Preconditions.checkNotNull(grpcClientModule.providesApiKeyHeaders(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
