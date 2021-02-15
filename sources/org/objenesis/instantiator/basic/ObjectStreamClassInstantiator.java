package org.objenesis.instantiator.basic;

import java.io.ObjectStreamClass;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class ObjectStreamClassInstantiator<T> implements ObjectInstantiator<T> {
    private static Method newInstanceMethod;
    private final ObjectStreamClass objStreamClass;

    private static void initialize() {
        if (newInstanceMethod == null) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, new Class[0]);
                newInstanceMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (RuntimeException e) {
                throw new ObjenesisException((Throwable) e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException((Throwable) e2);
            }
        }
    }

    public ObjectStreamClassInstantiator(Class<T> cls) {
        initialize();
        this.objStreamClass = ObjectStreamClass.lookup(cls);
    }

    public T newInstance() {
        try {
            return newInstanceMethod.invoke(this.objStreamClass, new Object[0]);
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
