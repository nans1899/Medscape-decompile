package com.medscape.android.util;

import android.app.Activity;
import android.view.View;

public class ViewHelper {
    public static <T extends View> T findById(Activity activity, int i) {
        return activity.findViewById(i);
    }

    public static <T extends View> T findById(View view, int i) {
        return view.findViewById(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUniqueId(android.view.View r5) {
        /*
            int r0 = r5.getId()
            android.content.Context r1 = r5.getContext()
            android.content.res.Resources r1 = r1.getResources()
            java.lang.String r2 = ""
            if (r0 <= 0) goto L_0x004c
            if (r1 == 0) goto L_0x004c
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r3 = r3 & r0
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            if (r3 == r4) goto L_0x0025
            r4 = 2130706432(0x7f000000, float:1.7014118E38)
            if (r3 == r4) goto L_0x0022
            java.lang.String r3 = r1.getResourcePackageName(r0)     // Catch:{ NotFoundException -> 0x004c }
            goto L_0x0027
        L_0x0022:
            java.lang.String r3 = "app"
            goto L_0x0027
        L_0x0025:
            java.lang.String r3 = "android"
        L_0x0027:
            java.lang.String r4 = r1.getResourceTypeName(r0)     // Catch:{ NotFoundException -> 0x004c }
            java.lang.String r0 = r1.getResourceEntryName(r0)     // Catch:{ NotFoundException -> 0x004c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NotFoundException -> 0x004c }
            r1.<init>()     // Catch:{ NotFoundException -> 0x004c }
            r1.append(r3)     // Catch:{ NotFoundException -> 0x004c }
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch:{ NotFoundException -> 0x004c }
            r1.append(r4)     // Catch:{ NotFoundException -> 0x004c }
            java.lang.String r3 = "/"
            r1.append(r3)     // Catch:{ NotFoundException -> 0x004c }
            r1.append(r0)     // Catch:{ NotFoundException -> 0x004c }
            java.lang.String r0 = r1.toString()     // Catch:{ NotFoundException -> 0x004c }
            goto L_0x004d
        L_0x004c:
            r0 = r2
        L_0x004d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getName()
            r3.append(r4)
            java.lang.String r4 = "@"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.<init>(r3)
            int r5 = java.lang.System.identityHashCode(r5)
            java.lang.String r5 = java.lang.Integer.toHexString(r5)
            r1.append(r5)
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L_0x007d
            goto L_0x008e
        L_0x007d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r2 = "?"
            r5.append(r2)
            r5.append(r0)
            java.lang.String r2 = r5.toString()
        L_0x008e:
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.ViewHelper.getUniqueId(android.view.View):java.lang.String");
    }
}
