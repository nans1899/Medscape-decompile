package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzazi extends zzazj {
    public final boolean zza(Activity activity, Configuration configuration) {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcts)).booleanValue()) {
            return false;
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctu)).booleanValue()) {
            return activity.isInMultiWindowMode();
        }
        zzwm.zzpt();
        int zzc = zzbbg.zzc(activity, configuration.screenHeightDp);
        int zzc2 = zzbbg.zzc(activity, configuration.screenWidthDp);
        zzp.zzkr();
        DisplayMetrics zza = zzayu.zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza.heightPixels;
        int i2 = zza.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        int round = ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d)) * ((Integer) zzwm.zzpx().zzd(zzabb.zzctr)).intValue();
        if (!(zze(i, zzc + dimensionPixelSize, round) && zze(i2, zzc2, round))) {
            return true;
        }
        return false;
    }

    private static boolean zze(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }
}
