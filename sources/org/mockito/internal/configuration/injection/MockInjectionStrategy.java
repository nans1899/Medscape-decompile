package org.mockito.internal.configuration.injection;

import java.lang.reflect.Field;
import java.util.Set;

public abstract class MockInjectionStrategy {
    private MockInjectionStrategy nextStrategy;

    /* access modifiers changed from: protected */
    public abstract boolean processInjection(Field field, Object obj, Set<Object> set);

    public static MockInjectionStrategy nop() {
        return new MockInjectionStrategy() {
            /* access modifiers changed from: protected */
            public boolean processInjection(Field field, Object obj, Set<Object> set) {
                return false;
            }
        };
    }

    public MockInjectionStrategy thenTry(MockInjectionStrategy mockInjectionStrategy) {
        MockInjectionStrategy mockInjectionStrategy2 = this.nextStrategy;
        if (mockInjectionStrategy2 != null) {
            mockInjectionStrategy2.thenTry(mockInjectionStrategy);
        } else {
            this.nextStrategy = mockInjectionStrategy;
        }
        return mockInjectionStrategy;
    }

    public boolean process(Field field, Object obj, Set<Object> set) {
        if (processInjection(field, obj, set)) {
            return true;
        }
        return relayProcessToNextStrategy(field, obj, set);
    }

    private boolean relayProcessToNextStrategy(Field field, Object obj, Set<Object> set) {
        MockInjectionStrategy mockInjectionStrategy = this.nextStrategy;
        return mockInjectionStrategy != null && mockInjectionStrategy.process(field, obj, set);
    }
}
