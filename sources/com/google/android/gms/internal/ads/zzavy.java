package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzavy implements zzawl {
    /* access modifiers changed from: private */
    public static List<Future<Void>> zzdwk = Collections.synchronizedList(new ArrayList());
    private final Object lock = new Object();
    private final zzawg zzdth;
    private final zzent.zzb.zza zzdwl;
    private final LinkedHashMap<String, zzent.zzb.zzh.C0019zzb> zzdwm;
    private final List<String> zzdwn = new ArrayList();
    private final List<String> zzdwo = new ArrayList();
    private final zzawn zzdwp;
    private boolean zzdwq;
    private final zzawm zzdwr;
    private HashSet<String> zzdws = new HashSet<>();
    private boolean zzdwt = false;
    private boolean zzdwu = false;
    private boolean zzdwv = false;
    private final Context zzvr;

    public zzavy(Context context, zzbbx zzbbx, zzawg zzawg, String str, zzawn zzawn) {
        Preconditions.checkNotNull(zzawg, "SafeBrowsing config is not present.");
        this.zzvr = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdwm = new LinkedHashMap<>();
        this.zzdwp = zzawn;
        this.zzdth = zzawg;
        for (String lowerCase : zzawg.zzdxg) {
            this.zzdws.add(lowerCase.toLowerCase(Locale.ENGLISH));
        }
        this.zzdws.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzent.zzb.zza zzbjm = zzent.zzb.zzbjm();
        zzbjm.zza(zzent.zzb.zzg.OCTAGON_AD);
        zzbjm.zzhy(str);
        zzbjm.zzhz(str);
        zzent.zzb.C0015zzb.zza zzbjo = zzent.zzb.C0015zzb.zzbjo();
        if (this.zzdth.zzdxc != null) {
            zzbjo.zzic(this.zzdth.zzdxc);
        }
        zzbjm.zza((zzent.zzb.C0015zzb) ((zzejz) zzbjo.zzbgt()));
        zzent.zzb.zzi.zza zzbx = zzent.zzb.zzi.zzbkc().zzbx(Wrappers.packageManager(this.zzvr).isCallerInstantApp());
        if (zzbbx.zzbre != null) {
            zzbx.zzij(zzbbx.zzbre);
        }
        long apkVersion = (long) GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzvr);
        if (apkVersion > 0) {
            zzbx.zzfu(apkVersion);
        }
        zzbjm.zza((zzent.zzb.zzi) ((zzejz) zzbx.zzbgt()));
        this.zzdwl = zzbjm;
        this.zzdwr = new zzawm(this.zzvr, this.zzdth.zzdxj, this);
    }

    static final /* synthetic */ Void zzee(String str) {
        return null;
    }

    public final zzawg zzvq() {
        return this.zzdth;
    }

    public final void zzea(String str) {
        synchronized (this.lock) {
            if (str == null) {
                this.zzdwl.zzbjk();
            } else {
                this.zzdwl.zzia(str);
            }
        }
    }

    public final boolean zzvr() {
        return PlatformVersion.isAtLeastKitKat() && this.zzdth.zzdxe && !this.zzdwu;
    }

    public final void zzl(View view) {
        if (this.zzdth.zzdxe && !this.zzdwu) {
            zzp.zzkr();
            Bitmap zzn = zzayu.zzn(view);
            if (zzn == null) {
                zzawi.zzef("Failed to capture the webview bitmap.");
                return;
            }
            this.zzdwu = true;
            zzayu.zzc(new zzawb(this, zzn));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, int r9) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.lock
            monitor-enter(r0)
            r1 = 3
            if (r9 != r1) goto L_0x0009
            r2 = 1
            r6.zzdwv = r2     // Catch:{ all -> 0x00bd }
        L_0x0009:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb> r2 = r6.zzdwm     // Catch:{ all -> 0x00bd }
            boolean r2 = r2.containsKey(r7)     // Catch:{ all -> 0x00bd }
            if (r2 == 0) goto L_0x0024
            if (r9 != r1) goto L_0x0022
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb> r8 = r6.zzdwm     // Catch:{ all -> 0x00bd }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb r7 = (com.google.android.gms.internal.ads.zzent.zzb.zzh.C0019zzb) r7     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzh$zza r8 = com.google.android.gms.internal.ads.zzent.zzb.zzh.zza.zzhw(r9)     // Catch:{ all -> 0x00bd }
            r7.zzb((com.google.android.gms.internal.ads.zzent.zzb.zzh.zza) r8)     // Catch:{ all -> 0x00bd }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return
        L_0x0024:
            com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb r1 = com.google.android.gms.internal.ads.zzent.zzb.zzh.zzbka()     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzh$zza r9 = com.google.android.gms.internal.ads.zzent.zzb.zzh.zza.zzhw(r9)     // Catch:{ all -> 0x00bd }
            if (r9 == 0) goto L_0x0031
            r1.zzb((com.google.android.gms.internal.ads.zzent.zzb.zzh.zza) r9)     // Catch:{ all -> 0x00bd }
        L_0x0031:
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb> r9 = r6.zzdwm     // Catch:{ all -> 0x00bd }
            int r9 = r9.size()     // Catch:{ all -> 0x00bd }
            r1.zzhx(r9)     // Catch:{ all -> 0x00bd }
            r1.zzih(r7)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzd$zza r9 = com.google.android.gms.internal.ads.zzent.zzb.zzd.zzbjs()     // Catch:{ all -> 0x00bd }
            java.util.HashSet<java.lang.String> r2 = r6.zzdws     // Catch:{ all -> 0x00bd }
            int r2 = r2.size()     // Catch:{ all -> 0x00bd }
            if (r2 <= 0) goto L_0x00ab
            if (r8 == 0) goto L_0x00ab
            java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x00bd }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00bd }
        L_0x0053:
            boolean r2 = r8.hasNext()     // Catch:{ all -> 0x00bd }
            if (r2 == 0) goto L_0x00ab
            java.lang.Object r2 = r8.next()     // Catch:{ all -> 0x00bd }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00bd }
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x00bd }
            if (r3 == 0) goto L_0x006c
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x00bd }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00bd }
            goto L_0x006e
        L_0x006c:
            java.lang.String r3 = ""
        L_0x006e:
            java.lang.Object r4 = r2.getValue()     // Catch:{ all -> 0x00bd }
            if (r4 == 0) goto L_0x007b
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00bd }
            goto L_0x007d
        L_0x007b:
            java.lang.String r2 = ""
        L_0x007d:
            java.util.Locale r4 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = r3.toLowerCase(r4)     // Catch:{ all -> 0x00bd }
            java.util.HashSet<java.lang.String> r5 = r6.zzdws     // Catch:{ all -> 0x00bd }
            boolean r4 = r5.contains(r4)     // Catch:{ all -> 0x00bd }
            if (r4 == 0) goto L_0x0053
            com.google.android.gms.internal.ads.zzent$zzb$zzc$zza r4 = com.google.android.gms.internal.ads.zzent.zzb.zzc.zzbjq()     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzeip r3 = com.google.android.gms.internal.ads.zzeip.zzhu(r3)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzc$zza r3 = r4.zzan(r3)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzeip r2 = com.google.android.gms.internal.ads.zzeip.zzhu(r2)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzc$zza r2 = r3.zzao(r2)     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzelj r2 = r2.zzbgt()     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzejz r2 = (com.google.android.gms.internal.ads.zzejz) r2     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzc r2 = (com.google.android.gms.internal.ads.zzent.zzb.zzc) r2     // Catch:{ all -> 0x00bd }
            r9.zza(r2)     // Catch:{ all -> 0x00bd }
            goto L_0x0053
        L_0x00ab:
            com.google.android.gms.internal.ads.zzelj r8 = r9.zzbgt()     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzejz r8 = (com.google.android.gms.internal.ads.zzejz) r8     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.ads.zzent$zzb$zzd r8 = (com.google.android.gms.internal.ads.zzent.zzb.zzd) r8     // Catch:{ all -> 0x00bd }
            r1.zzb((com.google.android.gms.internal.ads.zzent.zzb.zzd) r8)     // Catch:{ all -> 0x00bd }
            java.util.LinkedHashMap<java.lang.String, com.google.android.gms.internal.ads.zzent$zzb$zzh$zzb> r8 = r6.zzdwm     // Catch:{ all -> 0x00bd }
            r8.put(r7, r1)     // Catch:{ all -> 0x00bd }
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return
        L_0x00bd:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavy.zza(java.lang.String, java.util.Map, int):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzeb(String str) {
        synchronized (this.lock) {
            this.zzdwn.add(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzec(String str) {
        synchronized (this.lock) {
            this.zzdwo.add(str);
        }
    }

    public final String[] zza(String[] strArr) {
        return (String[]) this.zzdwr.zzb(strArr).toArray(new String[0]);
    }

    public final void zzvs() {
        this.zzdwt = true;
    }

    private final zzent.zzb.zzh.C0019zzb zzed(String str) {
        zzent.zzb.zzh.C0019zzb zzb;
        synchronized (this.lock) {
            zzb = this.zzdwm.get(str);
        }
        return zzb;
    }

    public final void zzvt() {
        synchronized (this.lock) {
            zzdzc<O> zzb = zzdyq.zzb(this.zzdwp.zza(this.zzvr, this.zzdwm.keySet()), new zzawa(this), (Executor) zzbbz.zzeeu);
            zzdzc<O> zza = zzdyq.zza(zzb, 10, TimeUnit.SECONDS, zzbbz.zzees);
            zzdyq.zza(zzb, new zzawf(this, zza), zzbbz.zzeeu);
            zzdwk.add(zza);
        }
    }

    private final zzdzc<Void> zzvu() {
        zzdzc<Void> zzb;
        if (!((this.zzdwq && this.zzdth.zzdxi) || (this.zzdwv && this.zzdth.zzdxh) || (!this.zzdwq && this.zzdth.zzdxf))) {
            return zzdyq.zzaf(null);
        }
        synchronized (this.lock) {
            for (zzent.zzb.zzh.C0019zzb zzbgt : this.zzdwm.values()) {
                this.zzdwl.zza((zzent.zzb.zzh) ((zzejz) zzbgt.zzbgt()));
            }
            this.zzdwl.zzm(this.zzdwn);
            this.zzdwl.zzn(this.zzdwo);
            if (zzawi.isEnabled()) {
                String url = this.zzdwl.getUrl();
                String zzbjj = this.zzdwl.zzbjj();
                StringBuilder sb = new StringBuilder(String.valueOf(url).length() + 53 + String.valueOf(zzbjj).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(url);
                sb.append("\n  clickUrl: ");
                sb.append(zzbjj);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzent.zzb.zzh next : this.zzdwl.zzbji()) {
                    sb2.append("    [");
                    sb2.append(next.zzbjz());
                    sb2.append("] ");
                    sb2.append(next.getUrl());
                }
                zzawi.zzef(sb2.toString());
            }
            zzdzc<String> zza = new zzbag(this.zzvr).zza(1, this.zzdth.zzdxd, (Map<String, String>) null, ((zzent.zzb) ((zzejz) this.zzdwl.zzbgt())).toByteArray());
            if (zzawi.isEnabled()) {
                zza.addListener(zzawd.zzdxa, zzbbz.zzeep);
            }
            zzb = zzdyq.zzb(zza, zzawc.zzdwz, (Executor) zzbbz.zzeeu);
        }
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzi(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.lock) {
                            int length = optJSONArray.length();
                            zzent.zzb.zzh.C0019zzb zzed = zzed(str);
                            if (zzed == null) {
                                String valueOf = String.valueOf(str);
                                zzawi.zzef(valueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(valueOf) : new String("Cannot find the corresponding resource object for "));
                            } else {
                                boolean z = false;
                                for (int i = 0; i < length; i++) {
                                    zzed.zzii(optJSONArray.getJSONObject(i).getString("threat_type"));
                                }
                                boolean z2 = this.zzdwq;
                                if (length > 0) {
                                    z = true;
                                }
                                this.zzdwq = z | z2;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (zzadc.zzdcj.get().booleanValue()) {
                    zzayp.zzb("Failed to get SafeBrowsing metadata", e);
                }
                return zzdyq.immediateFailedFuture(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzdwq) {
            synchronized (this.lock) {
                this.zzdwl.zza(zzent.zzb.zzg.OCTAGON_AD_SB_MATCH);
            }
        }
        return zzvu();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bitmap bitmap) {
        zzeiy zzbei = zzeip.zzbei();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, zzbei);
        synchronized (this.lock) {
            this.zzdwl.zza((zzent.zzb.zzf) ((zzejz) zzent.zzb.zzf.zzbjx().zzaq(zzbei.zzbdw()).zzie("image/png").zza(zzent.zzb.zzf.C0018zzb.TYPE_CREATIVE).zzbgt()));
        }
    }
}
