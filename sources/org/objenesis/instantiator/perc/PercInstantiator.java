package org.objenesis.instantiator.perc;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.STANDARD)
public class PercInstantiator<T> implements ObjectInstantiator<T> {
    private final Method newInstanceMethod;
    private final Object[] typeArgs;

    public PercInstantiator(Class<T> cls) {
        Object[] objArr = {null, Boolean.FALSE};
        this.typeArgs = objArr;
        objArr[0] = cls;
        Class<ObjectInputStream> cls2 = ObjectInputStream.class;
        try {
            Method declaredMethod = cls2.getDeclaredMethod(TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, new Class[]{Class.class, Boolean.TYPE});
            this.newInstanceMethod = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (RuntimeException e) {
            throw new ObjenesisException((Throwable) e);
        } catch (NoSuchMethodException e2) {
            throw new ObjenesisException((Throwable) e2);
        }
    }

    public T newInstance() {
        try {
            return this.newInstanceMethod.invoke((Object) null, this.typeArgs);
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
