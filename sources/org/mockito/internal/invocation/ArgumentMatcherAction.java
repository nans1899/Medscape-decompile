package org.mockito.internal.invocation;

import org.mockito.ArgumentMatcher;

public interface ArgumentMatcherAction {
    boolean apply(ArgumentMatcher<?> argumentMatcher, Object obj);
}
