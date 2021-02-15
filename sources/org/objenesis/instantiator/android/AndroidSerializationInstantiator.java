package org.objenesis.instantiator.android;

import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class AndroidSerializationInstantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod = getNewInstanceMethod();
    private final ObjectStreamClass objectStreamClass;
    private final Class<T> type;

    public AndroidSerializationInstantiator(Class<T> cls) {
        this.type = cls;
        try {
            try {
                this.objectStreamClass = (ObjectStreamClass) ObjectStreamClass.class.getMethod("lookupAny", new Class[]{Class.class}).invoke((Object) null, new Object[]{cls});
            } catch (IllegalAccessException e) {
                throw new ObjenesisException((Throwable) e);
            } catch (InvocationTargetException e2) {
                throw new ObjenesisException((Throwable) e2);
            }
        } catch (NoSuchMethodException e3) {
            throw new ObjenesisException((Throwable) e3);
        }
    }

    public T newInstance() {
        try {
            return this.type.cast(this.newInstanceMethod.invoke(this.objectStreamClass, new Object[]{this.type}));
        } catch (IllegalAccessException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (IllegalArgumentException e2) {
            throw new ObjenesisException((Throwable) e2);
        } catch (InvocationTargetException e3) {
            throw new ObjenesisException((Throwable) e3);
        }
    }

    private static Method getNewInstanceMethod() {
        try {
            Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, new Class[]{Class.class});
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (RuntimeException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (NoSuchMethodException e2) {
            throw new ObjenesisException((Throwable) e2);
        }
    }
}
