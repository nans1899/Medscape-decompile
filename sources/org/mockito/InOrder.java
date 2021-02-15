package org.mockito;

import org.mockito.verification.VerificationMode;

public interface InOrder {
    <T> T verify(T t);

    <T> T verify(T t, VerificationMode verificationMode);

    void verifyNoMoreInteractions();
}
