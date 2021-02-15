package com.google.android.gms.libs.punchclock.threads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class TracingHandler extends Handler {
    private static zza zzbi;

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public interface zza {
    }

    public TracingHandler() {
    }

    public TracingHandler(Handler.Callback callback) {
        super(callback);
    }

    public TracingHandler(Looper looper) {
        super(looper);
    }

    public TracingHandler(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    public boolean sendMessageAtTime(Message message, long j) {
        return super.sendMessageAtTime(message, j);
    }

    public boolean sendMessageAtFrontOfQueueTraced(Message message) {
        return sendMessageAtFrontOfQueue(message);
    }

    public boolean postAtFrontOfQueueTraced(Runnable runnable) {
        return postAtFrontOfQueue(runnable);
    }

    public final void dispatchMessage(Message message) {
        dispatchMessageTraced(message);
    }

    /* access modifiers changed from: protected */
    public void dispatchMessageTraced(Message message) {
        super.dispatchMessage(message);
    }
}
