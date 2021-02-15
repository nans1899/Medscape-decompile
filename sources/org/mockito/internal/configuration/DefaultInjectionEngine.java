package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import java.util.Set;
import org.mockito.internal.configuration.injection.MockInjection;

public class DefaultInjectionEngine {
    public void injectMocksOnFields(Set<Field> set, Set<Object> set2, Object obj) {
        MockInjection.onFields(set, obj).withMocks(set2).tryConstructorInjection().tryPropertyOrFieldInjection().handleSpyAnnotation().apply();
    }
}
