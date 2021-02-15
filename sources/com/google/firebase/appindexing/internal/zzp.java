package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.internal.icing.zzf;
import com.google.firebase.FirebaseExceptionMapper;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzp extends GoogleApi<Api.ApiOptions.NoOptions> {
    zzp(Context context) {
        super(context, zzf.zzg, null, (StatusExceptionMapper) new FirebaseExceptionMapper());
    }
}
