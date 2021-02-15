package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfv implements zzdgx<zzdgu<Bundle>> {
    private final String zzfui;
    private final Context zzvr;

    zzdfv(Context context, String str) {
        this.zzvr = context;
        this.zzfui = str;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        zzdfu zzdfu;
        if (this.zzfui == null) {
            zzdfu = null;
        } else {
            zzdfu = new zzdfu(this);
        }
        return zzdyq.zzaf(zzdfu);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(Bundle bundle) {
        bundle.putString("rewarded_sku_package", this.zzvr.getPackageName());
    }
}
