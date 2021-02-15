package com.google.firebase.appindexing.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final /* synthetic */ class zzm implements OnFailureListener {
    private final zzj zzfi;

    zzm(zzj zzj) {
        this.zzfi = zzj;
    }

    public final void onFailure(Exception exc) {
        this.zzfi.zza(exc);
    }
}
