package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdeh implements zzdgu<Bundle> {
    private final String zzdsh;
    private final String zzgws;
    private final Bundle zzgwt;

    private zzdeh(String str, String str2, Bundle bundle) {
        this.zzdsh = str;
        this.zzgws = str2;
        this.zzgwt = bundle;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putString("consent_string", this.zzdsh);
        bundle.putString("fc_consent", this.zzgws);
        bundle.putBundle("iab_consent_info", this.zzgwt);
    }
}
