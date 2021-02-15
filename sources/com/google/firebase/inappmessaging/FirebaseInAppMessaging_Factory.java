package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.internal.DataCollectionHelper;
import com.google.firebase.inappmessaging.internal.DeveloperListenerManager;
import com.google.firebase.inappmessaging.internal.DisplayCallbacksFactory;
import com.google.firebase.inappmessaging.internal.InAppMessageStreamManager;
import com.google.firebase.inappmessaging.internal.ProgramaticContextualTriggers;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class FirebaseInAppMessaging_Factory implements Factory<FirebaseInAppMessaging> {
    private final Provider<DataCollectionHelper> dataCollectionHelperProvider;
    private final Provider<DeveloperListenerManager> developerListenerManagerProvider;
    private final Provider<DisplayCallbacksFactory> displayCallbacksFactoryProvider;
    private final Provider<InAppMessageStreamManager> inAppMessageStreamManagerProvider;
    private final Provider<ProgramaticContextualTriggers> programaticContextualTriggersProvider;

    public FirebaseInAppMessaging_Factory(Provider<InAppMessageStreamManager> provider, Provider<ProgramaticContextualTriggers> provider2, Provider<DataCollectionHelper> provider3, Provider<DisplayCallbacksFactory> provider4, Provider<DeveloperListenerManager> provider5) {
        this.inAppMessageStreamManagerProvider = provider;
        this.programaticContextualTriggersProvider = provider2;
        this.dataCollectionHelperProvider = provider3;
        this.displayCallbacksFactoryProvider = provider4;
        this.developerListenerManagerProvider = provider5;
    }

    public FirebaseInAppMessaging get() {
        return new FirebaseInAppMessaging(this.inAppMessageStreamManagerProvider.get(), this.programaticContextualTriggersProvider.get(), this.dataCollectionHelperProvider.get(), this.displayCallbacksFactoryProvider.get(), this.developerListenerManagerProvider.get());
    }

    public static FirebaseInAppMessaging_Factory create(Provider<InAppMessageStreamManager> provider, Provider<ProgramaticContextualTriggers> provider2, Provider<DataCollectionHelper> provider3, Provider<DisplayCallbacksFactory> provider4, Provider<DeveloperListenerManager> provider5) {
        return new FirebaseInAppMessaging_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static FirebaseInAppMessaging newInstance(InAppMessageStreamManager inAppMessageStreamManager, ProgramaticContextualTriggers programaticContextualTriggers, DataCollectionHelper dataCollectionHelper, DisplayCallbacksFactory displayCallbacksFactory, DeveloperListenerManager developerListenerManager) {
        return new FirebaseInAppMessaging(inAppMessageStreamManager, programaticContextualTriggers, dataCollectionHelper, displayCallbacksFactory, developerListenerManager);
    }
}
