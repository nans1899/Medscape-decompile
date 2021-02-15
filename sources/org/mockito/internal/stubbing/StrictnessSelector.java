package org.mockito.internal.stubbing;

import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Stubbing;

public class StrictnessSelector {
    public static Strictness determineStrictness(Stubbing stubbing, MockCreationSettings mockCreationSettings, Strictness strictness) {
        if (stubbing == null || stubbing.getStrictness() == null) {
            return mockCreationSettings.isLenient() ? Strictness.LENIENT : strictness;
        }
        return stubbing.getStrictness();
    }
}
