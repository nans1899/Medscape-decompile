package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.io.StringReader;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpp {
    private final Executor executor;
    private final zzdok zzfsk;
    private final zzbbx zzgka;
    private final Context zzvr;

    public zzcpp(Context context, zzbbx zzbbx, zzdok zzdok, Executor executor2) {
        this.zzvr = context;
        this.zzgka = zzbbx;
        this.zzfsk = zzdok;
        this.executor = executor2;
    }

    public final zzdzc<zzdog> zzapy() {
        zzalt<I, O> zza = zzp.zzle().zzb(this.zzvr, this.zzgka).zza("google.afma.response.normalize", zzama.zzdio, zzama.zzdio);
        return zzdyq.zzb(zzdyq.zzb(zzdyq.zzb(zzdyq.zzaf(""), new zzcps(this, this.zzfsk.zzhfa.zzcho), this.executor), new zzcpr(zza), this.executor), new zzcpu(this), this.executor);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzo(JSONObject jSONObject) throws Exception {
        return zzdyq.zzaf(new zzdog(new zzdof(this.zzfsk), zzdoe.zza(new StringReader(jSONObject.toString()))));
    }
}
