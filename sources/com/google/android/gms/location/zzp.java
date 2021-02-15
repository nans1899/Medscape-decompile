package com.google.android.gms.location;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzp {
    public static final Feature zza = new Feature("support_context_feature_id", 1);
    public static final Feature zzb;
    public static final Feature[] zzc;
    private static final Feature zzd = new Feature("name_ulr_private", 1);
    private static final Feature zze = new Feature("name_sleep_segment_request", 1);

    static {
        Feature feature = new Feature("get_current_location", 1);
        zzb = feature;
        zzc = new Feature[]{zzd, zze, zza, feature};
    }
}
