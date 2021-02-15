package org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class ConstructorInstantiator<T> implements ObjectInstantiator<T> {
    protected Constructor<T> constructor;

    public ConstructorInstantiator(Class<T> cls) {
        try {
            this.constructor = cls.getDeclaredConstructor((Class[]) null);
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }

    public T newInstance() {
        try {
            return this.constructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
