package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcqv extends zzass {
    private final Executor zzfoc;
    private final zzatt zzglb;
    private final zzatq zzglc;
    private final zzbly zzgld;
    private final HashMap<String, zzcrk> zzgle;
    private final Context zzvr;

    public zzcqv(Context context, Executor executor, zzatt zzatt, zzbly zzbly, zzatq zzatq, HashMap<String, zzcrk> hashMap) {
        zzabb.initialize(context);
        this.zzvr = context;
        this.zzfoc = executor;
        this.zzglb = zzatt;
        this.zzglc = zzatq;
        this.zzgld = zzbly;
        this.zzgle = hashMap;
    }

    public final zzasp zza(zzasn zzasn) throws RemoteException {
        return null;
    }

    public final void zza(zzasn zzasn, zzasu zzasu) throws RemoteException {
    }

    public final zzdzc<InputStream> zzb(zzatc zzatc, int i) {
        zzamb zza = zzp.zzle().zza(this.zzvr, zzbbx.zzyv());
        zzdhp zza2 = this.zzgld.zza(zzatc, i);
        zzalt<I, O> zza3 = zza.zza("google.afma.response.normalize", zzcrj.zzglm, zzama.zzdip);
        zzcro zzcro = new zzcro(this.zzvr, zzatc.zzdqm.zzbre, this.zzglb, zzatc.zzdrm, i);
        zzdsi zzafo = zza2.zzafo();
        zzcrk zzcrk = null;
        if (!zzada.zzdca.get().booleanValue()) {
            if (zzatc.zzduc != null && !zzatc.zzduc.isEmpty()) {
                zzayp.zzei("Request contained a PoolKey but split request is disabled.");
            }
        } else if (zzatc.zzduc != null && !zzatc.zzduc.isEmpty() && (zzcrk = this.zzgle.remove(zzatc.zzduc)) == null) {
            zzayp.zzei("Request contained a PoolKey but no matching parameters were found.");
        }
        if (zzcrk == null) {
            zzdzc<JSONObject> zza4 = zza(zzatc, zzafo, zza2);
            zzdzc<zzati> zza5 = zza(zza4, zzafo, zza);
            zzdrr zzavs = zzafo.zza(zzdsf.HTTP, (zzdzc<?>[]) new zzdzc[]{zza5, zza4}).zzb(new zzcqy(zza4, zza5)).zzb(zzcro).zzavs();
            return zzafo.zza(zzdsf.PRE_PROCESS, (zzdzc<?>[]) new zzdzc[]{zza4, zza5, zzavs}).zzb(new zzcqx(zzavs, zza4, zza5)).zza(zza3).zzavs();
        }
        zzdrr zzavs2 = zzafo.zza(zzdsf.HTTP, zzdyq.zzaf(new zzcrn(zzcrk.zzglk, zzcrk.zzgll))).zzb(zzcro).zzavs();
        zzdzc zzaf = zzdyq.zzaf(zzcrk);
        return zzafo.zza(zzdsf.PRE_PROCESS, (zzdzc<?>[]) new zzdzc[]{zzavs2, zzaf}).zzb(new zzcra(zzavs2, zzaf)).zza(zza3).zzavs();
    }

    private static zzdzc<JSONObject> zza(zzatc zzatc, zzdsi zzdsi, zzdhp zzdhp) {
        zzcqz zzcqz = new zzcqz(zzdhp);
        return zzdsi.zza(zzdsf.GMS_SIGNALS, zzdyq.zzaf(zzatc.zzdty)).zza(zzcqz).zzb(zzcrc.zzgjj).zzavs();
    }

    private static zzdzc<zzati> zza(zzdzc<JSONObject> zzdzc, zzdsi zzdsi, zzamb zzamb) {
        return zzdsi.zza(zzdsf.BUILD_URL, zzdzc).zza(zzamb.zza("AFMA_getAdDictionary", zzama.zzdio, zzcrb.zzdin)).zzavs();
    }

    public final void zza(zzatc zzatc, zzasw zzasw) {
        zzdzc<InputStream> zzb = zzb(zzatc, Binder.getCallingUid());
        zza(zzb, zzasw);
        zzb.addListener(new zzcre(this), this.zzfoc);
    }

    public final zzdzc<InputStream> zzc(zzatc zzatc, int i) {
        if (!zzada.zzdca.get().booleanValue()) {
            return zzdyq.immediateFailedFuture(new Exception("Split request is disabled."));
        }
        if (zzatc.zzdub == null) {
            return zzdyq.immediateFailedFuture(new Exception("Pool configuration missing from request."));
        }
        if (zzatc.zzdub.zzhhy == 0 || zzatc.zzdub.zzhhz == 0) {
            return zzdyq.immediateFailedFuture(new Exception("Caching is disabled."));
        }
        zzamb zza = zzp.zzle().zza(this.zzvr, zzbbx.zzyv());
        zzdhp zza2 = this.zzgld.zza(zzatc, i);
        zzdsi zzafo = zza2.zzafo();
        zzdzc<JSONObject> zza3 = zza(zzatc, zzafo, zza2);
        zzdzc<zzati> zza4 = zza(zza3, zzafo, zza);
        return zzafo.zza(zzdsf.GET_URL_AND_CACHE_KEY, (zzdzc<?>[]) new zzdzc[]{zza3, zza4}).zzb(new zzcrd(this, zza4, zza3)).zzavs();
    }

    public final zzdzc<InputStream> zzgn(String str) {
        if (!zzada.zzdca.get().booleanValue()) {
            return zzdyq.immediateFailedFuture(new Exception("Split request is disabled."));
        }
        zzcri zzcri = new zzcri(this);
        if (this.zzgle.remove(str) != null) {
            return zzdyq.zzaf(zzcri);
        }
        String valueOf = String.valueOf(str);
        return zzdyq.immediateFailedFuture(new Exception(valueOf.length() != 0 ? "URL to be removed not found for cache key: ".concat(valueOf) : new String("URL to be removed not found for cache key: ")));
    }

    public final void zzc(zzatc zzatc, zzasw zzasw) {
        zza(zzc(zzatc, Binder.getCallingUid()), zzasw);
    }

    public final void zza(String str, zzasw zzasw) {
        zza(zzgn(str), zzasw);
    }

    public final zzdzc<InputStream> zzd(zzatc zzatc, int i) {
        zzamb zza = zzp.zzle().zza(this.zzvr, zzbbx.zzyv());
        if (!zzadg.zzdcr.get().booleanValue()) {
            return zzdyq.immediateFailedFuture(new Exception("Signal collection disabled."));
        }
        zzdhp zza2 = this.zzgld.zza(zzatc, i);
        zzdgw<JSONObject> zzafn = zza2.zzafn();
        return zza2.zzafo().zza(zzdsf.GET_SIGNALS, zzdyq.zzaf(zzatc.zzdty)).zza(new zzcrg(zzafn)).zzw(zzdsf.JS_SIGNALS).zza(zza.zza("google.afma.request.getSignals", zzama.zzdio, zzama.zzdip)).zzavs();
    }

    public final void zzb(zzatc zzatc, zzasw zzasw) {
        zza(zzd(zzatc, Binder.getCallingUid()), zzasw);
    }

    private final void zza(zzdzc<InputStream> zzdzc, zzasw zzasw) {
        zzdyq.zza(zzdyq.zzb(zzdzc, new zzcrf(this), (Executor) zzbbz.zzeep), new zzcrh(this, zzasw), zzbbz.zzeeu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ InputStream zza(zzdzc zzdzc, zzdzc zzdzc2) throws Exception {
        String zzvm = ((zzati) zzdzc.get()).zzvm();
        this.zzgle.put(zzvm, new zzcrk((zzati) zzdzc.get(), (JSONObject) zzdzc2.get()));
        return new ByteArrayInputStream(zzvm.getBytes(zzdvs.UTF_8));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaqa() {
        zzbcc.zza(this.zzglc.zzvo(), "persistFlags");
    }
}
