package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final /* synthetic */ class zzdtu implements OnFailureListener {
    private final zzdtp zzhmw;

    zzdtu(zzdtp zzdtp) {
        this.zzhmw = zzdtp;
    }

    public final void onFailure(Exception exc) {
        this.zzhmw.zzc(exc);
    }
}
