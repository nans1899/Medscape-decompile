package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwj {
    /* access modifiers changed from: private */
    public final Clock zzbqa;
    /* access modifiers changed from: private */
    public final zzctd zzfst;
    /* access modifiers changed from: private */
    public final zzcwl zzgpx;
    private final List<String> zzgpy = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public final boolean zzgpz;

    public zzcwj(Clock clock, zzcwl zzcwl, zzctd zzctd) {
        this.zzbqa = clock;
        this.zzgpx = zzcwl;
        this.zzgpz = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcxn)).booleanValue();
        this.zzfst = zzctd;
    }

    /* access modifiers changed from: package-private */
    public final <T> zzdzc<T> zza(zzdnw zzdnw, zzdnv zzdnv, zzdzc<T> zzdzc) {
        long elapsedRealtime = this.zzbqa.elapsedRealtime();
        String str = zzdnv.zzdjo;
        if (str != null) {
            zzdyq.zza(zzdzc, new zzcwm(this, elapsedRealtime, str, zzdnv, zzdnw), zzbbz.zzeeu);
        }
        return zzdzc;
    }

    /* access modifiers changed from: private */
    public final void zza(String str, int i, long j, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
        sb.append(str);
        sb.append(".");
        sb.append(i);
        sb.append(".");
        sb.append(j);
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 1 + String.valueOf(str2).length());
            sb3.append(sb2);
            sb3.append(".");
            sb3.append(str2);
            sb2 = sb3.toString();
        }
        this.zzgpy.add(sb2);
    }

    public final String zzaqm() {
        return TextUtils.join("_", this.zzgpy);
    }
}
