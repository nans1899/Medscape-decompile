package org.mockito.internal.creation.instance;

import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider2;

public class DefaultInstantiatorProvider implements InstantiatorProvider2 {
    private static final Instantiator INSTANCE = new ObjenesisInstantiator();

    public Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings) {
        if (mockCreationSettings == null || mockCreationSettings.getConstructorArgs() == null) {
            return INSTANCE;
        }
        return new ConstructorInstantiator(mockCreationSettings.getOuterClassInstance() != null, mockCreationSettings.getConstructorArgs());
    }
}
