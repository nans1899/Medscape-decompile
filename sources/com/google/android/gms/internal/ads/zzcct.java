package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcct implements zzeoy<zzccu> {
    private final zzeph<zzcep> zzfqj;
    private final zzeph<Map<String, zzcta<zzbqu>>> zzfrv;
    private final zzeph<Map<String, zzcta<zzcdx>>> zzfxf;
    private final zzeph<Map<String, zzcvl<zzcdx>>> zzfxg;
    private final zzeph<zzbqp<zzboq>> zzfxh;

    public zzcct(zzeph<Map<String, zzcta<zzbqu>>> zzeph, zzeph<Map<String, zzcta<zzcdx>>> zzeph2, zzeph<Map<String, zzcvl<zzcdx>>> zzeph3, zzeph<zzbqp<zzboq>> zzeph4, zzeph<zzcep> zzeph5) {
        this.zzfrv = zzeph;
        this.zzfxf = zzeph2;
        this.zzfxg = zzeph3;
        this.zzfxh = zzeph4;
        this.zzfqj = zzeph5;
    }

    public final /* synthetic */ Object get() {
        return new zzccu(this.zzfrv.get(), this.zzfxf.get(), this.zzfxg.get(), this.zzfxh, this.zzfqj.get());
    }
}
