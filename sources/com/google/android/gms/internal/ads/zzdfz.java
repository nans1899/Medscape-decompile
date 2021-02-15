package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfz implements zzdgx<zzdfw> {
    private final ScheduledExecutorService zzfmv;
    private final zzdok zzfsk;
    private final zzdzb zzgcz;
    private final zzcyy zzgqy;
    private String zzgup;
    private final zzcza zzgxj;
    private final Context zzvr;

    public zzdfz(zzdzb zzdzb, ScheduledExecutorService scheduledExecutorService, String str, zzcza zzcza, Context context, zzdok zzdok, zzcyy zzcyy) {
        this.zzgcz = zzdzb;
        this.zzfmv = scheduledExecutorService;
        this.zzgup = str;
        this.zzgxj = zzcza;
        this.zzvr = context;
        this.zzfsk = zzdok;
        this.zzgqy = zzcyy;
    }

    public final zzdzc<zzdfw> zzarj() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqc)).booleanValue()) {
            return zzdyq.zza(new zzdfy(this), this.zzgcz);
        }
        return zzdyq.zzaf(null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(String str, List list, Bundle bundle) throws Exception {
        zzbcg zzbcg = new zzbcg();
        this.zzgqy.zzgo(str);
        zzaph zzgp = this.zzgqy.zzgp(str);
        if (zzgp != null) {
            Bundle bundle2 = bundle;
            zzgp.zza(ObjectWrapper.wrap(this.zzvr), this.zzgup, bundle2, (Bundle) list.get(0), this.zzfsk.zzbpf, (zzapm) new zzczg(str, zzgp, zzbcg));
            return zzbcg;
        }
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzarw() {
        Map<String, List<Bundle>> zzt = this.zzgxj.zzt(this.zzgup, this.zzfsk.zzhfb);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : zzt.entrySet()) {
            String str = (String) next.getKey();
            arrayList.add(zzdyl.zzg(zzdyq.zza(new zzdgb(this, str, (List) next.getValue(), this.zzfsk.zzhfa.zzchi != null ? this.zzfsk.zzhfa.zzchi.getBundle(str) : null), this.zzgcz)).zza(((Long) zzwm.zzpx().zzd(zzabb.zzcqb)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv).zza(Throwable.class, new zzdga(str), (Executor) this.zzgcz));
        }
        return zzdyq.zzk(arrayList).zza(new zzdgd(arrayList), this.zzgcz);
    }
}
