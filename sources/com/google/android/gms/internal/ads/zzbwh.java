package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbwh implements zzeoy<zzbwa> {
    private final zzeph<Set<zzcab<zzp>>> zzfqq;

    private zzbwh(zzeph<Set<zzcab<zzp>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbwh zzo(zzeph<Set<zzcab<zzp>>> zzeph) {
        return new zzbwh(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbwa(this.zzfqq.get());
    }
}
