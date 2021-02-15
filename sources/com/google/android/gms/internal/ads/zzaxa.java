package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzaxa implements Callable {
    private final Context zzckh;
    private final zzawo zzdyb;

    zzaxa(zzawo zzawo, Context context) {
        this.zzdyb = zzawo;
        this.zzckh = context;
    }

    public final Object call() {
        return this.zzdyb.zzak(this.zzckh);
    }
}
