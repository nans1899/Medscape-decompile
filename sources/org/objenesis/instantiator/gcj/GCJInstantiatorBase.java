package org.objenesis.instantiator.gcj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

public abstract class GCJInstantiatorBase<T> implements ObjectInstantiator<T> {
    static ObjectInputStream dummyStream;
    static Method newObjectMethod;
    protected final Class<T> type;

    private static class DummyStream extends ObjectInputStream {
    }

    public abstract T newInstance();

    private static void initialize() {
        if (newObjectMethod == null) {
            try {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newObject", new Class[]{Class.class, Class.class});
                newObjectMethod = declaredMethod;
                declaredMethod.setAccessible(true);
                dummyStream = new DummyStream();
            } catch (RuntimeException e) {
                throw new ObjenesisException((Throwable) e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException((Throwable) e2);
            } catch (IOException e3) {
                throw new ObjenesisException((Throwable) e3);
            }
        }
    }

    public GCJInstantiatorBase(Class<T> cls) {
        this.type = cls;
        initialize();
    }
}
