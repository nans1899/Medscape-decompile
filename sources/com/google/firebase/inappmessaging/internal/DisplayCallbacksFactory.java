package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.AppForeground;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.google.firebase.inappmessaging.model.RateLimit;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class DisplayCallbacksFactory {
    private final RateLimit appForegroundRateLimit;
    private final CampaignCacheClient campaignCacheClient;
    private final Clock clock;
    private final DataCollectionHelper dataCollectionHelper;
    private final ImpressionStorageClient impressionStorageClient;
    private final MetricsLoggerClient metricsLoggerClient;
    private final RateLimiterClient rateLimiterClient;
    private final Schedulers schedulers;

    @Inject
    public DisplayCallbacksFactory(ImpressionStorageClient impressionStorageClient2, Clock clock2, Schedulers schedulers2, RateLimiterClient rateLimiterClient2, CampaignCacheClient campaignCacheClient2, @AppForeground RateLimit rateLimit, MetricsLoggerClient metricsLoggerClient2, DataCollectionHelper dataCollectionHelper2) {
        this.impressionStorageClient = impressionStorageClient2;
        this.clock = clock2;
        this.schedulers = schedulers2;
        this.rateLimiterClient = rateLimiterClient2;
        this.campaignCacheClient = campaignCacheClient2;
        this.appForegroundRateLimit = rateLimit;
        this.metricsLoggerClient = metricsLoggerClient2;
        this.dataCollectionHelper = dataCollectionHelper2;
    }

    public FirebaseInAppMessagingDisplayCallbacks generateDisplayCallback(InAppMessage inAppMessage, String str) {
        return new DisplayCallbacksImpl(this.impressionStorageClient, this.clock, this.schedulers, this.rateLimiterClient, this.campaignCacheClient, this.appForegroundRateLimit, this.metricsLoggerClient, this.dataCollectionHelper, inAppMessage, str);
    }
}
