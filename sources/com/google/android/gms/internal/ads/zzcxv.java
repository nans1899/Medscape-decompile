package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzbvp;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxv<AdT, AdapterT, ListenerT extends zzbvp> implements zzcta<AdT> {
    private final zzctb<AdapterT, ListenerT> zzfmb;
    private final zzdsi zzfsl;
    private final zzcti<AdT, AdapterT, ListenerT> zzgrb;
    private final zzdzb zzgrc;

    public zzcxv(zzdsi zzdsi, zzdzb zzdzb, zzctb<AdapterT, ListenerT> zzctb, zzcti<AdT, AdapterT, ListenerT> zzcti) {
        this.zzfsl = zzdsi;
        this.zzgrc = zzdzb;
        this.zzgrb = zzcti;
        this.zzfmb = zzctb;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return !zzdnv.zzhds.isEmpty();
    }

    public final zzdzc<AdT> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzctc<AdapterT, ListenerT> zzctc;
        Iterator<String> it = zzdnv.zzhds.iterator();
        while (true) {
            if (!it.hasNext()) {
                zzctc = null;
                break;
            }
            try {
                zzctc = this.zzfmb.zzf(it.next(), zzdnv.zzhdu);
                break;
            } catch (zzdos unused) {
            }
        }
        if (zzctc == null) {
            return zzdyq.immediateFailedFuture(new zzcvz("unable to instantiate mediation adapter class"));
        }
        zzbcg zzbcg = new zzbcg();
        zzctc.zzgns.zza(new zzcya(this, zzctc, zzbcg));
        if (zzdnv.zzduj) {
            Bundle bundle = zzdog.zzhev.zzfsk.zzhfa.zzchi;
            Bundle bundle2 = bundle.getBundle(AdMobAdapter.class.getName());
            if (bundle2 == null) {
                bundle2 = new Bundle();
                bundle.putBundle(AdMobAdapter.class.getName(), bundle2);
            }
            bundle2.putBoolean("render_test_ad_label", true);
        }
        return this.zzfsl.zzu(zzdsf.ADAPTER_LOAD_AD_SYN).zza((zzdrs) new zzcxy(this, zzdog, zzdnv, zzctc), this.zzgrc).zzw(zzdsf.ADAPTER_LOAD_AD_ACK).zze(zzbcg).zzw(zzdsf.ADAPTER_WRAP_ADAPTER).zzb(new zzcxx(this, zzdog, zzdnv, zzctc)).zzavs();
    }

    static String zza(String str, int i) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 31);
        sb.append("Error from: ");
        sb.append(str);
        sb.append(", code: ");
        sb.append(i);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(zzdog zzdog, zzdnv zzdnv, zzctc zzctc, Void voidR) throws Exception {
        return this.zzgrb.zzb(zzdog, zzdnv, zzctc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws Exception {
        this.zzgrb.zza(zzdog, zzdnv, zzctc);
    }
}
