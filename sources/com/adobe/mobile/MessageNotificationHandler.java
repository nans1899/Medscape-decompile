package com.adobe.mobile;

import android.content.BroadcastReceiver;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.adobe.mobile.StaticMethods;

public class MessageNotificationHandler extends BroadcastReceiver {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: android.app.Notification} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v24, resolved type: android.app.Notification} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v25, resolved type: android.app.Notification} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v35, resolved type: android.app.Notification} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v36, resolved type: android.app.Notification} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r14, android.content.Intent r15) {
        /*
            r13 = this;
            java.lang.String r14 = "userData"
            java.lang.String r0 = "adb_m_l_id"
            android.os.Bundle r1 = r15.getExtras()
            r2 = 0
            if (r1 != 0) goto L_0x0014
            java.lang.Object[] r14 = new java.lang.Object[r2]
            java.lang.String r15 = "Messages - unable to load extras from local notification intent"
            com.adobe.mobile.StaticMethods.logDebugFormat(r15, r14)
            return
        L_0x0014:
            r3 = 1
            java.lang.String r4 = "alarm_message"
            java.lang.String r4 = r1.getString(r4)     // Catch:{ Exception -> 0x021d }
            java.lang.String r5 = "adbMessageCode"
            int r5 = r1.getInt(r5)     // Catch:{ Exception -> 0x021d }
            java.lang.String r6 = "requestCode"
            int r6 = r1.getInt(r6)     // Catch:{ Exception -> 0x021d }
            java.lang.String r7 = r1.getString(r0)     // Catch:{ Exception -> 0x021d }
            java.lang.String r8 = "adb_deeplink"
            java.lang.String r8 = r1.getString(r8)     // Catch:{ Exception -> 0x021d }
            java.lang.String r1 = r1.getString(r14)     // Catch:{ Exception -> 0x021d }
            java.lang.Integer r9 = com.adobe.mobile.Messages.MESSAGE_LOCAL_IDENTIFIER
            int r9 = r9.intValue()
            if (r5 == r9) goto L_0x003e
            return
        L_0x003e:
            if (r4 != 0) goto L_0x0048
            java.lang.Object[] r14 = new java.lang.Object[r2]
            java.lang.String r15 = "Messages - local notification message was empty "
            com.adobe.mobile.StaticMethods.logDebugFormat(r15, r14)
            return
        L_0x0048:
            android.app.Activity r5 = com.adobe.mobile.StaticMethods.getCurrentActivity()     // Catch:{ NullActivityException -> 0x0212 }
            android.content.Context r9 = com.adobe.mobile.StaticMethods.getSharedContext()     // Catch:{ NullContextException -> 0x0207 }
            r10 = 0
            android.app.Activity r10 = com.adobe.mobile.StaticMethods.getCurrentActivity()     // Catch:{ NullActivityException -> 0x0056 }
            goto L_0x005d
        L_0x0056:
            java.lang.Object[] r11 = new java.lang.Object[r2]
            java.lang.String r12 = "Messages - unable to find activity for your notification, using default"
            com.adobe.mobile.StaticMethods.logErrorFormat(r12, r11)
        L_0x005d:
            if (r8 == 0) goto L_0x0074
            boolean r11 = r8.isEmpty()
            if (r11 != 0) goto L_0x0074
            android.content.Intent r15 = new android.content.Intent
            java.lang.String r10 = "android.intent.action.VIEW"
            r15.<init>(r10)
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r15.setData(r8)
            goto L_0x007a
        L_0x0074:
            if (r10 == 0) goto L_0x007a
            android.content.Intent r15 = r10.getIntent()
        L_0x007a:
            r8 = 603979776(0x24000000, float:2.7755576E-17)
            r15.setFlags(r8)
            r15.putExtra(r0, r7)
            r15.putExtra(r14, r1)
            int r14 = android.os.Build.VERSION.SDK_INT
            r0 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r15 = android.app.PendingIntent.getActivity(r9, r6, r15, r0)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            if (r15 != 0) goto L_0x0097
            java.lang.String r14 = "Messages - could not retrieve sender from broadcast, unable to post notification"
            java.lang.Object[] r15 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            com.adobe.mobile.StaticMethods.logDebugFormat(r14, r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            return
        L_0x0097:
            r0 = 11
            r1 = 16
            if (r14 < r0) goto L_0x016c
            java.lang.Class<android.content.BroadcastReceiver> r0 = android.content.BroadcastReceiver.class
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r6 = "android.app.Notification$Builder"
            java.lang.Class r0 = r0.loadClass(r6)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.content.Context> r7 = android.content.Context.class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Constructor r6 = r0.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r6.setAccessible(r3)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            android.content.Context r8 = com.adobe.mobile.StaticMethods.getSharedContext()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r7[r2] = r8     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object r6 = r6.newInstance(r7)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r7 = "setSmallIcon"
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r7 = r0.getDeclaredMethod(r7, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            int r9 = r13.getSmallIcon()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r7.invoke(r6, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            android.graphics.Bitmap r7 = r13.getLargeIcon()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            if (r7 == 0) goto L_0x00f6
            java.lang.String r8 = "setLargeIcon"
            java.lang.Class[] r9 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.graphics.Bitmap> r10 = android.graphics.Bitmap.class
            r9[r2] = r10     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r8 = r0.getDeclaredMethod(r8, r9)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r9[r2] = r7     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r8.invoke(r6, r9)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
        L_0x00f6:
            java.lang.String r7 = "setContentTitle"
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<java.lang.CharSequence> r9 = java.lang.CharSequence.class
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r7 = r0.getDeclaredMethod(r7, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r9 = r13.getAppName()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r7.invoke(r6, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r7 = "setContentText"
            java.lang.Class[] r8 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<java.lang.CharSequence> r9 = java.lang.CharSequence.class
            r8[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r7 = r0.getDeclaredMethod(r7, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r8[r2] = r4     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r7.invoke(r6, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r4 = "setContentIntent"
            java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.app.PendingIntent> r8 = android.app.PendingIntent.class
            r7[r2] = r8     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r4 = r0.getDeclaredMethod(r4, r7)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r7[r2] = r15     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r4.invoke(r6, r7)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r15 = "setAutoCancel"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r4[r2] = r7     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r15 = r0.getDeclaredMethod(r15, r4)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r3)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r4[r2] = r7     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r15.invoke(r6, r4)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            if (r14 < r1) goto L_0x015b
            java.lang.String r14 = "build"
            java.lang.Class[] r15 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r14 = r0.getDeclaredMethod(r14, r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r15 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object r14 = r14.invoke(r6, r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            goto L_0x0169
        L_0x015b:
            java.lang.String r14 = "getNotification"
            java.lang.Class[] r15 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r14 = r0.getDeclaredMethod(r14, r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r15 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object r14 = r14.invoke(r6, r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
        L_0x0169:
            if (r14 != 0) goto L_0x01b4
            return
        L_0x016c:
            android.app.Notification r14 = new android.app.Notification     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r14.<init>()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.app.Notification> r0 = android.app.Notification.class
            java.lang.String r6 = "setLatestEventInfo"
            r7 = 4
            java.lang.Class[] r8 = new java.lang.Class[r7]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.content.Context> r10 = android.content.Context.class
            r8[r2] = r10     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            r8[r3] = r10     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            r11 = 2
            r8[r11] = r10     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.app.PendingIntent> r10 = android.app.PendingIntent.class
            r12 = 3
            r8[r12] = r10     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r6, r8)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r6[r2] = r9     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.String r7 = r13.getAppName()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r6[r3] = r7     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r6[r11] = r4     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r6[r12] = r15     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r0.invoke(r14, r6)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Class<android.app.Notification> r15 = android.app.Notification.class
            java.lang.String r0 = "icon"
            java.lang.reflect.Field r15 = r15.getField(r0)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            int r0 = r13.getSmallIcon()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r15.set(r14, r0)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r14.flags = r1     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
        L_0x01b4:
            java.lang.String r15 = "notification"
            java.lang.Object r15 = r5.getSystemService(r15)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            android.app.NotificationManager r15 = (android.app.NotificationManager) r15     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            java.security.SecureRandom r0 = new java.security.SecureRandom     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r0.<init>()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            int r0 = r0.nextInt()     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            android.app.Notification r14 = (android.app.Notification) r14     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            r15.notify(r0, r14)     // Catch:{ ClassNotFoundException -> 0x01f8, NoSuchMethodException -> 0x01e9, NullContextException -> 0x01da, Exception -> 0x01cb }
            goto L_0x0206
        L_0x01cb:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r14 = r14.getMessage()
            r15[r2] = r14
            java.lang.String r14 = "Messages - unexpected error posting notification (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
            goto L_0x0206
        L_0x01da:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r14 = r14.getMessage()
            r15[r2] = r14
            java.lang.String r14 = "Messages - error posting notification (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
            goto L_0x0206
        L_0x01e9:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r14 = r14.getMessage()
            r15[r2] = r14
            java.lang.String r14 = "Messages - error posting notification, method not found (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
            goto L_0x0206
        L_0x01f8:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r14 = r14.getMessage()
            r15[r2] = r14
            java.lang.String r14 = "Messages - error posting notification, class not found (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
        L_0x0206:
            return
        L_0x0207:
            r14 = move-exception
            java.lang.String r14 = r14.getMessage()
            java.lang.Object[] r15 = new java.lang.Object[r2]
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
            return
        L_0x0212:
            r14 = move-exception
            java.lang.String r14 = r14.getMessage()
            java.lang.Object[] r15 = new java.lang.Object[r2]
            com.adobe.mobile.StaticMethods.logErrorFormat(r14, r15)
            return
        L_0x021d:
            r14 = move-exception
            java.lang.Object[] r15 = new java.lang.Object[r3]
            java.lang.String r14 = r14.getMessage()
            r15[r2] = r14
            java.lang.String r14 = "Messages - unable to load message from local notification (%s)"
            com.adobe.mobile.StaticMethods.logDebugFormat(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MessageNotificationHandler.onReceive(android.content.Context, android.content.Intent):void");
    }

    private String getAppName() {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = StaticMethods.getSharedContext().getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(StaticMethods.getSharedContext().getPackageName(), 0)) == null || packageManager.getApplicationLabel(applicationInfo) == null) {
                return "";
            }
            return (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            StaticMethods.logDebugFormat("Messages - unable to retrieve app name for local notification (%s)", e.getMessage());
            return "";
        } catch (StaticMethods.NullContextException e2) {
            StaticMethods.logDebugFormat("Messages - unable to get app name (%s)", e2.getMessage());
            return "";
        }
    }

    private int getSmallIcon() {
        if (Messages.getSmallIconResourceId() != -1) {
            return Messages.getSmallIconResourceId();
        }
        return 17301651;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0069, code lost:
        r2 = com.adobe.mobile.StaticMethods.getSharedContext().getPackageManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getLargeIcon() throws java.lang.ClassNotFoundException, java.lang.NoSuchMethodException, com.adobe.mobile.StaticMethods.NullContextException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r10 = this;
            int r0 = com.adobe.mobile.Messages.getLargeIconResourceId()
            r1 = 0
            r2 = -1
            if (r0 == r2) goto L_0x005f
            android.content.Context r2 = com.adobe.mobile.StaticMethods.getSharedContext()
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 20
            java.lang.String r5 = "getDrawable"
            r6 = 0
            r7 = 1
            if (r3 <= r4) goto L_0x0040
            java.lang.Class<android.content.res.Resources> r3 = android.content.res.Resources.class
            r4 = 2
            java.lang.Class[] r8 = new java.lang.Class[r4]
            java.lang.Class r9 = java.lang.Integer.TYPE
            r8[r6] = r9
            java.lang.Class<android.content.res.Resources$Theme> r9 = android.content.res.Resources.Theme.class
            r8[r7] = r9
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r5, r8)
            android.content.res.Resources r5 = r2.getResources()
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4[r6] = r0
            android.content.res.Resources$Theme r0 = r2.getTheme()
            r4[r7] = r0
            java.lang.Object r0 = r3.invoke(r5, r4)
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            goto L_0x0079
        L_0x0040:
            java.lang.Class<android.content.res.Resources> r3 = android.content.res.Resources.class
            java.lang.Class[] r4 = new java.lang.Class[r7]
            java.lang.Class r8 = java.lang.Integer.TYPE
            r4[r6] = r8
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r5, r4)
            android.content.res.Resources r2 = r2.getResources()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4[r6] = r0
            java.lang.Object r0 = r3.invoke(r2, r4)
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            goto L_0x0079
        L_0x005f:
            android.content.Context r0 = com.adobe.mobile.StaticMethods.getSharedContext()
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()
            if (r0 == 0) goto L_0x0078
            android.content.Context r2 = com.adobe.mobile.StaticMethods.getSharedContext()
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            if (r2 == 0) goto L_0x0078
            android.graphics.drawable.Drawable r0 = r2.getApplicationIcon(r0)
            goto L_0x0079
        L_0x0078:
            r0 = r1
        L_0x0079:
            if (r0 == 0) goto L_0x008a
            boolean r1 = r0 instanceof android.graphics.drawable.BitmapDrawable
            if (r1 == 0) goto L_0x0086
            android.graphics.drawable.BitmapDrawable r0 = (android.graphics.drawable.BitmapDrawable) r0
            android.graphics.Bitmap r1 = r0.getBitmap()
            goto L_0x008a
        L_0x0086:
            android.graphics.Bitmap r1 = r10.getBitmapFromDrawable(r0)
        L_0x008a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MessageNotificationHandler.getLargeIcon():android.graphics.Bitmap");
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }
}
