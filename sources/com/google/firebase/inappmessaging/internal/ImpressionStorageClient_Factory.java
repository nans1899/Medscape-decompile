package com.google.firebase.inappmessaging.internal;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class ImpressionStorageClient_Factory implements Factory<ImpressionStorageClient> {
    private final Provider<ProtoStorageClient> storageClientProvider;

    public ImpressionStorageClient_Factory(Provider<ProtoStorageClient> provider) {
        this.storageClientProvider = provider;
    }

    public ImpressionStorageClient get() {
        return new ImpressionStorageClient(this.storageClientProvider.get());
    }

    public static ImpressionStorageClient_Factory create(Provider<ProtoStorageClient> provider) {
        return new ImpressionStorageClient_Factory(provider);
    }

    public static ImpressionStorageClient newInstance(ProtoStorageClient protoStorageClient) {
        return new ImpressionStorageClient(protoStorageClient);
    }
}
