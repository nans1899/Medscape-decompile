package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.icing.zzbq;
import com.google.firebase.FirebaseExceptionMapper;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzh extends GoogleApi<Api.ApiOptions.NoOptions> {
    public zzh(Context context) {
        super(context, zze.API, null, Looper.getMainLooper(), new FirebaseExceptionMapper());
        zzbq.zzg(context);
    }
}
