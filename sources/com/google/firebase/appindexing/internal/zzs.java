package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.icing.zzaa;
import com.google.android.gms.internal.icing.zzah;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
abstract class zzs extends TaskApiCall<zzah, Void> implements BaseImplementation.ResultHolder<Status> {
    private TaskCompletionSource<Void> zzfn;

    private zzs() {
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzaa zzaa) throws RemoteException;

    public void setFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success.");
        this.zzfn.setException(zzaf.zza(status, status.getStatusMessage()));
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzfn = taskCompletionSource;
        zza((zzaa) ((zzah) anyClient).getService());
    }

    public /* synthetic */ void setResult(Object obj) {
        Status status = (Status) obj;
        if (status.isSuccess()) {
            this.zzfn.setResult(null);
        } else {
            this.zzfn.setException(zzaf.zza(status, "User Action indexing error, please try again."));
        }
    }

    /* synthetic */ zzs(zzq zzq) {
        this();
    }
}
