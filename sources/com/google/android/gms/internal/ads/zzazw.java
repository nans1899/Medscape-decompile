package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzazw implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzazx zzeca;

    zzazw(zzazx zzazx) {
        this.zzeca = zzazx;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        zzp.zzkr();
        zzayu.zza(this.zzeca.val$context, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
