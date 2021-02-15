package org.mockito.internal.creation.instance;

import org.mockito.creation.instance.Instantiator;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.objenesis.ObjenesisStd;

class ObjenesisInstantiator implements Instantiator {
    private final ObjenesisStd objenesis = new ObjenesisStd(new GlobalConfiguration().enableClassCache());

    ObjenesisInstantiator() {
    }

    public <T> T newInstance(Class<T> cls) {
        return this.objenesis.newInstance(cls);
    }
}
