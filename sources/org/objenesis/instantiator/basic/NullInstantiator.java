package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.NOT_COMPLIANT)
public class NullInstantiator<T> implements ObjectInstantiator<T> {
    public T newInstance() {
        return null;
    }

    public NullInstantiator(Class<T> cls) {
    }
}
