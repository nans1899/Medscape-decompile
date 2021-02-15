package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.SearchAuthApi;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzas implements SearchAuthApi {
    public final PendingResult<SearchAuthApi.GoogleNowAuthResult> getGoogleNowAuth(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzaw(googleApiClient, str));
    }

    public final PendingResult<Status> clearToken(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzau(googleApiClient, str));
    }
}
