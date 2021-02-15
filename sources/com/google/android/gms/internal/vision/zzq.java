package com.google.android.gms.internal.vision;

import android.content.Context;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzq {
    public static boolean zza(Context context, String str, String str2) {
        String zzl = zzdg.zzl(str2);
        if ("face".equals(str) || "ica".equals(str) || "ocr".equals(str) || "barcode".equals(str)) {
            int lastIndexOf = zzl.lastIndexOf(".so");
            if (lastIndexOf == zzl.length() - 3) {
                zzl = zzl.substring(0, lastIndexOf);
            }
            if (zzl.indexOf("lib") == 0) {
                zzl = zzl.substring(3);
            }
            boolean zza = zzr.zza(str, zzl);
            if (!zza) {
                Log.d("NativeLibraryLoader", String.format("%s engine not loaded with %s.", new Object[]{str, zzl}));
            }
            return zza;
        }
        Log.e("NativeLibraryLoader", String.format("Unrecognized engine: %s", new Object[]{str}));
        return false;
    }
}
