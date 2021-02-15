package mnetinternal;

import android.os.Build;
import android.view.Menu;
import android.view.View;
import android.widget.ActionMenuView;
import java.lang.reflect.Method;

public final class br {
    private static Class a;
    private static Method b;

    static {
        Class a2 = bu.a("androidx.appcompat.widget.ActionMenuView");
        a = a2;
        Method b2 = bu.b(a2, "peekMenu", new Class[0]);
        b = b2;
        if (b2 != null) {
            b2.setAccessible(true);
        }
    }

    public static boolean a(View view) {
        if (Build.VERSION.SDK_INT >= 21 && (view instanceof ActionMenuView)) {
            return true;
        }
        Class cls = a;
        if (cls == null || !cls.isInstance(view)) {
            return false;
        }
        return true;
    }

    public static int b(View view) {
        Object invoke;
        bi.a("##ActionMenuViewUtils##", "getCount()");
        Menu menu = null;
        try {
            if (Build.VERSION.SDK_INT >= 21 && (view instanceof ActionMenuView)) {
                menu = ((ActionMenuView) view).getMenu();
            }
            if (!(a == null || !a.isInstance(view) || b == null || (invoke = b.invoke(view, new Object[0])) == null)) {
                bi.a("##ActionMenuViewUtils##", "casting to menu");
                menu = (Menu) invoke;
            }
            if (menu != null) {
                return menu.size();
            }
        } catch (Exception e) {
            bi.a("##ActionMenuViewUtils##", e.getMessage(), e);
        }
        return 0;
    }
}
