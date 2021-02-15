package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.search.SearchAuth;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzau extends BaseImplementation.ApiMethodImpl<Status, zzap> {
    private final String zzbk;
    private final String zzbo;
    /* access modifiers changed from: private */
    public final boolean zzbp = Log.isLoggable("SearchAuth", 3);

    protected zzau(GoogleApiClient googleApiClient, String str) {
        super((Api<?>) SearchAuth.API, googleApiClient);
        this.zzbk = str;
        this.zzbo = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzap zzap = (zzap) anyClient;
        if (this.zzbp) {
            Log.d("SearchAuth", "ClearTokenImpl started");
        }
        ((zzan) zzap.getService()).zzb(new zzat(this), this.zzbo, this.zzbk);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        if (this.zzbp) {
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d("SearchAuth", valueOf.length() != 0 ? "ClearTokenImpl received failure: ".concat(valueOf) : new String("ClearTokenImpl received failure: "));
        }
        return status;
    }
}
