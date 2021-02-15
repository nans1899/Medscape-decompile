package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcne {
    private List<Map<String, String>> zzghv = new ArrayList();
    private boolean zzghw = false;
    private boolean zzghx = false;
    private String zzghy;
    private zzcmz zzghz;

    public zzcne(String str, zzcmz zzcmz) {
        this.zzghy = str;
        this.zzghz = zzcmz;
    }

    public final synchronized void zzgj(String str) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqm)).booleanValue()) {
            Map<String, String> zzapk = zzapk();
            zzapk.put("action", "adapter_init_started");
            zzapk.put("ancn", str);
            this.zzghv.add(zzapk);
        }
    }

    public final synchronized void zzgk(String str) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqm)).booleanValue()) {
            Map<String, String> zzapk = zzapk();
            zzapk.put("action", "adapter_init_finished");
            zzapk.put("ancn", str);
            this.zzghv.add(zzapk);
        }
    }

    public final synchronized void zzs(String str, String str2) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqm)).booleanValue()) {
            Map<String, String> zzapk = zzapk();
            zzapk.put("action", "adapter_init_finished");
            zzapk.put("ancn", str);
            zzapk.put("rqe", str2);
            this.zzghv.add(zzapk);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzapi() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcqm     // Catch:{ all -> 0x002e }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x002e }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x002e }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x002e }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x0015
            monitor-exit(r3)
            return
        L_0x0015:
            boolean r0 = r3.zzghw     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002c
            java.util.Map r0 = r3.zzapk()     // Catch:{ all -> 0x002e }
            java.lang.String r1 = "action"
            java.lang.String r2 = "init_started"
            r0.put(r1, r2)     // Catch:{ all -> 0x002e }
            java.util.List<java.util.Map<java.lang.String, java.lang.String>> r1 = r3.zzghv     // Catch:{ all -> 0x002e }
            r1.add(r0)     // Catch:{ all -> 0x002e }
            r0 = 1
            r3.zzghw = r0     // Catch:{ all -> 0x002e }
        L_0x002c:
            monitor-exit(r3)
            return
        L_0x002e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcne.zzapi():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzapj() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcqm     // Catch:{ all -> 0x0046 }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0046 }
            java.lang.Object r0 = r1.zzd(r0)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0046 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0046 }
            if (r0 != 0) goto L_0x0015
            monitor-exit(r3)
            return
        L_0x0015:
            boolean r0 = r3.zzghx     // Catch:{ all -> 0x0046 }
            if (r0 != 0) goto L_0x0044
            java.util.Map r0 = r3.zzapk()     // Catch:{ all -> 0x0046 }
            java.lang.String r1 = "action"
            java.lang.String r2 = "init_finished"
            r0.put(r1, r2)     // Catch:{ all -> 0x0046 }
            java.util.List<java.util.Map<java.lang.String, java.lang.String>> r1 = r3.zzghv     // Catch:{ all -> 0x0046 }
            r1.add(r0)     // Catch:{ all -> 0x0046 }
            java.util.List<java.util.Map<java.lang.String, java.lang.String>> r0 = r3.zzghv     // Catch:{ all -> 0x0046 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0046 }
        L_0x002f:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0041
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0046 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x0046 }
            com.google.android.gms.internal.ads.zzcmz r2 = r3.zzghz     // Catch:{ all -> 0x0046 }
            r2.zzn(r1)     // Catch:{ all -> 0x0046 }
            goto L_0x002f
        L_0x0041:
            r0 = 1
            r3.zzghx = r0     // Catch:{ all -> 0x0046 }
        L_0x0044:
            monitor-exit(r3)
            return
        L_0x0046:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcne.zzapj():void");
    }

    private final Map<String, String> zzapk() {
        Map<String, String> zzapg = this.zzghz.zzapg();
        zzapg.put("tms", Long.toString(zzp.zzky().elapsedRealtime(), 10));
        zzapg.put("tid", this.zzghy);
        return zzapg;
    }
}
