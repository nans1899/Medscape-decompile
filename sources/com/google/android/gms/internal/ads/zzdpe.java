package com.google.android.gms.internal.ads;

import android.os.IBinder;
import com.google.android.gms.ads.MobileAds;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpe {
    public static zzva zza(zzdpg zzdpg, zzva zzva) {
        if (zzdpg.equals(zzdpg.MEDIATION_SHOW_ERROR)) {
            if (((Integer) zzwm.zzpx().zzd(zzabb.zzcyn)).intValue() > 0) {
                return zzva;
            }
        }
        return zza(zzdpg, (String) null, zzva);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzva zza(com.google.android.gms.internal.ads.zzdpg r6, java.lang.String r7, com.google.android.gms.internal.ads.zzva r8) {
        /*
            if (r7 != 0) goto L_0x0043
            int[] r7 = com.google.android.gms.internal.ads.zzdpd.zzhfv
            int r0 = r6.ordinal()
            r7 = r7[r0]
            java.lang.String r0 = "No fill."
            switch(r7) {
                case 1: goto L_0x0041;
                case 2: goto L_0x003e;
                case 3: goto L_0x003b;
                case 4: goto L_0x0038;
                case 5: goto L_0x0036;
                case 6: goto L_0x0033;
                case 7: goto L_0x0030;
                case 8: goto L_0x002d;
                case 9: goto L_0x002a;
                case 10: goto L_0x0027;
                case 11: goto L_0x000f;
                case 12: goto L_0x0012;
                default: goto L_0x000f;
            }
        L_0x000f:
            java.lang.String r7 = "Internal error."
            goto L_0x0043
        L_0x0012:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r7 = com.google.android.gms.internal.ads.zzabb.zzcyq
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r7 = r1.zzd(r7)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r7 > 0) goto L_0x0036
            java.lang.String r7 = "The mediation adapter did not return an ad."
            goto L_0x0043
        L_0x0027:
            java.lang.String r7 = "The ad can not be shown when app is not in foreground."
            goto L_0x0043
        L_0x002a:
            java.lang.String r7 = "The ad has already been shown."
            goto L_0x0043
        L_0x002d:
            java.lang.String r7 = "The ad is not ready."
            goto L_0x0043
        L_0x0030:
            java.lang.String r7 = "A mediation adapter failed to show the ad."
            goto L_0x0043
        L_0x0033:
            java.lang.String r7 = "App ID missing."
            goto L_0x0043
        L_0x0036:
            r7 = r0
            goto L_0x0043
        L_0x0038:
            java.lang.String r7 = "Network error."
            goto L_0x0043
        L_0x003b:
            java.lang.String r7 = "Invalid request: Invalid ad size."
            goto L_0x0043
        L_0x003e:
            java.lang.String r7 = "Invalid request: Invalid ad unit ID."
            goto L_0x0043
        L_0x0041:
            java.lang.String r7 = "Invalid request."
        L_0x0043:
            r2 = r7
            com.google.android.gms.internal.ads.zzva r7 = new com.google.android.gms.internal.ads.zzva
            int[] r0 = com.google.android.gms.internal.ads.zzdpd.zzhfv
            int r1 = r6.ordinal()
            r0 = r0[r1]
            r1 = 0
            r3 = 2
            r4 = 1
            r5 = 3
            switch(r0) {
                case 1: goto L_0x009d;
                case 2: goto L_0x009d;
                case 3: goto L_0x009d;
                case 4: goto L_0x009b;
                case 5: goto L_0x0099;
                case 6: goto L_0x0094;
                case 7: goto L_0x0091;
                case 8: goto L_0x009b;
                case 9: goto L_0x009d;
                case 10: goto L_0x0099;
                case 11: goto L_0x009e;
                case 12: goto L_0x007a;
                case 13: goto L_0x009e;
                default: goto L_0x0055;
            }
        L_0x0055:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r8 = java.lang.String.valueOf(r6)
            int r8 = r8.length()
            int r8 = r8 + 18
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r8)
            java.lang.String r8 = "Unknown SdkError: "
            r0.append(r8)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r7.<init>(r6)
            throw r7
        L_0x007a:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r6 = com.google.android.gms.internal.ads.zzabb.zzcyq
            com.google.android.gms.internal.ads.zzaax r0 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r6 = r0.zzd(r6)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            if (r6 > 0) goto L_0x0099
            r6 = 9
            r1 = 9
            goto L_0x009e
        L_0x0091:
            r6 = 4
            r1 = 4
            goto L_0x009e
        L_0x0094:
            r6 = 8
            r1 = 8
            goto L_0x009e
        L_0x0099:
            r1 = 3
            goto L_0x009e
        L_0x009b:
            r1 = 2
            goto L_0x009e
        L_0x009d:
            r1 = 1
        L_0x009e:
            r5 = 0
            java.lang.String r3 = "com.google.android.gms.ads"
            r0 = r7
            r4 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdpe.zza(com.google.android.gms.internal.ads.zzdpg, java.lang.String, com.google.android.gms.internal.ads.zzva):com.google.android.gms.internal.ads.zzva");
    }

    public static zzva zzh(Throwable th) {
        if (th instanceof zzcte) {
            zzcte zzcte = (zzcte) th;
            return zza(zzcte.zzapr(), zzcte.zzaqj());
        } else if (th instanceof zzcof) {
            if (th.getMessage() == null) {
                return zza(((zzcof) th).zzapr(), (String) null, (zzva) null);
            }
            return zza(((zzcof) th).zzapr(), th.getMessage(), (zzva) null);
        } else if (!(th instanceof zzbab)) {
            return zza(zzdpg.INTERNAL_ERROR, (String) null, (zzva) null);
        } else {
            zzbab zzbab = (zzbab) th;
            return new zzva(zzbab.getErrorCode(), zzdwf.zzhi(zzbab.getMessage()), MobileAds.ERROR_DOMAIN, (zzva) null, (IBinder) null);
        }
    }

    public static zzva zza(Throwable th, zzctd zzctd) {
        zzva zzh = zzh(th);
        if ((zzh.errorCode == 3 || zzh.errorCode == 0) && zzh.zzcgy != null && !zzh.zzcgy.zzcgx.equals(MobileAds.ERROR_DOMAIN)) {
            zzh.zzcgy = null;
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue() && zzctd != null) {
            zzh.zzcgz = zzctd.zzaqi();
        }
        return zzh;
    }
}
