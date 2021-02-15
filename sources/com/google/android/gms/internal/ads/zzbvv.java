package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbvv extends zzbyr<AdMetadataListener> implements zzagt {
    private Bundle zzfux = new Bundle();

    public zzbvv(Set<zzcab<AdMetadataListener>> set) {
        super(set);
    }

    public final synchronized void zza(String str, Bundle bundle) {
        this.zzfux.putAll(bundle);
        zza(zzbvu.zzfur);
    }

    public final synchronized Bundle getAdMetadata() {
        return new Bundle(this.zzfux);
    }
}
