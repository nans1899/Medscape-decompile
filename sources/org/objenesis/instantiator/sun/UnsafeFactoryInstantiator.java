package org.objenesis.instantiator.sun;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

@Instantiator(Typology.STANDARD)
public class UnsafeFactoryInstantiator<T> implements ObjectInstantiator<T> {
    private final Class<T> type;
    private final Unsafe unsafe = UnsafeUtils.getUnsafe();

    public UnsafeFactoryInstantiator(Class<T> cls) {
        this.type = cls;
    }

    public T newInstance() {
        try {
            return this.type.cast(this.unsafe.allocateInstance(this.type));
        } catch (InstantiationException e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
