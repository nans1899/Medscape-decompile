package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import net.media.android.bidder.base.common.Constants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdc implements zzeoy<Set<String>> {
    private final zzeph<zzcep> zzfqj;

    public zzcdc(zzeph<zzcep> zzeph) {
        this.zzfqj = zzeph;
    }

    public static Set<String> zza(zzcep zzcep) {
        Set set;
        if (zzcep.zzams() != null) {
            set = Collections.singleton(Constants.Capabilities.BANNER);
        } else {
            set = Collections.emptySet();
        }
        return (Set) zzepe.zza(set, "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqj.get());
    }
}
