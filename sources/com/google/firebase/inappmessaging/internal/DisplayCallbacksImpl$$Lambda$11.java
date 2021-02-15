package com.google.firebase.inappmessaging.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class DisplayCallbacksImpl$$Lambda$11 implements Callable {
    private final TaskCompletionSource arg$1;

    private DisplayCallbacksImpl$$Lambda$11(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public static Callable lambdaFactory$(TaskCompletionSource taskCompletionSource) {
        return new DisplayCallbacksImpl$$Lambda$11(taskCompletionSource);
    }

    public Object call() {
        return this.arg$1.setResult(null);
    }
}
