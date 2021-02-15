package com.google.firebase.inappmessaging.internal;

import com.google.android.gms.tasks.Task;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class InAppMessageStreamManager$$Lambda$12 implements MaybeOnSubscribe {
    private final Task arg$1;

    private InAppMessageStreamManager$$Lambda$12(Task task) {
        this.arg$1 = task;
    }

    public static MaybeOnSubscribe lambdaFactory$(Task task) {
        return new InAppMessageStreamManager$$Lambda$12(task);
    }

    public void subscribe(MaybeEmitter maybeEmitter) {
        InAppMessageStreamManager.lambda$taskToMaybe$29(this.arg$1, maybeEmitter);
    }
}
