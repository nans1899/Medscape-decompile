package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class CampaignCacheClient_Factory implements Factory<CampaignCacheClient> {
    private final Provider<Application> applicationProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<ProtoStorageClient> storageClientProvider;

    public CampaignCacheClient_Factory(Provider<ProtoStorageClient> provider, Provider<Application> provider2, Provider<Clock> provider3) {
        this.storageClientProvider = provider;
        this.applicationProvider = provider2;
        this.clockProvider = provider3;
    }

    public CampaignCacheClient get() {
        return new CampaignCacheClient(this.storageClientProvider.get(), this.applicationProvider.get(), this.clockProvider.get());
    }

    public static CampaignCacheClient_Factory create(Provider<ProtoStorageClient> provider, Provider<Application> provider2, Provider<Clock> provider3) {
        return new CampaignCacheClient_Factory(provider, provider2, provider3);
    }

    public static CampaignCacheClient newInstance(ProtoStorageClient protoStorageClient, Application application, Clock clock) {
        return new CampaignCacheClient(protoStorageClient, application, clock);
    }
}
