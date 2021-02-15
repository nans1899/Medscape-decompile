package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzazr implements DialogInterface.OnClickListener {
    private final zzazp zzebn;
    private final int zzebs;
    private final int zzebt;
    private final int zzebu;

    zzazr(zzazp zzazp, int i, int i2, int i3) {
        this.zzebn = zzazp;
        this.zzebs = i;
        this.zzebt = i2;
        this.zzebu = i3;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzebn.zza(this.zzebs, this.zzebt, this.zzebu, dialogInterface, i);
    }
}
