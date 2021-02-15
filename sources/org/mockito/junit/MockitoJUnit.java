package org.mockito.junit;

import org.mockito.Incubating;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.internal.junit.VerificationCollectorImpl;
import org.mockito.quality.Strictness;

public class MockitoJUnit {
    public static MockitoRule rule() {
        return new JUnitRule(Plugins.getMockitoLogger(), Strictness.WARN);
    }

    @Incubating
    public static VerificationCollector collector() {
        return new VerificationCollectorImpl();
    }
}
