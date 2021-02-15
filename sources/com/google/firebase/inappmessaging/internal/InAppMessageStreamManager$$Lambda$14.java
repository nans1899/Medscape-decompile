package com.google.firebase.inappmessaging.internal;

import com.google.android.gms.tasks.OnFailureListener;
import io.reactivex.MaybeEmitter;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$14 implements OnFailureListener {
    private final MaybeEmitter arg$1;

    private InAppMessageStreamManager$$Lambda$14(MaybeEmitter maybeEmitter) {
        this.arg$1 = maybeEmitter;
    }

    public static OnFailureListener lambdaFactory$(MaybeEmitter maybeEmitter) {
        return new InAppMessageStreamManager$$Lambda$14(maybeEmitter);
    }

    public void onFailure(Exception exc) {
        InAppMessageStreamManager.lambda$taskToMaybe$28(this.arg$1, exc);
    }
}
