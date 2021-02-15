package org.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class SunReflectionFactorySerializationInstantiator<T> implements ObjectInstantiator<T> {
    private final Constructor<T> mungedConstructor;

    public SunReflectionFactorySerializationInstantiator(Class<T> cls) {
        try {
            Constructor<T> newConstructorForSerialization = SunReflectionFactoryHelper.newConstructorForSerialization(cls, SerializationInstantiatorHelper.getNonSerializableSuperClass(cls).getDeclaredConstructor((Class[]) null));
            this.mungedConstructor = newConstructorForSerialization;
            newConstructorForSerialization.setAccessible(true);
        } catch (NoSuchMethodException unused) {
            throw new ObjenesisException((Throwable) new NotSerializableException(cls + " has no suitable superclass constructor"));
        }
    }

    public T newInstance() {
        try {
            return this.mungedConstructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
