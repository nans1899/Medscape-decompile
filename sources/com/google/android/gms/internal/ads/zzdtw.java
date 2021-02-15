package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.internal.ads.zzcf;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdtw implements zzdtv {
    private static final zzcf.zza zzhmx = ((zzcf.zza) ((zzejz) zzcf.zza.zzaq().zzw(ExifInterface.LONGITUDE_EAST).zzbgt()));

    zzdtw() {
    }

    public final zzcf.zza zzck(Context context) throws PackageManager.NameNotFoundException {
        return zzdtj.zzj(context, context.getPackageName(), Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
    }

    public final zzcf.zza zzawe() {
        return zzhmx;
    }
}
