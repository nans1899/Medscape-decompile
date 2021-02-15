package com.google.firebase.inappmessaging.display;

import android.app.Application;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.display.internal.injection.components.DaggerAppComponent;
import com.google.firebase.inappmessaging.display.internal.injection.components.DaggerUniversalComponent;
import com.google.firebase.inappmessaging.display.internal.injection.modules.ApplicationModule;
import com.google.firebase.inappmessaging.display.internal.injection.modules.HeadlessInAppMessagingModule;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class FirebaseInAppMessagingDisplayRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseInAppMessagingDisplay.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(AnalyticsConnector.class)).add(Dependency.required(FirebaseInAppMessaging.class)).factory(FirebaseInAppMessagingDisplayRegistrar$$Lambda$1.lambdaFactory$(this)).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-fiamd", "19.0.5")});
    }

    /* access modifiers changed from: private */
    public FirebaseInAppMessagingDisplay buildFirebaseInAppMessagingUI(ComponentContainer componentContainer) {
        Application application = (Application) FirebaseApp.getInstance().getApplicationContext();
        FirebaseInAppMessagingDisplay providesFirebaseInAppMessagingUI = DaggerAppComponent.builder().universalComponent(DaggerUniversalComponent.builder().applicationModule(new ApplicationModule(application)).build()).headlessInAppMessagingModule(new HeadlessInAppMessagingModule((FirebaseInAppMessaging) componentContainer.get(FirebaseInAppMessaging.class))).build().providesFirebaseInAppMessagingUI();
        application.registerActivityLifecycleCallbacks(providesFirebaseInAppMessagingUI);
        return providesFirebaseInAppMessagingUI;
    }
}
