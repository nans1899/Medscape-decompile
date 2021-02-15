package com.google.firebase.inappmessaging;

import android.app.Application;
import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.component.AbtComponent;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.inappmessaging.internal.AbtIntegrationHelper;
import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import com.google.firebase.inappmessaging.internal.injection.components.DaggerAppComponent;
import com.google.firebase.inappmessaging.internal.injection.components.DaggerUniversalComponent;
import com.google.firebase.inappmessaging.internal.injection.components.UniversalComponent;
import com.google.firebase.inappmessaging.internal.injection.modules.AnalyticsEventsModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.AppMeasurementModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ApplicationModule;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.ProgrammaticContextualTriggerFlowableModule;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class FirebaseInAppMessagingRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseInAppMessaging.class).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseInstanceId.class)).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(AbtComponent.class)).add(Dependency.optional(AnalyticsConnector.class)).add(Dependency.required(TransportFactory.class)).add(Dependency.required(Subscriber.class)).factory(FirebaseInAppMessagingRegistrar$$Lambda$1.lambdaFactory$(this)).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-fiam", "19.0.5")});
    }

    /* access modifiers changed from: private */
    public FirebaseInAppMessaging providesFirebaseInAppMessaging(ComponentContainer componentContainer) {
        FirebaseApp firebaseApp = (FirebaseApp) componentContainer.get(FirebaseApp.class);
        UniversalComponent build = DaggerUniversalComponent.builder().applicationModule(new ApplicationModule((Application) firebaseApp.getApplicationContext())).appMeasurementModule(new AppMeasurementModule((AnalyticsConnector) componentContainer.get(AnalyticsConnector.class), (Subscriber) componentContainer.get(Subscriber.class))).analyticsEventsModule(new AnalyticsEventsModule()).programmaticContextualTriggerFlowableModule(new ProgrammaticContextualTriggerFlowableModule(new ProgramaticContextualTriggers())).build();
        return DaggerAppComponent.builder().abtIntegrationHelper(new AbtIntegrationHelper(((AbtComponent) componentContainer.get(AbtComponent.class)).get("fiam"))).apiClientModule(new ApiClientModule(firebaseApp, (FirebaseInstanceId) componentContainer.get(FirebaseInstanceId.class), build.clock())).grpcClientModule(new GrpcClientModule(firebaseApp)).universalComponent(build).transportFactory((TransportFactory) componentContainer.get(TransportFactory.class)).build().providesFirebaseInAppMessaging();
    }
}
