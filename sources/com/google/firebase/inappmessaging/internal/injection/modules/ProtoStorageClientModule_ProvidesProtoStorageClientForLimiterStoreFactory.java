package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.ProtoStorageClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory implements Factory<ProtoStorageClient> {
    private final Provider<Application> applicationProvider;
    private final ProtoStorageClientModule module;

    public ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory(ProtoStorageClientModule protoStorageClientModule, Provider<Application> provider) {
        this.module = protoStorageClientModule;
        this.applicationProvider = provider;
    }

    public ProtoStorageClient get() {
        return providesProtoStorageClientForLimiterStore(this.module, this.applicationProvider.get());
    }

    public static ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory create(ProtoStorageClientModule protoStorageClientModule, Provider<Application> provider) {
        return new ProtoStorageClientModule_ProvidesProtoStorageClientForLimiterStoreFactory(protoStorageClientModule, provider);
    }

    public static ProtoStorageClient providesProtoStorageClientForLimiterStore(ProtoStorageClientModule protoStorageClientModule, Application application) {
        return (ProtoStorageClient) Preconditions.checkNotNull(protoStorageClientModule.providesProtoStorageClientForLimiterStore(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
