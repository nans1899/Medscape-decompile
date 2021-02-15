package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcoq implements Callable {
    private final Context zzckh;
    private final zzeg zzgjl;

    zzcoq(zzeg zzeg, Context context) {
        this.zzgjl = zzeg;
        this.zzckh = context;
    }

    public final Object call() {
        zzeg zzeg = this.zzgjl;
        return zzeg.zzca().zzb(this.zzckh);
    }
}
