package org.mockito.internal.invocation;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.CapturesArguments;
import org.mockito.internal.reporting.PrintSettings;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.MatchableInvocation;

public class InvocationMatcher implements MatchableInvocation, DescribedInvocation, Serializable {
    private final Invocation invocation;
    private final List<ArgumentMatcher<?>> matchers;

    public InvocationMatcher(Invocation invocation2, List<ArgumentMatcher> list) {
        this.invocation = invocation2;
        if (list.isEmpty()) {
            this.matchers = ArgumentsProcessor.argumentsToMatchers(invocation2.getArguments());
        } else {
            this.matchers = list;
        }
    }

    public InvocationMatcher(Invocation invocation2) {
        this(invocation2, Collections.emptyList());
    }

    public static List<InvocationMatcher> createFrom(List<Invocation> list) {
        LinkedList linkedList = new LinkedList();
        for (Invocation invocationMatcher : list) {
            linkedList.add(new InvocationMatcher(invocationMatcher));
        }
        return linkedList;
    }

    public Method getMethod() {
        return this.invocation.getMethod();
    }

    public Invocation getInvocation() {
        return this.invocation;
    }

    public List<ArgumentMatcher> getMatchers() {
        return this.matchers;
    }

    public String toString() {
        return new PrintSettings().print(this.matchers, this.invocation);
    }

    public boolean matches(Invocation invocation2) {
        return this.invocation.getMock().equals(invocation2.getMock()) && hasSameMethod(invocation2) && argumentsMatch(invocation2);
    }

    public boolean hasSimilarMethod(Invocation invocation2) {
        if (!getMethod().getName().equals(invocation2.getMethod().getName()) || invocation2.isVerified() || getInvocation().getMock() != invocation2.getMock()) {
            return false;
        }
        if (hasSameMethod(invocation2)) {
            return true;
        }
        return !argumentsMatch(invocation2);
    }

    public boolean hasSameMethod(Invocation invocation2) {
        Method method = this.invocation.getMethod();
        Method method2 = invocation2.getMethod();
        if (method.getName() == null || !method.getName().equals(method2.getName())) {
            return false;
        }
        return Arrays.equals(method.getParameterTypes(), method2.getParameterTypes());
    }

    public Location getLocation() {
        return this.invocation.getLocation();
    }

    public void captureArgumentsFrom(Invocation invocation2) {
        MatcherApplicationStrategy.getMatcherApplicationStrategyFor(invocation2, this.matchers).forEachMatcherAndArgument(captureArgument());
    }

    private ArgumentMatcherAction captureArgument() {
        return new ArgumentMatcherAction() {
            public boolean apply(ArgumentMatcher<?> argumentMatcher, Object obj) {
                if (!(argumentMatcher instanceof CapturesArguments)) {
                    return true;
                }
                ((CapturesArguments) argumentMatcher).captureFrom(obj);
                return true;
            }
        };
    }

    private boolean argumentsMatch(Invocation invocation2) {
        return MatcherApplicationStrategy.getMatcherApplicationStrategyFor(invocation2, getMatchers()).forEachMatcherAndArgument(TypeSafeMatching.matchesTypeSafe());
    }
}
