package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbe extends zzbqo {
    private final zzccm zzfpr;
    private boolean zzfps = false;
    private final WeakReference<zzbgj> zzfwr;
    private final zzbzp zzfws;
    private final zzbrh zzfwt;
    private final zzdtn zzfwu;
    private final zzbuk zzfwv;
    private final Context zzvr;

    zzcbe(zzbqn zzbqn, Context context, @Nullable zzbgj zzbgj, zzbzp zzbzp, zzccm zzccm, zzbrh zzbrh, zzdtn zzdtn, zzbuk zzbuk) {
        super(zzbqn);
        this.zzvr = context;
        this.zzfwr = new WeakReference<>(zzbgj);
        this.zzfws = zzbzp;
        this.zzfpr = zzccm;
        this.zzfwt = zzbrh;
        this.zzfwu = zzdtn;
        this.zzfwv = zzbuk;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzbi(boolean r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcnz
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0049
            com.google.android.gms.ads.internal.zzp.zzkr()
            android.content.Context r0 = r4.zzvr
            boolean r0 = com.google.android.gms.internal.ads.zzayu.zzav(r0)
            if (r0 == 0) goto L_0x0049
            java.lang.String r0 = "Interstitials that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit  https://googlemobileadssdk.page.link/admob-interstitial-policies"
            com.google.android.gms.internal.ads.zzayp.zzfe(r0)
            com.google.android.gms.internal.ads.zzbuk r0 = r4.zzfwv
            r0.zzajw()
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.zzabb.zzcoa
            com.google.android.gms.internal.ads.zzaax r3 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r3.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x004f
            com.google.android.gms.internal.ads.zzdtn r0 = r4.zzfwu
            com.google.android.gms.internal.ads.zzdog r3 = r4.zzfmw
            com.google.android.gms.internal.ads.zzdoe r3 = r3.zzhew
            com.google.android.gms.internal.ads.zzdnw r3 = r3.zzeou
            java.lang.String r3 = r3.zzdtb
            r0.zzhc(r3)
            goto L_0x004f
        L_0x0049:
            boolean r0 = r4.zzfps
            if (r0 != 0) goto L_0x004f
            r0 = 1
            goto L_0x0050
        L_0x004f:
            r0 = 0
        L_0x0050:
            if (r0 != 0) goto L_0x0053
            return r2
        L_0x0053:
            com.google.android.gms.internal.ads.zzbzp r0 = r4.zzfws
            r0.zzajl()
            com.google.android.gms.internal.ads.zzccm r0 = r4.zzfpr     // Catch:{ zzccl -> 0x0067 }
            android.content.Context r3 = r4.zzvr     // Catch:{ zzccl -> 0x0067 }
            r0.zza(r5, r3)     // Catch:{ zzccl -> 0x0067 }
            com.google.android.gms.internal.ads.zzbzp r5 = r4.zzfws     // Catch:{ zzccl -> 0x0067 }
            r5.zzajj()     // Catch:{ zzccl -> 0x0067 }
            r4.zzfps = r1
            return r1
        L_0x0067:
            r5 = move-exception
            com.google.android.gms.internal.ads.zzbuk r0 = r4.zzfwv
            r0.zza(r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcbe.zzbi(boolean):boolean");
    }

    public final boolean isClosed() {
        return this.zzfwt.isClosed();
    }

    public final void finalize() throws Throwable {
        try {
            zzbgj zzbgj = (zzbgj) this.zzfwr.get();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxj)).booleanValue()) {
                if (!this.zzfps && zzbgj != null) {
                    zzdzb zzdzb = zzbbz.zzeet;
                    zzbgj.getClass();
                    zzdzb.execute(zzcbd.zzh(zzbgj));
                }
            } else if (zzbgj != null) {
                zzbgj.destroy();
            }
        } finally {
            super.finalize();
        }
    }
}
