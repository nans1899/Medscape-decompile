package org.mockito;

import java.util.List;
import org.mockito.internal.matchers.CapturingMatcher;
import org.mockito.internal.util.Primitives;

public class ArgumentCaptor<T> {
    private final CapturingMatcher<T> capturingMatcher = new CapturingMatcher<>();
    private final Class<? extends T> clazz;

    private ArgumentCaptor(Class<? extends T> cls) {
        this.clazz = cls;
    }

    public T capture() {
        Mockito.argThat(this.capturingMatcher);
        return Primitives.defaultValue(this.clazz);
    }

    public T getValue() {
        return this.capturingMatcher.getLastValue();
    }

    public List<T> getAllValues() {
        return this.capturingMatcher.getAllValues();
    }

    public static <U, S extends U> ArgumentCaptor<U> forClass(Class<S> cls) {
        return new ArgumentCaptor<>(cls);
    }
}
