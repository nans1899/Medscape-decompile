package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.OnPaidEventListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaab extends zzyl {
    private final OnPaidEventListener zzclc;

    public zzaab(OnPaidEventListener onPaidEventListener) {
        this.zzclc = onPaidEventListener;
    }

    public final void zza(zzvp zzvp) {
        if (this.zzclc != null) {
            this.zzclc.onPaidEvent(AdValue.zza(zzvp.zzadc, zzvp.zzadd, zzvp.zzade));
        }
    }
}
