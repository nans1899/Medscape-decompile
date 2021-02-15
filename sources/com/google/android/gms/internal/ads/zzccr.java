package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.internal.ads.zzua;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccr implements zzeoy<zzccs> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzawo> zzfmk;
    private final zzeph<View> zzfnp;
    private final zzeph<zzua.zza.C0021zza> zzfow;
    private final zzeph<zzawp> zzfrp;

    private zzccr(zzeph<zzawp> zzeph, zzeph<Context> zzeph2, zzeph<zzawo> zzeph3, zzeph<View> zzeph4, zzeph<zzua.zza.C0021zza> zzeph5) {
        this.zzfrp = zzeph;
        this.zzesu = zzeph2;
        this.zzfmk = zzeph3;
        this.zzfnp = zzeph4;
        this.zzfow = zzeph5;
    }

    public static zzccr zzd(zzeph<zzawp> zzeph, zzeph<Context> zzeph2, zzeph<zzawo> zzeph3, zzeph<View> zzeph4, zzeph<zzua.zza.C0021zza> zzeph5) {
        return new zzccr(zzeph, zzeph2, zzeph3, zzeph4, zzeph5);
    }

    public final /* synthetic */ Object get() {
        return new zzccs(this.zzfrp.get(), this.zzesu.get(), this.zzfmk.get(), this.zzfnp.get(), this.zzfow.get());
    }
}
