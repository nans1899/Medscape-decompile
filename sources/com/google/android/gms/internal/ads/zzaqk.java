package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaqk implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzaqi zzdnu;

    zzaqk(zzaqi zzaqi) {
        this.zzdnu = zzaqi;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdnu.zzdx("User canceled the download.");
    }
}
