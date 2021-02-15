package com.mnet.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

public abstract class j {
    public abstract <T> T a(Class<T> cls);

    public static j a() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get((Object) null);
            final Method method = cls.getMethod("allocateInstance", new Class[]{Class.class});
            return new j() {
                public <T> T a(Class<T> cls) {
                    j.c(cls);
                    return method.invoke(obj, new Object[]{cls});
                }
            };
        } catch (Exception unused) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                declaredMethod.setAccessible(true);
                final int intValue = ((Integer) declaredMethod.invoke((Object) null, new Object[]{Object.class})).intValue();
                final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, new Class[]{Class.class, Integer.TYPE});
                declaredMethod2.setAccessible(true);
                return new j() {
                    public <T> T a(Class<T> cls) {
                        j.c(cls);
                        return declaredMethod2.invoke((Object) null, new Object[]{cls, Integer.valueOf(intValue)});
                    }
                };
            } catch (Exception unused2) {
                try {
                    final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, new Class[]{Class.class, Class.class});
                    declaredMethod3.setAccessible(true);
                    return new j() {
                        public <T> T a(Class<T> cls) {
                            j.c(cls);
                            return declaredMethod3.invoke((Object) null, new Object[]{cls, Object.class});
                        }
                    };
                } catch (Exception unused3) {
                    return new j() {
                        public <T> T a(Class<T> cls) {
                            throw new UnsupportedOperationException("Cannot allocate " + cls);
                        }
                    };
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void c(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        } else if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }
}
