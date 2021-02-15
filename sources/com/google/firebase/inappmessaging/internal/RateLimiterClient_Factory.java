package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class RateLimiterClient_Factory implements Factory<RateLimiterClient> {
    private final Provider<Clock> clockProvider;
    private final Provider<ProtoStorageClient> storageClientProvider;

    public RateLimiterClient_Factory(Provider<ProtoStorageClient> provider, Provider<Clock> provider2) {
        this.storageClientProvider = provider;
        this.clockProvider = provider2;
    }

    public RateLimiterClient get() {
        return new RateLimiterClient(this.storageClientProvider.get(), this.clockProvider.get());
    }

    public static RateLimiterClient_Factory create(Provider<ProtoStorageClient> provider, Provider<Clock> provider2) {
        return new RateLimiterClient_Factory(provider, provider2);
    }

    public static RateLimiterClient newInstance(ProtoStorageClient protoStorageClient, Clock clock) {
        return new RateLimiterClient(protoStorageClient, clock);
    }
}
