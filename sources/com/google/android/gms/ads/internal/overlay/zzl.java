package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.ads.zzayu;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzl {
    public static void zza(Context context, AdOverlayInfoParcel adOverlayInfoParcel, boolean z) {
        if (adOverlayInfoParcel.zzdpr == 4 && adOverlayInfoParcel.zzdpm == null) {
            if (adOverlayInfoParcel.zzcgv != null) {
                adOverlayInfoParcel.zzcgv.onAdClicked();
            }
            zzp.zzkp();
            zza.zza(context, adOverlayInfoParcel.zzdpl, adOverlayInfoParcel.zzdpq);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", adOverlayInfoParcel.zzbpe.zzeen);
        intent.putExtra("shouldCallOnOverlayOpened", z);
        AdOverlayInfoParcel.zza(intent, adOverlayInfoParcel);
        if (!PlatformVersion.isAtLeastLollipop()) {
            intent.addFlags(524288);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        zzp.zzkr();
        zzayu.zza(context, intent);
    }
}
