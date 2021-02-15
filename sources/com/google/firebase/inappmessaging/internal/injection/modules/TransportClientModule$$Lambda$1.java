package com.google.firebase.inappmessaging.internal.injection.modules;

import com.google.android.datatransport.Transformer;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class TransportClientModule$$Lambda$1 implements Transformer {
    private static final TransportClientModule$$Lambda$1 instance = new TransportClientModule$$Lambda$1();

    private TransportClientModule$$Lambda$1() {
    }

    public static Transformer lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return TransportClientModule.lambda$providesApiClient$0((byte[]) obj);
    }
}
