package mnetinternal;

import android.app.ActionBar;
import android.app.Activity;
import java.lang.reflect.Method;

public final class bq {
    private static Method a = bu.b(Activity.class, "getActionBar", new Class[0]);
    private static Method b = bu.b(ActionBar.class, "getTitle", new Class[0]);
    private static Class c = bu.a("androidx.appcompat.app.AppCompatActivity");
    private static Method d = bu.b(c, "getSupportActionBar", new Class[0]);
    private static Method e = bu.b(f, "getTitle", new Class[0]);
    private static Class f = bu.a("androidx.appcompat.app.ActionBar");

    private static String b(Activity activity) {
        try {
            if (a != null) {
                if (b != null) {
                    Object invoke = a.invoke(activity, new Object[0]);
                    if (invoke != null) {
                        return (String) b.invoke(invoke, new Object[0]);
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            bi.a("##ActionBarUtils", e2.getMessage(), e2);
        }
    }

    private static String c(Activity activity) {
        try {
            if (d != null) {
                if (e != null) {
                    Object invoke = d.invoke(activity, new Object[0]);
                    if (invoke != null) {
                        return (String) e.invoke(invoke, new Object[0]);
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e2) {
            bi.a("##ActionBarUtils", e2.getMessage(), e2);
        }
    }

    public static String a(Activity activity) {
        Class cls = c;
        if (cls == null || !cls.isInstance(activity)) {
            return b(activity);
        }
        return c(activity);
    }
}
