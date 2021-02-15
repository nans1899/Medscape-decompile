package com.google.android.gms.internal.ads;

import android.view.View;
import com.google.android.gms.ads.internal.zzg;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabr extends zzabw {
    private final zzg zzczj;
    private final String zzczk;
    private final String zzczl;

    public zzabr(zzg zzg, String str, String str2) {
        this.zzczj = zzg;
        this.zzczk = str;
        this.zzczl = str2;
    }

    public final String zzrs() {
        return this.zzczk;
    }

    public final String getContent() {
        return this.zzczl;
    }

    public final void zzn(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.zzczj.zzh((View) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public final void recordClick() {
        this.zzczj.zzkc();
    }

    public final void recordImpression() {
        this.zzczj.zzkd();
    }
}
