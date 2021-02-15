package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzj {
    /* access modifiers changed from: private */
    public final zzy zzfc;
    /* access modifiers changed from: private */
    public final TaskCompletionSource<Void> zzfd = new TaskCompletionSource<>();
    final /* synthetic */ zzk zzfe;

    public zzj(zzk zzk, zzy zzy) {
        this.zzfe = zzk;
        this.zzfc = zzy;
    }

    public final Task<Void> getTask() {
        return this.zzfd.getTask();
    }

    public final void execute() {
        synchronized (this.zzfe.zzff) {
            Preconditions.checkState(this.zzfe.zzfg == 0);
            int unused = this.zzfe.zzfg = 1;
        }
        this.zzfe.zzfa.doWrite(new zzl(this)).addOnFailureListener((Executor) this.zzfe, (OnFailureListener) new zzm(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Exception exc) {
        zzj zzj;
        synchronized (this.zzfe.zzff) {
            if (this.zzfe.zzff.peek() == this) {
                this.zzfe.zzff.remove();
                int unused = this.zzfe.zzfg = 0;
                zzj = (zzj) this.zzfe.zzff.peek();
            } else {
                zzj = null;
            }
        }
        this.zzfd.trySetException(exc);
        if (zzj != null) {
            zzj.execute();
        }
    }
}
