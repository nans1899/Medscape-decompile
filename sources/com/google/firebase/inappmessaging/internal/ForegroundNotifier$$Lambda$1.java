package com.google.firebase.inappmessaging.internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ForegroundNotifier$$Lambda$1 implements Runnable {
    private final ForegroundNotifier arg$1;

    private ForegroundNotifier$$Lambda$1(ForegroundNotifier foregroundNotifier) {
        this.arg$1 = foregroundNotifier;
    }

    public static Runnable lambdaFactory$(ForegroundNotifier foregroundNotifier) {
        return new ForegroundNotifier$$Lambda$1(foregroundNotifier);
    }

    public void run() {
        ForegroundNotifier.lambda$onActivityPaused$0(this.arg$1);
    }
}
