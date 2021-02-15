package mnetinternal;

import java.lang.reflect.Method;
import net.media.android.bidder.base.logging.Logger;

final class ce {
    private static volatile ce a;
    private ClassLoader b = getClass().getClassLoader();

    private ce() {
    }

    public static ce a() {
        ce ceVar = a;
        if (ceVar == null) {
            synchronized (ce.class) {
                ceVar = a;
                if (ceVar == null) {
                    ceVar = new ce();
                    a = ceVar;
                }
            }
        }
        return ceVar;
    }

    public static Object a(Object obj, String str, Class[] clsArr, Object... objArr) {
        return a().b(obj, str, clsArr, objArr);
    }

    private Method a(String str, Class cls, Class[] clsArr) {
        boolean z;
        Method method;
        try {
            method = cls.getMethod(str, clsArr);
            z = false;
        } catch (NoSuchMethodException unused) {
            z = true;
            method = null;
        }
        return z ? cls.getDeclaredMethod(str, clsArr) : method;
    }

    private synchronized Object b(Object obj, String str, Class[] clsArr, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method a2 = a(str, obj.getClass(), clsArr);
            a2.setAccessible(true);
            return a2.invoke(obj, objArr);
        } catch (Exception e) {
            Logger.error("##Reflect", "reflection error", e);
            return null;
        }
    }
}
