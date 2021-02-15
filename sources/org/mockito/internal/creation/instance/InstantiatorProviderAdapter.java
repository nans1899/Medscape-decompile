package org.mockito.internal.creation.instance;

import org.mockito.creation.instance.InstantiationException;
import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;

public class InstantiatorProviderAdapter implements InstantiatorProvider2 {
    /* access modifiers changed from: private */
    public final InstantiatorProvider provider;

    public InstantiatorProviderAdapter(InstantiatorProvider instantiatorProvider) {
        this.provider = instantiatorProvider;
    }

    public Instantiator getInstantiator(final MockCreationSettings<?> mockCreationSettings) {
        return new Instantiator() {
            public <T> T newInstance(Class<T> cls) throws InstantiationException {
                try {
                    return InstantiatorProviderAdapter.this.provider.getInstantiator(mockCreationSettings).newInstance(cls);
                } catch (InstantiationException e) {
                    throw new InstantiationException(e.getMessage(), e.getCause());
                }
            }
        };
    }
}
