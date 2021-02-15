package com.epapyrus.plugpdf.core;

import android.content.Context;

public class ResourceManager {
    public static int getId(Context context, String str) {
        return getResourseIdByName(context, "id", str);
    }

    public static int getStringId(Context context, String str) {
        return getResourseIdByName(context, "string", str);
    }

    public static int getLayoutId(Context context, String str) {
        return getResourseIdByName(context, "layout", str);
    }

    public static int getDrawableId(Context context, String str) {
        return getResourseIdByName(context, "drawable", str);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0061 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getResourseIdByName(android.content.Context r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "[ERROR] ResourceManager.getResourceIdByName"
            java.lang.String r1 = "PlugPDF"
            java.lang.String r2 = r9.getPackageName()
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            r4.<init>()     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            r4.append(r2)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.String r5 = ".R"
            r4.append(r5)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.String r4 = r4.toString()     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.Class[] r4 = r4.getClasses()     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            r5 = 0
            r6 = 0
        L_0x0024:
            int r7 = r4.length     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            if (r6 >= r7) goto L_0x0042
            r7 = r4[r6]     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.String r7 = r7.getName()     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            java.lang.String r8 = "\\$"
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            r8 = 1
            r7 = r7[r8]     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            boolean r7 = r7.equals(r10)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            if (r7 == 0) goto L_0x003f
            r5 = r4[r6]     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            goto L_0x0042
        L_0x003f:
            int r6 = r6 + 1
            goto L_0x0024
        L_0x0042:
            if (r5 == 0) goto L_0x006e
            java.lang.reflect.Field r4 = r5.getField(r11)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            int r3 = r4.getInt(r5)     // Catch:{ ClassNotFoundException -> 0x0061, IllegalArgumentException -> 0x005c, SecurityException -> 0x0057, IllegalAccessException -> 0x0052, NoSuchFieldException -> 0x004d }
            goto L_0x006e
        L_0x004d:
            r9 = move-exception
            android.util.Log.e(r1, r0, r9)
            goto L_0x006e
        L_0x0052:
            r9 = move-exception
            android.util.Log.e(r1, r0, r9)
            goto L_0x006e
        L_0x0057:
            r9 = move-exception
            android.util.Log.e(r1, r0, r9)
            goto L_0x006e
        L_0x005c:
            r9 = move-exception
            android.util.Log.e(r1, r0, r9)
            goto L_0x006e
        L_0x0061:
            android.content.res.Resources r9 = r9.getResources()     // Catch:{ Exception -> 0x006a }
            int r3 = r9.getIdentifier(r11, r10, r2)     // Catch:{ Exception -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r9 = move-exception
            android.util.Log.e(r1, r0, r9)
        L_0x006e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.ResourceManager.getResourseIdByName(android.content.Context, java.lang.String, java.lang.String):int");
    }
}
