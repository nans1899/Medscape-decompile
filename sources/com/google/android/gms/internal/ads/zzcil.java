package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzb;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcil {
    private final zzbgr zzbpt;
    private final zzbbx zzdqm;
    private final zzeg zzeom;
    private final Executor zzfoc;
    private final zzb zzgdm;
    /* access modifiers changed from: private */
    public final zzcit zzgeg = new zzcit((zzciq) null);
    private final zzahw zzgeh;
    private zzdzc<zzbgj> zzgei;
    private final Context zzvr;

    zzcil(zzciy zzciy) {
        this.zzvr = zzciy.zzvr;
        this.zzfoc = zzciy.zzfoc;
        this.zzeom = zzciy.zzeom;
        this.zzdqm = zzciy.zzdqm;
        this.zzgdm = zzciy.zzgdm;
        this.zzbpt = zzciy.zzbpt;
        this.zzgeh = new zzahw();
    }

    public final synchronized void zzans() {
        zzdzc<zzbgj> zzb = zzdyq.zzb(zzbgr.zza(this.zzvr, this.zzdqm, (String) zzwm.zzpx().zzd(zzabb.zzcru), this.zzeom, this.zzgdm), new zzcio(this), this.zzfoc);
        this.zzgei = zzb;
        zzbcc.zza(zzb, "NativeJavascriptExecutor.initializeEngine");
    }

    public final synchronized void destroy() {
        if (this.zzgei != null) {
            zzdyq.zza(this.zzgei, new zzciq(this), this.zzfoc);
            this.zzgei = null;
        }
    }

    public final synchronized zzdzc<JSONObject> zzc(String str, JSONObject jSONObject) {
        if (this.zzgei == null) {
            return zzdyq.zzaf(null);
        }
        return zzdyq.zzb(this.zzgei, new zzcin(this, str, jSONObject), this.zzfoc);
    }

    public final synchronized void zza(String str, zzahq<Object> zzahq) {
        if (this.zzgei != null) {
            zzdyq.zza(this.zzgei, new zzcip(this, str, zzahq), this.zzfoc);
        }
    }

    public final synchronized void zzb(String str, zzahq<Object> zzahq) {
        if (this.zzgei != null) {
            zzdyq.zza(this.zzgei, new zzcis(this, str, zzahq), this.zzfoc);
        }
    }

    public final synchronized void zza(String str, Map<String, ?> map) {
        if (this.zzgei != null) {
            zzdyq.zza(this.zzgei, new zzcir(this, str, map), this.zzfoc);
        }
    }

    public final synchronized void zza(zzdnv zzdnv, zzdnw zzdnw) {
        if (this.zzgei != null) {
            zzdyq.zza(this.zzgei, new zzciu(this, zzdnv, zzdnw), this.zzfoc);
        }
    }

    public final <T> void zza(WeakReference<T> weakReference, String str, zzahq<T> zzahq) {
        zza(str, (zzahq<Object>) new zzcix(this, weakReference, str, zzahq, (zzciq) null));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(String str, JSONObject jSONObject, zzbgj zzbgj) throws Exception {
        return this.zzgeh.zza(zzbgj, str, jSONObject);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzbgj zzm(zzbgj zzbgj) {
        zzbgj.zza("/result", (zzahq<? super zzbgj>) this.zzgeh);
        zzbhv zzabw = zzbgj.zzabw();
        zzcit zzcit = this.zzgeg;
        zzabw.zza((zzux) null, zzcit, zzcit, zzcit, zzcit, false, (zzahp) null, new zza(this.zzvr, (zzawl) null, (zzasr) null), (zzaqq) null, (zzawl) null);
        return zzbgj;
    }
}
