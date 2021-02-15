package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbam extends zzaa<zzy> {
    private final Map<String, String> zzam;
    private final zzbcg<zzy> zzecx;
    private final zzbbk zzecy;

    public zzbam(String str, zzbcg<zzy> zzbcg) {
        this(str, (Map<String, String>) null, zzbcg);
    }

    private zzbam(String str, Map<String, String> map, zzbcg<zzy> zzbcg) {
        super(0, str, new zzbap(zzbcg));
        this.zzam = null;
        this.zzecx = zzbcg;
        zzbbk zzbbk = new zzbbk();
        this.zzecy = zzbbk;
        zzbbk.zza(str, "GET", (Map<String, ?>) null, (byte[]) null);
    }

    /* access modifiers changed from: protected */
    public final zzaj<zzy> zza(zzy zzy) {
        return zzaj.zza(zzy, zzbc.zzb(zzy));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        zzy zzy = (zzy) obj;
        this.zzecy.zza((Map<String, ?>) zzy.zzam, zzy.statusCode);
        zzbbk zzbbk = this.zzecy;
        byte[] bArr = zzy.data;
        if (zzbbk.isEnabled() && bArr != null) {
            zzbbk.zzi(bArr);
        }
        this.zzecx.set(zzy);
    }
}
