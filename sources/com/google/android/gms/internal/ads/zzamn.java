package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzamn<I, O> implements zzdya<I, O> {
    /* access modifiers changed from: private */
    public final zzalv<O> zzdiw;
    private final zzaly<I> zzdix;
    private final String zzdiy;
    private final zzdzc<zzalp> zzdjc;

    zzamn(zzdzc<zzalp> zzdzc, String str, zzaly<I> zzaly, zzalv<O> zzalv) {
        this.zzdjc = zzdzc;
        this.zzdiy = str;
        this.zzdix = zzaly;
        this.zzdiw = zzalv;
    }

    public final zzdzc<O> zzf(I i) throws Exception {
        return zzdyq.zzb(this.zzdjc, new zzamq(this, i), (Executor) zzbbz.zzeeu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(Object obj, zzalp zzalp) throws Exception {
        zzbcg zzbcg = new zzbcg();
        zzp.zzkr();
        String zzxs = zzayu.zzxs();
        zzagx.zzdfg.zza(zzxs, (zzahy) new zzamp(this, zzbcg));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", zzxs);
        jSONObject.put("args", this.zzdix.zzj(obj));
        zzalp.zzb(this.zzdiy, jSONObject);
        return zzbcg;
    }
}
