package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.internal.injection.qualifiers.ImpressionStore;
import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpression;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpressionList;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.HashSet;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ImpressionStorageClient {
    private static final CampaignImpressionList EMPTY_IMPRESSIONS = CampaignImpressionList.getDefaultInstance();
    private Maybe<CampaignImpressionList> cachedImpressionsMaybe = Maybe.empty();
    private final ProtoStorageClient storageClient;

    @Inject
    ImpressionStorageClient(@ImpressionStore ProtoStorageClient protoStorageClient) {
        this.storageClient = protoStorageClient;
    }

    /* access modifiers changed from: private */
    public static CampaignImpressionList appendImpression(CampaignImpressionList campaignImpressionList, CampaignImpression campaignImpression) {
        return (CampaignImpressionList) CampaignImpressionList.newBuilder(campaignImpressionList).addAlreadySeenCampaigns(campaignImpression).build();
    }

    public Completable storeImpression(CampaignImpression campaignImpression) {
        return getAllImpressions().defaultIfEmpty(EMPTY_IMPRESSIONS).flatMapCompletable(ImpressionStorageClient$$Lambda$1.lambdaFactory$(this, campaignImpression));
    }

    public Maybe<CampaignImpressionList> getAllImpressions() {
        return this.cachedImpressionsMaybe.switchIfEmpty(this.storageClient.read(CampaignImpressionList.parser()).doOnSuccess(ImpressionStorageClient$$Lambda$2.lambdaFactory$(this))).doOnError(ImpressionStorageClient$$Lambda$3.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void initInMemCache(CampaignImpressionList campaignImpressionList) {
        this.cachedImpressionsMaybe = Maybe.just(campaignImpressionList);
    }

    /* access modifiers changed from: private */
    public void clearInMemCache() {
        this.cachedImpressionsMaybe = Maybe.empty();
    }

    public Single<Boolean> isImpressed(CampaignProto.ThickContent thickContent) {
        String str;
        if (thickContent.getPayloadCase().equals(CampaignProto.ThickContent.PayloadCase.VANILLA_PAYLOAD)) {
            str = thickContent.getVanillaPayload().getCampaignId();
        } else {
            str = thickContent.getExperimentalPayload().getCampaignId();
        }
        return getAllImpressions().map(ImpressionStorageClient$$Lambda$4.lambdaFactory$()).flatMapObservable(ImpressionStorageClient$$Lambda$5.lambdaFactory$()).map(ImpressionStorageClient$$Lambda$6.lambdaFactory$()).contains(str);
    }

    public Completable clearImpressions(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        String str;
        HashSet hashSet = new HashSet();
        for (CampaignProto.ThickContent next : fetchEligibleCampaignsResponse.getMessagesList()) {
            if (next.getPayloadCase().equals(CampaignProto.ThickContent.PayloadCase.VANILLA_PAYLOAD)) {
                str = next.getVanillaPayload().getCampaignId();
            } else {
                str = next.getExperimentalPayload().getCampaignId();
            }
            hashSet.add(str);
        }
        Logging.logd("Potential impressions to clear: " + hashSet.toString());
        return getAllImpressions().defaultIfEmpty(EMPTY_IMPRESSIONS).flatMapCompletable(ImpressionStorageClient$$Lambda$7.lambdaFactory$(this, hashSet));
    }

    static /* synthetic */ CompletableSource lambda$clearImpressions$4(ImpressionStorageClient impressionStorageClient, HashSet hashSet, CampaignImpressionList campaignImpressionList) throws Exception {
        Logging.logd("Existing impressions: " + campaignImpressionList.toString());
        CampaignImpressionList.Builder newBuilder = CampaignImpressionList.newBuilder();
        for (CampaignImpression next : campaignImpressionList.getAlreadySeenCampaignsList()) {
            if (!hashSet.contains(next.getCampaignId())) {
                newBuilder.addAlreadySeenCampaigns(next);
            }
        }
        CampaignImpressionList campaignImpressionList2 = (CampaignImpressionList) newBuilder.build();
        Logging.logd("New cleared impression list: " + campaignImpressionList2.toString());
        return impressionStorageClient.storageClient.write(campaignImpressionList2).doOnComplete(ImpressionStorageClient$$Lambda$8.lambdaFactory$(impressionStorageClient, campaignImpressionList2));
    }
}
