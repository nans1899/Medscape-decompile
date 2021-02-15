package org.mockito.junit;

import org.junit.rules.MethodRule;
import org.mockito.Incubating;
import org.mockito.quality.Strictness;

public interface MockitoRule extends MethodRule {
    MockitoRule silent();

    @Incubating
    MockitoRule strictness(Strictness strictness);
}
