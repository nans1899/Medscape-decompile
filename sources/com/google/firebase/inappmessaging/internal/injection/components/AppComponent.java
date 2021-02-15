package com.google.firebase.inappmessaging.internal.injection.components;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.internal.AbtIntegrationHelper;
import com.google.firebase.inappmessaging.internal.DisplayCallbacksFactory;
import com.google.firebase.inappmessaging.internal.injection.modules.ApiClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.GrpcClientModule;
import com.google.firebase.inappmessaging.internal.injection.modules.TransportClientModule;
import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = {UniversalComponent.class}, modules = {ApiClientModule.class, GrpcClientModule.class, TransportClientModule.class})
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public interface AppComponent {

    @Component.Builder
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface Builder {
        @BindsInstance
        Builder abtIntegrationHelper(AbtIntegrationHelper abtIntegrationHelper);

        Builder apiClientModule(ApiClientModule apiClientModule);

        AppComponent build();

        Builder grpcClientModule(GrpcClientModule grpcClientModule);

        @BindsInstance
        Builder transportFactory(TransportFactory transportFactory);

        Builder universalComponent(UniversalComponent universalComponent);
    }

    DisplayCallbacksFactory displayCallbacksFactory();

    FirebaseInAppMessaging providesFirebaseInAppMessaging();
}
