package org.mockito.internal.progress;

import java.util.Set;
import org.mockito.listeners.MockitoListener;
import org.mockito.listeners.VerificationListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

public interface MockingProgress {
    void addListener(MockitoListener mockitoListener);

    void clearListeners();

    ArgumentMatcherStorage getArgumentMatcherStorage();

    VerificationMode maybeVerifyLazily(VerificationMode verificationMode);

    void mockingStarted(Object obj, MockCreationSettings mockCreationSettings);

    OngoingStubbing<?> pullOngoingStubbing();

    VerificationMode pullVerificationMode();

    void removeListener(MockitoListener mockitoListener);

    void reportOngoingStubbing(OngoingStubbing<?> ongoingStubbing);

    void reset();

    void resetOngoingStubbing();

    void setVerificationStrategy(VerificationStrategy verificationStrategy);

    void stubbingCompleted();

    void stubbingStarted();

    void validateState();

    Set<VerificationListener> verificationListeners();

    void verificationStarted(VerificationMode verificationMode);
}
