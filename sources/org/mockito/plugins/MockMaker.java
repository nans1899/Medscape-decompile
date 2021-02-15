package org.mockito.plugins;

import org.mockito.Incubating;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;

public interface MockMaker {

    @Incubating
    public interface TypeMockability {
        boolean mockable();

        String nonMockableReason();
    }

    <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler);

    MockHandler getHandler(Object obj);

    @Incubating
    TypeMockability isTypeMockable(Class<?> cls);

    void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings);
}
