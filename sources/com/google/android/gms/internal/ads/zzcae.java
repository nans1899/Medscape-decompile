package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcae extends zzbyr<zzqu> implements zzqu {
    private final zzdnv zzeot;
    private Map<View, zzqq> zzfwm = new WeakHashMap(1);
    private final Context zzvr;

    public zzcae(Context context, Set<zzcab<zzqu>> set, zzdnv zzdnv) {
        super(set);
        this.zzvr = context;
        this.zzeot = zzdnv;
    }

    public final synchronized void zza(zzqr zzqr) {
        zza(new zzcad(zzqr));
    }

    public final synchronized void zzv(View view) {
        zzqq zzqq = this.zzfwm.get(view);
        if (zzqq == null) {
            zzqq = new zzqq(this.zzvr, view);
            zzqq.zza((zzqu) this);
            this.zzfwm.put(view, zzqq);
        }
        if (this.zzeot != null && this.zzeot.zzdtq) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcpw)).booleanValue()) {
                zzqq.zzen(((Long) zzwm.zzpx().zzd(zzabb.zzcpv)).longValue());
                return;
            }
        }
        zzqq.zzlw();
    }

    public final synchronized void zzw(View view) {
        if (this.zzfwm.containsKey(view)) {
            this.zzfwm.get(view).zzb(this);
            this.zzfwm.remove(view);
        }
    }
}
