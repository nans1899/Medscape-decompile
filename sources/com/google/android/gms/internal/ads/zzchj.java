package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchj {
    private final Executor zzfoc;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;

    public zzchj(zzdok zzdok, Executor executor, zzcjt zzcjt) {
        this.zzfsk = zzdok;
        this.zzfoc = executor;
        this.zzgbx = zzcjt;
    }

    public final zzdzc<zzbgj> zzn(JSONObject jSONObject) {
        return zzdyq.zzb(zzdyq.zzb(zzdyq.zzaf(null), new zzcho(this), this.zzfoc), new zzchm(this, jSONObject), this.zzfoc);
    }

    public final zzdzc<zzbgj> zzo(String str, String str2) {
        return zzdyq.zzb(zzdyq.zzaf(null), new zzchl(this, str, str2), this.zzfoc);
    }

    private final void zzk(zzbgj zzbgj) {
        zzbgj.zza("/video", (zzahq<? super zzbgj>) zzagx.zzdfd);
        zzbgj.zza("/videoMeta", (zzahq<? super zzbgj>) zzagx.zzdfe);
        zzbgj.zza("/precache", (zzahq<? super zzbgj>) new zzbfq());
        zzbgj.zza("/delayPageLoaded", (zzahq<? super zzbgj>) zzagx.zzdfh);
        zzbgj.zza("/instrument", (zzahq<? super zzbgj>) zzagx.zzdff);
        zzbgj.zza("/log", (zzahq<? super zzbgj>) zzagx.zzdey);
        zzbgj.zza("/videoClicked", (zzahq<? super zzbgj>) zzagx.zzdez);
        zzbgj.zzabw().zzay(true);
        zzbgj.zza("/click", (zzahq<? super zzbgj>) zzagx.zzdeu);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrx)).booleanValue()) {
            zzbgj.zza("/getNativeAdViewSignals", (zzahq<? super zzbgj>) zzagx.zzdfk);
        }
        if (this.zzfsk.zzdsi != null) {
            zzbgj.zzabw().zzaz(true);
            zzbgj.zza("/open", (zzahq<? super zzbgj>) new zzahu((zza) null, (zzaqd) null));
        } else {
            zzbgj.zzabw().zzaz(false);
        }
        if (zzp.zzlp().zzac(zzbgj.getContext())) {
            zzbgj.zza("/logScionEvent", (zzahq<? super zzbgj>) new zzahs(zzbgj.getContext()));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzr(Object obj) throws Exception {
        zzbgj zzc = this.zzgbx.zzc(zzvn.zzpk());
        zzbch zzl = zzbch.zzl(zzc);
        zzk(zzc);
        zzc.zzabw().zza((zzbhx) new zzchn(zzl));
        zzc.loadUrl((String) zzwm.zzpx().zzd(zzabb.zzcrw));
        return zzl;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(String str, String str2, Object obj) throws Exception {
        zzbgj zzc = this.zzgbx.zzc(zzvn.zzpk());
        zzbch zzl = zzbch.zzl(zzc);
        zzk(zzc);
        if (this.zzfsk.zzdsi != null) {
            zzc.zza(zzbhy.zzada());
        } else {
            zzc.zza(zzbhy.zzacz());
        }
        zzc.zzabw().zza((zzbhu) new zzchq(this, zzc, zzl));
        zzc.zzb(str, str2, (String) null);
        return zzl;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbgj zzbgj, zzbch zzbch, boolean z) {
        if (z) {
            if (!(this.zzfsk.zzhez == null || zzbgj.zzzs() == null)) {
                zzbgj.zzzs().zzb(this.zzfsk.zzhez);
            }
            zzbch.zzyw();
            return;
        }
        zzbch.setException(new zzcwn(zzdpg.INTERNAL_ERROR, "Instream video Web View failed to load."));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(JSONObject jSONObject, zzbgj zzbgj) throws Exception {
        zzbch zzl = zzbch.zzl(zzbgj);
        if (this.zzfsk.zzdsi != null) {
            zzbgj.zza(zzbhy.zzada());
        } else {
            zzbgj.zza(zzbhy.zzacz());
        }
        zzbgj.zzabw().zza((zzbhu) new zzchp(this, zzbgj, zzl));
        zzbgj.zzb("google.afma.nativeAds.renderVideo", jSONObject);
        return zzl;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzbgj zzbgj, zzbch zzbch, boolean z) {
        if (!(this.zzfsk.zzhez == null || zzbgj.zzzs() == null)) {
            zzbgj.zzzs().zzb(this.zzfsk.zzhez);
        }
        zzbch.zzyw();
    }
}
