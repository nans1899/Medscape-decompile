package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class zzas {
    private static UserManager zzfm;
    private static volatile boolean zzfn = (!zzu());
    private static boolean zzfo = false;

    private zzas() {
    }

    public static boolean zzu() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzu() || zzd(context);
    }

    private static boolean zzc(Context context) {
        boolean z;
        boolean z2 = true;
        int i = 1;
        while (true) {
            z = false;
            if (i > 2) {
                break;
            }
            if (zzfm == null) {
                zzfm = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zzfm;
            if (userManager == null) {
                return true;
            }
            try {
                if (!userManager.isUserUnlocked() && userManager.isUserRunning(Process.myUserHandle())) {
                    z2 = false;
                }
                z = z2;
            } catch (NullPointerException e) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e);
                zzfm = null;
                i++;
            }
        }
        if (z) {
            zzfm = null;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzd(android.content.Context r3) {
        /*
            boolean r0 = zzfn
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Class<com.google.android.gms.internal.vision.zzas> r0 = com.google.android.gms.internal.vision.zzas.class
            monitor-enter(r0)
            boolean r2 = zzfn     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r1
        L_0x000f:
            boolean r3 = zzc(r3)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0017
            zzfn = r3     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r3
        L_0x0019:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzas.zzd(android.content.Context):boolean");
    }
}
