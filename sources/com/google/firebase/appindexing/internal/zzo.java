package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzo extends IStatusCallback.Stub {
    private final /* synthetic */ TaskCompletionSource zzfk;
    private final /* synthetic */ zzl zzfl;

    zzo(zzl zzl, TaskCompletionSource taskCompletionSource) {
        this.zzfl = zzl;
        this.zzfk = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        if (!this.zzfk.trySetResult(null)) {
            return;
        }
        if (status.isSuccess()) {
            this.zzfl.zzfh.zzfd.setResult(null);
        } else {
            this.zzfl.zzfh.zzfd.setException(zzaf.zza(status, "Indexing error, please try again."));
        }
    }
}
