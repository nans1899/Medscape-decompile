package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.developers.mobile.targeting.proto.ClientSignalsProto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.inappmessaging.internal.time.Clock;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.ClientAppInfo;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsRequest;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import dagger.Lazy;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ApiClient {
    private static final String DATA_COLLECTION_DISABLED_ERROR = "Automatic data collection is disabled, not attempting campaign fetch from service.";
    private static final String FETCHING_CAMPAIGN_MESSAGE = "Fetching campaigns from service.";
    private final Application application;
    private final Clock clock;
    private final DataCollectionHelper dataCollectionHelper;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstanceId firebaseInstanceId;
    private final Lazy<GrpcClient> grpcClient;
    private final ProviderInstaller providerInstaller;

    public ApiClient(Lazy<GrpcClient> lazy, FirebaseApp firebaseApp2, Application application2, FirebaseInstanceId firebaseInstanceId2, DataCollectionHelper dataCollectionHelper2, Clock clock2, ProviderInstaller providerInstaller2) {
        this.grpcClient = lazy;
        this.firebaseApp = firebaseApp2;
        this.application = application2;
        this.firebaseInstanceId = firebaseInstanceId2;
        this.dataCollectionHelper = dataCollectionHelper2;
        this.clock = clock2;
        this.providerInstaller = providerInstaller2;
    }

    static FetchEligibleCampaignsResponse createCacheExpiringResponse() {
        return (FetchEligibleCampaignsResponse) FetchEligibleCampaignsResponse.newBuilder().setExpirationEpochTimestampMillis(1).build();
    }

    /* access modifiers changed from: package-private */
    public Task<FetchEligibleCampaignsResponse> getFiams(CampaignImpressionList campaignImpressionList) {
        if (!this.dataCollectionHelper.isAutomaticDataCollectionEnabled()) {
            Logging.logi(DATA_COLLECTION_DISABLED_ERROR);
            return Tasks.forResult(createCacheExpiringResponse());
        }
        Logging.logi(FETCHING_CAMPAIGN_MESSAGE);
        this.providerInstaller.install();
        return this.firebaseInstanceId.getInstanceId().continueWith(ApiClient$$Lambda$1.lambdaFactory$(this, campaignImpressionList));
    }

    static /* synthetic */ FetchEligibleCampaignsResponse lambda$getFiams$0(ApiClient apiClient, CampaignImpressionList campaignImpressionList, Task task) throws Exception {
        InstanceIdResult instanceIdResult = (InstanceIdResult) task.getResult();
        if (instanceIdResult != null) {
            return apiClient.withCacheExpirationSafeguards(apiClient.grpcClient.get().fetchEligibleCampaigns((FetchEligibleCampaignsRequest) FetchEligibleCampaignsRequest.newBuilder().setProjectNumber(apiClient.firebaseApp.getOptions().getGcmSenderId()).addAllAlreadySeenCampaigns(campaignImpressionList.getAlreadySeenCampaignsList()).setClientSignals(apiClient.getClientSignals()).setRequestingClientApp(apiClient.getClientAppInfo(instanceIdResult)).build()));
        }
        Logging.logw("InstanceID is null, not calling backend");
        return createCacheExpiringResponse();
    }

    private FetchEligibleCampaignsResponse withCacheExpirationSafeguards(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        if (fetchEligibleCampaignsResponse.getExpirationEpochTimestampMillis() < this.clock.now() + TimeUnit.MINUTES.toMillis(1) || fetchEligibleCampaignsResponse.getExpirationEpochTimestampMillis() > this.clock.now() + TimeUnit.DAYS.toMillis(3)) {
            return (FetchEligibleCampaignsResponse) ((FetchEligibleCampaignsResponse.Builder) fetchEligibleCampaignsResponse.toBuilder()).setExpirationEpochTimestampMillis(this.clock.now() + TimeUnit.DAYS.toMillis(1)).build();
        }
        return fetchEligibleCampaignsResponse;
    }

    private ClientSignalsProto.ClientSignals getClientSignals() {
        ClientSignalsProto.ClientSignals.Builder timeZone = ClientSignalsProto.ClientSignals.newBuilder().setPlatformVersion(String.valueOf(Build.VERSION.SDK_INT)).setLanguageCode(Locale.getDefault().toString()).setTimeZone(TimeZone.getDefault().getID());
        String versionName = getVersionName();
        if (!TextUtils.isEmpty(versionName)) {
            timeZone.setAppVersion(versionName);
        }
        return (ClientSignalsProto.ClientSignals) timeZone.build();
    }

    private ClientAppInfo getClientAppInfo(InstanceIdResult instanceIdResult) {
        ClientAppInfo.Builder gmpAppId = ClientAppInfo.newBuilder().setGmpAppId(this.firebaseApp.getOptions().getApplicationId());
        String id = instanceIdResult.getId();
        String token = instanceIdResult.getToken();
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(token)) {
            Logging.logw("Empty instance ID or instance token");
        } else {
            gmpAppId.setAppInstanceId(id);
            gmpAppId.setAppInstanceIdToken(token);
        }
        return (ClientAppInfo) gmpAppId.build();
    }

    @Nullable
    private String getVersionName() {
        try {
            return this.application.getPackageManager().getPackageInfo(this.application.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logging.loge("Error finding versionName : " + e.getMessage());
            return null;
        }
    }
}
