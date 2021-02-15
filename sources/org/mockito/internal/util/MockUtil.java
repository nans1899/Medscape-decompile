package org.mockito.internal.util;

import org.mockito.Mockito;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.handler.MockHandlerFactory;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.util.reflection.LenientCopyTool;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.mock.MockName;
import org.mockito.plugins.MockMaker;

public class MockUtil {
    private static final MockMaker mockMaker = Plugins.getMockMaker();

    private MockUtil() {
    }

    public static MockMaker.TypeMockability typeMockabilityOf(Class<?> cls) {
        return mockMaker.isTypeMockable(cls);
    }

    public static <T> T createMock(MockCreationSettings<T> mockCreationSettings) {
        T createMock = mockMaker.createMock(mockCreationSettings, MockHandlerFactory.createMockHandler(mockCreationSettings));
        Object spiedInstance = mockCreationSettings.getSpiedInstance();
        if (spiedInstance != null) {
            new LenientCopyTool().copyToMock(spiedInstance, createMock);
        }
        return createMock;
    }

    public static <T> void resetMock(T t) {
        MockCreationSettings mockSettings = getMockHandler(t).getMockSettings();
        mockMaker.resetMock(t, MockHandlerFactory.createMockHandler(mockSettings), mockSettings);
    }

    public static <T> MockHandler<T> getMockHandler(T t) {
        if (t == null) {
            throw new NotAMockException("Argument should be a mock, but is null!");
        } else if (isMock(t)) {
            return mockMaker.getHandler(t);
        } else {
            throw new NotAMockException("Argument should be a mock, but is: " + t.getClass());
        }
    }

    public static InvocationContainerImpl getInvocationContainer(Object obj) {
        return (InvocationContainerImpl) getMockHandler(obj).getInvocationContainer();
    }

    public static boolean isSpy(Object obj) {
        return isMock(obj) && getMockSettings(obj).getDefaultAnswer() == Mockito.CALLS_REAL_METHODS;
    }

    public static boolean isMock(Object obj) {
        return (obj == null || mockMaker.getHandler(obj) == null) ? false : true;
    }

    public static MockName getMockName(Object obj) {
        return getMockHandler(obj).getMockSettings().getMockName();
    }

    public static void maybeRedefineMockName(Object obj, String str) {
        MockName mockName = getMockName(obj);
        MockCreationSettings mockSettings = getMockHandler(obj).getMockSettings();
        if (mockName.isDefault() && (mockSettings instanceof CreationSettings)) {
            ((CreationSettings) mockSettings).setMockName(new MockNameImpl(str));
        }
    }

    public static MockCreationSettings getMockSettings(Object obj) {
        return getMockHandler(obj).getMockSettings();
    }
}
