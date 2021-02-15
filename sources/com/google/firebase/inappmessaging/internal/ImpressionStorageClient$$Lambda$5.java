package com.google.firebase.inappmessaging.internal;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ImpressionStorageClient$$Lambda$5 implements Function {
    private static final ImpressionStorageClient$$Lambda$5 instance = new ImpressionStorageClient$$Lambda$5();

    private ImpressionStorageClient$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Observable.fromIterable((List) obj);
    }
}
