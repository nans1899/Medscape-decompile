package com.google.android.gms.internal.ads;

import java.util.List;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbty extends zzym {
    private final List<zzvr> zzadu;
    private final String zzful;
    private final String zzfum;

    public zzbty(zzdnv zzdnv, String str, zzctd zzctd) {
        String str2;
        String str3 = null;
        if (zzdnv == null) {
            str2 = null;
        } else {
            str2 = zzdnv.zzfum;
        }
        this.zzfum = str2;
        str3 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) ? zzc(zzdnv) : str3;
        this.zzful = str3 != null ? str3 : str;
        this.zzadu = zzctd.getAdapterResponses();
    }

    public final String getMediationAdapterClassName() {
        return this.zzful;
    }

    public final String getResponseId() {
        return this.zzfum;
    }

    private static String zzc(zzdnv zzdnv) {
        try {
            return zzdnv.zzhdu.getString("class_name");
        } catch (JSONException unused) {
            return null;
        }
    }

    public final List<zzvr> getAdapterResponses() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            return null;
        }
        return this.zzadu;
    }
}
