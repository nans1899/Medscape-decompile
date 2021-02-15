package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.internal.aa;
import java.io.File;

public class SplitInstallHelper {
    private static final aa a = new aa("SplitInstallHelper");

    private SplitInstallHelper() {
    }

    public static void loadLibrary(Context context, String str) throws UnsatisfiedLinkError {
        try {
            synchronized (v.class) {
                System.loadLibrary(str);
            }
        } catch (UnsatisfiedLinkError e) {
            try {
                String str2 = context.getApplicationInfo().nativeLibraryDir;
                String mapLibraryName = System.mapLibraryName(str);
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(mapLibraryName).length());
                sb.append(str2);
                sb.append("/");
                sb.append(mapLibraryName);
                String sb2 = sb.toString();
                if (new File(sb2).exists()) {
                    System.load(sb2);
                    return;
                }
                throw e;
            } catch (UnsatisfiedLinkError e2) {
                throw e2;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateAppInfo(android.content.Context r7) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 25
            if (r0 <= r1) goto L_0x007b
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 >= r1) goto L_0x007b
            com.google.android.play.core.internal.aa r0 = a
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "Calling dispatchPackageBroadcast"
            r0.c(r3, r2)
            java.lang.String r0 = "android.app.ActivityThread"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r2 = "currentActivityThread"
            java.lang.Class[] r3 = new java.lang.Class[r1]     // Catch:{ Exception -> 0x0071 }
            java.lang.reflect.Method r2 = r0.getMethod(r2, r3)     // Catch:{ Exception -> 0x0071 }
            r3 = 1
            r2.setAccessible(r3)     // Catch:{ Exception -> 0x0071 }
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0071 }
            r5 = 0
            java.lang.Object r2 = r2.invoke(r5, r4)     // Catch:{ Exception -> 0x0071 }
            java.lang.String r4 = "mAppThread"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r4)     // Catch:{ Exception -> 0x0071 }
            r0.setAccessible(r3)     // Catch:{ Exception -> 0x0071 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Exception -> 0x0071 }
            java.lang.Class r2 = r0.getClass()     // Catch:{ Exception -> 0x0071 }
            r4 = 2
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x0071 }
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0071 }
            r5[r1] = r6     // Catch:{ Exception -> 0x0071 }
            java.lang.Class<java.lang.String[]> r6 = java.lang.String[].class
            r5[r3] = r6     // Catch:{ Exception -> 0x0071 }
            java.lang.String r6 = "dispatchPackageBroadcast"
            java.lang.reflect.Method r2 = r2.getMethod(r6, r5)     // Catch:{ Exception -> 0x0071 }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0071 }
            r5 = 3
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0071 }
            r4[r1] = r5     // Catch:{ Exception -> 0x0071 }
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0071 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ Exception -> 0x0071 }
            r5[r1] = r7     // Catch:{ Exception -> 0x0071 }
            r4[r3] = r5     // Catch:{ Exception -> 0x0071 }
            r2.invoke(r0, r4)     // Catch:{ Exception -> 0x0071 }
            com.google.android.play.core.internal.aa r7 = a     // Catch:{ Exception -> 0x0071 }
            java.lang.String r0 = "Called dispatchPackageBroadcast"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0071 }
            r7.c(r0, r2)     // Catch:{ Exception -> 0x0071 }
            return
        L_0x0071:
            r7 = move-exception
            com.google.android.play.core.internal.aa r0 = a
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Update app info with dispatchPackageBroadcast failed!"
            r0.a((java.lang.Throwable) r7, (java.lang.String) r2, (java.lang.Object[]) r1)
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.SplitInstallHelper.updateAppInfo(android.content.Context):void");
    }
}
