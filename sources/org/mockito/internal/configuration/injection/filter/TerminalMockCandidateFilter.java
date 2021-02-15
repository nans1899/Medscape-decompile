package org.mockito.internal.configuration.injection.filter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.reflection.BeanPropertySetter;
import org.mockito.internal.util.reflection.FieldSetter;

public class TerminalMockCandidateFilter implements MockCandidateFilter {
    public OngoingInjector filterCandidate(Collection<Object> collection, final Field field, List<Field> list, final Object obj) {
        if (collection.size() != 1) {
            return OngoingInjector.nop;
        }
        final Object next = collection.iterator().next();
        return new OngoingInjector() {
            public Object thenInject() {
                try {
                    if (!new BeanPropertySetter(obj, field).set(next)) {
                        FieldSetter.setField(obj, field, next);
                    }
                    return next;
                } catch (RuntimeException e) {
                    throw Reporter.cannotInjectDependency(field, next, e);
                }
            }
        };
    }
}
