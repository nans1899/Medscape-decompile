package org.mockito.plugins;

import org.mockito.internal.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;

@Deprecated
public interface InstantiatorProvider {
    @Deprecated
    Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings);
}
