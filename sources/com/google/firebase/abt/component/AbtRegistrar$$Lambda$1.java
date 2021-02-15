package com.google.firebase.abt.component;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
final /* synthetic */ class AbtRegistrar$$Lambda$1 implements ComponentFactory {
    private static final AbtRegistrar$$Lambda$1 instance = new AbtRegistrar$$Lambda$1();

    private AbtRegistrar$$Lambda$1() {
    }

    public static ComponentFactory lambdaFactory$() {
        return instance;
    }

    public Object create(ComponentContainer componentContainer) {
        return AbtRegistrar.lambda$getComponents$0(componentContainer);
    }
}
