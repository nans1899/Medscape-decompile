package org.mockito;

import java.io.Serializable;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.mock.SerializableMode;
import org.mockito.stubbing.Answer;

public interface MockSettings extends Serializable {
    @Incubating
    <T> MockCreationSettings<T> build(Class<T> cls);

    MockSettings defaultAnswer(Answer answer);

    MockSettings extraInterfaces(Class<?>... clsArr);

    MockSettings invocationListeners(InvocationListener... invocationListenerArr);

    @Incubating
    MockSettings lenient();

    MockSettings name(String str);

    @Incubating
    MockSettings outerInstance(Object obj);

    MockSettings serializable();

    MockSettings serializable(SerializableMode serializableMode);

    MockSettings spiedInstance(Object obj);

    MockSettings stubOnly();

    MockSettings stubbingLookupListeners(StubbingLookupListener... stubbingLookupListenerArr);

    @Incubating
    MockSettings useConstructor(Object... objArr);

    MockSettings verboseLogging();

    @Incubating
    MockSettings verificationStartedListeners(VerificationStartedListener... verificationStartedListenerArr);

    @Incubating
    MockSettings withoutAnnotations();
}
