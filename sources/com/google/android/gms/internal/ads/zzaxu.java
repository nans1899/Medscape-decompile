package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaxu implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzbcg zzdyg;

    zzaxu(zzaxv zzaxv, Context context, zzbcg zzbcg) {
        this.val$context = context;
        this.zzdyg = zzbcg;
    }

    public final void run() {
        try {
            this.zzdyg.set(AdvertisingIdClient.getAdvertisingIdInfo(this.val$context));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzdyg.setException(e);
            zzbbq.zzc("Exception while getting advertising Id info", e);
        }
    }
}
