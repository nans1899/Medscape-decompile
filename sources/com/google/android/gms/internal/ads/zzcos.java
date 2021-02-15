package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcos implements zzeoy<zzdzc<String>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdsi> zzfqv;

    private zzcos(zzeph<zzdsi> zzeph, zzeph<Context> zzeph2) {
        this.zzfqv = zzeph;
        this.zzesu = zzeph2;
    }

    public static zzcos zzap(zzeph<zzdsi> zzeph, zzeph<Context> zzeph2) {
        return new zzcos(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzdzc) zzepe.zza(this.zzfqv.get().zzu(zzdsf.WEBVIEW_COOKIE).zzc(new zzcoo(zzp.zzkt().zzbh(this.zzesu.get()))).zza(1, TimeUnit.SECONDS).zza(Exception.class, zzcon.zzgjj).zzavs(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
