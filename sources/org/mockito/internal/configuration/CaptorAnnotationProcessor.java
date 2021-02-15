package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.reflection.GenericMaster;

public class CaptorAnnotationProcessor implements FieldAnnotationProcessor<Captor> {
    public Object process(Captor captor, Field field) {
        if (ArgumentCaptor.class.isAssignableFrom(field.getType())) {
            return ArgumentCaptor.forClass(new GenericMaster().getGenericType(field));
        }
        throw new MockitoException("@Captor field must be of the type ArgumentCaptor.\nField: '" + field.getName() + "' has wrong type\nFor info how to use @Captor annotations see examples in javadoc for MockitoAnnotations class.");
    }
}
