package com.google.android.gms.internal.icing;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzbo {
    private static volatile zzbx<Boolean> zzcw = zzbx.zzw();
    private static final Object zzcx = new Object();

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
        } else if (zzcw.isPresent()) {
            return zzcw.get().booleanValue();
        } else {
            synchronized (zzcx) {
                if (zzcw.isPresent()) {
                    boolean booleanValue = zzcw.get().booleanValue();
                    return booleanValue;
                }
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0);
                    if (resolveContentProvider == null || !"com.google.android.gms".equals(resolveContentProvider.packageName)) {
                        z = false;
                        if (z && zzf(context)) {
                            z2 = true;
                        }
                        zzcw = zzbx.zzb(Boolean.valueOf(z2));
                        return zzcw.get().booleanValue();
                    }
                }
                z = true;
                z2 = true;
                zzcw = zzbx.zzb(Boolean.valueOf(z2));
                return zzcw.get().booleanValue();
            }
        }
    }
}
