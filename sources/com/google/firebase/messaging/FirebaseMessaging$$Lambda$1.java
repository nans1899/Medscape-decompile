package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.InstanceIdResult;

/* compiled from: com.google.firebase:firebase-messaging@@20.3.0 */
final /* synthetic */ class FirebaseMessaging$$Lambda$1 implements Continuation {
    static final Continuation $instance = new FirebaseMessaging$$Lambda$1();

    private FirebaseMessaging$$Lambda$1() {
    }

    public final Object then(Task task) {
        return ((InstanceIdResult) task.getResult()).getToken();
    }
}
