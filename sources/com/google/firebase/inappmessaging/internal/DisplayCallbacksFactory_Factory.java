package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.RateLimit;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class DisplayCallbacksFactory_Factory implements Factory<DisplayCallbacksFactory> {
    private final Provider<RateLimit> appForegroundRateLimitProvider;
    private final Provider<CampaignCacheClient> campaignCacheClientProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<DataCollectionHelper> dataCollectionHelperProvider;
    private final Provider<ImpressionStorageClient> impressionStorageClientProvider;
    private final Provider<MetricsLoggerClient> metricsLoggerClientProvider;
    private final Provider<RateLimiterClient> rateLimiterClientProvider;
    private final Provider<Schedulers> schedulersProvider;

    public DisplayCallbacksFactory_Factory(Provider<ImpressionStorageClient> provider, Provider<Clock> provider2, Provider<Schedulers> provider3, Provider<RateLimiterClient> provider4, Provider<CampaignCacheClient> provider5, Provider<RateLimit> provider6, Provider<MetricsLoggerClient> provider7, Provider<DataCollectionHelper> provider8) {
        this.impressionStorageClientProvider = provider;
        this.clockProvider = provider2;
        this.schedulersProvider = provider3;
        this.rateLimiterClientProvider = provider4;
        this.campaignCacheClientProvider = provider5;
        this.appForegroundRateLimitProvider = provider6;
        this.metricsLoggerClientProvider = provider7;
        this.dataCollectionHelperProvider = provider8;
    }

    public DisplayCallbacksFactory get() {
        return new DisplayCallbacksFactory(this.impressionStorageClientProvider.get(), this.clockProvider.get(), this.schedulersProvider.get(), this.rateLimiterClientProvider.get(), this.campaignCacheClientProvider.get(), this.appForegroundRateLimitProvider.get(), this.metricsLoggerClientProvider.get(), this.dataCollectionHelperProvider.get());
    }

    public static DisplayCallbacksFactory_Factory create(Provider<ImpressionStorageClient> provider, Provider<Clock> provider2, Provider<Schedulers> provider3, Provider<RateLimiterClient> provider4, Provider<CampaignCacheClient> provider5, Provider<RateLimit> provider6, Provider<MetricsLoggerClient> provider7, Provider<DataCollectionHelper> provider8) {
        return new DisplayCallbacksFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static DisplayCallbacksFactory newInstance(ImpressionStorageClient impressionStorageClient, Clock clock, Schedulers schedulers, RateLimiterClient rateLimiterClient, CampaignCacheClient campaignCacheClient, RateLimit rateLimit, MetricsLoggerClient metricsLoggerClient, DataCollectionHelper dataCollectionHelper) {
        return new DisplayCallbacksFactory(impressionStorageClient, clock, schedulers, rateLimiterClient, campaignCacheClient, rateLimit, metricsLoggerClient, dataCollectionHelper);
    }
}
