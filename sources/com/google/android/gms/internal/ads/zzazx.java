package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzazx implements Runnable {
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzecb;
    private final /* synthetic */ boolean zzecc;
    private final /* synthetic */ boolean zzecd;

    zzazx(zzazu zzazu, Context context, String str, boolean z, boolean z2) {
        this.val$context = context;
        this.zzecb = str;
        this.zzecc = z;
        this.zzecd = z2;
    }

    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.val$context);
        builder.setMessage(this.zzecb);
        if (this.zzecc) {
            builder.setTitle("Error");
        } else {
            builder.setTitle("Info");
        }
        if (this.zzecd) {
            builder.setNeutralButton("Dismiss", (DialogInterface.OnClickListener) null);
        } else {
            builder.setPositiveButton("Learn More", new zzazw(this));
            builder.setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null);
        }
        builder.create().show();
    }
}
