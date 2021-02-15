package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzdbd {
    private String zzgup;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static class zza {
        /* access modifiers changed from: private */
        public String zzgup;

        public final zza zzgs(String str) {
            this.zzgup = str;
            return this;
        }
    }

    private zzdbd(zza zza2) {
        this.zzgup = zza2.zzgup;
    }

    public final Set<String> zzarg() {
        HashSet hashSet = new HashSet();
        hashSet.add(this.zzgup.toLowerCase(Locale.ROOT));
        return hashSet;
    }

    public final String zzarh() {
        return this.zzgup.toLowerCase(Locale.ROOT);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzua.zza.C0021zza zzari() {
        /*
            r5 = this;
            java.lang.String r0 = r5.zzgup
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -1999289321: goto L_0x002b;
                case -1372958932: goto L_0x0021;
                case 543046670: goto L_0x0017;
                case 1951953708: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0035
        L_0x000d:
            java.lang.String r1 = "BANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 0
            goto L_0x0036
        L_0x0017:
            java.lang.String r1 = "REWARDED"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 3
            goto L_0x0036
        L_0x0021:
            java.lang.String r1 = "INTERSTITIAL"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 1
            goto L_0x0036
        L_0x002b:
            java.lang.String r1 = "NATIVE"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0035
            r0 = 2
            goto L_0x0036
        L_0x0035:
            r0 = -1
        L_0x0036:
            if (r0 == 0) goto L_0x004a
            if (r0 == r4) goto L_0x0047
            if (r0 == r3) goto L_0x0044
            if (r0 == r2) goto L_0x0041
            com.google.android.gms.internal.ads.zzua$zza$zza r0 = com.google.android.gms.internal.ads.zzua.zza.C0021zza.AD_INITIATER_UNSPECIFIED
            return r0
        L_0x0041:
            com.google.android.gms.internal.ads.zzua$zza$zza r0 = com.google.android.gms.internal.ads.zzua.zza.C0021zza.REWARD_BASED_VIDEO_AD
            return r0
        L_0x0044:
            com.google.android.gms.internal.ads.zzua$zza$zza r0 = com.google.android.gms.internal.ads.zzua.zza.C0021zza.AD_LOADER
            return r0
        L_0x0047:
            com.google.android.gms.internal.ads.zzua$zza$zza r0 = com.google.android.gms.internal.ads.zzua.zza.C0021zza.INTERSTITIAL
            return r0
        L_0x004a:
            com.google.android.gms.internal.ads.zzua$zza$zza r0 = com.google.android.gms.internal.ads.zzua.zza.C0021zza.BANNER
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdbd.zzari():com.google.android.gms.internal.ads.zzua$zza$zza");
    }
}
