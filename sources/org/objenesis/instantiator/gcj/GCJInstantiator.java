package org.objenesis.instantiator.gcj;

import java.lang.reflect.InvocationTargetException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class GCJInstantiator<T> extends GCJInstantiatorBase<T> {
    public GCJInstantiator(Class<T> cls) {
        super(cls);
    }

    public T newInstance() {
        try {
            return this.type.cast(newObjectMethod.invoke(dummyStream, new Object[]{this.type, Object.class}));
        } catch (RuntimeException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException((Throwable) e2);
        } catch (InvocationTargetException e3) {
            throw new ObjenesisException((Throwable) e3);
        }
    }
}
