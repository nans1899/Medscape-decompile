package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnu {
    private boolean enabled = true;
    /* access modifiers changed from: private */
    public final Executor executor;
    private final zzbbx zzbot;
    private final Context zzclo;
    private final Executor zzfoc;
    private final ScheduledExecutorService zzfoz;
    private boolean zzgie = false;
    /* access modifiers changed from: private */
    public boolean zzgif = false;
    /* access modifiers changed from: private */
    public final long zzgig;
    /* access modifiers changed from: private */
    public final zzbcg<Boolean> zzgih = new zzbcg<>();
    private final WeakReference<Context> zzgii;
    private final zzcku zzgij;
    /* access modifiers changed from: private */
    public final zzcne zzgik;
    private Map<String, zzaiq> zzgil = new ConcurrentHashMap();

    public zzcnu(Executor executor2, Context context, WeakReference<Context> weakReference, Executor executor3, zzcku zzcku, ScheduledExecutorService scheduledExecutorService, zzcne zzcne, zzbbx zzbbx) {
        this.zzgij = zzcku;
        this.zzclo = context;
        this.zzgii = weakReference;
        this.executor = executor3;
        this.zzfoz = scheduledExecutorService;
        this.zzfoc = executor2;
        this.zzgik = zzcne;
        this.zzbot = zzbbx;
        this.zzgig = zzp.zzky().elapsedRealtime();
        zza("com.google.android.gms.ads.MobileAds", false, "", 0);
    }

    public final void disable() {
        this.enabled = false;
    }

    public final void zzb(zzait zzait) {
        this.zzgih.addListener(new zzcnt(this, zzait), this.zzfoc);
    }

    public final void zzapl() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqi)).booleanValue() && !zzacx.zzdbt.get().booleanValue()) {
            if (this.zzbot.zzeem >= ((Integer) zzwm.zzpx().zzd(zzabb.zzcqj)).intValue() && this.enabled) {
                if (!this.zzgie) {
                    synchronized (this) {
                        if (!this.zzgie) {
                            this.zzgik.zzapi();
                            this.zzgih.addListener(new zzcnw(this), this.executor);
                            this.zzgie = true;
                            zzdzc<String> zzapn = zzapn();
                            this.zzfoz.schedule(new zzcny(this), ((Long) zzwm.zzpx().zzd(zzabb.zzcql)).longValue(), TimeUnit.SECONDS);
                            zzdyq.zza(zzapn, new zzcob(this), this.executor);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
        }
        if (!this.zzgie) {
            zza("com.google.android.gms.ads.MobileAds", true, "", 0);
            this.zzgih.set(false);
            this.zzgie = true;
        }
    }

    public final List<zzaiq> zzapm() {
        ArrayList arrayList = new ArrayList();
        for (String next : this.zzgil.keySet()) {
            zzaiq zzaiq = this.zzgil.get(next);
            arrayList.add(new zzaiq(next, zzaiq.zzdga, zzaiq.zzdgb, zzaiq.description));
        }
        return arrayList;
    }

    private final synchronized zzdzc<String> zzapn() {
        String zzwg = zzp.zzkv().zzwt().zzxk().zzwg();
        if (!TextUtils.isEmpty(zzwg)) {
            return zzdyq.zzaf(zzwg);
        }
        zzbcg zzbcg = new zzbcg();
        zzp.zzkv().zzwt().zzb(new zzcnv(this, zzbcg));
        return zzbcg;
    }

    /* access modifiers changed from: private */
    public final void zzgl(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str).getJSONObject("initializer_settings").getJSONObject(Constants.CONFIG_FILE_NAME);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = new Object();
                zzbcg zzbcg = new zzbcg();
                zzdzc zza = zzdyq.zza(zzbcg, ((Long) zzwm.zzpx().zzd(zzabb.zzcqk)).longValue(), TimeUnit.SECONDS, this.zzfoz);
                this.zzgik.zzgj(next);
                long elapsedRealtime = zzp.zzky().elapsedRealtime();
                Iterator<String> it = keys;
                zzcnx zzcnx = r1;
                zzcnx zzcnx2 = new zzcnx(this, obj, zzbcg, next, elapsedRealtime);
                zza.addListener(zzcnx, this.executor);
                arrayList.add(zza);
                zzcod zzcod = new zzcod(this, obj, next, elapsedRealtime, zzbcg);
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                ArrayList arrayList2 = new ArrayList();
                if (optJSONObject != null) {
                    try {
                        JSONArray jSONArray = optJSONObject.getJSONArray("data");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String optString = jSONObject2.optString("format", "");
                            JSONObject optJSONObject2 = jSONObject2.optJSONObject("data");
                            Bundle bundle = new Bundle();
                            if (optJSONObject2 != null) {
                                Iterator<String> keys2 = optJSONObject2.keys();
                                while (keys2.hasNext()) {
                                    String next2 = keys2.next();
                                    bundle.putString(next2, optJSONObject2.optString(next2, ""));
                                }
                            }
                            arrayList2.add(new zzaja(optString, bundle));
                        }
                    } catch (JSONException unused) {
                    }
                }
                zza(next, false, "", 0);
                try {
                    this.zzfoc.execute(new zzcnz(this, this.zzgij.zzd(next, new JSONObject()), zzcod, arrayList2, next));
                } catch (zzdos unused2) {
                    try {
                        zzcod.onInitializationFailed("Failed to create Adapter.");
                    } catch (RemoteException e) {
                        zzbbq.zzc("", e);
                    }
                }
                keys = it;
            }
            zzdyq.zzj(arrayList).zza(new zzcoa(this), this.executor);
        } catch (JSONException e2) {
            zzayp.zza("Malformed CLD response", e2);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(String str, boolean z, String str2, int i) {
        this.zzgil.put(str, new zzaiq(str, z, i, str2));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        com.google.android.gms.internal.ads.zzbbq.zzc("", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r4 = new java.lang.StringBuilder(java.lang.String.valueOf(r5).length() + 74);
        r4.append("Failed to initialize adapter. ");
        r4.append(r5);
        r4.append(" does not implement the initialize() method.");
        r3.onInitializationFailed(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(com.google.android.gms.internal.ads.zzdpa r2, com.google.android.gms.internal.ads.zzais r3, java.util.List r4, java.lang.String r5) {
        /*
            r1 = this;
            java.lang.ref.WeakReference<android.content.Context> r0 = r1.zzgii     // Catch:{ zzdos -> 0x0011 }
            java.lang.Object r0 = r0.get()     // Catch:{ zzdos -> 0x0011 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ zzdos -> 0x0011 }
            if (r0 == 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            android.content.Context r0 = r1.zzclo     // Catch:{ zzdos -> 0x0011 }
        L_0x000d:
            r2.zza((android.content.Context) r0, (com.google.android.gms.internal.ads.zzais) r3, (java.util.List<com.google.android.gms.internal.ads.zzaja>) r4)     // Catch:{ zzdos -> 0x0011 }
            return
        L_0x0011:
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ RemoteException -> 0x0035 }
            int r2 = r2.length()     // Catch:{ RemoteException -> 0x0035 }
            int r2 = r2 + 74
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0035 }
            r4.<init>(r2)     // Catch:{ RemoteException -> 0x0035 }
            java.lang.String r2 = "Failed to initialize adapter. "
            r4.append(r2)     // Catch:{ RemoteException -> 0x0035 }
            r4.append(r5)     // Catch:{ RemoteException -> 0x0035 }
            java.lang.String r2 = " does not implement the initialize() method."
            r4.append(r2)     // Catch:{ RemoteException -> 0x0035 }
            java.lang.String r2 = r4.toString()     // Catch:{ RemoteException -> 0x0035 }
            r3.onInitializationFailed(r2)     // Catch:{ RemoteException -> 0x0035 }
            return
        L_0x0035:
            r2 = move-exception
            java.lang.String r3 = ""
            com.google.android.gms.internal.ads.zzbbq.zzc(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcnu.zza(com.google.android.gms.internal.ads.zzdpa, com.google.android.gms.internal.ads.zzais, java.util.List, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzapo() throws Exception {
        this.zzgih.set(true);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zzbcg zzbcg, String str, long j) {
        synchronized (obj) {
            if (!zzbcg.isDone()) {
                zza(str, false, "Timeout.", (int) (zzp.zzky().elapsedRealtime() - j));
                this.zzgik.zzs(str, "timeout");
                zzbcg.set(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzapp() {
        synchronized (this) {
            if (!this.zzgif) {
                zza("com.google.android.gms.ads.MobileAds", false, "Timeout.", (int) (zzp.zzky().elapsedRealtime() - this.zzgig));
                this.zzgih.setException(new Exception());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbcg zzbcg) {
        this.executor.execute(new zzcoc(this, zzbcg));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzapq() {
        this.zzgik.zzapj();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzait zzait) {
        try {
            zzait.zze(zzapm());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
