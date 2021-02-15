package com.google.android.gms.internal.vision;

import android.os.Binder;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final /* synthetic */ class zzbb {
    public static <V> V zza(zzba<V> zzba) {
        long clearCallingIdentity;
        try {
            return zzba.zzac();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzac = zzba.zzac();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzac;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
