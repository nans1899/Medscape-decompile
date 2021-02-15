package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfr implements zzeoy<zzdfp> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Bundle> zzgxf;

    private zzdfr(zzeph<zzdzb> zzeph, zzeph<Bundle> zzeph2) {
        this.zzesr = zzeph;
        this.zzgxf = zzeph2;
    }

    public static zzdfr zzbd(zzeph<zzdzb> zzeph, zzeph<Bundle> zzeph2) {
        return new zzdfr(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdfp(this.zzesr.get(), this.zzgxf.get());
    }
}
