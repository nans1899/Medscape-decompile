package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgj implements zzdgu<Bundle> {
    private final String zzbre;
    private final int zzdri;
    private final boolean zzdsf;
    private final boolean zzeeo;
    private final boolean zzgxs;
    private final int zzgxt;
    private final int zzgxu;

    public zzdgj(boolean z, boolean z2, String str, boolean z3, int i, int i2, int i3) {
        this.zzgxs = z;
        this.zzeeo = z2;
        this.zzbre = str;
        this.zzdsf = z3;
        this.zzdri = i;
        this.zzgxt = i2;
        this.zzgxu = i3;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putString("js", this.zzbre);
        bundle.putBoolean("is_nonagon", true);
        bundle.putString("extra_caps", (String) zzwm.zzpx().zzd(zzabb.zzcrs));
        bundle.putInt("target_api", this.zzdri);
        bundle.putInt("dv", this.zzgxt);
        bundle.putInt("lv", this.zzgxu);
        Bundle zza = zzdot.zza(bundle, "sdk_env");
        zza.putBoolean("mf", zzacw.zzdbr.get().booleanValue());
        zza.putBoolean("instant_app", this.zzgxs);
        zza.putBoolean("lite", this.zzeeo);
        zza.putBoolean("is_privileged_process", this.zzdsf);
        bundle.putBundle("sdk_env", zza);
        Bundle zza2 = zzdot.zza(zza, "build_meta");
        zza2.putString("cl", "330794610");
        zza2.putString("rapid_rc", "dev");
        zza2.putString("rapid_rollup", "HEAD");
        zza.putBundle("build_meta", zza2);
    }
}
