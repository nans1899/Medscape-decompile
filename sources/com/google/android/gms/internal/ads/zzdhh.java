package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhh implements zzdgx<zzdhe> {
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzdhh(zzdzb zzdzb, Context context) {
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzdhe> zzarj() {
        return this.zzgcz.zze(new zzdhg(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzdhe zzasc() throws java.lang.Exception {
        /*
            r8 = this;
            android.content.Context r0 = r8.zzvr
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r2 = r0.getNetworkOperator()
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastR()
            r3 = 0
            if (r1 == 0) goto L_0x0029
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zzabb.zzcyr
            com.google.android.gms.internal.ads.zzaax r4 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r1 = r4.zzd(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0029
            r4 = 0
            goto L_0x002e
        L_0x0029:
            int r1 = r0.getNetworkType()
            r4 = r1
        L_0x002e:
            int r5 = r0.getPhoneType()
            r0 = -2
            com.google.android.gms.ads.internal.zzp.zzkr()
            android.content.Context r1 = r8.zzvr
            java.lang.String r6 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = com.google.android.gms.internal.ads.zzayu.zzr(r1, r6)
            r6 = -1
            if (r1 == 0) goto L_0x0067
            android.content.Context r0 = r8.zzvr
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            android.net.NetworkInfo r1 = r0.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x005e
            int r6 = r1.getType()
            android.net.NetworkInfo$DetailedState r1 = r1.getDetailedState()
            int r1 = r1.ordinal()
            goto L_0x005f
        L_0x005e:
            r1 = -1
        L_0x005f:
            boolean r0 = r0.isActiveNetworkMetered()
            r7 = r1
            r3 = r6
            r6 = r0
            goto L_0x006a
        L_0x0067:
            r3 = -2
            r6 = 0
            r7 = -1
        L_0x006a:
            com.google.android.gms.internal.ads.zzdhe r0 = new com.google.android.gms.internal.ads.zzdhe
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdhh.zzasc():com.google.android.gms.internal.ads.zzdhe");
    }
}
