package com.google.firebase.appindexing.internal;

import android.os.Handler;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzk implements OnCompleteListener<Void>, Executor {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final GoogleApi<?> zzfa;
    /* access modifiers changed from: private */
    public final Queue<zzj> zzff = new ArrayDeque();
    /* access modifiers changed from: private */
    public int zzfg = 0;

    public zzk(GoogleApi<?> googleApi) {
        this.zzfa = googleApi;
        this.handler = new TracingHandler(googleApi.getLooper());
    }

    public final Task<Void> zzb(zzy zzy) {
        boolean isEmpty;
        zzj zzj = new zzj(this, zzy);
        Task<Void> task = zzj.getTask();
        task.addOnCompleteListener((Executor) this, (OnCompleteListener<Void>) this);
        synchronized (this.zzff) {
            isEmpty = this.zzff.isEmpty();
            this.zzff.add(zzj);
        }
        if (isEmpty) {
            zzj.execute();
        }
        return task;
    }

    public final void onComplete(Task<Void> task) {
        zzj zzj;
        synchronized (this.zzff) {
            if (this.zzfg == 2) {
                zzj = this.zzff.peek();
                Preconditions.checkState(zzj != null);
            } else {
                zzj = null;
            }
            this.zzfg = 0;
        }
        if (zzj != null) {
            zzj.execute();
        }
    }

    public final void execute(Runnable runnable) {
        this.handler.post(runnable);
    }
}
