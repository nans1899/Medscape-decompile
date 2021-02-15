package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvx implements zzeoy<zzbvv> {
    private final zzeph<Set<zzcab<AdMetadataListener>>> zzfqq;

    private zzbvx(zzeph<Set<zzcab<AdMetadataListener>>> zzeph) {
        this.zzfqq = zzeph;
    }

    public static zzbvx zzm(zzeph<Set<zzcab<AdMetadataListener>>> zzeph) {
        return new zzbvx(zzeph);
    }

    public final /* synthetic */ Object get() {
        return new zzbvv(this.zzfqq.get());
    }
}
