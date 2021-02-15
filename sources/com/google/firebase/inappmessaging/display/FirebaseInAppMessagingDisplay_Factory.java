package com.google.firebase.inappmessaging.display;

import android.app.Application;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.display.internal.BindingWrapperFactory;
import com.google.firebase.inappmessaging.display.internal.FiamAnimator;
import com.google.firebase.inappmessaging.display.internal.FiamImageLoader;
import com.google.firebase.inappmessaging.display.internal.FiamWindowManager;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.display.internal.RenewableTimer;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class FirebaseInAppMessagingDisplay_Factory implements Factory<FirebaseInAppMessagingDisplay> {
    private final Provider<FiamAnimator> animatorProvider;
    private final Provider<Application> applicationProvider;
    private final Provider<RenewableTimer> autoDismissTimerAndImpressionTimerProvider;
    private final Provider<BindingWrapperFactory> bindingWrapperFactoryProvider;
    private final Provider<FirebaseInAppMessaging> headlessInAppMessagingProvider;
    private final Provider<FiamImageLoader> imageLoaderProvider;
    private final Provider<Map<String, Provider<InAppMessageLayoutConfig>>> layoutConfigsProvider;
    private final Provider<FiamWindowManager> windowManagerProvider;

    public FirebaseInAppMessagingDisplay_Factory(Provider<FirebaseInAppMessaging> provider, Provider<Map<String, Provider<InAppMessageLayoutConfig>>> provider2, Provider<FiamImageLoader> provider3, Provider<RenewableTimer> provider4, Provider<FiamWindowManager> provider5, Provider<Application> provider6, Provider<BindingWrapperFactory> provider7, Provider<FiamAnimator> provider8) {
        this.headlessInAppMessagingProvider = provider;
        this.layoutConfigsProvider = provider2;
        this.imageLoaderProvider = provider3;
        this.autoDismissTimerAndImpressionTimerProvider = provider4;
        this.windowManagerProvider = provider5;
        this.applicationProvider = provider6;
        this.bindingWrapperFactoryProvider = provider7;
        this.animatorProvider = provider8;
    }

    public FirebaseInAppMessagingDisplay get() {
        return new FirebaseInAppMessagingDisplay(this.headlessInAppMessagingProvider.get(), this.layoutConfigsProvider.get(), this.imageLoaderProvider.get(), this.autoDismissTimerAndImpressionTimerProvider.get(), this.autoDismissTimerAndImpressionTimerProvider.get(), this.windowManagerProvider.get(), this.applicationProvider.get(), this.bindingWrapperFactoryProvider.get(), this.animatorProvider.get());
    }

    public static FirebaseInAppMessagingDisplay_Factory create(Provider<FirebaseInAppMessaging> provider, Provider<Map<String, Provider<InAppMessageLayoutConfig>>> provider2, Provider<FiamImageLoader> provider3, Provider<RenewableTimer> provider4, Provider<FiamWindowManager> provider5, Provider<Application> provider6, Provider<BindingWrapperFactory> provider7, Provider<FiamAnimator> provider8) {
        return new FirebaseInAppMessagingDisplay_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static FirebaseInAppMessagingDisplay newInstance(FirebaseInAppMessaging firebaseInAppMessaging, Map<String, Provider<InAppMessageLayoutConfig>> map, FiamImageLoader fiamImageLoader, RenewableTimer renewableTimer, RenewableTimer renewableTimer2, FiamWindowManager fiamWindowManager, Application application, BindingWrapperFactory bindingWrapperFactory, FiamAnimator fiamAnimator) {
        return new FirebaseInAppMessagingDisplay(firebaseInAppMessaging, map, fiamImageLoader, renewableTimer, renewableTimer2, fiamWindowManager, application, bindingWrapperFactory, fiamAnimator);
    }
}
