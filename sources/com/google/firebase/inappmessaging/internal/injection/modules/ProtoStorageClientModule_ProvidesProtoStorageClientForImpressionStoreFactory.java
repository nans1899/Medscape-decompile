package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.ProtoStorageClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory implements Factory<ProtoStorageClient> {
    private final Provider<Application> applicationProvider;
    private final ProtoStorageClientModule module;

    public ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory(ProtoStorageClientModule protoStorageClientModule, Provider<Application> provider) {
        this.module = protoStorageClientModule;
        this.applicationProvider = provider;
    }

    public ProtoStorageClient get() {
        return providesProtoStorageClientForImpressionStore(this.module, this.applicationProvider.get());
    }

    public static ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory create(ProtoStorageClientModule protoStorageClientModule, Provider<Application> provider) {
        return new ProtoStorageClientModule_ProvidesProtoStorageClientForImpressionStoreFactory(protoStorageClientModule, provider);
    }

    public static ProtoStorageClient providesProtoStorageClientForImpressionStore(ProtoStorageClientModule protoStorageClientModule, Application application) {
        return (ProtoStorageClient) Preconditions.checkNotNull(protoStorageClientModule.providesProtoStorageClientForImpressionStore(application), "Cannot return null from a non-@Nullable @Provides method");
    }
}
