package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbnm implements zzeoy<View> {
    private final zzbnj zzfpi;

    public zzbnm(zzbnj zzbnj) {
        this.zzfpi = zzbnj;
    }

    public static View zza(zzbnj zzbnj) {
        return (View) zzepe.zza(zzbnj.zzahm(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfpi);
    }
}
