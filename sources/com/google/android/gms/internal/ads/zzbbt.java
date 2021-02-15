package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzbbt {
    public static <T> T zza(Context context, String str, zzbbs<IBinder, T> zzbbs) throws zzbbv {
        try {
            return zzbbs.apply(zzbv(context).instantiate(str));
        } catch (Exception e) {
            throw new zzbbv(e);
        }
    }

    public static Context zzbu(Context context) throws zzbbv {
        return zzbv(context).getModuleContext();
    }

    private static DynamiteModule zzbv(Context context) throws zzbbv {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
        } catch (Exception e) {
            throw new zzbbv(e);
        }
    }
}
