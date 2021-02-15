package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgs {
    private final zzdzb zzgcz;
    private final zzcgw zzgda;
    private final zzchg zzgdb;

    public zzcgs(zzdzb zzdzb, zzcgw zzcgw, zzchg zzchg) {
        this.zzgcz = zzdzb;
        this.zzgda = zzcgw;
        this.zzgdb = zzchg;
    }

    public final zzdzc<zzcei> zza(zzdog zzdog, zzdnv zzdnv, JSONObject jSONObject) {
        zzdzc zzb;
        JSONObject jSONObject2 = jSONObject;
        zzdog zzdog2 = zzdog;
        zzdzc zze = this.zzgcz.zze(new zzcgr(this, zzdog, zzdnv, jSONObject2));
        zzdzc<List<zzadq>> zzd = this.zzgda.zzd(jSONObject2, Constants.WBMDDrugResponseKeyDrugImages);
        zzdzc<zzadq> zzc = this.zzgda.zzc(jSONObject2, "secondary_image");
        zzdzc<zzadq> zzc2 = this.zzgda.zzc(jSONObject2, "app_icon");
        zzdzc<zzadl> zze2 = this.zzgda.zze(jSONObject2, "attribution");
        zzdzc<zzbgj> zzm = this.zzgda.zzm(jSONObject2);
        zzcgw zzcgw = this.zzgda;
        if (!jSONObject2.optBoolean("enable_omid")) {
            zzb = zzdyq.zzaf(null);
        } else {
            JSONObject optJSONObject = jSONObject2.optJSONObject("omid_settings");
            if (optJSONObject == null) {
                zzb = zzdyq.zzaf(null);
            } else {
                String optString = optJSONObject.optString("omid_html");
                if (TextUtils.isEmpty(optString)) {
                    zzb = zzdyq.zzaf(null);
                } else {
                    zzb = zzdyq.zzb(zzdyq.zzaf(null), new zzcha(zzcgw, optString), (Executor) zzbbz.zzeet);
                }
            }
        }
        zzdzc zzdzc = zzb;
        zzdzc<List<zzchh>> zzg = this.zzgdb.zzg(jSONObject2, "custom_assets");
        return zzdyq.zza((zzdzc<? extends V>[]) new zzdzc[]{zze, zzd, zzc, zzc2, zze2, zzm, zzdzc, zzg}).zza(new zzcgu(this, zze, zzd, zzc2, zzc, zze2, jSONObject, zzm, zzdzc, zzg), this.zzgcz);
    }
}
