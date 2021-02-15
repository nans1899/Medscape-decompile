package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbg {
    private static volatile zzcy<Boolean> zzgc = zzcy.zzcb();
    private static final Object zzgd = new Object();

    private static boolean zzf(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static boolean zza(Context context, Uri uri) {
        boolean z;
        String authority = uri.getAuthority();
        boolean z2 = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
            sb.append(authority);
            sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            Log.e("PhenotypeClientHelper", sb.toString());
            return false;
        } else if (zzgc.isPresent()) {
            return zzgc.get().booleanValue();
        } else {
            synchronized (zzgd) {
                if (zzgc.isPresent()) {
                    boolean booleanValue = zzgc.get().booleanValue();
                    return booleanValue;
                }
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0);
                    if (resolveContentProvider == null || !"com.google.android.gms".equals(resolveContentProvider.packageName)) {
                        z = false;
                        if (z && zzf(context)) {
                            z2 = true;
                        }
                        zzgc = zzcy.zzb(Boolean.valueOf(z2));
                        return zzgc.get().booleanValue();
                    }
                }
                z = true;
                z2 = true;
                zzgc = zzcy.zzb(Boolean.valueOf(z2));
                return zzgc.get().booleanValue();
            }
        }
    }
}
