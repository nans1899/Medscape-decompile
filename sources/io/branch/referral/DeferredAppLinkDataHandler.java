package io.branch.referral;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DeferredAppLinkDataHandler {
    private static final String NATIVE_URL_KEY = "com.facebook.platform.APPLINK_NATIVE_URL";

    public interface AppLinkFetchEvents {
        void onAppLinkFetchFinished(String str);
    }

    DeferredAppLinkDataHandler() {
    }

    public static Boolean fetchDeferredAppLinkData(Context context, final AppLinkFetchEvents appLinkFetchEvents) {
        boolean z = false;
        try {
            Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            final Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
            Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
            Method method = cls.getMethod("fetchDeferredAppLinkData", new Class[]{Context.class, String.class, cls2});
            AnonymousClass1 r7 = new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (!method.getName().equals("onDeferredAppLinkDataFetched") || objArr[0] == null) {
                        AppLinkFetchEvents appLinkFetchEvents = appLinkFetchEvents;
                        if (appLinkFetchEvents != null) {
                            appLinkFetchEvents.onAppLinkFetchFinished((String) null);
                        }
                    } else {
                        Bundle cast = Bundle.class.cast(cls.getMethod("getArgumentBundle", new Class[0]).invoke(cls.cast(objArr[0]), new Object[0]));
                        String string = cast != null ? cast.getString("com.facebook.platform.APPLINK_NATIVE_URL") : null;
                        AppLinkFetchEvents appLinkFetchEvents2 = appLinkFetchEvents;
                        if (appLinkFetchEvents2 != null) {
                            appLinkFetchEvents2.onAppLinkFetchFinished(string);
                        }
                    }
                    return null;
                }
            };
            Object newProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, r7);
            String string = context.getString(context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName()));
            if (!TextUtils.isEmpty(string)) {
                method.invoke((Object) null, new Object[]{context, string, newProxyInstance});
                z = true;
            }
        } catch (Throwable unused) {
        }
        return Boolean.valueOf(z);
    }
}
