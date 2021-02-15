package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import android.view.WindowInsets;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzazn implements View.OnApplyWindowInsetsListener {
    private final zzazk zzebl;
    private final Activity zzebm;

    zzazn(zzazk zzazk, Activity activity) {
        this.zzebl = zzazk;
        this.zzebm = activity;
    }

    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return zzazk.zza(this.zzebm, view, windowInsets);
    }
}
