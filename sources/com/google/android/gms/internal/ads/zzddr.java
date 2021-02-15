package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddr implements zzdgu<Bundle> {
    private final String zzguw;

    public zzddr(@Nullable String str) {
        this.zzguw = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (!TextUtils.isEmpty(this.zzguw)) {
            bundle.putString("fwd_cld", this.zzguw);
        }
    }
}
