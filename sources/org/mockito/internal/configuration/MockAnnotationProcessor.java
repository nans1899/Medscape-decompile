package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;

public class MockAnnotationProcessor implements FieldAnnotationProcessor<Mock> {
    public Object process(Mock mock, Field field) {
        return processAnnotationForMock(mock, field.getType(), field.getName());
    }

    public static Object processAnnotationForMock(Mock mock, Class<?> cls, String str) {
        MockSettings withSettings = Mockito.withSettings();
        if (mock.extraInterfaces().length > 0) {
            withSettings.extraInterfaces(mock.extraInterfaces());
        }
        if ("".equals(mock.name())) {
            withSettings.name(str);
        } else {
            withSettings.name(mock.name());
        }
        if (mock.serializable()) {
            withSettings.serializable();
        }
        if (mock.stubOnly()) {
            withSettings.stubOnly();
        }
        if (mock.lenient()) {
            withSettings.lenient();
        }
        withSettings.defaultAnswer(mock.answer());
        return Mockito.mock(cls, withSettings);
    }
}
