package com.google.firebase.inappmessaging.display.internal.injection.modules;

import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class HeadlessInAppMessagingModule {
    private final FirebaseInAppMessaging headless;

    public HeadlessInAppMessagingModule(FirebaseInAppMessaging firebaseInAppMessaging) {
        this.headless = firebaseInAppMessaging;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public FirebaseInAppMessaging providesHeadlesssSingleton() {
        return this.headless;
    }
}
