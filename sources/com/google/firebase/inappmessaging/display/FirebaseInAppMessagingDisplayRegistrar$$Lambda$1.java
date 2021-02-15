package com.google.firebase.inappmessaging.display;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
final /* synthetic */ class FirebaseInAppMessagingDisplayRegistrar$$Lambda$1 implements ComponentFactory {
    private final FirebaseInAppMessagingDisplayRegistrar arg$1;

    private FirebaseInAppMessagingDisplayRegistrar$$Lambda$1(FirebaseInAppMessagingDisplayRegistrar firebaseInAppMessagingDisplayRegistrar) {
        this.arg$1 = firebaseInAppMessagingDisplayRegistrar;
    }

    public static ComponentFactory lambdaFactory$(FirebaseInAppMessagingDisplayRegistrar firebaseInAppMessagingDisplayRegistrar) {
        return new FirebaseInAppMessagingDisplayRegistrar$$Lambda$1(firebaseInAppMessagingDisplayRegistrar);
    }

    public Object create(ComponentContainer componentContainer) {
        return this.arg$1.buildFirebaseInAppMessagingUI(componentContainer);
    }
}
