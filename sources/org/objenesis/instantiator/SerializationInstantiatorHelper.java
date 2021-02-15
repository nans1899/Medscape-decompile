package org.objenesis.instantiator;

import java.io.Serializable;

public class SerializationInstantiatorHelper {
    public static <T> Class<? super T> getNonSerializableSuperClass(Class<T> cls) {
        Class<? super T> superclass;
        do {
            boolean isAssignableFrom = Serializable.class.isAssignableFrom(r1);
            Class<? super T> cls2 = cls;
            if (!isAssignableFrom) {
                return cls2;
            }
            superclass = cls2.getSuperclass();
            cls2 = superclass;
        } while (superclass != null);
        throw new Error("Bad class hierarchy: No non-serializable parents");
    }
}
