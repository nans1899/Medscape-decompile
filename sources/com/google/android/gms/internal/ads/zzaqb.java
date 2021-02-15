package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaqb implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzaqc zzdmw;

    zzaqb(zzaqc zzaqc) {
        this.zzdmw = zzaqc;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent createIntent = this.zzdmw.createIntent();
        zzp.zzkr();
        zzayu.zza(this.zzdmw.zzvr, createIntent);
    }
}
