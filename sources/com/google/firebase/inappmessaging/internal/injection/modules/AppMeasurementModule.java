package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inappmessaging.internal.StubAnalyticsConnector;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class AppMeasurementModule {
    private AnalyticsConnector analyticsConnector;
    private Subscriber firebaseEventsSubscriber;

    public AppMeasurementModule(AnalyticsConnector analyticsConnector2, Subscriber subscriber) {
        this.analyticsConnector = analyticsConnector2 == null ? StubAnalyticsConnector.instance : analyticsConnector2;
        this.firebaseEventsSubscriber = subscriber;
    }

    /* access modifiers changed from: package-private */
    @Singleton
    @Provides
    public AnalyticsConnector providesAnalyticsConnector() {
        return this.analyticsConnector;
    }

    /* access modifiers changed from: package-private */
    @Singleton
    @Provides
    public Subscriber providesSubsriber() {
        return this.firebaseEventsSubscriber;
    }
}
