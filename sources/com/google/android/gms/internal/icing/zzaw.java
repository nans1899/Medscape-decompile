package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.search.GoogleNowAuthState;
import com.google.android.gms.search.SearchAuth;
import com.google.android.gms.search.SearchAuthApi;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzaw extends BaseImplementation.ApiMethodImpl<SearchAuthApi.GoogleNowAuthResult, zzap> {
    private final String zzbo;
    /* access modifiers changed from: private */
    public final boolean zzbp = Log.isLoggable("SearchAuth", 3);
    private final String zzbr;

    protected zzaw(GoogleApiClient googleApiClient, String str) {
        super((Api<?>) SearchAuth.API, googleApiClient);
        this.zzbr = str;
        this.zzbo = googleApiClient.getContext().getPackageName();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzap zzap = (zzap) anyClient;
        if (this.zzbp) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl started");
        }
        ((zzan) zzap.getService()).zza(new zzav(this), this.zzbo, this.zzbr);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        if (this.zzbp) {
            String valueOf = String.valueOf(status.getStatusMessage());
            Log.d("SearchAuth", valueOf.length() != 0 ? "GetGoogleNowAuthImpl received failure: ".concat(valueOf) : new String("GetGoogleNowAuthImpl received failure: "));
        }
        return new zzay(status, (GoogleNowAuthState) null);
    }
}
