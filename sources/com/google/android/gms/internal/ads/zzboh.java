package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzboh extends zzbqo {
    private final zzbgj zzdgy;
    private final int zzfpf;
    private final boolean zzfpg;
    private final boolean zzfph;
    private final zzbnn zzfpo;
    private final zzccm zzfpr;
    private boolean zzfps = false;

    zzboh(zzbqn zzbqn, zzbgj zzbgj, int i, boolean z, boolean z2, zzbnn zzbnn, zzccm zzccm) {
        super(zzbqn);
        this.zzdgy = zzbgj;
        this.zzfpf = i;
        this.zzfpg = z;
        this.zzfph = z2;
        this.zzfpo = zzbnn;
        this.zzfpr = zzccm;
    }

    public final void zza(zzsc zzsc) {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.zza(zzsc);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006e A[SYNTHETIC, Splitter:B:16:0x006e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.app.Activity r6, com.google.android.gms.internal.ads.zzsq r7) throws android.os.RemoteException {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcnz
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x005b
            com.google.android.gms.ads.internal.zzp.zzkr()
            boolean r0 = com.google.android.gms.internal.ads.zzayu.zzav(r6)
            if (r0 == 0) goto L_0x005b
            java.lang.String r0 = "Interstitials that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit  https://googlemobileadssdk.page.link/admob-interstitial-policies"
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)
            com.google.android.gms.internal.ads.zzdpg r0 = com.google.android.gms.internal.ads.zzdpg.APP_NOT_FOREGROUND
            r3 = 0
            com.google.android.gms.internal.ads.zzva r0 = com.google.android.gms.internal.ads.zzdpe.zza(r0, r3, r3)
            r7.zzb(r0)
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcoa
            com.google.android.gms.internal.ads.zzaax r3 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r3.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x006a
            com.google.android.gms.internal.ads.zzdtn r0 = new com.google.android.gms.internal.ads.zzdtn
            android.content.Context r3 = r6.getApplicationContext()
            com.google.android.gms.internal.ads.zzbas r4 = com.google.android.gms.ads.internal.zzp.zzlf()
            android.os.Looper r4 = r4.zzyj()
            r0.<init>(r3, r4)
            com.google.android.gms.internal.ads.zzdog r3 = r5.zzfmw
            com.google.android.gms.internal.ads.zzdoe r3 = r3.zzhew
            com.google.android.gms.internal.ads.zzdnw r3 = r3.zzeou
            java.lang.String r3 = r3.zzdtb
            r0.zzhc(r3)
            goto L_0x006a
        L_0x005b:
            boolean r0 = r5.zzfps
            if (r0 == 0) goto L_0x0064
            java.lang.String r0 = "App open interstitial ad is already visible."
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)
        L_0x0064:
            boolean r0 = r5.zzfps
            if (r0 != 0) goto L_0x006a
            r0 = 1
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            if (r0 != 0) goto L_0x006e
            return
        L_0x006e:
            com.google.android.gms.internal.ads.zzccm r0 = r5.zzfpr     // Catch:{ zzccl -> 0x0076 }
            r0.zza(r2, r6)     // Catch:{ zzccl -> 0x0076 }
            r5.zzfps = r1
            return
        L_0x0076:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzva r6 = com.google.android.gms.internal.ads.zzdpe.zzh(r6)
            r7.zzb(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzboh.zza(android.app.Activity, com.google.android.gms.internal.ads.zzsq):void");
    }

    public final int zzaho() {
        return this.zzfpf;
    }

    public final void zzfd(long j) {
        this.zzfpo.zzfd(j);
    }

    public final void destroy() {
        super.destroy();
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            zzbgj.destroy();
        }
    }
}
