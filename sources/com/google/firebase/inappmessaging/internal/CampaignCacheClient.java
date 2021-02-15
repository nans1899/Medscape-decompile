package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import com.google.firebase.inappmessaging.internal.injection.modules.ProtoStorageClientModule;
import com.google.firebase.inappmessaging.internal.injection.qualifiers.CampaignCache;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class CampaignCacheClient {
    private final Application application;
    /* access modifiers changed from: private */
    @Nullable
    public FetchEligibleCampaignsResponse cachedResponse;
    private final Clock clock;
    private final ProtoStorageClient storageClient;

    @Inject
    CampaignCacheClient(@CampaignCache ProtoStorageClient protoStorageClient, Application application2, Clock clock2) {
        this.storageClient = protoStorageClient;
        this.application = application2;
        this.clock = clock2;
    }

    public Completable put(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        return this.storageClient.write(fetchEligibleCampaignsResponse).doOnComplete(CampaignCacheClient$$Lambda$1.lambdaFactory$(this, fetchEligibleCampaignsResponse));
    }

    public Maybe<FetchEligibleCampaignsResponse> get() {
        return Maybe.fromCallable(CampaignCacheClient$$Lambda$2.lambdaFactory$(this)).switchIfEmpty(this.storageClient.read(FetchEligibleCampaignsResponse.parser()).doOnSuccess(CampaignCacheClient$$Lambda$3.lambdaFactory$(this))).filter(CampaignCacheClient$$Lambda$4.lambdaFactory$(this)).doOnError(CampaignCacheClient$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public boolean isResponseValid(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        long expirationEpochTimestampMillis = fetchEligibleCampaignsResponse.getExpirationEpochTimestampMillis();
        long now = this.clock.now();
        File file = new File(this.application.getApplicationContext().getFilesDir(), ProtoStorageClientModule.CAMPAIGN_CACHE_FILE);
        if (expirationEpochTimestampMillis != 0) {
            return now < expirationEpochTimestampMillis;
        }
        if (!file.exists() || now < file.lastModified() + TimeUnit.DAYS.toMillis(1)) {
            return true;
        }
        return false;
    }
}
