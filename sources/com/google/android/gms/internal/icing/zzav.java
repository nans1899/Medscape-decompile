package com.google.android.gms.internal.icing;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzav extends zzar {
    private final /* synthetic */ zzaw zzbq;

    zzav(zzaw zzaw) {
        this.zzbq = zzaw;
    }

    public final void zza(Status status, GoogleNowAuthState googleNowAuthState) {
        if (this.zzbq.zzbp) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl success");
        }
        this.zzbq.setResult(new zzay(status, googleNowAuthState));
    }
}
