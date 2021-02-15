package org.mockito.internal.configuration.injection.scanner;

import java.lang.reflect.Field;
import java.util.Set;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.collections.Sets;
import org.mockito.internal.util.reflection.FieldReader;

public class MockScanner {
    private final Class<?> clazz;
    private final Object instance;

    public MockScanner(Object obj, Class<?> cls) {
        this.instance = obj;
        this.clazz = cls;
    }

    public void addPreparedMocks(Set<Object> set) {
        set.addAll(scan());
    }

    private Set<Object> scan() {
        Set<Object> newMockSafeHashSet = Sets.newMockSafeHashSet(new Object[0]);
        for (Field field : this.clazz.getDeclaredFields()) {
            Object preparedMock = preparedMock(new FieldReader(this.instance, field).read(), field);
            if (preparedMock != null) {
                newMockSafeHashSet.add(preparedMock);
            }
        }
        return newMockSafeHashSet;
    }

    private Object preparedMock(Object obj, Field field) {
        if (isAnnotatedByMockOrSpy(field)) {
            return obj;
        }
        if (!isMockOrSpy(obj)) {
            return null;
        }
        MockUtil.maybeRedefineMockName(obj, field.getName());
        return obj;
    }

    private boolean isAnnotatedByMockOrSpy(Field field) {
        return field.isAnnotationPresent(Spy.class) || field.isAnnotationPresent(Mock.class);
    }

    private boolean isMockOrSpy(Object obj) {
        return MockUtil.isMock(obj) || MockUtil.isSpy(obj);
    }
}
