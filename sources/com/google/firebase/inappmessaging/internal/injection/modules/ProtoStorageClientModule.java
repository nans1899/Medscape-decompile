package com.google.firebase.inappmessaging.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.ProtoStorageClient;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.CampaignCache;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.ImpressionStore;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.RateLimit;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ProtoStorageClientModule {
    public static final String CAMPAIGN_CACHE_FILE = "fiam_eligible_campaigns_cache_file";
    public static final String IMPRESSIONS_STORE_FILE = "fiam_impressions_store_file";
    public static final String RATE_LIMIT_STORE_FILE = "rate_limit_store_file";

    @CampaignCache
    @Singleton
    @Provides
    public ProtoStorageClient providesProtoStorageClientForCampaign(Application application) {
        return new ProtoStorageClient(application, CAMPAIGN_CACHE_FILE);
    }

    @Singleton
    @ImpressionStore
    @Provides
    public ProtoStorageClient providesProtoStorageClientForImpressionStore(Application application) {
        return new ProtoStorageClient(application, IMPRESSIONS_STORE_FILE);
    }

    @RateLimit
    @Singleton
    @Provides
    public ProtoStorageClient providesProtoStorageClientForLimiterStore(Application application) {
        return new ProtoStorageClient(application, RATE_LIMIT_STORE_FILE);
    }
}
