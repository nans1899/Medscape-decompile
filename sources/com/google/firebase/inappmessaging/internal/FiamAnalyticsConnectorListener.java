package com.google.firebase.inappmessaging.internal;

import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import io.reactivex.FlowableEmitter;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final class FiamAnalyticsConnectorListener implements AnalyticsConnector.AnalyticsConnectorListener {
    private FlowableEmitter<String> emitter;

    FiamAnalyticsConnectorListener(FlowableEmitter<String> flowableEmitter) {
        this.emitter = flowableEmitter;
    }

    public void onMessageTriggered(int i, Bundle bundle) {
        if (i == 2) {
            this.emitter.onNext(bundle.getString(OmnitureConstants.CHANNEL));
        }
    }
}
