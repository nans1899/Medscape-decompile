package org.mockito.internal.matchers;

import org.mockito.ArgumentMatcher;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.invocation.Location;

public class LocalizedMatcher {
    private final Location location = new LocationImpl();
    private final ArgumentMatcher<?> matcher;

    public LocalizedMatcher(ArgumentMatcher<?> argumentMatcher) {
        this.matcher = argumentMatcher;
    }

    public Location getLocation() {
        return this.location;
    }

    public ArgumentMatcher<?> getMatcher() {
        return this.matcher;
    }
}
