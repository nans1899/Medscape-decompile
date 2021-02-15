package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbox implements zzeoy<View> {
    private final zzbot zzfqp;

    public zzbox(zzbot zzbot) {
        this.zzfqp = zzbot;
    }

    public static View zza(zzbot zzbot) {
        return (View) zzepe.zza(zzbot.zzahx(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp);
    }
}
