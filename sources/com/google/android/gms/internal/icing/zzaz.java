package com.google.android.gms.internal.icing;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class zzaz {
    private static UserManager zzcf;
    private static volatile boolean zzcg = (!zzk());
    private static boolean zzch = false;

    private zzaz() {
    }

    public static boolean zzk() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isUserUnlocked(Context context) {
        return !zzk() || zzb(context);
    }

    private static boolean zza(Context context) {
        boolean z;
        boolean z2 = true;
        int i = 1;
        while (true) {
            z = false;
            if (i > 2) {
                break;
            }
            if (zzcf == null) {
                zzcf = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zzcf;
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
                zzcf = null;
                i++;
            }
        }
        if (z) {
            zzcf = null;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzb(android.content.Context r3) {
        /*
            boolean r0 = zzcg
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Class<com.google.android.gms.internal.icing.zzaz> r0 = com.google.android.gms.internal.icing.zzaz.class
            monitor-enter(r0)
            boolean r2 = zzcg     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r1
        L_0x000f:
            boolean r3 = zza(r3)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0017
            zzcg = r3     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            return r3
        L_0x0019:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0019 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzaz.zzb(android.content.Context):boolean");
    }
}
