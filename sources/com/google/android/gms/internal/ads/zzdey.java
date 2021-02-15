package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdey implements zzdgx<zzdez> {
    private final PackageInfo zzdqs;
    private final zzayr zzdzr;
    private final zzdok zzfsk;
    private final zzdzb zzgcz;

    public zzdey(zzdzb zzdzb, zzdok zzdok, PackageInfo packageInfo, zzayr zzayr) {
        this.zzgcz = zzdzb;
        this.zzfsk = zzdok;
        this.zzdqs = packageInfo;
        this.zzdzr = zzayr;
    }

    public final zzdzc<zzdez> zzarj() {
        return this.zzgcz.zze(new zzdfb(this));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f9, code lost:
        if (r9 == 3) goto L_0x0120;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(java.util.ArrayList r9, android.os.Bundle r10) {
        /*
            r8 = this;
            java.lang.String r0 = "native_version"
            r1 = 3
            r10.putInt(r0, r1)
            java.lang.String r0 = "native_templates"
            r10.putStringArrayList(r0, r9)
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            java.util.ArrayList<java.lang.String> r9 = r9.zzhfd
            java.lang.String r0 = "native_custom_templates"
            r10.putStringArrayList(r0, r9)
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r9 = com.google.android.gms.internal.ads.zzabb.zzcsb
            com.google.android.gms.internal.ads.zzaax r0 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r9 = r0.zzd(r9)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            java.lang.String r0 = "landscape"
            java.lang.String r2 = "portrait"
            java.lang.String r3 = "any"
            java.lang.String r4 = "unknown"
            r5 = 2
            r6 = 1
            if (r9 == 0) goto L_0x0061
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzadu r9 = r9.zzdly
            int r9 = r9.versionCode
            if (r9 <= r1) goto L_0x0061
            java.lang.String r9 = "enable_native_media_orientation"
            r10.putBoolean(r9, r6)
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzadu r9 = r9.zzdly
            int r9 = r9.zzbno
            if (r9 == r6) goto L_0x0055
            if (r9 == r5) goto L_0x0053
            if (r9 == r1) goto L_0x0051
            r7 = 4
            if (r9 == r7) goto L_0x004e
            r9 = r4
            goto L_0x0056
        L_0x004e:
            java.lang.String r9 = "square"
            goto L_0x0056
        L_0x0051:
            r9 = r2
            goto L_0x0056
        L_0x0053:
            r9 = r0
            goto L_0x0056
        L_0x0055:
            r9 = r3
        L_0x0056:
            boolean r7 = r4.equals(r9)
            if (r7 != 0) goto L_0x0061
            java.lang.String r7 = "native_media_orientation"
            r10.putString(r7, r9)
        L_0x0061:
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzadu r9 = r9.zzdly
            int r9 = r9.zzbnn
            if (r9 == 0) goto L_0x0071
            if (r9 == r6) goto L_0x006f
            if (r9 == r5) goto L_0x0072
            r0 = r4
            goto L_0x0072
        L_0x006f:
            r0 = r2
            goto L_0x0072
        L_0x0071:
            r0 = r3
        L_0x0072:
            boolean r9 = r4.equals(r0)
            if (r9 != 0) goto L_0x007d
            java.lang.String r9 = "native_image_orientation"
            r10.putString(r9, r0)
        L_0x007d:
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzadu r9 = r9.zzdly
            boolean r9 = r9.zzbnp
            java.lang.String r0 = "native_multiple_images"
            r10.putBoolean(r0, r9)
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzadu r9 = r9.zzdly
            boolean r9 = r9.zzbns
            java.lang.String r0 = "use_custom_mute"
            r10.putBoolean(r0, r9)
            android.content.pm.PackageInfo r9 = r8.zzdqs
            if (r9 != 0) goto L_0x0099
            r9 = 0
            goto L_0x009b
        L_0x0099:
            int r9 = r9.versionCode
        L_0x009b:
            com.google.android.gms.internal.ads.zzayr r0 = r8.zzdzr
            int r0 = r0.zzxj()
            if (r9 <= r0) goto L_0x00ad
            com.google.android.gms.internal.ads.zzayr r0 = r8.zzdzr
            r0.zzxp()
            com.google.android.gms.internal.ads.zzayr r0 = r8.zzdzr
            r0.zzdf(r9)
        L_0x00ad:
            com.google.android.gms.internal.ads.zzayr r9 = r8.zzdzr
            org.json.JSONObject r9 = r9.zzxo()
            if (r9 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.zzdok r0 = r8.zzfsk
            java.lang.String r0 = r0.zzhfb
            org.json.JSONArray r9 = r9.optJSONArray(r0)
            if (r9 == 0) goto L_0x00c4
            java.lang.String r9 = r9.toString()
            goto L_0x00c5
        L_0x00c4:
            r9 = 0
        L_0x00c5:
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L_0x00d0
            java.lang.String r0 = "native_advanced_settings"
            r10.putString(r0, r9)
        L_0x00d0:
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            int r9 = r9.zzgtu
            if (r9 <= r6) goto L_0x00df
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            int r9 = r9.zzgtu
            java.lang.String r0 = "max_num_ads"
            r10.putInt(r0, r9)
        L_0x00df:
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzajc r9 = r9.zzdsi
            if (r9 == 0) goto L_0x0132
            java.lang.String r0 = r9.zzdgg
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0126
            int r0 = r9.versionCode
            java.lang.String r2 = "p"
            java.lang.String r3 = "l"
            if (r0 < r5) goto L_0x00fc
            int r9 = r9.zzbno
            if (r9 == r5) goto L_0x011f
            if (r9 == r1) goto L_0x0120
            goto L_0x011f
        L_0x00fc:
            int r0 = r9.zzdgf
            if (r0 == r6) goto L_0x011f
            if (r0 == r5) goto L_0x0120
            int r9 = r9.zzdgf
            r0 = 52
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Instream ad video aspect ratio "
            r1.append(r0)
            r1.append(r9)
            java.lang.String r9 = " is wrong."
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            com.google.android.gms.internal.ads.zzbbq.zzfc(r9)
        L_0x011f:
            r2 = r3
        L_0x0120:
            java.lang.String r9 = "ia_var"
            r10.putString(r9, r2)
            goto L_0x012d
        L_0x0126:
            java.lang.String r9 = r9.zzdgg
            java.lang.String r0 = "ad_tag"
            r10.putString(r0, r9)
        L_0x012d:
            java.lang.String r9 = "instr"
            r10.putBoolean(r9, r6)
        L_0x0132:
            com.google.android.gms.internal.ads.zzdok r9 = r8.zzfsk
            com.google.android.gms.internal.ads.zzafy r9 = r9.zzati()
            if (r9 == 0) goto L_0x013f
            java.lang.String r9 = "has_delayed_banner_listener"
            r10.putBoolean(r9, r6)
        L_0x013f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdey.zza(java.util.ArrayList, android.os.Bundle):void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdez zzars() throws Exception {
        ArrayList<String> arrayList = this.zzfsk.zzhfc;
        if (arrayList == null) {
            return zzdfa.zzgwy;
        }
        if (arrayList.isEmpty()) {
            return zzdfd.zzgwy;
        }
        return new zzdfc(this, arrayList);
    }
}
