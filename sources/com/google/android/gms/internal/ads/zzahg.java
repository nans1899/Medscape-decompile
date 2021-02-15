package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahg implements zzahq<zzbgj> {
    zzahg() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        if (zzbgj.zzack() != null) {
            zzbgj.zzack().zzmt();
        }
        zze zzabs = zzbgj.zzabs();
        if (zzabs != null) {
            zzabs.close();
            return;
        }
        zze zzabt = zzbgj.zzabt();
        if (zzabt != null) {
            zzabt.close();
        } else {
            zzayp.zzfe("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
