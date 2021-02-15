package com.google.android.gms.internal.icing;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public interface zzam extends IInterface {
    void zza(Status status, GoogleNowAuthState googleNowAuthState) throws RemoteException;

    void zzb(Status status) throws RemoteException;
}
