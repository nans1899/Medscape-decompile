package org.mockito.junit;

import org.junit.rules.TestRule;
import org.mockito.Incubating;
import org.mockito.exceptions.base.MockitoAssertionError;

@Incubating
public interface VerificationCollector extends TestRule {
    @Incubating
    VerificationCollector assertLazily();

    @Incubating
    void collectAndReport() throws MockitoAssertionError;
}
