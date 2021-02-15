package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.firebase.messaging.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfm {
    private final zzceq zzfyt;
    private final zzcil zzgbo;
    private final zzcjt zzgbx;
    private final zzbnd zzgby;

    public zzcfm(zzcjt zzcjt, zzcil zzcil, zzbnd zzbnd, zzceq zzceq) {
        this.zzgbx = zzcjt;
        this.zzgbo = zzcil;
        this.zzgby = zzbnd;
        this.zzfyt = zzceq;
    }

    public final View zzanl() throws zzbgv {
        zzbgj zzc = this.zzgbx.zzc(zzvn.zzpk());
        zzc.getView().setVisibility(8);
        zzc.zza("/sendMessageToSdk", (zzahq<? super zzbgj>) new zzcfl(this));
        zzc.zza("/adMuted", (zzahq<? super zzbgj>) new zzcfo(this));
        this.zzgbo.zza(new WeakReference(zzc), "/loadHtml", new zzcfn(this));
        this.zzgbo.zza(new WeakReference(zzc), "/showOverlay", new zzcfq(this));
        this.zzgbo.zza(new WeakReference(zzc), "/hideOverlay", new zzcfp(this));
        return zzc.getView();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbgj zzbgj, Map map) {
        zzayp.zzfd("Hiding native ads overlay.");
        zzbgj.getView().setVisibility(8);
        this.zzgby.zzbf(false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzbgj zzbgj, Map map) {
        zzayp.zzfd("Showing native ads overlay.");
        zzbgj.getView().setVisibility(0);
        this.zzgby.zzbf(true);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Map map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.FirelogAnalytics.PARAM_MESSAGE_TYPE, "htmlLoaded");
        hashMap.put("id", (String) map.get("id"));
        this.zzgbo.zza("sendMessageToNativeJs", (Map<String, ?>) hashMap);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzbgj zzbgj, Map map) {
        this.zzfyt.zzalq();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzbgj zzbgj, Map map) {
        this.zzgbo.zza("sendMessageToNativeJs", (Map<String, ?>) map);
    }
}
