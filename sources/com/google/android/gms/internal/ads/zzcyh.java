package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyh implements zzeoy<zzcyb> {
    private final zzeph<zzdzb> zzfqk;
    private final zzeph<zzdsi> zzfqv;
    private final zzeph<zzbpm> zzgof;
    private final zzeph<Context> zzgrp;
    private final zzeph<zzaby> zzgrq;

    public zzcyh(zzeph<Context> zzeph, zzeph<zzbpm> zzeph2, zzeph<zzdsi> zzeph3, zzeph<zzdzb> zzeph4, zzeph<zzaby> zzeph5) {
        this.zzgrp = zzeph;
        this.zzgof = zzeph2;
        this.zzfqv = zzeph3;
        this.zzfqk = zzeph4;
        this.zzgrq = zzeph5;
    }

    public final /* synthetic */ Object get() {
        return new zzcyb(this.zzgrp.get(), this.zzgof.get(), this.zzfqv.get(), this.zzfqk.get(), this.zzgrq.get());
    }
}
