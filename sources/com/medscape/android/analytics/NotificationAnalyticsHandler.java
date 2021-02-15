package com.medscape.android.analytics;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.core.app.NotificationManagerCompat;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.notifications.INotificationProvider;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J&\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ.\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\b\u001a\u00020\tH\u0002J.\u0010\u0012\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\b\u001a\u00020\tH\u0002J\u001a\u0010\u0013\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J.\u0010\u0014\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/analytics/NotificationAnalyticsHandler;", "Lcom/medscape/android/notifications/INotificationProvider;", "()V", "clearAllTags", "", "context", "Landroid/content/Context;", "closeSession", "activity", "Landroid/app/Activity;", "getProviderId", "", "logEvent", "eventName", "openSession", "tagBrazeUser", "map", "", "tagFirebaseUser", "tagLoggedInUserAttributes", "tagPlatformsUser", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NotificationAnalyticsHandler.kt */
public final class NotificationAnalyticsHandler implements INotificationProvider {
    public static final NotificationAnalyticsHandler INSTANCE = new NotificationAnalyticsHandler();

    private NotificationAnalyticsHandler() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void tagLoggedInUserAttributes(android.content.Context r14, android.app.Activity r15) {
        /*
            r13 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            if (r14 == 0) goto L_0x01bc
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            com.medscape.android.homescreen.user.UserProfileProvider r1 = com.medscape.android.homescreen.user.UserProfileProvider.INSTANCE
            com.medscape.android.parser.model.UserProfile r1 = r1.getUserProfile()
            com.medscape.android.auth.AuthenticationManager r2 = com.medscape.android.auth.AuthenticationManager.getInstance(r14)
            if (r2 == 0) goto L_0x0021
            java.lang.String r2 = r2.getMaskedGuid()
            if (r2 == 0) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            java.lang.String r2 = ""
        L_0x0023:
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L_0x0030
            r3 = 1
            goto L_0x0031
        L_0x0030:
            r3 = 0
        L_0x0031:
            if (r3 != 0) goto L_0x0038
            java.lang.String r3 = "userid"
            r0.put(r3, r2)
        L_0x0038:
            java.lang.String r2 = java.lang.String.valueOf(r5)
            java.lang.String r3 = "loggedin"
            r0.put(r3, r2)
            java.lang.String r2 = "medpulsePushEnabled"
            java.lang.String r3 = com.webmd.wbmdproffesionalauthentication.keychain.KeychainManager.getValueFromKeychain(r14, r2)
            java.lang.String r6 = "medpulsePushDisabled"
            java.lang.String r7 = "medpulsePush"
            if (r3 == 0) goto L_0x005b
            java.lang.String r8 = "true"
            boolean r3 = kotlin.text.StringsKt.equals(r3, r8, r5)     // Catch:{ Exception -> 0x0059 }
            if (r3 == 0) goto L_0x005b
            r0.put(r7, r2)     // Catch:{ Exception -> 0x0059 }
            goto L_0x0065
        L_0x0059:
            r2 = move-exception
            goto L_0x005f
        L_0x005b:
            r0.put(r7, r6)     // Catch:{ Exception -> 0x0059 }
            goto L_0x0065
        L_0x005f:
            r2.printStackTrace()
            r0.put(r7, r6)
        L_0x0065:
            if (r1 == 0) goto L_0x0176
            java.lang.String r2 = r1.getCountryId()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x0078
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            r2 = 0
            goto L_0x0079
        L_0x0078:
            r2 = 1
        L_0x0079:
            java.lang.String r3 = "null cannot be cast to non-null type java.lang.String"
            java.lang.String r6 = "(this as java.lang.String).toLowerCase()"
            if (r2 != 0) goto L_0x009d
            java.lang.String r2 = r1.getCountryId()
            java.lang.String r7 = "userProfile.countryId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)
            if (r2 == 0) goto L_0x0097
            java.lang.String r2 = r2.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r7 = "ct"
            r0.put(r7, r2)
            goto L_0x009d
        L_0x0097:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r3)
            throw r14
        L_0x009d:
            java.lang.String r2 = r1.getStateId()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x00ae
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00ac
            goto L_0x00ae
        L_0x00ac:
            r2 = 0
            goto L_0x00af
        L_0x00ae:
            r2 = 1
        L_0x00af:
            if (r2 != 0) goto L_0x00cf
            java.lang.String r2 = r1.getStateId()
            java.lang.String r7 = "userProfile.stateId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)
            if (r2 == 0) goto L_0x00c9
            java.lang.String r2 = r2.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r7 = "st"
            r0.put(r7, r2)
            goto L_0x00cf
        L_0x00c9:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r3)
            throw r14
        L_0x00cf:
            java.lang.String r2 = r1.getProfession()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x00e0
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00de
            goto L_0x00e0
        L_0x00de:
            r2 = 0
            goto L_0x00e1
        L_0x00e0:
            r2 = 1
        L_0x00e1:
            if (r2 != 0) goto L_0x0101
            java.lang.String r2 = r1.getProfession()
            java.lang.String r7 = "userProfile.profession"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)
            if (r2 == 0) goto L_0x00fb
            java.lang.String r2 = r2.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r7 = "profid"
            r0.put(r7, r2)
            goto L_0x0101
        L_0x00fb:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r3)
            throw r14
        L_0x0101:
            java.lang.String r2 = r1.getSpecialty()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x010f
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0110
        L_0x010f:
            r4 = 1
        L_0x0110:
            if (r4 != 0) goto L_0x0130
            java.lang.String r2 = r1.getSpecialty()
            java.lang.String r4 = "userProfile.specialty"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            if (r2 == 0) goto L_0x012a
            java.lang.String r2 = r2.toLowerCase()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r3 = "spid"
            r0.put(r3, r2)
            goto L_0x0130
        L_0x012a:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r3)
            throw r14
        L_0x0130:
            java.util.HashMap r2 = r1.getTidMap()
            if (r2 == 0) goto L_0x0176
            java.util.HashMap r1 = r1.getTidMap()
            java.lang.String r2 = "2001"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = ","
            if (r1 == 0) goto L_0x0156
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.String[] r4 = new java.lang.String[]{r2}
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (java.lang.String[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)
            goto L_0x0157
        L_0x0156:
            r1 = 0
        L_0x0157:
            if (r1 == 0) goto L_0x0176
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x0176
            r4 = r1
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            r5 = r2
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 62
            r12 = 0
            java.lang.String r1 = kotlin.collections.CollectionsKt.joinToString$default(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            java.lang.String r2 = "tids"
            r0.put(r2, r1)
        L_0x0176:
            java.lang.String r1 = com.medscape.android.util.Util.getApplicationVersion(r14)
            java.lang.String r2 = "Util.getApplicationVersion(context)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r2 = "appver"
            r0.put(r2, r1)
            java.lang.String r1 = "device"
            java.lang.String r2 = "android"
            r0.put(r1, r2)
            boolean r1 = com.medscape.android.util.Util.isNotificationChannelEnabled()     // Catch:{ Exception -> 0x01af }
            java.lang.String r2 = "Appboy"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01af }
            r3.<init>()     // Catch:{ Exception -> 0x01af }
            java.lang.String r4 = "tagLoggedInUserAttributes: "
            r3.append(r4)     // Catch:{ Exception -> 0x01af }
            r3.append(r1)     // Catch:{ Exception -> 0x01af }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01af }
            android.util.Log.i(r2, r3)     // Catch:{ Exception -> 0x01af }
            java.lang.String r2 = "medscapePushEnabled"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x01af }
            com.webmd.wbmdproffesionalauthentication.keychain.KeychainManager.saveToKeychain(r14, r2, r1)     // Catch:{ Exception -> 0x01af }
            goto L_0x01b3
        L_0x01af:
            r1 = move-exception
            r1.printStackTrace()
        L_0x01b3:
            r13.tagFirebaseUser(r14, r0, r15)
            r13.tagBrazeUser(r14, r0, r15)
            r13.tagPlatformsUser(r14, r0, r15)
        L_0x01bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.analytics.NotificationAnalyticsHandler.tagLoggedInUserAttributes(android.content.Context, android.app.Activity):void");
    }

    private final void tagFirebaseUser(Context context, Map<String, String> map, Activity activity) {
        if (context != null) {
            PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(activity, false, true);
            platformRouteDispatcher.routeUserId(map.get(INotificationProvider.USER_TAG_USER_ID));
            platformRouteDispatcher.routeUserAttribute(INotificationProvider.USER_TAG_KEY_TIDS, map.get(INotificationProvider.USER_TAG_KEY_TIDS));
        }
    }

    /* JADX WARNING: type inference failed for: r13v6, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void tagBrazeUser(android.content.Context r12, java.util.Map<java.lang.String, java.lang.String> r13, android.app.Activity r14) {
        /*
            r11 = this;
            if (r12 == 0) goto L_0x0078
            com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher r0 = new com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher
            r1 = 1
            r2 = 0
            r0.<init>(r14, r1, r2)
            com.appboy.Appboy r14 = com.appboy.Appboy.getInstance(r12)
            java.lang.String r3 = "Appboy.getInstance(context)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r3)
            com.appboy.AppboyUser r14 = r14.getCurrentUser()
            java.lang.String r3 = "userid"
            java.lang.Object r3 = r13.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            if (r3 == 0) goto L_0x003c
            if (r14 == 0) goto L_0x0028
            java.lang.String r14 = r14.getUserId()
            goto L_0x0029
        L_0x0028:
            r14 = r4
        L_0x0029:
            boolean r14 = kotlin.text.StringsKt.equals(r3, r14, r1)
            if (r14 != 0) goto L_0x003c
            androidx.core.app.NotificationManagerCompat r12 = androidx.core.app.NotificationManagerCompat.from(r12)
            boolean r12 = r12.areNotificationsEnabled()
            if (r12 == 0) goto L_0x003c
            r0.routeUserId(r3)
        L_0x003c:
            java.lang.String r12 = "tids"
            java.lang.Object r14 = r13.get(r12)
            if (r14 == 0) goto L_0x0078
            java.lang.Object r13 = r13.get(r12)
            java.lang.String r13 = (java.lang.String) r13
            if (r13 == 0) goto L_0x0075
            r5 = r13
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String r13 = ","
            java.lang.String[] r6 = new java.lang.String[]{r13}
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            java.util.List r13 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r5, (java.lang.String[]) r6, (boolean) r7, (int) r8, (int) r9, (java.lang.Object) r10)
            if (r13 == 0) goto L_0x0075
            java.util.Collection r13 = (java.util.Collection) r13
            java.lang.String[] r14 = new java.lang.String[r2]
            java.lang.Object[] r13 = r13.toArray(r14)
            if (r13 == 0) goto L_0x006d
            r4 = r13
            java.lang.String[] r4 = (java.lang.String[]) r4
            goto L_0x0075
        L_0x006d:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException
            java.lang.String r13 = "null cannot be cast to non-null type kotlin.Array<T>"
            r12.<init>(r13)
            throw r12
        L_0x0075:
            r0.routeUserAttribute((java.lang.String) r12, (java.lang.String[]) r4)
        L_0x0078:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.analytics.NotificationAnalyticsHandler.tagBrazeUser(android.content.Context, java.util.Map, android.app.Activity):void");
    }

    private final void tagPlatformsUser(Context context, Map<String, String> map, Activity activity) {
        PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(activity);
        platformRouteDispatcher.routeUserAttribute(INotificationProvider.USER_TAG_USER_ID, map.get(INotificationProvider.USER_TAG_USER_ID));
        platformRouteDispatcher.routeUserAttribute(INotificationProvider.USER_TAG_KEY_LOGGEDIN, String.valueOf(true));
        platformRouteDispatcher.routeUserAttribute(INotificationProvider.USER_TAG_KEY_MEDPULSEPUSH, map.get(INotificationProvider.USER_TAG_KEY_MEDPULSEPUSH));
        platformRouteDispatcher.routeUserAttribute("st", map.get("st"));
        platformRouteDispatcher.routeUserAttribute("profid", map.get("profid"));
        platformRouteDispatcher.routeUserAttribute("spid", map.get("spid"));
        platformRouteDispatcher.routeUserAttribute("ct", map.get("ct"));
        platformRouteDispatcher.routeUserAttribute(INotificationProvider.USER_TAG_KEY_APPVER, map.get(INotificationProvider.USER_TAG_KEY_APPVER));
        platformRouteDispatcher.routeUserAttribute("device", map.get("android"));
    }

    public void logEvent(Context context, String str, Activity activity) {
        if (context != null && str != null) {
            new PlatformRouteDispatcher(activity).routeEvent(str);
        }
    }

    public void clearAllTags(Context context) {
        try {
            if (context != null) {
                PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher((Activity) context);
                platformRouteDispatcher.unrouteUserAttribute("ct");
                platformRouteDispatcher.unrouteUserAttribute("st");
                platformRouteDispatcher.unrouteUserAttribute("profid");
                platformRouteDispatcher.unrouteUserAttribute("spid");
                platformRouteDispatcher.unrouteUserAttribute(INotificationProvider.USER_TAG_KEY_TIDS);
                platformRouteDispatcher.unrouteUserAttribute(INotificationProvider.USER_TAG_KEY_LOGGEDIN);
                platformRouteDispatcher.unrouteUserAttribute(INotificationProvider.USER_TAG_KEY_MEDPULSEPUSH);
                platformRouteDispatcher.unrouteUserAttribute(INotificationProvider.USER_TAG_KEY_APPVER);
                platformRouteDispatcher.unrouteUserAttribute("device");
                Appboy.getInstance(context).changeUser((String) null);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.e(Constants.APPBOY, message);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r1 = (r1 = r1.getCurrentUser()).getUserId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getProviderId(android.content.Context r1) {
        /*
            r0 = this;
            com.appboy.Appboy r1 = com.appboy.Appboy.getInstance(r1)
            if (r1 == 0) goto L_0x0013
            com.appboy.AppboyUser r1 = r1.getCurrentUser()
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = r1.getUserId()
            if (r1 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            java.lang.String r1 = "Not Available"
        L_0x0015:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.analytics.NotificationAnalyticsHandler.getProviderId(android.content.Context):java.lang.String");
    }

    public final void openSession(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            if (NotificationManagerCompat.from(activity).areNotificationsEnabled() && AccountProvider.isUserLoggedIn(activity)) {
                Appboy instance = Appboy.getInstance(activity);
                Intrinsics.checkNotNullExpressionValue(instance, "Appboy.getInstance(activity)");
                if (instance.getCurrentUser() == null) {
                    Appboy instance2 = Appboy.getInstance(activity);
                    AuthenticationManager instance3 = AuthenticationManager.getInstance(activity);
                    Intrinsics.checkNotNullExpressionValue(instance3, "AuthenticationManager.getInstance(activity)");
                    instance2.changeUser(instance3.getMaskedGuid());
                }
                Appboy.getInstance(activity).openSession(activity);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.e(Constants.APPBOY, message);
            }
        }
    }

    public final void closeSession(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            if (NotificationManagerCompat.from(activity).areNotificationsEnabled() && AccountProvider.isUserLoggedIn(activity)) {
                Appboy.getInstance(activity).closeSession(activity);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.e(Constants.APPBOY, message);
            }
        }
    }
}
