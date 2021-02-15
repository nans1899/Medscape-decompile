package org.mockito;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.GlobalConfiguration;

public class MockitoAnnotations {
    public static void initMocks(Object obj) {
        if (obj != null) {
            new GlobalConfiguration().tryGetPluginAnnotationEngine().process(obj.getClass(), obj);
            return;
        }
        throw new MockitoException("testClass cannot be null. For info how to use @Mock annotations see examples in javadoc for MockitoAnnotations class");
    }
}
