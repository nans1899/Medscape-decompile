package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfx implements zzeoy<zzdfv> {
    private final zzeph<Context> zzesu;
    private final zzeph<String> zzevh;

    private zzdfx(zzeph<Context> zzeph, zzeph<String> zzeph2) {
        this.zzesu = zzeph;
        this.zzevh = zzeph2;
    }

    public static zzdfx zzbe(zzeph<Context> zzeph, zzeph<String> zzeph2) {
        return new zzdfx(zzeph, zzeph2);
    }

    public static zzdfv zzu(Context context, String str) {
        return new zzdfv(context, str);
    }

    public final /* synthetic */ Object get() {
        return zzu(this.zzesu.get(), this.zzevh.get());
    }
}
