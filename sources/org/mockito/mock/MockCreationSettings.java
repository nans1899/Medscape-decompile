package org.mockito.mock;

import java.util.List;
import java.util.Set;
import org.mockito.Incubating;
import org.mockito.NotExtensible;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.stubbing.Answer;

@NotExtensible
public interface MockCreationSettings<T> {
    @Incubating
    Object[] getConstructorArgs();

    Answer<?> getDefaultAnswer();

    Set<Class<?>> getExtraInterfaces();

    List<InvocationListener> getInvocationListeners();

    MockName getMockName();

    @Incubating
    Object getOuterClassInstance();

    SerializableMode getSerializableMode();

    Object getSpiedInstance();

    List<StubbingLookupListener> getStubbingLookupListeners();

    Class<T> getTypeToMock();

    @Incubating
    List<VerificationStartedListener> getVerificationStartedListeners();

    @Incubating
    boolean isLenient();

    boolean isSerializable();

    boolean isStripAnnotations();

    boolean isStubOnly();

    @Incubating
    boolean isUsingConstructor();
}
