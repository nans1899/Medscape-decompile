package org.mockito.internal.creation.bytebuddy;

import org.mockito.Incubating;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

public class ByteBuddyMockMaker implements ClassCreatingMockMaker {
    private ClassCreatingMockMaker defaultByteBuddyMockMaker = new SubclassByteBuddyMockMaker();

    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        return this.defaultByteBuddyMockMaker.createMock(mockCreationSettings, mockHandler);
    }

    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        return this.defaultByteBuddyMockMaker.createMockType(mockCreationSettings);
    }

    public MockHandler getHandler(Object obj) {
        return this.defaultByteBuddyMockMaker.getHandler(obj);
    }

    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        this.defaultByteBuddyMockMaker.resetMock(obj, mockHandler, mockCreationSettings);
    }

    @Incubating
    public MockMaker.TypeMockability isTypeMockable(Class<?> cls) {
        return this.defaultByteBuddyMockMaker.isTypeMockable(cls);
    }
}
