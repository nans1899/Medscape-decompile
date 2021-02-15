package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaqe implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzaqc zzdmw;

    zzaqe(zzaqc zzaqc) {
        this.zzdmw = zzaqc;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdmw.zzdx("Operation denied by user.");
    }
}
