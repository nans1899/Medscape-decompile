package com.google.firebase.inappmessaging.internal;

import com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse;
import io.reactivex.functions.Consumer;
import java.util.Locale;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$24 implements Consumer {
    private static final InAppMessageStreamManager$$Lambda$24 instance = new InAppMessageStreamManager$$Lambda$24();

    private InAppMessageStreamManager$$Lambda$24() {
    }

    public static Consumer lambdaFactory$() {
        return instance;
    }

    public void accept(Object obj) {
        Logging.logi(String.format(Locale.US, "Successfully fetched %d messages from backend", new Object[]{Integer.valueOf(((FetchEligibleCampaignsResponse) obj).getMessagesList().size())}));
    }
}
