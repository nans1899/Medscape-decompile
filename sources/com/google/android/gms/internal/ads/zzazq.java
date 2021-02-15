package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzazq implements DialogInterface.OnClickListener {
    private final String zzdgm;
    private final zzazp zzebn;

    zzazq(zzazp zzazp, String str) {
        this.zzebn = zzazp;
        this.zzdgm = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzebn.zza(this.zzdgm, dialogInterface, i);
    }
}
