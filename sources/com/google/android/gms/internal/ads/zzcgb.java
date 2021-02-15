package com.google.android.gms.internal.ads;

import androidx.collection.ArrayMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgb implements zzbuz {
    private final zzcei zzfxu;
    private final zzcem zzfyv;

    public zzcgb(zzcei zzcei, zzcem zzcem) {
        this.zzfxu = zzcei;
        this.zzfyv = zzcem;
    }

    public final void onAdImpression() {
        if (this.zzfxu.zzamh() != null) {
            zzbgj zzamg = this.zzfxu.zzamg();
            zzbgj zzamf = this.zzfxu.zzamf();
            if (zzamg == null) {
                zzamg = zzamf != null ? zzamf : null;
            }
            if (this.zzfyv.zzalw() && zzamg != null) {
                zzamg.zza("onSdkImpression", (Map<String, ?>) new ArrayMap());
            }
        }
    }
}
