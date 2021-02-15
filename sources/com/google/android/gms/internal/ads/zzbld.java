package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbld extends zzxv {
    private final zzbbx zzbpe;
    private final zzawo zzbqq;
    private final zzcku zzfma;
    private final zzctb<zzdpa, zzcut> zzfmb;
    private final zzcza zzfmc;
    private final zzcnu zzfmd;
    private final zzckw zzfme;
    private final Context zzvr;
    private boolean zzzh = false;

    zzbld(Context context, zzbbx zzbbx, zzcku zzcku, zzctb<zzdpa, zzcut> zzctb, zzcza zzcza, zzcnu zzcnu, zzawo zzawo, zzckw zzckw) {
        this.zzvr = context;
        this.zzbpe = zzbbx;
        this.zzfma = zzcku;
        this.zzfmb = zzctb;
        this.zzfmc = zzcza;
        this.zzfmd = zzcnu;
        this.zzbqq = zzawo;
        this.zzfme = zzckw;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void initialize() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzzh     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x000c
            java.lang.String r0 = "Mobile ads is initialized already."
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)     // Catch:{ all -> 0x005d }
            monitor-exit(r3)
            return
        L_0x000c:
            android.content.Context r0 = r3.zzvr     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzabb.initialize(r0)     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzayb r0 = com.google.android.gms.ads.internal.zzp.zzkv()     // Catch:{ all -> 0x005d }
            android.content.Context r1 = r3.zzvr     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzbbx r2 = r3.zzbpe     // Catch:{ all -> 0x005d }
            r0.zzd(r1, r2)     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzsw r0 = com.google.android.gms.ads.internal.zzp.zzkx()     // Catch:{ all -> 0x005d }
            android.content.Context r1 = r3.zzvr     // Catch:{ all -> 0x005d }
            r0.initialize(r1)     // Catch:{ all -> 0x005d }
            r0 = 1
            r3.zzzh = r0     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzcnu r0 = r3.zzfmd     // Catch:{ all -> 0x005d }
            r0.zzapl()     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcqd     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x005d }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x005d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0044
            com.google.android.gms.internal.ads.zzcza r0 = r3.zzfmc     // Catch:{ all -> 0x005d }
            r0.zzaoh()     // Catch:{ all -> 0x005d }
        L_0x0044:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcsr     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x005d }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x005d }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x005d }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x005b
            com.google.android.gms.internal.ads.zzckw r0 = r3.zzfme     // Catch:{ all -> 0x005d }
            r0.zzaoh()     // Catch:{ all -> 0x005d }
        L_0x005b:
            monitor-exit(r3)
            return
        L_0x005d:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbld.initialize():void");
    }

    public final synchronized void setAppVolume(float f) {
        zzp.zzkw().setAppVolume(f);
    }

    public final synchronized float zzqg() {
        return zzp.zzkw().zzqg();
    }

    public final synchronized void setAppMuted(boolean z) {
        zzp.zzkw().setAppMuted(z);
    }

    public final synchronized boolean zzqh() {
        return zzp.zzkw().zzqh();
    }

    public final synchronized void zzch(String str) {
        zzabb.initialize(this.zzvr);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcsq)).booleanValue()) {
                zzp.zzkz().zza(this.zzvr, this.zzbpe, str, (Runnable) null);
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzayp.zzfc("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            zzayp.zzfc("Context is null. Failed to open debug menu.");
            return;
        }
        zzazp zzazp = new zzazp(context);
        zzazp.setAdUnitId(str);
        zzazp.zzae(this.zzbpe.zzbre);
        zzazp.showDialog();
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        String str2;
        zzabb.initialize(this.zzvr);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcss)).booleanValue()) {
            zzp.zzkr();
            str2 = zzayu.zzbd(this.zzvr);
        } else {
            str2 = "";
        }
        if (!TextUtils.isEmpty(str2)) {
            str = str2;
        }
        if (!TextUtils.isEmpty(str)) {
            boolean booleanValue = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcsq)).booleanValue() | ((Boolean) zzwm.zzpx().zzd(zzabb.zzcok)).booleanValue();
            zzblg zzblg = null;
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcok)).booleanValue()) {
                booleanValue = true;
                zzblg = new zzblg(this, (Runnable) ObjectWrapper.unwrap(iObjectWrapper));
            }
            if (booleanValue) {
                zzp.zzkz().zza(this.zzvr, this.zzbpe, str, (Runnable) zzblg);
            }
        }
    }

    public final String getVersionString() {
        return this.zzbpe.zzbre;
    }

    public final void zzci(String str) {
        this.zzfmc.zzgq(str);
    }

    public final void zza(zzanb zzanb) throws RemoteException {
        this.zzfma.zzb(zzanb);
    }

    public final void zza(zzait zzait) throws RemoteException {
        this.zzfmd.zzb(zzait);
    }

    public final List<zzaiq> zzqi() throws RemoteException {
        return this.zzfmd.zzapm();
    }

    public final void zza(zzaae zzaae) throws RemoteException {
        this.zzbqq.zza(this.zzvr, zzaae);
    }

    public final void zzqj() {
        this.zzfmd.disable();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Runnable runnable) {
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map<String, zzana> zzwi = zzp.zzkv().zzwt().zzxk().zzwi();
        if (zzwi != null && !zzwi.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzayp.zzd("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            if (this.zzfma.zzaof()) {
                HashMap hashMap = new HashMap();
                for (zzana zzana : zzwi.values()) {
                    for (zzamx next : zzana.zzdki) {
                        String str = next.zzdjv;
                        for (String next2 : next.zzdjn) {
                            if (!hashMap.containsKey(next2)) {
                                hashMap.put(next2, new ArrayList());
                            }
                            if (str != null) {
                                ((Collection) hashMap.get(next2)).add(str);
                            }
                        }
                    }
                }
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    try {
                        zzctc<zzdpa, zzcut> zzf = this.zzfmb.zzf(str2, jSONObject);
                        if (zzf != null) {
                            zzdpa zzdpa = (zzdpa) zzf.zzdlf;
                            if (!zzdpa.isInitialized()) {
                                if (zzdpa.zztx()) {
                                    zzdpa.zza(this.zzvr, (zzaur) (zzcut) zzf.zzgns, (List<String>) (List) entry.getValue());
                                    String valueOf = String.valueOf(str2);
                                    zzayp.zzef(valueOf.length() != 0 ? "Initialized rewarded video mediation adapter ".concat(valueOf) : new String("Initialized rewarded video mediation adapter "));
                                }
                            }
                        }
                    } catch (zzdos e) {
                        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 56);
                        sb.append("Failed to initialize rewarded video mediation adapter \"");
                        sb.append(str2);
                        sb.append("\"");
                        zzayp.zzd(sb.toString(), e);
                    }
                }
            }
        }
    }
}
