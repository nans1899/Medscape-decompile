package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.collection.ArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqd implements zzbuz, zzbvs {
    private final zzbbx zzbpe;
    private final zzbgj zzdgy;
    private final zzdnv zzeot;
    private IObjectWrapper zzfrd;
    private boolean zzfre;
    private final Context zzvr;

    public zzbqd(Context context, zzbgj zzbgj, zzdnv zzdnv, zzbbx zzbbx) {
        this.zzvr = context;
        this.zzdgy = zzbgj;
        this.zzeot = zzdnv;
        this.zzbpe = zzbbx;
    }

    public final synchronized void onAdLoaded() {
        if (!this.zzfre) {
            zzait();
        }
    }

    public final synchronized void onAdImpression() {
        if (!this.zzfre) {
            zzait();
        }
        if (!(!this.zzeot.zzdtm || this.zzfrd == null || this.zzdgy == null)) {
            this.zzdgy.zza("onSdkImpression", (Map<String, ?>) new ArrayMap());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0080, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zzait() {
        /*
            r10 = this;
            monitor-enter(r10)
            com.google.android.gms.internal.ads.zzdnv r0 = r10.zzeot     // Catch:{ all -> 0x0081 }
            boolean r0 = r0.zzdtm     // Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r10)
            return
        L_0x0009:
            com.google.android.gms.internal.ads.zzbgj r0 = r10.zzdgy     // Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r10)
            return
        L_0x000f:
            com.google.android.gms.internal.ads.zzaqv r0 = com.google.android.gms.ads.internal.zzp.zzlg()     // Catch:{ all -> 0x0081 }
            android.content.Context r1 = r10.zzvr     // Catch:{ all -> 0x0081 }
            boolean r0 = r0.zzp(r1)     // Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x001d
            monitor-exit(r10)
            return
        L_0x001d:
            com.google.android.gms.internal.ads.zzbbx r0 = r10.zzbpe     // Catch:{ all -> 0x0081 }
            int r0 = r0.zzeel     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzbbx r1 = r10.zzbpe     // Catch:{ all -> 0x0081 }
            int r1 = r1.zzeem     // Catch:{ all -> 0x0081 }
            r2 = 23
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r3.<init>(r2)     // Catch:{ all -> 0x0081 }
            r3.append(r0)     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = "."
            r3.append(r0)     // Catch:{ all -> 0x0081 }
            r3.append(r1)     // Catch:{ all -> 0x0081 }
            java.lang.String r5 = r3.toString()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzdnv r0 = r10.zzeot     // Catch:{ all -> 0x0081 }
            com.google.android.gms.ads.nonagon.transaction.omid.OmidSettings r0 = r0.zzhec     // Catch:{ all -> 0x0081 }
            java.lang.String r9 = r0.getVideoEventsOwner()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzaqv r4 = com.google.android.gms.ads.internal.zzp.zzlg()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzbgj r0 = r10.zzdgy     // Catch:{ all -> 0x0081 }
            android.webkit.WebView r6 = r0.getWebView()     // Catch:{ all -> 0x0081 }
            java.lang.String r7 = ""
            java.lang.String r8 = "javascript"
            com.google.android.gms.dynamic.IObjectWrapper r0 = r4.zza(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0081 }
            r10.zzfrd = r0     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzbgj r0 = r10.zzdgy     // Catch:{ all -> 0x0081 }
            android.view.View r0 = r0.getView()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.dynamic.IObjectWrapper r1 = r10.zzfrd     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x007f
            if (r0 == 0) goto L_0x007f
            com.google.android.gms.internal.ads.zzaqv r1 = com.google.android.gms.ads.internal.zzp.zzlg()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.dynamic.IObjectWrapper r2 = r10.zzfrd     // Catch:{ all -> 0x0081 }
            r1.zza(r2, r0)     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzbgj r0 = r10.zzdgy     // Catch:{ all -> 0x0081 }
            com.google.android.gms.dynamic.IObjectWrapper r1 = r10.zzfrd     // Catch:{ all -> 0x0081 }
            r0.zzap(r1)     // Catch:{ all -> 0x0081 }
            com.google.android.gms.internal.ads.zzaqv r0 = com.google.android.gms.ads.internal.zzp.zzlg()     // Catch:{ all -> 0x0081 }
            com.google.android.gms.dynamic.IObjectWrapper r1 = r10.zzfrd     // Catch:{ all -> 0x0081 }
            r0.zzab(r1)     // Catch:{ all -> 0x0081 }
            r0 = 1
            r10.zzfre = r0     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r10)
            return
        L_0x0081:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqd.zzait():void");
    }
}
