package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.firebase.inappmessaging.internal.MetricsLoggerClient;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class TransportClientModule$$Lambda$2 implements MetricsLoggerClient.EngagementMetricsLoggerInterface {
    private final Transport arg$1;

    private TransportClientModule$$Lambda$2(Transport transport) {
        this.arg$1 = transport;
    }

    public static MetricsLoggerClient.EngagementMetricsLoggerInterface lambdaFactory$(Transport transport) {
        return new TransportClientModule$$Lambda$2(transport);
    }

    public void logEvent(byte[] bArr) {
        this.arg$1.send(Event.ofData(bArr));
    }
}
