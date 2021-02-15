package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvo implements zzcvl<zzcdx> {
    private final zzdzb zzgcz;
    private final zzcda zzgpj;
    private final zzcgs zzgpk;
    private final zzdoz<zzcil> zzgpl;

    public zzcvo(zzcda zzcda, zzdzb zzdzb, zzcgs zzcgs, zzdoz<zzcil> zzdoz) {
        this.zzgpj = zzcda;
        this.zzgcz = zzdzb;
        this.zzgpk = zzcgs;
        this.zzgpl = zzdoz;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzfxs == null) ? false : true;
    }

    public final zzdzc<List<zzdzc<zzcdx>>> zzb(zzdog zzdog, zzdnv zzdnv) {
        return zzdyq.zzb(zzdyq.zzb(this.zzgpl.zzatt(), new zzcvn(this, zzdnv), (Executor) this.zzgcz), new zzcvq(this, zzdog, zzdnv), (Executor) this.zzgcz);
    }

    private final zzdzc<zzcdx> zzb(zzdog zzdog, zzdnv zzdnv, JSONObject jSONObject) {
        zzdzc<zzcil> zzatt = this.zzgpl.zzatt();
        zzdzc<zzcei> zza = this.zzgpk.zza(zzdog, zzdnv, jSONObject);
        return zzdyq.zzb((zzdzc<? extends V>[]) new zzdzc[]{zzatt, zza}).zza(new zzcvr(this, zza, zzatt, zzdog, zzdnv, jSONObject), this.zzgcz);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzcdx zza(zzdzc zzdzc, zzdzc zzdzc2, zzdog zzdog, zzdnv zzdnv, JSONObject jSONObject) throws Exception {
        zzcei zzcei = (zzcei) zzdzc.get();
        zzcil zzcil = (zzcil) zzdzc2.get();
        zzcek zza = this.zzgpj.zza(new zzbre(zzdog, zzdnv, (String) null), new zzceu(zzcei), new zzcdl(jSONObject, zzcil));
        zza.zzafh().zzant();
        zza.zzafi().zzb(zzcil);
        zza.zzafj().zzl(zzcei.zzamf());
        return zza.zzafg();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzcil zzcil, JSONObject jSONObject) throws Exception {
        this.zzgpl.zzd(zzdyq.zzaf(zzcil));
        if (jSONObject.optBoolean("success")) {
            return zzdyq.zzaf(jSONObject.getJSONObject("json").getJSONArray("ads"));
        }
        throw new zzalu("process json failed");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdog zzdog, zzdnv zzdnv, JSONArray jSONArray) throws Exception {
        if (jSONArray.length() == 0) {
            return zzdyq.immediateFailedFuture(new zzcof(zzdpg.NO_FILL));
        }
        if (zzdog.zzhev.zzfsk.zzgtu <= 1) {
            return zzdyq.zzb(zzb(zzdog, zzdnv, jSONArray.getJSONObject(0)), zzcvs.zzdwz, (Executor) this.zzgcz);
        }
        int length = jSONArray.length();
        this.zzgpl.zzed(Math.min(length, zzdog.zzhev.zzfsk.zzgtu));
        ArrayList arrayList = new ArrayList(zzdog.zzhev.zzfsk.zzgtu);
        for (int i = 0; i < zzdog.zzhev.zzfsk.zzgtu; i++) {
            if (i < length) {
                arrayList.add(zzb(zzdog, zzdnv, jSONArray.getJSONObject(i)));
            } else {
                arrayList.add(zzdyq.immediateFailedFuture(new zzcof(zzdpg.NO_FILL)));
            }
        }
        return zzdyq.zzaf(arrayList);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdnv zzdnv, zzcil zzcil) throws Exception {
        JSONObject zza = zzbao.zza("isNonagon", (Object) true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("response", zzdnv.zzhdr.zzfxs);
        jSONObject.put("sdk_params", zza);
        return zzdyq.zzb(zzcil.zzc("google.afma.nativeAds.preProcessJson", jSONObject), new zzcvp(this, zzcil), (Executor) this.zzgcz);
    }
}
