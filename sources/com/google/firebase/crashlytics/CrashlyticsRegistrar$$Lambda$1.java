package com.google.firebase.crashlytics;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: CrashlyticsRegistrar */
final /* synthetic */ class CrashlyticsRegistrar$$Lambda$1 implements ComponentFactory {
    private final CrashlyticsRegistrar arg$1;

    private CrashlyticsRegistrar$$Lambda$1(CrashlyticsRegistrar crashlyticsRegistrar) {
        this.arg$1 = crashlyticsRegistrar;
    }

    public static ComponentFactory lambdaFactory$(CrashlyticsRegistrar crashlyticsRegistrar) {
        return new CrashlyticsRegistrar$$Lambda$1(crashlyticsRegistrar);
    }

    public Object create(ComponentContainer componentContainer) {
        return this.arg$1.buildCrashlytics(componentContainer);
    }
}
