package com.google.firebase.inappmessaging.display.internal.injection.modules;

import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class HeadlessInAppMessagingModule_ProvidesHeadlesssSingletonFactory implements Factory<FirebaseInAppMessaging> {
    private final HeadlessInAppMessagingModule module;

    public HeadlessInAppMessagingModule_ProvidesHeadlesssSingletonFactory(HeadlessInAppMessagingModule headlessInAppMessagingModule) {
        this.module = headlessInAppMessagingModule;
    }

    public FirebaseInAppMessaging get() {
        return providesHeadlesssSingleton(this.module);
    }

    public static HeadlessInAppMessagingModule_ProvidesHeadlesssSingletonFactory create(HeadlessInAppMessagingModule headlessInAppMessagingModule) {
        return new HeadlessInAppMessagingModule_ProvidesHeadlesssSingletonFactory(headlessInAppMessagingModule);
    }

    public static FirebaseInAppMessaging providesHeadlesssSingleton(HeadlessInAppMessagingModule headlessInAppMessagingModule) {
        return (FirebaseInAppMessaging) Preconditions.checkNotNull(headlessInAppMessagingModule.providesHeadlesssSingleton(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
