package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import com.medscape.android.cache.Cache;
import com.medscape.android.settings.Settings;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddw implements zzdgu<Bundle> {
    @Nullable
    private final Location zznb;

    public zzddw(@Nullable Location location) {
        this.zznb = location;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        if (this.zznb != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putFloat("radius", this.zznb.getAccuracy() * 1000.0f);
            bundle2.putLong(Settings.LATITUDE_KEY, (long) (this.zznb.getLatitude() * 1.0E7d));
            bundle2.putLong(Settings.LONGITUDE_KEY, (long) (this.zznb.getLongitude() * 1.0E7d));
            bundle2.putLong(Cache.Caches.TIME, this.zznb.getTime() * 1000);
            bundle.putBundle("uule", bundle2);
        }
    }
}
