package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzamg<I, O> implements zzalt<I, O> {
    private final zzakr zzdis;
    /* access modifiers changed from: private */
    public final zzalv<O> zzdiw;
    private final zzaly<I> zzdix;
    private final String zzdiy;

    zzamg(zzakr zzakr, String str, zzaly<I> zzaly, zzalv<O> zzalv) {
        this.zzdis = zzakr;
        this.zzdiy = str;
        this.zzdix = zzaly;
        this.zzdiw = zzalv;
    }

    public final zzdzc<O> zzi(I i) {
        zzbcg zzbcg = new zzbcg();
        zzali zzb = this.zzdis.zzb((zzeg) null);
        zzb.zza(new zzamf(this, zzb, i, zzbcg), new zzami(this, zzbcg, zzb));
        return zzbcg;
    }

    /* access modifiers changed from: private */
    public final void zza(zzali zzali, zzalp zzalp, I i, zzbcg<O> zzbcg) {
        try {
            zzp.zzkr();
            String zzxs = zzayu.zzxs();
            zzagx.zzdfg.zza(zzxs, (zzahy) new zzamh(this, zzali, zzbcg));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", zzxs);
            jSONObject.put("args", this.zzdix.zzj(i));
            zzalp.zzb(this.zzdiy, jSONObject);
        } catch (Exception e) {
            zzbcg.setException(e);
            zzayp.zzc("Unable to invokeJavascript", e);
            zzali.release();
        } catch (Throwable th) {
            zzali.release();
            throw th;
        }
    }

    public final zzdzc<O> zzf(I i) throws Exception {
        return zzi(i);
    }
}
