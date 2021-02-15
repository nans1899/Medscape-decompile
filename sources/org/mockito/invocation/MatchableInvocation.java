package org.mockito.invocation;

import java.util.List;
import org.mockito.ArgumentMatcher;

public interface MatchableInvocation extends DescribedInvocation {
    void captureArgumentsFrom(Invocation invocation);

    Invocation getInvocation();

    List<ArgumentMatcher> getMatchers();

    boolean hasSameMethod(Invocation invocation);

    boolean hasSimilarMethod(Invocation invocation);

    boolean matches(Invocation invocation);
}
