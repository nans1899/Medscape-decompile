package org.objenesis.instantiator.gcj;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
public class GCJSerializationInstantiator<T> extends GCJInstantiatorBase<T> {
    private Class<? super T> superType;

    public GCJSerializationInstantiator(Class<T> cls) {
        super(cls);
        this.superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(cls);
    }

    public T newInstance() {
        try {
            return this.type.cast(newObjectMethod.invoke(dummyStream, new Object[]{this.type, this.superType}));
        } catch (Exception e) {
            throw new ObjenesisException((Throwable) e);
        }
    }
}
