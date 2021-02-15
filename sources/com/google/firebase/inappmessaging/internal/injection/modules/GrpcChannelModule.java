package com.google.firebase.inappmessaging.internal.injection.modules;

import dagger.Module;
import dagger.Provides;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class GrpcChannelModule {
    @Singleton
    @Provides
    @Named("host")
    public String providesServiceHost() {
        return "firebaseinappmessaging.googleapis.com";
    }

    @Singleton
    @Provides
    public Channel providesGrpcChannel(@Named("host") String str) {
        return ManagedChannelBuilder.forTarget(str).build();
    }
}
