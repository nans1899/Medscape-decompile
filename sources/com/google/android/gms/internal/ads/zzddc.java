package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddc implements zzdgu<Bundle> {
    private final float zzdrh;
    private final int zzdul;
    private final boolean zzdum;
    private final boolean zzdun;
    private final int zzduo;
    private final int zzdup;
    private final int zzduq;
    private final boolean zzgwb;

    public zzddc(int i, boolean z, boolean z2, int i2, int i3, int i4, float f, boolean z3) {
        this.zzdul = i;
        this.zzdum = z;
        this.zzdun = z2;
        this.zzduo = i2;
        this.zzdup = i3;
        this.zzduq = i4;
        this.zzdrh = f;
        this.zzgwb = z3;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putInt("am", this.zzdul);
        bundle.putBoolean("ma", this.zzdum);
        bundle.putBoolean("sp", this.zzdun);
        bundle.putInt("muv", this.zzduo);
        bundle.putInt("rm", this.zzdup);
        bundle.putInt("riv", this.zzduq);
        bundle.putFloat("android_app_volume", this.zzdrh);
        bundle.putBoolean("android_app_muted", this.zzgwb);
    }
}
