package org.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class PercSerializationInstantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod;
    private Object[] typeArgs;

    public PercSerializationInstantiator(Class<T> cls) {
        Class<T> cls2 = cls;
        while (Serializable.class.isAssignableFrom(cls2)) {
            cls2 = cls2.getSuperclass();
        }
        try {
            Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("noArgConstruct", new Class[]{Class.class, Object.class, Class.forName("COM.newmonics.PercClassLoader.Method")});
            this.newInstanceMethod = declaredMethod;
            declaredMethod.setAccessible(true);
            Object invoke = Class.forName("COM.newmonics.PercClassLoader.PercClass").getDeclaredMethod("getPercClass", new Class[]{Class.class}).invoke((Object) null, new Object[]{cls2});
            this.typeArgs = new Object[]{cls2, cls, invoke.getClass().getDeclaredMethod("findMethod", new Class[]{String.class}).invoke(invoke, new Object[]{"<init>()V"})};
        } catch (ClassNotFoundException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (NoSuchMethodException e2) {
            throw new ObjenesisException((Throwable) e2);
        } catch (InvocationTargetException e3) {
            throw new ObjenesisException((Throwable) e3);
        } catch (IllegalAccessException e4) {
            throw new ObjenesisException((Throwable) e4);
        }
    }

    public T newInstance() {
        try {
            return this.newInstanceMethod.invoke((Object) null, this.typeArgs);
        } catch (IllegalAccessException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (InvocationTargetException e2) {
            throw new ObjenesisException((Throwable) e2);
        }
    }
}
