package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddk implements zzdgx<zzdgu<Bundle>> {
    private final zzdlf zzgwe;

    zzddk(zzdlf zzdlf) {
        this.zzgwe = zzdlf;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        zzdlf zzdlf = this.zzgwe;
        return zzdyq.zzaf((zzdlf == null || zzdlf.zzasz() == null || this.zzgwe.zzasz().isEmpty()) ? null : new zzddn(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(Bundle bundle) {
        bundle.putString("key_schema", this.zzgwe.zzasz());
    }
}
