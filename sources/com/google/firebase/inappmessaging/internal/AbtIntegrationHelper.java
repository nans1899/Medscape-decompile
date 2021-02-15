package com.google.firebase.inappmessaging.internal;

import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.AbtExperimentInfo;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import developers.mobile.abt.FirebaseAbt;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class AbtIntegrationHelper {
    private final FirebaseABTesting abTesting;
    Executor executor = Executors.newSingleThreadExecutor();

    @Inject
    public AbtIntegrationHelper(FirebaseABTesting firebaseABTesting) {
        this.abTesting = firebaseABTesting;
    }

    /* access modifiers changed from: package-private */
    public void updateRunningExperiments(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        ArrayList arrayList = new ArrayList();
        for (CampaignProto.ThickContent next : fetchEligibleCampaignsResponse.getMessagesList()) {
            if (!next.getIsTestCampaign() && next.getPayloadCase().equals(CampaignProto.ThickContent.PayloadCase.EXPERIMENTAL_PAYLOAD)) {
                FirebaseAbt.ExperimentPayload experimentPayload = next.getExperimentalPayload().getExperimentPayload();
                arrayList.add(new AbtExperimentInfo(experimentPayload.getExperimentId(), experimentPayload.getVariantId(), experimentPayload.getTriggerEvent(), new Date(experimentPayload.getExperimentStartTimeMillis()), experimentPayload.getTriggerTimeoutMillis(), experimentPayload.getTimeToLiveMillis()));
            }
        }
        if (!arrayList.isEmpty()) {
            this.executor.execute(AbtIntegrationHelper$$Lambda$1.lambdaFactory$(this, arrayList));
        }
    }

    static /* synthetic */ void lambda$updateRunningExperiments$0(AbtIntegrationHelper abtIntegrationHelper, ArrayList arrayList) {
        try {
            Logging.logd("Updating running experiments with: " + arrayList.size() + " experiments");
            abtIntegrationHelper.abTesting.validateRunningExperiments(arrayList);
        } catch (AbtException e) {
            Logging.loge("Unable to register experiments with ABT, missing analytics?\n" + e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void setExperimentActive(FirebaseAbt.ExperimentPayload experimentPayload) {
        this.executor.execute(AbtIntegrationHelper$$Lambda$2.lambdaFactory$(this, experimentPayload));
    }

    static /* synthetic */ void lambda$setExperimentActive$1(AbtIntegrationHelper abtIntegrationHelper, FirebaseAbt.ExperimentPayload experimentPayload) {
        try {
            Logging.logd("Updating active experiment: " + experimentPayload.toString());
            abtIntegrationHelper.abTesting.reportActiveExperiment(new AbtExperimentInfo(experimentPayload.getExperimentId(), experimentPayload.getVariantId(), experimentPayload.getTriggerEvent(), new Date(experimentPayload.getExperimentStartTimeMillis()), experimentPayload.getTriggerTimeoutMillis(), experimentPayload.getTimeToLiveMillis()));
        } catch (AbtException e) {
            Logging.loge("Unable to set experiment as active with ABT, missing analytics?\n" + e.getMessage());
        }
    }
}
