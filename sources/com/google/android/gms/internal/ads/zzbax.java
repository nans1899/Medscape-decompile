package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbax extends zzayl {
    private final String url;
    private final zzbbu zzedh;

    public zzbax(Context context, String str, String str2) {
        this(str2, zzp.zzkr().zzs(context, str));
    }

    private zzbax(String str, String str2) {
        this.zzedh = new zzbbu(str2);
        this.url = str;
    }

    public final void zzvb() {
        this.zzedh.zzes(this.url);
    }
}
