package org.objenesis.strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

public class SingleInstantiatorStrategy implements InstantiatorStrategy {
    private Constructor<?> constructor;

    public <T extends ObjectInstantiator<?>> SingleInstantiatorStrategy(Class<T> cls) {
        try {
            this.constructor = cls.getConstructor(new Class[]{Class.class});
        } catch (NoSuchMethodException e) {
            throw new ObjenesisException((Throwable) e);
        }
    }

    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        try {
            return (ObjectInstantiator) this.constructor.newInstance(new Object[]{cls});
        } catch (InstantiationException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException((Throwable) e2);
        } catch (InvocationTargetException e3) {
            throw new ObjenesisException((Throwable) e3);
        }
    }
}
