package mnetinternal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class bu {
    static Method a(Class cls, String str, Class... clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            return a(cls.getSuperclass(), str, clsArr);
        }
    }

    static Method[] a(Class cls) {
        if (cls == null) {
            return null;
        }
        return cls.getDeclaredMethods();
    }

    static Method b(Class cls, String str, Class... clsArr) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            bi.a("##ReflectionUtils", e.getMessage(), e);
            return null;
        }
    }

    public static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            bi.a("##ReflectionUtils", e.getMessage(), e);
            return null;
        }
    }

    static Field a(Class cls, String str) {
        if (!(cls == null || str == null || str.isEmpty())) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException e) {
                bi.a("##ReflectionUtils", e.getMessage(), e);
            }
        }
        return null;
    }
}
