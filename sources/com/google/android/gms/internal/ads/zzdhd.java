package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.LocaleList;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdhd implements zzdgx<zzdha> {
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzdhd(zzdzb zzdzb, Context context) {
        this.zzgcz = zzdzb;
        this.zzvr = context;
    }

    public final zzdzc<zzdha> zzarj() {
        return this.zzgcz.zze(new zzdhc(this));
    }

    private static String zzx(Context context) {
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = packageInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static String zza(Context context, PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza == null || (activityInfo = zza.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = activityInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdha zzasb() throws Exception {
        boolean z;
        PackageManager packageManager = this.zzvr.getPackageManager();
        Locale locale = Locale.getDefault();
        boolean z2 = zza(packageManager, "geo:0,0?q=donuts") != null;
        boolean z3 = zza(packageManager, "http://www.google.com") != null;
        String country = locale.getCountry();
        boolean startsWith = Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        boolean isLatchsky = DeviceProperties.isLatchsky(this.zzvr);
        boolean isSidewinder = DeviceProperties.isSidewinder(this.zzvr);
        String language = locale.getLanguage();
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList = LocaleList.getDefault();
            for (int i = 0; i < localeList.size(); i++) {
                arrayList.add(localeList.get(i).getLanguage());
            }
        }
        String zza = zza(this.zzvr, packageManager);
        String zzx = zzx(this.zzvr);
        String str = Build.FINGERPRINT;
        Context context = this.zzvr;
        if (packageManager != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= queryIntentActivities.size()) {
                        break;
                    } else if (resolveActivity.activityInfo.name.equals(queryIntentActivities.get(i2).activityInfo.name)) {
                        z = resolveActivity.activityInfo.packageName.equals(zzepj.zzcl(context));
                        break;
                    } else {
                        i2++;
                    }
                }
                return new zzdha(z2, z3, country, startsWith, isLatchsky, isSidewinder, language, arrayList, zza, zzx, str, z, Build.MODEL, zzp.zzkt().zzya());
            }
        }
        z = false;
        return new zzdha(z2, z3, country, startsWith, isLatchsky, isSidewinder, language, arrayList, zza, zzx, str, z, Build.MODEL, zzp.zzkt().zzya());
    }
}
