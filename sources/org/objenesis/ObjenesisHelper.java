package org.objenesis;

import java.io.Serializable;
import org.objenesis.instantiator.ObjectInstantiator;

public final class ObjenesisHelper {
    private static final Objenesis OBJENESIS_SERIALIZER = new ObjenesisSerializer();
    private static final Objenesis OBJENESIS_STD = new ObjenesisStd();

    private ObjenesisHelper() {
    }

    public static <T> T newInstance(Class<T> cls) {
        return OBJENESIS_STD.newInstance(cls);
    }

    public static <T extends Serializable> T newSerializableInstance(Class<T> cls) {
        return (Serializable) OBJENESIS_SERIALIZER.newInstance(cls);
    }

    public static <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls) {
        return OBJENESIS_STD.getInstantiatorOf(cls);
    }

    public static <T extends Serializable> ObjectInstantiator<T> getSerializableObjectInstantiatorOf(Class<T> cls) {
        return OBJENESIS_SERIALIZER.getInstantiatorOf(cls);
    }
}
