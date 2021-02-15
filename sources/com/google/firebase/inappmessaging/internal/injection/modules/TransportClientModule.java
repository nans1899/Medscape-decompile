package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.MetricsLoggerClient;
import com.google.firebase.inappmessaging.internal.time.Clock;
import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class TransportClientModule {
    private static final String TRANSPORT_NAME = "731";

    static /* synthetic */ byte[] lambda$providesApiClient$0(byte[] bArr) {
        return bArr;
    }

    @Provides
    static MetricsLoggerClient providesApiClient(FirebaseApp firebaseApp, TransportFactory transportFactory, AnalyticsConnector analyticsConnector, FirebaseInstanceId firebaseInstanceId, Clock clock, DeveloperListenerManager developerListenerManager) {
        return new MetricsLoggerClient(TransportClientModule$$Lambda$2.lambdaFactory$(transportFactory.getTransport(TRANSPORT_NAME, byte[].class, TransportClientModule$$Lambda$1.lambdaFactory$())), analyticsConnector, firebaseApp, firebaseInstanceId, clock, developerListenerManager);
    }
}
