package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcdj implements zzahq<Object> {
    private WeakReference<zzcdi> zzfyj;

    private zzcdj(zzcdi zzcdi) {
        this.zzfyj = new WeakReference<>(zzcdi);
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzcdi zzcdi = (zzcdi) this.zzfyj.get();
        if (zzcdi != null) {
            zzcdi.zzfxv.onAdImpression();
        }
    }
}
