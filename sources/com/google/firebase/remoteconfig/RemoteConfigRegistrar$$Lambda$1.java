package com.google.firebase.remoteconfig;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
final /* synthetic */ class RemoteConfigRegistrar$$Lambda$1 implements ComponentFactory {
    private static final RemoteConfigRegistrar$$Lambda$1 instance = new RemoteConfigRegistrar$$Lambda$1();

    private RemoteConfigRegistrar$$Lambda$1() {
    }

    public static ComponentFactory lambdaFactory$() {
        return instance;
    }

    public Object create(ComponentContainer componentContainer) {
        return RemoteConfigRegistrar.lambda$getComponents$0(componentContainer);
    }
}
