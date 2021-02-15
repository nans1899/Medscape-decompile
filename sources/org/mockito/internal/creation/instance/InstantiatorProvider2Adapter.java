package org.mockito.internal.creation.instance;

import org.mockito.creation.instance.InstantiationException;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;

public class InstantiatorProvider2Adapter implements InstantiatorProvider {
    /* access modifiers changed from: private */
    public final InstantiatorProvider2 provider;

    public InstantiatorProvider2Adapter(InstantiatorProvider2 instantiatorProvider2) {
        this.provider = instantiatorProvider2;
    }

    public Instantiator getInstantiator(final MockCreationSettings<?> mockCreationSettings) {
        return new Instantiator() {
            public <T> T newInstance(Class<T> cls) throws InstantiationException {
                try {
                    return InstantiatorProvider2Adapter.this.provider.getInstantiator(mockCreationSettings).newInstance(cls);
                } catch (InstantiationException e) {
                    throw new InstantiationException(e.getMessage(), e.getCause());
                }
            }
        };
    }
}
