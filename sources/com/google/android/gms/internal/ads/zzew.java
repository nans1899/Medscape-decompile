package com.google.android.gms.internal.ads;

import android.view.View;
import com.appboy.Constants;
import com.google.android.gms.internal.ads.zzcf;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzew implements zzdve {
    private final zzfi zzxn;
    private final zzdtd zzyr;
    private final zzdtp zzys;
    private final zzev zzyt;

    zzew(zzdtd zzdtd, zzdtp zzdtp, zzfi zzfi, zzev zzev) {
        this.zzyr = zzdtd;
        this.zzys = zzdtp;
        this.zzxn = zzfi;
        this.zzyt = zzev;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(View view) {
        this.zzxn.zze(view);
    }

    private final Map<String, Object> zzcc() {
        HashMap hashMap = new HashMap();
        zzcf.zza zzco = this.zzys.zzco();
        hashMap.put("v", this.zzyr.zzavt());
        hashMap.put("gms", Boolean.valueOf(this.zzyr.zzcm()));
        hashMap.put("int", zzco.zzaf());
        hashMap.put("up", Boolean.valueOf(this.zzyt.zzcb()));
        hashMap.put(Constants.APPBOY_PUSH_TITLE_KEY, new Throwable());
        return hashMap;
    }

    public final Map<String, Object> zzcd() {
        Map<String, Object> zzcc = zzcc();
        zzcf.zza zzawa = this.zzys.zzawa();
        zzcc.put("gai", Boolean.valueOf(this.zzyr.zzavu()));
        zzcc.put("did", zzawa.zzal());
        zzcc.put("dst", Integer.valueOf(zzawa.zzam().zzv()));
        zzcc.put("doo", Boolean.valueOf(zzawa.zzan()));
        return zzcc;
    }

    public final Map<String, Object> zzce() {
        return zzcc();
    }

    public final Map<String, Object> zzcf() {
        Map<String, Object> zzcc = zzcc();
        zzcc.put("lts", Long.valueOf(this.zzxn.zzcu()));
        return zzcc;
    }
}
