package com.google.android.gms.internal.ads;

import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.zzp;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbna implements zzeoy<zzqo> {
    private final zzeph<JSONObject> zzffa;
    private final zzeph<zzdnv> zzfou;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<String> zzfow;

    private zzbna(zzeph<zzdnv> zzeph, zzeph<zzbbx> zzeph2, zzeph<JSONObject> zzeph3, zzeph<String> zzeph4) {
        this.zzfou = zzeph;
        this.zzfov = zzeph2;
        this.zzffa = zzeph3;
        this.zzfow = zzeph4;
    }

    public static zzbna zza(zzeph<zzdnv> zzeph, zzeph<zzbbx> zzeph2, zzeph<JSONObject> zzeph3, zzeph<String> zzeph4) {
        return new zzbna(zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        zzdnv zzdnv = this.zzfou.get();
        String str = this.zzfow.get();
        boolean equals = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(str);
        zzp.zzkr();
        return (zzqo) zzepe.zza(new zzqo(zzayu.zzxs(), this.zzfov.get(), str, this.zzffa.get(), false, equals), "Cannot return null from a non-@Nullable @Provides method");
    }
}
