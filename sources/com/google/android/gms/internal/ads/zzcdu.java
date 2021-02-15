package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdu implements zzeoy<zzqo> {
    private final zzeph<zzbbx> zzfov;
    private final zzeph<String> zzfow;

    public zzcdu(zzeph<zzbbx> zzeph, zzeph<String> zzeph2) {
        this.zzfov = zzeph;
        this.zzfow = zzeph2;
    }

    public final /* synthetic */ Object get() {
        zzp.zzkr();
        return (zzqo) zzepe.zza(new zzqo(zzayu.zzxs(), this.zzfov.get(), this.zzfow.get(), new JSONObject(), false, true), "Cannot return null from a non-@Nullable @Provides method");
    }
}
