package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzayu;
import com.google.android.gms.internal.ads.zzwm;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zza {
    public static boolean zza(Context context, zzb zzb, zzv zzv) {
        int i = 0;
        if (zzb == null) {
            zzayp.zzfe("No intent data for launcher overlay.");
            return false;
        }
        zzabb.initialize(context);
        if (zzb.intent != null) {
            return zza(context, zzb.intent, zzv);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(zzb.url)) {
            zzayp.zzfe("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty(zzb.mimeType)) {
            intent.setDataAndType(Uri.parse(zzb.url), zzb.mimeType);
        } else {
            intent.setData(Uri.parse(zzb.url));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(zzb.packageName)) {
            intent.setPackage(zzb.packageName);
        }
        if (!TextUtils.isEmpty(zzb.zzdoj)) {
            String[] split = zzb.zzdoj.split("/", 2);
            if (split.length < 2) {
                String valueOf = String.valueOf(zzb.zzdoj);
                zzayp.zzfe(valueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(valueOf) : new String("Could not parse component name from open GMSG: "));
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        String str = zzb.zzdok;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                zzayp.zzfe("Could not parse intent flags.");
            }
            intent.addFlags(i);
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctk)).booleanValue()) {
            intent.addFlags(268435456);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        } else {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctj)).booleanValue()) {
                zzp.zzkr();
                zzayu.zzb(context, intent);
            }
        }
        return zza(context, intent, zzv);
    }

    private static boolean zza(Context context, Intent intent, zzv zzv) {
        try {
            String valueOf = String.valueOf(intent.toURI());
            zzayp.zzei(valueOf.length() != 0 ? "Launching an intent: ".concat(valueOf) : new String("Launching an intent: "));
            zzp.zzkr();
            zzayu.zza(context, intent);
            if (zzv == null) {
                return true;
            }
            zzv.zzvd();
            return true;
        } catch (ActivityNotFoundException e) {
            zzayp.zzfe(e.getMessage());
            return false;
        }
    }
}
