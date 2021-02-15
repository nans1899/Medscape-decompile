package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.FieldInitializer;

public class ConstructorInjection extends MockInjectionStrategy {
    public boolean processInjection(Field field, Object obj, Set<Object> set) {
        try {
            return new FieldInitializer(obj, field, (FieldInitializer.ConstructorArgumentResolver) new SimpleArgumentResolver(set)).initialize().fieldWasInitializedUsingContructorArgs();
        } catch (MockitoException e) {
            if (!(e.getCause() instanceof InvocationTargetException)) {
                return false;
            }
            throw Reporter.fieldInitialisationThrewException(field, e.getCause().getCause());
        }
    }

    static class SimpleArgumentResolver implements FieldInitializer.ConstructorArgumentResolver {
        final Set<Object> objects;

        public SimpleArgumentResolver(Set<Object> set) {
            this.objects = set;
        }

        public Object[] resolveTypeInstances(Class<?>... clsArr) {
            ArrayList arrayList = new ArrayList(clsArr.length);
            for (Class<?> objectThatIsAssignableFrom : clsArr) {
                arrayList.add(objectThatIsAssignableFrom(objectThatIsAssignableFrom));
            }
            return arrayList.toArray();
        }

        private Object objectThatIsAssignableFrom(Class<?> cls) {
            for (Object next : this.objects) {
                if (cls.isAssignableFrom(next.getClass())) {
                    return next;
                }
            }
            return null;
        }
    }
}
